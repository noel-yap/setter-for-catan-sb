package noelyap.setterforcatan.component.scenario.seafarers;

import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.BRICK_HARBOR;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.DESERT;
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
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.SEA;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.WOOL_HARBOR;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.Array;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import noelyap.setterforcatan.component.SpecificationImpl;
import noelyap.setterforcatan.component.scenario.Base;
import noelyap.setterforcatan.protogen.ChitOuterClass.Chit;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Coordinate;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge;
import noelyap.setterforcatan.protogen.TileOuterClass.Tile;
import noelyap.setterforcatan.util.ChitUtils;
import noelyap.setterforcatan.util.CoordinateUtils;
import noelyap.setterforcatan.util.TileMappingUtils;
import noelyap.setterforcatan.util.TileUtils;

public class HeadingForNewShores {
  private static final Tuple2<Array<Tile>, Boolean> P3_BIG_ISLAND_PRODUCING_TILES =
      Tuple.of(
          TileUtils.newTiles(3, FIELD)
              .appendAll(TileUtils.newTiles(3, FOREST))
              .appendAll(TileUtils.newTiles(4, PASTURE))
              .appendAll(TileUtils.newTiles(2, HILL))
              .appendAll(TileUtils.newTiles(2, MOUNTAIN)),
          false);
  private static final Tuple2<Array<Tile>, Boolean> P3_SMALL_ISLAND_PRODUCING_TILES =
      Tuple.of(
          Array.of(TileUtils.newTile(FIELD))
              .append(TileUtils.newTile(PASTURE))
              .appendAll(TileUtils.newTiles(2, GOLD_FIELD))
              .appendAll(TileUtils.newTiles(2, HILL))
              .appendAll(TileUtils.newTiles(2, MOUNTAIN)),
          false);
  private static final Tuple2<Array<Tile>, Boolean> P3_SEA_TILES =
      Tuple.of(TileUtils.newTiles(5, SEA), true);
  public static final Tuple2<Array<Tile>, Boolean> P3_HARBOR_TILES =
      Tuple.of(
          TileUtils.newTiles(3, GENERIC_HARBOR)
              .append(TileUtils.newTile(GRAIN_HARBOR))
              .append(TileUtils.newTile(LUMBER_HARBOR))
              .append(TileUtils.newTile(WOOL_HARBOR))
              .append(TileUtils.newTile(BRICK_HARBOR))
              .append(TileUtils.newTile(ORE_HARBOR)),
          true);
  private static final Map<String, Tuple2<Array<Tile>, Boolean>> P3_TILES =
      HashMap.of(
          "big-island-producing-terrain",
          P3_BIG_ISLAND_PRODUCING_TILES,
          "small-island-producing-terrain",
          P3_SMALL_ISLAND_PRODUCING_TILES,
          "sea",
          P3_SEA_TILES,
          "harbor",
          P3_HARBOR_TILES);

  private static final Array<Coordinate> P3_BIG_ISLAND_TERRAIN_COORDINATES =
      CoordinateUtils.newCoordinates(
          CoordinateUtils.newCoordinate(3, 1),
          CoordinateUtils.newCoordinate(5, 1),
          CoordinateUtils.newCoordinate(2, 2),
          CoordinateUtils.newCoordinate(4, 2),
          CoordinateUtils.newCoordinate(6, 2),
          CoordinateUtils.newCoordinate(1, 3),
          CoordinateUtils.newCoordinate(3, 3),
          CoordinateUtils.newCoordinate(5, 3),
          CoordinateUtils.newCoordinate(7, 3),
          CoordinateUtils.newCoordinate(2, 4),
          CoordinateUtils.newCoordinate(4, 4),
          CoordinateUtils.newCoordinate(6, 4),
          CoordinateUtils.newCoordinate(3, 5),
          CoordinateUtils.newCoordinate(5, 5));
  private static final Array<Coordinate> P3_SMALL_ISLAND_TERRAIN_COORDINATES =
      CoordinateUtils.newCoordinates(
          CoordinateUtils.newCoordinate(9, 1),
          CoordinateUtils.newCoordinate(10, 2),
          CoordinateUtils.newCoordinate(11, 3),
          CoordinateUtils.newCoordinate(10, 4),
          CoordinateUtils.newCoordinate(12, 4),
          CoordinateUtils.newCoordinate(11, 5),
          CoordinateUtils.newCoordinate(13, 5),
          CoordinateUtils.newCoordinate(10, 6),
          CoordinateUtils.newCoordinate(12, 6),
          CoordinateUtils.newCoordinate(3, 7),
          CoordinateUtils.newCoordinate(5, 7),
          CoordinateUtils.newCoordinate(7, 7),
          CoordinateUtils.newCoordinate(9, 7));
  private static final Array<Coordinate> P3_HARBOR_COORDINATES =
      CoordinateUtils.newCoordinates(
          CoordinateUtils.newCoordinate(4, 0, Edge.Position.BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(1, 1, Edge.Position.RIGHT),
          CoordinateUtils.newCoordinate(7, 1, Edge.Position.LEFT),
          CoordinateUtils.newCoordinate(0, 2, Edge.Position.BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(0, 4, Edge.Position.TOP_RIGHT),
          CoordinateUtils.newCoordinate(8, 4, Edge.Position.TOP_LEFT),
          CoordinateUtils.newCoordinate(1, 5, Edge.Position.RIGHT),
          CoordinateUtils.newCoordinate(7, 5, Edge.Position.LEFT));
  private static final Array<Coordinate> P3_FISHERY_COORDINATES =
      CoordinateUtils.newCoordinates(
          CoordinateUtils.newCoordinate(2, 2, Edge.Position.LEFT, Edge.Position.TOP_LEFT),
          CoordinateUtils.newCoordinate(8, 2, Edge.Position.BOTTOM_LEFT, Edge.Position.LEFT),
          CoordinateUtils.newCoordinate(6, 4, Edge.Position.RIGHT, Edge.Position.BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(4, 6, Edge.Position.TOP_LEFT, Edge.Position.TOP_RIGHT));
  private static final Map<String, Array<Coordinate>> P3_COORDINATES =
      HashMap.of(
          "big-island-terrain",
          P3_BIG_ISLAND_TERRAIN_COORDINATES,
          "small-island-terrain",
          P3_SMALL_ISLAND_TERRAIN_COORDINATES,
          "harbor",
          P3_HARBOR_COORDINATES);

  private static final Array<Chit> P3_BIG_ISLAND_PRODUCING_TERRAIN_CHITS =
      Array.of(ChitUtils.CHITS_2, ChitUtils.CHITS_3, ChitUtils.CHITS_4, ChitUtils.CHITS_9)
          .appendAll(Array.fill(2, ChitUtils.CHITS_5))
          .appendAll(Array.fill(2, ChitUtils.CHITS_6))
          .appendAll(Array.fill(2, ChitUtils.CHITS_8))
          .appendAll(Array.fill(2, ChitUtils.CHITS_10))
          .appendAll(Array.fill(2, ChitUtils.CHITS_11));
  private static final Array<Chit> P3_SMALL_ISLAND_PRODUCING_TERRAIN_CHITS =
      Array.of(
              ChitUtils.CHITS_3,
              ChitUtils.CHITS_5,
              ChitUtils.CHITS_8,
              ChitUtils.CHITS_9,
              ChitUtils.CHITS_10,
              ChitUtils.CHITS_12)
          .appendAll(Array.fill(2, ChitUtils.CHITS_4));
  private static final Map<String, Array<Chit>> P3_CHITS =
      HashMap.of(
          "big-island-producing-terrain",
          P3_BIG_ISLAND_PRODUCING_TERRAIN_CHITS,
          "small-island-producing-terrain",
          P3_SMALL_ISLAND_PRODUCING_TERRAIN_CHITS);

  private static final SpecificationImpl.Builder P3_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P3_TILES,
          P3_COORDINATES,
          P3_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("harbor"),
              TileMappingUtils.newEntry("big-island-terrain", "big-island-producing-terrain"),
              TileMappingUtils.newEntry(
                  "small-island-terrain", "small-island-producing-terrain", "sea")),
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("big-island-producing-terrain"),
              TileMappingUtils.newSelfReferringEntry("small-island-producing-terrain")));

  public static final SpecificationImpl P3_SPECIFICATION_IMPL = P3_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P3_FISHERMEN_SPECIFICATION_IMPL =
      P3_SPECIFICATION_BUILDER.withFisheries(P3_FISHERY_COORDINATES).build();

  private static final Tuple2<Array<Tile>, Boolean> P4_BIG_ISLAND_PRODUCING_TILES =
      Base.P3_P4_PRODUCING_TILES;
  private static final Tuple2<Array<Tile>, Boolean> P4_DESERT_TILES = Base.P3_P4_DESERT_TILES;
  private static final Tuple2<Array<Tile>, Boolean> P4_SMALL_ISLAND_PRODUCING_TILES =
      Tuple.of(P3_SMALL_ISLAND_PRODUCING_TILES._1.append(TileUtils.newTile(FOREST)), false);
  private static final Tuple2<Array<Tile>, Boolean> P4_SEA_TILES =
      Tuple.of(TileUtils.newTiles(4, SEA), true);
  private static final Tuple2<Array<Tile>, Boolean> P4_HARBOR_TILES = Base.P3_P4_HARBOR_TILES;
  private static final Map<String, Tuple2<Array<Tile>, Boolean>> P4_TILES =
      HashMap.of(
          "big-island-producing-terrain",
          P4_BIG_ISLAND_PRODUCING_TILES,
          TileUtils.DESERT_OR_LAKE_NAME,
          P4_DESERT_TILES,
          "small-island-producing-terrain",
          P4_SMALL_ISLAND_PRODUCING_TILES,
          "sea",
          P4_SEA_TILES,
          "harbor",
          P4_HARBOR_TILES);

  private static final Array<Coordinate> P4_BIG_ISLAND_TERRAIN_COORDINATES =
      Base.P3_P4_TERRAIN_COORDINATES;
  private static final Array<Coordinate> P4_SMALL_ISLAND_TERRAIN_COORDINATES =
      CoordinateUtils.newCoordinates(
          CoordinateUtils.newCoordinate(11, 1),
          CoordinateUtils.newCoordinate(12, 2),
          CoordinateUtils.newCoordinate(13, 3),
          CoordinateUtils.newCoordinate(12, 4),
          CoordinateUtils.newCoordinate(11, 5),
          CoordinateUtils.newCoordinate(13, 5),
          CoordinateUtils.newCoordinate(10, 6),
          CoordinateUtils.newCoordinate(12, 6),
          CoordinateUtils.newCoordinate(3, 7),
          CoordinateUtils.newCoordinate(5, 7),
          CoordinateUtils.newCoordinate(7, 7),
          CoordinateUtils.newCoordinate(9, 7),
          CoordinateUtils.newCoordinate(11, 7));
  private static final Array<Coordinate> P4_HARBOR_COORDINATES = Base.P3_P4_HARBOR_COORDINATES;
  private static final Array<Coordinate> P4_FISHERY_COORDINATES = Base.P3_P4_FISHERY_COORDINATES;
  private static final Map<String, Array<Coordinate>> P4_COORDINATES =
      HashMap.of(
          "big-island-terrain",
          P4_BIG_ISLAND_TERRAIN_COORDINATES,
          "small-island-terrain",
          P4_SMALL_ISLAND_TERRAIN_COORDINATES,
          "harbor",
          P4_HARBOR_COORDINATES);

  private static final Array<Chit> P4_BIG_ISLAND_PRODUCING_TERRAIN_CHITS =
      Base.P3_P4_PRODUCING_TERRAIN_CHITS;
  private static final Array<Chit> P4_SMALL_ISLAND_PRODUCING_TERRAIN_CHITS =
      Array.of(
          ChitUtils.CHITS_2,
          ChitUtils.CHITS_3,
          ChitUtils.CHITS_4,
          ChitUtils.CHITS_5,
          ChitUtils.CHITS_6,
          ChitUtils.CHITS_8,
          ChitUtils.CHITS_9,
          ChitUtils.CHITS_10,
          ChitUtils.CHITS_11);
  private static final Map<String, Array<Chit>> P4_CHITS =
      HashMap.of(
          "big-island-producing-terrain",
          P4_BIG_ISLAND_PRODUCING_TERRAIN_CHITS,
          "small-island-producing-terrain",
          P4_SMALL_ISLAND_PRODUCING_TERRAIN_CHITS);

  private static final SpecificationImpl.Builder P4_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P4_TILES,
          P4_COORDINATES,
          P4_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("harbor"),
              TileMappingUtils.newEntry(
                  "big-island-terrain",
                  "big-island-producing-terrain",
                  TileUtils.DESERT_OR_LAKE_NAME),
              TileMappingUtils.newEntry(
                  "small-island-terrain", "small-island-producing-terrain", "sea")),
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("big-island-producing-terrain"),
              TileMappingUtils.newSelfReferringEntry("small-island-producing-terrain")));

  public static final SpecificationImpl P4_SPECIFICATION_IMPL = P4_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P4_FISHERMEN_SPECIFICATION_IMPL =
      P4_SPECIFICATION_BUILDER.withFisheries(P4_FISHERY_COORDINATES).build();

  private static final Tuple2<Array<Tile>, Boolean> P5_P6_BIG_ISLAND_PRODUCING_TILES =
      Base.P5_P6_PRODUCING_TILES;
  public static final Tuple2<Array<Tile>, Boolean> P5_P6_DESERT_TILES =
      Tuple.of(TileUtils.newTiles(2, DESERT), true);
  private static final Tuple2<Array<Tile>, Boolean> P5_P6_SMALL_ISLAND_PRODUCING_TILES =
      Tuple.of(
          Array.of(TileUtils.newTile(FIELD))
              .append(TileUtils.newTile(FOREST))
              .append(TileUtils.newTile(PASTURE))
              .appendAll(TileUtils.newTiles(2, HILL))
              .appendAll(TileUtils.newTiles(2, MOUNTAIN))
              .appendAll(TileUtils.newTiles(3, GOLD_FIELD)),
          false);
  private static final Tuple2<Array<Tile>, Boolean> P5_P6_SEA_TILES =
      Tuple.of(TileUtils.newTiles(2, SEA), true);
  public static final Tuple2<Array<Tile>, Boolean> P5_P6_HARBOR_TILES = Base.P5_P6_HARBOR_TILES;
  private static final Map<String, Tuple2<Array<Tile>, Boolean>> P5_P6_TILES =
      HashMap.of(
          "big-island-producing-terrain",
          P5_P6_BIG_ISLAND_PRODUCING_TILES,
          TileUtils.DESERT_OR_LAKE_NAME,
          P5_P6_DESERT_TILES,
          "small-island-producing-terrain",
          P5_P6_SMALL_ISLAND_PRODUCING_TILES,
          "sea",
          P5_P6_SEA_TILES,
          "harbor",
          P5_P6_HARBOR_TILES);

  private static final Array<Coordinate> P5_P6_BIG_ISLAND_TERRAIN_COORDINATES =
      Base.P5_P6_TERRAIN_COORDINATES.map(c ->
              Coordinate.newBuilder()
                  .setX(c.getX() + 2)
                  .setY(c.getY())
                  .addAllEdgePositions(c.getEdgePositionsList())
                  .build());
  private static final Array<Coordinate> P5_P6_SMALL_ISLAND_TERRAIN_COORDINATES =
      CoordinateUtils.newCoordinates(
          CoordinateUtils.newCoordinate(3, 1),
          CoordinateUtils.newCoordinate(15, 1),
          CoordinateUtils.newCoordinate(2, 2),
          CoordinateUtils.newCoordinate(16, 2),
          CoordinateUtils.newCoordinate(1, 3),
          CoordinateUtils.newCoordinate(17, 3),
          CoordinateUtils.newCoordinate(1, 5),
          CoordinateUtils.newCoordinate(17, 5),
          CoordinateUtils.newCoordinate(2, 6),
          CoordinateUtils.newCoordinate(16, 6),
          CoordinateUtils.newCoordinate(3, 7),
          CoordinateUtils.newCoordinate(15, 7));
  private static final Array<Coordinate> P5_P6_HARBOR_COORDINATES =
      CoordinateUtils.newCoordinates(
          CoordinateUtils.newCoordinate(6, 0, Edge.Position.BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(10, 0, Edge.Position.BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(13, 1, Edge.Position.BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(4, 2, Edge.Position.RIGHT),
          CoordinateUtils.newCoordinate(3, 3, Edge.Position.BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(15, 3, Edge.Position.BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(3, 5, Edge.Position.RIGHT),
          CoordinateUtils.newCoordinate(15, 5, Edge.Position.LEFT),
          CoordinateUtils.newCoordinate(5, 7, Edge.Position.TOP_RIGHT),
          CoordinateUtils.newCoordinate(13, 7, Edge.Position.TOP_LEFT),
          CoordinateUtils.newCoordinate(8, 8, Edge.Position.TOP_RIGHT));
  private static final Array<Coordinate> P5_P6_FISHERY_COORDINATES =
      CoordinateUtils.newCoordinates(
          CoordinateUtils.newCoordinate(5, 1, Edge.Position.RIGHT, Edge.Position.BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(14, 2, Edge.Position.BOTTOM_LEFT, Edge.Position.LEFT),
          CoordinateUtils.newCoordinate(5, 3, Edge.Position.LEFT, Edge.Position.TOP_LEFT),
          CoordinateUtils.newCoordinate(4, 4, Edge.Position.BOTTOM_LEFT, Edge.Position.LEFT),
          CoordinateUtils.newCoordinate(14, 4, Edge.Position.RIGHT, Edge.Position.BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(14, 6, Edge.Position.LEFT, Edge.Position.TOP_LEFT),
          CoordinateUtils.newCoordinate(4, 6, Edge.Position.TOP_RIGHT, Edge.Position.RIGHT),
          CoordinateUtils.newCoordinate(11, 7, Edge.Position.RIGHT, Edge.Position.BOTTOM_RIGHT));
  private static final Map<String, Array<Coordinate>> P5_P6_COORDINATES =
      HashMap.of(
          "big-island-terrain",
          P5_P6_BIG_ISLAND_TERRAIN_COORDINATES,
          "small-island-terrain",
          P5_P6_SMALL_ISLAND_TERRAIN_COORDINATES,
          "harbor",
          P5_P6_HARBOR_COORDINATES);

  private static final Array<Chit> P5_P6_BIG_ISLAND_PRODUCING_TERRAIN_CHITS =
      Base.P5_P6_PRODUCING_TERRAIN_CHITS;
  private static final Array<Chit> P5_P6_SMALL_ISLAND_PRODUCING_TERRAIN_CHITS =
      P4_SMALL_ISLAND_PRODUCING_TERRAIN_CHITS.append(ChitUtils.CHITS_12);
  private static final Map<String, Array<Chit>> P5_P6_CHITS =
      HashMap.of(
          "big-island-producing-terrain",
          P5_P6_BIG_ISLAND_PRODUCING_TERRAIN_CHITS,
          "small-island-producing-terrain",
          P5_P6_SMALL_ISLAND_PRODUCING_TERRAIN_CHITS);

  private static final SpecificationImpl.Builder P5_P6_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P5_P6_TILES,
          P5_P6_COORDINATES,
          P5_P6_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("harbor"),
              TileMappingUtils.newEntry(
                  "big-island-terrain",
                  "big-island-producing-terrain",
                  TileUtils.DESERT_OR_LAKE_NAME),
              TileMappingUtils.newEntry(
                  "small-island-terrain", "small-island-producing-terrain", "sea")),
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("big-island-producing-terrain"),
              TileMappingUtils.newSelfReferringEntry("small-island-producing-terrain")));

  public static final SpecificationImpl P5_P6_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P5_P6_FISHERMEN_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.withFisheries(P5_P6_FISHERY_COORDINATES).build();

  private static final Tuple2<Array<Tile>, Boolean> P7_P8_BIG_ISLAND_PRODUCING_TILES =
      Base.P7_P8_PRODUCING_TILES;
  public static final Tuple2<Array<Tile>, Boolean> P7_P8_DESERT_TILES = Base.P7_P8_DESERT_TILES;
  private static final Tuple2<Array<Tile>, Boolean> P7_P8_SMALL_ISLAND_PRODUCING_TILES =
      Tuple.of(
          P5_P6_SMALL_ISLAND_PRODUCING_TILES._1.appendAll(P5_P6_SMALL_ISLAND_PRODUCING_TILES._1),
          false);
  private static final Tuple2<Array<Tile>, Boolean> P7_P8_SEA_TILES =
      Tuple.of(TileUtils.newTiles(10, SEA), true);
  public static final Tuple2<Array<Tile>, Boolean> P7_P8_HARBOR_TILES = Base.P7_P8_HARBOR_TILES;
  private static final Map<String, Tuple2<Array<Tile>, Boolean>> P7_P8_TILES =
      HashMap.of(
          "big-island-producing-terrain",
          P7_P8_BIG_ISLAND_PRODUCING_TILES,
          TileUtils.DESERT_OR_LAKE_NAME,
          P7_P8_DESERT_TILES,
          "small-island-producing-terrain",
          P7_P8_SMALL_ISLAND_PRODUCING_TILES,
          "sea",
          P7_P8_SEA_TILES,
          "harbor",
          P7_P8_HARBOR_TILES);

  private static final Array<Coordinate> P7_P8_BIG_ISLAND_TERRAIN_COORDINATES =
      Base.P7_P8_TERRAIN_COORDINATES.map(c ->
              Coordinate.newBuilder()
                  .setX(c.getX() + 3)
                  .setY(c.getY() + 1)
                  .addAllEdgePositions(c.getEdgePositionsList())
                  .build());
  private static final Array<Coordinate> P7_P8_SMALL_ISLAND_TERRAIN_COORDINATES =
      CoordinateUtils.newCoordinates(
          CoordinateUtils.newCoordinate(6, 0),
          CoordinateUtils.newCoordinate(8, 0),
          CoordinateUtils.newCoordinate(10, 0),
          CoordinateUtils.newCoordinate(12, 0),
          CoordinateUtils.newCoordinate(14, 0),
          CoordinateUtils.newCoordinate(16, 0),
          CoordinateUtils.newCoordinate(5, 1),
          CoordinateUtils.newCoordinate(17, 1),
          CoordinateUtils.newCoordinate(4, 2),
          CoordinateUtils.newCoordinate(18, 2),
          CoordinateUtils.newCoordinate(3, 3),
          CoordinateUtils.newCoordinate(19, 3),
          CoordinateUtils.newCoordinate(2, 4),
          CoordinateUtils.newCoordinate(20, 4),
          CoordinateUtils.newCoordinate(1, 5),
          CoordinateUtils.newCoordinate(21, 5),
          CoordinateUtils.newCoordinate(2, 6),
          CoordinateUtils.newCoordinate(20, 6),
          CoordinateUtils.newCoordinate(3, 7),
          CoordinateUtils.newCoordinate(19, 7),
          CoordinateUtils.newCoordinate(4, 8),
          CoordinateUtils.newCoordinate(18, 8),
          CoordinateUtils.newCoordinate(5, 9),
          CoordinateUtils.newCoordinate(17, 9),
          CoordinateUtils.newCoordinate(6, 10),
          CoordinateUtils.newCoordinate(8, 10),
          CoordinateUtils.newCoordinate(10, 10),
          CoordinateUtils.newCoordinate(12, 10),
          CoordinateUtils.newCoordinate(14, 10),
          CoordinateUtils.newCoordinate(16, 10));
  private static final Array<Coordinate> P7_P8_HARBOR_COORDINATES =
      Base.P7_P8_HARBOR_COORDINATES.map(c ->
              Coordinate.newBuilder()
                  .setX(c.getX() + 3)
                  .setY(c.getY() + 1)
                  .addAllEdgePositions(c.getEdgePositionsList())
                  .build());
  private static final Array<Coordinate> P7_P8_FISHERY_COORDINATES =
      Base.P7_P8_FISHERY_COORDINATES.map(c ->
              Coordinate.newBuilder()
                  .setX(c.getX() + 3)
                  .setY(c.getY() + 1)
                  .addAllEdgePositions(c.getEdgePositionsList())
                  .build());
  private static final Map<String, Array<Coordinate>> P7_P8_COORDINATES =
      HashMap.of(
          "big-island-terrain",
          P7_P8_BIG_ISLAND_TERRAIN_COORDINATES,
          "small-island-terrain",
          P7_P8_SMALL_ISLAND_TERRAIN_COORDINATES,
          "harbor",
          P7_P8_HARBOR_COORDINATES);

  private static final Array<Chit> P7_P8_BIG_ISLAND_PRODUCING_TERRAIN_CHITS =
      Base.P7_P8_PRODUCING_TERRAIN_CHITS;
  private static final Array<Chit> P7_P8_SMALL_ISLAND_PRODUCING_TERRAIN_CHITS =
      P5_P6_SMALL_ISLAND_PRODUCING_TERRAIN_CHITS.appendAll(
          P5_P6_SMALL_ISLAND_PRODUCING_TERRAIN_CHITS);
  private static final Map<String, Array<Chit>> P7_P8_CHITS =
      HashMap.of(
          "big-island-producing-terrain",
          P7_P8_BIG_ISLAND_PRODUCING_TERRAIN_CHITS,
          "small-island-producing-terrain",
          P7_P8_SMALL_ISLAND_PRODUCING_TERRAIN_CHITS);

  private static final SpecificationImpl.Builder P7_P8_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P7_P8_TILES,
          P7_P8_COORDINATES,
          P7_P8_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("harbor"),
              TileMappingUtils.newEntry(
                  "big-island-terrain",
                  "big-island-producing-terrain",
                  TileUtils.DESERT_OR_LAKE_NAME),
              TileMappingUtils.newEntry(
                  "small-island-terrain", "small-island-producing-terrain", "sea")),
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("big-island-producing-terrain"),
              TileMappingUtils.newSelfReferringEntry("small-island-producing-terrain")));

  public static final SpecificationImpl P7_P8_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P7_P8_FISHERMEN_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.withFisheries(P7_P8_FISHERY_COORDINATES).build();
}
