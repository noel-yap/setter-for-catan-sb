package noelyap.setterforcatan.component;

import static noelyap.setterforcatan.component.Chits.CHITS_2_3_11_12;
import static noelyap.setterforcatan.component.Chits.CHITS_4_10;
import static noelyap.setterforcatan.component.Chits.CHIT_10;
import static noelyap.setterforcatan.component.Chits.CHIT_12;
import static noelyap.setterforcatan.component.Chits.CHIT_2;
import static noelyap.setterforcatan.component.Chits.CHIT_4;
import static noelyap.setterforcatan.component.Chits.CHIT_5;
import static noelyap.setterforcatan.component.Chits.CHIT_6;
import static noelyap.setterforcatan.component.Chits.CHIT_8;
import static noelyap.setterforcatan.component.Chits.CHIT_9;
import static noelyap.setterforcatan.component.Tiles.CASTLE;
import static noelyap.setterforcatan.component.Tiles.CHIT;
import static noelyap.setterforcatan.component.Tiles.DESERT;
import static noelyap.setterforcatan.component.Tiles.DEVELOPMENT_CARD;
import static noelyap.setterforcatan.component.Tiles.FIELD;
import static noelyap.setterforcatan.component.Tiles.FISHERMEN_OF_CATAN_DESERT_CONVERTIBLE_TO_LAKE;
import static noelyap.setterforcatan.component.Tiles.FISHERY;
import static noelyap.setterforcatan.component.Tiles.FOREST;
import static noelyap.setterforcatan.component.Tiles.GENERIC_HARBOR;
import static noelyap.setterforcatan.component.Tiles.GLASSWORKS;
import static noelyap.setterforcatan.component.Tiles.GOLD_FIELD;
import static noelyap.setterforcatan.component.Tiles.HILL;
import static noelyap.setterforcatan.component.Tiles.LAKE;
import static noelyap.setterforcatan.component.Tiles.MOUNTAIN;
import static noelyap.setterforcatan.component.Tiles.OASIS;
import static noelyap.setterforcatan.component.Tiles.PASTURE;
import static noelyap.setterforcatan.component.Tiles.QUARRY;
import static noelyap.setterforcatan.component.Tiles.SEA;
import static noelyap.setterforcatan.component.Tiles.SWAMP;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge.Position.BOTTOM_LEFT;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge.Position.BOTTOM_RIGHT;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge.Position.LEFT;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge.Position.RIGHT;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge.Position.TOP_LEFT;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge.Position.TOP_RIGHT;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Shape.PENTAGON;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Shape.TRAPEZOID;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.UNDEFINED;
import static org.assertj.core.api.Assertions.allOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.HamcrestCondition.matching;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.startsWith;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.Array;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import io.vavr.collection.Set;
import io.vavr.control.Option;
import noelyap.setterforcatan.component.SpecificationImpl.InvalidSpecificationError;
import noelyap.setterforcatan.matcher.HasOddsGreaterThan;
import noelyap.setterforcatan.matcher.TileIs;
import noelyap.setterforcatan.protogen.ChitOuterClass.Chit;
import noelyap.setterforcatan.protogen.ConfigurationOuterClass.Configuration;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Coordinate;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge;
import noelyap.setterforcatan.protogen.TileOuterClass.Tile;
import noelyap.setterforcatan.util.MersenneTwister;
import noelyap.setterforcatan.util.TileMappingUtils;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.hamcrest.core.AllOf;
import org.hamcrest.core.IsNot;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SoftAssertionsExtension.class)
@SuppressWarnings({"unchecked"})
public class SpecificationImplTest {
  final MersenneTwister prng = new MersenneTwister();

  @Test
  @DisplayName("Should create specification with fisheries.")
  public void shouldCreateSpecificationWithFisheries(final SoftAssertions softly) {
    final Array<Coordinate> goldFieldCoordinates = Array.of(Coordinates.of(6, 6));
    final Array<Coordinate> desertCoordinates = Array.of(Coordinates.of(1, 1));
    final Array<Coordinate> desertOrLakeCoordinates =
        Array.of(
            Coordinates.of(2, 2), Coordinates.of(3, 3), Coordinates.of(4, 4), Coordinates.of(5, 5));

    final Map<String, Array<Tile>> tiles;
    tiles =
        HashMap.of(
            "gold-field",
            Array.of(GOLD_FIELD),
            "desert",
            Array.of(DESERT),
            FISHERMEN_OF_CATAN_DESERT_CONVERTIBLE_TO_LAKE,
            Array.of(DESERT, DESERT, DESERT, DESERT));
    final Map<String, Array<Chit>> chits = HashMap.of("gold-field", Array.of(CHIT_6));
    final Map<String, Array<String>> chitsTilesMap =
        HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("gold-field"));
    final Map<String, Array<Coordinate>> coordinates =
        HashMap.of(
            "gold-field",
            goldFieldCoordinates,
            "desert",
            desertCoordinates,
            FISHERMEN_OF_CATAN_DESERT_CONVERTIBLE_TO_LAKE,
            desertOrLakeCoordinates);
    final Map<String, Array<String>> coordinatesTilesMap =
        HashMap.ofEntries(
            TileMappingUtils.newSelfReferringEntry("gold-field"),
            TileMappingUtils.newSelfReferringEntry("desert"),
            TileMappingUtils.newSelfReferringEntry(FISHERMEN_OF_CATAN_DESERT_CONVERTIBLE_TO_LAKE));

    final Array<Coordinate> fisheriesCoordinates =
        Array.of(
            Coordinates.onEdges(6, 6, TOP_LEFT, TOP_RIGHT),
            Coordinates.onEdges(7, 7, TOP_RIGHT, RIGHT),
            Coordinates.onEdges(8, 8, RIGHT, BOTTOM_RIGHT),
            Coordinates.onEdges(9, 9, BOTTOM_RIGHT, BOTTOM_LEFT),
            Coordinates.onEdges(10, 10, BOTTOM_LEFT, LEFT),
            Coordinates.onEdges(11, 11, LEFT, TOP_LEFT),
            Coordinates.onEdges(12, 12, TOP_LEFT, TOP_RIGHT),
            Coordinates.onEdges(13, 13, RIGHT, BOTTOM_RIGHT),
            Coordinates.onEdges(14, 14, BOTTOM_LEFT, LEFT));

    final Array<Configuration> actual =
        SpecificationImpl.newBuilder(tiles, coordinates, chits, coordinatesTilesMap, chitsTilesMap)
            .withFisheries(fisheriesCoordinates)
            .build()
            .toConfiguration()
            .get();

    final Set<Configuration> actualDesertConfigurations =
        actual.filter(c -> DESERT.equals(c.getTile())).toSet();
    final Array<Chit> actualDesertChits =
        Array.ofAll(actualDesertConfigurations)
            .map(Configuration::getChit)
            .filter(c -> c.getValuesCount() != 0);
    final Set<Coordinate> actualDesertCoordinates =
        actualDesertConfigurations.map(Configuration::getCoordinate);

    final Set<Configuration> actualLakeConfigurations =
        actual.filter(c -> LAKE.equals(c.getTile())).toSet();
    final Array<Chit> actualLakeChits =
        Array.ofAll(actualLakeConfigurations).map(Configuration::getChit);
    final Set<Coordinate> actualLakeCoordinates =
        actualLakeConfigurations.map(Configuration::getCoordinate);

    final Set<Configuration> actualFisheryConfigurations =
        actual.filter(c -> FISHERY.equals(c.getTile())).toSet();
    final Array<Chit> actualFisheryChits =
        Array.ofAll(actualFisheryConfigurations).map(Configuration::getChit);

    softly.assertThat(actual).hasSize(15);
    softly.assertThat(actualDesertChits).isEmpty();
    softly
        .assertThat(actualDesertCoordinates)
        .containsExactlyInAnyOrder(desertCoordinates.toJavaArray(Coordinate[]::new));
    softly
        .assertThat(actualLakeChits)
        .containsExactlyInAnyOrder(
            Array.of(CHITS_2_3_11_12, CHITS_2_3_11_12, CHITS_4_10, CHITS_4_10)
                .toJavaArray(Chit[]::new));
    softly
        .assertThat(actualLakeCoordinates)
        .containsExactlyInAnyOrder(desertOrLakeCoordinates.toJavaArray(Coordinate[]::new));
    softly
        .assertThat(actualFisheryChits)
        .containsExactlyInAnyOrder(
            Array.of(CHIT_4, CHIT_4, CHIT_5, CHIT_5, CHIT_6, CHIT_8, CHIT_9, CHIT_9, CHIT_10)
                .toJavaArray(Chit[]::new));
  }

  @Test
  @DisplayName("Should shuffle tiles with artifacts.")
  public void shouldShuffleTilesWithArtifacts(final SoftAssertions softly) {
    final Map<String, Array<Tile>> tiles =
        HashMap.of(
            "desert-and-lake",
            Array.of(DESERT, LAKE),
            "gold",
            Array.of(GOLD_FIELD),
            "sea",
            Array.of(SEA),
            "caravans",
            Array.of(OASIS),
            "rivers-of-catan",
            Array.of(SWAMP),
            "traders-and-barbarians",
            Array.of(CASTLE, GLASSWORKS, QUARRY));
    final Map<String, Array<Coordinate>> coordinates =
        HashMap.of(
            "desert-and-lake",
            Array.of(Coordinates.of(1, 1), Coordinates.of(2, 2)),
            "seafarers",
            Array.of(Coordinates.of(3, 3), Coordinates.of(4, 4)),
            "traders-and-barbarians",
            Array.of(
                Coordinates.of(5, 5),
                Coordinates.of(6, 6),
                Coordinates.of(7, 7),
                Coordinates.of(8, 8),
                Coordinates.of(9, 9)));
    final Map<String, Array<String>> coordinatesTilesMap =
        HashMap.ofEntries(
            TileMappingUtils.newSelfReferringEntry("desert-and-lake"),
            TileMappingUtils.newEntry("seafarers", "gold", "sea"),
            TileMappingUtils.newEntry(
                "traders-and-barbarians", "caravans", "rivers-of-catan", "traders-and-barbarians"));

    final Array<Tuple2<Tuple2<String, Tile>, Coordinate>> actual =
        SpecificationImpl.shuffleTiles(prng, tiles, coordinates, coordinatesTilesMap);

    softly
        .assertThat(actual.map(Tuple2::_1))
        .containsExactlyInAnyOrder(
            Tuple.of("desert-and-lake", DESERT),
            Tuple.of("desert-and-lake", LAKE),
            Tuple.of("gold", GOLD_FIELD),
            Tuple.of("sea", SEA),
            Tuple.of("caravans", OASIS),
            Tuple.of("rivers-of-catan", SWAMP),
            Tuple.of("traders-and-barbarians", CASTLE),
            Tuple.of("traders-and-barbarians", GLASSWORKS),
            Tuple.of("traders-and-barbarians", QUARRY));
    softly
        .assertThat(actual.map(Tuple2::_2))
        .containsExactlyInAnyOrder(
            Coordinates.of(1, 1),
            Coordinates.of(2, 2),
            Coordinates.of(3, 3),
            Coordinates.of(4, 4),
            Coordinates.of(5, 5),
            Coordinates.of(6, 6),
            Coordinates.of(7, 7),
            Coordinates.of(8, 8),
            Coordinates.of(9, 9));

    softly
        .assertThat(actual.filter(t2 -> t2._1.equals(Tuple.of("desert-and-lake", DESERT))).map(Tuple2::_2))
        .allSatisfy(c -> assertThat(c).isIn(Coordinates.of(1, 1), Coordinates.of(2, 2)));
    softly
        .assertThat(actual.filter(t2 -> t2._1.equals(Tuple.of("desert-and-lake", LAKE))).map(Tuple2::_2))
        .allSatisfy(c -> assertThat(c).isIn(Coordinates.of(1, 1), Coordinates.of(2, 2)));
    softly
        .assertThat(actual.filter(t2 -> t2._1.equals(Tuple.of("gold", GOLD_FIELD))).map(Tuple2::_2))
        .allSatisfy(c -> assertThat(c).isIn(Coordinates.of(3, 3), Coordinates.of(4, 4)));
    softly
        .assertThat(actual.filter(t2 -> t2._1.equals(Tuple.of("sea", SEA))).map(Tuple2::_2))
        .allSatisfy(c -> assertThat(c).isIn(Coordinates.of(3, 3), Coordinates.of(4, 4)));
    softly
        .assertThat(actual.filter(t2 -> t2._1.equals(Tuple.of("caravans", OASIS))).map(Tuple2::_2))
        .allSatisfy(c ->
                assertThat(c)
                    .isIn(
                        Coordinates.of(5, 5),
                        Coordinates.of(6, 6),
                        Coordinates.of(7, 7),
                        Coordinates.of(8, 8),
                        Coordinates.of(9, 9)));
    softly
        .assertThat(actual.filter(t2 -> t2._1.equals(Tuple.of("rivers-of-catan", OASIS))).map(Tuple2::_2))
        .allSatisfy(c ->
                assertThat(c)
                    .isIn(
                        Coordinates.of(5, 5),
                        Coordinates.of(6, 6),
                        Coordinates.of(7, 7),
                        Coordinates.of(8, 8),
                        Coordinates.of(9, 9)));
    softly
        .assertThat(
            actual
                .filter(t2 -> t2._1.equals(Tuple.of("traders-and-barbarians", CASTLE)))
                .map(Tuple2::_2))
        .allSatisfy(c ->
                assertThat(c)
                    .isIn(
                        Coordinates.of(5, 5),
                        Coordinates.of(6, 6),
                        Coordinates.of(7, 7),
                        Coordinates.of(8, 8),
                        Coordinates.of(9, 9)));
    softly
        .assertThat(
            actual
                .filter(t2 -> t2._1.equals(Tuple.of("traders-and-barbarians", GLASSWORKS)))
                .map(Tuple2::_2))
        .allSatisfy(c ->
                assertThat(c)
                    .isIn(
                        Coordinates.of(5, 5),
                        Coordinates.of(6, 6),
                        Coordinates.of(7, 7),
                        Coordinates.of(8, 8),
                        Coordinates.of(9, 9)));
    softly
        .assertThat(
            actual
                .filter(t2 -> t2._1.equals(Tuple.of("traders-and-barbarians", QUARRY)))
                .map(Tuple2::_2))
        .allSatisfy(c ->
                assertThat(c)
                    .isIn(
                        Coordinates.of(5, 5),
                        Coordinates.of(6, 6),
                        Coordinates.of(7, 7),
                        Coordinates.of(8, 8),
                        Coordinates.of(9, 9)));
  }

  @Test
  @DisplayName("Should not dedupe tiles.")
  public void shouldNotDedupeTilesWithinSameSet() {
    final Map<String, Array<Tile>> tiles =
        HashMap.of("gold-field", Array.of(GOLD_FIELD, GOLD_FIELD));
    final Map<String, Array<Chit>> chits = HashMap.of("gold-field", Array.of(CHIT_2, CHIT_12));
    final Map<String, Array<String>> chitsTilesMap =
        HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("gold-field"));

    final Array<Tuple2<Tuple2<String, Tile>, Chit>> actual =
        SpecificationImpl.shuffleTiles(prng, tiles, chits, chitsTilesMap);

    assertThat(actual).hasSize(2);
  }

  @Test
  @DisplayName("Should not dedupe chitless tiles.")
  public void shouldNotDedupeChitlessTiles() {
    final Map<String, Array<Tile>> tiles =
        HashMap.of("chitless", Array.of(GOLD_FIELD), "chitful", Array.of(GOLD_FIELD));
    final Map<String, Array<Coordinate>> coordinates =
        HashMap.of(
            "chitless", Array.of(Coordinates.of(0, 0)),
            "chitful", Array.of(Coordinates.of(2, 2)));
    final Map<String, Array<Chit>> chits = HashMap.of("chitful", Array.of(CHITS_2_3_11_12));

    final SpecificationImpl specification =
        SpecificationImpl.newBuilder(
                tiles,
                coordinates,
                chits,
                HashMap.ofEntries(
                    TileMappingUtils.newSelfReferringEntry("chitless"),
                    TileMappingUtils.newSelfReferringEntry("chitful")),
                HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("chitful")))
            .build();

    final Option<Array<Configuration>> actual = specification.toConfiguration();

    assertThat(actual).isNotEmpty();
    assertThat(actual.get()).hasSize(2);
  }

  @Test
  @DisplayName("Should validate tile shapes.")
  public void shouldValidateTileShapes() {
    final Map<String, Array<Tile>> waterTiles = HashMap.of("water", Array.of(LAKE, FISHERY, SEA));

    final Set<String> actual = SpecificationImpl.checkTileShapes(waterTiles);

    assertThat(actual)
        .containsExactlyInAnyOrder("\"water\" tiles contain mixed shapes: HEXAGON, CHEVRON.");
  }

  @Test
  @DisplayName("Should validate mixed tile shapes.")
  public void shouldValidateMixedTileShapes() {
    final Map<String, Array<Tile>> tiles =
        HashMap.of("lake", Array.of(LAKE), "fishery", Array.of(FISHERY), "sea", Array.of(SEA));
    final Map<String, Array<Coordinate>> coordinates = HashMap.empty();
    final Map<String, Array<String>> coordinateTilesMap =
        HashMap.of(TileMappingUtils.newEntry("water", "lake", "fishery", "sea"));

    final Set<String> actual =
        SpecificationImpl.checkForCoordinateEdgesVersusTileShapesMismatchError(
            tiles, coordinates, coordinateTilesMap);

    assertThat(actual)
        .containsExactlyInAnyOrder(
            "[lake, fishery, sea] in \"water\" contain mixed shapes: HEXAGON, CHEVRON.");
  }

  @Test
  @DisplayName("Should validate mixed edge counts.")
  public void shouldValidateMixedEdgeCounts() {
    final Map<String, Array<Tile>> tiles = HashMap.of("lake", Array.of(LAKE), "sea", Array.of(SEA));
    final Map<String, Array<Coordinate>> coordinates =
        HashMap.of("water", Array.of(Coordinates.of(0, 0), Coordinates.onEdges(1, 1)));
    final Map<String, Array<String>> coordinateTilesMap =
        HashMap.of(TileMappingUtils.newEntry("water", "lake", "sea"));

    final Set<String> actual =
        SpecificationImpl.checkForCoordinateEdgesVersusTileShapesMismatchError(
            tiles, coordinates, coordinateTilesMap);

    assertThat(actual)
        .containsExactlyInAnyOrder("\"water\" coordinates contain mixed edge counts: 6, 0.");
  }

  @Test
  @DisplayName("Should validate hexagon tile edge count.")
  public void shouldValidateHexagonTileEdgeCount() {
    final Map<String, Array<Tile>> tiles = HashMap.of("hexagon", Array.of(LAKE));
    final Map<String, Array<Coordinate>> coordinates =
        HashMap.of("no-edges", Array.of(Coordinates.onEdges(0, 0)));
    final Map<String, Array<String>> coordinateTilesMap =
        HashMap.of(TileMappingUtils.newEntry("no-edges", "hexagon"));

    final Set<String> actual =
        SpecificationImpl.checkForCoordinateEdgesVersusTileShapesMismatchError(
            tiles, coordinates, coordinateTilesMap);

    assertThat(actual)
        .containsExactlyInAnyOrder(
            "HEXAGON tiles needing 6 edges are being configured with \"no-edges\" coordinates having 0 edges.",
            "Edge positions [] are not compatible with HEXAGON tiles.");
  }

  @Test
  @DisplayName("Should accept hexagon edge positions in any order.")
  public void shouldAcceptHexagonEdgePositionsInAnyOrder() {
    final Array<Edge.Position> edgePositions =
        Array.of(Edge.Position.values())
            .filter(ep -> ep != Edge.Position.UNRECOGNIZED)
            .shuffle(prng);

    final Map<String, Array<Tile>> tiles = HashMap.of("hexagon", Array.of(LAKE));
    final Map<String, Array<Coordinate>> coordinates =
        HashMap.of(
            "six-edges",
            Array.of(
                Coordinate.newBuilder()
                    .setX(0)
                    .setY(0)
                    .addAllEdgePositions(edgePositions)
                    .build()));
    final Map<String, Array<String>> coordinateTilesMap =
        HashMap.of(TileMappingUtils.newEntry("six-edges", "hexagon"));

    final Set<String> actual =
        SpecificationImpl.checkForCoordinateEdgesVersusTileShapesMismatchError(
            tiles, coordinates, coordinateTilesMap);

    assertThat(actual).isEmpty();
  }

  @Test
  @DisplayName("Should reject hexagon edge positions with duplicates.")
  public void shouldRejectHexagonEdgePositionsWithDuplicates() {
    final Array<Edge.Position> shuffledEdgePositions =
        Array.of(Edge.Position.values())
            .filter(ep -> ep != Edge.Position.UNRECOGNIZED)
            .shuffle(prng);
    final Array<Edge.Position> edgePositions =
        shuffledEdgePositions.dropRight(1).insert(1, shuffledEdgePositions.get(3));

    final Map<String, Array<Tile>> tiles = HashMap.of("hexagon", Array.of(LAKE));
    final Map<String, Array<Coordinate>> coordinates =
        HashMap.of(
            "six-edges",
            Array.of(
                Coordinate.newBuilder()
                    .setX(0)
                    .setY(0)
                    .addAllEdgePositions(edgePositions)
                    .build()));
    final Map<String, Array<String>> coordinateTilesMap =
        HashMap.of(TileMappingUtils.newEntry("six-edges", "hexagon"));

    final Set<String> actual =
        SpecificationImpl.checkForCoordinateEdgesVersusTileShapesMismatchError(
            tiles, coordinates, coordinateTilesMap);

    assertThat(actual)
        .are(
            allOf(
                matching(startsWith("Edge positions ")),
                matching(endsWith(" are not compatible with HEXAGON tiles."))));
  }

  @Test
  @DisplayName("Should not support pentagon tiles.")
  public void shouldNotSupportPentagonTiles() {
    final Map<String, Array<Tile>> tiles =
        HashMap.of(
            "pentagon", Array.of(Tile.newBuilder().setShape(PENTAGON).setType(UNDEFINED).build()));
    final Map<String, Array<Coordinate>> coordinates =
        HashMap.of("no-edges", Array.of(Coordinates.onEdges(0, 0)));
    final Map<String, Array<String>> coordinateTilesMap =
        HashMap.of(TileMappingUtils.newEntry("no-edges", "pentagon"));

    assertThatThrownBy(
            () ->
                SpecificationImpl.checkForCoordinateEdgesVersusTileShapesMismatchError(
                    tiles, coordinates, coordinateTilesMap))
        .isInstanceOf(UnsupportedOperationException.class)
        .hasMessage("Unsupported shape: PENTAGON");
  }

  @Test
  @DisplayName("Should not support trapezoid tiles.")
  public void shouldNotSupportTrapezoidTiles() {
    final Map<String, Array<Tile>> tiles =
        HashMap.of(
            "trapezoid",
            Array.of(Tile.newBuilder().setShape(TRAPEZOID).setType(UNDEFINED).build()));
    final Map<String, Array<Coordinate>> coordinates =
        HashMap.of("no-edges", Array.of(Coordinates.onEdges(0, 0)));
    final Map<String, Array<String>> coordinateTilesMap =
        HashMap.of(TileMappingUtils.newEntry("no-edges", "trapezoid"));

    assertThatThrownBy(
            () ->
                SpecificationImpl.checkForCoordinateEdgesVersusTileShapesMismatchError(
                    tiles, coordinates, coordinateTilesMap))
        .isInstanceOf(UnsupportedOperationException.class)
        .hasMessage("Unsupported shape: TRAPEZOID");
  }

  @Test
  @DisplayName("Should validate chevron tile edge count.")
  public void shouldValidateChevronTileEdgeCount() {
    final Map<String, Array<Tile>> tiles = HashMap.of("chevron", Array.of(FISHERY));
    final Map<String, Array<Coordinate>> coordinates =
        HashMap.of("no-edges", Array.of(Coordinates.onEdges(0, 0)));
    final Map<String, Array<String>> coordinateTilesMap =
        HashMap.of(TileMappingUtils.newEntry("no-edges", "chevron"));

    final Set<String> actual =
        SpecificationImpl.checkForCoordinateEdgesVersusTileShapesMismatchError(
            tiles, coordinates, coordinateTilesMap);

    assertThat(actual)
        .containsExactlyInAnyOrder(
            "CHEVRON tiles needing 2 edges are being configured with \"no-edges\" coordinates having 0 edges.",
            "Edge positions [] are not compatible with CHEVRON tiles.");
  }

  @Test
  @DisplayName("Should accept chevron with adjacent edge positions.")
  public void shouldAcceptChevronWithAdjacentEdgePositions() {
    final int edgePositionCount = Edge.Position.values().length - 1;
    final var firstEdgePosition = Edge.Position.forNumber(prng.nextInt(edgePositionCount));
    final var secondEdgePosition =
        Edge.Position.forNumber(
            (firstEdgePosition.getNumber() + prng.nextInt(2) * 2 - 1) % edgePositionCount);
    final var edgePositions = Array.of(firstEdgePosition, secondEdgePosition);

    final Map<String, Array<Tile>> tiles = HashMap.of("chevron", Array.of(FISHERY));
    final Map<String, Array<Coordinate>> coordinates =
        HashMap.of(
            "two-edges",
            Array.of(
                Coordinate.newBuilder()
                    .setX(0)
                    .setY(0)
                    .addAllEdgePositions(edgePositions)
                    .build()));
    final Map<String, Array<String>> coordinateTilesMap =
        HashMap.of(TileMappingUtils.newEntry("two-edges", "chevron"));

    final Set<String> actual =
        SpecificationImpl.checkForCoordinateEdgesVersusTileShapesMismatchError(
            tiles, coordinates, coordinateTilesMap);

    assertThat(actual).isEmpty();
  }

  @Test
  @DisplayName("Should reject chevron with non-adjacent edge positions.")
  public void shouldRejectChevronWithNonAdjacentEdgePositions() {
    final int edgePositionCount = Edge.Position.values().length - 1;
    final var firstEdgePosition = Edge.Position.forNumber(prng.nextInt(edgePositionCount));
    final var secondEdgePosition =
        Edge.Position.forNumber(
            (firstEdgePosition.getNumber() + prng.nextInt(5) + 2) % edgePositionCount);
    final var edgePositions = Array.of(firstEdgePosition, secondEdgePosition);

    final Map<String, Array<Tile>> tiles = HashMap.of("chevron", Array.of(FISHERY));
    final Map<String, Array<Coordinate>> coordinates =
        HashMap.of(
            "two-edges",
            Array.of(
                Coordinate.newBuilder()
                    .setX(0)
                    .setY(0)
                    .addAllEdgePositions(edgePositions)
                    .build()));
    final Map<String, Array<String>> coordinateTilesMap =
        HashMap.of(TileMappingUtils.newEntry("two-edges", "chevron"));

    final Set<String> actual =
        SpecificationImpl.checkForCoordinateEdgesVersusTileShapesMismatchError(
            tiles, coordinates, coordinateTilesMap);

    assertThat(actual)
        .are(
            allOf(
                matching(startsWith("Edge positions ")),
                matching(endsWith(" are not compatible with CHEVRON tiles."))));
  }

  @Test
  @DisplayName("Should validate rectangle tile edge count.")
  public void shouldValidateRectangleTileEdgeCount() {
    final Map<String, Array<Tile>> tiles = HashMap.of("rectangle", Array.of(DEVELOPMENT_CARD));
    final Map<String, Array<Coordinate>> coordinates =
        HashMap.of("no-edges", Array.of(Coordinates.onEdges(0, 0)));
    final Map<String, Array<String>> coordinateTilesMap =
        HashMap.of(TileMappingUtils.newEntry("no-edges", "rectangle"));

    final Set<String> actual =
        SpecificationImpl.checkForCoordinateEdgesVersusTileShapesMismatchError(
            tiles, coordinates, coordinateTilesMap);

    assertThat(actual)
        .containsExactlyInAnyOrder(
            "RECTANGLE tiles needing 2 edges are being configured with \"no-edges\" coordinates having 0 edges.",
            "Edge positions [] are not compatible with RECTANGLE tiles.");
  }

  @Test
  @DisplayName("Should accept rectangle with opposite edge positions.")
  public void shouldAcceptRectangleWithOppositeEdgePositions() {
    final int edgePositionCount = Edge.Position.values().length - 1;
    final var firstEdgePosition = Edge.Position.forNumber(prng.nextInt(edgePositionCount));
    final var secondEdgePosition =
        Edge.Position.forNumber(
            (firstEdgePosition.getNumber() + edgePositionCount / 2) % edgePositionCount);
    final var edgePositions = Array.of(firstEdgePosition, secondEdgePosition);

    final Map<String, Array<Tile>> tiles = HashMap.of("rectangle", Array.of(DEVELOPMENT_CARD));
    final Map<String, Array<Coordinate>> coordinates =
        HashMap.of(
            "two-edges",
            Array.of(
                Coordinate.newBuilder()
                    .setX(0)
                    .setY(0)
                    .addAllEdgePositions(edgePositions)
                    .build()));
    final Map<String, Array<String>> coordinateTilesMap =
        HashMap.of(TileMappingUtils.newEntry("two-edges", "rectangle"));

    final Set<String> actual =
        SpecificationImpl.checkForCoordinateEdgesVersusTileShapesMismatchError(
            tiles, coordinates, coordinateTilesMap);

    assertThat(actual).isEmpty();
  }

  @Test
  @DisplayName("Should reject rectangle with non-opposite edge positions.")
  public void shouldRejectChevronWithNonOppositeEdgePositions() {
    final int edgePositionCount = Edge.Position.values().length - 1;
    final var firstEdgePosition = Edge.Position.forNumber(prng.nextInt(edgePositionCount));
    final var secondEdgePosition =
        Edge.Position.forNumber(
            (firstEdgePosition.getNumber() + prng.nextInt(5) + 4) % edgePositionCount);
    final var edgePositions = Array.of(firstEdgePosition, secondEdgePosition);

    final Map<String, Array<Tile>> tiles = HashMap.of("rectangle", Array.of(DEVELOPMENT_CARD));
    final Map<String, Array<Coordinate>> coordinates =
        HashMap.of(
            "two-edges",
            Array.of(
                Coordinate.newBuilder()
                    .setX(0)
                    .setY(0)
                    .addAllEdgePositions(edgePositions)
                    .build()));
    final Map<String, Array<String>> coordinateTilesMap =
        HashMap.of(TileMappingUtils.newEntry("two-edges", "rectangle"));

    final Set<String> actual =
        SpecificationImpl.checkForCoordinateEdgesVersusTileShapesMismatchError(
            tiles, coordinates, coordinateTilesMap);

    assertThat(actual)
        .are(
            allOf(
                matching(startsWith("Edge positions ")),
                matching(endsWith(" are not compatible with RECTANGLE tiles."))));
  }

  @Test
  @DisplayName("Should validate triangle tile edge count.")
  public void shouldValidateTriangleTileEdgeCount() {
    final Map<String, Array<Tile>> tiles = HashMap.of("triangle", Array.of(GENERIC_HARBOR));
    final Map<String, Array<Coordinate>> coordinates =
        HashMap.of("no-edges", Array.of(Coordinates.onEdges(0, 0)));
    final Map<String, Array<String>> coordinateTilesMap =
        HashMap.of(TileMappingUtils.newEntry("no-edges", "triangle"));

    final Set<String> actual =
        SpecificationImpl.checkForCoordinateEdgesVersusTileShapesMismatchError(
            tiles, coordinates, coordinateTilesMap);

    assertThat(actual)
        .containsExactlyInAnyOrder(
            "TRIANGLE tiles needing 1 edge are being configured with \"no-edges\" coordinates having 0 edges.",
            "Edge positions [] are not compatible with TRIANGLE tiles.");
  }

  @Test
  @DisplayName("Should validate point tile edge count.")
  public void shouldValidatePointTileEdgeCount() {
    final Map<String, Array<Tile>> tiles = HashMap.of("point", Array.of(CHIT));
    final Map<String, Array<Coordinate>> coordinates =
        HashMap.of("no-edges", Array.of(Coordinates.of(0, 0)));
    final Map<String, Array<String>> coordinateTilesMap =
        HashMap.of(TileMappingUtils.newEntry("no-edges", "point"));

    final Set<String> actual =
        SpecificationImpl.checkForCoordinateEdgesVersusTileShapesMismatchError(
            tiles, coordinates, coordinateTilesMap);

    assertThat(actual)
        .containsExactlyInAnyOrder(
            "POINT tiles needing 0 edges are being configured with \"no-edges\" coordinates having 6 edges.",
            "Edge positions [TOP_RIGHT, RIGHT, BOTTOM_RIGHT, BOTTOM_LEFT, LEFT, TOP_LEFT] are not compatible with POINT tiles.");
  }

  @Test
  @DisplayName("Should validate duplicate coordinates.")
  public void shouldValidateDuplicateCoordinates(final SoftAssertions softly) {
    final Map<String, Array<Coordinate>> coordinates =
        HashMap.of(
            "key-1",
            Array.of(Coordinates.of(1, 2), Coordinates.of(1, 2), Coordinates.of(3, 5)),
            "key-2",
            Array.of(Coordinates.of(3, 5), Coordinates.of(8, 13)));

    final Set<String> actual = SpecificationImpl.checkForDuplicateCoordinates(coordinates);

    final var edgePositions =
        "edge_positions: TOP_RIGHT\nedge_positions: RIGHT\nedge_positions: BOTTOM_RIGHT\nedge_positions: BOTTOM_LEFT\nedge_positions: LEFT\nedge_positions: TOP_LEFT\n";
    final var vertexPositions =
        "vertex_positions: TOP\nvertex_positions: TOP_RIGHT\nvertex_positions: BOTTOM_RIGHT\nvertex_positions: BOTTOM\nvertex_positions: BOTTOM_LEFT\nvertex_positions: TOP_LEFT\n";

    softly.assertThat(actual).hasSize(2);
    softly
        .assertThat(actual)
        .containsExactlyInAnyOrder(
            "Coordinate `x: 1\ny: 2\n"
                + edgePositions
                + vertexPositions
                + "` referenced more than once in [key-1]",
            "Coordinate `x: 3\ny: 5\n"
                + edgePositions
                + vertexPositions
                + "` referenced more than once across [key-1, key-2]");
  }

  @Test
  @DisplayName("Should validate count mismatch.")
  public void shouldValidateCountMismatch() {
    final Map<String, Array<Tile>> tiles = HashMap.of("sea", Array.of(SEA));
    final Map<String, Array<Chit>> chits = HashMap.of("sea", Array.of(CHIT_2, CHIT_12));
    final Map<String, Array<String>> chitsTilesMap =
        HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("sea"));
    final Map<String, Array<Coordinate>> coordinates =
        HashMap.of("sea", Array.of(Coordinates.of(1, 1), Coordinates.of(2, 2)));
    final Map<String, Array<String>> coordinatesTilesMap =
        HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("sea"));

    final Set<String> actual =
        SpecificationImpl.checkForFeaturesVersusTilesCountMismatchError(
                "chits", tiles, SpecificationImpl.widenFeaturesMap(chits), chitsTilesMap)
            .union(
                SpecificationImpl.checkForFeaturesVersusTilesCountMismatchError(
                    "coordinates",
                    tiles,
                    SpecificationImpl.widenFeaturesMap(coordinates),
                    coordinatesTilesMap));

    assertThat(actual)
        .containsExactlyInAnyOrder(
            "2 chits in \"sea\" do not match 1 tiles in [sea].",
            "2 coordinates in \"sea\" do not match 1 tiles in [sea].");
  }

  @Test
  @DisplayName("Should validate undefined tiles.")
  public void shouldValidateUndefinedTiles() {
    final Map<String, Array<Tile>> tiles =
        HashMap.of(
            "desert", Array.of(DESERT, DESERT), "gold-field", Array.of(GOLD_FIELD, GOLD_FIELD));
    final Map<String, Array<Chit>> chits = HashMap.of("oasis", Array.of(CHIT_2, CHIT_12));
    final Map<String, Array<String>> chitsTilesMap =
        HashMap.ofEntries(TileMappingUtils.newEntry("swamp", "desert", "lake"));
    final Map<String, Array<Coordinate>> coordinates =
        HashMap.of("swamp", Array.of(Coordinates.of(1, 1), Coordinates.of(2, 2)));
    final Map<String, Array<String>> coordinatesTilesMap =
        HashMap.ofEntries(TileMappingUtils.newEntry("oasis", "desert", "lake"));

    final Set<String> actual =
        SpecificationImpl.checkForUndefinedTilesErrors(
                "chits", tiles, SpecificationImpl.widenFeaturesMap(chits), chitsTilesMap)
            .union(
                SpecificationImpl.checkForUndefinedTilesErrors(
                    "coordinates",
                    tiles,
                    SpecificationImpl.widenFeaturesMap(coordinates),
                    coordinatesTilesMap));

    assertThat(actual)
        .containsExactlyInAnyOrder(
            "[lake] tiles referenced in chitsTilesMap \"swamp\" are not defined.",
            "[lake] tiles referenced in coordinatesTilesMap \"oasis\" are not defined.");
  }

  @Test
  @DisplayName("Should validate unreferenced tiles.")
  public void shouldValidateUnreferencedTiles() {
    final Map<String, Array<Tile>> tiles =
        HashMap.of(
            "desert", Array.of(DESERT, DESERT), "gold-field", Array.of(GOLD_FIELD, GOLD_FIELD));
    final Map<String, Array<Chit>> chits = HashMap.of("oasis", Array.of(CHIT_2, CHIT_12));
    final Map<String, Array<String>> chitsTilesMap =
        HashMap.ofEntries(TileMappingUtils.newEntry("swamp", "desert", "lake"));
    final Map<String, Array<Coordinate>> coordinates =
        HashMap.of("swamp", Array.of(Coordinates.of(1, 1), Coordinates.of(2, 2)));
    final Map<String, Array<String>> coordinatesTilesMap =
        HashMap.ofEntries(TileMappingUtils.newEntry("oasis", "desert", "lake"));

    final Set<String> actual =
        SpecificationImpl.checkForUnreferencedTilesErrors(
                "chits", tiles, SpecificationImpl.widenFeaturesMap(chits), chitsTilesMap)
            .union(
                SpecificationImpl.checkForUnreferencedTilesErrors(
                    "coordinates",
                    tiles,
                    SpecificationImpl.widenFeaturesMap(coordinates),
                    coordinatesTilesMap));

    assertThat(actual)
        .containsExactlyInAnyOrder(
            "\"gold-field\" tiles is not referenced in chitsTilesMap.",
            "\"gold-field\" tiles is not referenced in coordinatesTilesMap.");
  }

  @Test
  @DisplayName("Should validate undefined features.")
  public void shouldValidateUndefinedFeatures() {
    final Map<String, Array<Tile>> tiles =
        HashMap.of(
            "desert", Array.of(DESERT, DESERT), "gold-field", Array.of(GOLD_FIELD, GOLD_FIELD));
    final Map<String, Array<Chit>> chits = HashMap.of("oasis", Array.of(CHIT_2, CHIT_12));
    final Map<String, Array<String>> chitsTilesMap =
        HashMap.ofEntries(TileMappingUtils.newEntry("swamp", "desert", "lake"));
    final Map<String, Array<Coordinate>> coordinates =
        HashMap.of("swamp", Array.of(Coordinates.of(1, 1), Coordinates.of(2, 2)));
    final Map<String, Array<String>> coordinatesTilesMap =
        HashMap.ofEntries(TileMappingUtils.newEntry("oasis", "desert", "lake"));

    final Set<String> actual =
        SpecificationImpl.checkForUndefinedFeaturesErrors(
                "chits", tiles, SpecificationImpl.widenFeaturesMap(chits), chitsTilesMap)
            .union(
                SpecificationImpl.checkForUndefinedFeaturesErrors(
                    "coordinates",
                    tiles,
                    SpecificationImpl.widenFeaturesMap(coordinates),
                    coordinatesTilesMap));

    assertThat(actual)
        .containsExactlyInAnyOrder(
            "\"swamp\" referenced in chitsTilesMap not defined in chits.",
            "\"oasis\" referenced in coordinatesTilesMap not defined in coordinates.");
  }

  @Test
  @DisplayName("Should validate unreferenced features.")
  public void shouldValidateUnreferencedFeatures() {
    final Map<String, Array<Tile>> tiles =
        HashMap.of(
            "desert", Array.of(DESERT, DESERT), "gold-field", Array.of(GOLD_FIELD, GOLD_FIELD));
    final Map<String, Array<Chit>> chits = HashMap.of("oasis", Array.of(CHIT_2, CHIT_12));
    final Map<String, Array<String>> chitsTilesMap =
        HashMap.ofEntries(TileMappingUtils.newEntry("swamp", "desert", "lake"));
    final Map<String, Array<Coordinate>> coordinates =
        HashMap.of("swamp", Array.of(Coordinates.of(1, 1), Coordinates.of(2, 2)));
    final Map<String, Array<String>> coordinatesTilesMap =
        HashMap.ofEntries(TileMappingUtils.newEntry("oasis", "desert", "lake"));

    final Set<String> actual =
        SpecificationImpl.checkForUnreferencedFeaturesErrors(
                "chits", tiles, SpecificationImpl.widenFeaturesMap(chits), chitsTilesMap)
            .union(
                SpecificationImpl.checkForUnreferencedFeaturesErrors(
                    "coordinates",
                    tiles,
                    SpecificationImpl.widenFeaturesMap(coordinates),
                    coordinatesTilesMap));

    assertThat(actual)
        .containsExactlyInAnyOrder(
            "\"swamp\" in coordinates not referenced in coordinatesTilesMap.",
            "\"oasis\" in chits not referenced in chitsTilesMap.");
  }

  @Test
  @DisplayName("Should validate.")
  public void shouldValidate() {
    final Map<String, Array<Tile>> tiles =
        HashMap.of(
            "desert", Array.of(DESERT, DESERT), "gold-field", Array.of(GOLD_FIELD, GOLD_FIELD));
    final Map<String, Array<Chit>> chits = HashMap.of("oasis", Array.of(CHIT_2, CHIT_12));
    final Map<String, Array<String>> chitsTilesMap =
        HashMap.ofEntries(TileMappingUtils.newEntry("swamp", "desert", "lake"));
    final Map<String, Array<Coordinate>> coordinates =
        HashMap.of("swamp", Array.of(Coordinates.of(1, 1), Coordinates.of(2, 2)));
    final Map<String, Array<String>> coordinatesTilesMap =
        HashMap.ofEntries(TileMappingUtils.newEntry("oasis", "desert", "lake"));

    assertThatThrownBy(
            () ->
                SpecificationImpl.newBuilder(
                        tiles, coordinates, chits, coordinatesTilesMap, chitsTilesMap)
                    .build())
        .isInstanceOf(InvalidSpecificationError.class)
        .hasMessageContainingAll(
            "0 chits in \"swamp\" do not match 2 tiles in [desert, lake].",
            "0 coordinates in \"oasis\" do not match 2 tiles in [desert, lake].",
            "[lake] tiles referenced in chitsTilesMap \"swamp\" are not defined.",
            "[lake] tiles referenced in coordinatesTilesMap \"oasis\" are not defined.",
            "\"gold-field\" tiles is not referenced in coordinatesTilesMap.",
            "\"swamp\" referenced in chitsTilesMap not defined in chits.",
            "\"oasis\" referenced in coordinatesTilesMap not defined in coordinates.",
            "\"oasis\" in chits not referenced in chitsTilesMap.",
            "\"swamp\" in coordinates not referenced in coordinatesTilesMap.");
  }

  @Test
  @DisplayName("Should create configuration.")
  public void shouldCreateConfiguration(final SoftAssertions softly) {
    final Array<Tile> producingLandTiles =
        Array.of(FIELD, FOREST, HILL, MOUNTAIN, PASTURE, GOLD_FIELD, LAKE);
    final Array<Tile> barrenLandTiles = Array.of(DESERT, SWAMP, OASIS);
    final Array<Tile> tradersAndBarbariansDestinationTiles = Array.of(CASTLE, GLASSWORKS, QUARRY);

    final Array<Chit> producingLandChits =
        Array.of(CHITS_4_10, CHIT_2, CHIT_4, CHIT_6, CHIT_8, CHIT_10, CHIT_12);

    final Array<Coordinate> terrainCoordinates =
        Array.of(
            Coordinates.of(1, 1),
            Coordinates.of(2, 2),
            Coordinates.of(3, 3),
            Coordinates.of(4, 4),
            Coordinates.of(5, 5),
            Coordinates.of(6, 6),
            Coordinates.of(7, 7),
            Coordinates.of(8, 8),
            Coordinates.of(9, 9),
            Coordinates.of(10, 10));
    final Array<Coordinate> tradersAndBarbariansDestinationCoordinates =
        Array.of(Coordinates.of(11, 11), Coordinates.of(12, 12), Coordinates.of(13, 13));

    final Map<String, Array<Tile>> tiles =
        HashMap.of(
            "producing-terrain",
            producingLandTiles,
            "barren-terrain",
            barrenLandTiles,
            "traders-and-barbarians-destination",
            tradersAndBarbariansDestinationTiles);
    final Map<String, Array<Chit>> chits = HashMap.of("producing-terrain", producingLandChits);
    final Map<String, Array<String>> chitsTilesMap =
        HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("producing-terrain"));
    final Map<String, Array<Coordinate>> coordinates =
        HashMap.of(
            "terrain",
            terrainCoordinates,
            "traders-and-barbarians-destination",
            tradersAndBarbariansDestinationCoordinates);
    final Map<String, Array<String>> coordinatesTilesMap =
        HashMap.ofEntries(
            TileMappingUtils.newEntry("terrain", "producing-terrain", "barren-terrain"),
            TileMappingUtils.newSelfReferringEntry("traders-and-barbarians-destination"));

    final SpecificationImpl specificationImpl =
        SpecificationImpl.newBuilder(tiles, coordinates, chits, coordinatesTilesMap, chitsTilesMap)
            .build();

    final Array<Configuration> actual = specificationImpl.toConfiguration().get();

    final Set<Configuration> producingLandConfigurationsFromTiles =
        actual.filter(c -> producingLandTiles.contains(c.getTile())).toSet();
    final Set<Configuration> barrenLandConfigurationsFromTiles =
        actual.filter(c -> barrenLandTiles.contains(c.getTile())).toSet();
    final Set<Configuration> tradersAndBarbariansDestinationConfigurationsFromTiles =
        actual.filter(c -> tradersAndBarbariansDestinationTiles.contains(c.getTile())).toSet();

    final Set<Configuration> producingLandConfigurationsFromChits =
        actual.filter(c -> producingLandChits.contains(c.getChit())).toSet();
    final Set<Configuration> configurationsWithNoChits =
        actual.filter(c -> c.getChit().getValuesCount() == 0).toSet();

    final Set<Configuration> terrainConfigurationsFromCoordinates =
        actual.filter(c -> terrainCoordinates.contains(c.getCoordinate())).toSet();
    final Set<Configuration> tradersAndBarbariansDestinationConfigurationsFromCoordinates =
        actual
            .filter(c -> tradersAndBarbariansDestinationCoordinates.contains(c.getCoordinate()))
            .toSet();

    softly.assertThat(actual).hasSize(13);
    softly
        .assertThat(producingLandConfigurationsFromTiles)
        .containsExactlyInAnyOrder(
            producingLandConfigurationsFromChits.toJavaArray(Configuration[]::new));
    softly
        .assertThat(
            barrenLandConfigurationsFromTiles.union(
                tradersAndBarbariansDestinationConfigurationsFromTiles))
        .containsExactlyInAnyOrder(configurationsWithNoChits.toJavaArray(Configuration[]::new));
    softly
        .assertThat(producingLandConfigurationsFromTiles.union(barrenLandConfigurationsFromTiles))
        .containsExactlyInAnyOrder(
            terrainConfigurationsFromCoordinates.toJavaArray(Configuration[]::new));
    softly
        .assertThat(tradersAndBarbariansDestinationConfigurationsFromTiles)
        .containsExactlyInAnyOrder(
            tradersAndBarbariansDestinationConfigurationsFromCoordinates.toJavaArray(
                Configuration[]::new));
  }

  @Test
  @DisplayName("Should not mix up tiles, coordinates, and chits.")
  public void shouldNotMixUpTilesCoordinatesAndChits(final SoftAssertions softly) {
    final String tiles0Name = "tiles0";
    final Array<Tile> tiles0 = Array.fill(3, GOLD_FIELD);
    final String tiles1Name = "tiles1";
    final Array<Tile> tiles1 = Array.fill(3, GOLD_FIELD);
    final String tiles2Name = "tiles2";
    final Array<Tile> tiles2 = Array.fill(3, GOLD_FIELD);
    final Map<String, Array<Tile>> tiles =
        HashMap.of(
            tiles0Name, tiles0,
            tiles1Name, tiles1,
            tiles2Name, tiles2);

    final String coordinates0Name = "coordinates0";
    final Array<Coordinate> coordinates0 =
        Array.of(Coordinates.of(0, 0), Coordinates.of(6, 6), Coordinates.of(12, 12));
    final String coordinates1Name = "coordinates1";
    final Array<Coordinate> coordinates1 =
        Array.of(Coordinates.of(2, 2), Coordinates.of(8, 8), Coordinates.of(14, 14));
    final String coordinates2Name = "coordinates2";
    final Array<Coordinate> coordinates2 =
        Array.of(Coordinates.of(4, 4), Coordinates.of(10, 10), Coordinates.of(16, 16));
    final Map<String, Array<Coordinate>> coordinates =
        HashMap.of(
            coordinates0Name, coordinates0,
            coordinates1Name, coordinates1,
            coordinates2Name, coordinates2);
    final Map<String, Array<String>> coordinatesTilesMap =
        HashMap.ofEntries(
            TileMappingUtils.newEntry("coordinates2", "tiles0"),
            TileMappingUtils.newEntry("coordinates1", "tiles1"),
            TileMappingUtils.newEntry("coordinates0", "tiles2"));

    final String chits0Name = "chits0";
    final Array<Chit> chits0 = Array.of(CHIT_2, CHIT_6, CHIT_10);
    final String chits2Name = "chits2";
    final Array<Chit> chits2 = Array.of(CHIT_4, CHIT_8, CHIT_12);
    final Map<String, Array<Chit>> chits =
        HashMap.of(
            chits0Name, chits0,
            chits2Name, chits2);
    final Map<String, Array<String>> chitsTilesMap =
        HashMap.ofEntries(
            TileMappingUtils.newEntry("chits0", "tiles0"),
            TileMappingUtils.newEntry("chits2", "tiles2"));

    final SpecificationImpl specificationImpl =
        SpecificationImpl.newBuilder(tiles, coordinates, chits, coordinatesTilesMap, chitsTilesMap)
            .build();

    final Array<Configuration> configurations = specificationImpl.toConfiguration().get();

    final Set<Chit> coordinates0Chits =
        configurations
            .filter(c -> coordinates0.contains(c.getCoordinate()))
            .map(Configuration::getChit)
            .filter(c -> c.getValuesCount() > 0)
            .toSet();
    final Set<Chit> coordinates1Chits =
        configurations
            .filter(c -> coordinates1.contains(c.getCoordinate()))
            .map(Configuration::getChit)
            .filter(c -> c.getValuesCount() > 0)
            .toSet();
    final Set<Chit> coordinates2Chits =
        configurations
            .filter(c -> coordinates2.contains(c.getCoordinate()))
            .map(Configuration::getChit)
            .filter(c -> c.getValuesCount() > 0)
            .toSet();

    softly.assertThat(coordinates0Chits).containsExactlyInAnyOrder(CHIT_4, CHIT_8, CHIT_12);
    softly.assertThat(coordinates1Chits).isEmpty();
    softly.assertThat(coordinates2Chits).containsExactlyInAnyOrder(CHIT_2, CHIT_6, CHIT_10);
  }

  @Test
  @DisplayName("Should not pass configuration validator.")
  public void shouldNotPassConfigurationValidator() {
    final Map<String, Array<Tile>> tiles = HashMap.of("single", Array.of(GOLD_FIELD));
    final Map<String, Array<Coordinate>> coordinates =
        HashMap.of("single", Array.of(Coordinates.of(0, 0)));
    final Map<String, Array<Chit>> chits = HashMap.of("single", Array.of(CHITS_4_10));

    final SpecificationImpl specification =
        SpecificationImpl.newBuilder(
                tiles,
                coordinates,
                chits,
                HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("single")),
                HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("single")))
            .withConfigurationMatcher(
                IsNot.not(
                    AllOf.allOf(
                        TileIs.tileIs(GOLD_FIELD), HasOddsGreaterThan.hasOddsGreaterThan(4))))
            .build();

    final Option<Array<Configuration>> actual = specification.toConfiguration();

    assertThat(actual).isEmpty();
  }
}
