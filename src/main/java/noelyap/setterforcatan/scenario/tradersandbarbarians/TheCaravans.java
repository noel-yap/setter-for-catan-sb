package noelyap.setterforcatan.scenario.tradersandbarbarians;

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

import static noelyap.setterforcatan.component.Chits.CHIT_10;
import static noelyap.setterforcatan.component.Chits.CHIT_11;
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
import static noelyap.setterforcatan.component.Tiles.OASIS;
import static noelyap.setterforcatan.component.Tiles.PASTURE;

public class TheCaravans {
  private static final Array<Tile> P3_P4_PRODUCING_LAND_TILES = Base.P3_P4_PRODUCING_TILES;
  private static final Array<Tile> P3_P4_NON_PRODUCING_LAND_TILES =
      Array.of(OASIS);
  private static final Array<Tile> P3_P4_HARBOR_TILES = Base.P3_P4_HARBOR_TILES;
  private static final Map<String, Array<Tile>> P3_P4_TILES =
      HashMap.of(
          "producing-land",
          P3_P4_PRODUCING_LAND_TILES,
          "non-producing-land",
          P3_P4_NON_PRODUCING_LAND_TILES,
          "harbor",
          P3_P4_HARBOR_TILES);

  private static final Array<Coordinate> P3_P4_OASIS_COORDINATES = Array.of(Coordinates.of(5, 3));
  private static final Array<Coordinate> P3_P4_PRODUCING_LAND_COORDINATES =
      Base.P3_P4_LAND_COORDINATES
          .filterNot(P3_P4_OASIS_COORDINATES::contains);
  private static final Array<Coordinate> P3_P4_NON_PRODUCING_LAND_COORDINATES =
      P3_P4_OASIS_COORDINATES;
  private static final Array<Coordinate> P3_P4_HARBOR_COORDINATES =
      Base.P3_P4_HARBOR_COORDINATES;
  private static final Array<Coordinate> P3_P4_NON_RIVER_FISHERY_COORDINATES =
      Base.P3_P4_FISHERY_COORDINATES;
  private static final Map<String, Array<Coordinate>> P3_P4_COORDINATES =
      HashMap.of(
          "producing-land",
          P3_P4_PRODUCING_LAND_COORDINATES,
          "non-producing-land",
          P3_P4_NON_PRODUCING_LAND_COORDINATES,
          "harbor",
          P3_P4_HARBOR_COORDINATES);

  private static final Array<Chit> P3_P4_PRODUCING_CHITS = Base.P3_P4_PRODUCING_CHITS;
  private static final Map<String, Array<Chit>> P3_P4_CHITS =
      HashMap.of("producing-land", P3_P4_PRODUCING_CHITS);

  private static final SpecificationImpl.Builder P3_P4_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P3_P4_TILES,
          P3_P4_COORDINATES,
          P3_P4_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("producing-land"),
              TileMappingUtils.newSelfReferringEntry("non-producing-land"),
              TileMappingUtils.newSelfReferringEntry("harbor")),
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("producing-land")));

  public static final SpecificationImpl P3_P4_SPECIFICATION_IMPL =
      P3_P4_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P3_P4_FISHERMEN_SPECIFICATION_IMPL =
      P3_P4_SPECIFICATION_BUILDER.withFisheries(P3_P4_NON_RIVER_FISHERY_COORDINATES).build();

  private static final Array<Tile> P5_P6_PRODUCING_LAND_TILES = Base.P5_P6_PRODUCING_TILES;
  private static final Array<Tile> P5_P6_NON_PRODUCING_LAND_TILES =
      Array.fill(2, OASIS);
  private static final Array<Tile> P5_P6_HARBOR_TILES = Base.P5_P6_HARBOR_TILES;
  private static final Map<String, Array<Tile>> P5_P6_TILES =
      HashMap.of(
          "producing-land",
          P5_P6_PRODUCING_LAND_TILES,
          "non-producing-land",
          P5_P6_NON_PRODUCING_LAND_TILES,
          "harbor",
          P5_P6_HARBOR_TILES);

  private static final Array<Coordinate> P5_P6_OASIS_COORDINATES =
      Array.of(
          Coordinates.of(5, 3),
          Coordinates.of(9, 5));
  private static final Array<Coordinate> P5_P6_PRODUCING_LAND_COORDINATES =
      Base.P5_P6_LAND_COORDINATES
          .filterNot(P5_P6_OASIS_COORDINATES::contains);
  private static final Array<Coordinate> P5_P6_NON_PRODUCING_LAND_COORDINATES =
      P5_P6_OASIS_COORDINATES;
  private static final Array<Coordinate> P5_P6_HARBOR_COORDINATES =
      Base.P5_P6_HARBOR_COORDINATES;
  private static final Array<Coordinate> P5_P6_NON_RIVER_FISHERY_COORDINATES =
      Base.P5_P6_FISHERY_COORDINATES;
  private static final Map<String, Array<Coordinate>> P5_P6_COORDINATES =
      HashMap.of(
          "producing-land",
          P5_P6_PRODUCING_LAND_COORDINATES,
          "non-producing-land",
          P5_P6_NON_PRODUCING_LAND_COORDINATES,
          "harbor",
          P5_P6_HARBOR_COORDINATES);

  private static final Array<Chit> P5_P6_PRODUCING_CHITS = Base.P5_P6_PRODUCING_CHITS;
  private static final Map<String, Array<Chit>> P5_P6_CHITS =
      HashMap.of("producing-land", P5_P6_PRODUCING_CHITS);

  private static final SpecificationImpl.Builder P5_P6_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P5_P6_TILES,
          P5_P6_COORDINATES,
          P5_P6_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("producing-land"),
              TileMappingUtils.newSelfReferringEntry("non-producing-land"),
              TileMappingUtils.newSelfReferringEntry("harbor")),
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("producing-land")));

  public static final SpecificationImpl P5_P6_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P5_P6_FISHERMEN_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.withFisheries(P5_P6_NON_RIVER_FISHERY_COORDINATES).build();

  private static final Array<Tile> P7_P8_PRODUCING_LAND_TILES = Base.P3_P4_PRODUCING_TILES
      .appendAll(Array.fill(2, HILL))
      .appendAll(Array.fill(2, MOUNTAIN))
      .appendAll(Array.fill(4, FIELD))
      .appendAll(Array.fill(4, FOREST))
      .appendAll(Array.fill(4, PASTURE));
  private static final Array<Tile> P7_P8_NON_PRODUCING_LAND_TILES =
      Array.fill(3, OASIS);
  private static final Array<Tile> P7_P8_HARBOR_TILES = Base.P7_P8_HARBOR_TILES;
  private static final Map<String, Array<Tile>> P7_P8_TILES =
      HashMap.of(
          "producing-land",
          P7_P8_PRODUCING_LAND_TILES,
          "non-producing-land",
          P7_P8_NON_PRODUCING_LAND_TILES,
          "harbor",
          P7_P8_HARBOR_TILES);

  private static final Array<Coordinate> P7_P8_OASIS_COORDINATES = Array.of(
      Coordinates.of(5, 3),
      Coordinates.of(11, 3),
      Coordinates.of(8, 6));
  private static final Array<Coordinate> P7_P8_PRODUCING_LAND_COORDINATES =
      Base.P7_P8_LAND_COORDINATES
          .filterNot(P7_P8_OASIS_COORDINATES::contains);
  private static final Array<Coordinate> P7_P8_NON_PRODUCING_LAND_COORDINATES =
      P7_P8_OASIS_COORDINATES;
  private static final Array<Coordinate> P7_P8_HARBOR_COORDINATES =
      Base.P7_P8_HARBOR_COORDINATES;
  private static final Array<Coordinate> P7_P8_NON_RIVER_FISHERY_COORDINATES =
      Base.P7_P8_FISHERY_COORDINATES;
  private static final Map<String, Array<Coordinate>> P7_P8_COORDINATES =
      HashMap.of(
          "producing-land",
          P7_P8_PRODUCING_LAND_COORDINATES,
          "non-producing-land",
          P7_P8_NON_PRODUCING_LAND_COORDINATES,
          "harbor",
          P7_P8_HARBOR_COORDINATES);

  private static final Array<Chit> P7_P8_PRODUCING_CHITS = Base.P3_P4_PRODUCING_CHITS
      .appendAll(Array.fill(2, CHIT_3))
      .appendAll(Array.fill(2, CHIT_4))
      .appendAll(Array.fill(2, CHIT_5))
      .appendAll(Array.fill(2, CHIT_6))
      .appendAll(Array.fill(2, CHIT_8))
      .appendAll(Array.fill(2, CHIT_9))
      .appendAll(Array.fill(2, CHIT_10))
      .appendAll(Array.fill(2, CHIT_11));
  private static final Map<String, Array<Chit>> P7_P8_CHITS =
      HashMap.of("producing-land", P7_P8_PRODUCING_CHITS);

  private static final SpecificationImpl.Builder P7_P8_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P7_P8_TILES,
          P7_P8_COORDINATES,
          P7_P8_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("producing-land"),
              TileMappingUtils.newSelfReferringEntry("non-producing-land"),
              TileMappingUtils.newSelfReferringEntry("harbor")),
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("producing-land")));

  public static final SpecificationImpl P7_P8_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P7_P8_FISHERMEN_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.withFisheries(P7_P8_NON_RIVER_FISHERY_COORDINATES).build();
}
