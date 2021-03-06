package noelyap.setterforcatan.component;

import static noelyap.setterforcatan.component.Chits.CHITS_2_3_11_12;
import static noelyap.setterforcatan.component.Chits.CHITS_4_10;
import static noelyap.setterforcatan.component.Chits.CHIT_10;
import static noelyap.setterforcatan.component.Chits.CHIT_4;
import static noelyap.setterforcatan.component.Chits.CHIT_5;
import static noelyap.setterforcatan.component.Chits.CHIT_6;
import static noelyap.setterforcatan.component.Chits.CHIT_8;
import static noelyap.setterforcatan.component.Chits.CHIT_9;
import static noelyap.setterforcatan.component.Tiles.FISHERMEN_OF_CATAN_DESERT_CONVERTIBLE_TO_LAKE;
import static noelyap.setterforcatan.component.Tiles.FISHERMEN_OF_CATAN_FISHERY;
import static noelyap.setterforcatan.component.Tiles.FISHERMEN_OF_CATAN_LAKE;
import static org.assertj.core.api.HamcrestCondition.matching;

import com.google.common.annotations.VisibleForTesting;
import com.google.protobuf.GeneratedMessageV3;
import io.vavr.Function0;
import io.vavr.Function1;
import io.vavr.Function2;
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
import io.vavr.control.Option;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import noelyap.setterforcatan.matcher.PassThroughMatcher;
import noelyap.setterforcatan.protogen.ChitOuterClass.Chit;
import noelyap.setterforcatan.protogen.ChitOuterClass.Chits;
import noelyap.setterforcatan.protogen.ConfigurationOuterClass.Configuration;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Coordinate;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Coordinates;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge;
import noelyap.setterforcatan.protogen.MarkerOuterClass.Marker;
import noelyap.setterforcatan.protogen.SpecificationOuterClass.ChitsTilesMapping;
import noelyap.setterforcatan.protogen.SpecificationOuterClass.CoordinateTilesMapping;
import noelyap.setterforcatan.protogen.SpecificationOuterClass.Specification;
import noelyap.setterforcatan.protogen.TileOuterClass.Tile;
import noelyap.setterforcatan.protogen.TileOuterClass.Tiles;
import noelyap.setterforcatan.util.MersenneTwister;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.SoftAssertions;
import org.hamcrest.Matcher;

@Slf4j
public class SpecificationImpl {
  @SuppressWarnings("serial")
  public static class InvalidSpecificationError extends RuntimeException {
    public InvalidSpecificationError(final String message) {
      super(message);
    }
  }

  public static class Builder {
    private final Map<String, Array<Tile>> tiles;
    private final Map<String, Array<Coordinate>> coordinates;
    private final Map<String, Array<Chit>> chits;
    private final Map<String, Array<String>> coordinatesTilesMap;
    private final Map<String, Array<String>> chitsTilesMap;
    private final Set<Marker> markers;

    private final Matcher<Configuration> configurationMatcher;

    private Builder(
        final Map<String, Array<Tile>> tiles,
        final Map<String, Array<Coordinate>> coordinates,
        final Map<String, Array<Chit>> chits,
        final Map<String, Array<String>> coordinatesTilesMap,
        final Map<String, Array<String>> chitsTilesMap,
        final Set<Marker> markers,
        final Matcher<Configuration> configurationMatcher) {
      this.tiles = tiles;
      this.coordinates = coordinates;
      this.chits = chits;
      this.coordinatesTilesMap = coordinatesTilesMap;
      this.chitsTilesMap = chitsTilesMap;
      this.markers = markers;
      this.configurationMatcher = configurationMatcher;
    }

    public SpecificationImpl build() throws InvalidSpecificationError {
      final SpecificationImpl specificationImpl =
          new SpecificationImpl(
              tiles,
              coordinates,
              chits,
              coordinatesTilesMap,
              chitsTilesMap,
              markers,
              configurationMatcher);

      specificationImpl.validate();

      return specificationImpl;
    }

    public Builder withFisheries(final Array<Coordinate> fisheryCoordinates) {
      final Tile fisheryTile = noelyap.setterforcatan.component.Tiles.FISHERY;
      final Stream<Chit> fisheryChits =
          Stream.of(CHIT_4, CHIT_10, CHIT_5, CHIT_9, CHIT_6, CHIT_8, CHIT_5, CHIT_9);

      final Tile lakeTile = Tile.newBuilder().setType(Tile.Type.LAKE).build();
      final Stream<Chit> lakeChits = Stream.of(CHITS_2_3_11_12, CHITS_4_10);

      final int desertCount =
          tiles
              .get(FISHERMEN_OF_CATAN_DESERT_CONVERTIBLE_TO_LAKE)
              .map(Traversable::size)
              .getOrElse(0);
      final int fisheryCount = fisheryCoordinates.size();

      final Map<String, Array<Tile>> tiles =
          this.tiles
              .filter(t2 -> !FISHERMEN_OF_CATAN_DESERT_CONVERTIBLE_TO_LAKE.equals(t2._1))
              .put(FISHERMEN_OF_CATAN_LAKE, Array.fill(desertCount, lakeTile))
              .filter(t2 -> desertCount > 0 || !t2._1.equals(FISHERMEN_OF_CATAN_LAKE))
              .put(FISHERMEN_OF_CATAN_FISHERY, Array.fill(fisheryCount, fisheryTile));

      final Map<String, Array<Chit>> chits =
          this.chits
              .put(FISHERMEN_OF_CATAN_LAKE, Array.ofAll(lakeChits.cycle().slice(0, desertCount)))
              .filter(t2 -> desertCount > 0 || !t2._1.equals(FISHERMEN_OF_CATAN_LAKE))
              .put(
                  FISHERMEN_OF_CATAN_FISHERY,
                  Array.ofAll(fisheryChits.cycle().slice(0, fisheryCount)));
      final Map<String, Array<String>> chitsTilesMap =
          this.chitsTilesMap
              .put(FISHERMEN_OF_CATAN_LAKE, Array.of(FISHERMEN_OF_CATAN_LAKE))
              .filter(t2 -> desertCount > 0 || !t2._1.equals(FISHERMEN_OF_CATAN_LAKE))
              .put(FISHERMEN_OF_CATAN_FISHERY, Array.of(FISHERMEN_OF_CATAN_FISHERY));

      final Map<String, Array<Coordinate>> coordinates =
          HashMap.ofEntries(
                  this.coordinates.map(t2 ->
                          Tuple.of(
                              FISHERMEN_OF_CATAN_DESERT_CONVERTIBLE_TO_LAKE.equals(t2._1)
                                  ? FISHERMEN_OF_CATAN_LAKE
                                  : t2._1,
                              t2._2)))
              .put(FISHERMEN_OF_CATAN_FISHERY, fisheryCoordinates);
      final Map<String, Array<String>> coordinatesTilesMap =
          HashMap.ofEntries(
                  this.coordinatesTilesMap.map(t2 -> {
                        final String key = t2._1;
                        final Array<String> value = t2._2;

                        return Tuple.of(
                            FISHERMEN_OF_CATAN_DESERT_CONVERTIBLE_TO_LAKE.equals(key)
                                ? FISHERMEN_OF_CATAN_LAKE
                                : key,
                            value.map(v ->
                                    FISHERMEN_OF_CATAN_DESERT_CONVERTIBLE_TO_LAKE.equals(v)
                                        ? FISHERMEN_OF_CATAN_LAKE
                                        : v));
                      }))
              .put(FISHERMEN_OF_CATAN_FISHERY, Array.of(FISHERMEN_OF_CATAN_FISHERY));

      return new Builder(
          tiles,
          coordinates,
          chits,
          coordinatesTilesMap,
          chitsTilesMap,
          this.markers,
          this.configurationMatcher);
    }

    public Builder withConfigurationMatcher(final Matcher<Configuration> configurationMatcher) {
      return new Builder(
          this.tiles,
          this.coordinates,
          this.chits,
          this.coordinatesTilesMap,
          this.chitsTilesMap,
          this.markers,
          configurationMatcher);
    }
  }

  private final MersenneTwister chitsShufflerPrng = new MersenneTwister();
  private final MersenneTwister coordinatesShufflerPrng = new MersenneTwister();

  private final Map<String, Array<Tile>> tiles;
  private final Map<String, Array<Coordinate>> coordinates;
  private final Map<String, Array<Chit>> chits;
  private final Map<String, Array<String>> coordinatesTilesMap;
  private final Map<String, Array<String>> chitsTilesMap;
  private final Set<Marker> markers;
  private final Matcher<Configuration> configurationMatcher;

  public static Builder newBuilder(final Specification that) throws InvalidSpecificationError {
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
        HashMap.ofEntries(tiles.map(t -> Tuple.of(t.getName(), Array.ofAll(t.getTilesList())))),
        HashMap.ofEntries(coordinates.map(c -> Tuple.of(c.getName(), Array.ofAll(c.getCoordinatesList())))),
        HashMap.ofEntries(chits.map(c -> Tuple.of(c.getName(), Array.ofAll(c.getChitsList())))),
        HashMap.ofEntries(
            coordinatesTilesMap.map(ct -> Tuple.of(ct.getCoordinatesName(), Array.ofAll(ct.getTilesNamesList())))),
        HashMap.ofEntries(
            chitsTilesMap.map(ct -> Tuple.of(ct.getChitsName(), Array.ofAll(ct.getTilesNamesList())))),
        markers);
  }

  public static Builder newBuilder(
      final Map<String, Array<Tile>> tiles,
      final Map<String, Array<Coordinate>> coordinates,
      final Map<String, Array<Chit>> chits,
      final Map<String, Array<String>> coordinatesTilesMap,
      final Map<String, Array<String>> chitsTilesMap) {
    return newBuilder(
        tiles, coordinates, chits, coordinatesTilesMap, chitsTilesMap, HashSet.empty());
  }

  public static Builder newBuilder(
      final Map<String, Array<Tile>> tiles,
      final Map<String, Array<Coordinate>> coordinates,
      final Map<String, Array<Chit>> chits,
      final Map<String, Array<String>> coordinatesTilesMap,
      final Map<String, Array<String>> chitsTilesMap,
      final Set<Marker> markers) {
    return new Builder(
        tiles,
        coordinates,
        chits,
        coordinatesTilesMap,
        chitsTilesMap,
        markers,
        new PassThroughMatcher());
  }

  private SpecificationImpl(
      final Map<String, Array<Tile>> tiles,
      final Map<String, Array<Coordinate>> coordinates,
      final Map<String, Array<Chit>> chits,
      final Map<String, Array<String>> coordinatesTilesMap,
      final Map<String, Array<String>> chitsTilesMap,
      final Set<Marker> markers,
      final Matcher<Configuration> configurationMatcher) {
    this.tiles = tiles;
    this.coordinates = coordinates;
    this.chits = chits;
    this.coordinatesTilesMap = coordinatesTilesMap;
    this.chitsTilesMap = chitsTilesMap;
    this.markers = markers;
    this.configurationMatcher = configurationMatcher;
  }

  public Set<Marker> getMarkers() {
    return this.markers;
  }

  public Specification toProto() {
    return Specification.newBuilder()
        .addAllTiles(tiles.map(t2 -> Tiles.newBuilder().setName(t2._1).addAllTiles(t2._2).build()))
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
                Map<String, Array<Tile>>,
                Map<String, Array<GeneratedMessageV3>>,
                Map<String, Array<String>>,
                Set<String>>>
        fns =
            Array.of(
                SpecificationImpl::checkForUndefinedTilesErrors,
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
            .union(checkTileShapes(tiles))
            .union(
                checkForCoordinateEdgesVersusTileShapesMismatchError(
                    tiles, coordinates, coordinatesTilesMap))
            .union(checkForDuplicateCoordinates(coordinates))
            .union(
                checkForUnreferencedTilesErrors(
                    "coordinates", tiles, widenFeaturesMap(coordinates), coordinatesTilesMap))
            .union(
                HashSet.ofAll(fns.crossProduct(args))
                    .flatMap(t2 -> {
                          final var fn = t2._1;
                          final var arg = t2._2;

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
  static Set<String> checkTileShapes(final Map<String, Array<Tile>> tileSets) {
    return tileSets
        .flatMap(t2 -> {
              final var tileName = t2._1;
              final var tiles = t2._2;

              final Set<Tile.Shape> shapes = tiles.map(Tile::getShape).toSet();

              return (shapes.size() < 2
                  ? Option.none()
                  : Option.of(
                      String.format(
                          "\"%s\" tiles contain mixed shapes: %s.",
                          tileName,
                          String.join(
                              ", ", shapes.map(Tile.Shape::toString).toJavaArray(String[]::new)))));
            })
        .toSet();
  }

  @VisibleForTesting
  static Set<String> checkForCoordinateEdgesVersusTileShapesMismatchError(
      final Map<String, Array<Tile>> tiles,
      final Map<String, Array<Coordinate>> coordinates,
      final Map<String, Array<String>> coordinatesTilesMap) {
    return coordinatesTilesMap
        .flatMap(t2 -> {
              final String coordinatesName = t2._1;
              final Array<String> tilesNames = t2._2;

              final Set<Tile.Shape> shapes =
                  tilesNames
                      .flatMap(tileName -> tiles.get(tileName).getOrElse(Array.empty()))
                      .map(Tile::getShape)
                      .toSet();

              if (shapes.size() > 1) {
                return Option.of(
                    String.format(
                        "[%s] in \"%s\" contain mixed shapes: %s.",
                        String.join(", ", tilesNames.toJavaArray(String[]::new)),
                        coordinatesName,
                        String.join(
                            ", ", shapes.map(Tile.Shape::toString).toJavaArray(String[]::new))));
              } else {
                final var shape = shapes.get();

                if (shape != Tile.Shape.POLYMORPHIC) {
                  final Set<Integer> edgeCounts =
                      coordinates
                          .get(coordinatesName)
                          .getOrElse(Array.empty())
                          .groupBy(Coordinate::getEdgePositionsCount)
                          .keySet();

                  if (edgeCounts.size() > 1) {
                    return Option.of(
                        String.format(
                            "\"%s\" coordinates contain mixed edge counts: %s.",
                            coordinatesName,
                            String.join(
                                ", ",
                                edgeCounts
                                    .map(i -> Integer.toString(i))
                                    .toJavaArray(String[]::new))));
                  } else {
                    final int edgeCount = edgeCounts.getOrElse(0);
                    final Array<Array<Edge.Position>> edgePositions =
                        coordinates
                            .get(coordinatesName)
                            .getOrElse(Array.empty())
                            .map(Coordinate::getEdgePositionsList)
                            .map(Array::ofAll);

                    final Function2<Integer, Function0<Array<String>>, Array<String>>
                        edgeValidator =
                            (final Integer expectedEdgeCount,
                                final Function0<Array<String>> edgePositionValidator) -> {
                              final Function1<Integer, String> edgesString =
                                  ec -> ec == 1 ? "edge" : "edges";

                              final Array<String> edgeCountErrorMessage =
                                  (edgeCount == expectedEdgeCount)
                                      ? Array.empty()
                                      : Array.of(
                                          String.format(
                                              "%s tiles needing %d %s are being configured with \"%s\" coordinates having %d %s.",
                                              shape,
                                              expectedEdgeCount,
                                              edgesString.apply(expectedEdgeCount),
                                              coordinatesName,
                                              edgeCount,
                                              edgesString.apply(edgeCount)));
                              final Array<String> edgePositionErrorMessages =
                                  edgePositionValidator.apply();

                              return edgeCountErrorMessage.appendAll(edgePositionErrorMessages);
                            };
                    final Function1<Array<Edge.Position>, String>
                        edgePositionErrorMessageGenerator =
                            eps ->
                                String.format(
                                    "Edge positions [%s] are not compatible with %s tiles.",
                                    String.join(
                                        ", ",
                                        eps.map(Edge.Position::toString)
                                            .toJavaArray(String[]::new)),
                                    shape);

                    return switch (shape) {
                      case HEXAGON -> edgeValidator.apply(
                          6,
                          () ->
                              edgePositions
                                  .filterNot(eps ->
                                          eps.sorted()
                                              .equals(
                                                  Array.of(Edge.Position.values())
                                                      .filter(ep -> ep != Edge.Position.UNRECOGNIZED)))
                                  .map(edgePositionErrorMessageGenerator));
                      case PENTAGON, TRAPEZOID -> throw new UnsupportedOperationException(
                          "Unsupported shape: " + shape);
                      case CHEVRON -> edgeValidator.apply(
                          2,
                          () ->
                              edgePositions
                                  .filter(eps -> {
                                        final var edgeNumbers = eps.map(Edge.Position::getNumber);

                                        return edgeNumbers.size() != 2
                                            || !Array.of(1, 5)
                                                .contains(
                                                    Math.abs(
                                                        edgeNumbers.get(0) - edgeNumbers.get(1)));
                                      })
                                  .map(edgePositionErrorMessageGenerator));
                      case RECTANGLE -> edgeValidator.apply(
                          2,
                          () ->
                              edgePositions
                                  .filter(eps -> {
                                        final int edgePositionCount =
                                            Edge.Position.values().length - 1;
                                        final var edgeNumbers = eps.map(Edge.Position::getNumber);

                                        return edgeNumbers.size() != 2
                                            || Math.abs(edgeNumbers.get(0) - edgeNumbers.get(1))
                                                != edgePositionCount / 2;
                                      })
                                  .map(edgePositionErrorMessageGenerator));
                      case TRIANGLE -> edgeValidator.apply(
                          1,
                          () ->
                              edgePositions
                                  .filter(eps -> eps.size() != 1)
                                  .map(eps ->
                                          String.format(
                                              "Edge positions [%s] are not compatible with %s tiles.",
                                              String.join(
                                                  ", ",
                                                  eps.map(Edge.Position::toString)
                                                      .toJavaArray(String[]::new)),
                                              shape)));
                      case POINT -> edgeValidator.apply(
                          0,
                          () ->
                              edgePositions
                                  .filterNot(Array::isEmpty)
                                  .map(edgePositionErrorMessageGenerator));
                      default -> throw new IllegalStateException("Unexpected value: " + shape);
                    };
                  }
                }
              }

              return Option.none();
            })
        .toSet();
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
      final Map<String, Array<Tile>> tiles,
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
      final Map<String, Array<Tile>> tiles,
      final Map<String, Array<GeneratedMessageV3>> features,
      final Map<String, Array<String>> featuresTilesMap) {
    return tiles
        .keySet()
        .diff(featuresTilesMap.flatMap(t2 -> HashSet.ofAll(t2._2)).toSet())
        .map(key -> String.format("\"%s\" tiles is not referenced in %sTilesMap.", key, feature));
  }

  @VisibleForTesting
  static Set<String> checkForUndefinedFeaturesErrors(
      final String feature,
      final Map<String, Array<Tile>> tiles,
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
      final Map<String, Array<Tile>> tiles,
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
      final Map<String, Array<Tile>> tiles,
      final Map<String, Array<GeneratedMessageV3>> features,
      final Map<String, Array<String>> featuresTilesMap) {
    return featuresTilesMap
        .map(t2 -> {
              final String featuresName = t2._1;
              final Array<String> tilesNames = t2._2;

              final int featuresCount = features.get(featuresName).getOrElse(Array.empty()).size();

              final int tilesCount =
                  tilesNames
                      .map(tileName -> tiles.get(tileName).getOrElse(Array.empty()).size())
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

  public Option<Array<Configuration>> toConfiguration() {
    final Array<Tuple2<Tuple2<String, Tile>, Coordinate>> tilesCoordinates =
        shuffleTiles(coordinatesShufflerPrng, tiles, coordinates, coordinatesTilesMap);
    final Array<Tuple2<Tuple2<String, Tile>, Chit>> tilesChits =
        shuffleTiles(chitsShufflerPrng, tiles, chits, chitsTilesMap);

    final var tilesCoordinatesMap = tilesCoordinates.groupBy(Tuple2::_1);
    final var tilesChitsMap = tilesChits.groupBy(Tuple2::_1);

    final Array<Configuration> configurations =
        tilesCoordinatesMap
            .toArray()
            .flatMap(entry -> {
                  final var tileId = entry._1;

                  final Tile tile = tileId._2;
                  final Array<Coordinate> coordinates = entry._2.map(Tuple2::_2);
                  final Array<Chit> chits =
                      tilesChitsMap
                          .get(tileId)
                          .getOrElse(
                              Array.fill(
                                  coordinates.length(),
                                  Tuple.of(tileId, noelyap.setterforcatan.component.Chits.empty())))
                          .map(Tuple2::_2);

                  return coordinates
                      .zip(chits)
                      .map(t2 ->
                              Configuration.newBuilder()
                                  .setTile(tile)
                                  .setCoordinate(t2._1)
                                  .setChit(t2._2)
                                  .build());
                });

    try {
      final SoftAssertions softly = new SoftAssertions();

      for (final Configuration configuration : configurations) {
        softly.assertThat(configuration).is(matching(configurationMatcher));
      }

      softly.assertAll();
    } catch (final AssertionError e) {
      log.debug(e.getMessage());

      return Option.none();
    }

    return Option.of(configurations);
  }

  @VisibleForTesting
  static <ChitsOrCoordinates extends GeneratedMessageV3>
      Array<Tuple2<Tuple2<String, Tile>, ChitsOrCoordinates>> shuffleTiles(
          final Random prng,
          final Map<String, Array<Tile>> tiles,
          final Map<String, Array<ChitsOrCoordinates>> featuresMap,
          final Map<String, Array<String>> featuresTilesMap) {
    return featuresTilesMap
        .keySet()
        .toArray()
        .flatMap(featureName -> {
              final Array<String> tilesNames = featuresTilesMap.get(featureName).get();
              final Array<Tuple2<String, Tile>> tileIds =
                  tilesNames.flatMap(tilesName ->
                          tiles.get(tilesName).get().map(tile -> Tuple.of(tilesName, tile)));
              final Array<ChitsOrCoordinates> chitsOrCoordinates =
                  featuresMap.get(featureName).get();

              return tileIds.zip(chitsOrCoordinates.shuffle(prng));
            });
  }

  @VisibleForTesting
  static <ChitsOrCoordinates extends GeneratedMessageV3>
      Map<String, Array<GeneratedMessageV3>> widenFeaturesMap(
          final Map<String, Array<ChitsOrCoordinates>> featuresMap) {
    return HashMap.ofEntries(featuresMap.map(t2 -> Tuple.of(t2._1, Array.narrow(t2._2))));
  }
}
