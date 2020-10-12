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
import static noelyap.setterforcatan.component.Tiles.DESERT;
import static noelyap.setterforcatan.component.Tiles.DESERT_OR_LAKE_NAME;
import static noelyap.setterforcatan.component.Tiles.FIELD;
import static noelyap.setterforcatan.component.Tiles.FOREST;
import static noelyap.setterforcatan.component.Tiles.GENERIC_HARBOR;
import static noelyap.setterforcatan.component.Tiles.GOLD_FIELD;
import static noelyap.setterforcatan.component.Tiles.HILL;
import static noelyap.setterforcatan.component.Tiles.MOUNTAIN;
import static noelyap.setterforcatan.component.Tiles.PASTURE;
import static noelyap.setterforcatan.component.Tiles.SEA;
import static noelyap.setterforcatan.component.Tiles.TWO_FOR_ONE_HARBORS;
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

// AKA Fog Island
public class Oceania {
  private static final Array<Tile> P3_FACE_UP_LAND_TILES =
      Array.fill(2, FIELD)
          .appendAll(Array.fill(2, HILL))
          .appendAll(Array.fill(2, MOUNTAIN))
          .appendAll(Array.fill(4, FOREST))
          .appendAll(Array.fill(4, PASTURE));
  private static final Array<Tile> P3_FACE_UP_HARBOR_TILES =
      Array.fill(3, GENERIC_HARBOR).appendAll(TWO_FOR_ONE_HARBORS);
  private static final Array<Tile> P3_FACE_DOWN_LAND_TILES =
      Array.of(FOREST, PASTURE)
          .appendAll(Array.fill(2, FIELD))
          .appendAll(Array.fill(2, GOLD_FIELD))
          .appendAll(Array.fill(2, HILL))
          .appendAll(Array.fill(2, MOUNTAIN));
  private static final Array<Tile> P3_FACE_DOWN_SEA_TILES = Array.fill(2, SEA);
  private static final Map<String, Array<Tile>> P3_TILES =
      HashMap.of(
          "face-up-land",
          P3_FACE_UP_LAND_TILES,
          "face-up-harbor",
          P3_FACE_UP_HARBOR_TILES,
          "face-down-land",
          P3_FACE_DOWN_LAND_TILES,
          "face-down-sea",
          P3_FACE_DOWN_SEA_TILES);

  private static final Array<Coordinate> P3_FACE_UP_LAND_COORDINATES =
      Array.of(
          Coordinates.of(5, 1),
          Coordinates.of(4, 2),
          Coordinates.of(6, 2),
          Coordinates.of(3, 3),
          Coordinates.of(5, 3),
          Coordinates.of(2, 4),
          Coordinates.of(4, 4),
          Coordinates.of(12, 4),
          Coordinates.of(11, 5),
          Coordinates.of(13, 5),
          Coordinates.of(10, 6),
          Coordinates.of(12, 6),
          Coordinates.of(9, 7),
          Coordinates.of(11, 7));
  private static final Array<Coordinate> P3_FACE_UP_HARBOR_COORDINATES =
      Array.of(
          Coordinates.of(3, 1, RIGHT),
          Coordinates.of(7, 1, BOTTOM_LEFT),
          Coordinates.of(2, 2, BOTTOM_RIGHT),
          Coordinates.of(1, 3, BOTTOM_RIGHT),
          Coordinates.of(14, 4, LEFT),
          Coordinates.of(14, 6, TOP_LEFT),
          Coordinates.of(13, 7, LEFT),
          Coordinates.of(10, 8, TOP_LEFT));
  private static final Array<Coordinate> P3_FACE_UP_FISHERY_COORDINATES =
      Array.of(Coordinates.of(4, 2, LEFT, TOP_LEFT), Coordinates.of(12, 6, RIGHT, BOTTOM_RIGHT));
  private static final Array<Coordinate> P3_FACE_DOWN_COORDINATES =
      Array.of(
          Coordinates.faceDownOf(9, 1),
          Coordinates.faceDownOf(11, 1),
          Coordinates.faceDownOf(10, 2),
          Coordinates.faceDownOf(12, 2),
          Coordinates.faceDownOf(9, 3),
          Coordinates.faceDownOf(8, 4),
          Coordinates.faceDownOf(7, 5),
          Coordinates.faceDownOf(2, 6),
          Coordinates.faceDownOf(4, 6),
          Coordinates.faceDownOf(6, 6),
          Coordinates.faceDownOf(3, 7),
          Coordinates.faceDownOf(5, 7));
  private static final Map<String, Array<Coordinate>> P3_COORDINATES =
      HashMap.of(
          "face-up-land",
          P3_FACE_UP_LAND_COORDINATES,
          "face-up-harbor",
          P3_FACE_UP_HARBOR_COORDINATES,
          "face-down",
          P3_FACE_DOWN_COORDINATES);

  private static final Array<Chit> P3_FACE_UP_LAND_CHITS =
      Array.of(CHIT_3, CHIT_4, CHIT_10, CHIT_12)
          .appendAll(Array.fill(2, CHIT_5))
          .appendAll(Array.fill(2, CHIT_6))
          .appendAll(Array.fill(2, CHIT_8))
          .appendAll(Array.fill(2, CHIT_9))
          .appendAll(Array.fill(2, CHIT_11));
  private static final Array<Chit> P3_FACE_DOWN_LAND_CHITS =
      Array.of(CHIT_4, CHIT_5, CHIT_6, CHIT_8, CHIT_9, CHIT_10, CHIT_11, CHIT_12)
          .appendAll(Array.fill(2, CHIT_3));
  private static final Map<String, Array<Chit>> P3_CHITS =
      HashMap.of(
          "face-up-land", P3_FACE_UP_LAND_CHITS,
          "face-down-land", P3_FACE_DOWN_LAND_CHITS);

  private static final SpecificationImpl.Builder P3_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P3_TILES,
          P3_COORDINATES,
          P3_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("face-up-land"),
              TileMappingUtils.newSelfReferringEntry("face-up-harbor"),
              TileMappingUtils.newEntry("face-down", "face-down-land", "face-down-sea")),
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("face-up-land"),
              TileMappingUtils.newSelfReferringEntry("face-down-land")));

  public static final SpecificationImpl P3_SPECIFICATION_IMPL = P3_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P3_FISHERMEN_SPECIFICATION_IMPL =
      P3_SPECIFICATION_BUILDER.withFisheries(P3_FACE_UP_FISHERY_COORDINATES).build();

  private static final Array<Tile> P4_FACE_UP_LAND_TILES =
      Array.fill(3, FIELD)
          .appendAll(Array.fill(3, HILL))
          .appendAll(Array.fill(3, MOUNTAIN))
          .appendAll(Array.fill(4, FOREST))
          .appendAll(Array.fill(4, PASTURE));
  private static final Array<Tile> P4_FACE_UP_HARBOR_TILES = Base.P3_P4_HARBOR_TILES;
  private static final Array<Tile> P4_FACE_DOWN_LAND_TILES = P3_FACE_DOWN_LAND_TILES;
  private static final Array<Tile> P4_FACE_DOWN_SEA_TILES = P3_FACE_DOWN_SEA_TILES;
  private static final Map<String, Array<Tile>> P4_TILES =
      HashMap.of(
          "face-up-land",
          P4_FACE_UP_LAND_TILES,
          "face-up-harbor",
          P4_FACE_UP_HARBOR_TILES,
          "face-down-land",
          P4_FACE_DOWN_LAND_TILES,
          "face-down-sea",
          P4_FACE_DOWN_SEA_TILES);

  private static final Array<Coordinate> P4_FACE_UP_LAND_COORDINATES =
      Array.of(
          Coordinates.of(3, 1),
          Coordinates.of(5, 1),
          Coordinates.of(2, 2),
          Coordinates.of(4, 2),
          Coordinates.of(1, 3),
          Coordinates.of(3, 3),
          Coordinates.of(13, 3),
          Coordinates.of(2, 4),
          Coordinates.of(12, 4),
          Coordinates.of(11, 5),
          Coordinates.of(13, 5),
          Coordinates.of(8, 6),
          Coordinates.of(10, 6),
          Coordinates.of(12, 6),
          Coordinates.of(7, 7),
          Coordinates.of(9, 7),
          Coordinates.of(11, 7));
  private static final Array<Coordinate> P4_FACE_UP_HARBOR_COORDINATES =
      Array.of(
          Coordinates.of(4, 0, BOTTOM_LEFT),
          Coordinates.of(1, 1, RIGHT),
          Coordinates.of(0, 2, BOTTOM_RIGHT),
          Coordinates.of(0, 4, RIGHT),
          Coordinates.of(14, 4, TOP_LEFT),
          Coordinates.of(14, 6, TOP_LEFT),
          Coordinates.of(13, 7, LEFT),
          Coordinates.of(8, 8, TOP_LEFT),
          Coordinates.of(10, 8, TOP_RIGHT));
  private static final Array<Coordinate> P4_FACE_UP_FISHERY_COORDINATES =
      Array.of(
          Coordinates.of(2, 2, LEFT, TOP_LEFT),
          Coordinates.of(1, 3, BOTTOM_LEFT, LEFT),
          Coordinates.of(13, 5, TOP_RIGHT, RIGHT),
          Coordinates.of(9, 7, BOTTOM_RIGHT, BOTTOM_LEFT));
  private static final Array<Coordinate> P4_FACE_DOWN_COORDINATES =
      Array.of(
          Coordinates.faceDownOf(9, 1),
          Coordinates.faceDownOf(11, 1),
          Coordinates.faceDownOf(8, 2),
          Coordinates.faceDownOf(10, 2),
          Coordinates.faceDownOf(7, 3),
          Coordinates.faceDownOf(9, 3),
          Coordinates.faceDownOf(6, 4),
          Coordinates.faceDownOf(8, 4),
          Coordinates.faceDownOf(5, 5),
          Coordinates.faceDownOf(2, 6),
          Coordinates.faceDownOf(4, 6),
          Coordinates.faceDownOf(3, 7));
  private static final Map<String, Array<Coordinate>> P4_COORDINATES =
      HashMap.of(
          "face-up-land",
          P4_FACE_UP_LAND_COORDINATES,
          "face-up-harbor",
          P4_FACE_UP_HARBOR_COORDINATES,
          "face-down",
          P4_FACE_DOWN_COORDINATES);

  private static final Array<Chit> P4_FACE_UP_LAND_CHITS =
      Array.of(CHIT_2, CHIT_11, CHIT_12)
          .appendAll(Array.fill(2, CHIT_3))
          .appendAll(Array.fill(2, CHIT_4))
          .appendAll(Array.fill(2, CHIT_5))
          .appendAll(Array.fill(2, CHIT_6))
          .appendAll(Array.fill(2, CHIT_8))
          .appendAll(Array.fill(2, CHIT_9))
          .appendAll(Array.fill(2, CHIT_10));
  private static final Array<Chit> P4_FACE_DOWN_LAND_CHITS =
      Array.of(CHIT_3, CHIT_4, CHIT_5, CHIT_6, CHIT_8, CHIT_9, CHIT_10, CHIT_12)
          .appendAll(Array.fill(2, CHIT_11));
  private static final Map<String, Array<Chit>> P4_CHITS =
      HashMap.of(
          "face-up-land", P4_FACE_UP_LAND_CHITS,
          "face-down-land", P4_FACE_DOWN_LAND_CHITS);

  private static final SpecificationImpl.Builder P4_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P4_TILES,
          P4_COORDINATES,
          P4_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("face-up-land"),
              TileMappingUtils.newSelfReferringEntry("face-up-harbor"),
              TileMappingUtils.newEntry("face-down", "face-down-land", "face-down-sea")),
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("face-up-land"),
              TileMappingUtils.newSelfReferringEntry("face-down-land")));

  public static final SpecificationImpl P4_SPECIFICATION_IMPL = P4_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P4_FISHERMEN_SPECIFICATION_IMPL =
      P4_SPECIFICATION_BUILDER.withFisheries(P4_FACE_UP_FISHERY_COORDINATES).build();

  private static final Array<Tile> P5_P6_FACE_UP_LAND_TILES =
      Array.fill(4, MOUNTAIN)
          .appendAll(Array.fill(5, FIELD))
          .appendAll(Array.fill(5, FOREST))
          .appendAll(Array.fill(5, HILL))
          .appendAll(Array.fill(5, PASTURE));
  private static final Array<Tile> P5_P6_FACE_DOWN_PRODUCING_TILES =
      Array.fill(2, FIELD)
          .appendAll(Array.fill(2, FOREST))
          .appendAll(Array.fill(2, HILL))
          .appendAll(Array.fill(2, PASTURE))
          .appendAll(Array.fill(3, MOUNTAIN))
          .appendAll(Array.fill(3, GOLD_FIELD));
  private static final Array<Tile> P5_P6_FACE_UP_HARBOR_TILES = Base.P5_P6_HARBOR_TILES;
  private static final Array<Tile> P5_P6_FACE_DOWN_UNBEARING_TILES =
      Array.of(DESERT).appendAll(Array.fill(3, SEA));
  private static final Map<String, Array<Tile>> P5_P6_TILES =
      HashMap.of(
          "face-up-land",
          P5_P6_FACE_UP_LAND_TILES,
          "face-down-producing",
          P5_P6_FACE_DOWN_PRODUCING_TILES,
          "face-down-unbearing",
          P5_P6_FACE_DOWN_UNBEARING_TILES,
          "face-up-harbor",
          P5_P6_FACE_UP_HARBOR_TILES);

  private static final Array<Coordinate> P5_P6_FACE_UP_LAND_COORDINATES =
      Array.of(
          Coordinates.of(3, 1),
          Coordinates.of(5, 1),
          Coordinates.of(15, 1),
          Coordinates.of(2, 2),
          Coordinates.of(4, 2),
          Coordinates.of(16, 2),
          Coordinates.of(1, 3),
          Coordinates.of(3, 3),
          Coordinates.of(15, 3),
          Coordinates.of(17, 3),
          Coordinates.of(2, 4),
          Coordinates.of(4, 4),
          Coordinates.of(14, 4),
          Coordinates.of(16, 4),
          Coordinates.of(1, 5),
          Coordinates.of(3, 5),
          Coordinates.of(15, 5),
          Coordinates.of(17, 5),
          Coordinates.of(2, 6),
          Coordinates.of(14, 6),
          Coordinates.of(16, 6),
          Coordinates.of(3, 7),
          Coordinates.of(13, 7),
          Coordinates.of(15, 7));
  private static final Array<Coordinate> P5_P6_FACE_UP_HARBOR_COORDINATES =
      Array.of(
          Coordinates.of(0, 2, RIGHT),
          Coordinates.of(6, 2, TOP_LEFT),
          Coordinates.of(14, 2, RIGHT),
          Coordinates.of(18, 2, BOTTOM_LEFT),
          Coordinates.of(0, 4, RIGHT),
          Coordinates.of(18, 4, TOP_LEFT),
          Coordinates.of(5, 5, LEFT),
          Coordinates.of(13, 5, BOTTOM_RIGHT),
          Coordinates.of(18, 6, TOP_LEFT),
          Coordinates.of(1, 7, TOP_RIGHT),
          Coordinates.of(14, 8, TOP_LEFT));
  private static final Array<Coordinate> P5_P6_FACE_UP_FISHERY_COORDINATES =
      Array.of(
          Coordinates.of(1, 1, RIGHT, BOTTOM_RIGHT),
          Coordinates.of(17, 1, BOTTOM_LEFT, LEFT),
          Coordinates.of(1, 3, BOTTOM_LEFT, LEFT),
          Coordinates.of(17, 5, TOP_RIGHT, RIGHT),
          Coordinates.of(0, 6, TOP_RIGHT, RIGHT),
          Coordinates.of(17, 7, LEFT, TOP_LEFT));
  private static final Array<Coordinate> P5_P6_FACE_DOWN_COORDINATES =
      Array.of(
          Coordinates.faceDownOf(9, 1),
          Coordinates.faceDownOf(11, 1),
          Coordinates.faceDownOf(8, 2),
          Coordinates.faceDownOf(10, 2),
          Coordinates.faceDownOf(12, 2),
          Coordinates.faceDownOf(7, 3),
          Coordinates.faceDownOf(9, 3),
          Coordinates.faceDownOf(11, 3),
          Coordinates.faceDownOf(8, 4),
          Coordinates.faceDownOf(10, 4),
          Coordinates.faceDownOf(7, 5),
          Coordinates.faceDownOf(9, 5),
          Coordinates.faceDownOf(11, 5),
          Coordinates.faceDownOf(6, 6),
          Coordinates.faceDownOf(8, 6),
          Coordinates.faceDownOf(10, 6),
          Coordinates.faceDownOf(7, 7),
          Coordinates.faceDownOf(9, 7));
  private static final Map<String, Array<Coordinate>> P5_P6_COORDINATES =
      HashMap.of(
          "face-up-land",
          P5_P6_FACE_UP_LAND_COORDINATES,
          "face-up-harbor",
          P5_P6_FACE_UP_HARBOR_COORDINATES,
          "face-down",
          P5_P6_FACE_DOWN_COORDINATES);

  private static final Array<Chit> P5_P6_FACE_UP_LAND_CHITS =
      Array.of(CHIT_2)
          .appendAll(Array.fill(2, CHIT_3))
          .appendAll(Array.fill(3, CHIT_4))
          .appendAll(Array.fill(2, CHIT_5))
          .appendAll(Array.fill(3, CHIT_6))
          .appendAll(Array.fill(3, CHIT_8))
          .appendAll(Array.fill(3, CHIT_9))
          .appendAll(Array.fill(3, CHIT_10))
          .appendAll(Array.fill(2, CHIT_11))
          .appendAll(Array.fill(2, CHIT_12));
  private static final Array<Chit> P5_P6_FACE_DOWN_PRODUCING_CHITS =
      Array.of(CHIT_4, CHIT_6, CHIT_8, CHIT_9, CHIT_10, CHIT_12)
          .appendAll(Array.fill(2, CHIT_2))
          .appendAll(Array.fill(2, CHIT_3))
          .appendAll(Array.fill(2, CHIT_5))
          .appendAll(Array.fill(2, CHIT_11));
  private static final Map<String, Array<Chit>> P5_P6_CHITS =
      HashMap.of(
          "face-up-land", P5_P6_FACE_UP_LAND_CHITS,
          "face-down-producing", P5_P6_FACE_DOWN_PRODUCING_CHITS);

  private static final SpecificationImpl.Builder P5_P6_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P5_P6_TILES,
          P5_P6_COORDINATES,
          P5_P6_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("face-up-land"),
              TileMappingUtils.newSelfReferringEntry("face-up-harbor"),
              TileMappingUtils.newEntry("face-down", "face-down-producing", "face-down-unbearing")),
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("face-up-land"),
              TileMappingUtils.newSelfReferringEntry("face-down-producing")));

  public static final SpecificationImpl P5_P6_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P5_P6_FISHERMEN_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.withFisheries(P5_P6_FACE_UP_FISHERY_COORDINATES).build();

  private static final Array<Tile> P7_P8_FACE_UP_PRODUCING_TILES =
      Array.fill(5, MOUNTAIN)
          .appendAll(Array.fill(6, FOREST))
          .appendAll(Array.fill(6, PASTURE))
          .appendAll(Array.fill(7, FIELD))
          .appendAll(Array.fill(7, HILL));
  private static final Array<Tile> P7_P8_FACE_UP_UNBEARING_TILES = Base.P7_P8_UNBEARING_LAND_TILES;
  private static final Array<Tile> P7_P8_FACE_DOWN_LAND_TILES =
      Array.fill(2, FIELD)
          .appendAll(Array.fill(2, HILL))
          .appendAll(Array.fill(2, MOUNTAIN))
          .appendAll(Array.fill(4, GOLD_FIELD))
          .appendAll(Array.fill(5, FOREST))
          .appendAll(Array.fill(5, PASTURE));
  private static final Array<Tile> P7_P8_FACE_DOWN_SEA_TILES = Array.fill(18, SEA);
  private static final Array<Tile> P7_P8_FACE_UP_HARBOR_TILES = Base.P7_P8_HARBOR_TILES;
  private static final Map<String, Array<Tile>> P7_P8_TILES =
      HashMap.of(
          "face-up-producing",
          P7_P8_FACE_UP_PRODUCING_TILES,
          DESERT_OR_LAKE_NAME,
          P7_P8_FACE_UP_UNBEARING_TILES,
          "face-up-harbor",
          P7_P8_FACE_UP_HARBOR_TILES,
          "face-down-land",
          P7_P8_FACE_DOWN_LAND_TILES,
          "face-down-sea",
          P7_P8_FACE_DOWN_SEA_TILES);

  private static final Array<Coordinate> P7_P8_FACE_UP_LAND_COORDINATES =
      Array.of(
          Coordinates.of(11, 1),
          Coordinates.of(13, 1),
          Coordinates.of(10, 2),
          Coordinates.of(12, 2),
          Coordinates.of(9, 3),
          Coordinates.of(11, 3),
          Coordinates.of(2, 4),
          Coordinates.of(4, 4),
          Coordinates.of(6, 4),
          Coordinates.of(8, 4),
          Coordinates.of(10, 4),
          Coordinates.of(1, 5),
          Coordinates.of(3, 5),
          Coordinates.of(5, 5),
          Coordinates.of(7, 5),
          Coordinates.of(9, 5),
          Coordinates.of(14, 8),
          Coordinates.of(16, 8),
          Coordinates.of(18, 8),
          Coordinates.of(20, 8),
          Coordinates.of(22, 8),
          Coordinates.of(13, 9),
          Coordinates.of(15, 9),
          Coordinates.of(17, 9),
          Coordinates.of(19, 9),
          Coordinates.of(21, 9),
          Coordinates.of(12, 10),
          Coordinates.of(14, 10),
          Coordinates.of(11, 11),
          Coordinates.of(13, 11),
          Coordinates.of(10, 12),
          Coordinates.of(12, 12));
  private static final Array<Coordinate> P7_P8_FACE_UP_HARBOR_COORDINATES =
      Array.of(
          Coordinates.of(14, 0, BOTTOM_LEFT),
          Coordinates.of(10, 0, BOTTOM_RIGHT),
          Coordinates.of(8, 2, BOTTOM_RIGHT),
          Coordinates.of(3, 3, BOTTOM_LEFT),
          Coordinates.of(7, 3, BOTTOM_LEFT),
          Coordinates.of(0, 4, BOTTOM_RIGHT),
          Coordinates.of(23, 9, TOP_LEFT),
          Coordinates.of(16, 10, TOP_RIGHT),
          Coordinates.of(20, 10, TOP_RIGHT),
          Coordinates.of(15, 11, TOP_LEFT),
          Coordinates.of(9, 13, TOP_RIGHT),
          Coordinates.of(13, 13, TOP_LEFT));
  private static final Array<Coordinate> P7_P8_FACE_UP_FISHERY_COORDINATES =
      Array.of(
          Coordinates.of(12, 0, BOTTOM_RIGHT, BOTTOM_LEFT),
          Coordinates.of(10, 2, LEFT, TOP_LEFT),
          Coordinates.of(2, 4, LEFT, TOP_LEFT),
          Coordinates.of(5, 3, BOTTOM_RIGHT, BOTTOM_LEFT),
          Coordinates.of(21, 9, RIGHT, BOTTOM_RIGHT),
          Coordinates.of(18, 10, TOP_LEFT, TOP_RIGHT),
          Coordinates.of(13, 11, RIGHT, BOTTOM_RIGHT),
          Coordinates.of(11, 13, TOP_LEFT, TOP_RIGHT));
  private static final Array<Coordinate> P7_P8_FACE_DOWN_COORDINATES =
      Array.of(
          Coordinates.faceDownOf(16, 2),
          Coordinates.faceDownOf(18, 2),
          Coordinates.faceDownOf(15, 3),
          Coordinates.faceDownOf(17, 3),
          Coordinates.faceDownOf(19, 3),
          Coordinates.faceDownOf(14, 4),
          Coordinates.faceDownOf(16, 4),
          Coordinates.faceDownOf(18, 4),
          Coordinates.faceDownOf(20, 4),
          Coordinates.faceDownOf(13, 5),
          Coordinates.faceDownOf(15, 5),
          Coordinates.faceDownOf(17, 5),
          Coordinates.faceDownOf(19, 5),
          Coordinates.faceDownOf(21, 5),
          Coordinates.faceDownOf(12, 6),
          Coordinates.faceDownOf(14, 6),
          Coordinates.faceDownOf(16, 6),
          Coordinates.faceDownOf(18, 6),
          Coordinates.faceDownOf(20, 6),
          Coordinates.faceDownOf(3, 7),
          Coordinates.faceDownOf(5, 7),
          Coordinates.faceDownOf(7, 7),
          Coordinates.faceDownOf(9, 7),
          Coordinates.faceDownOf(11, 7),
          Coordinates.faceDownOf(2, 8),
          Coordinates.faceDownOf(4, 8),
          Coordinates.faceDownOf(6, 8),
          Coordinates.faceDownOf(8, 8),
          Coordinates.faceDownOf(10, 8),
          Coordinates.faceDownOf(3, 9),
          Coordinates.faceDownOf(5, 9),
          Coordinates.faceDownOf(7, 9),
          Coordinates.faceDownOf(9, 9),
          Coordinates.faceDownOf(4, 10),
          Coordinates.faceDownOf(6, 10),
          Coordinates.faceDownOf(8, 10),
          Coordinates.faceDownOf(5, 11),
          Coordinates.faceDownOf(7, 11));
  private static final Map<String, Array<Coordinate>> P7_P8_COORDINATES =
      HashMap.of(
          "face-up-land",
          P7_P8_FACE_UP_LAND_COORDINATES,
          "face-up-harbor",
          P7_P8_FACE_UP_HARBOR_COORDINATES,
          "face-down",
          P7_P8_FACE_DOWN_COORDINATES);

  private static final Array<Chit> P7_P8_FACE_UP_PRODUCING_CHITS =
      Array.of(CHITS_2_12)
          .appendAll(Array.fill(3, CHIT_2))
          .appendAll(Array.fill(3, CHIT_3))
          .appendAll(Array.fill(3, CHIT_4))
          .appendAll(Array.fill(3, CHIT_5))
          .appendAll(Array.fill(3, CHIT_6))
          .appendAll(Array.fill(3, CHIT_8))
          .appendAll(Array.fill(3, CHIT_9))
          .appendAll(Array.fill(3, CHIT_10))
          .appendAll(Array.fill(3, CHIT_11))
          .appendAll(Array.fill(3, CHIT_12));
  private static final Array<Chit> P7_P8_FACE_DOWN_LAND_CHITS =
      Array.of(CHIT_2, CHIT_12)
          .appendAll(Array.fill(2, CHIT_3))
          .appendAll(Array.fill(3, CHIT_4))
          .appendAll(Array.fill(2, CHIT_5))
          .appendAll(Array.fill(2, CHIT_6))
          .appendAll(Array.fill(2, CHIT_8))
          .appendAll(Array.fill(2, CHIT_9))
          .appendAll(Array.fill(3, CHIT_10))
          .appendAll(Array.fill(2, CHIT_11));
  private static final Map<String, Array<Chit>> P7_P8_CHITS =
      HashMap.of(
          "face-up-producing", P7_P8_FACE_UP_PRODUCING_CHITS,
          "face-down-land", P7_P8_FACE_DOWN_LAND_CHITS);

  private static final SpecificationImpl.Builder P7_P8_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P7_P8_TILES,
          P7_P8_COORDINATES,
          P7_P8_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("face-up-harbor"),
              TileMappingUtils.newEntry("face-up-land", "face-up-producing", DESERT_OR_LAKE_NAME),
              TileMappingUtils.newEntry("face-down", "face-down-land", "face-down-sea")),
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("face-up-producing"),
              TileMappingUtils.newSelfReferringEntry("face-down-land")));

  public static final SpecificationImpl P7_P8_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P7_P8_FISHERMEN_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.withFisheries(P7_P8_FACE_UP_FISHERY_COORDINATES).build();
}
