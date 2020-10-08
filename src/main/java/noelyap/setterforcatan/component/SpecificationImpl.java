package noelyap.setterforcatan.component;

import com.google.common.annotations.VisibleForTesting;
import com.google.protobuf.GeneratedMessageV3;
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
import io.vavr.collection.Traversable;
import java.util.Random;
import noelyap.setterforcatan.protogen.ChitOuterClass.Chit;
import noelyap.setterforcatan.protogen.ChitOuterClass.Chits;
import noelyap.setterforcatan.protogen.ConfigurationOuterClass.Configuration;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Coordinate;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Coordinates;
import noelyap.setterforcatan.protogen.MarkerOuterClass.Marker;
import noelyap.setterforcatan.protogen.SpecificationOuterClass.ChitsTilesMapping;
import noelyap.setterforcatan.protogen.SpecificationOuterClass.CoordinateTilesMapping;
import noelyap.setterforcatan.protogen.TileOuterClass.Tile;
import noelyap.setterforcatan.protogen.TileOuterClass.Tiles;
import noelyap.setterforcatan.util.ChitUtils;
import noelyap.setterforcatan.util.MersenneTwister;
import noelyap.setterforcatan.util.TileUtils;
import org.apache.commons.lang3.StringUtils;

public class SpecificationImpl {
  @SuppressWarnings("serial")
  public static class InvalidSpecificationError extends RuntimeException {
    public InvalidSpecificationError(final String message) {
      super(message);
    }
  }

  public static class Builder {
    private final Map<String, Tuple2<Array<Tile>, Boolean>> tiles;
    private final Map<String, Array<Coordinate>> coordinates;
    private final Map<String, Array<Chit>> chits;
    private final Map<String, Array<String>> coordinatesTilesMap;
    private final Map<String, Array<String>> chitsTilesMap;
    private final Set<Marker> markers;

    private Builder(
        final Map<String, Tuple2<Array<Tile>, Boolean>> tiles,
        final Map<String, Array<Coordinate>> coordinates,
        final Map<String, Array<Chit>> chits,
        final Map<String, Array<String>> coordinatesTilesMap,
        final Map<String, Array<String>> chitsTilesMap,
        final Set<Marker> markers) {
      this.tiles = tiles;
      this.coordinates = coordinates;
      this.chits = chits;
      this.coordinatesTilesMap = coordinatesTilesMap;
      this.chitsTilesMap = chitsTilesMap;
      this.markers = markers;
    }

    public SpecificationImpl build() throws InvalidSpecificationError {
      final SpecificationImpl specificationImpl =
          new SpecificationImpl(
              tiles, coordinates, chits, coordinatesTilesMap, chitsTilesMap, markers);

      specificationImpl.validate();

      return specificationImpl;
    }

    public Builder withFisheries(final Array<Coordinate> fisheryCoordinates) {
      final Tile fisheryTile = Tile.newBuilder().setType(Tile.Type.FISHERY).build();
      final Stream<Chit> fisheryChits = Stream.of(4, 10, 5, 9, 6, 8, 5, 9).map(ChitUtils::newChit);

      final Tile lakeTile = Tile.newBuilder().setType(Tile.Type.LAKE).build();
      final Stream<Chit> lakeChits =
          Stream.of(ChitUtils.newChit(2, 3, 11, 12), ChitUtils.newChit(4, 10));

      final int desertCount =
          tiles.get(TileUtils.DESERT_OR_LAKE_NAME).map(t2 -> t2._1.size()).getOrElse(0);
      final int fisheryCount = fisheryCoordinates.size();

      final Map<String, Tuple2<Array<Tile>, Boolean>> tiles =
          this.tiles
              .filter(t2 -> !TileUtils.DESERT_OR_LAKE_NAME.equals(t2._1))
              .put(TileUtils.LAKE_NAME, Tuple.of(Array.fill(desertCount, lakeTile), false))
              .filter(t2 -> desertCount > 0 || !t2._1.equals(TileUtils.LAKE_NAME))
              .put(TileUtils.FISHERY_NAME, Tuple.of(Array.fill(fisheryCount, fisheryTile), false));

      final Map<String, Array<Chit>> chits =
          this.chits
              .put(TileUtils.LAKE_NAME, Array.ofAll(lakeChits.cycle().slice(0, desertCount)))
              .filter(t2 -> desertCount > 0 || !t2._1.equals(TileUtils.LAKE_NAME))
              .put(
                  TileUtils.FISHERY_NAME, Array.ofAll(fisheryChits.cycle().slice(0, fisheryCount)));
      final Map<String, Array<String>> chitsTilesMap =
          this.chitsTilesMap
              .put(TileUtils.LAKE_NAME, Array.of(TileUtils.LAKE_NAME))
              .filter(t2 -> desertCount > 0 || !t2._1.equals(TileUtils.LAKE_NAME))
              .put(TileUtils.FISHERY_NAME, Array.of(TileUtils.FISHERY_NAME));

      final Map<String, Array<Coordinate>> coordinates =
          HashMap.ofEntries(
                  this.coordinates.map(t2 ->
                          Tuple.of(
                              TileUtils.DESERT_OR_LAKE_NAME.equals(t2._1)
                                  ? TileUtils.LAKE_NAME
                                  : t2._1,
                              t2._2)))
              .put(TileUtils.FISHERY_NAME, fisheryCoordinates);
      final Map<String, Array<String>> coordinatesTilesMap =
          HashMap.ofEntries(
                  this.coordinatesTilesMap.map(t2 -> {
                        final String key = t2._1;
                        final Array<String> value = t2._2;

                        return Tuple.of(
                            TileUtils.DESERT_OR_LAKE_NAME.equals(key) ? TileUtils.LAKE_NAME : key,
                            value.map(v ->
                                    TileUtils.DESERT_OR_LAKE_NAME.equals(v)
                                        ? TileUtils.LAKE_NAME
                                        : v));
                      }))
              .put(TileUtils.FISHERY_NAME, Array.of(TileUtils.FISHERY_NAME));

      return new Builder(
          tiles, coordinates, chits, coordinatesTilesMap, chitsTilesMap, this.markers);
    }
  }

  private final MersenneTwister chitsShufflerPrng = new MersenneTwister();
  private final MersenneTwister coordinatesShufflerPrng = new MersenneTwister();

  private final Map<String, Tuple2<Array<Tile>, Boolean>> tiles;
  private final Map<String, Array<Coordinate>> coordinates;
  private final Map<String, Array<Chit>> chits;
  private final Map<String, Array<String>> coordinatesTilesMap;
  private final Map<String, Array<String>> chitsTilesMap;
  private final Set<Marker> markers;

  public static Builder newBuilder(
      final noelyap.setterforcatan.protogen.SpecificationOuterClass.Specification that)
      throws InvalidSpecificationError {
    return newBuilder(
        HashSet.ofAll(that.getTilesList()),
        HashSet.ofAll(that.getCoordinatesList()),
        HashSet.ofAll(that.getChitsList()),
        HashSet.ofAll(that.getCoordinatesTilesMappingsList()),
        HashSet.ofAll(that.getChitsTilesMappingsList()),
        HashSet.ofAll(that.getMarkersList()));
  }

  public static Builder newBuilder(
      final Set<Tiles> tiles,
      final Set<Coordinates> coordinates,
      final Set<Chits> chits,
      final Set<CoordinateTilesMapping> coordinatesTilesMap,
      final Set<ChitsTilesMapping> chitsTilesMap,
      final Set<Marker> markers) {
    return newBuilder(
        HashMap.ofEntries(
            tiles.map(t ->
                    Tuple.of(
                        t.getName(), Tuple.of(Array.ofAll(t.getTilesList()), t.getChitless())))),
        HashMap.ofEntries(coordinates.map(c -> Tuple.of(c.getName(), Array.ofAll(c.getCoordinatesList())))),
        HashMap.ofEntries(chits.map(c -> Tuple.of(c.getName(), Array.ofAll(c.getChitsList())))),
        HashMap.ofEntries(
            coordinatesTilesMap.map(ct -> Tuple.of(ct.getCoordinatesName(), Array.ofAll(ct.getTilesNamesList())))),
        HashMap.ofEntries(
            chitsTilesMap.map(ct -> Tuple.of(ct.getChitsName(), Array.ofAll(ct.getTilesNamesList())))),
        markers);
  }

  public static Builder newBuilder(
      final Map<String, Tuple2<Array<Tile>, Boolean>> tiles,
      final Map<String, Array<Coordinate>> coordinates,
      final Map<String, Array<Chit>> chits,
      final Map<String, Array<String>> coordinatesTilesMap,
      final Map<String, Array<String>> chitsTilesMap) {
    return newBuilder(
        tiles, coordinates, chits, coordinatesTilesMap, chitsTilesMap, HashSet.empty());
  }

  public static Builder newBuilder(
      final Map<String, Tuple2<Array<Tile>, Boolean>> tiles,
      final Map<String, Array<Coordinate>> coordinates,
      final Map<String, Array<Chit>> chits,
      final Map<String, Array<String>> coordinatesTilesMap,
      final Map<String, Array<String>> chitsTilesMap,
      final Set<Marker> markers) {
    return new Builder(tiles, coordinates, chits, coordinatesTilesMap, chitsTilesMap, markers);
  }

  private SpecificationImpl(
      final Map<String, Tuple2<Array<Tile>, Boolean>> tiles,
      final Map<String, Array<Coordinate>> coordinates,
      final Map<String, Array<Chit>> chits,
      final Map<String, Array<String>> coordinatesTilesMap,
      final Map<String, Array<String>> chitsTilesMap,
      final Set<Marker> markers) {
    this.tiles = tiles;
    this.coordinates = coordinates;
    this.chits = chits;
    this.coordinatesTilesMap = coordinatesTilesMap;
    this.chitsTilesMap = chitsTilesMap;
    this.markers = markers;
  }

  public Set<Marker> getMarkers() {
    return this.markers;
  }

  public noelyap.setterforcatan.protogen.SpecificationOuterClass.Specification toProto() {
    return noelyap.setterforcatan.protogen.SpecificationOuterClass.Specification.newBuilder()
        .addAllTiles(
            tiles.map(t2 ->
                    Tiles.newBuilder()
                        .setName(t2._1)
                        .addAllTiles(t2._2._1)
                        .setChitless(t2._2._2)
                        .build()))
        .addAllCoordinates(
            coordinates.map(t2 -> Coordinates.newBuilder().setName(t2._1).addAllCoordinates(t2._2).build()))
        .addAllChits(chits.map(t2 -> Chits.newBuilder().setName(t2._1).addAllChits(t2._2).build()))
        .addAllCoordinatesTilesMappings(
            coordinatesTilesMap.map(t2 ->
                    CoordinateTilesMapping.newBuilder()
                        .setCoordinatesName(t2._1)
                        .addAllTilesNames(t2._2)
                        .build()))
        .addAllChitsTilesMappings(
            chitsTilesMap.map(t2 ->
                    ChitsTilesMapping.newBuilder()
                        .setChitsName(t2._1)
                        .addAllTilesNames(t2._2)
                        .build()))
        .addAllMarkers(markers)
        .build();
  }

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
                SpecificationImpl::checkForUndefinedTilesErrors,
                SpecificationImpl::checkForUnreferencedTilesErrors,
                SpecificationImpl::checkForUndefinedFeaturesErrors,
                SpecificationImpl::checkForUnreferencedFeaturesErrors,
                SpecificationImpl::checkForFeaturesVersusTilesCountMismatchError);
    final Array<Tuple3<String, Map<String, Array<GeneratedMessageV3>>, Map<String, Array<String>>>>
        args =
            Array.of(
                Tuple.of("chits", widenFeaturesMap(chits), chitsTilesMap),
                Tuple.of("coordinates", widenFeaturesMap(coordinates), coordinatesTilesMap));

    final Set<String> errors =
        checkForEmptyFeatureMaps("tiles", tiles)
            .union(checkForEmptyFeatureMaps("coordinates", coordinates))
            .union(checkForEmptyFeatureMaps("chits", chits))
            .union(checkForDuplicateCoordinates(coordinates))
            .union(
                HashSet.ofAll(fns.crossProduct(args))
                    .flatMap(t2 -> {
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
                        }));

    if (errors.length() != 0) {
      final Set<Tuple2<String, Integer>> enumeratedErrors = errors.zipWithIndex();
      final Array<String> errorMessages =
          Array.ofAll(enumeratedErrors.map(t2 -> String.format("Violation %d: %s", t2._2 + 1, t2._1)))
              .sortBy(s -> Integer.parseInt(s.substring("Violation ".length(), s.indexOf(':'))));

      throw new InvalidSpecificationError(
          "Invalid Specification:\n\t"
              + String.join("\n\t", errorMessages.toJavaArray(String[]::new)));
    }
  }

  private static <T> Set<String> checkForEmptyFeatureMaps(
      final String feature, final Map<String, T> featuresMap) {
    return featuresMap.isEmpty()
        ? HashSet.of(String.format("%s map is empty.", StringUtils.capitalize(feature)))
        : HashSet.empty();
  }

  @VisibleForTesting
  static Set<String> checkForDuplicateCoordinates(
      final Map<String, Array<Coordinate>> coordinates) {
    final Multimap<Coordinate, String> reverseIndex =
        HashMultimap.withSeq()
            .ofEntries(coordinates.flatMap(t2 -> t2._2.map(s -> Tuple.of(s, t2._1))));
    final Multimap<Coordinate, String> duplicates =
        reverseIndex.filter(t2 -> {
              final Coordinate key = t2._1;

              return reverseIndex.get(key).get().size() > 1;
            });

    return duplicates
        .keySet()
        .map(c -> {
              final Traversable<String> coordinateNames = duplicates.get(c).get().distinct();

              return String.format(
                  "Coordinate `%s` referenced more than once %s [%s]",
                  c,
                  coordinateNames.size() == 1 ? "in" : "across",
                  String.join(", ", coordinateNames.toJavaArray(String[]::new)));
            });
  }

  @VisibleForTesting
  static Set<String> checkForUndefinedTilesErrors(
      final String feature,
      final Map<String, Tuple2<Array<Tile>, Boolean>> tiles,
      final Map<String, Array<GeneratedMessageV3>> features,
      final Map<String, Array<String>> featuresTilesMap) {
    return featuresTilesMap
        .map(t2 -> {
              final String featuresName = t2._1;
              final Array<String> tilesNames = t2._2;

              return Tuple.of(featuresName, HashSet.ofAll(tilesNames).diff(tiles.keySet()));
            })
        .filter(t2 -> !t2._2.isEmpty())
        .map(t2 ->
                String.format(
                    "[%s] tiles referenced in %sTilesMap \"%s\" are not defined.",
                    String.join(", ", t2._2), feature, t2._1))
        .toSet();
  }

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
        .map(key ->
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
        .map(key ->
                String.format("\"%s\" in %s not referenced in %sTilesMap.", key, feature, feature));
  }

  @VisibleForTesting
  static Set<String> checkForFeaturesVersusTilesCountMismatchError(
      final String feature,
      final Map<String, Tuple2<Array<Tile>, Boolean>> tiles,
      final Map<String, Array<GeneratedMessageV3>> features,
      final Map<String, Array<String>> featuresTilesMap) {
    return featuresTilesMap
        .map(t2 -> {
              final String featuresName = t2._1;
              final Array<String> tilesNames = t2._2;

              final int featuresCount;
              featuresCount = features.get(featuresName).getOrElse(Array.empty()).size();

              final int tilesCount =
                  tilesNames
                      .map(tileName ->
                              tiles
                                  .get(tileName)
                                  .getOrElse(Tuple.of(Array.empty(), false))
                                  ._1
                                  .size())
                      .sum()
                      .intValue();

              return Tuple.of(featuresName, featuresCount, tilesNames, tilesCount);
            })
        .filter(t4 -> !t4._2.equals(t4._4))
        .map(t4 -> {
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

  public Array<Configuration> toConfiguration() {
    final Multimap<Tile, Chit> tileChitsMap =
        shuffleTiles(chitsShufflerPrng, tiles, chits, chitsTilesMap);
    final Multimap<Tile, Coordinate> tileCoordinateMap =
        shuffleTiles(coordinatesShufflerPrng, tiles, coordinates, coordinatesTilesMap);

    return Array.ofAll(
        tileCoordinateMap
            .keySet()
            .flatMap(tile -> {
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
                      .map(t2 ->
                              Configuration.newBuilder()
                                  .setTile(tile)
                                  .setCoordinate(t2._1)
                                  .setChit(t2._2)
                                  .build());
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
            Array.ofAll(featuresTilesMap.keySet()) // prevent dedupe
                .flatMap(featureName -> {
                      final Array<String> tilesNames = featuresTilesMap.get(featureName).get();
                      final Array<Tile> tileIds =
                          tilesNames.flatMap(tilesName -> tiles.get(tilesName).get()._1);
                      final Array<ChitsOrCoordinates> chitsOrCoordinates =
                          featuresMap.get(featureName).get();

                      return tileIds.zip(chitsOrCoordinates.shuffle(prng));
                    }));
  }

  @VisibleForTesting
  static <ChitsOrCoordinates extends GeneratedMessageV3>
      Map<String, Array<GeneratedMessageV3>> widenFeaturesMap(
          final Map<String, Array<ChitsOrCoordinates>> featuresMap) {
    return HashMap.ofEntries(featuresMap.map(t2 -> Tuple.of(t2._1, Array.narrow(t2._2))));
  }
}
