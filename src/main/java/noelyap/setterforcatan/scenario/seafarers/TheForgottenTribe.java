package noelyap.setterforcatan.scenario.seafarers;

import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge.Position.BOTTOM_LEFT;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge.Position.BOTTOM_RIGHT;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge.Position.LEFT;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge.Position.RIGHT;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge.Position.TOP_LEFT;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge.Position.TOP_RIGHT;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.BRICK_HARBOR;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.DESERT;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.DEVELOPMENT_CARD;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.FIELD;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.FOREST;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.GENERIC_HARBOR;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.GOLD_FIELD;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.GRAIN_HARBOR;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.HILL;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.LUMBER_HARBOR;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.MOUNTAIN;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.ORE_HARBOR;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.PASTURE;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.VICTORY_POINT;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.WOOL_HARBOR;
import static noelyap.setterforcatan.util.ChitUtils.CHITS_10;
import static noelyap.setterforcatan.util.ChitUtils.CHITS_11;
import static noelyap.setterforcatan.util.ChitUtils.CHITS_12;
import static noelyap.setterforcatan.util.ChitUtils.CHITS_2;
import static noelyap.setterforcatan.util.ChitUtils.CHITS_3;
import static noelyap.setterforcatan.util.ChitUtils.CHITS_4;
import static noelyap.setterforcatan.util.ChitUtils.CHITS_5;
import static noelyap.setterforcatan.util.ChitUtils.CHITS_6;
import static noelyap.setterforcatan.util.ChitUtils.CHITS_8;
import static noelyap.setterforcatan.util.ChitUtils.CHITS_9;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.Array;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import noelyap.setterforcatan.component.SpecificationImpl;
import noelyap.setterforcatan.matcher.CoordinateIsIn;
import noelyap.setterforcatan.matcher.HasOddsGreaterThan;
import noelyap.setterforcatan.protogen.ChitOuterClass;
import noelyap.setterforcatan.protogen.ConfigurationOuterClass;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Coordinate;
import noelyap.setterforcatan.protogen.TileOuterClass.Tile;
import noelyap.setterforcatan.scenario.Base;
import noelyap.setterforcatan.util.CoordinateUtils;
import noelyap.setterforcatan.util.TileMappingUtils;
import noelyap.setterforcatan.util.TileUtils;
import org.hamcrest.Matcher;
import org.hamcrest.core.AllOf;
import org.hamcrest.core.IsNot;

public class TheForgottenTribe {
  private static final Array<Coordinate> P3_P4_BOUNDED_ODDS_TILES =
      Array.of(
          CoordinateUtils.newCoordinate(11, 3),
          CoordinateUtils.newCoordinate(12, 4),
          CoordinateUtils.newCoordinate(11, 5));
  private static final Matcher<ConfigurationOuterClass.Configuration> P3_P4_CONFIGURATION_MATCHER =
      IsNot.not(
          AllOf.allOf(
              CoordinateIsIn.coordinateIsIn(P3_P4_BOUNDED_ODDS_TILES),
              HasOddsGreaterThan.hasOddsGreaterThan(3)));

  private static final Tuple2<Array<Tile>, Boolean> P3_P4_MAIN_ISLAND_LAND_TILES =
      Base.P3_P4_PRODUCING_TILES;
  private static final Tuple2<Array<Tile>, Boolean> P3_P4_SMALL_ISLAND_LAND_TILES =
      Tuple.of(
          TileUtils.newTiles(FIELD, FOREST, PASTURE)
              .appendAll(TileUtils.newTiles(2, GOLD_FIELD))
              .appendAll(TileUtils.newTiles(2, HILL))
              .appendAll(TileUtils.newTiles(2, MOUNTAIN))
              .appendAll(TileUtils.newTiles(3, DESERT)),
          true);
  private static final Tuple2<Array<Tile>, Boolean> P3_P4_SMALL_ISLAND_HARBOR_TILES =
      Tuple.of(
          TileUtils.newTiles(
              BRICK_HARBOR, GENERIC_HARBOR, GRAIN_HARBOR, LUMBER_HARBOR, ORE_HARBOR, WOOL_HARBOR),
          true);
  private static final Tuple2<Array<Tile>, Boolean> P3_P4_SMALL_ISLAND_DEVELOPMENT_CARD_TILES =
      Tuple.of(TileUtils.newTiles(4, DEVELOPMENT_CARD), true);
  private static final Tuple2<Array<Tile>, Boolean> P3_P4_SMALL_ISLAND_VICTORY_POINT_TILES =
      Tuple.of(TileUtils.newTiles(8, VICTORY_POINT), true);
  private static final Map<String, Tuple2<Array<Tile>, Boolean>> P3_P4_TILES =
      HashMap.of(
          "main-island-land",
          P3_P4_MAIN_ISLAND_LAND_TILES,
          "small-island-land",
          P3_P4_SMALL_ISLAND_LAND_TILES,
          "small-island-harbor",
          P3_P4_SMALL_ISLAND_HARBOR_TILES,
          "small-island-development-card",
          P3_P4_SMALL_ISLAND_DEVELOPMENT_CARD_TILES,
          "small-island-victory-point",
          P3_P4_SMALL_ISLAND_VICTORY_POINT_TILES);

  private static final Array<Coordinate> P3_P4_MAIN_ISLAND_LAND_COORDINATES =
      P3_P4_BOUNDED_ODDS_TILES.appendAll(
          Array.of(
              CoordinateUtils.newCoordinate(1, 3),
              CoordinateUtils.newCoordinate(3, 3),
              CoordinateUtils.newCoordinate(5, 3),
              CoordinateUtils.newCoordinate(7, 3),
              CoordinateUtils.newCoordinate(9, 3),
              CoordinateUtils.newCoordinate(2, 4),
              CoordinateUtils.newCoordinate(4, 4),
              CoordinateUtils.newCoordinate(6, 4),
              CoordinateUtils.newCoordinate(8, 4),
              CoordinateUtils.newCoordinate(10, 4),
              CoordinateUtils.newCoordinate(1, 5),
              CoordinateUtils.newCoordinate(3, 5),
              CoordinateUtils.newCoordinate(5, 5),
              CoordinateUtils.newCoordinate(7, 5),
              CoordinateUtils.newCoordinate(9, 5)));
  private static final Array<Coordinate> P3_P4_MAIN_ISLAND_FISHERY_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(1, 3, BOTTOM_LEFT, LEFT),
          CoordinateUtils.newCoordinate(1, 5, LEFT, TOP_LEFT));
  private static final Array<Coordinate> P3_P4_SMALL_ISLAND_LAND_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(3, 1),
          CoordinateUtils.newCoordinate(5, 1),
          CoordinateUtils.newCoordinate(9, 1),
          CoordinateUtils.newCoordinate(11, 1),
          CoordinateUtils.newCoordinate(13, 1),
          CoordinateUtils.newCoordinate(15, 3),
          CoordinateUtils.newCoordinate(15, 5),
          CoordinateUtils.newCoordinate(3, 7),
          CoordinateUtils.newCoordinate(5, 7),
          CoordinateUtils.newCoordinate(9, 7),
          CoordinateUtils.newCoordinate(11, 7),
          CoordinateUtils.newCoordinate(13, 7));
  private static final Array<Coordinate> P3_P4_SMALL_ISLAND_HARBOR_COORDINATES =
      Array.of(
          CoordinateUtils.newFaceDownCoordinate(2, 0, BOTTOM_RIGHT),
          CoordinateUtils.newFaceDownCoordinate(8, 0, BOTTOM_RIGHT),
          CoordinateUtils.newFaceDownCoordinate(16, 2, BOTTOM_LEFT),
          CoordinateUtils.newFaceDownCoordinate(2, 8, TOP_RIGHT),
          CoordinateUtils.newFaceDownCoordinate(10, 8, TOP_LEFT),
          CoordinateUtils.newFaceDownCoordinate(16, 6, TOP_LEFT));
  private static final Array<Coordinate> P3_P4_SMALL_ISLAND_DEVELOPMENT_CARD_COORDINATES =
      Array.of(
          CoordinateUtils.newFaceDownCoordinate(1, 1, RIGHT, LEFT),
          CoordinateUtils.newFaceDownCoordinate(15, 1, RIGHT, LEFT),
          CoordinateUtils.newFaceDownCoordinate(1, 7, RIGHT, LEFT),
          CoordinateUtils.newFaceDownCoordinate(15, 7, RIGHT, LEFT));
  private static final Array<Coordinate> P3_P4_SMALL_ISLAND_VICTORY_POINT_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(4, 0, BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(10, 0, BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(14, 0, BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(17, 3, LEFT),
          CoordinateUtils.newCoordinate(17, 5, LEFT),
          CoordinateUtils.newCoordinate(4, 8, TOP_LEFT),
          CoordinateUtils.newCoordinate(8, 8, TOP_RIGHT),
          CoordinateUtils.newCoordinate(14, 8, TOP_LEFT));
  private static final Map<String, Array<Coordinate>> P3_P4_COORDINATES =
      HashMap.of(
          "main-island-land",
          P3_P4_MAIN_ISLAND_LAND_COORDINATES,
          "small-island-land",
          P3_P4_SMALL_ISLAND_LAND_COORDINATES,
          "small-island-harbor",
          P3_P4_SMALL_ISLAND_HARBOR_COORDINATES,
          "small-island-development-card",
          P3_P4_SMALL_ISLAND_DEVELOPMENT_CARD_COORDINATES,
          "small-island-victory-point",
          P3_P4_SMALL_ISLAND_VICTORY_POINT_COORDINATES);

  private static final Array<ChitOuterClass.Chit> P3_P4_MAIN_ISLAND_LAND_CHITS =
      Base.P3_P4_PRODUCING_CHITS;
  private static final Map<String, Array<ChitOuterClass.Chit>> P3_P4_CHITS =
      HashMap.of("main-island-land", P3_P4_MAIN_ISLAND_LAND_CHITS);

  private static final SpecificationImpl.Builder P3_P4_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
              P3_P4_TILES,
              P3_P4_COORDINATES,
              P3_P4_CHITS,
              HashMap.ofEntries(
                  TileMappingUtils.newSelfReferringEntry("main-island-land"),
                  TileMappingUtils.newSelfReferringEntry("small-island-land"),
                  TileMappingUtils.newSelfReferringEntry("small-island-harbor"),
                  TileMappingUtils.newSelfReferringEntry("small-island-development-card"),
                  TileMappingUtils.newSelfReferringEntry("small-island-victory-point")),
              HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("main-island-land")))
          .withConfigurationMatcher(P3_P4_CONFIGURATION_MATCHER);

  public static final SpecificationImpl P3_P4_SPECIFICATION_IMPL =
      P3_P4_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P3_P4_FISHERMEN_SPECIFICATION_IMPL =
      P3_P4_SPECIFICATION_BUILDER.withFisheries(P3_P4_MAIN_ISLAND_FISHERY_COORDINATES).build();

  private static final Tuple2<Array<Tile>, Boolean> P5_P6_MAIN_ISLAND_LAND_TILES =
      Tuple.of(
          TileUtils.newTiles(5, MOUNTAIN)
              .appendAll(TileUtils.newTiles(5, PASTURE))
              .appendAll(TileUtils.newTiles(6, FIELD))
              .appendAll(TileUtils.newTiles(6, HILL))
              .appendAll(TileUtils.newTiles(7, FOREST)),
          false);
  private static final Tuple2<Array<Tile>, Boolean> P5_P6_SMALL_ISLAND_LAND_TILES =
      Tuple.of(
          TileUtils.newTiles(FIELD, HILL, MOUNTAIN)
              .appendAll(TileUtils.newTiles(2, PASTURE))
              .appendAll(TileUtils.newTiles(3, GOLD_FIELD))
              .appendAll(TileUtils.newTiles(4, DESERT)),
          true);
  private static final Tuple2<Array<Tile>, Boolean> P5_P6_SMALL_ISLAND_HARBOR_TILES =
      Tuple.of(
          P3_P4_SMALL_ISLAND_HARBOR_TILES._1.appendAll(TileUtils.newTiles(2, GENERIC_HARBOR)),
          true);
  private static final Tuple2<Array<Tile>, Boolean> P5_P6_SMALL_ISLAND_DEVELOPMENT_CARD_TILES =
      Tuple.of(TileUtils.newTiles(6, DEVELOPMENT_CARD), true);
  private static final Tuple2<Array<Tile>, Boolean> P5_P6_SMALL_ISLAND_VICTORY_POINT_TILES =
      Tuple.of(TileUtils.newTiles(10, VICTORY_POINT), true);
  private static final Map<String, Tuple2<Array<Tile>, Boolean>> P5_P6_TILES =
      HashMap.of(
          "main-island-land",
          P5_P6_MAIN_ISLAND_LAND_TILES,
          "small-island-land",
          P5_P6_SMALL_ISLAND_LAND_TILES,
          "small-island-harbor",
          P5_P6_SMALL_ISLAND_HARBOR_TILES,
          "small-island-development-card",
          P5_P6_SMALL_ISLAND_DEVELOPMENT_CARD_TILES,
          "small-island-victory-point",
          P5_P6_SMALL_ISLAND_VICTORY_POINT_TILES);

  private static final Array<Coordinate> P5_P6_MAIN_ISLAND_LAND_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(1, 3),
          CoordinateUtils.newCoordinate(3, 3),
          CoordinateUtils.newCoordinate(5, 3),
          CoordinateUtils.newCoordinate(7, 3),
          CoordinateUtils.newCoordinate(9, 3),
          CoordinateUtils.newCoordinate(11, 3),
          CoordinateUtils.newCoordinate(13, 3),
          CoordinateUtils.newCoordinate(15, 3),
          CoordinateUtils.newCoordinate(17, 3),
          CoordinateUtils.newCoordinate(19, 3),
          CoordinateUtils.newCoordinate(2, 4),
          CoordinateUtils.newCoordinate(4, 4),
          CoordinateUtils.newCoordinate(6, 4),
          CoordinateUtils.newCoordinate(8, 4),
          CoordinateUtils.newCoordinate(10, 4),
          CoordinateUtils.newCoordinate(12, 4),
          CoordinateUtils.newCoordinate(14, 4),
          CoordinateUtils.newCoordinate(16, 4),
          CoordinateUtils.newCoordinate(18, 4),
          CoordinateUtils.newCoordinate(1, 5),
          CoordinateUtils.newCoordinate(3, 5),
          CoordinateUtils.newCoordinate(5, 5),
          CoordinateUtils.newCoordinate(7, 5),
          CoordinateUtils.newCoordinate(9, 5),
          CoordinateUtils.newCoordinate(11, 5),
          CoordinateUtils.newCoordinate(13, 5),
          CoordinateUtils.newCoordinate(15, 5),
          CoordinateUtils.newCoordinate(17, 5),
          CoordinateUtils.newCoordinate(19, 5));
  private static final Array<Coordinate> P5_P6_MAIN_ISLAND_FISHERY_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(19, 3, RIGHT, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(19, 5, TOP_RIGHT, RIGHT));
  private static final Array<Coordinate> P5_P6_SMALL_ISLAND_LAND_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(3, 1),
          CoordinateUtils.newCoordinate(5, 1),
          CoordinateUtils.newCoordinate(9, 1),
          CoordinateUtils.newCoordinate(11, 1),
          CoordinateUtils.newCoordinate(15, 1),
          CoordinateUtils.newCoordinate(17, 1),
          CoordinateUtils.newCoordinate(3, 7),
          CoordinateUtils.newCoordinate(5, 7),
          CoordinateUtils.newCoordinate(9, 7),
          CoordinateUtils.newCoordinate(11, 7),
          CoordinateUtils.newCoordinate(15, 7),
          CoordinateUtils.newCoordinate(17, 7));
  private static final Array<Coordinate> P5_P6_SMALL_ISLAND_HARBOR_COORDINATES =
      Array.of(
          CoordinateUtils.newFaceDownCoordinate(6, 0, BOTTOM_LEFT),
          CoordinateUtils.newFaceDownCoordinate(8, 0, BOTTOM_RIGHT),
          CoordinateUtils.newFaceDownCoordinate(14, 0, BOTTOM_RIGHT),
          CoordinateUtils.newFaceDownCoordinate(18, 0, BOTTOM_LEFT),
          CoordinateUtils.newFaceDownCoordinate(2, 8, TOP_RIGHT),
          CoordinateUtils.newFaceDownCoordinate(4, 8, TOP_RIGHT),
          CoordinateUtils.newFaceDownCoordinate(12, 8, TOP_LEFT),
          CoordinateUtils.newFaceDownCoordinate(14, 8, TOP_RIGHT));
  private static final Array<Coordinate> P5_P6_SMALL_ISLAND_DEVELOPMENT_CARD_COORDINATES =
      Array.of(
          CoordinateUtils.newFaceDownCoordinate(12, 0, TOP_RIGHT, BOTTOM_LEFT),
          CoordinateUtils.newFaceDownCoordinate(1, 1, RIGHT, LEFT),
          CoordinateUtils.newFaceDownCoordinate(19, 1, RIGHT, LEFT),
          CoordinateUtils.newFaceDownCoordinate(1, 7, RIGHT, LEFT),
          CoordinateUtils.newFaceDownCoordinate(19, 7, RIGHT, LEFT),
          CoordinateUtils.newFaceDownCoordinate(8, 8, TOP_RIGHT, BOTTOM_LEFT));
  private static final Array<Coordinate> P5_P6_SMALL_ISLAND_VICTORY_POINT_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(2, 0, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(4, 0, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(10, 0, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(10, 0, BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(16, 0, BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(6, 8, TOP_LEFT),
          CoordinateUtils.newCoordinate(10, 8, TOP_RIGHT),
          CoordinateUtils.newCoordinate(10, 8, TOP_LEFT),
          CoordinateUtils.newCoordinate(16, 8, TOP_LEFT),
          CoordinateUtils.newCoordinate(18, 8, TOP_LEFT));
  private static final Map<String, Array<Coordinate>> P5_P6_COORDINATES =
      HashMap.of(
          "main-island-land",
          P5_P6_MAIN_ISLAND_LAND_COORDINATES,
          "small-island-land",
          P5_P6_SMALL_ISLAND_LAND_COORDINATES,
          "small-island-harbor",
          P5_P6_SMALL_ISLAND_HARBOR_COORDINATES,
          "small-island-development-card",
          P5_P6_SMALL_ISLAND_DEVELOPMENT_CARD_COORDINATES,
          "small-island-victory-point",
          P5_P6_SMALL_ISLAND_VICTORY_POINT_COORDINATES);

  private static final Array<ChitOuterClass.Chit> P5_P6_MAIN_ISLAND_LAND_CHITS =
      Array.of(CHITS_2, CHITS_12)
          .appendAll(Array.fill(3, CHITS_6))
          .appendAll(Array.fill(3, CHITS_8))
          .appendAll(Array.fill(3, CHITS_9))
          .appendAll(Array.fill(3, CHITS_10))
          .appendAll(Array.fill(3, CHITS_11))
          .appendAll(Array.fill(4, CHITS_3))
          .appendAll(Array.fill(4, CHITS_4))
          .appendAll(Array.fill(4, CHITS_5));
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
              TileMappingUtils.newSelfReferringEntry("small-island-harbor"),
              TileMappingUtils.newSelfReferringEntry("small-island-development-card"),
              TileMappingUtils.newSelfReferringEntry("small-island-victory-point")),
          HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("main-island-land")));

  public static final SpecificationImpl P5_P6_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P5_P6_FISHERMEN_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.withFisheries(P5_P6_MAIN_ISLAND_FISHERY_COORDINATES).build();

  private static final Tuple2<Array<Tile>, Boolean> P7_P8_MAIN_ISLAND_LAND_TILES =
      Tuple.of(
          TileUtils.newTiles(6, PASTURE)
              .appendAll(TileUtils.newTiles(7, MOUNTAIN))
              .appendAll(TileUtils.newTiles(8, FIELD))
              .appendAll(TileUtils.newTiles(9, HILL))
              .appendAll(TileUtils.newTiles(10, FOREST)),
          false);
  private static final Tuple2<Array<Tile>, Boolean> P7_P8_SMALL_ISLAND_LAND_TILES =
      P5_P6_SMALL_ISLAND_LAND_TILES;
  private static final Tuple2<Array<Tile>, Boolean> P7_P8_SMALL_ISLAND_HARBOR_TILES =
      Tuple.of(
          P3_P4_SMALL_ISLAND_HARBOR_TILES._1.appendAll(TileUtils.newTiles(4, GENERIC_HARBOR)),
          true);
  private static final Tuple2<Array<Tile>, Boolean> P7_P8_SMALL_ISLAND_DEVELOPMENT_CARD_TILES =
      Tuple.of(TileUtils.newTiles(8, DEVELOPMENT_CARD), true);
  private static final Tuple2<Array<Tile>, Boolean> P7_P8_SMALL_ISLAND_VICTORY_POINT_TILES =
      Tuple.of(TileUtils.newTiles(12, VICTORY_POINT), true);
  private static final Map<String, Tuple2<Array<Tile>, Boolean>> P7_P8_TILES =
      HashMap.of(
          "main-island-land",
          P7_P8_MAIN_ISLAND_LAND_TILES,
          "small-island-land",
          P7_P8_SMALL_ISLAND_LAND_TILES,
          "small-island-harbor",
          P7_P8_SMALL_ISLAND_HARBOR_TILES,
          "small-island-development-card",
          P7_P8_SMALL_ISLAND_DEVELOPMENT_CARD_TILES,
          "small-island-victory-point",
          P7_P8_SMALL_ISLAND_VICTORY_POINT_TILES);

  private static final Array<Coordinate> P7_P8_MAIN_ISLAND_LAND_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(3, 3),
          CoordinateUtils.newCoordinate(5, 3),
          CoordinateUtils.newCoordinate(7, 3),
          CoordinateUtils.newCoordinate(9, 3),
          CoordinateUtils.newCoordinate(11, 3),
          CoordinateUtils.newCoordinate(13, 3),
          CoordinateUtils.newCoordinate(15, 3),
          CoordinateUtils.newCoordinate(17, 3),
          CoordinateUtils.newCoordinate(19, 3),
          CoordinateUtils.newCoordinate(21, 3),
          CoordinateUtils.newCoordinate(23, 3),
          CoordinateUtils.newCoordinate(25, 3),
          CoordinateUtils.newCoordinate(27, 3),
          CoordinateUtils.newCoordinate(2, 4),
          CoordinateUtils.newCoordinate(4, 4),
          CoordinateUtils.newCoordinate(6, 4),
          CoordinateUtils.newCoordinate(8, 4),
          CoordinateUtils.newCoordinate(10, 4),
          CoordinateUtils.newCoordinate(12, 4),
          CoordinateUtils.newCoordinate(14, 4),
          CoordinateUtils.newCoordinate(16, 4),
          CoordinateUtils.newCoordinate(18, 4),
          CoordinateUtils.newCoordinate(20, 4),
          CoordinateUtils.newCoordinate(22, 4),
          CoordinateUtils.newCoordinate(24, 4),
          CoordinateUtils.newCoordinate(26, 4),
          CoordinateUtils.newCoordinate(28, 4),
          CoordinateUtils.newCoordinate(3, 5),
          CoordinateUtils.newCoordinate(5, 5),
          CoordinateUtils.newCoordinate(7, 5),
          CoordinateUtils.newCoordinate(9, 5),
          CoordinateUtils.newCoordinate(11, 5),
          CoordinateUtils.newCoordinate(13, 5),
          CoordinateUtils.newCoordinate(15, 5),
          CoordinateUtils.newCoordinate(17, 5),
          CoordinateUtils.newCoordinate(19, 5),
          CoordinateUtils.newCoordinate(21, 5),
          CoordinateUtils.newCoordinate(23, 5),
          CoordinateUtils.newCoordinate(25, 5),
          CoordinateUtils.newCoordinate(27, 5));
  private static final Array<Coordinate> P7_P8_MAIN_ISLAND_FISHERY_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(1, 3, RIGHT, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(29, 3, BOTTOM_LEFT, LEFT),
          CoordinateUtils.newCoordinate(1, 5, TOP_RIGHT, RIGHT),
          CoordinateUtils.newCoordinate(29, 5, LEFT, TOP_LEFT));
  private static final Array<Coordinate> P7_P8_SMALL_ISLAND_LAND_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(5, 1),
          CoordinateUtils.newCoordinate(9, 1),
          CoordinateUtils.newCoordinate(13, 1),
          CoordinateUtils.newCoordinate(17, 1),
          CoordinateUtils.newCoordinate(21, 1),
          CoordinateUtils.newCoordinate(25, 1),
          CoordinateUtils.newCoordinate(5, 7),
          CoordinateUtils.newCoordinate(9, 7),
          CoordinateUtils.newCoordinate(13, 7),
          CoordinateUtils.newCoordinate(17, 7),
          CoordinateUtils.newCoordinate(21, 7),
          CoordinateUtils.newCoordinate(25, 7));
  private static final Array<Coordinate> P7_P8_SMALL_ISLAND_HARBOR_COORDINATES =
      Array.of(
          CoordinateUtils.newFaceDownCoordinate(4, 0, BOTTOM_RIGHT),
          CoordinateUtils.newFaceDownCoordinate(10, 0, BOTTOM_LEFT),
          CoordinateUtils.newFaceDownCoordinate(12, 0, BOTTOM_RIGHT),
          CoordinateUtils.newFaceDownCoordinate(20, 0, BOTTOM_RIGHT),
          CoordinateUtils.newFaceDownCoordinate(26, 0, BOTTOM_LEFT),
          CoordinateUtils.newFaceDownCoordinate(6, 8, TOP_LEFT),
          CoordinateUtils.newFaceDownCoordinate(8, 8, TOP_RIGHT),
          CoordinateUtils.newFaceDownCoordinate(16, 8, TOP_RIGHT),
          CoordinateUtils.newFaceDownCoordinate(22, 8, TOP_LEFT),
          CoordinateUtils.newFaceDownCoordinate(24, 8, TOP_RIGHT));
  private static final Array<Coordinate> P7_P8_SMALL_ISLAND_DEVELOPMENT_CARD_COORDINATES =
      Array.of(
          CoordinateUtils.newFaceDownCoordinate(3, 1, RIGHT, LEFT),
          CoordinateUtils.newFaceDownCoordinate(11, 1, RIGHT, LEFT),
          CoordinateUtils.newFaceDownCoordinate(19, 1, RIGHT, LEFT),
          CoordinateUtils.newFaceDownCoordinate(27, 1, RIGHT, LEFT),
          CoordinateUtils.newFaceDownCoordinate(3, 7, RIGHT, LEFT),
          CoordinateUtils.newFaceDownCoordinate(11, 7, RIGHT, LEFT),
          CoordinateUtils.newFaceDownCoordinate(19, 7, RIGHT, LEFT),
          CoordinateUtils.newFaceDownCoordinate(27, 7, RIGHT, LEFT));
  private static final Array<Coordinate> P7_P8_SMALL_ISLAND_VICTORY_POINT_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(6, 0, BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(8, 0, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(14, 0, BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(16, 0, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(22, 0, BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(24, 0, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(4, 8, TOP_RIGHT),
          CoordinateUtils.newCoordinate(10, 8, TOP_LEFT),
          CoordinateUtils.newCoordinate(12, 8, TOP_RIGHT),
          CoordinateUtils.newCoordinate(18, 8, TOP_LEFT),
          CoordinateUtils.newCoordinate(20, 8, TOP_RIGHT),
          CoordinateUtils.newCoordinate(26, 8, TOP_LEFT));
  private static final Map<String, Array<Coordinate>> P7_P8_COORDINATES =
      HashMap.of(
          "main-island-land",
          P7_P8_MAIN_ISLAND_LAND_COORDINATES,
          "small-island-land",
          P7_P8_SMALL_ISLAND_LAND_COORDINATES,
          "small-island-harbor",
          P7_P8_SMALL_ISLAND_HARBOR_COORDINATES,
          "small-island-development-card",
          P7_P8_SMALL_ISLAND_DEVELOPMENT_CARD_COORDINATES,
          "small-island-victory-point",
          P7_P8_SMALL_ISLAND_VICTORY_POINT_COORDINATES);

  private static final Array<ChitOuterClass.Chit> P7_P8_MAIN_ISLAND_LAND_CHITS =
      Base.P7_P8_PRODUCING_CHITS
          .appendAll(Array.fill(2, CHITS_2))
          .appendAll(Array.fill(2, CHITS_12));
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
              TileMappingUtils.newSelfReferringEntry("small-island-harbor"),
              TileMappingUtils.newSelfReferringEntry("small-island-development-card"),
              TileMappingUtils.newSelfReferringEntry("small-island-victory-point")),
          HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("main-island-land")));

  public static final SpecificationImpl P7_P8_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P7_P8_FISHERMEN_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.withFisheries(P7_P8_MAIN_ISLAND_FISHERY_COORDINATES).build();
}
