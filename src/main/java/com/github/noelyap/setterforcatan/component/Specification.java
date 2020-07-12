package com.github.noelyap.setterforcatan.component;

import com.github.noelyap.setterforcatan.board.protogen.ChitsTilesMapping;
import com.github.noelyap.setterforcatan.board.protogen.CoordinateTilesMapping;
import com.github.noelyap.setterforcatan.protogen.ChitOuterClass.Chit;
import com.github.noelyap.setterforcatan.protogen.ChitOuterClass.Chits;
import com.github.noelyap.setterforcatan.protogen.ConfigurationOuterClass.Configuration;
import com.github.noelyap.setterforcatan.protogen.CoordinateOuterClass.Coordinate;
import com.github.noelyap.setterforcatan.protogen.CoordinateOuterClass.Coordinates;
import com.github.noelyap.setterforcatan.protogen.MarkerOuterClass.Marker;
import com.github.noelyap.setterforcatan.protogen.TileOuterClass.Tile;
import com.github.noelyap.setterforcatan.protogen.TileOuterClass.Tiles;
import com.github.noelyap.setterforcatan.util.ChitUtils;
import com.github.noelyap.setterforcatan.util.MersenneTwister;
import com.google.common.annotations.VisibleForTesting;
import com.google.protobuf.GeneratedMessageV3;
import io.vavr.Function1;
import io.vavr.Function4;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import io.vavr.collection.Array;
import io.vavr.collection.HashMap;
import io.vavr.collection.HashMultimap;
import io.vavr.collection.HashSet;
import io.vavr.collection.Map;
import io.vavr.collection.Multimap;
import io.vavr.collection.Set;
import io.vavr.collection.Stream;
import java.util.Random;

public class Specification {
  @SuppressWarnings("serial")
  public static class InvalidSpecificationError extends Exception {
    public InvalidSpecificationError(final String message) {
      super(message);
    }
  }

  private final MersenneTwister chitsShufflerPrng = new MersenneTwister();
  private final MersenneTwister coordinatesShufflerPrng = new MersenneTwister();

  private final Map<String, Tuple2<Array<Tile>, Boolean>> tiles;
  private final Map<String, Array<Coordinate>> coordinates;
  private final Map<String, Array<Chit>> chits;
  private final Map<String, Array<String>> coordinatesTilesMap;
  private final Map<String, Array<String>> chitsTilesMap;
  private final Marker[] markers;
  private final Function1<Configuration, Boolean> validateConfiguration;
  private final Function1<Configuration, Boolean> filterConfigurationScorer;

  public Specification(
      final Set<Tiles> tiles,
      final Set<Coordinates> coordinates,
      final Set<Chits> chits,
      final Set<CoordinateTilesMapping> coordinatesTilesMap,
      final Set<ChitsTilesMapping> chitsTilesMap)
      throws InvalidSpecificationError {
    this(
        HashMap.ofEntries(
            tiles.map(
                t -> {
                  return Tuple.of(
                      t.getName(), Tuple.of(Array.ofAll(t.getTilesList()), t.getChitless()));
                })),
        HashMap.ofEntries(
            coordinates.map(
                c -> {
                  return Tuple.of(c.getName(), Array.ofAll(c.getCoordinatesList()));
                })),
        HashMap.ofEntries(
            chits.map(
                c -> {
                  return Tuple.of(c.getName(), Array.ofAll(c.getChitsList()));
                })),
        HashMap.ofEntries(
            coordinatesTilesMap.map(
                ct -> {
                  return Tuple.of(ct.getCoordinatesName(), Array.ofAll(ct.getTilesNamesList()));
                })),
        HashMap.ofEntries(
            chitsTilesMap.map(
                ct -> {
                  return Tuple.of(ct.getChitsName(), Array.ofAll(ct.getTilesNamesList()));
                })));
  }

  public Specification(
      final Map<String, Tuple2<Array<Tile>, Boolean>> tiles,
      final Map<String, Array<Coordinate>> coordinates,
      final Map<String, Array<Chit>> chits,
      final Map<String, Array<String>> coordinatesTilesMap,
      final Map<String, Array<String>> chitsTilesMap)
      throws InvalidSpecificationError {
    this(
        tiles,
        coordinates,
        chits,
        coordinatesTilesMap,
        chitsTilesMap,
        new Marker[0],
        c -> true,
        c -> true);
  }

  public Specification(
      final Map<String, Tuple2<Array<Tile>, Boolean>> tiles,
      final Map<String, Array<Coordinate>> coordinates,
      final Map<String, Array<Chit>> chits,
      final Map<String, Array<String>> coordinatesTilesMap,
      final Map<String, Array<String>> chitsTilesMap,
      final Marker[] markers,
      final Function1<Configuration, Boolean> validateConfiguration,
      final Function1<Configuration, Boolean> filterConfigurationScorer)
      throws InvalidSpecificationError {
    this.tiles = tiles;
    this.coordinates = coordinates;
    this.chits = chits;
    this.coordinatesTilesMap = coordinatesTilesMap;
    this.chitsTilesMap = chitsTilesMap;
    this.markers = markers;
    this.validateConfiguration = validateConfiguration;
    this.filterConfigurationScorer = filterConfigurationScorer;

    this.validate();
  }

  public Specification withFisheries(final Array<Coordinate> fisheryCoordinates)
      throws InvalidSpecificationError {
    final Tile fisheryTile = Tile.newBuilder().setType(Tile.Type.FISHERY).build();
    final Stream<Chit> fisheryChits =
        Stream.of(4, 10, 5, 9, 6, 8, 5, 9).map(v -> ChitUtils.newChit(v));

    final Tile lakeTile = Tile.newBuilder().setType(Tile.Type.LAKE).build();
    final Stream<Chit> lakeChits =
        Stream.of(ChitUtils.newChit(2, 3, 11, 12), ChitUtils.newChit(4, 10));

    // TODO(nyap): Create `DESERT_OR_LAKE` constant.
    final int desertCount = tiles.get("«DESERT»|«LAKE»").map(t2 -> t2._1.size()).getOrElse(0);
    final int fisheryCount = fisheryCoordinates.size();

    final Map<String, Tuple2<Array<Tile>, Boolean>> tiles =
        this.tiles
            .filter(t2 -> !"«DESERT»|«LAKE»".equals(t2._1))
            .put("«LAKE»", Tuple.of(Array.fill(desertCount, lakeTile), false))
            .put("«FISHERY»", Tuple.of(Array.fill(fisheryCount, fisheryTile), false));

    // TODO(nyap): Create `FISHERY` constant.
    // TODO(nyap): Create `LAKE` constant.
    final Map<String, Array<Chit>> chits =
        this.chits
            .put("«LAKE»", Array.ofAll(lakeChits.cycle().slice(0, desertCount)))
            .put("«FISHERY»", Array.ofAll(fisheryChits.cycle().slice(0, fisheryCount)));
    final Map<String, Array<String>> chitsTilesMap =
        this.chitsTilesMap
            .put("«LAKE»", Array.of("«LAKE»"))
            .put("«FISHERY»", Array.of("«FISHERY»"));

    final Map<String, Array<Coordinate>> coordinates =
        HashMap.ofEntries(
                this.coordinates.map(
                    t2 -> Tuple.of("«DESERT»|«LAKE»".equals(t2._1) ? "«LAKE»" : t2._1, t2._2)))
            .put("«FISHERY»", fisheryCoordinates);
    final Map<String, Array<String>> coordinatesTilesMap =
        this.coordinatesTilesMap
            .filter(t2 -> !"«DESERT»|«LAKE»".equals(t2._1))
            .put("«LAKE»", Array.of("«LAKE»"))
            .put("«FISHERY»", Array.of("«FISHERY»"));

    return new Specification(
        tiles,
        coordinates,
        chits,
        coordinatesTilesMap,
        chitsTilesMap,
        this.markers,
        this.validateConfiguration,
        this.filterConfigurationScorer);
  }

  public Specification withMarkers(final Marker... markers) throws InvalidSpecificationError {
    return new Specification(
        this.tiles,
        this.coordinates,
        this.chits,
        this.coordinatesTilesMap,
        this.chitsTilesMap,
        markers,
        this.validateConfiguration,
        this.filterConfigurationScorer);
  }

  public Specification withConfigurationValidator(
      final Function1<Configuration, Boolean> configurationValidator)
      throws InvalidSpecificationError {
    return new Specification(
        this.tiles,
        this.coordinates,
        this.chits,
        this.coordinatesTilesMap,
        this.chitsTilesMap,
        this.markers,
        configurationValidator,
        this.filterConfigurationScorer);
  }

  public Specification withConfigurationScorerFilter(
      final Function1<Configuration, Boolean> configurationScorerFilter)
      throws InvalidSpecificationError {
    return new Specification(
        this.tiles,
        this.coordinates,
        this.chits,
        this.coordinatesTilesMap,
        this.chitsTilesMap,
        this.markers,
        this.validateConfiguration,
        configurationScorerFilter);
  }

  @SuppressWarnings("deprecation")
  // TODO(nyap): Check for duplicate coordinates errors.
  private void validate() throws InvalidSpecificationError {
    final Array<
            Function4<
                String,
                Map<String, Tuple2<Array<Tile>, Boolean>>,
                Map<String, Array<GeneratedMessageV3>>,
                Map<String, Array<String>>,
                Set<String>>>
        fns =
            Array.of(
                Specification::checkForUndefinedTilesErrors,
                Specification::checkForUnreferencedTilesErrors,
                Specification::checkForUndefinedFeaturesErrors,
                Specification::checkForUnreferencedFeaturesErrors,
                Specification::checkForFeaturesVersusTilesCountMismatchError);
    final Array<Tuple3<String, Map<String, Array<GeneratedMessageV3>>, Map<String, Array<String>>>>
        args =
            Array.of(
                Tuple.of("chits", widenFeaturesMap(chits), chitsTilesMap),
                Tuple.of("coordinates", widenFeaturesMap(coordinates), coordinatesTilesMap));

    final Set<String> errors =
        HashSet.ofAll(fns.crossProduct(args))
            .flatMap(
                t2 -> {
                  final Function4<
                          String,
                          Map<String, Tuple2<Array<Tile>, Boolean>>,
                          Map<String, Array<GeneratedMessageV3>>,
                          Map<String, Array<String>>,
                          Set<String>>
                      fn = t2._1;
                  final Tuple3<
                          String,
                          Map<String, Array<GeneratedMessageV3>>,
                          Map<String, Array<String>>>
                      arg = t2._2;

                  return fn.apply(arg._1, this.tiles, arg._2, arg._3);
                });

    if (errors.length() != 0) {
      throw new InvalidSpecificationError(
          "Invalid Specification:\n\t" + String.join("\n\t", errors.toJavaArray(String[]::new)));
    }
  }

  @SuppressWarnings("deprecation")
  @VisibleForTesting
  static Set<String> checkForUndefinedTilesErrors(
      final String feature,
      final Map<String, Tuple2<Array<Tile>, Boolean>> tiles,
      final Map<String, Array<GeneratedMessageV3>> features,
      final Map<String, Array<String>> featuresTilesMap) {
    return featuresTilesMap
        .map(
            t2 -> {
              final String featuresName = t2._1;
              final Array<String> tilesNames = t2._2;

              return Tuple.of(featuresName, HashSet.ofAll(tilesNames).diff(tiles.keySet()));
            })
        .filter(t2 -> !t2._2.isEmpty())
        .map(
            t2 ->
                String.format(
                    "[%s] tiles referenced in %sTilesMap \"%s\" are not defined.",
                    String.join(", ", t2._2), feature, t2._1))
        .toSet();
  }

  @SuppressWarnings("deprecation")
  @VisibleForTesting
  static Set<String> checkForUnreferencedTilesErrors(
      final String feature,
      final Map<String, Tuple2<Array<Tile>, Boolean>> tiles,
      final Map<String, Array<GeneratedMessageV3>> features,
      final Map<String, Array<String>> featuresTilesMap) {
    return tiles
        .filter(t2 -> !t2._2._2)
        .keySet()
        .diff(featuresTilesMap.flatMap(t2 -> HashSet.ofAll(t2._2)).toSet())
        .map(key -> String.format("\"%s\" tiles is not referenced in %sTilesMap.", key, feature));
  }

  @VisibleForTesting
  static Set<String> checkForUndefinedFeaturesErrors(
      final String feature,
      final Map<String, Tuple2<Array<Tile>, Boolean>> tiles,
      final Map<String, Array<GeneratedMessageV3>> features,
      final Map<String, Array<String>> featuresTilesMap) {
    final Set<String> featuresTilesMapKeys = featuresTilesMap.keySet();
    final Set<String> featureKeys = features.keySet();

    return featuresTilesMapKeys
        .diff(featureKeys)
        .map(
            key ->
                String.format(
                    "\"%s\" referenced in %sTilesMap not defined in %s.", key, feature, feature));
  }

  @VisibleForTesting
  static Set<String> checkForUnreferencedFeaturesErrors(
      final String feature,
      final Map<String, Tuple2<Array<Tile>, Boolean>> tiles,
      final Map<String, Array<GeneratedMessageV3>> features,
      final Map<String, Array<String>> featuresTilesMap) {
    final Set<String> featuresTilesMapKeys = featuresTilesMap.keySet();
    final Set<String> featureKeys = features.keySet();

    return featureKeys
        .diff(featuresTilesMapKeys)
        .map(
            key ->
                String.format("\"%s\" in %s not referenced in %sTilesMap.", key, feature, feature));
  }

  @SuppressWarnings("deprecation")
  @VisibleForTesting
  static Set<String> checkForFeaturesVersusTilesCountMismatchError(
      final String feature,
      final Map<String, Tuple2<Array<Tile>, Boolean>> tiles,
      final Map<String, Array<GeneratedMessageV3>> features,
      final Map<String, Array<String>> featuresTilesMap) {
    return featuresTilesMap
        .map(
            t2 -> {
              final String featuresName = t2._1;
              final Array<String> tilesNames = t2._2;

              final int featuresCount =
                  features.get(featuresName).getOrElse(Array.<GeneratedMessageV3>empty()).size();

              final int tilesCount =
                  tilesNames
                      .map(
                          tileName ->
                              tiles
                                  .get(tileName)
                                  .getOrElse(Tuple.of(Array.<Tile>empty(), false))
                                  ._1
                                  .size())
                      .sum()
                      .intValue();

              return Tuple.of(featuresName, featuresCount, tilesNames, tilesCount);
            })
        .filter(t4 -> t4._2 != t4._4)
        .map(
            t4 -> {
              final String featuresName = t4._1;
              final int featuresCount = t4._2;
              final Array<String> tilesNames = t4._3;
              final int tilesCount = t4._4;

              return String.format(
                  "%d %s in \"%s\" do not match %d tiles in [%s].",
                  featuresCount, feature, featuresName, tilesCount, String.join(", ", tilesNames));
            })
        .toSet();
  }

  @VisibleForTesting
  Array<Configuration> toConfiguration() {
    final Multimap<Tile, Chit> tileChitsMap =
        shuffleTiles(chitsShufflerPrng, tiles, chits, chitsTilesMap);
    final Multimap<Tile, Coordinate> tileCoordinateMap =
        shuffleTiles(coordinatesShufflerPrng, tiles, coordinates, coordinatesTilesMap);

    return Array.ofAll(
        tileCoordinateMap
            .keySet()
            .flatMap(
                tile -> {
                  final Array<Coordinate> coordinates =
                      Array.ofAll(tileCoordinateMap.get(tile).get());
                  final Array<Chit> chits =
                      Array.ofAll(
                          tileChitsMap
                              .get(tile)
                              .getOrElse(
                                  Array.fill(coordinates.length(), Chit.newBuilder().build())));

                  return coordinates
                      .zip(chits)
                      .map(
                          t2 -> {
                            return Configuration.newBuilder()
                                .setTile(tile)
                                .setCoordinate(t2._1)
                                .setChit(t2._2)
                                .build();
                          });
                }));
  }

  @VisibleForTesting
  static <ChitsOrCoordinates extends GeneratedMessageV3>
      Multimap<Tile, ChitsOrCoordinates> shuffleTiles(
          final Random prng,
          final Map<String, Tuple2<Array<Tile>, Boolean>> tiles,
          final Map<String, Array<ChitsOrCoordinates>> featuresMap,
          final Map<String, Array<String>> featuresTilesMap) {
    return HashMultimap.withSeq()
        .ofEntries(
            Array.ofAll(featuresTilesMap.keySet()) // prevent dedup
                .flatMap(
                    featureName -> {
                      final Array<String> tilesNames = featuresTilesMap.get(featureName).get();
                      final Array<Tile> tileIds =
                          tilesNames.flatMap(
                              tilesName -> {
                                return tiles.get(tilesName).get()._1;
                              });
                      final Array<ChitsOrCoordinates> chitsOrCoordinates =
                          featuresMap.get(featureName).get();

                      return tileIds.zip(chitsOrCoordinates.shuffle(prng));
                    }));
  }

  @VisibleForTesting
  static <ChitsOrCoordinates extends GeneratedMessageV3>
      Map<String, Array<GeneratedMessageV3>> widenFeaturesMap(
          final Map<String, Array<ChitsOrCoordinates>> featuresMap) {
    return HashMap.ofEntries(
        featuresMap.map(t2 -> Tuple.of(t2._1, Array.<GeneratedMessageV3>ofAll(t2._2))));
  }
}