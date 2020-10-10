package noelyap.setterforcatan.component.scenario.seafarers;

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

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.Array;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import noelyap.setterforcatan.component.SpecificationImpl;
import noelyap.setterforcatan.component.scenario.Base;
import noelyap.setterforcatan.protogen.ChitOuterClass;
import noelyap.setterforcatan.protogen.CoordinateOuterClass;
import noelyap.setterforcatan.protogen.TileOuterClass;
import noelyap.setterforcatan.util.ChitUtils;
import noelyap.setterforcatan.util.CoordinateUtils;
import noelyap.setterforcatan.util.TileMappingUtils;
import noelyap.setterforcatan.util.TileUtils;

// AKA Fog Island
public class Oceania {
  public static final Tuple2<Array<TileOuterClass.Tile>, Boolean> P3_FACE_UP_TILES =
      Tuple.of(
          Array.fill(2, TileUtils.newTile(FIELD))
              .appendAll(Array.fill(2, TileUtils.newTile(HILL)))
              .appendAll(Array.fill(2, TileUtils.newTile(MOUNTAIN)))
              .appendAll(Array.fill(4, TileUtils.newTile(FOREST)))
              .appendAll(Array.fill(4, TileUtils.newTile(PASTURE))),
          false);
  public static final Tuple2<Array<TileOuterClass.Tile>, Boolean> P3_FACE_DOWN_LAND_TILES =
      Tuple.of(
          Array.of(TileUtils.newTile(FOREST), TileUtils.newTile(PASTURE))
              .appendAll(Array.fill(2, TileUtils.newTile(FIELD)))
              .appendAll(Array.fill(2, TileUtils.newTile(GOLD_FIELD)))
              .appendAll(Array.fill(2, TileUtils.newTile(HILL)))
              .appendAll(Array.fill(2, TileUtils.newTile(MOUNTAIN))),
          false);
  public static final Tuple2<Array<TileOuterClass.Tile>, Boolean> P3_FACE_DOWN_SEA_TILES =
      Tuple.of(Array.fill(2, TileUtils.newTile(SEA)), true);
  public static final Tuple2<Array<TileOuterClass.Tile>, Boolean> P3_HARBOR_TILES =
      Tuple.of(
          TileUtils.newTiles(3, GENERIC_HARBOR).appendAll(TileUtils.TWO_FOR_ONE_HARBORS), true);
  public static final Map<String, Tuple2<Array<TileOuterClass.Tile>, Boolean>> P3_TILES =
      HashMap.of(
          "face-up",
          P3_FACE_UP_TILES,
          "face-down-land",
          P3_FACE_DOWN_LAND_TILES,
          "face-down-sea",
          P3_FACE_DOWN_SEA_TILES,
          "harbor",
          P3_HARBOR_TILES);

  public static final Array<CoordinateOuterClass.Coordinate> P3_FACE_UP_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(5, 1),
          CoordinateUtils.newCoordinate(4, 2),
          CoordinateUtils.newCoordinate(6, 2),
          CoordinateUtils.newCoordinate(3, 3),
          CoordinateUtils.newCoordinate(5, 3),
          CoordinateUtils.newCoordinate(2, 4),
          CoordinateUtils.newCoordinate(4, 4),
          CoordinateUtils.newCoordinate(12, 4),
          CoordinateUtils.newCoordinate(11, 5),
          CoordinateUtils.newCoordinate(13, 5),
          CoordinateUtils.newCoordinate(10, 6),
          CoordinateUtils.newCoordinate(12, 6),
          CoordinateUtils.newCoordinate(9, 7),
          CoordinateUtils.newCoordinate(11, 7));
  public static final Array<CoordinateOuterClass.Coordinate> P3_FACE_DOWN_COORDINATES =
      Array.of(
          CoordinateUtils.newFaceDownCoordinate(9, 1),
          CoordinateUtils.newFaceDownCoordinate(11, 1),
          CoordinateUtils.newFaceDownCoordinate(10, 2),
          CoordinateUtils.newFaceDownCoordinate(12, 2),
          CoordinateUtils.newFaceDownCoordinate(9, 3),
          CoordinateUtils.newFaceDownCoordinate(8, 4),
          CoordinateUtils.newFaceDownCoordinate(7, 5),
          CoordinateUtils.newFaceDownCoordinate(2, 6),
          CoordinateUtils.newFaceDownCoordinate(4, 6),
          CoordinateUtils.newFaceDownCoordinate(6, 6),
          CoordinateUtils.newFaceDownCoordinate(3, 7),
          CoordinateUtils.newFaceDownCoordinate(5, 7));
  public static final Array<CoordinateOuterClass.Coordinate> P3_HARBOR_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(3, 1, RIGHT),
          CoordinateUtils.newCoordinate(7, 1, BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(2, 2, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(1, 3, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(14, 4, LEFT),
          CoordinateUtils.newCoordinate(14, 6, TOP_LEFT),
          CoordinateUtils.newCoordinate(13, 7, LEFT),
          CoordinateUtils.newCoordinate(10, 8, TOP_LEFT));
  public static final Array<CoordinateOuterClass.Coordinate> P3_FISHERY_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(4, 2, LEFT, TOP_LEFT),
          CoordinateUtils.newCoordinate(12, 6, RIGHT, BOTTOM_RIGHT));
  public static final Map<String, Array<CoordinateOuterClass.Coordinate>> P3_COORDINATES =
      HashMap.of(
          "face-up",
          P3_FACE_UP_COORDINATES,
          "face-down",
          P3_FACE_DOWN_COORDINATES,
          "harbor",
          P3_HARBOR_COORDINATES);

  public static final Array<ChitOuterClass.Chit> P3_FACE_UP_CHITS =
      Array.of(ChitUtils.CHITS_3, ChitUtils.CHITS_4, ChitUtils.CHITS_10, ChitUtils.CHITS_12)
          .appendAll(Array.fill(2, ChitUtils.CHITS_5))
          .appendAll(Array.fill(2, ChitUtils.CHITS_6))
          .appendAll(Array.fill(2, ChitUtils.CHITS_8))
          .appendAll(Array.fill(2, ChitUtils.CHITS_9))
          .appendAll(Array.fill(2, ChitUtils.CHITS_11));
  public static final Array<ChitOuterClass.Chit> P3_FACE_DOWN_LAND_CHITS =
      Array.of(
              ChitUtils.CHITS_4,
              ChitUtils.CHITS_5,
              ChitUtils.CHITS_6,
              ChitUtils.CHITS_8,
              ChitUtils.CHITS_9,
              ChitUtils.CHITS_10,
              ChitUtils.CHITS_11,
              ChitUtils.CHITS_12)
          .appendAll(Array.fill(2, ChitUtils.CHITS_3));
  public static final Map<String, Array<ChitOuterClass.Chit>> P3_CHITS =
      HashMap.of(
          "face-up", P3_FACE_UP_CHITS,
          "face-down-land", P3_FACE_DOWN_LAND_CHITS);

  private static final SpecificationImpl.Builder P3_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P3_TILES,
          P3_COORDINATES,
          P3_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("face-up"),
              TileMappingUtils.newSelfReferringEntry("harbor"),
              TileMappingUtils.newEntry("face-down", "face-down-land", "face-down-sea")),
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("face-up"),
              TileMappingUtils.newSelfReferringEntry("face-down-land")));

  public static final SpecificationImpl P3_SPECIFICATION_IMPL = P3_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P3_FISHERMEN_SPECIFICATION_IMPL =
      P3_SPECIFICATION_BUILDER.withFisheries(P3_FISHERY_COORDINATES).build();

  public static final Tuple2<Array<TileOuterClass.Tile>, Boolean> P4_FACE_UP_TILES =
      Tuple.of(
          Array.fill(3, TileUtils.newTile(FIELD))
              .appendAll(Array.fill(3, TileUtils.newTile(HILL)))
              .appendAll(Array.fill(3, TileUtils.newTile(MOUNTAIN)))
              .appendAll(Array.fill(4, TileUtils.newTile(FOREST)))
              .appendAll(Array.fill(4, TileUtils.newTile(PASTURE))),
          false);
  public static final Tuple2<Array<TileOuterClass.Tile>, Boolean> P4_FACE_DOWN_LAND_TILES =
      P3_FACE_DOWN_LAND_TILES;
  public static final Tuple2<Array<TileOuterClass.Tile>, Boolean> P4_FACE_DOWN_SEA_TILES =
      P3_FACE_DOWN_SEA_TILES;
  public static final Tuple2<Array<TileOuterClass.Tile>, Boolean> P4_HARBOR_TILES =
      Base.P3_P4_HARBOR_TILES;
  public static final Map<String, Tuple2<Array<TileOuterClass.Tile>, Boolean>> P4_TILES =
      HashMap.of(
          "face-up",
          P4_FACE_UP_TILES,
          "face-down-land",
          P4_FACE_DOWN_LAND_TILES,
          "face-down-sea",
          P4_FACE_DOWN_SEA_TILES,
          "harbor",
          P4_HARBOR_TILES);

  public static final Array<CoordinateOuterClass.Coordinate> P4_FACE_UP_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(3, 1),
          CoordinateUtils.newCoordinate(5, 1),
          CoordinateUtils.newCoordinate(2, 2),
          CoordinateUtils.newCoordinate(4, 2),
          CoordinateUtils.newCoordinate(1, 3),
          CoordinateUtils.newCoordinate(3, 3),
          CoordinateUtils.newCoordinate(13, 3),
          CoordinateUtils.newCoordinate(2, 4),
          CoordinateUtils.newCoordinate(12, 4),
          CoordinateUtils.newCoordinate(11, 5),
          CoordinateUtils.newCoordinate(13, 5),
          CoordinateUtils.newCoordinate(8, 6),
          CoordinateUtils.newCoordinate(10, 6),
          CoordinateUtils.newCoordinate(12, 6),
          CoordinateUtils.newCoordinate(7, 7),
          CoordinateUtils.newCoordinate(9, 7),
          CoordinateUtils.newCoordinate(11, 7));
  public static final Array<CoordinateOuterClass.Coordinate> P4_FACE_DOWN_COORDINATES =
      Array.of(
          CoordinateUtils.newFaceDownCoordinate(9, 1),
          CoordinateUtils.newFaceDownCoordinate(11, 1),
          CoordinateUtils.newFaceDownCoordinate(8, 2),
          CoordinateUtils.newFaceDownCoordinate(10, 2),
          CoordinateUtils.newFaceDownCoordinate(7, 3),
          CoordinateUtils.newFaceDownCoordinate(9, 3),
          CoordinateUtils.newFaceDownCoordinate(6, 4),
          CoordinateUtils.newFaceDownCoordinate(8, 4),
          CoordinateUtils.newFaceDownCoordinate(5, 5),
          CoordinateUtils.newFaceDownCoordinate(2, 6),
          CoordinateUtils.newFaceDownCoordinate(4, 6),
          CoordinateUtils.newFaceDownCoordinate(3, 7));
  public static final Array<CoordinateOuterClass.Coordinate> P4_HARBOR_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(4, 0, BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(1, 1, RIGHT),
          CoordinateUtils.newCoordinate(0, 2, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(0, 4, RIGHT),
          CoordinateUtils.newCoordinate(14, 4, TOP_LEFT),
          CoordinateUtils.newCoordinate(14, 6, TOP_LEFT),
          CoordinateUtils.newCoordinate(13, 7, LEFT),
          CoordinateUtils.newCoordinate(8, 8, TOP_LEFT),
          CoordinateUtils.newCoordinate(10, 8, TOP_RIGHT));
  public static final Array<CoordinateOuterClass.Coordinate> P4_FISHERY_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(2, 2, LEFT, TOP_LEFT),
          CoordinateUtils.newCoordinate(1, 3, BOTTOM_LEFT, LEFT),
          CoordinateUtils.newCoordinate(13, 5, TOP_RIGHT, RIGHT),
          CoordinateUtils.newCoordinate(9, 7, BOTTOM_RIGHT, BOTTOM_LEFT));
  public static final Map<String, Array<CoordinateOuterClass.Coordinate>> P4_COORDINATES =
      HashMap.of(
          "face-up",
          P4_FACE_UP_COORDINATES,
          "face-down",
          P4_FACE_DOWN_COORDINATES,
          "harbor",
          P4_HARBOR_COORDINATES);

  public static final Array<ChitOuterClass.Chit> P4_FACE_UP_CHITS =
      Array.of(ChitUtils.CHITS_2, ChitUtils.CHITS_11, ChitUtils.CHITS_12)
          .appendAll(Array.fill(2, ChitUtils.CHITS_3))
          .appendAll(Array.fill(2, ChitUtils.CHITS_4))
          .appendAll(Array.fill(2, ChitUtils.CHITS_5))
          .appendAll(Array.fill(2, ChitUtils.CHITS_6))
          .appendAll(Array.fill(2, ChitUtils.CHITS_8))
          .appendAll(Array.fill(2, ChitUtils.CHITS_9))
          .appendAll(Array.fill(2, ChitUtils.CHITS_10));
  public static final Array<ChitOuterClass.Chit> P4_FACE_DOWN_LAND_CHITS =
      Array.of(
              ChitUtils.CHITS_3,
              ChitUtils.CHITS_4,
              ChitUtils.CHITS_5,
              ChitUtils.CHITS_6,
              ChitUtils.CHITS_8,
              ChitUtils.CHITS_9,
              ChitUtils.CHITS_10,
              ChitUtils.CHITS_12)
          .appendAll(Array.fill(2, ChitUtils.CHITS_11));
  public static final Map<String, Array<ChitOuterClass.Chit>> P4_CHITS =
      HashMap.of(
          "face-up", P4_FACE_UP_CHITS,
          "face-down-land", P4_FACE_DOWN_LAND_CHITS);

  private static final SpecificationImpl.Builder P4_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P4_TILES,
          P4_COORDINATES,
          P4_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("face-up"),
              TileMappingUtils.newSelfReferringEntry("harbor"),
              TileMappingUtils.newEntry("face-down", "face-down-land", "face-down-sea")),
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("face-up"),
              TileMappingUtils.newSelfReferringEntry("face-down-land")));

  public static final SpecificationImpl P4_SPECIFICATION_IMPL = P4_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P4_FISHERMEN_SPECIFICATION_IMPL =
      P4_SPECIFICATION_BUILDER.withFisheries(P4_FISHERY_COORDINATES).build();

  public static final Tuple2<Array<TileOuterClass.Tile>, Boolean> P5_P6_FACE_UP_TILES =
      Tuple.of(
          Array.fill(4, TileUtils.newTile(MOUNTAIN))
              .appendAll(Array.fill(5, TileUtils.newTile(FIELD)))
              .appendAll(Array.fill(5, TileUtils.newTile(FOREST)))
              .appendAll(Array.fill(5, TileUtils.newTile(HILL)))
              .appendAll(Array.fill(5, TileUtils.newTile(PASTURE))),
          false);
  public static final Tuple2<Array<TileOuterClass.Tile>, Boolean>
      P5_P6_FACE_DOWN_PRODUCING_LAND_TILES =
          Tuple.of(
              Array.fill(2, TileUtils.newTile(FIELD))
                  .appendAll(Array.fill(2, TileUtils.newTile(FOREST)))
                  .appendAll(Array.fill(2, TileUtils.newTile(HILL)))
                  .appendAll(Array.fill(2, TileUtils.newTile(PASTURE)))
                  .appendAll(Array.fill(3, TileUtils.newTile(MOUNTAIN)))
                  .appendAll(Array.fill(3, TileUtils.newTile(GOLD_FIELD))),
              false);
  public static final Tuple2<Array<TileOuterClass.Tile>, Boolean>
      P5_P6_FACE_DOWN_BARREN_LAND_TILES = Tuple.of(Array.of(TileUtils.newTile(DESERT)), true);
  public static final Tuple2<Array<TileOuterClass.Tile>, Boolean> P5_P6_FACE_DOWN_SEA_TILES =
      Tuple.of(Array.fill(3, TileUtils.newTile(SEA)), true);
  public static final Tuple2<Array<TileOuterClass.Tile>, Boolean> P5_P6_HARBOR_TILES =
      Base.P5_P6_HARBOR_TILES;
  public static final Map<String, Tuple2<Array<TileOuterClass.Tile>, Boolean>> P5_P6_TILES =
      HashMap.of(
          "face-up",
          P5_P6_FACE_UP_TILES,
          "face-down-producing-land",
          P5_P6_FACE_DOWN_PRODUCING_LAND_TILES,
          "face-down-barren-land",
          P5_P6_FACE_DOWN_BARREN_LAND_TILES,
          "face-down-sea",
          P5_P6_FACE_DOWN_SEA_TILES,
          "harbor",
          P5_P6_HARBOR_TILES);

  public static final Array<CoordinateOuterClass.Coordinate> P5_P6_FACE_UP_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(3, 1),
          CoordinateUtils.newCoordinate(5, 1),
          CoordinateUtils.newCoordinate(15, 1),
          CoordinateUtils.newCoordinate(2, 2),
          CoordinateUtils.newCoordinate(4, 2),
          CoordinateUtils.newCoordinate(16, 2),
          CoordinateUtils.newCoordinate(1, 3),
          CoordinateUtils.newCoordinate(3, 3),
          CoordinateUtils.newCoordinate(15, 3),
          CoordinateUtils.newCoordinate(17, 3),
          CoordinateUtils.newCoordinate(2, 4),
          CoordinateUtils.newCoordinate(4, 4),
          CoordinateUtils.newCoordinate(14, 4),
          CoordinateUtils.newCoordinate(16, 4),
          CoordinateUtils.newCoordinate(1, 5),
          CoordinateUtils.newCoordinate(3, 5),
          CoordinateUtils.newCoordinate(15, 5),
          CoordinateUtils.newCoordinate(17, 5),
          CoordinateUtils.newCoordinate(2, 6),
          CoordinateUtils.newCoordinate(14, 6),
          CoordinateUtils.newCoordinate(16, 6),
          CoordinateUtils.newCoordinate(3, 7),
          CoordinateUtils.newCoordinate(13, 7),
          CoordinateUtils.newCoordinate(15, 7));
  public static final Array<CoordinateOuterClass.Coordinate> P5_P6_FACE_DOWN_COORDINATES =
      Array.of(
          CoordinateUtils.newFaceDownCoordinate(9, 1),
          CoordinateUtils.newFaceDownCoordinate(11, 1),
          CoordinateUtils.newFaceDownCoordinate(8, 2),
          CoordinateUtils.newFaceDownCoordinate(10, 2),
          CoordinateUtils.newFaceDownCoordinate(12, 2),
          CoordinateUtils.newFaceDownCoordinate(7, 3),
          CoordinateUtils.newFaceDownCoordinate(9, 3),
          CoordinateUtils.newFaceDownCoordinate(11, 3),
          CoordinateUtils.newFaceDownCoordinate(8, 4),
          CoordinateUtils.newFaceDownCoordinate(10, 4),
          CoordinateUtils.newFaceDownCoordinate(7, 5),
          CoordinateUtils.newFaceDownCoordinate(9, 5),
          CoordinateUtils.newFaceDownCoordinate(11, 5),
          CoordinateUtils.newFaceDownCoordinate(6, 6),
          CoordinateUtils.newFaceDownCoordinate(8, 6),
          CoordinateUtils.newFaceDownCoordinate(10, 6),
          CoordinateUtils.newFaceDownCoordinate(7, 7),
          CoordinateUtils.newFaceDownCoordinate(9, 7));
  public static final Array<CoordinateOuterClass.Coordinate> P5_P6_HARBOR_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(0, 2, RIGHT),
          CoordinateUtils.newCoordinate(6, 2, TOP_LEFT),
          CoordinateUtils.newCoordinate(14, 2, RIGHT),
          CoordinateUtils.newCoordinate(18, 2, BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(0, 4, RIGHT),
          CoordinateUtils.newCoordinate(18, 4, TOP_LEFT),
          CoordinateUtils.newCoordinate(5, 5, LEFT),
          CoordinateUtils.newCoordinate(13, 5, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(18, 6, TOP_LEFT),
          CoordinateUtils.newCoordinate(1, 7, TOP_RIGHT),
          CoordinateUtils.newCoordinate(14, 8, TOP_LEFT));
  public static final Array<CoordinateOuterClass.Coordinate> P5_P6_FISHERY_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(1, 1, RIGHT, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(17, 1, BOTTOM_LEFT, LEFT),
          CoordinateUtils.newCoordinate(1, 3, BOTTOM_LEFT, LEFT),
          CoordinateUtils.newCoordinate(17, 5, TOP_RIGHT, RIGHT),
          CoordinateUtils.newCoordinate(0, 6, TOP_RIGHT, RIGHT),
          CoordinateUtils.newCoordinate(17, 7, LEFT, TOP_LEFT));
  public static final Map<String, Array<CoordinateOuterClass.Coordinate>> P5_P6_COORDINATES =
      HashMap.of(
          "face-up",
          P5_P6_FACE_UP_COORDINATES,
          "face-down",
          P5_P6_FACE_DOWN_COORDINATES,
          "harbor",
          P5_P6_HARBOR_COORDINATES);

  public static final Array<ChitOuterClass.Chit> P5_P6_FACE_UP_CHITS =
      Array.of(ChitUtils.CHITS_2)
          .appendAll(Array.fill(2, ChitUtils.CHITS_3))
          .appendAll(Array.fill(3, ChitUtils.CHITS_4))
          .appendAll(Array.fill(2, ChitUtils.CHITS_5))
          .appendAll(Array.fill(3, ChitUtils.CHITS_6))
          .appendAll(Array.fill(3, ChitUtils.CHITS_8))
          .appendAll(Array.fill(3, ChitUtils.CHITS_9))
          .appendAll(Array.fill(3, ChitUtils.CHITS_10))
          .appendAll(Array.fill(2, ChitUtils.CHITS_11))
          .appendAll(Array.fill(2, ChitUtils.CHITS_12));
  public static final Array<ChitOuterClass.Chit> P5_P6_FACE_DOWN_PRODUCING_LAND_CHITS =
      Array.of(
              ChitUtils.CHITS_4,
              ChitUtils.CHITS_6,
              ChitUtils.CHITS_8,
              ChitUtils.CHITS_9,
              ChitUtils.CHITS_10,
              ChitUtils.CHITS_12)
          .appendAll(Array.fill(2, ChitUtils.CHITS_2))
          .appendAll(Array.fill(2, ChitUtils.CHITS_3))
          .appendAll(Array.fill(2, ChitUtils.CHITS_5))
          .appendAll(Array.fill(2, ChitUtils.CHITS_11));
  public static final Map<String, Array<ChitOuterClass.Chit>> P5_P6_CHITS =
      HashMap.of(
          "face-up", P5_P6_FACE_UP_CHITS,
          "face-down-producing-land", P5_P6_FACE_DOWN_PRODUCING_LAND_CHITS);

  private static final SpecificationImpl.Builder P5_P6_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P5_P6_TILES,
          P5_P6_COORDINATES,
          P5_P6_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("face-up"),
              TileMappingUtils.newSelfReferringEntry("harbor"),
              TileMappingUtils.newEntry(
                  "face-down",
                  "face-down-producing-land",
                  "face-down-barren-land",
                  "face-down-sea")),
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("face-up"),
              TileMappingUtils.newSelfReferringEntry("face-down-producing-land")));

  public static final SpecificationImpl P5_P6_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P5_P6_FISHERMEN_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.withFisheries(P5_P6_FISHERY_COORDINATES).build();

  public static final Tuple2<Array<TileOuterClass.Tile>, Boolean> P7_P8_FACE_UP_PRODUCING_TILES =
      Tuple.of(
          Array.fill(5, TileUtils.newTile(MOUNTAIN))
              .appendAll(Array.fill(6, TileUtils.newTile(FOREST)))
              .appendAll(Array.fill(6, TileUtils.newTile(PASTURE)))
              .appendAll(Array.fill(7, TileUtils.newTile(FIELD)))
              .appendAll(Array.fill(7, TileUtils.newTile(HILL))),
          false);
  public static final Tuple2<Array<TileOuterClass.Tile>, Boolean> P7_P8_FACE_UP_BARREN_TILES =
      Base.P7_P8_BARREN_LAND_TILES;
  public static final Tuple2<Array<TileOuterClass.Tile>, Boolean> P7_P8_FACE_DOWN_LAND_TILES =
      Tuple.of(
          Array.fill(2, TileUtils.newTile(FIELD))
              .appendAll(Array.fill(2, TileUtils.newTile(HILL)))
              .appendAll(Array.fill(2, TileUtils.newTile(MOUNTAIN)))
              .appendAll(Array.fill(4, TileUtils.newTile(GOLD_FIELD)))
              .appendAll(Array.fill(5, TileUtils.newTile(FOREST)))
              .appendAll(Array.fill(5, TileUtils.newTile(PASTURE))),
          false);
  public static final Tuple2<Array<TileOuterClass.Tile>, Boolean> P7_P8_FACE_DOWN_SEA_TILES =
      Tuple.of(Array.fill(18, TileUtils.newTile(SEA)), true);
  public static final Tuple2<Array<TileOuterClass.Tile>, Boolean> P7_P8_HARBOR_TILES =
      Base.P7_P8_HARBOR_TILES;
  public static final Map<String, Tuple2<Array<TileOuterClass.Tile>, Boolean>> P7_P8_TILES =
      HashMap.of(
          "face-up-producing",
          P7_P8_FACE_UP_PRODUCING_TILES,
          TileUtils.DESERT_OR_LAKE_NAME,
          P7_P8_FACE_UP_BARREN_TILES,
          "face-down-land",
          P7_P8_FACE_DOWN_LAND_TILES,
          "face-down-sea",
          P7_P8_FACE_DOWN_SEA_TILES,
          "harbor",
          P7_P8_HARBOR_TILES);

  public static final Array<CoordinateOuterClass.Coordinate> P7_P8_FACE_UP_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(11, 1),
          CoordinateUtils.newCoordinate(13, 1),
          CoordinateUtils.newCoordinate(10, 2),
          CoordinateUtils.newCoordinate(12, 2),
          CoordinateUtils.newCoordinate(9, 3),
          CoordinateUtils.newCoordinate(11, 3),
          CoordinateUtils.newCoordinate(2, 4),
          CoordinateUtils.newCoordinate(4, 4),
          CoordinateUtils.newCoordinate(6, 4),
          CoordinateUtils.newCoordinate(8, 4),
          CoordinateUtils.newCoordinate(10, 4),
          CoordinateUtils.newCoordinate(1, 5),
          CoordinateUtils.newCoordinate(3, 5),
          CoordinateUtils.newCoordinate(5, 5),
          CoordinateUtils.newCoordinate(7, 5),
          CoordinateUtils.newCoordinate(9, 5),
          CoordinateUtils.newCoordinate(14, 8),
          CoordinateUtils.newCoordinate(16, 8),
          CoordinateUtils.newCoordinate(18, 8),
          CoordinateUtils.newCoordinate(20, 8),
          CoordinateUtils.newCoordinate(22, 8),
          CoordinateUtils.newCoordinate(13, 9),
          CoordinateUtils.newCoordinate(15, 9),
          CoordinateUtils.newCoordinate(17, 9),
          CoordinateUtils.newCoordinate(19, 9),
          CoordinateUtils.newCoordinate(21, 9),
          CoordinateUtils.newCoordinate(12, 10),
          CoordinateUtils.newCoordinate(14, 10),
          CoordinateUtils.newCoordinate(11, 11),
          CoordinateUtils.newCoordinate(13, 11),
          CoordinateUtils.newCoordinate(10, 12),
          CoordinateUtils.newCoordinate(12, 12));
  public static final Array<CoordinateOuterClass.Coordinate> P7_P8_FACE_DOWN_COORDINATES =
      Array.of(
          CoordinateUtils.newFaceDownCoordinate(16, 2),
          CoordinateUtils.newFaceDownCoordinate(18, 2),
          CoordinateUtils.newFaceDownCoordinate(15, 3),
          CoordinateUtils.newFaceDownCoordinate(17, 3),
          CoordinateUtils.newFaceDownCoordinate(19, 3),
          CoordinateUtils.newFaceDownCoordinate(14, 4),
          CoordinateUtils.newFaceDownCoordinate(16, 4),
          CoordinateUtils.newFaceDownCoordinate(18, 4),
          CoordinateUtils.newFaceDownCoordinate(20, 4),
          CoordinateUtils.newFaceDownCoordinate(13, 5),
          CoordinateUtils.newFaceDownCoordinate(15, 5),
          CoordinateUtils.newFaceDownCoordinate(17, 5),
          CoordinateUtils.newFaceDownCoordinate(19, 5),
          CoordinateUtils.newFaceDownCoordinate(21, 5),
          CoordinateUtils.newFaceDownCoordinate(12, 6),
          CoordinateUtils.newFaceDownCoordinate(14, 6),
          CoordinateUtils.newFaceDownCoordinate(16, 6),
          CoordinateUtils.newFaceDownCoordinate(18, 6),
          CoordinateUtils.newFaceDownCoordinate(20, 6),
          CoordinateUtils.newFaceDownCoordinate(3, 7),
          CoordinateUtils.newFaceDownCoordinate(5, 7),
          CoordinateUtils.newFaceDownCoordinate(7, 7),
          CoordinateUtils.newFaceDownCoordinate(9, 7),
          CoordinateUtils.newFaceDownCoordinate(11, 7),
          CoordinateUtils.newFaceDownCoordinate(2, 8),
          CoordinateUtils.newFaceDownCoordinate(4, 8),
          CoordinateUtils.newFaceDownCoordinate(6, 8),
          CoordinateUtils.newFaceDownCoordinate(8, 8),
          CoordinateUtils.newFaceDownCoordinate(10, 8),
          CoordinateUtils.newFaceDownCoordinate(3, 9),
          CoordinateUtils.newFaceDownCoordinate(5, 9),
          CoordinateUtils.newFaceDownCoordinate(7, 9),
          CoordinateUtils.newFaceDownCoordinate(9, 9),
          CoordinateUtils.newFaceDownCoordinate(4, 10),
          CoordinateUtils.newFaceDownCoordinate(6, 10),
          CoordinateUtils.newFaceDownCoordinate(8, 10),
          CoordinateUtils.newFaceDownCoordinate(5, 11),
          CoordinateUtils.newFaceDownCoordinate(7, 11));
  public static final Array<CoordinateOuterClass.Coordinate> P7_P8_HARBOR_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(14, 0, BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(10, 0, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(8, 2, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(3, 3, BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(7, 3, BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(0, 4, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(23, 9, TOP_LEFT),
          CoordinateUtils.newCoordinate(16, 10, TOP_RIGHT),
          CoordinateUtils.newCoordinate(20, 10, TOP_RIGHT),
          CoordinateUtils.newCoordinate(15, 11, TOP_LEFT),
          CoordinateUtils.newCoordinate(9, 13, TOP_RIGHT),
          CoordinateUtils.newCoordinate(13, 13, TOP_LEFT));
  public static final Array<CoordinateOuterClass.Coordinate> P7_P8_FISHERY_COORDINATES =
      Array.of(
          CoordinateUtils.newCoordinate(12, 0, BOTTOM_RIGHT, BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(10, 2, LEFT, TOP_LEFT),
          CoordinateUtils.newCoordinate(2, 4, LEFT, TOP_LEFT),
          CoordinateUtils.newCoordinate(5, 3, BOTTOM_RIGHT, BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(21, 9, RIGHT, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(18, 10, TOP_LEFT, TOP_RIGHT),
          CoordinateUtils.newCoordinate(13, 11, RIGHT, BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(11, 13, TOP_LEFT, TOP_RIGHT));
  public static final Map<String, Array<CoordinateOuterClass.Coordinate>> P7_P8_COORDINATES =
      HashMap.of(
          "face-up",
          P7_P8_FACE_UP_COORDINATES,
          "face-down",
          P7_P8_FACE_DOWN_COORDINATES,
          "harbor",
          P7_P8_HARBOR_COORDINATES);

  public static final Array<ChitOuterClass.Chit> P7_P8_FACE_UP_PRODUCING_CHITS =
      Array.of(ChitUtils.CHITS_2_12)
          .appendAll(Array.fill(3, ChitUtils.CHITS_2))
          .appendAll(Array.fill(3, ChitUtils.CHITS_3))
          .appendAll(Array.fill(3, ChitUtils.CHITS_4))
          .appendAll(Array.fill(3, ChitUtils.CHITS_5))
          .appendAll(Array.fill(3, ChitUtils.CHITS_6))
          .appendAll(Array.fill(3, ChitUtils.CHITS_8))
          .appendAll(Array.fill(3, ChitUtils.CHITS_9))
          .appendAll(Array.fill(3, ChitUtils.CHITS_10))
          .appendAll(Array.fill(3, ChitUtils.CHITS_11))
          .appendAll(Array.fill(3, ChitUtils.CHITS_12));
  public static final Array<ChitOuterClass.Chit> P7_P8_FACE_DOWN_LAND_CHITS =
      Array.of(ChitUtils.CHITS_2, ChitUtils.CHITS_12)
          .appendAll(Array.fill(2, ChitUtils.CHITS_3))
          .appendAll(Array.fill(3, ChitUtils.CHITS_4))
          .appendAll(Array.fill(2, ChitUtils.CHITS_5))
          .appendAll(Array.fill(2, ChitUtils.CHITS_6))
          .appendAll(Array.fill(2, ChitUtils.CHITS_8))
          .appendAll(Array.fill(2, ChitUtils.CHITS_9))
          .appendAll(Array.fill(3, ChitUtils.CHITS_10))
          .appendAll(Array.fill(2, ChitUtils.CHITS_11));
  public static final Map<String, Array<ChitOuterClass.Chit>> P7_P8_CHITS =
      HashMap.of(
          "face-up-producing", P7_P8_FACE_UP_PRODUCING_CHITS,
          "face-down-land", P7_P8_FACE_DOWN_LAND_CHITS);

  private static final SpecificationImpl.Builder P7_P8_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P7_P8_TILES,
          P7_P8_COORDINATES,
          P7_P8_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("face-up"),
              TileMappingUtils.newSelfReferringEntry("harbor"),
              TileMappingUtils.newEntry(
                  "face-up", "face-up-producing", TileUtils.DESERT_OR_LAKE_NAME),
              TileMappingUtils.newEntry("face-down", "face-down-land", "face-down-sea")),
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("face-up-producing"),
              TileMappingUtils.newSelfReferringEntry("face-down-land")));

  public static final SpecificationImpl P7_P8_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P7_P8_FISHERMEN_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.withFisheries(P7_P8_FISHERY_COORDINATES).build();
}
