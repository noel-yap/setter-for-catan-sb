package noelyap.setterforcatan.component.scenario.seafarers;

import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.FIELD;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.FOREST;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.HILL;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.MOUNTAIN;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.PASTURE;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.Array;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import noelyap.setterforcatan.component.SpecificationImpl;
import noelyap.setterforcatan.component.scenario.Base;
import noelyap.setterforcatan.protogen.ChitOuterClass;
import noelyap.setterforcatan.protogen.CoordinateOuterClass;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge;
import noelyap.setterforcatan.protogen.TileOuterClass;
import noelyap.setterforcatan.util.ChitUtils;
import noelyap.setterforcatan.util.CoordinateUtils;
import noelyap.setterforcatan.util.TileMappingUtils;
import noelyap.setterforcatan.util.TileUtils;

public class TheNIslands {
  public static final Tuple2<Array<TileOuterClass.Tile>, Boolean> P3_TERRAIN_TILES =
      Tuple.of(
          Base.P3_P4_PRODUCING_TILES
              ._1
              .append(TileUtils.newTile(HILL))
              .append(TileUtils.newTile(MOUNTAIN)),
          false);
  public static final Tuple2<Array<TileOuterClass.Tile>, Boolean> P3_HARBOR_TILES =
      Base.P3_P4_HARBOR_TILES;
  private static final Map<String, Tuple2<Array<TileOuterClass.Tile>, Boolean>> P3_TILES =
      HashMap.of("terrain", P3_TERRAIN_TILES, "harbor", P3_HARBOR_TILES);

  private static final Array<CoordinateOuterClass.Coordinate> P3_TERRAIN_COORDINATES =
      CoordinateUtils.newCoordinates(
          CoordinateUtils.newCoordinate(10, 0),
          CoordinateUtils.newCoordinate(3, 1),
          CoordinateUtils.newCoordinate(5, 1),
          CoordinateUtils.newCoordinate(9, 1),
          CoordinateUtils.newCoordinate(11, 1),
          CoordinateUtils.newCoordinate(2, 2),
          CoordinateUtils.newCoordinate(4, 2),
          CoordinateUtils.newCoordinate(8, 2),
          CoordinateUtils.newCoordinate(10, 2),
          CoordinateUtils.newCoordinate(12, 2),
          CoordinateUtils.newCoordinate(4, 4),
          CoordinateUtils.newCoordinate(6, 4),
          CoordinateUtils.newCoordinate(10, 4),
          CoordinateUtils.newCoordinate(12, 4),
          CoordinateUtils.newCoordinate(3, 5),
          CoordinateUtils.newCoordinate(5, 5),
          CoordinateUtils.newCoordinate(9, 5),
          CoordinateUtils.newCoordinate(11, 5),
          CoordinateUtils.newCoordinate(4, 6),
          CoordinateUtils.newCoordinate(6, 6));
  private static final Array<CoordinateOuterClass.Coordinate> P3_HARBOR_COORDINATES =
      CoordinateUtils.newCoordinates(
          CoordinateUtils.newCoordinate(8, 0, Edge.Position.BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(1, 1, Edge.Position.BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(13, 1, Edge.Position.BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(6, 2, Edge.Position.TOP_LEFT),
          CoordinateUtils.newCoordinate(5, 3, Edge.Position.BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(11, 3, Edge.Position.TOP_RIGHT),
          CoordinateUtils.newCoordinate(8, 4, Edge.Position.BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(13, 5, Edge.Position.TOP_LEFT),
          CoordinateUtils.newCoordinate(2, 6, Edge.Position.TOP_RIGHT));
  private static final Array<CoordinateOuterClass.Coordinate> P3_FISHERY_COORDINATES =
      CoordinateUtils.newCoordinates(
          CoordinateUtils.newCoordinate(
              4, 0, Edge.Position.BOTTOM_RIGHT, Edge.Position.BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(3, 3, Edge.Position.TOP_LEFT, Edge.Position.TOP_RIGHT),
          CoordinateUtils.newCoordinate(
              11, 3, Edge.Position.BOTTOM_RIGHT, Edge.Position.BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(10, 6, Edge.Position.TOP_LEFT, Edge.Position.TOP_RIGHT));
  private static final Map<String, Array<CoordinateOuterClass.Coordinate>> P3_COORDINATES =
      HashMap.of("terrain", P3_TERRAIN_COORDINATES, "harbor", P3_HARBOR_COORDINATES);

  private static final Array<ChitOuterClass.Chit> P3_TERRAIN_CHITS =
      Base.P3_P4_PRODUCING_TERRAIN_CHITS.appendAll(Array.of(ChitUtils.CHITS_5, ChitUtils.CHITS_9));
  private static final Map<String, Array<ChitOuterClass.Chit>> P3_CHITS =
      HashMap.of("terrain", P3_TERRAIN_CHITS);

  private static final SpecificationImpl.Builder P3_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P3_TILES,
          P3_COORDINATES,
          P3_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("terrain"),
              TileMappingUtils.newSelfReferringEntry("harbor")),
          HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("terrain")));

  public static final SpecificationImpl P3_SPECIFICATION_IMPL = P3_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P3_FISHERMEN_SPECIFICATION_IMPL =
      P3_SPECIFICATION_BUILDER.withFisheries(P3_FISHERY_COORDINATES).build();

  public static final Tuple2<Array<TileOuterClass.Tile>, Boolean> P4_TERRAIN_TILES =
      Tuple.of(
          P3_TERRAIN_TILES
              ._1
              .append(TileUtils.newTile(FIELD))
              .append(TileUtils.newTile(PASTURE))
              .append(TileUtils.newTile(FOREST)),
          false);
  public static final Tuple2<Array<TileOuterClass.Tile>, Boolean> P4_HARBOR_TILES =
      Base.P3_P4_HARBOR_TILES;
  private static final Map<String, Tuple2<Array<TileOuterClass.Tile>, Boolean>> P4_TILES =
      HashMap.of("terrain", P4_TERRAIN_TILES, "harbor", P4_HARBOR_TILES);

  private static final Array<CoordinateOuterClass.Coordinate> P4_TERRAIN_COORDINATES =
      CoordinateUtils.newCoordinates(
          CoordinateUtils.newCoordinate(4, 0),
          CoordinateUtils.newCoordinate(8, 0),
          CoordinateUtils.newCoordinate(10, 0),
          CoordinateUtils.newCoordinate(3, 1),
          CoordinateUtils.newCoordinate(7, 1),
          CoordinateUtils.newCoordinate(9, 1),
          CoordinateUtils.newCoordinate(11, 1),
          CoordinateUtils.newCoordinate(2, 2),
          CoordinateUtils.newCoordinate(4, 2),
          CoordinateUtils.newCoordinate(10, 2),
          CoordinateUtils.newCoordinate(12, 2),
          CoordinateUtils.newCoordinate(7, 3),
          CoordinateUtils.newCoordinate(4, 4),
          CoordinateUtils.newCoordinate(6, 4),
          CoordinateUtils.newCoordinate(10, 4),
          CoordinateUtils.newCoordinate(12, 4),
          CoordinateUtils.newCoordinate(3, 5),
          CoordinateUtils.newCoordinate(5, 5),
          CoordinateUtils.newCoordinate(7, 5),
          CoordinateUtils.newCoordinate(11, 5),
          CoordinateUtils.newCoordinate(4, 6),
          CoordinateUtils.newCoordinate(6, 6),
          CoordinateUtils.newCoordinate(10, 6));
  private static final Array<CoordinateOuterClass.Coordinate> P4_HARBOR_COORDINATES =
      CoordinateUtils.newCoordinates(
          CoordinateUtils.newCoordinate(12, 0, Edge.Position.LEFT),
          CoordinateUtils.newCoordinate(1, 1, Edge.Position.BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(13, 1, Edge.Position.BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(8, 2, Edge.Position.TOP_RIGHT),
          CoordinateUtils.newCoordinate(3, 3, Edge.Position.TOP_LEFT),
          CoordinateUtils.newCoordinate(11, 3, Edge.Position.BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(2, 4, Edge.Position.BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(8, 4, Edge.Position.TOP_LEFT),
          CoordinateUtils.newCoordinate(13, 5, Edge.Position.LEFT));
  private static final Array<CoordinateOuterClass.Coordinate> P4_FISHERY_COORDINATES =
      CoordinateUtils.newCoordinates(
          CoordinateUtils.newCoordinate(2, 0, Edge.Position.RIGHT, Edge.Position.BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(2, 2, Edge.Position.BOTTOM_LEFT, Edge.Position.LEFT),
          CoordinateUtils.newCoordinate(4, 2, Edge.Position.TOP_RIGHT, Edge.Position.RIGHT),
          CoordinateUtils.newCoordinate(10, 4, Edge.Position.BOTTOM_LEFT, Edge.Position.LEFT),
          CoordinateUtils.newCoordinate(12, 4, Edge.Position.TOP_RIGHT, Edge.Position.RIGHT),
          CoordinateUtils.newCoordinate(12, 6, Edge.Position.LEFT, Edge.Position.TOP_LEFT));
  private static final Map<String, Array<CoordinateOuterClass.Coordinate>> P4_COORDINATES =
      HashMap.of("terrain", P4_TERRAIN_COORDINATES, "harbor", P4_HARBOR_COORDINATES);

  private static final Array<ChitOuterClass.Chit> P4_TERRAIN_CHITS =
      P3_TERRAIN_CHITS.appendAll(
          Array.of(ChitUtils.CHITS_4, ChitUtils.CHITS_10, ChitUtils.CHITS_2_12));
  private static final Map<String, Array<ChitOuterClass.Chit>> P4_CHITS =
      HashMap.of("terrain", P4_TERRAIN_CHITS);

  private static final SpecificationImpl.Builder P4_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P4_TILES,
          P4_COORDINATES,
          P4_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("terrain"),
              TileMappingUtils.newSelfReferringEntry("harbor")),
          HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("terrain")));

  public static final SpecificationImpl P4_SPECIFICATION_IMPL = P4_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P4_FISHERMEN_SPECIFICATION_IMPL =
      P4_SPECIFICATION_BUILDER.withFisheries(P4_FISHERY_COORDINATES).build();

  public static final Tuple2<Array<TileOuterClass.Tile>, Boolean> P5_P6_TERRAIN_TILES =
      Tuple.of(
          Array.fill(6, TileUtils.newTile(FIELD))
              .appendAll(Array.fill(6, TileUtils.newTile(HILL)))
              .appendAll(Array.fill(6, TileUtils.newTile(MOUNTAIN)))
              .appendAll(Array.fill(7, TileUtils.newTile(PASTURE)))
              .appendAll(Array.fill(7, TileUtils.newTile(FOREST))),
          false);
  public static final Tuple2<Array<TileOuterClass.Tile>, Boolean> P5_P6_HARBOR_TILES =
      Base.P5_P6_HARBOR_TILES;
  private static final Map<String, Tuple2<Array<TileOuterClass.Tile>, Boolean>> P5_P6_TILES =
      HashMap.of("terrain", P5_P6_TERRAIN_TILES, "harbor", P5_P6_HARBOR_TILES);

  private static final Array<CoordinateOuterClass.Coordinate> P5_P6_TERRAIN_COORDINATES =
      CoordinateUtils.newCoordinates(
          CoordinateUtils.newCoordinate(3, 1),
          CoordinateUtils.newCoordinate(5, 1),
          CoordinateUtils.newCoordinate(9, 1),
          CoordinateUtils.newCoordinate(13, 1),
          CoordinateUtils.newCoordinate(15, 1),
          CoordinateUtils.newCoordinate(2, 2),
          CoordinateUtils.newCoordinate(4, 2),
          CoordinateUtils.newCoordinate(8, 2),
          CoordinateUtils.newCoordinate(10, 2),
          CoordinateUtils.newCoordinate(14, 2),
          CoordinateUtils.newCoordinate(16, 2),
          CoordinateUtils.newCoordinate(1, 3),
          CoordinateUtils.newCoordinate(3, 3),
          CoordinateUtils.newCoordinate(7, 3),
          CoordinateUtils.newCoordinate(9, 3),
          CoordinateUtils.newCoordinate(15, 3),
          CoordinateUtils.newCoordinate(3, 5),
          CoordinateUtils.newCoordinate(9, 5),
          CoordinateUtils.newCoordinate(11, 5),
          CoordinateUtils.newCoordinate(15, 5),
          CoordinateUtils.newCoordinate(17, 5),
          CoordinateUtils.newCoordinate(2, 6),
          CoordinateUtils.newCoordinate(4, 6),
          CoordinateUtils.newCoordinate(8, 6),
          CoordinateUtils.newCoordinate(10, 6),
          CoordinateUtils.newCoordinate(14, 6),
          CoordinateUtils.newCoordinate(16, 6),
          CoordinateUtils.newCoordinate(3, 7),
          CoordinateUtils.newCoordinate(5, 7),
          CoordinateUtils.newCoordinate(9, 7),
          CoordinateUtils.newCoordinate(13, 7),
          CoordinateUtils.newCoordinate(15, 7));
  private static final Array<CoordinateOuterClass.Coordinate> P5_P6_HARBOR_COORDINATES =
      CoordinateUtils.newCoordinates(
          CoordinateUtils.newCoordinate(6, 0, Edge.Position.BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(11, 1, Edge.Position.BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(0, 2, Edge.Position.RIGHT),
          CoordinateUtils.newCoordinate(12, 2, Edge.Position.TOP_RIGHT),
          CoordinateUtils.newCoordinate(18, 2, Edge.Position.LEFT),
          CoordinateUtils.newCoordinate(16, 4, Edge.Position.BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(5, 5, Edge.Position.BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(13, 5, Edge.Position.BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(2, 8, Edge.Position.TOP_RIGHT),
          CoordinateUtils.newCoordinate(8, 8, Edge.Position.TOP_RIGHT),
          CoordinateUtils.newCoordinate(14, 8, Edge.Position.TOP_RIGHT));
  private static final Array<CoordinateOuterClass.Coordinate> P5_P6_FISHERY_COORDINATES =
      CoordinateUtils.newCoordinates(
          CoordinateUtils.newCoordinate(
              14, 0, Edge.Position.BOTTOM_RIGHT, Edge.Position.BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(3, 1, Edge.Position.LEFT, Edge.Position.TOP_LEFT),
          CoordinateUtils.newCoordinate(17, 1, Edge.Position.BOTTOM_LEFT, Edge.Position.LEFT),
          CoordinateUtils.newCoordinate(16, 6, Edge.Position.RIGHT, Edge.Position.BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(1, 7, Edge.Position.TOP_RIGHT, Edge.Position.RIGHT),
          CoordinateUtils.newCoordinate(4, 8, Edge.Position.TOP_LEFT, Edge.Position.TOP_RIGHT));
  private static final Map<String, Array<CoordinateOuterClass.Coordinate>> P5_P6_COORDINATES =
      HashMap.of("terrain", P5_P6_TERRAIN_COORDINATES, "harbor", P5_P6_HARBOR_COORDINATES);

  private static final Array<ChitOuterClass.Chit> P5_P6_TERRAIN_CHITS =
      Array.fill(2, ChitUtils.CHITS_2)
          .appendAll(Array.fill(3, ChitUtils.CHITS_3))
          .appendAll(Array.fill(4, ChitUtils.CHITS_4))
          .appendAll(Array.fill(4, ChitUtils.CHITS_5))
          .appendAll(Array.fill(4, ChitUtils.CHITS_6))
          .appendAll(Array.fill(3, ChitUtils.CHITS_8))
          .appendAll(Array.fill(4, ChitUtils.CHITS_9))
          .appendAll(Array.fill(4, ChitUtils.CHITS_10))
          .appendAll(Array.fill(2, ChitUtils.CHITS_11))
          .appendAll(Array.fill(2, ChitUtils.CHITS_12));
  private static final Map<String, Array<ChitOuterClass.Chit>> P5_P6_CHITS =
      HashMap.of("terrain", P5_P6_TERRAIN_CHITS);

  private static final SpecificationImpl.Builder P5_P6_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P5_P6_TILES,
          P5_P6_COORDINATES,
          P5_P6_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("terrain"),
              TileMappingUtils.newSelfReferringEntry("harbor")),
          HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("terrain")));

  public static final SpecificationImpl P5_P6_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P5_P6_FISHERMEN_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.withFisheries(P5_P6_FISHERY_COORDINATES).build();

  public static final Tuple2<Array<TileOuterClass.Tile>, Boolean> P7_P8_TERRAIN_TILES =
      Tuple.of(
          Array.fill(8, TileUtils.newTile(HILL))
              .appendAll(Array.fill(8, TileUtils.newTile(MOUNTAIN)))
              .appendAll(Array.fill(8, TileUtils.newTile(PASTURE)))
              .appendAll(Array.fill(9, TileUtils.newTile(FIELD)))
              .appendAll(Array.fill(9, TileUtils.newTile(FOREST))),
          false);
  public static final Tuple2<Array<TileOuterClass.Tile>, Boolean> P7_P8_HARBOR_TILES =
      Base.P7_P8_HARBOR_TILES;
  private static final Map<String, Tuple2<Array<TileOuterClass.Tile>, Boolean>> P7_P8_TILES =
      HashMap.of("terrain", P7_P8_TERRAIN_TILES, "harbor", P7_P8_HARBOR_TILES);

  private static final Array<CoordinateOuterClass.Coordinate> P7_P8_TERRAIN_COORDINATES =
      CoordinateUtils.newCoordinates(
          CoordinateUtils.newCoordinate(3, 1),
          CoordinateUtils.newCoordinate(5, 1),
          CoordinateUtils.newCoordinate(9, 1),
          CoordinateUtils.newCoordinate(15, 1),
          CoordinateUtils.newCoordinate(17, 1),
          CoordinateUtils.newCoordinate(23, 1),
          CoordinateUtils.newCoordinate(25, 1),
          CoordinateUtils.newCoordinate(2, 2),
          CoordinateUtils.newCoordinate(4, 2),
          CoordinateUtils.newCoordinate(8, 2),
          CoordinateUtils.newCoordinate(10, 2),
          CoordinateUtils.newCoordinate(14, 2),
          CoordinateUtils.newCoordinate(16, 2),
          CoordinateUtils.newCoordinate(18, 2),
          CoordinateUtils.newCoordinate(22, 2),
          CoordinateUtils.newCoordinate(24, 2),
          CoordinateUtils.newCoordinate(1, 3),
          CoordinateUtils.newCoordinate(3, 3),
          CoordinateUtils.newCoordinate(7, 3),
          CoordinateUtils.newCoordinate(9, 3),
          CoordinateUtils.newCoordinate(23, 3),
          CoordinateUtils.newCoordinate(3, 5),
          CoordinateUtils.newCoordinate(15, 5),
          CoordinateUtils.newCoordinate(17, 5),
          CoordinateUtils.newCoordinate(23, 5),
          CoordinateUtils.newCoordinate(25, 5),
          CoordinateUtils.newCoordinate(2, 6),
          CoordinateUtils.newCoordinate(4, 6),
          CoordinateUtils.newCoordinate(8, 6),
          CoordinateUtils.newCoordinate(10, 6),
          CoordinateUtils.newCoordinate(12, 6),
          CoordinateUtils.newCoordinate(16, 6),
          CoordinateUtils.newCoordinate(18, 6),
          CoordinateUtils.newCoordinate(22, 6),
          CoordinateUtils.newCoordinate(24, 6),
          CoordinateUtils.newCoordinate(3, 7),
          CoordinateUtils.newCoordinate(5, 7),
          CoordinateUtils.newCoordinate(9, 7),
          CoordinateUtils.newCoordinate(11, 7),
          CoordinateUtils.newCoordinate(17, 7),
          CoordinateUtils.newCoordinate(21, 7),
          CoordinateUtils.newCoordinate(23, 7));
  private static final Array<CoordinateOuterClass.Coordinate> P7_P8_HARBOR_COORDINATES =
      CoordinateUtils.newCoordinates(
          CoordinateUtils.newCoordinate(2, 0, Edge.Position.BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(10, 0, Edge.Position.BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(16, 0, Edge.Position.BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(26, 0, Edge.Position.BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(0, 2, Edge.Position.RIGHT),
          CoordinateUtils.newCoordinate(26, 2, Edge.Position.LEFT),
          CoordinateUtils.newCoordinate(0, 6, Edge.Position.RIGHT),
          CoordinateUtils.newCoordinate(26, 6, Edge.Position.LEFT),
          CoordinateUtils.newCoordinate(2, 8, Edge.Position.TOP_RIGHT),
          CoordinateUtils.newCoordinate(10, 8, Edge.Position.TOP_LEFT),
          CoordinateUtils.newCoordinate(16, 8, Edge.Position.TOP_RIGHT),
          CoordinateUtils.newCoordinate(24, 8, Edge.Position.TOP_LEFT));
  private static final Array<CoordinateOuterClass.Coordinate> P7_P8_FISHERY_COORDINATES =
      CoordinateUtils.newCoordinates(
          CoordinateUtils.newCoordinate(
              24, 0, Edge.Position.BOTTOM_RIGHT, Edge.Position.BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(1, 1, Edge.Position.RIGHT, Edge.Position.BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(15, 1, Edge.Position.LEFT, Edge.Position.TOP_LEFT),
          CoordinateUtils.newCoordinate(25, 1, Edge.Position.RIGHT, Edge.Position.BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(1, 7, Edge.Position.TOP_RIGHT, Edge.Position.RIGHT),
          CoordinateUtils.newCoordinate(11, 7, Edge.Position.RIGHT, Edge.Position.BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(25, 7, Edge.Position.LEFT, Edge.Position.TOP_LEFT),
          CoordinateUtils.newCoordinate(4, 8, Edge.Position.TOP_LEFT, Edge.Position.TOP_RIGHT));
  private static final Map<String, Array<CoordinateOuterClass.Coordinate>> P7_P8_COORDINATES =
      HashMap.of("terrain", P7_P8_TERRAIN_COORDINATES, "harbor", P7_P8_HARBOR_COORDINATES);

  private static final Array<ChitOuterClass.Chit> P7_P8_TERRAIN_CHITS =
      Array.fill(4, ChitUtils.CHITS_2)
          .appendAll(Array.fill(4, ChitUtils.CHITS_3))
          .appendAll(Array.fill(5, ChitUtils.CHITS_4))
          .appendAll(Array.fill(4, ChitUtils.CHITS_5))
          .appendAll(Array.fill(4, ChitUtils.CHITS_6))
          .appendAll(Array.fill(4, ChitUtils.CHITS_8))
          .appendAll(Array.fill(4, ChitUtils.CHITS_9))
          .appendAll(Array.fill(5, ChitUtils.CHITS_10))
          .appendAll(Array.fill(4, ChitUtils.CHITS_11))
          .appendAll(Array.fill(4, ChitUtils.CHITS_12));
  private static final Map<String, Array<ChitOuterClass.Chit>> P7_P8_CHITS =
      HashMap.of("terrain", P7_P8_TERRAIN_CHITS);

  private static final SpecificationImpl.Builder P7_P8_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P7_P8_TILES,
          P7_P8_COORDINATES,
          P7_P8_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("terrain"),
              TileMappingUtils.newSelfReferringEntry("harbor")),
          HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("terrain")));

  public static final SpecificationImpl P7_P8_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P7_P8_FISHERMEN_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.withFisheries(P7_P8_FISHERY_COORDINATES).build();
}
