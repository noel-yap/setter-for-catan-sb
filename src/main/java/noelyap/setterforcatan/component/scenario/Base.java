package noelyap.setterforcatan.component.scenario;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.Array;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import noelyap.setterforcatan.component.SpecificationImpl;
import noelyap.setterforcatan.protogen.ChitOuterClass.Chit;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Coordinate;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge;
import noelyap.setterforcatan.protogen.TileOuterClass.Tile;
import noelyap.setterforcatan.util.ChitUtils;
import noelyap.setterforcatan.util.CoordinateUtils;
import noelyap.setterforcatan.util.TileMappingUtils;
import noelyap.setterforcatan.util.TileUtils;

public class Base {
  public static final Tuple2<Array<Tile>, Boolean> P3_P4_PRODUCING_TILES =
      Tuple.of(
          TileUtils.newTiles(4, Tile.Type.FIELD)
              .appendAll(TileUtils.newTiles(4, Tile.Type.FOREST))
              .appendAll(TileUtils.newTiles(4, Tile.Type.PASTURE))
              .appendAll(TileUtils.newTiles(3, Tile.Type.HILL))
              .appendAll(TileUtils.newTiles(3, Tile.Type.MOUNTAIN)),
          false);
  public static final Tuple2<Array<Tile>, Boolean> P3_P4_DESERT_TILES =
      Tuple.of(TileUtils.newTiles(1, Tile.Type.DESERT), true);
  public static final Tuple2<Array<Tile>, Boolean> P3_P4_HARBOR_TILES =
      Tuple.of(
          TileUtils.newTiles(4, Tile.Type.GENERIC_HARBOR)
              .append(TileUtils.newTile(Tile.Type.GRAIN_HARBOR))
              .append(TileUtils.newTile(Tile.Type.LUMBER_HARBOR))
              .append(TileUtils.newTile(Tile.Type.WOOL_HARBOR))
              .append(TileUtils.newTile(Tile.Type.BRICK_HARBOR))
              .append(TileUtils.newTile(Tile.Type.ORE_HARBOR)),
          true);
  public static final Map<String, Tuple2<Array<Tile>, Boolean>> P3_P4_TILES =
      HashMap.of(
          "producing-terrain",
          P3_P4_PRODUCING_TILES,
          TileUtils.DESERT_OR_LAKE_NAME,
          P3_P4_DESERT_TILES,
          "harbor",
          P3_P4_HARBOR_TILES);

  public static final Array<Coordinate> P3_P4_TERRAIN_COORDINATES =
      CoordinateUtils.newCoordinates(
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
      CoordinateUtils.newCoordinates(
          CoordinateUtils.newCoordinate(2, 0, Edge.Position.BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(6, 0, Edge.Position.BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(9, 1, Edge.Position.BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(0, 2, Edge.Position.RIGHT),
          CoordinateUtils.newCoordinate(11, 3, Edge.Position.LEFT),
          CoordinateUtils.newCoordinate(0, 4, Edge.Position.RIGHT),
          CoordinateUtils.newCoordinate(9, 5, Edge.Position.TOP_LEFT),
          CoordinateUtils.newCoordinate(6, 6, Edge.Position.TOP_LEFT),
          CoordinateUtils.newCoordinate(2, 6, Edge.Position.TOP_RIGHT));
  public static final Array<Coordinate> P3_P4_FISHERY_COORDINATES =
      CoordinateUtils.newCoordinates(
          CoordinateUtils.newCoordinate(
              4, 0, Edge.Position.BOTTOM_RIGHT, Edge.Position.BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(1, 1, Edge.Position.RIGHT, Edge.Position.BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(10, 2, Edge.Position.BOTTOM_LEFT, Edge.Position.LEFT),
          CoordinateUtils.newCoordinate(10, 4, Edge.Position.LEFT, Edge.Position.TOP_LEFT),
          CoordinateUtils.newCoordinate(1, 5, Edge.Position.TOP_RIGHT, Edge.Position.RIGHT),
          CoordinateUtils.newCoordinate(4, 6, Edge.Position.TOP_LEFT, Edge.Position.TOP_RIGHT));
  public static final Map<String, Array<Coordinate>> P3_P4_COORDINATES =
      HashMap.of("terrain", P3_P4_TERRAIN_COORDINATES, "harbor", P3_P4_HARBOR_COORDINATES);

  public static final Array<Chit> P3_P4_PRODUCING_TERRAIN_CHITS =
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
      HashMap.of("producing-terrain", P3_P4_PRODUCING_TERRAIN_CHITS);

  private static final SpecificationImpl.Builder P3_P4_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P3_P4_TILES,
          P3_P4_COORDINATES,
          P3_P4_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("harbor"),
              TileMappingUtils.newEntry(
                  "terrain", "producing-terrain", TileUtils.DESERT_OR_LAKE_NAME)),
          HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("producing-terrain")));
  public static final SpecificationImpl P_3_P_4_SPECIFICATION_IMPL =
      P3_P4_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P_3_P_4_FISHERMEN_SPECIFICATION_IMPL =
      P3_P4_SPECIFICATION_BUILDER.withFisheries(P3_P4_FISHERY_COORDINATES).build();

  public static final Tuple2<Array<Tile>, Boolean> P5_P6_PRODUCING_TILES =
      Tuple.of(
          P3_P4_PRODUCING_TILES
              ._1
              .appendAll(TileUtils.newTiles(2, Tile.Type.FIELD))
              .appendAll(TileUtils.newTiles(2, Tile.Type.FOREST))
              .appendAll(TileUtils.newTiles(2, Tile.Type.PASTURE))
              .appendAll(TileUtils.newTiles(2, Tile.Type.HILL))
              .appendAll(TileUtils.newTiles(2, Tile.Type.MOUNTAIN)),
          false);
  public static final Tuple2<Array<Tile>, Boolean> P5_P6_DESERT_TILES =
      Tuple.of(TileUtils.newTiles(2, Tile.Type.DESERT), true);
  public static final Tuple2<Array<Tile>, Boolean> P5_P6_HARBOR_TILES =
      Tuple.of(
          P3_P4_HARBOR_TILES
              ._1
              .append(TileUtils.newTile(Tile.Type.GENERIC_HARBOR))
              .append(TileUtils.newTile(Tile.Type.WOOL_HARBOR)),
          true);
  public static final Map<String, Tuple2<Array<Tile>, Boolean>> P5_P6_TILES =
      HashMap.of(
          "producing-terrain",
          P5_P6_PRODUCING_TILES,
          TileUtils.DESERT_OR_LAKE_NAME,
          P5_P6_DESERT_TILES,
          "harbor",
          P5_P6_HARBOR_TILES);

  public static final Array<Coordinate> P5_P6_TERRAIN_COORDINATES =
      CoordinateUtils.newCoordinates(
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
      CoordinateUtils.newCoordinates(
          CoordinateUtils.newCoordinate(4, 0, Edge.Position.BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(8, 0, Edge.Position.BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(11, 1, Edge.Position.BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(1, 3, Edge.Position.RIGHT),
          CoordinateUtils.newCoordinate(14, 4, Edge.Position.LEFT),
          CoordinateUtils.newCoordinate(1, 5, Edge.Position.TOP_RIGHT),
          CoordinateUtils.newCoordinate(2, 6, Edge.Position.RIGHT),
          CoordinateUtils.newCoordinate(12, 6, Edge.Position.TOP_LEFT),
          CoordinateUtils.newCoordinate(11, 7, Edge.Position.LEFT),
          CoordinateUtils.newCoordinate(8, 8, Edge.Position.TOP_LEFT),
          CoordinateUtils.newCoordinate(4, 8, Edge.Position.TOP_RIGHT));
  public static final Array<Coordinate> P5_P6_FISHERY_COORDINATES =
      CoordinateUtils.newCoordinates(
          CoordinateUtils.newCoordinate(
              6, 0, Edge.Position.BOTTOM_RIGHT, Edge.Position.BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(4, 2, Edge.Position.LEFT, Edge.Position.TOP_LEFT),
          CoordinateUtils.newCoordinate(11, 3, Edge.Position.TOP_RIGHT, Edge.Position.RIGHT),
          CoordinateUtils.newCoordinate(3, 5, Edge.Position.BOTTOM_LEFT, Edge.Position.LEFT),
          CoordinateUtils.newCoordinate(13, 5, Edge.Position.LEFT, Edge.Position.TOP_LEFT),
          CoordinateUtils.newCoordinate(10, 6, Edge.Position.RIGHT, Edge.Position.BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(3, 7, Edge.Position.TOP_RIGHT, Edge.Position.RIGHT),
          CoordinateUtils.newCoordinate(6, 8, Edge.Position.TOP_LEFT, Edge.Position.TOP_RIGHT));
  public static final Map<String, Array<Coordinate>> P5_P6_COORDINATES =
      HashMap.of("terrain", P5_P6_TERRAIN_COORDINATES, "harbor", P5_P6_HARBOR_COORDINATES);

  public static final Array<Chit> P5_P6_PRODUCING_TERRAIN_CHITS =
      P3_P4_PRODUCING_TERRAIN_CHITS
          .append(ChitUtils.CHITS_2)
          .append(ChitUtils.CHITS_3)
          .append(ChitUtils.CHITS_4)
          .append(ChitUtils.CHITS_5)
          .append(ChitUtils.CHITS_6)
          .append(ChitUtils.CHITS_8)
          .append(ChitUtils.CHITS_9)
          .append(ChitUtils.CHITS_10)
          .append(ChitUtils.CHITS_11)
          .append(ChitUtils.CHITS_12);
  public static final Map<String, Array<Chit>> P5_P6_CHITS =
      HashMap.of("producing-terrain", P5_P6_PRODUCING_TERRAIN_CHITS);

  private static final SpecificationImpl.Builder P5_P6_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P5_P6_TILES,
          P5_P6_COORDINATES,
          P5_P6_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("harbor"),
              TileMappingUtils.newEntry(
                  "terrain", "producing-terrain", TileUtils.DESERT_OR_LAKE_NAME)),
          HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("producing-terrain")));
  public static final SpecificationImpl P_5_P_6_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P_5_P_6_FISHERMEN_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.withFisheries(P5_P6_FISHERY_COORDINATES).build();

  public static final Tuple2<Array<Tile>, Boolean> P7_P8_PRODUCING_TILES =
      Tuple.of(P3_P4_PRODUCING_TILES._1.appendAll(P3_P4_PRODUCING_TILES._1), false);
  public static final Tuple2<Array<Tile>, Boolean> P7_P8_DESERT_TILES =
      Tuple.of(TileUtils.newTiles(1, Tile.Type.DESERT), true);
  public static final Tuple2<Array<Tile>, Boolean> P7_P8_HARBOR_TILES =
      Tuple.of(
          P3_P4_HARBOR_TILES
              ._1
              .append(TileUtils.newTile(Tile.Type.GRAIN_HARBOR))
              .append(TileUtils.newTile(Tile.Type.LUMBER_HARBOR))
              .append(TileUtils.newTile(Tile.Type.WOOL_HARBOR)),
          true);
  public static final Map<String, Tuple2<Array<Tile>, Boolean>> P7_P8_TILES =
      HashMap.of(
          "producing-terrain",
          P7_P8_PRODUCING_TILES,
          TileUtils.DESERT_OR_LAKE_NAME,
          P7_P8_DESERT_TILES,
          "harbor",
          P7_P8_HARBOR_TILES);

  public static final Array<Coordinate> P7_P8_TERRAIN_COORDINATES =
      CoordinateUtils.newCoordinates(
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
      CoordinateUtils.newCoordinates(
          CoordinateUtils.newCoordinate(4, 0, Edge.Position.BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(8, 0, Edge.Position.BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(10, 0, Edge.Position.BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(13, 1, Edge.Position.BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(1, 3, Edge.Position.RIGHT),
          CoordinateUtils.newCoordinate(16, 4, Edge.Position.LEFT),
          CoordinateUtils.newCoordinate(1, 5, Edge.Position.TOP_RIGHT),
          CoordinateUtils.newCoordinate(2, 6, Edge.Position.RIGHT),
          CoordinateUtils.newCoordinate(14, 6, Edge.Position.TOP_LEFT),
          CoordinateUtils.newCoordinate(13, 7, Edge.Position.LEFT),
          CoordinateUtils.newCoordinate(4, 8, Edge.Position.TOP_RIGHT),
          CoordinateUtils.newCoordinate(10, 8, Edge.Position.TOP_LEFT));
  public static final Array<Coordinate> P7_P8_FISHERY_COORDINATES =
      CoordinateUtils.newCoordinates(
          CoordinateUtils.newCoordinate(
              6, 0, Edge.Position.BOTTOM_RIGHT, Edge.Position.BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(11, 1, Edge.Position.TOP_RIGHT, Edge.Position.RIGHT),
          CoordinateUtils.newCoordinate(2, 2, Edge.Position.RIGHT, Edge.Position.BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(13, 3, Edge.Position.TOP_RIGHT, Edge.Position.RIGHT),
          CoordinateUtils.newCoordinate(3, 5, Edge.Position.BOTTOM_LEFT, Edge.Position.LEFT),
          CoordinateUtils.newCoordinate(15, 5, Edge.Position.LEFT, Edge.Position.TOP_LEFT),
          CoordinateUtils.newCoordinate(
              11, 7, Edge.Position.BOTTOM_RIGHT, Edge.Position.BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(6, 8, Edge.Position.TOP_LEFT, Edge.Position.TOP_RIGHT));
  public static final Map<String, Array<Coordinate>> P7_P8_COORDINATES =
      HashMap.of("terrain", P7_P8_TERRAIN_COORDINATES, "harbor", P7_P8_HARBOR_COORDINATES);

  public static final Array<Chit> P7_P8_PRODUCING_TERRAIN_CHITS =
      P3_P4_PRODUCING_TERRAIN_CHITS.appendAll(P3_P4_PRODUCING_TERRAIN_CHITS);
  public static final Map<String, Array<Chit>> P7_P8_CHITS =
      HashMap.of("producing-terrain", P7_P8_PRODUCING_TERRAIN_CHITS);

  public static final SpecificationImpl.Builder P7_P8_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P7_P8_TILES,
          P7_P8_COORDINATES,
          P7_P8_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("harbor"),
              TileMappingUtils.newEntry(
                  "terrain", "producing-terrain", TileUtils.DESERT_OR_LAKE_NAME)),
          HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("producing-terrain")));
  public static final SpecificationImpl P_7_P_8_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P_7_P_8_FISHERMEN_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.withFisheries(P7_P8_FISHERY_COORDINATES).build();
}
