package noelyap.setterforcatan.scenario;

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
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.GRAIN_HARBOR;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.HILL;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.LUMBER_HARBOR;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.MOUNTAIN;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.PASTURE;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.WOOL_HARBOR;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.Array;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import noelyap.setterforcatan.component.SpecificationImpl;
import noelyap.setterforcatan.protogen.ChitOuterClass.Chit;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Coordinate;
import noelyap.setterforcatan.protogen.TileOuterClass.Tile;
import noelyap.setterforcatan.util.ChitUtils;
import noelyap.setterforcatan.util.CoordinateUtils;
import noelyap.setterforcatan.util.TileMappingUtils;
import noelyap.setterforcatan.util.TileUtils;

public class Base {
  public static final Tuple2<Array<Tile>, Boolean> P3_P4_PRODUCING_TILES =
      Tuple.of(
          TileUtils.newTiles(4, FIELD)
              .appendAll(TileUtils.newTiles(4, FOREST))
              .appendAll(TileUtils.newTiles(4, PASTURE))
              .appendAll(TileUtils.newTiles(3, HILL))
              .appendAll(TileUtils.newTiles(3, MOUNTAIN)),
          false);
  public static final Tuple2<Array<Tile>, Boolean> P3_P4_BARREN_TILES =
      Tuple.of(Array.of(TileUtils.newTile(DESERT)), true);
  public static final Tuple2<Array<Tile>, Boolean> P3_P4_HARBOR_TILES =
      Tuple.of(
          TileUtils.newTiles(4, GENERIC_HARBOR).appendAll(TileUtils.TWO_FOR_ONE_HARBORS), true);
  public static final Map<String, Tuple2<Array<Tile>, Boolean>> P3_P4_TILES =
      HashMap.of(
          "producing",
          P3_P4_PRODUCING_TILES,
          TileUtils.DESERT_OR_LAKE_NAME,
          P3_P4_BARREN_TILES,
          "harbor",
          P3_P4_HARBOR_TILES);

  public static final Array<Coordinate> P3_P4_LAND_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(3, 1),
          CoordinateUtils.newCoordinate(5, 1),
          CoordinateUtils.newCoordinate(7, 1),
          CoordinateUtils.newCoordinate(2, 2),
          CoordinateUtils.newCoordinate(4, 2),
          CoordinateUtils.newCoordinate(6, 2),
          CoordinateUtils.newCoordinate(8, 2),
          CoordinateUtils.newCoordinate(1, 3),
          CoordinateUtils.newCoordinate(3, 3),
          CoordinateUtils.newCoordinate(5, 3),
          CoordinateUtils.newCoordinate(7, 3),
          CoordinateUtils.newCoordinate(9, 3),
          CoordinateUtils.newCoordinate(2, 4),
          CoordinateUtils.newCoordinate(4, 4),
          CoordinateUtils.newCoordinate(6, 4),
          CoordinateUtils.newCoordinate(8, 4),
          CoordinateUtils.newCoordinate(3, 5),
          CoordinateUtils.newCoordinate(5, 5),
          CoordinateUtils.newCoordinate(7, 5));
  public static final Array<Coordinate> P3_P4_HARBOR_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(2, 0, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(6, 0, BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(9, 1, BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(0, 2, RIGHT),
          CoordinateUtils.newCoordinate(11, 3, LEFT),
          CoordinateUtils.newCoordinate(0, 4, RIGHT),
          CoordinateUtils.newCoordinate(9, 5, TOP_LEFT),
          CoordinateUtils.newCoordinate(6, 6, TOP_LEFT),
          CoordinateUtils.newCoordinate(2, 6, TOP_RIGHT));
  public static final Array<Coordinate> P3_P4_FISHERY_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(4, 0, BOTTOM_RIGHT, BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(1, 1, RIGHT, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(10, 2, BOTTOM_LEFT, LEFT),
          CoordinateUtils.newCoordinate(10, 4, LEFT, TOP_LEFT),
          CoordinateUtils.newCoordinate(1, 5, TOP_RIGHT, RIGHT),
          CoordinateUtils.newCoordinate(4, 6, TOP_LEFT, TOP_RIGHT));
  public static final Map<String, Array<Coordinate>> P3_P4_COORDINATES =
      HashMap.of("land", P3_P4_LAND_COORDINATES, "harbor", P3_P4_HARBOR_COORDINATES);

  public static final Array<Chit> P3_P4_PRODUCING_CHITS =
      Array.of(ChitUtils.CHITS_2, ChitUtils.CHITS_12)
          .appendAll(Array.fill(2, ChitUtils.CHITS_3))
          .appendAll(Array.fill(2, ChitUtils.CHITS_4))
          .appendAll(Array.fill(2, ChitUtils.CHITS_5))
          .appendAll(Array.fill(2, ChitUtils.CHITS_6))
          .appendAll(Array.fill(2, ChitUtils.CHITS_8))
          .appendAll(Array.fill(2, ChitUtils.CHITS_9))
          .appendAll(Array.fill(2, ChitUtils.CHITS_10))
          .appendAll(Array.fill(2, ChitUtils.CHITS_11));
  public static final Map<String, Array<Chit>> P3_P4_CHITS =
      HashMap.of("producing", P3_P4_PRODUCING_CHITS);

  private static final SpecificationImpl.Builder P3_P4_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P3_P4_TILES,
          P3_P4_COORDINATES,
          P3_P4_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("harbor"),
              TileMappingUtils.newEntry("land", "producing", TileUtils.DESERT_OR_LAKE_NAME)),
          HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("producing")));

  public static final SpecificationImpl P3_P4_SPECIFICATION_IMPL =
      P3_P4_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P3_P4_FISHERMEN_SPECIFICATION_IMPL =
      P3_P4_SPECIFICATION_BUILDER.withFisheries(P3_P4_FISHERY_COORDINATES).build();

  public static final Tuple2<Array<Tile>, Boolean> P5_P6_PRODUCING_TILES =
      Tuple.of(
          P3_P4_PRODUCING_TILES
              ._1
              .appendAll(TileUtils.newTiles(2, FIELD))
              .appendAll(TileUtils.newTiles(2, FOREST))
              .appendAll(TileUtils.newTiles(2, PASTURE))
              .appendAll(TileUtils.newTiles(2, HILL))
              .appendAll(TileUtils.newTiles(2, MOUNTAIN)),
          false);
  public static final Tuple2<Array<Tile>, Boolean> P5_P6_BARREN_LAND_TILES =
      Tuple.of(TileUtils.newTiles(2, DESERT), true);
  public static final Tuple2<Array<Tile>, Boolean> P5_P6_HARBOR_TILES =
      Tuple.of(
          P3_P4_HARBOR_TILES
              ._1
              .append(TileUtils.newTile(GENERIC_HARBOR))
              .append(TileUtils.newTile(WOOL_HARBOR)),
          true);
  public static final Map<String, Tuple2<Array<Tile>, Boolean>> P5_P6_TILES =
      HashMap.of(
          "producing",
          P5_P6_PRODUCING_TILES,
          TileUtils.DESERT_OR_LAKE_NAME,
          P5_P6_BARREN_LAND_TILES,
          "harbor",
          P5_P6_HARBOR_TILES);

  public static final Array<Coordinate> P5_P6_LAND_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(5, 1),
          CoordinateUtils.newCoordinate(7, 1),
          CoordinateUtils.newCoordinate(9, 1),
          CoordinateUtils.newCoordinate(4, 2),
          CoordinateUtils.newCoordinate(6, 2),
          CoordinateUtils.newCoordinate(8, 2),
          CoordinateUtils.newCoordinate(10, 2),
          CoordinateUtils.newCoordinate(3, 3),
          CoordinateUtils.newCoordinate(5, 3),
          CoordinateUtils.newCoordinate(7, 3),
          CoordinateUtils.newCoordinate(9, 3),
          CoordinateUtils.newCoordinate(11, 3),
          CoordinateUtils.newCoordinate(2, 4),
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
          CoordinateUtils.newCoordinate(4, 6),
          CoordinateUtils.newCoordinate(6, 6),
          CoordinateUtils.newCoordinate(8, 6),
          CoordinateUtils.newCoordinate(10, 6),
          CoordinateUtils.newCoordinate(5, 7),
          CoordinateUtils.newCoordinate(7, 7),
          CoordinateUtils.newCoordinate(9, 7));
  public static final Array<Coordinate> P5_P6_HARBOR_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(4, 0, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(8, 0, BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(11, 1, BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(1, 3, RIGHT),
          CoordinateUtils.newCoordinate(14, 4, LEFT),
          CoordinateUtils.newCoordinate(1, 5, TOP_RIGHT),
          CoordinateUtils.newCoordinate(2, 6, RIGHT),
          CoordinateUtils.newCoordinate(12, 6, TOP_LEFT),
          CoordinateUtils.newCoordinate(11, 7, LEFT),
          CoordinateUtils.newCoordinate(8, 8, TOP_LEFT),
          CoordinateUtils.newCoordinate(4, 8, TOP_RIGHT));
  public static final Array<Coordinate> P5_P6_FISHERY_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(6, 0, BOTTOM_RIGHT, BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(4, 2, LEFT, TOP_LEFT),
          CoordinateUtils.newCoordinate(11, 3, TOP_RIGHT, RIGHT),
          CoordinateUtils.newCoordinate(3, 5, BOTTOM_LEFT, LEFT),
          CoordinateUtils.newCoordinate(13, 5, LEFT, TOP_LEFT),
          CoordinateUtils.newCoordinate(10, 6, RIGHT, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(3, 7, TOP_RIGHT, RIGHT),
          CoordinateUtils.newCoordinate(6, 8, TOP_LEFT, TOP_RIGHT));
  public static final Map<String, Array<Coordinate>> P5_P6_COORDINATES =
      HashMap.of("land", P5_P6_LAND_COORDINATES, "harbor", P5_P6_HARBOR_COORDINATES);

  public static final Array<Chit> P5_P6_PRODUCING_CHITS =
      P3_P4_PRODUCING_CHITS.appendAll(
          Array.of(
              ChitUtils.CHITS_2,
              ChitUtils.CHITS_3,
              ChitUtils.CHITS_4,
              ChitUtils.CHITS_5,
              ChitUtils.CHITS_6,
              ChitUtils.CHITS_8,
              ChitUtils.CHITS_9,
              ChitUtils.CHITS_10,
              ChitUtils.CHITS_11,
              ChitUtils.CHITS_12));
  public static final Map<String, Array<Chit>> P5_P6_CHITS =
      HashMap.of("producing", P5_P6_PRODUCING_CHITS);

  private static final SpecificationImpl.Builder P5_P6_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P5_P6_TILES,
          P5_P6_COORDINATES,
          P5_P6_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("harbor"),
              TileMappingUtils.newEntry("land", "producing", TileUtils.DESERT_OR_LAKE_NAME)),
          HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("producing")));

  public static final SpecificationImpl P5_P6_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P5_P6_FISHERMEN_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.withFisheries(P5_P6_FISHERY_COORDINATES).build();

  public static final Tuple2<Array<Tile>, Boolean> P7_P8_PRODUCING_TILES =
      Tuple.of(P3_P4_PRODUCING_TILES._1.appendAll(P3_P4_PRODUCING_TILES._1), false);
  public static final Tuple2<Array<Tile>, Boolean> P7_P8_BARREN_LAND_TILES = P3_P4_BARREN_TILES;
  public static final Tuple2<Array<Tile>, Boolean> P7_P8_HARBOR_TILES =
      Tuple.of(
          P3_P4_HARBOR_TILES
              ._1
              .append(TileUtils.newTile(GRAIN_HARBOR))
              .append(TileUtils.newTile(LUMBER_HARBOR))
              .append(TileUtils.newTile(WOOL_HARBOR)),
          true);
  public static final Map<String, Tuple2<Array<Tile>, Boolean>> P7_P8_TILES =
      HashMap.of(
          "producing",
          P7_P8_PRODUCING_TILES,
          TileUtils.DESERT_OR_LAKE_NAME,
          P7_P8_BARREN_LAND_TILES,
          "harbor",
          P7_P8_HARBOR_TILES);

  public static final Array<Coordinate> P7_P8_LAND_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(5, 1),
          CoordinateUtils.newCoordinate(7, 1),
          CoordinateUtils.newCoordinate(9, 1),
          CoordinateUtils.newCoordinate(11, 1),
          CoordinateUtils.newCoordinate(4, 2),
          CoordinateUtils.newCoordinate(6, 2),
          CoordinateUtils.newCoordinate(8, 2),
          CoordinateUtils.newCoordinate(10, 2),
          CoordinateUtils.newCoordinate(12, 2),
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
          CoordinateUtils.newCoordinate(3, 5),
          CoordinateUtils.newCoordinate(5, 5),
          CoordinateUtils.newCoordinate(7, 5),
          CoordinateUtils.newCoordinate(9, 5),
          CoordinateUtils.newCoordinate(11, 5),
          CoordinateUtils.newCoordinate(13, 5),
          CoordinateUtils.newCoordinate(4, 6),
          CoordinateUtils.newCoordinate(6, 6),
          CoordinateUtils.newCoordinate(8, 6),
          CoordinateUtils.newCoordinate(10, 6),
          CoordinateUtils.newCoordinate(12, 6),
          CoordinateUtils.newCoordinate(5, 7),
          CoordinateUtils.newCoordinate(7, 7),
          CoordinateUtils.newCoordinate(9, 7),
          CoordinateUtils.newCoordinate(11, 7));
  public static final Array<Coordinate> P7_P8_HARBOR_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(4, 0, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(8, 0, BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(10, 0, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(13, 1, BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(1, 3, RIGHT),
          CoordinateUtils.newCoordinate(16, 4, LEFT),
          CoordinateUtils.newCoordinate(1, 5, TOP_RIGHT),
          CoordinateUtils.newCoordinate(2, 6, RIGHT),
          CoordinateUtils.newCoordinate(14, 6, TOP_LEFT),
          CoordinateUtils.newCoordinate(13, 7, LEFT),
          CoordinateUtils.newCoordinate(4, 8, TOP_RIGHT),
          CoordinateUtils.newCoordinate(10, 8, TOP_LEFT));
  public static final Array<Coordinate> P7_P8_FISHERY_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(6, 0, BOTTOM_RIGHT, BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(11, 1, TOP_RIGHT, RIGHT),
          CoordinateUtils.newCoordinate(2, 2, RIGHT, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(13, 3, TOP_RIGHT, RIGHT),
          CoordinateUtils.newCoordinate(3, 5, BOTTOM_LEFT, LEFT),
          CoordinateUtils.newCoordinate(15, 5, LEFT, TOP_LEFT),
          CoordinateUtils.newCoordinate(11, 7, BOTTOM_RIGHT, BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(6, 8, TOP_LEFT, TOP_RIGHT));
  public static final Map<String, Array<Coordinate>> P7_P8_COORDINATES =
      HashMap.of("land", P7_P8_LAND_COORDINATES, "harbor", P7_P8_HARBOR_COORDINATES);

  public static final Array<Chit> P7_P8_PRODUCING_CHITS =
      P3_P4_PRODUCING_CHITS.appendAll(P3_P4_PRODUCING_CHITS);
  public static final Map<String, Array<Chit>> P7_P8_CHITS =
      HashMap.of("producing", P7_P8_PRODUCING_CHITS);

  public static final SpecificationImpl.Builder P7_P8_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P7_P8_TILES,
          P7_P8_COORDINATES,
          P7_P8_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("harbor"),
              TileMappingUtils.newEntry("land", "producing", TileUtils.DESERT_OR_LAKE_NAME)),
          HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("producing")));

  public static final SpecificationImpl P7_P8_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P7_P8_FISHERMEN_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.withFisheries(P7_P8_FISHERY_COORDINATES).build();
}
