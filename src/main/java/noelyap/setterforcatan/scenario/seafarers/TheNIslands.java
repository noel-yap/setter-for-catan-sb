package noelyap.setterforcatan.scenario.seafarers;

import static noelyap.setterforcatan.component.Chits.CHITS_2_12;
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
import static noelyap.setterforcatan.component.Tiles.FIELD;
import static noelyap.setterforcatan.component.Tiles.FOREST;
import static noelyap.setterforcatan.component.Tiles.HILL;
import static noelyap.setterforcatan.component.Tiles.MOUNTAIN;
import static noelyap.setterforcatan.component.Tiles.PASTURE;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge.Position.BOTTOM_LEFT;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge.Position.BOTTOM_RIGHT;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge.Position.LEFT;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge.Position.RIGHT;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge.Position.TOP_LEFT;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge.Position.TOP_RIGHT;

import io.vavr.collection.Array;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import noelyap.setterforcatan.component.Coordinates;
import noelyap.setterforcatan.component.SpecificationImpl;
import noelyap.setterforcatan.protogen.ChitOuterClass.Chit;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Coordinate;
import noelyap.setterforcatan.protogen.TileOuterClass.Tile;
import noelyap.setterforcatan.scenario.Base;
import noelyap.setterforcatan.util.TileMappingUtils;

public class TheNIslands {
  private static final Array<Tile> P3_ISLAND_TILES =
      Base.P3_P4_PRODUCING_TILES.append(HILL).append(MOUNTAIN);
  private static final Array<Tile> P3_HARBOR_TILES = Base.P3_P4_HARBOR_TILES;
  private static final Map<String, Array<Tile>> P3_TILES =
      HashMap.of("island", P3_ISLAND_TILES, "harbor", P3_HARBOR_TILES);

  private static final Array<Coordinate> P3_ISLAND_COORDINATES =
      Array.of(
          Coordinates.of(10, 0),
          Coordinates.of(3, 1),
          Coordinates.of(5, 1),
          Coordinates.of(9, 1),
          Coordinates.of(11, 1),
          Coordinates.of(2, 2),
          Coordinates.of(4, 2),
          Coordinates.of(8, 2),
          Coordinates.of(10, 2),
          Coordinates.of(12, 2),
          Coordinates.of(4, 4),
          Coordinates.of(6, 4),
          Coordinates.of(10, 4),
          Coordinates.of(12, 4),
          Coordinates.of(3, 5),
          Coordinates.of(5, 5),
          Coordinates.of(9, 5),
          Coordinates.of(11, 5),
          Coordinates.of(4, 6),
          Coordinates.of(6, 6));
  private static final Array<Coordinate> P3_HARBOR_COORDINATES =
      Array.of(
          Coordinates.of(8, 0, BOTTOM_RIGHT),
          Coordinates.of(1, 1, BOTTOM_RIGHT),
          Coordinates.of(13, 1, BOTTOM_LEFT),
          Coordinates.of(6, 2, TOP_LEFT),
          Coordinates.of(5, 3, BOTTOM_LEFT),
          Coordinates.of(11, 3, TOP_RIGHT),
          Coordinates.of(8, 4, BOTTOM_RIGHT),
          Coordinates.of(13, 5, TOP_LEFT),
          Coordinates.of(2, 6, TOP_RIGHT));
  private static final Array<Coordinate> P3_FISHERY_COORDINATES =
      Array.of(
          Coordinates.of(4, 0, BOTTOM_RIGHT, BOTTOM_LEFT),
          Coordinates.of(3, 3, TOP_LEFT, TOP_RIGHT),
          Coordinates.of(11, 3, BOTTOM_RIGHT, BOTTOM_LEFT),
          Coordinates.of(10, 6, TOP_LEFT, TOP_RIGHT));
  private static final Map<String, Array<Coordinate>> P3_COORDINATES =
      HashMap.of("island", P3_ISLAND_COORDINATES, "harbor", P3_HARBOR_COORDINATES);

  private static final Array<Chit> P3_ISLAND_CHITS =
      Base.P3_P4_PRODUCING_CHITS.appendAll(Array.of(CHIT_5, CHIT_9));
  private static final Map<String, Array<Chit>> P3_CHITS = HashMap.of("island", P3_ISLAND_CHITS);

  private static final SpecificationImpl.Builder P3_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P3_TILES,
          P3_COORDINATES,
          P3_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("island"),
              TileMappingUtils.newSelfReferringEntry("harbor")),
          HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("island")));

  public static final SpecificationImpl P3_SPECIFICATION_IMPL = P3_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P3_FISHERMEN_SPECIFICATION_IMPL =
      P3_SPECIFICATION_BUILDER.withFisheries(P3_FISHERY_COORDINATES).build();

  private static final Array<Tile> P4_ISLAND_TILES =
      P3_ISLAND_TILES.append(FIELD).append(PASTURE).append(FOREST);
  private static final Array<Tile> P4_HARBOR_TILES = Base.P3_P4_HARBOR_TILES;
  private static final Map<String, Array<Tile>> P4_TILES =
      HashMap.of("island", P4_ISLAND_TILES, "harbor", P4_HARBOR_TILES);

  private static final Array<Coordinate> P4_ISLAND_COORDINATES =
      Array.of(
          Coordinates.of(4, 0),
          Coordinates.of(8, 0),
          Coordinates.of(10, 0),
          Coordinates.of(3, 1),
          Coordinates.of(7, 1),
          Coordinates.of(9, 1),
          Coordinates.of(11, 1),
          Coordinates.of(2, 2),
          Coordinates.of(4, 2),
          Coordinates.of(10, 2),
          Coordinates.of(12, 2),
          Coordinates.of(7, 3),
          Coordinates.of(4, 4),
          Coordinates.of(6, 4),
          Coordinates.of(10, 4),
          Coordinates.of(12, 4),
          Coordinates.of(3, 5),
          Coordinates.of(5, 5),
          Coordinates.of(7, 5),
          Coordinates.of(11, 5),
          Coordinates.of(4, 6),
          Coordinates.of(6, 6),
          Coordinates.of(10, 6));
  private static final Array<Coordinate> P4_HARBOR_COORDINATES =
      Array.of(
          Coordinates.of(12, 0, LEFT),
          Coordinates.of(1, 1, BOTTOM_RIGHT),
          Coordinates.of(13, 1, BOTTOM_LEFT),
          Coordinates.of(8, 2, TOP_RIGHT),
          Coordinates.of(3, 3, TOP_LEFT),
          Coordinates.of(11, 3, BOTTOM_RIGHT),
          Coordinates.of(2, 4, BOTTOM_RIGHT),
          Coordinates.of(8, 4, TOP_LEFT),
          Coordinates.of(13, 5, LEFT));
  private static final Array<Coordinate> P4_FISHERY_COORDINATES =
      Array.of(
          Coordinates.of(2, 0, RIGHT, BOTTOM_RIGHT),
          Coordinates.of(2, 2, BOTTOM_LEFT, LEFT),
          Coordinates.of(4, 2, TOP_RIGHT, RIGHT),
          Coordinates.of(10, 4, BOTTOM_LEFT, LEFT),
          Coordinates.of(12, 4, TOP_RIGHT, RIGHT),
          Coordinates.of(12, 6, LEFT, TOP_LEFT));
  private static final Map<String, Array<Coordinate>> P4_COORDINATES =
      HashMap.of("island", P4_ISLAND_COORDINATES, "harbor", P4_HARBOR_COORDINATES);

  private static final Array<Chit> P4_ISLAND_CHITS =
      P3_ISLAND_CHITS.appendAll(Array.of(CHIT_4, CHIT_10, CHITS_2_12));
  private static final Map<String, Array<Chit>> P4_CHITS = HashMap.of("island", P4_ISLAND_CHITS);

  private static final SpecificationImpl.Builder P4_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P4_TILES,
          P4_COORDINATES,
          P4_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("island"),
              TileMappingUtils.newSelfReferringEntry("harbor")),
          HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("island")));

  public static final SpecificationImpl P4_SPECIFICATION_IMPL = P4_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P4_FISHERMEN_SPECIFICATION_IMPL =
      P4_SPECIFICATION_BUILDER.withFisheries(P4_FISHERY_COORDINATES).build();

  private static final Array<Tile> P5_P6_ISLAND_TILES =
      Array.fill(6, FIELD)
          .appendAll(Array.fill(6, HILL))
          .appendAll(Array.fill(6, MOUNTAIN))
          .appendAll(Array.fill(7, PASTURE))
          .appendAll(Array.fill(7, FOREST));
  private static final Array<Tile> P5_P6_HARBOR_TILES = Base.P5_P6_HARBOR_TILES;
  private static final Map<String, Array<Tile>> P5_P6_TILES =
      HashMap.of("island", P5_P6_ISLAND_TILES, "harbor", P5_P6_HARBOR_TILES);

  private static final Array<Coordinate> P5_P6_ISLAND_COORDINATES =
      Array.of(
          Coordinates.of(3, 1),
          Coordinates.of(5, 1),
          Coordinates.of(9, 1),
          Coordinates.of(13, 1),
          Coordinates.of(15, 1),
          Coordinates.of(2, 2),
          Coordinates.of(4, 2),
          Coordinates.of(8, 2),
          Coordinates.of(10, 2),
          Coordinates.of(14, 2),
          Coordinates.of(16, 2),
          Coordinates.of(1, 3),
          Coordinates.of(3, 3),
          Coordinates.of(7, 3),
          Coordinates.of(9, 3),
          Coordinates.of(15, 3),
          Coordinates.of(3, 5),
          Coordinates.of(9, 5),
          Coordinates.of(11, 5),
          Coordinates.of(15, 5),
          Coordinates.of(17, 5),
          Coordinates.of(2, 6),
          Coordinates.of(4, 6),
          Coordinates.of(8, 6),
          Coordinates.of(10, 6),
          Coordinates.of(14, 6),
          Coordinates.of(16, 6),
          Coordinates.of(3, 7),
          Coordinates.of(5, 7),
          Coordinates.of(9, 7),
          Coordinates.of(13, 7),
          Coordinates.of(15, 7));
  private static final Array<Coordinate> P5_P6_HARBOR_COORDINATES =
      Array.of(
          Coordinates.of(6, 0, BOTTOM_LEFT),
          Coordinates.of(11, 1, BOTTOM_LEFT),
          Coordinates.of(0, 2, RIGHT),
          Coordinates.of(12, 2, TOP_RIGHT),
          Coordinates.of(18, 2, LEFT),
          Coordinates.of(16, 4, BOTTOM_LEFT),
          Coordinates.of(5, 5, BOTTOM_LEFT),
          Coordinates.of(13, 5, BOTTOM_RIGHT),
          Coordinates.of(2, 8, TOP_RIGHT),
          Coordinates.of(8, 8, TOP_RIGHT),
          Coordinates.of(14, 8, TOP_RIGHT));
  private static final Array<Coordinate> P5_P6_FISHERY_COORDINATES =
      Array.of(
          Coordinates.of(14, 0, BOTTOM_RIGHT, BOTTOM_LEFT),
          Coordinates.of(3, 1, LEFT, TOP_LEFT),
          Coordinates.of(17, 1, BOTTOM_LEFT, LEFT),
          Coordinates.of(16, 6, RIGHT, BOTTOM_RIGHT),
          Coordinates.of(1, 7, TOP_RIGHT, RIGHT),
          Coordinates.of(4, 8, TOP_LEFT, TOP_RIGHT));
  private static final Map<String, Array<Coordinate>> P5_P6_COORDINATES =
      HashMap.of("island", P5_P6_ISLAND_COORDINATES, "harbor", P5_P6_HARBOR_COORDINATES);

  private static final Array<Chit> P5_P6_ISLAND_CHITS =
      Array.fill(2, CHIT_2)
          .appendAll(Array.fill(3, CHIT_3))
          .appendAll(Array.fill(4, CHIT_4))
          .appendAll(Array.fill(4, CHIT_5))
          .appendAll(Array.fill(4, CHIT_6))
          .appendAll(Array.fill(3, CHIT_8))
          .appendAll(Array.fill(4, CHIT_9))
          .appendAll(Array.fill(4, CHIT_10))
          .appendAll(Array.fill(2, CHIT_11))
          .appendAll(Array.fill(2, CHIT_12));
  private static final Map<String, Array<Chit>> P5_P6_CHITS =
      HashMap.of("island", P5_P6_ISLAND_CHITS);

  private static final SpecificationImpl.Builder P5_P6_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P5_P6_TILES,
          P5_P6_COORDINATES,
          P5_P6_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("island"),
              TileMappingUtils.newSelfReferringEntry("harbor")),
          HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("island")));

  public static final SpecificationImpl P5_P6_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P5_P6_FISHERMEN_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.withFisheries(P5_P6_FISHERY_COORDINATES).build();

  private static final Array<Tile> P7_P8_ISLAND_TILES =
      Array.fill(8, HILL)
          .appendAll(Array.fill(8, MOUNTAIN))
          .appendAll(Array.fill(8, PASTURE))
          .appendAll(Array.fill(9, FIELD))
          .appendAll(Array.fill(9, FOREST));
  private static final Array<Tile> P7_P8_HARBOR_TILES = Base.P7_P8_HARBOR_TILES;
  private static final Map<String, Array<Tile>> P7_P8_TILES =
      HashMap.of("island", P7_P8_ISLAND_TILES, "harbor", P7_P8_HARBOR_TILES);

  private static final Array<Coordinate> P7_P8_ISLAND_COORDINATES =
      Array.of(
          Coordinates.of(3, 1),
          Coordinates.of(5, 1),
          Coordinates.of(9, 1),
          Coordinates.of(15, 1),
          Coordinates.of(17, 1),
          Coordinates.of(23, 1),
          Coordinates.of(25, 1),
          Coordinates.of(2, 2),
          Coordinates.of(4, 2),
          Coordinates.of(8, 2),
          Coordinates.of(10, 2),
          Coordinates.of(14, 2),
          Coordinates.of(16, 2),
          Coordinates.of(18, 2),
          Coordinates.of(22, 2),
          Coordinates.of(24, 2),
          Coordinates.of(1, 3),
          Coordinates.of(3, 3),
          Coordinates.of(7, 3),
          Coordinates.of(9, 3),
          Coordinates.of(23, 3),
          Coordinates.of(3, 5),
          Coordinates.of(15, 5),
          Coordinates.of(17, 5),
          Coordinates.of(23, 5),
          Coordinates.of(25, 5),
          Coordinates.of(2, 6),
          Coordinates.of(4, 6),
          Coordinates.of(8, 6),
          Coordinates.of(10, 6),
          Coordinates.of(12, 6),
          Coordinates.of(16, 6),
          Coordinates.of(18, 6),
          Coordinates.of(22, 6),
          Coordinates.of(24, 6),
          Coordinates.of(3, 7),
          Coordinates.of(5, 7),
          Coordinates.of(9, 7),
          Coordinates.of(11, 7),
          Coordinates.of(17, 7),
          Coordinates.of(21, 7),
          Coordinates.of(23, 7));
  private static final Array<Coordinate> P7_P8_HARBOR_COORDINATES =
      Array.of(
          Coordinates.of(2, 0, BOTTOM_RIGHT),
          Coordinates.of(10, 0, BOTTOM_LEFT),
          Coordinates.of(16, 0, BOTTOM_RIGHT),
          Coordinates.of(26, 0, BOTTOM_LEFT),
          Coordinates.of(0, 2, RIGHT),
          Coordinates.of(26, 2, LEFT),
          Coordinates.of(0, 6, RIGHT),
          Coordinates.of(26, 6, LEFT),
          Coordinates.of(2, 8, TOP_RIGHT),
          Coordinates.of(10, 8, TOP_LEFT),
          Coordinates.of(16, 8, TOP_RIGHT),
          Coordinates.of(24, 8, TOP_LEFT));
  private static final Array<Coordinate> P7_P8_FISHERY_COORDINATES =
      Array.of(
          Coordinates.of(24, 0, BOTTOM_RIGHT, BOTTOM_LEFT),
          Coordinates.of(1, 1, RIGHT, BOTTOM_RIGHT),
          Coordinates.of(15, 1, LEFT, TOP_LEFT),
          Coordinates.of(25, 1, RIGHT, BOTTOM_RIGHT),
          Coordinates.of(1, 7, TOP_RIGHT, RIGHT),
          Coordinates.of(11, 7, RIGHT, BOTTOM_RIGHT),
          Coordinates.of(25, 7, LEFT, TOP_LEFT),
          Coordinates.of(4, 8, TOP_LEFT, TOP_RIGHT));
  private static final Map<String, Array<Coordinate>> P7_P8_COORDINATES =
      HashMap.of("island", P7_P8_ISLAND_COORDINATES, "harbor", P7_P8_HARBOR_COORDINATES);

  private static final Array<Chit> P7_P8_ISLAND_CHITS =
      Array.fill(4, CHIT_2)
          .appendAll(Array.fill(4, CHIT_3))
          .appendAll(Array.fill(5, CHIT_4))
          .appendAll(Array.fill(4, CHIT_5))
          .appendAll(Array.fill(4, CHIT_6))
          .appendAll(Array.fill(4, CHIT_8))
          .appendAll(Array.fill(4, CHIT_9))
          .appendAll(Array.fill(5, CHIT_10))
          .appendAll(Array.fill(4, CHIT_11))
          .appendAll(Array.fill(4, CHIT_12));
  private static final Map<String, Array<Chit>> P7_P8_CHITS =
      HashMap.of("island", P7_P8_ISLAND_CHITS);

  private static final SpecificationImpl.Builder P7_P8_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P7_P8_TILES,
          P7_P8_COORDINATES,
          P7_P8_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("island"),
              TileMappingUtils.newSelfReferringEntry("harbor")),
          HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("island")));

  public static final SpecificationImpl P7_P8_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P7_P8_FISHERMEN_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.withFisheries(P7_P8_FISHERY_COORDINATES).build();
}
