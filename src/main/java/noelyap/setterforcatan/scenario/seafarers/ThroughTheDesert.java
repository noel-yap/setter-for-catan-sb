package noelyap.setterforcatan.scenario.seafarers;

import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge.Position.BOTTOM_LEFT;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge.Position.BOTTOM_RIGHT;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge.Position.LEFT;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge.Position.RIGHT;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge.Position.TOP_LEFT;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge.Position.TOP_RIGHT;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.DESERT;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.FIELD;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.FOREST;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.GENERIC_HARBOR;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.GOLD_FIELD;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.HILL;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.MOUNTAIN;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.PASTURE;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.SEA;
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
import noelyap.setterforcatan.matcher.HasOddsGreaterThan;
import noelyap.setterforcatan.matcher.IsTileType;
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

public class ThroughTheDesert {
  private static final Matcher<ConfigurationOuterClass.Configuration> CONFIGURATION_MATCHER =
      IsNot.not(
          AllOf.allOf(IsTileType.isTileType(GOLD_FIELD), HasOddsGreaterThan.hasOddsGreaterThan(4)));

  private static final Tuple2<Array<Tile>, Boolean> P3_INDIGENOUS_LAND_TILES =
      Tuple.of(
          Array.fill(2, TileUtils.newTile(FIELD))
              .appendAll(Array.fill(2, TileUtils.newTile(MOUNTAIN)))
              .appendAll(Array.fill(3, TileUtils.newTile(HILL)))
              .appendAll(Array.fill(3, TileUtils.newTile(PASTURE)))
              .appendAll(Array.fill(4, TileUtils.newTile(FOREST))),
          false);
  private static final Tuple2<Array<Tile>, Boolean> P3_INDIGENOUS_HARBOR_TILES =
      Tuple.of(
          TileUtils.newTiles(3, GENERIC_HARBOR).appendAll(TileUtils.TWO_FOR_ONE_HARBORS), true);
  private static final Tuple2<Array<Tile>, Boolean> P3_DESERT_TILES =
      Tuple.of(Array.fill(3, TileUtils.newTile(DESERT)), true);
  private static final Tuple2<Array<Tile>, Boolean> P3_FOREIGN_LAND_TILES =
      Tuple.of(
          TileUtils.newTiles(FOREST, PASTURE)
              .appendAll(Array.fill(2, TileUtils.newTile(FIELD)))
              .appendAll(Array.fill(2, TileUtils.newTile(GOLD_FIELD)))
              .appendAll(Array.fill(2, TileUtils.newTile(MOUNTAIN))),
          false);
  private static final Tuple2<Array<Tile>, Boolean> P3_FOREIGN_SEA_TILES =
      Tuple.of(Array.fill(2, TileUtils.newTile(SEA)), true);
  private static final Map<String, Tuple2<Array<Tile>, Boolean>> P3_TILES =
      HashMap.of(
          "indigenous-land",
          P3_INDIGENOUS_LAND_TILES,
          "indigenous-harbor",
          P3_INDIGENOUS_HARBOR_TILES,
          "desert",
          P3_DESERT_TILES,
          "foreign-land",
          P3_FOREIGN_LAND_TILES,
          "foreign-sea",
          P3_FOREIGN_SEA_TILES);

  private static final Array<Coordinate> P3_INDIGENOUS_LAND_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(2, 2),
          CoordinateUtils.newCoordinate(4, 2),
          CoordinateUtils.newCoordinate(6, 2),
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
          CoordinateUtils.newCoordinate(3, 5));
  private static final Array<Coordinate> P3_INDIGENOUS_HARBOR_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(3, 1, BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(5, 1, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(0, 2, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(8, 2, LEFT),
          CoordinateUtils.newCoordinate(0, 4, TOP_RIGHT),
          CoordinateUtils.newCoordinate(12, 4, LEFT),
          CoordinateUtils.newCoordinate(1, 5, RIGHT),
          CoordinateUtils.newCoordinate(5, 5, TOP_LEFT));
  private static final Array<Coordinate> P3_INDIGENOUS_FISHERY_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(2, 2, LEFT, TOP_LEFT),
          CoordinateUtils.newCoordinate(2, 4, BOTTOM_LEFT, LEFT));
  private static final Array<Coordinate> P3_DESERT_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(7, 5),
          CoordinateUtils.newCoordinate(9, 5),
          CoordinateUtils.newCoordinate(11, 5));
  private static final Array<Coordinate> P3_FOREIGN_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(4, 0),
          CoordinateUtils.newCoordinate(6, 0),
          CoordinateUtils.newCoordinate(8, 0),
          CoordinateUtils.newCoordinate(9, 1),
          CoordinateUtils.newCoordinate(11, 1),
          CoordinateUtils.newCoordinate(12, 2),
          CoordinateUtils.newCoordinate(13, 3),
          CoordinateUtils.newCoordinate(6, 6),
          CoordinateUtils.newCoordinate(8, 6),
          CoordinateUtils.newCoordinate(10, 6));
  private static final Map<String, Array<Coordinate>> P3_COORDINATES =
      HashMap.of(
          "indigenous-land",
          P3_INDIGENOUS_LAND_COORDINATES,
          "indigenous-harbor",
          P3_INDIGENOUS_HARBOR_COORDINATES,
          "desert",
          P3_DESERT_COORDINATES,
          "foreign",
          P3_FOREIGN_COORDINATES);

  private static final Array<ChitOuterClass.Chit> P3_INDIGENOUS_LAND_CHITS =
      Array.of(CHITS_2, CHITS_3, CHITS_5, CHITS_11)
          .appendAll(Array.fill(2, CHITS_4))
          .appendAll(Array.fill(2, CHITS_6))
          .appendAll(Array.fill(2, CHITS_8))
          .appendAll(Array.fill(2, CHITS_9))
          .appendAll(Array.fill(2, CHITS_10));
  private static final Array<ChitOuterClass.Chit> P3_FOREIGN_LAND_CHITS =
      Array.of(CHITS_3, CHITS_4, CHITS_6, CHITS_8, CHITS_9, CHITS_11)
          .appendAll(Array.fill(2, CHITS_5));
  private static final Map<String, Array<ChitOuterClass.Chit>> P3_CHITS =
      HashMap.of(
          "indigenous-land", P3_INDIGENOUS_LAND_CHITS,
          "foreign-land", P3_FOREIGN_LAND_CHITS);

  private static final SpecificationImpl.Builder P3_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
              P3_TILES,
              P3_COORDINATES,
              P3_CHITS,
              HashMap.ofEntries(
                  TileMappingUtils.newSelfReferringEntry("indigenous-land"),
                  TileMappingUtils.newSelfReferringEntry("indigenous-harbor"),
                  TileMappingUtils.newSelfReferringEntry("desert"),
                  TileMappingUtils.newEntry("foreign", "foreign-land", "foreign-sea")),
              HashMap.ofEntries(
                  TileMappingUtils.newSelfReferringEntry("indigenous-land"),
                  TileMappingUtils.newSelfReferringEntry("foreign-land")))
          .withConfigurationMatcher(CONFIGURATION_MATCHER);

  public static final SpecificationImpl P3_SPECIFICATION_IMPL = P3_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P3_FISHERMEN_SPECIFICATION_IMPL =
      P3_SPECIFICATION_BUILDER.withFisheries(P3_INDIGENOUS_FISHERY_COORDINATES).build();

  private static final Tuple2<Array<Tile>, Boolean> P4_INDIGENOUS_LAND_TILES =
      Tuple.of(
          Array.fill(2, TileUtils.newTile(FIELD))
              .appendAll(Array.fill(2, TileUtils.newTile(MOUNTAIN)))
              .appendAll(Array.fill(4, TileUtils.newTile(HILL)))
              .appendAll(Array.fill(4, TileUtils.newTile(PASTURE)))
              .appendAll(Array.fill(5, TileUtils.newTile(FOREST))),
          false);
  private static final Tuple2<Array<Tile>, Boolean> P4_INDIGENOUS_HARBOR_TILES =
      Base.P3_P4_HARBOR_TILES;
  private static final Tuple2<Array<Tile>, Boolean> P4_DESERT_TILES = P3_DESERT_TILES;
  private static final Tuple2<Array<Tile>, Boolean> P4_FOREIGN_LAND_TILES =
      Tuple.of(
          TileUtils.newTiles(HILL, PASTURE)
              .appendAll(Array.fill(2, TileUtils.newTile(GOLD_FIELD)))
              .appendAll(Array.fill(3, TileUtils.newTile(FIELD)))
              .appendAll(Array.fill(3, TileUtils.newTile(MOUNTAIN))),
          false);
  private static final Tuple2<Array<Tile>, Boolean> P4_FOREIGN_SEA_TILES = P3_FOREIGN_SEA_TILES;
  private static final Map<String, Tuple2<Array<Tile>, Boolean>> P4_TILES =
      HashMap.of(
          "indigenous-land",
          P4_INDIGENOUS_LAND_TILES,
          "indigenous-harbor",
          P4_INDIGENOUS_HARBOR_TILES,
          "desert",
          P4_DESERT_TILES,
          "foreign-land",
          P4_FOREIGN_LAND_TILES,
          "foreign-sea",
          P4_FOREIGN_SEA_TILES);

  private static final Array<Coordinate> P4_INDIGENOUS_LAND_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(3, 3),
          CoordinateUtils.newCoordinate(5, 3),
          CoordinateUtils.newCoordinate(7, 3),
          CoordinateUtils.newCoordinate(9, 3),
          CoordinateUtils.newCoordinate(11, 3),
          CoordinateUtils.newCoordinate(4, 4),
          CoordinateUtils.newCoordinate(6, 4),
          CoordinateUtils.newCoordinate(8, 4),
          CoordinateUtils.newCoordinate(10, 4),
          CoordinateUtils.newCoordinate(12, 4),
          CoordinateUtils.newCoordinate(3, 5),
          CoordinateUtils.newCoordinate(5, 5),
          CoordinateUtils.newCoordinate(7, 5),
          CoordinateUtils.newCoordinate(9, 5),
          CoordinateUtils.newCoordinate(11, 5),
          CoordinateUtils.newCoordinate(13, 5),
          CoordinateUtils.newCoordinate(4, 6));
  private static final Array<Coordinate> P4_INDIGENOUS_HARBOR_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(4, 2, BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(6, 2, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(10, 2, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(1, 3, RIGHT),
          CoordinateUtils.newCoordinate(13, 3, LEFT),
          CoordinateUtils.newCoordinate(2, 4, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(14, 4, BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(2, 6, TOP_RIGHT),
          CoordinateUtils.newCoordinate(6, 6, TOP_RIGHT));
  private static final Array<Coordinate> P4_INDIGENOUS_FISHERY_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(13, 5, RIGHT, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(4, 6, BOTTOM_LEFT, LEFT));
  private static final Array<Coordinate> P4_DESERT_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(8, 6),
          CoordinateUtils.newCoordinate(10, 6),
          CoordinateUtils.newCoordinate(12, 6));
  private static final Array<Coordinate> P4_FOREIGN_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(6, 0),
          CoordinateUtils.newCoordinate(8, 0),
          CoordinateUtils.newCoordinate(10, 0),
          CoordinateUtils.newCoordinate(5, 1),
          CoordinateUtils.newCoordinate(9, 1),
          CoordinateUtils.newCoordinate(11, 1),
          CoordinateUtils.newCoordinate(13, 1),
          CoordinateUtils.newCoordinate(14, 2),
          CoordinateUtils.newCoordinate(15, 3),
          CoordinateUtils.newCoordinate(7, 7),
          CoordinateUtils.newCoordinate(9, 7),
          CoordinateUtils.newCoordinate(11, 7));
  private static final Map<String, Array<Coordinate>> P4_COORDINATES =
      HashMap.of(
          "indigenous-land",
          P4_INDIGENOUS_LAND_COORDINATES,
          "indigenous-harbor",
          P4_INDIGENOUS_HARBOR_COORDINATES,
          "desert",
          P4_DESERT_COORDINATES,
          "foreign",
          P4_FOREIGN_COORDINATES);

  private static final Array<ChitOuterClass.Chit> P4_INDIGENOUS_LAND_CHITS =
      Array.of(CHITS_12)
          .appendAll(Array.fill(2, CHITS_3))
          .appendAll(Array.fill(2, CHITS_4))
          .appendAll(Array.fill(2, CHITS_5))
          .appendAll(Array.fill(2, CHITS_6))
          .appendAll(Array.fill(2, CHITS_8))
          .appendAll(Array.fill(2, CHITS_9))
          .appendAll(Array.fill(2, CHITS_10))
          .appendAll(Array.fill(2, CHITS_11));
  private static final Array<ChitOuterClass.Chit> P4_FOREIGN_LAND_CHITS =
      Array.of(
          CHITS_2, CHITS_3, CHITS_4, CHITS_5, CHITS_6, CHITS_8, CHITS_9, CHITS_10, CHITS_11,
          CHITS_12);
  private static final Map<String, Array<ChitOuterClass.Chit>> P4_CHITS =
      HashMap.of(
          "indigenous-land", P4_INDIGENOUS_LAND_CHITS,
          "foreign-land", P4_FOREIGN_LAND_CHITS);

  private static final SpecificationImpl.Builder P4_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
              P4_TILES,
              P4_COORDINATES,
              P4_CHITS,
              HashMap.ofEntries(
                  TileMappingUtils.newSelfReferringEntry("indigenous-land"),
                  TileMappingUtils.newSelfReferringEntry("indigenous-harbor"),
                  TileMappingUtils.newSelfReferringEntry("desert"),
                  TileMappingUtils.newEntry("foreign", "foreign-land", "foreign-sea")),
              HashMap.ofEntries(
                  TileMappingUtils.newSelfReferringEntry("indigenous-land"),
                  TileMappingUtils.newSelfReferringEntry("foreign-land")))
          .withConfigurationMatcher(CONFIGURATION_MATCHER);

  public static final SpecificationImpl P4_SPECIFICATION_IMPL = P4_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P4_FISHERMEN_SPECIFICATION_IMPL =
      P4_SPECIFICATION_BUILDER.withFisheries(P4_INDIGENOUS_FISHERY_COORDINATES).build();

  private static final Tuple2<Array<Tile>, Boolean> P5_P6_INDIGENOUS_LAND_TILES =
      Tuple.of(
          Array.fill(4, TileUtils.newTile(FIELD))
              .appendAll(Array.fill(4, TileUtils.newTile(FOREST)))
              .appendAll(Array.fill(4, TileUtils.newTile(MOUNTAIN)))
              .appendAll(Array.fill(4, TileUtils.newTile(PASTURE)))
              .appendAll(Array.fill(5, TileUtils.newTile(HILL))),
          false);
  private static final Tuple2<Array<Tile>, Boolean> P5_P6_INDIGENOUS_HARBOR_TILES =
      Base.P5_P6_HARBOR_TILES;
  private static final Tuple2<Array<Tile>, Boolean> P5_P6_DESERT_TILES =
      Tuple.of(Array.fill(5, TileUtils.newTile(DESERT)), true);
  private static final Tuple2<Array<Tile>, Boolean> P5_P6_FOREIGN_LAND_TILES =
      Tuple.of(
          Array.fill(2, TileUtils.newTile(HILL))
              .appendAll(Array.fill(3, TileUtils.newTile(GOLD_FIELD)))
              .appendAll(Array.fill(3, TileUtils.newTile(FIELD)))
              .appendAll(Array.fill(3, TileUtils.newTile(FOREST)))
              .appendAll(Array.fill(3, TileUtils.newTile(MOUNTAIN)))
              .appendAll(Array.fill(3, TileUtils.newTile(PASTURE))),
          false);
  private static final Tuple2<Array<Tile>, Boolean> P5_P6_FOREIGN_SEA_TILES =
      Tuple.of(Array.fill(5, TileUtils.newTile(SEA)), true);
  private static final Map<String, Tuple2<Array<Tile>, Boolean>> P5_P6_TILES =
      HashMap.of(
          "indigenous-land",
          P5_P6_INDIGENOUS_LAND_TILES,
          "indigenous-harbor",
          P5_P6_INDIGENOUS_HARBOR_TILES,
          "desert",
          P5_P6_DESERT_TILES,
          "foreign-land",
          P5_P6_FOREIGN_LAND_TILES,
          "foreign-sea",
          P5_P6_FOREIGN_SEA_TILES);

  private static final Array<Coordinate> P5_P6_INDIGENOUS_LAND_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(3, 1),
          CoordinateUtils.newCoordinate(2, 2),
          CoordinateUtils.newCoordinate(4, 2),
          CoordinateUtils.newCoordinate(1, 3),
          CoordinateUtils.newCoordinate(3, 3),
          CoordinateUtils.newCoordinate(5, 3),
          CoordinateUtils.newCoordinate(7, 3),
          CoordinateUtils.newCoordinate(9, 3),
          CoordinateUtils.newCoordinate(11, 3),
          CoordinateUtils.newCoordinate(13, 3),
          CoordinateUtils.newCoordinate(2, 4),
          CoordinateUtils.newCoordinate(4, 4),
          CoordinateUtils.newCoordinate(6, 4),
          CoordinateUtils.newCoordinate(8, 4),
          CoordinateUtils.newCoordinate(10, 4),
          CoordinateUtils.newCoordinate(12, 4),
          CoordinateUtils.newCoordinate(14, 4),
          CoordinateUtils.newCoordinate(7, 5),
          CoordinateUtils.newCoordinate(9, 5),
          CoordinateUtils.newCoordinate(11, 5),
          CoordinateUtils.newCoordinate(13, 5));
  private static final Array<Coordinate> P5_P6_INDIGENOUS_HARBOR_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(2, 0, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(5, 1, BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(0, 2, RIGHT),
          CoordinateUtils.newCoordinate(6, 2, BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(8, 2, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(12, 2, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(15, 3, BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(0, 4, TOP_RIGHT),
          CoordinateUtils.newCoordinate(1, 5, TOP_RIGHT),
          CoordinateUtils.newCoordinate(5, 5, TOP_RIGHT),
          CoordinateUtils.newCoordinate(15, 5, LEFT));
  private static final Array<Coordinate> P5_P6_INDIGENOUS_FISHERY_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(1, 1, RIGHT, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(1, 3, LEFT, TOP_LEFT));
  private static final Array<Coordinate> P5_P6_DESERT_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(6, 6),
          CoordinateUtils.newCoordinate(8, 6),
          CoordinateUtils.newCoordinate(10, 6),
          CoordinateUtils.newCoordinate(12, 6),
          CoordinateUtils.newCoordinate(14, 6));
  private static final Array<Coordinate> P5_P6_FOREIGN_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(7, 1),
          CoordinateUtils.newCoordinate(9, 1),
          CoordinateUtils.newCoordinate(11, 1),
          CoordinateUtils.newCoordinate(13, 1),
          CoordinateUtils.newCoordinate(15, 1),
          CoordinateUtils.newCoordinate(17, 1),
          CoordinateUtils.newCoordinate(16, 2),
          CoordinateUtils.newCoordinate(18, 2),
          CoordinateUtils.newCoordinate(19, 3),
          CoordinateUtils.newCoordinate(18, 4),
          CoordinateUtils.newCoordinate(19, 5),
          CoordinateUtils.newCoordinate(2, 6),
          CoordinateUtils.newCoordinate(4, 6),
          CoordinateUtils.newCoordinate(18, 6),
          CoordinateUtils.newCoordinate(3, 7),
          CoordinateUtils.newCoordinate(5, 7),
          CoordinateUtils.newCoordinate(7, 7),
          CoordinateUtils.newCoordinate(9, 7),
          CoordinateUtils.newCoordinate(11, 7),
          CoordinateUtils.newCoordinate(13, 7),
          CoordinateUtils.newCoordinate(15, 7),
          CoordinateUtils.newCoordinate(17, 7));
  private static final Map<String, Array<Coordinate>> P5_P6_COORDINATES =
      HashMap.of(
          "indigenous-land",
          P5_P6_INDIGENOUS_LAND_COORDINATES,
          "indigenous-harbor",
          P5_P6_INDIGENOUS_HARBOR_COORDINATES,
          "desert",
          P5_P6_DESERT_COORDINATES,
          "foreign",
          P5_P6_FOREIGN_COORDINATES);

  private static final Array<ChitOuterClass.Chit> P5_P6_INDIGENOUS_LAND_CHITS =
      Array.of(CHITS_2)
          .appendAll(Array.fill(2, CHITS_3))
          .appendAll(Array.fill(2, CHITS_4))
          .appendAll(Array.fill(2, CHITS_5))
          .appendAll(Array.fill(2, CHITS_6))
          .appendAll(Array.fill(2, CHITS_8))
          .appendAll(Array.fill(3, CHITS_9))
          .appendAll(Array.fill(3, CHITS_10))
          .appendAll(Array.fill(2, CHITS_11))
          .appendAll(Array.fill(2, CHITS_12));
  private static final Array<ChitOuterClass.Chit> P5_P6_FOREIGN_LAND_CHITS =
      Array.of(CHITS_9, CHITS_10, CHITS_12)
          .appendAll(Array.fill(2, CHITS_2))
          .appendAll(Array.fill(2, CHITS_3))
          .appendAll(Array.fill(2, CHITS_4))
          .appendAll(Array.fill(2, CHITS_5))
          .appendAll(Array.fill(2, CHITS_6))
          .appendAll(Array.fill(2, CHITS_8))
          .appendAll(Array.fill(2, CHITS_11));
  private static final Map<String, Array<ChitOuterClass.Chit>> P5_P6_CHITS =
      HashMap.of(
          "indigenous-land", P5_P6_INDIGENOUS_LAND_CHITS,
          "foreign-land", P5_P6_FOREIGN_LAND_CHITS);

  private static final SpecificationImpl.Builder P5_P6_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
              P5_P6_TILES,
              P5_P6_COORDINATES,
              P5_P6_CHITS,
              HashMap.ofEntries(
                  TileMappingUtils.newSelfReferringEntry("indigenous-land"),
                  TileMappingUtils.newSelfReferringEntry("indigenous-harbor"),
                  TileMappingUtils.newSelfReferringEntry("desert"),
                  TileMappingUtils.newEntry("foreign", "foreign-land", "foreign-sea")),
              HashMap.ofEntries(
                  TileMappingUtils.newSelfReferringEntry("indigenous-land"),
                  TileMappingUtils.newSelfReferringEntry("foreign-land")))
          .withConfigurationMatcher(CONFIGURATION_MATCHER);

  public static final SpecificationImpl P5_P6_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P5_P6_FISHERMEN_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.withFisheries(P5_P6_INDIGENOUS_FISHERY_COORDINATES).build();

  private static final Tuple2<Array<Tile>, Boolean> P7_P8_INDIGENOUS_LAND_TILES =
      Tuple.of(
          Array.fill(5, TileUtils.newTile(FIELD))
              .appendAll(Array.fill(5, TileUtils.newTile(FOREST)))
              .appendAll(Array.fill(5, TileUtils.newTile(MOUNTAIN)))
              .appendAll(Array.fill(5, TileUtils.newTile(PASTURE)))
              .appendAll(Array.fill(6, TileUtils.newTile(HILL))),
          false);
  private static final Tuple2<Array<Tile>, Boolean> P7_P8_INDIGENOUS_HARBOR_TILES =
      Base.P7_P8_HARBOR_TILES;
  private static final Tuple2<Array<Tile>, Boolean> P7_P8_DESERT_TILES =
      Tuple.of(Array.fill(7, TileUtils.newTile(DESERT)), true);
  private static final Tuple2<Array<Tile>, Boolean> P7_P8_FOREIGN_LAND_TILES =
      Tuple.of(
          Array.fill(4, TileUtils.newTile(HILL))
              .appendAll(Array.fill(4, TileUtils.newTile(PASTURE)))
              .appendAll(Array.fill(5, TileUtils.newTile(FIELD)))
              .appendAll(Array.fill(5, TileUtils.newTile(FOREST)))
              .appendAll(Array.fill(5, TileUtils.newTile(MOUNTAIN)))
              .appendAll(Array.fill(5, TileUtils.newTile(GOLD_FIELD))),
          false);
  private static final Tuple2<Array<Tile>, Boolean> P7_P8_FOREIGN_SEA_TILES =
      Tuple.of(Array.fill(8, TileUtils.newTile(SEA)), true);
  private static final Map<String, Tuple2<Array<Tile>, Boolean>> P7_P8_TILES =
      HashMap.of(
          "indigenous-land",
          P7_P8_INDIGENOUS_LAND_TILES,
          "indigenous-harbor",
          P7_P8_INDIGENOUS_HARBOR_TILES,
          "desert",
          P7_P8_DESERT_TILES,
          "foreign-land",
          P7_P8_FOREIGN_LAND_TILES,
          "foreign-sea",
          P7_P8_FOREIGN_SEA_TILES);

  private static final Array<Coordinate> P7_P8_INDIGENOUS_LAND_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(3, 1),
          CoordinateUtils.newCoordinate(2, 2),
          CoordinateUtils.newCoordinate(4, 2),
          CoordinateUtils.newCoordinate(1, 3),
          CoordinateUtils.newCoordinate(3, 3),
          CoordinateUtils.newCoordinate(5, 3),
          CoordinateUtils.newCoordinate(7, 3),
          CoordinateUtils.newCoordinate(9, 3),
          CoordinateUtils.newCoordinate(11, 3),
          CoordinateUtils.newCoordinate(13, 3),
          CoordinateUtils.newCoordinate(15, 3),
          CoordinateUtils.newCoordinate(2, 4),
          CoordinateUtils.newCoordinate(4, 4),
          CoordinateUtils.newCoordinate(6, 4),
          CoordinateUtils.newCoordinate(8, 4),
          CoordinateUtils.newCoordinate(10, 4),
          CoordinateUtils.newCoordinate(12, 4),
          CoordinateUtils.newCoordinate(14, 4),
          CoordinateUtils.newCoordinate(16, 4),
          CoordinateUtils.newCoordinate(18, 4),
          CoordinateUtils.newCoordinate(7, 5),
          CoordinateUtils.newCoordinate(9, 5),
          CoordinateUtils.newCoordinate(11, 5),
          CoordinateUtils.newCoordinate(13, 5),
          CoordinateUtils.newCoordinate(15, 5),
          CoordinateUtils.newCoordinate(17, 5));
  private static final Array<Coordinate> P7_P8_INDIGENOUS_HARBOR_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(2, 0, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(5, 1, BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(0, 2, RIGHT),
          CoordinateUtils.newCoordinate(6, 2, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(10, 2, BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(12, 2, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(16, 2, BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(17, 3, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(0, 4, TOP_RIGHT),
          CoordinateUtils.newCoordinate(3, 5, TOP_LEFT),
          CoordinateUtils.newCoordinate(5, 5, TOP_RIGHT),
          CoordinateUtils.newCoordinate(19, 5, TOP_LEFT));
  private static final Array<Coordinate> P7_P8_INDIGENOUS_FISHERY_COORDINATES =
      P5_P6_INDIGENOUS_FISHERY_COORDINATES;
  private static final Array<Coordinate> P7_P8_DESERT_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(6, 6),
          CoordinateUtils.newCoordinate(8, 6),
          CoordinateUtils.newCoordinate(10, 6),
          CoordinateUtils.newCoordinate(12, 6),
          CoordinateUtils.newCoordinate(14, 6),
          CoordinateUtils.newCoordinate(16, 6),
          CoordinateUtils.newCoordinate(18, 6));
  private static final Array<Coordinate> P7_P8_FOREIGN_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(7, 1),
          CoordinateUtils.newCoordinate(9, 1),
          CoordinateUtils.newCoordinate(11, 1),
          CoordinateUtils.newCoordinate(13, 1),
          CoordinateUtils.newCoordinate(15, 1),
          CoordinateUtils.newCoordinate(17, 1),
          CoordinateUtils.newCoordinate(19, 1),
          CoordinateUtils.newCoordinate(21, 1),
          CoordinateUtils.newCoordinate(20, 2),
          CoordinateUtils.newCoordinate(22, 2),
          CoordinateUtils.newCoordinate(24, 2),
          CoordinateUtils.newCoordinate(23, 3),
          CoordinateUtils.newCoordinate(25, 3),
          CoordinateUtils.newCoordinate(22, 4),
          CoordinateUtils.newCoordinate(24, 4),
          CoordinateUtils.newCoordinate(23, 5),
          CoordinateUtils.newCoordinate(2, 6),
          CoordinateUtils.newCoordinate(4, 6),
          CoordinateUtils.newCoordinate(22, 6),
          CoordinateUtils.newCoordinate(24, 6),
          CoordinateUtils.newCoordinate(1, 7),
          CoordinateUtils.newCoordinate(3, 7),
          CoordinateUtils.newCoordinate(5, 7),
          CoordinateUtils.newCoordinate(7, 7),
          CoordinateUtils.newCoordinate(9, 7),
          CoordinateUtils.newCoordinate(11, 7),
          CoordinateUtils.newCoordinate(13, 7),
          CoordinateUtils.newCoordinate(15, 7),
          CoordinateUtils.newCoordinate(17, 7),
          CoordinateUtils.newCoordinate(19, 7),
          CoordinateUtils.newCoordinate(21, 7),
          CoordinateUtils.newCoordinate(0, 8),
          CoordinateUtils.newCoordinate(6, 8),
          CoordinateUtils.newCoordinate(12, 8),
          CoordinateUtils.newCoordinate(18, 8),
          CoordinateUtils.newCoordinate(24, 8));
  private static final Map<String, Array<Coordinate>> P7_P8_COORDINATES =
      HashMap.of(
          "indigenous-land",
          P7_P8_INDIGENOUS_LAND_COORDINATES,
          "indigenous-harbor",
          P7_P8_INDIGENOUS_HARBOR_COORDINATES,
          "desert",
          P7_P8_DESERT_COORDINATES,
          "foreign",
          P7_P8_FOREIGN_COORDINATES);

  private static final Array<ChitOuterClass.Chit> P7_P8_INDIGENOUS_LAND_CHITS =
      Array.of(CHITS_2, CHITS_12)
          .appendAll(Array.fill(2, CHITS_3))
          .appendAll(Array.fill(3, CHITS_4))
          .appendAll(Array.fill(4, CHITS_5))
          .appendAll(Array.fill(3, CHITS_6))
          .appendAll(Array.fill(3, CHITS_8))
          .appendAll(Array.fill(4, CHITS_9))
          .appendAll(Array.fill(3, CHITS_10))
          .appendAll(Array.fill(2, CHITS_11));
  private static final Array<ChitOuterClass.Chit> P7_P8_FOREIGN_LAND_CHITS =
      Array.fill(2, CHITS_2)
          .appendAll(Array.fill(3, CHITS_3))
          .appendAll(Array.fill(3, CHITS_4))
          .appendAll(Array.fill(3, CHITS_5))
          .appendAll(Array.fill(3, CHITS_6))
          .appendAll(Array.fill(3, CHITS_8))
          .appendAll(Array.fill(3, CHITS_9))
          .appendAll(Array.fill(3, CHITS_10))
          .appendAll(Array.fill(3, CHITS_11))
          .appendAll(Array.fill(2, CHITS_12));
  private static final Map<String, Array<ChitOuterClass.Chit>> P7_P8_CHITS =
      HashMap.of(
          "indigenous-land", P7_P8_INDIGENOUS_LAND_CHITS,
          "foreign-land", P7_P8_FOREIGN_LAND_CHITS);

  private static final SpecificationImpl.Builder P7_P8_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
              P7_P8_TILES,
              P7_P8_COORDINATES,
              P7_P8_CHITS,
              HashMap.ofEntries(
                  TileMappingUtils.newSelfReferringEntry("indigenous-land"),
                  TileMappingUtils.newSelfReferringEntry("indigenous-harbor"),
                  TileMappingUtils.newSelfReferringEntry("desert"),
                  TileMappingUtils.newEntry("foreign", "foreign-land", "foreign-sea")),
              HashMap.ofEntries(
                  TileMappingUtils.newSelfReferringEntry("indigenous-land"),
                  TileMappingUtils.newSelfReferringEntry("foreign-land")))
          .withConfigurationMatcher(CONFIGURATION_MATCHER);

  public static final SpecificationImpl P7_P8_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P7_P8_FISHERMEN_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.withFisheries(P7_P8_INDIGENOUS_FISHERY_COORDINATES).build();
}
