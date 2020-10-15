package noelyap.setterforcatan.scenario.tradersandbarbarians;

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
import static noelyap.setterforcatan.component.Tiles.CASTLE;
import static noelyap.setterforcatan.component.Tiles.DESERT;
import static noelyap.setterforcatan.component.Tiles.FIELD;
import static noelyap.setterforcatan.component.Tiles.FOREST;
import static noelyap.setterforcatan.component.Tiles.HILL;
import static noelyap.setterforcatan.component.Tiles.MOUNTAIN;
import static noelyap.setterforcatan.component.Tiles.PASTURE;

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

public class BarbarianAttack {
  private static final Array<Tile> P3_P4_COASTAL_PRODUCING_TILES =
      Array.of(MOUNTAIN)
          .appendAll(Array.fill(2, FIELD))
          .appendAll(Array.fill(2, FOREST))
          .appendAll(Array.fill(2, HILL))
          .appendAll(Array.fill(3, PASTURE));
  private static final Array<Tile> P3_P4_COASTAL_NON_PRODUCING_DESERT_TILES = Array.of(DESERT);
  private static final Array<Tile> P3_P4_COASTAL_NON_PRODUCING_CASTLE_TILES = Array.of(CASTLE);
  private static final Array<Tile> P3_P4_INLAND_TILES =
      Array.of(FOREST, HILL, PASTURE)
          .appendAll(Array.fill(2, FIELD))
          .appendAll(Array.fill(2, MOUNTAIN));
  private static final Array<Tile> P3_P4_HARBOR_TILES = Base.P3_P4_HARBOR_TILES;
  private static final Map<String, Array<Tile>> P3_P4_TILES =
      HashMap.of(
          "coastal-producing",
          P3_P4_COASTAL_PRODUCING_TILES,
          "coastal-non-producing-desert",
          P3_P4_COASTAL_NON_PRODUCING_DESERT_TILES,
          "coastal-non-producing-castle",
          P3_P4_COASTAL_NON_PRODUCING_CASTLE_TILES,
          "inland",
          P3_P4_INLAND_TILES,
          "harbor",
          P3_P4_HARBOR_TILES);

  private static final Array<Coordinate> P3_P4_COASTAL_PRODUCING_COORDINATES =
      Array.of(
          Coordinates.of(3, 1),
          Coordinates.of(5, 1),
          Coordinates.of(2, 2),
          Coordinates.of(8, 2),
          Coordinates.of(1, 3),
          Coordinates.of(9, 3),
          Coordinates.of(2, 4),
          Coordinates.of(8, 4),
          Coordinates.of(5, 5),
          Coordinates.of(7, 5));
  private static final Array<Coordinate> P3_P4_COASTAL_NON_PRODUCING_DESERT_COORDINATES =
      Array.of(Coordinates.of(7, 1));
  private static final Array<Coordinate> P3_P4_COASTAL_NON_PRODUCING_CASTLE_COORDINATES =
      Array.of(Coordinates.of(3, 5));
  private static final Array<Coordinate> P3_P4_INLAND_COORDINATES =
      Array.of(
          Coordinates.of(4, 2),
          Coordinates.of(6, 2),
          Coordinates.of(3, 3),
          Coordinates.of(5, 3),
          Coordinates.of(7, 3),
          Coordinates.of(4, 4),
          Coordinates.of(6, 4));
  private static final Array<Coordinate> P3_P4_HARBOR_COORDINATES = Base.P3_P4_HARBOR_COORDINATES;
  private static final Array<Coordinate> P3_P4_FISHERY_COORDINATES = Base.P3_P4_FISHERY_COORDINATES;
  private static final Map<String, Array<Coordinate>> P3_P4_COORDINATES =
      HashMap.of(
          "coastal-producing",
          P3_P4_COASTAL_PRODUCING_COORDINATES,
          "coastal-non-producing-desert",
          P3_P4_COASTAL_NON_PRODUCING_DESERT_COORDINATES,
          "coastal-non-producing-castle",
          P3_P4_COASTAL_NON_PRODUCING_CASTLE_COORDINATES,
          "inland",
          P3_P4_INLAND_COORDINATES,
          "harbor",
          P3_P4_HARBOR_COORDINATES);

  private static final Array<Chit> P3_P4_PRODUCING_CHITS =
      Array.of(CHIT_2, CHIT_11, CHIT_12)
          .appendAll(Array.fill(2, CHIT_3))
          .appendAll(Array.fill(2, CHIT_4))
          .appendAll(Array.fill(2, CHIT_5))
          .appendAll(Array.fill(2, CHIT_6))
          .appendAll(Array.fill(2, CHIT_8))
          .appendAll(Array.fill(2, CHIT_9))
          .appendAll(Array.fill(2, CHIT_10));
  private static final Map<String, Array<Chit>> P3_P4_CHITS =
      HashMap.of("producing", P3_P4_PRODUCING_CHITS);

  private static final SpecificationImpl.Builder P3_P4_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P3_P4_TILES,
          P3_P4_COORDINATES,
          P3_P4_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("coastal-producing"),
              TileMappingUtils.newSelfReferringEntry("coastal-non-producing-desert"),
              TileMappingUtils.newSelfReferringEntry("coastal-non-producing-castle"),
              TileMappingUtils.newSelfReferringEntry("inland"),
              TileMappingUtils.newSelfReferringEntry("harbor")),
          HashMap.ofEntries(TileMappingUtils.newEntry("producing", "coastal-producing", "inland")));

  public static final SpecificationImpl P3_P4_SPECIFICATION_IMPL =
      P3_P4_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P3_P4_FISHERMEN_SPECIFICATION_IMPL =
      P3_P4_SPECIFICATION_BUILDER.withFisheries(P3_P4_FISHERY_COORDINATES).build();

  private static final Array<Tile> P5_P6_COASTAL_PRODUCING_TILES =
      Array.fill(2, FIELD)
          .appendAll(Array.fill(2, MOUNTAIN))
          .appendAll(Array.fill(2, PASTURE))
          .appendAll(Array.fill(3, HILL))
          .appendAll(Array.fill(3, FOREST));
  private static final Array<Tile> P5_P6_COASTAL_NON_PRODUCING_DESERT_TILES = Array.fill(2, DESERT);
  private static final Array<Tile> P5_P6_COASTAL_NON_PRODUCING_CASTLE_TILES = Array.fill(2, CASTLE);
  private static final Array<Tile> P5_P6_INLAND_TILES =
      Array.fill(2, FOREST)
          .appendAll(Array.fill(2, HILL))
          .appendAll(Array.fill(3, MOUNTAIN))
          .appendAll(Array.fill(3, PASTURE))
          .appendAll(Array.fill(4, FIELD));
  private static final Array<Tile> P5_P6_HARBOR_TILES = Base.P5_P6_HARBOR_TILES;
  private static final Map<String, Array<Tile>> P5_P6_TILES =
      HashMap.of(
          "coastal-producing",
          P5_P6_COASTAL_PRODUCING_TILES,
          "coastal-non-producing-desert",
          P5_P6_COASTAL_NON_PRODUCING_DESERT_TILES,
          "coastal-non-producing-castle",
          P5_P6_COASTAL_NON_PRODUCING_CASTLE_TILES,
          "inland",
          P5_P6_INLAND_TILES,
          "harbor",
          P5_P6_HARBOR_TILES);

  private static final Array<Coordinate> P5_P6_COASTAL_PRODUCING_COORDINATES =
      Array.of(
          Coordinates.of(5, 1),
          Coordinates.of(7, 1),
          Coordinates.of(9, 1),
          Coordinates.of(4, 2),
          Coordinates.of(10, 2),
          Coordinates.of(3, 3),
          Coordinates.of(11, 5),
          Coordinates.of(4, 6),
          Coordinates.of(10, 6),
          Coordinates.of(5, 7),
          Coordinates.of(7, 7),
          Coordinates.of(9, 7));
  private static final Array<Coordinate> P5_P6_COASTAL_NON_PRODUCING_DESERT_COORDINATES =
      Array.of(Coordinates.of(11, 3), Coordinates.of(3, 5));
  private static final Array<Coordinate> P5_P6_COASTAL_NON_PRODUCING_CASTLE_COORDINATES =
      Array.of(Coordinates.of(2, 4), Coordinates.of(12, 4));
  private static final Array<Coordinate> P5_P6_INLAND_COORDINATES =
      Array.of(
          Coordinates.of(6, 2),
          Coordinates.of(8, 2),
          Coordinates.of(5, 3),
          Coordinates.of(7, 3),
          Coordinates.of(9, 3),
          Coordinates.of(4, 4),
          Coordinates.of(6, 4),
          Coordinates.of(8, 4),
          Coordinates.of(10, 4),
          Coordinates.of(5, 5),
          Coordinates.of(7, 5),
          Coordinates.of(9, 5),
          Coordinates.of(6, 6),
          Coordinates.of(8, 6));
  private static final Array<Coordinate> P5_P6_HARBOR_COORDINATES = Base.P5_P6_HARBOR_COORDINATES;
  private static final Array<Coordinate> P5_P6_FISHERY_COORDINATES = Base.P5_P6_FISHERY_COORDINATES;
  private static final Map<String, Array<Coordinate>> P5_P6_COORDINATES =
      HashMap.of(
          "coastal-producing",
          P5_P6_COASTAL_PRODUCING_COORDINATES,
          "coastal-non-producing-desert",
          P5_P6_COASTAL_NON_PRODUCING_DESERT_COORDINATES,
          "coastal-non-producing-castle",
          P5_P6_COASTAL_NON_PRODUCING_CASTLE_COORDINATES,
          "inland",
          P5_P6_INLAND_COORDINATES,
          "harbor",
          P5_P6_HARBOR_COORDINATES);

  private static final Array<Chit> P5_P6_PRODUCING_CHITS =
      Array.of(CHIT_2, CHIT_12)
          .appendAll(Array.fill(3, CHIT_3))
          .appendAll(Array.fill(3, CHIT_4))
          .appendAll(Array.fill(3, CHIT_5))
          .appendAll(Array.fill(3, CHIT_6))
          .appendAll(Array.fill(3, CHIT_8))
          .appendAll(Array.fill(3, CHIT_9))
          .appendAll(Array.fill(3, CHIT_10))
          .appendAll(Array.fill(3, CHIT_11));
  private static final Map<String, Array<Chit>> P5_P6_CHITS =
      HashMap.of("producing", P5_P6_PRODUCING_CHITS);

  private static final SpecificationImpl.Builder P5_P6_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P5_P6_TILES,
          P5_P6_COORDINATES,
          P5_P6_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("coastal-producing"),
              TileMappingUtils.newSelfReferringEntry("coastal-non-producing-desert"),
              TileMappingUtils.newSelfReferringEntry("coastal-non-producing-castle"),
              TileMappingUtils.newSelfReferringEntry("inland"),
              TileMappingUtils.newSelfReferringEntry("harbor")),
          HashMap.ofEntries(TileMappingUtils.newEntry("producing", "coastal-producing", "inland")));

  public static final SpecificationImpl P5_P6_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P5_P6_FISHERMEN_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.withFisheries(P5_P6_FISHERY_COORDINATES).build();

  private static final Array<Tile> P7_P8_COASTAL_PRODUCING_TILES =
      Array.fill(2, FIELD)
          .appendAll(Array.fill(2, FOREST))
          .appendAll(Array.fill(2, PASTURE))
          .appendAll(Array.fill(3, HILL))
          .appendAll(Array.fill(3, MOUNTAIN));
  private static final Array<Tile> P7_P8_COASTAL_NON_PRODUCING_DESERT_TILES = Array.fill(3, DESERT);
  private static final Array<Tile> P7_P8_COASTAL_NON_PRODUCING_CASTLE_TILES = Array.fill(3, CASTLE);
  private static final Array<Tile> P7_P8_INLAND_TILES =
      Array.fill(2, HILL)
          .appendAll(Array.fill(2, MOUNTAIN))
          .appendAll(Array.fill(5, FIELD))
          .appendAll(Array.fill(5, FOREST))
          .appendAll(Array.fill(5, PASTURE));
  private static final Array<Tile> P7_P8_HARBOR_TILES = Base.P7_P8_HARBOR_TILES;
  private static final Map<String, Array<Tile>> P7_P8_TILES =
      HashMap.of(
          "coastal-producing",
          P7_P8_COASTAL_PRODUCING_TILES,
          "coastal-non-producing-desert",
          P7_P8_COASTAL_NON_PRODUCING_DESERT_TILES,
          "coastal-non-producing-castle",
          P7_P8_COASTAL_NON_PRODUCING_CASTLE_TILES,
          "inland",
          P7_P8_INLAND_TILES,
          "harbor",
          P7_P8_HARBOR_TILES);

  private static final Array<Coordinate> P7_P8_COASTAL_PRODUCING_COORDINATES =
      Array.of(
          Coordinates.of(7, 1),
          Coordinates.of(9, 1),
          Coordinates.of(11, 1),
          Coordinates.of(12, 2),
          Coordinates.of(3, 3),
          Coordinates.of(2, 4),
          Coordinates.of(3, 5),
          Coordinates.of(13, 5),
          Coordinates.of(4, 6),
          Coordinates.of(12, 6),
          Coordinates.of(9, 7),
          Coordinates.of(11, 7));
  private static final Array<Coordinate> P7_P8_COASTAL_NON_PRODUCING_DESERT_COORDINATES =
      Array.of(Coordinates.of(4, 2), Coordinates.of(13, 3), Coordinates.of(7, 7));
  private static final Array<Coordinate> P7_P8_COASTAL_NON_PRODUCING_CASTLE_COORDINATES =
      Array.of(Coordinates.of(5, 1), Coordinates.of(14, 4), Coordinates.of(5, 7));
  private static final Array<Coordinate> P7_P8_INLAND_COORDINATES =
      Array.of(
          Coordinates.of(6, 2),
          Coordinates.of(8, 2),
          Coordinates.of(10, 2),
          Coordinates.of(5, 3),
          Coordinates.of(7, 3),
          Coordinates.of(9, 3),
          Coordinates.of(11, 3),
          Coordinates.of(4, 4),
          Coordinates.of(6, 4),
          Coordinates.of(8, 4),
          Coordinates.of(10, 4),
          Coordinates.of(12, 4),
          Coordinates.of(5, 5),
          Coordinates.of(7, 5),
          Coordinates.of(9, 5),
          Coordinates.of(11, 5),
          Coordinates.of(6, 6),
          Coordinates.of(8, 6),
          Coordinates.of(10, 6));
  private static final Array<Coordinate> P7_P8_HARBOR_COORDINATES = Base.P7_P8_HARBOR_COORDINATES;
  private static final Array<Coordinate> P7_P8_FISHERY_COORDINATES = Base.P7_P8_FISHERY_COORDINATES;
  private static final Map<String, Array<Coordinate>> P7_P8_COORDINATES =
      HashMap.of(
          "coastal-producing",
          P7_P8_COASTAL_PRODUCING_COORDINATES,
          "coastal-non-producing-desert",
          P7_P8_COASTAL_NON_PRODUCING_DESERT_COORDINATES,
          "coastal-non-producing-castle",
          P7_P8_COASTAL_NON_PRODUCING_CASTLE_COORDINATES,
          "inland",
          P7_P8_INLAND_COORDINATES,
          "harbor",
          P7_P8_HARBOR_COORDINATES);

  private static final Array<Chit> P7_P8_PRODUCING_CHITS =
      Array.of(CHITS_2_12)
          .appendAll(Array.fill(2, CHIT_2))
          .appendAll(Array.fill(3, CHIT_3))
          .appendAll(Array.fill(4, CHIT_4))
          .appendAll(Array.fill(3, CHIT_5))
          .appendAll(Array.fill(3, CHIT_6))
          .appendAll(Array.fill(3, CHIT_8))
          .appendAll(Array.fill(3, CHIT_9))
          .appendAll(Array.fill(4, CHIT_10))
          .appendAll(Array.fill(3, CHIT_11))
          .appendAll(Array.fill(2, CHIT_12));
  private static final Map<String, Array<Chit>> P7_P8_CHITS =
      HashMap.of("producing", P7_P8_PRODUCING_CHITS);

  private static final SpecificationImpl.Builder P7_P8_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P7_P8_TILES,
          P7_P8_COORDINATES,
          P7_P8_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("coastal-producing"),
              TileMappingUtils.newSelfReferringEntry("coastal-non-producing-desert"),
              TileMappingUtils.newSelfReferringEntry("coastal-non-producing-castle"),
              TileMappingUtils.newSelfReferringEntry("inland"),
              TileMappingUtils.newSelfReferringEntry("harbor")),
          HashMap.ofEntries(TileMappingUtils.newEntry("producing", "coastal-producing", "inland")));

  public static final SpecificationImpl P7_P8_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P7_P8_FISHERMEN_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.withFisheries(P7_P8_FISHERY_COORDINATES).build();
}
