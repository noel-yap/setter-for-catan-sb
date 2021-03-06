package noelyap.setterforcatan.scenario.seafarers;

import static noelyap.setterforcatan.component.Chits.CHIT_10;
import static noelyap.setterforcatan.component.Chits.CHIT_11;
import static noelyap.setterforcatan.component.Chits.CHIT_12;
import static noelyap.setterforcatan.component.Chits.CHIT_2;
import static noelyap.setterforcatan.component.Chits.CHIT_3;
import static noelyap.setterforcatan.component.Chits.CHIT_4;
import static noelyap.setterforcatan.component.Chits.CHIT_5;
import static noelyap.setterforcatan.component.Chits.CHIT_6;
import static noelyap.setterforcatan.component.Chits.CHIT_8;
import static noelyap.setterforcatan.component.Chits.CHIT_9;
import static noelyap.setterforcatan.component.Tiles.BRICK_HARBOR;
import static noelyap.setterforcatan.component.Tiles.DESERT;
import static noelyap.setterforcatan.component.Tiles.DEVELOPMENT_CARD;
import static noelyap.setterforcatan.component.Tiles.FIELD;
import static noelyap.setterforcatan.component.Tiles.FOREST;
import static noelyap.setterforcatan.component.Tiles.GENERIC_HARBOR;
import static noelyap.setterforcatan.component.Tiles.GOLD_FIELD;
import static noelyap.setterforcatan.component.Tiles.GRAIN_HARBOR;
import static noelyap.setterforcatan.component.Tiles.HILL;
import static noelyap.setterforcatan.component.Tiles.LUMBER_HARBOR;
import static noelyap.setterforcatan.component.Tiles.MOUNTAIN;
import static noelyap.setterforcatan.component.Tiles.ORE_HARBOR;
import static noelyap.setterforcatan.component.Tiles.PASTURE;
import static noelyap.setterforcatan.component.Tiles.VICTORY_POINT;
import static noelyap.setterforcatan.component.Tiles.WOOL_HARBOR;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge.Position.BOTTOM_LEFT;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge.Position.BOTTOM_RIGHT;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge.Position.LEFT;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge.Position.RIGHT;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge.Position.TOP_LEFT;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge.Position.TOP_RIGHT;

import com.google.common.annotations.VisibleForTesting;
import io.vavr.collection.Array;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import noelyap.setterforcatan.component.Coordinates;
import noelyap.setterforcatan.component.SpecificationImpl;
import noelyap.setterforcatan.matcher.CoordinateIsIn;
import noelyap.setterforcatan.matcher.HasOddsGreaterThan;
import noelyap.setterforcatan.protogen.ChitOuterClass;
import noelyap.setterforcatan.protogen.ConfigurationOuterClass;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Coordinate;
import noelyap.setterforcatan.protogen.TileOuterClass.Tile;
import noelyap.setterforcatan.scenario.Base;
import noelyap.setterforcatan.util.TileMappingUtils;
import org.hamcrest.Matcher;
import org.hamcrest.core.AllOf;
import org.hamcrest.core.IsNot;

public class TheForgottenTribe {
  private static final Array<Tile> P3_P4_MAIN_ISLAND_LAND_TILES = Base.P3_P4_PRODUCING_TILES;
  private static final Array<Tile> P3_P4_SMALL_ISLAND_LAND_TILES =
      Array.of(FIELD, FOREST, PASTURE)
          .appendAll(Array.fill(2, GOLD_FIELD))
          .appendAll(Array.fill(2, HILL))
          .appendAll(Array.fill(2, MOUNTAIN))
          .appendAll(Array.fill(3, DESERT));
  private static final Array<Tile> P3_P4_HARBOR_TILES =
      Array.of(BRICK_HARBOR, GENERIC_HARBOR, GRAIN_HARBOR, LUMBER_HARBOR, ORE_HARBOR, WOOL_HARBOR);
  private static final Array<Tile> P3_P4_DEVELOPMENT_CARD_TILES = Array.fill(4, DEVELOPMENT_CARD);
  private static final Array<Tile> P3_P4_VICTORY_POINT_TILES = Array.fill(8, VICTORY_POINT);
  private static final Map<String, Array<Tile>> P3_P4_TILES =
      HashMap.of(
          "main-island-land",
          P3_P4_MAIN_ISLAND_LAND_TILES,
          "small-island-land",
          P3_P4_SMALL_ISLAND_LAND_TILES,
          "harbor",
          P3_P4_HARBOR_TILES,
          "development-card",
          P3_P4_DEVELOPMENT_CARD_TILES,
          "victory-point",
          P3_P4_VICTORY_POINT_TILES);

  private static final Array<Coordinate> P3_P4_MAIN_ISLAND_LAND_COORDINATES =
      Array.of(
          Coordinates.of(1, 3),
          Coordinates.of(3, 3),
          Coordinates.of(5, 3),
          Coordinates.of(7, 3),
          Coordinates.of(9, 3),
          Coordinates.of(11, 3),
          Coordinates.of(2, 4),
          Coordinates.of(4, 4),
          Coordinates.of(6, 4),
          Coordinates.of(8, 4),
          Coordinates.of(10, 4),
          Coordinates.of(12, 4),
          Coordinates.of(1, 5),
          Coordinates.of(3, 5),
          Coordinates.of(5, 5),
          Coordinates.of(7, 5),
          Coordinates.of(9, 5),
          Coordinates.of(11, 5));
  private static final Array<Coordinate> P3_P4_MAIN_ISLAND_FISHERY_COORDINATES =
      Array.of(
          Coordinates.onEdges(1, 3, BOTTOM_LEFT, LEFT), Coordinates.onEdges(1, 5, LEFT, TOP_LEFT));
  private static final Array<Coordinate> P3_P4_SMALL_ISLAND_LAND_COORDINATES =
      Array.of(
          Coordinates.of(3, 1),
          Coordinates.of(5, 1),
          Coordinates.of(9, 1),
          Coordinates.of(11, 1),
          Coordinates.of(13, 1),
          Coordinates.of(15, 3),
          Coordinates.of(15, 5),
          Coordinates.of(3, 7),
          Coordinates.of(5, 7),
          Coordinates.of(9, 7),
          Coordinates.of(11, 7),
          Coordinates.of(13, 7));
  private static final Array<Coordinate> P3_P4_HARBOR_COORDINATES =
      Array.of(
          Coordinates.faceDownOnEdges(2, 0, BOTTOM_RIGHT),
          Coordinates.faceDownOnEdges(8, 0, BOTTOM_RIGHT),
          Coordinates.faceDownOnEdges(16, 2, BOTTOM_LEFT),
          Coordinates.faceDownOnEdges(2, 8, TOP_RIGHT),
          Coordinates.faceDownOnEdges(10, 8, TOP_LEFT),
          Coordinates.faceDownOnEdges(16, 6, TOP_LEFT));
  private static final Array<Coordinate> P3_P4_DEVELOPMENT_CARD_COORDINATES =
      Array.of(
          Coordinates.faceDownOnEdges(1, 1, RIGHT, LEFT),
          Coordinates.faceDownOnEdges(15, 1, RIGHT, LEFT),
          Coordinates.faceDownOnEdges(1, 7, RIGHT, LEFT),
          Coordinates.faceDownOnEdges(15, 7, RIGHT, LEFT));
  private static final Array<Coordinate> P3_P4_VICTORY_POINT_COORDINATES =
      Array.of(
          Coordinates.onEdges(4, 0, BOTTOM_LEFT),
          Coordinates.onEdges(10, 0, BOTTOM_LEFT),
          Coordinates.onEdges(14, 0, BOTTOM_LEFT),
          Coordinates.onEdges(17, 3, LEFT),
          Coordinates.onEdges(17, 5, LEFT),
          Coordinates.onEdges(4, 8, TOP_LEFT),
          Coordinates.onEdges(8, 8, TOP_RIGHT),
          Coordinates.onEdges(14, 8, TOP_LEFT));
  private static final Map<String, Array<Coordinate>> P3_P4_COORDINATES =
      HashMap.of(
          "main-island-land",
          P3_P4_MAIN_ISLAND_LAND_COORDINATES,
          "small-island-land",
          P3_P4_SMALL_ISLAND_LAND_COORDINATES,
          "harbor",
          P3_P4_HARBOR_COORDINATES,
          "development-card",
          P3_P4_DEVELOPMENT_CARD_COORDINATES,
          "victory-point",
          P3_P4_VICTORY_POINT_COORDINATES);

  private static final Array<ChitOuterClass.Chit> P3_P4_MAIN_ISLAND_LAND_CHITS =
      Base.P3_P4_PRODUCING_CHITS;
  private static final Map<String, Array<ChitOuterClass.Chit>> P3_P4_CHITS =
      HashMap.of("main-island-land", P3_P4_MAIN_ISLAND_LAND_CHITS);

  // Eastern-most tiles on main island.
  @VisibleForTesting
  static final Array<Coordinate> P3_P4_BOUNDED_ODDS_COORDINATES =
      P3_P4_MAIN_ISLAND_LAND_COORDINATES
          .foldLeft(
              HashMap.empty(),
              ((HashMap<Integer, Integer> map, Coordinate coordinate) -> {
                final int y = coordinate.getY();

                return map.put(y, Math.max(map.get(y).getOrElse(0), coordinate.getX()));
              }))
          .toArray()
          .map(t2 -> Coordinates.of(t2._2, t2._1));

  private static final Matcher<ConfigurationOuterClass.Configuration> P3_P4_CONFIGURATION_MATCHER =
      IsNot.not(
          AllOf.allOf(
              CoordinateIsIn.coordinateIsIn(P3_P4_BOUNDED_ODDS_COORDINATES),
              HasOddsGreaterThan.hasOddsGreaterThan(3)));

  private static final SpecificationImpl.Builder P3_P4_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
              P3_P4_TILES,
              P3_P4_COORDINATES,
              P3_P4_CHITS,
              HashMap.ofEntries(
                  TileMappingUtils.newSelfReferringEntry("main-island-land"),
                  TileMappingUtils.newSelfReferringEntry("small-island-land"),
                  TileMappingUtils.newSelfReferringEntry("harbor"),
                  TileMappingUtils.newSelfReferringEntry("development-card"),
                  TileMappingUtils.newSelfReferringEntry("victory-point")),
              HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("main-island-land")))
          .withConfigurationMatcher(P3_P4_CONFIGURATION_MATCHER);

  public static final SpecificationImpl P3_P4_SPECIFICATION_IMPL =
      P3_P4_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P3_P4_FISHERMEN_SPECIFICATION_IMPL =
      P3_P4_SPECIFICATION_BUILDER.withFisheries(P3_P4_MAIN_ISLAND_FISHERY_COORDINATES).build();

  private static final Array<Tile> P5_P6_MAIN_ISLAND_LAND_TILES =
      Array.fill(5, MOUNTAIN)
          .appendAll(Array.fill(5, PASTURE))
          .appendAll(Array.fill(6, FIELD))
          .appendAll(Array.fill(6, HILL))
          .appendAll(Array.fill(7, FOREST));
  private static final Array<Tile> P5_P6_SMALL_ISLAND_LAND_TILES =
      Array.of(FIELD, HILL, MOUNTAIN)
          .appendAll(Array.fill(2, PASTURE))
          .appendAll(Array.fill(3, GOLD_FIELD))
          .appendAll(Array.fill(4, DESERT));
  private static final Array<Tile> P5_P6_HARBOR_TILES =
      P3_P4_HARBOR_TILES.appendAll(Array.fill(2, GENERIC_HARBOR));
  private static final Array<Tile> P5_P6_DEVELOPMENT_CARD_TILES = Array.fill(6, DEVELOPMENT_CARD);
  private static final Array<Tile> P5_P6_VICTORY_POINT_TILES = Array.fill(10, VICTORY_POINT);
  private static final Map<String, Array<Tile>> P5_P6_TILES =
      HashMap.of(
          "main-island-land",
          P5_P6_MAIN_ISLAND_LAND_TILES,
          "small-island-land",
          P5_P6_SMALL_ISLAND_LAND_TILES,
          "harbor",
          P5_P6_HARBOR_TILES,
          "development-card",
          P5_P6_DEVELOPMENT_CARD_TILES,
          "victory-point",
          P5_P6_VICTORY_POINT_TILES);

  private static final Array<Coordinate> P5_P6_MAIN_ISLAND_LAND_COORDINATES =
      Array.of(
          Coordinates.of(1, 3),
          Coordinates.of(3, 3),
          Coordinates.of(5, 3),
          Coordinates.of(7, 3),
          Coordinates.of(9, 3),
          Coordinates.of(11, 3),
          Coordinates.of(13, 3),
          Coordinates.of(15, 3),
          Coordinates.of(17, 3),
          Coordinates.of(19, 3),
          Coordinates.of(2, 4),
          Coordinates.of(4, 4),
          Coordinates.of(6, 4),
          Coordinates.of(8, 4),
          Coordinates.of(10, 4),
          Coordinates.of(12, 4),
          Coordinates.of(14, 4),
          Coordinates.of(16, 4),
          Coordinates.of(18, 4),
          Coordinates.of(1, 5),
          Coordinates.of(3, 5),
          Coordinates.of(5, 5),
          Coordinates.of(7, 5),
          Coordinates.of(9, 5),
          Coordinates.of(11, 5),
          Coordinates.of(13, 5),
          Coordinates.of(15, 5),
          Coordinates.of(17, 5),
          Coordinates.of(19, 5));
  private static final Array<Coordinate> P5_P6_MAIN_ISLAND_FISHERY_COORDINATES =
      Array.of(
          Coordinates.onEdges(19, 3, RIGHT, BOTTOM_RIGHT),
          Coordinates.onEdges(19, 5, TOP_RIGHT, RIGHT));
  private static final Array<Coordinate> P5_P6_SMALL_ISLAND_LAND_COORDINATES =
      Array.of(
          Coordinates.of(3, 1),
          Coordinates.of(5, 1),
          Coordinates.of(9, 1),
          Coordinates.of(11, 1),
          Coordinates.of(15, 1),
          Coordinates.of(17, 1),
          Coordinates.of(3, 7),
          Coordinates.of(5, 7),
          Coordinates.of(9, 7),
          Coordinates.of(11, 7),
          Coordinates.of(15, 7),
          Coordinates.of(17, 7));
  private static final Array<Coordinate> P5_P6_HARBOR_COORDINATES =
      Array.of(
          Coordinates.faceDownOnEdges(6, 0, BOTTOM_LEFT),
          Coordinates.faceDownOnEdges(8, 0, BOTTOM_RIGHT),
          Coordinates.faceDownOnEdges(14, 0, BOTTOM_RIGHT),
          Coordinates.faceDownOnEdges(18, 0, BOTTOM_LEFT),
          Coordinates.faceDownOnEdges(2, 8, TOP_RIGHT),
          Coordinates.faceDownOnEdges(4, 8, TOP_RIGHT),
          Coordinates.faceDownOnEdges(12, 8, TOP_LEFT),
          Coordinates.faceDownOnEdges(14, 8, TOP_RIGHT));
  private static final Array<Coordinate> P5_P6_DEVELOPMENT_CARD_COORDINATES =
      Array.of(
          Coordinates.faceDownOnEdges(12, 0, TOP_RIGHT, BOTTOM_LEFT),
          Coordinates.faceDownOnEdges(1, 1, RIGHT, LEFT),
          Coordinates.faceDownOnEdges(19, 1, RIGHT, LEFT),
          Coordinates.faceDownOnEdges(1, 7, RIGHT, LEFT),
          Coordinates.faceDownOnEdges(19, 7, RIGHT, LEFT),
          Coordinates.faceDownOnEdges(8, 8, TOP_RIGHT, BOTTOM_LEFT));
  private static final Array<Coordinate> P5_P6_VICTORY_POINT_COORDINATES =
      Array.of(
          Coordinates.onEdges(2, 0, BOTTOM_RIGHT),
          Coordinates.onEdges(4, 0, BOTTOM_RIGHT),
          Coordinates.onEdges(10, 0, BOTTOM_RIGHT),
          Coordinates.onEdges(10, 0, BOTTOM_LEFT),
          Coordinates.onEdges(16, 0, BOTTOM_LEFT),
          Coordinates.onEdges(6, 8, TOP_LEFT),
          Coordinates.onEdges(10, 8, TOP_RIGHT),
          Coordinates.onEdges(10, 8, TOP_LEFT),
          Coordinates.onEdges(16, 8, TOP_LEFT),
          Coordinates.onEdges(18, 8, TOP_LEFT));
  private static final Map<String, Array<Coordinate>> P5_P6_COORDINATES =
      HashMap.of(
          "main-island-land",
          P5_P6_MAIN_ISLAND_LAND_COORDINATES,
          "small-island-land",
          P5_P6_SMALL_ISLAND_LAND_COORDINATES,
          "harbor",
          P5_P6_HARBOR_COORDINATES,
          "development-card",
          P5_P6_DEVELOPMENT_CARD_COORDINATES,
          "victory-point",
          P5_P6_VICTORY_POINT_COORDINATES);

  private static final Array<ChitOuterClass.Chit> P5_P6_MAIN_ISLAND_LAND_CHITS =
      Array.of(CHIT_2, CHIT_12)
          .appendAll(Array.fill(3, CHIT_6))
          .appendAll(Array.fill(3, CHIT_8))
          .appendAll(Array.fill(3, CHIT_9))
          .appendAll(Array.fill(3, CHIT_10))
          .appendAll(Array.fill(3, CHIT_11))
          .appendAll(Array.fill(4, CHIT_3))
          .appendAll(Array.fill(4, CHIT_4))
          .appendAll(Array.fill(4, CHIT_5));
  private static final Map<String, Array<ChitOuterClass.Chit>> P5_P6_CHITS =
      HashMap.of("main-island-land", P5_P6_MAIN_ISLAND_LAND_CHITS);

  private static final SpecificationImpl.Builder P5_P6_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P5_P6_TILES,
          P5_P6_COORDINATES,
          P5_P6_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("main-island-land"),
              TileMappingUtils.newSelfReferringEntry("small-island-land"),
              TileMappingUtils.newSelfReferringEntry("harbor"),
              TileMappingUtils.newSelfReferringEntry("development-card"),
              TileMappingUtils.newSelfReferringEntry("victory-point")),
          HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("main-island-land")));

  public static final SpecificationImpl P5_P6_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P5_P6_FISHERMEN_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.withFisheries(P5_P6_MAIN_ISLAND_FISHERY_COORDINATES).build();

  private static final Array<Tile> P7_P8_MAIN_ISLAND_LAND_TILES =
      Array.fill(6, PASTURE)
          .appendAll(Array.fill(7, MOUNTAIN))
          .appendAll(Array.fill(8, FIELD))
          .appendAll(Array.fill(9, HILL))
          .appendAll(Array.fill(10, FOREST));
  private static final Array<Tile> P7_P8_SMALL_ISLAND_LAND_TILES = P5_P6_SMALL_ISLAND_LAND_TILES;
  private static final Array<Tile> P7_P8_HARBOR_TILES =
      P3_P4_HARBOR_TILES.appendAll(Array.fill(4, GENERIC_HARBOR));
  private static final Array<Tile> P7_P8_DEVELOPMENT_CARD_TILES = Array.fill(8, DEVELOPMENT_CARD);
  private static final Array<Tile> P7_P8_VICTORY_POINT_TILES = Array.fill(12, VICTORY_POINT);
  private static final Map<String, Array<Tile>> P7_P8_TILES =
      HashMap.of(
          "main-island-land",
          P7_P8_MAIN_ISLAND_LAND_TILES,
          "small-island-land",
          P7_P8_SMALL_ISLAND_LAND_TILES,
          "harbor",
          P7_P8_HARBOR_TILES,
          "development-card",
          P7_P8_DEVELOPMENT_CARD_TILES,
          "victory-point",
          P7_P8_VICTORY_POINT_TILES);

  private static final Array<Coordinate> P7_P8_MAIN_ISLAND_LAND_COORDINATES =
      Array.of(
          Coordinates.of(3, 3),
          Coordinates.of(5, 3),
          Coordinates.of(7, 3),
          Coordinates.of(9, 3),
          Coordinates.of(11, 3),
          Coordinates.of(13, 3),
          Coordinates.of(15, 3),
          Coordinates.of(17, 3),
          Coordinates.of(19, 3),
          Coordinates.of(21, 3),
          Coordinates.of(23, 3),
          Coordinates.of(25, 3),
          Coordinates.of(27, 3),
          Coordinates.of(2, 4),
          Coordinates.of(4, 4),
          Coordinates.of(6, 4),
          Coordinates.of(8, 4),
          Coordinates.of(10, 4),
          Coordinates.of(12, 4),
          Coordinates.of(14, 4),
          Coordinates.of(16, 4),
          Coordinates.of(18, 4),
          Coordinates.of(20, 4),
          Coordinates.of(22, 4),
          Coordinates.of(24, 4),
          Coordinates.of(26, 4),
          Coordinates.of(28, 4),
          Coordinates.of(3, 5),
          Coordinates.of(5, 5),
          Coordinates.of(7, 5),
          Coordinates.of(9, 5),
          Coordinates.of(11, 5),
          Coordinates.of(13, 5),
          Coordinates.of(15, 5),
          Coordinates.of(17, 5),
          Coordinates.of(19, 5),
          Coordinates.of(21, 5),
          Coordinates.of(23, 5),
          Coordinates.of(25, 5),
          Coordinates.of(27, 5));
  private static final Array<Coordinate> P7_P8_MAIN_ISLAND_FISHERY_COORDINATES =
      Array.of(
          Coordinates.onEdges(1, 3, RIGHT, BOTTOM_RIGHT),
          Coordinates.onEdges(29, 3, BOTTOM_LEFT, LEFT),
          Coordinates.onEdges(1, 5, TOP_RIGHT, RIGHT),
          Coordinates.onEdges(29, 5, LEFT, TOP_LEFT));
  private static final Array<Coordinate> P7_P8_SMALL_ISLAND_LAND_COORDINATES =
      Array.of(
          Coordinates.of(5, 1),
          Coordinates.of(9, 1),
          Coordinates.of(13, 1),
          Coordinates.of(17, 1),
          Coordinates.of(21, 1),
          Coordinates.of(25, 1),
          Coordinates.of(5, 7),
          Coordinates.of(9, 7),
          Coordinates.of(13, 7),
          Coordinates.of(17, 7),
          Coordinates.of(21, 7),
          Coordinates.of(25, 7));
  private static final Array<Coordinate> P7_P8_HARBOR_COORDINATES =
      Array.of(
          Coordinates.faceDownOnEdges(4, 0, BOTTOM_RIGHT),
          Coordinates.faceDownOnEdges(10, 0, BOTTOM_LEFT),
          Coordinates.faceDownOnEdges(12, 0, BOTTOM_RIGHT),
          Coordinates.faceDownOnEdges(20, 0, BOTTOM_RIGHT),
          Coordinates.faceDownOnEdges(26, 0, BOTTOM_LEFT),
          Coordinates.faceDownOnEdges(6, 8, TOP_LEFT),
          Coordinates.faceDownOnEdges(8, 8, TOP_RIGHT),
          Coordinates.faceDownOnEdges(16, 8, TOP_RIGHT),
          Coordinates.faceDownOnEdges(22, 8, TOP_LEFT),
          Coordinates.faceDownOnEdges(24, 8, TOP_RIGHT));
  private static final Array<Coordinate> P7_P8_DEVELOPMENT_CARD_COORDINATES =
      Array.of(
          Coordinates.faceDownOnEdges(3, 1, RIGHT, LEFT),
          Coordinates.faceDownOnEdges(11, 1, RIGHT, LEFT),
          Coordinates.faceDownOnEdges(19, 1, RIGHT, LEFT),
          Coordinates.faceDownOnEdges(27, 1, RIGHT, LEFT),
          Coordinates.faceDownOnEdges(3, 7, RIGHT, LEFT),
          Coordinates.faceDownOnEdges(11, 7, RIGHT, LEFT),
          Coordinates.faceDownOnEdges(19, 7, RIGHT, LEFT),
          Coordinates.faceDownOnEdges(27, 7, RIGHT, LEFT));
  private static final Array<Coordinate> P7_P8_VICTORY_POINT_COORDINATES =
      Array.of(
          Coordinates.onEdges(6, 0, BOTTOM_LEFT),
          Coordinates.onEdges(8, 0, BOTTOM_RIGHT),
          Coordinates.onEdges(14, 0, BOTTOM_LEFT),
          Coordinates.onEdges(16, 0, BOTTOM_RIGHT),
          Coordinates.onEdges(22, 0, BOTTOM_LEFT),
          Coordinates.onEdges(24, 0, BOTTOM_RIGHT),
          Coordinates.onEdges(4, 8, TOP_RIGHT),
          Coordinates.onEdges(10, 8, TOP_LEFT),
          Coordinates.onEdges(12, 8, TOP_RIGHT),
          Coordinates.onEdges(18, 8, TOP_LEFT),
          Coordinates.onEdges(20, 8, TOP_RIGHT),
          Coordinates.onEdges(26, 8, TOP_LEFT));
  private static final Map<String, Array<Coordinate>> P7_P8_COORDINATES =
      HashMap.of(
          "main-island-land",
          P7_P8_MAIN_ISLAND_LAND_COORDINATES,
          "small-island-land",
          P7_P8_SMALL_ISLAND_LAND_COORDINATES,
          "harbor",
          P7_P8_HARBOR_COORDINATES,
          "development-card",
          P7_P8_DEVELOPMENT_CARD_COORDINATES,
          "victory-point",
          P7_P8_VICTORY_POINT_COORDINATES);

  private static final Array<ChitOuterClass.Chit> P7_P8_MAIN_ISLAND_LAND_CHITS =
      Base.P7_P8_PRODUCING_CHITS.appendAll(Array.fill(2, CHIT_2)).appendAll(Array.fill(2, CHIT_12));
  private static final Map<String, Array<ChitOuterClass.Chit>> P7_P8_CHITS =
      HashMap.of("main-island-land", P7_P8_MAIN_ISLAND_LAND_CHITS);

  private static final SpecificationImpl.Builder P7_P8_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P7_P8_TILES,
          P7_P8_COORDINATES,
          P7_P8_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("main-island-land"),
              TileMappingUtils.newSelfReferringEntry("small-island-land"),
              TileMappingUtils.newSelfReferringEntry("harbor"),
              TileMappingUtils.newSelfReferringEntry("development-card"),
              TileMappingUtils.newSelfReferringEntry("victory-point")),
          HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("main-island-land")));

  public static final SpecificationImpl P7_P8_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P7_P8_FISHERMEN_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.withFisheries(P7_P8_MAIN_ISLAND_FISHERY_COORDINATES).build();
}
