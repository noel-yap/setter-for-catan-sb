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
import static noelyap.setterforcatan.component.Tiles.RIVER;
import static noelyap.setterforcatan.component.Tiles.SWAMP;
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

public class RiversOfCatan {
  private static final Array<Tile> P3_P4_NON_RIVER_LAND_TILES =
      Array.of(HILL, MOUNTAIN)
          .appendAll(Array.fill(2, PASTURE))
          .appendAll(Array.fill(4, FIELD))
          .appendAll(Array.fill(4, FOREST));
  private static final Array<Tile> P3_P4_NON_RIVER_HARBOR_TILES = Base.P3_P4_HARBOR_TILES;
  private static final Array<Tile> P3_P4_RIVER_MOUNTAIN_TILES = Array.fill(2, MOUNTAIN);
  private static final Array<Tile> P3_P4_RIVER_HILL_TILES = Array.fill(2, HILL);
  private static final Array<Tile> P3_P4_RIVER_PASTURE_TILES = Array.of(PASTURE);
  private static final Array<Tile> P3_P4_RIVER_SWAMP_TILES = Array.fill(2, SWAMP);
  private static final Array<Tile> P3_P4_RIVER_RIVER_TILES = Array.fill(7, RIVER);
  private static final Map<String, Array<Tile>> P3_P4_TILES =
      HashMap.of(
          "non-river-land",
          P3_P4_NON_RIVER_LAND_TILES,
          "non-river-harbor",
          P3_P4_NON_RIVER_HARBOR_TILES,
          "river-mountain",
          P3_P4_RIVER_MOUNTAIN_TILES,
          "river-hill",
          P3_P4_RIVER_HILL_TILES,
          "river-pasture",
          P3_P4_RIVER_PASTURE_TILES,
          "river-swamp",
          P3_P4_RIVER_SWAMP_TILES,
          "river-river",
          P3_P4_RIVER_RIVER_TILES);

  private static final Array<Coordinate> P3_P4_NON_RIVER_LAND_COORDINATES =
      Array.of(
          Coordinates.of(3, 1),
          Coordinates.of(5, 1),
          Coordinates.of(7, 1),
          Coordinates.of(1, 3),
          Coordinates.of(3, 3),
          Coordinates.of(5, 3),
          Coordinates.of(7, 3),
          Coordinates.of(9, 3),
          Coordinates.of(8, 4),
          Coordinates.of(3, 5),
          Coordinates.of(5, 5),
          Coordinates.of(7, 5));
  private static final Array<Coordinate> P3_P4_NON_RIVER_HARBOR_COORDINATES =
      Base.P3_P4_HARBOR_COORDINATES;
  private static final Array<Coordinate> P3_P4_NON_RIVER_FISHERY_COORDINATES =
      Base.P3_P4_FISHERY_COORDINATES;
  private static final Array<Coordinate> P3_P4_RIVER_MOUNTAIN_COORDINATES =
      Array.of(Coordinates.of(8, 2), Coordinates.of(6, 4));
  private static final Array<Coordinate> P3_P4_RIVER_HILL_COORDINATES =
      Array.of(Coordinates.of(6, 2), Coordinates.of(4, 4));
  private static final Array<Coordinate> P3_P4_RIVER_PASTURE_COORDINATES =
      Array.of(Coordinates.of(4, 2));
  private static final Array<Coordinate> P3_P4_RIVER_SWAMP_COORDINATES =
      Array.of(Coordinates.of(2, 2), Coordinates.of(2, 4));
  private static final Array<Coordinate> P3_P4_RIVER_RIVER_COORDINATES =
      Array.of(
          Coordinates.onEdges(2, 2, RIGHT, TOP_LEFT),
          Coordinates.onEdges(4, 2, RIGHT, LEFT),
          Coordinates.onEdges(6, 2, RIGHT, LEFT),
          Coordinates.onEdges(8, 2, LEFT),
          Coordinates.onEdges(2, 4, RIGHT, BOTTOM_LEFT),
          Coordinates.onEdges(4, 4, RIGHT, LEFT),
          Coordinates.onEdges(6, 4, LEFT));
  private static final Map<String, Array<Coordinate>> P3_P4_COORDINATES =
      HashMap.of(
          "non-river-land",
          P3_P4_NON_RIVER_LAND_COORDINATES,
          "non-river-harbor",
          P3_P4_NON_RIVER_HARBOR_COORDINATES,
          "river-mountain",
          P3_P4_RIVER_MOUNTAIN_COORDINATES,
          "river-hill",
          P3_P4_RIVER_HILL_COORDINATES,
          "river-pasture",
          P3_P4_RIVER_PASTURE_COORDINATES,
          "river-swamp",
          P3_P4_RIVER_SWAMP_COORDINATES,
          "river-river",
          P3_P4_RIVER_RIVER_COORDINATES);

  private static final Array<Chit> P3_P4_PRODUCING_CHITS =
      Array.of(CHITS_2_12)
          .appendAll(Array.fill(2, CHIT_3))
          .appendAll(Array.fill(2, CHIT_4))
          .appendAll(Array.fill(2, CHIT_5))
          .appendAll(Array.fill(2, CHIT_6))
          .appendAll(Array.fill(2, CHIT_8))
          .appendAll(Array.fill(2, CHIT_9))
          .appendAll(Array.fill(2, CHIT_10))
          .appendAll(Array.fill(2, CHIT_11));
  private static final Map<String, Array<Chit>> P3_P4_CHITS =
      HashMap.of("producing", P3_P4_PRODUCING_CHITS);

  private static final SpecificationImpl.Builder P3_P4_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P3_P4_TILES,
          P3_P4_COORDINATES,
          P3_P4_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("non-river-land"),
              TileMappingUtils.newSelfReferringEntry("non-river-harbor"),
              TileMappingUtils.newSelfReferringEntry("river-mountain"),
              TileMappingUtils.newSelfReferringEntry("river-hill"),
              TileMappingUtils.newSelfReferringEntry("river-pasture"),
              TileMappingUtils.newSelfReferringEntry("river-swamp"),
              TileMappingUtils.newSelfReferringEntry("river-river")),
          HashMap.ofEntries(
              TileMappingUtils.newEntry(
                  "producing", "non-river-land", "river-mountain", "river-hill", "river-pasture")));

  public static final SpecificationImpl P3_P4_SPECIFICATION_IMPL =
      P3_P4_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P3_P4_FISHERMEN_SPECIFICATION_IMPL =
      P3_P4_SPECIFICATION_BUILDER.withFisheries(P3_P4_NON_RIVER_FISHERY_COORDINATES).build();

  private static final Array<Tile> P5_P6_NON_RIVER_LAND_TILES =
      Array.fill(2, MOUNTAIN)
          .appendAll(Array.fill(3, HILL))
          .appendAll(Array.fill(3, PASTURE))
          .appendAll(Array.fill(6, FIELD))
          .appendAll(Array.fill(6, FOREST));
  private static final Array<Tile> P5_P6_NON_RIVER_HARBOR_TILES = Base.P5_P6_HARBOR_TILES;
  private static final Array<Tile> P5_P6_RIVER_MOUNTAIN_TILES = Array.fill(3, MOUNTAIN);
  private static final Array<Tile> P5_P6_RIVER_HILL_TILES = Array.fill(2, HILL);
  private static final Array<Tile> P5_P6_RIVER_PASTURE_TILES = Array.fill(3, PASTURE);
  private static final Array<Tile> P5_P6_RIVER_SWAMP_TILES = Array.fill(2, SWAMP);
  private static final Array<Tile> P5_P6_RIVER_RIVER_TILES = Array.fill(10, RIVER);
  private static final Map<String, Array<Tile>> P5_P6_TILES =
      HashMap.of(
          "non-river-land",
          P5_P6_NON_RIVER_LAND_TILES,
          "non-river-harbor",
          P5_P6_NON_RIVER_HARBOR_TILES,
          "river-mountain",
          P5_P6_RIVER_MOUNTAIN_TILES,
          "river-hill",
          P5_P6_RIVER_HILL_TILES,
          "river-pasture",
          P5_P6_RIVER_PASTURE_TILES,
          "river-swamp",
          P5_P6_RIVER_SWAMP_TILES,
          "river-river",
          P5_P6_RIVER_RIVER_TILES);

  private static final Array<Coordinate> P5_P6_NON_RIVER_LAND_COORDINATES =
      Array.of(
          Coordinates.of(5, 1),
          Coordinates.of(9, 1),
          Coordinates.of(4, 2),
          Coordinates.of(8, 2),
          Coordinates.of(10, 2),
          Coordinates.of(3, 3),
          Coordinates.of(7, 3),
          Coordinates.of(9, 3),
          Coordinates.of(11, 3),
          Coordinates.of(2, 4),
          Coordinates.of(6, 4),
          Coordinates.of(3, 5),
          Coordinates.of(7, 5),
          Coordinates.of(9, 5),
          Coordinates.of(11, 5),
          Coordinates.of(4, 6),
          Coordinates.of(8, 6),
          Coordinates.of(10, 6),
          Coordinates.of(5, 7),
          Coordinates.of(9, 7));
  private static final Array<Coordinate> P5_P6_NON_RIVER_HARBOR_COORDINATES =
      Base.P5_P6_HARBOR_COORDINATES;
  private static final Array<Coordinate> P5_P6_NON_RIVER_FISHERY_COORDINATES =
      Base.P5_P6_FISHERY_COORDINATES;
  private static final Array<Coordinate> P5_P6_RIVER_MOUNTAIN_COORDINATES =
      Array.of(Coordinates.of(5, 3), Coordinates.of(4, 4), Coordinates.of(8, 4));
  private static final Array<Coordinate> P5_P6_RIVER_HILL_COORDINATES =
      Array.of(Coordinates.of(6, 2), Coordinates.of(5, 5));
  private static final Array<Coordinate> P5_P6_RIVER_PASTURE_COORDINATES =
      Array.of(Coordinates.of(10, 4), Coordinates.of(12, 4), Coordinates.of(6, 6));
  private static final Array<Coordinate> P5_P6_RIVER_SWAMP_COORDINATES =
      Array.of(Coordinates.of(7, 1), Coordinates.of(7, 7));
  private static final Array<Coordinate> P5_P6_RIVER_RIVER_COORDINATES =
      Array.of(
          Coordinates.onEdges(7, 1, BOTTOM_LEFT, TOP_LEFT),
          Coordinates.onEdges(6, 2, TOP_RIGHT, BOTTOM_LEFT),
          Coordinates.onEdges(5, 3, TOP_RIGHT),
          Coordinates.onEdges(4, 4, BOTTOM_RIGHT),
          Coordinates.onEdges(8, 4, RIGHT),
          Coordinates.onEdges(10, 4, RIGHT, LEFT),
          Coordinates.onEdges(12, 4, TOP_RIGHT, LEFT),
          Coordinates.onEdges(5, 5, BOTTOM_RIGHT, TOP_LEFT),
          Coordinates.onEdges(6, 6, BOTTOM_RIGHT, TOP_LEFT),
          Coordinates.onEdges(7, 7, BOTTOM_LEFT, TOP_LEFT));
  private static final Map<String, Array<Coordinate>> P5_P6_COORDINATES =
      HashMap.of(
          "non-river-land",
          P5_P6_NON_RIVER_LAND_COORDINATES,
          "non-river-harbor",
          P5_P6_NON_RIVER_HARBOR_COORDINATES,
          "river-mountain",
          P5_P6_RIVER_MOUNTAIN_COORDINATES,
          "river-hill",
          P5_P6_RIVER_HILL_COORDINATES,
          "river-pasture",
          P5_P6_RIVER_PASTURE_COORDINATES,
          "river-swamp",
          P5_P6_RIVER_SWAMP_COORDINATES,
          "river-river",
          P5_P6_RIVER_RIVER_COORDINATES);

  private static final Array<Chit> P5_P6_PRODUCING_CHITS = Base.P5_P6_PRODUCING_CHITS;
  private static final Map<String, Array<Chit>> P5_P6_CHITS =
      HashMap.of("producing", P5_P6_PRODUCING_CHITS);

  private static final SpecificationImpl.Builder P5_P6_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P5_P6_TILES,
          P5_P6_COORDINATES,
          P5_P6_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("non-river-land"),
              TileMappingUtils.newSelfReferringEntry("non-river-harbor"),
              TileMappingUtils.newSelfReferringEntry("river-mountain"),
              TileMappingUtils.newSelfReferringEntry("river-hill"),
              TileMappingUtils.newSelfReferringEntry("river-pasture"),
              TileMappingUtils.newSelfReferringEntry("river-swamp"),
              TileMappingUtils.newSelfReferringEntry("river-river")),
          HashMap.ofEntries(
              TileMappingUtils.newEntry(
                  "producing", "non-river-land", "river-mountain", "river-hill", "river-pasture")));

  public static final SpecificationImpl P5_P6_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P5_P6_FISHERMEN_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.withFisheries(P5_P6_NON_RIVER_FISHERY_COORDINATES).build();

  private static final Array<Tile> P7_P8_NON_RIVER_LAND_TILES =
      Array.fill(3, MOUNTAIN)
          .appendAll(Array.fill(4, HILL))
          .appendAll(Array.fill(5, PASTURE))
          .appendAll(Array.fill(7, FIELD))
          .appendAll(Array.fill(8, FOREST));
  private static final Array<Tile> P7_P8_NON_RIVER_HARBOR_TILES = Base.P7_P8_HARBOR_TILES;
  private static final Array<Tile> P7_P8_RIVER_MOUNTAIN_TILES = P5_P6_RIVER_MOUNTAIN_TILES;
  private static final Array<Tile> P7_P8_RIVER_HILL_TILES = P5_P6_RIVER_HILL_TILES;
  private static final Array<Tile> P7_P8_RIVER_PASTURE_TILES = P5_P6_RIVER_PASTURE_TILES;
  private static final Array<Tile> P7_P8_RIVER_SWAMP_TILES = P5_P6_RIVER_SWAMP_TILES;
  private static final Array<Tile> P7_P8_RIVER_RIVER_TILES = P5_P6_RIVER_RIVER_TILES;
  private static final Map<String, Array<Tile>> P7_P8_TILES =
      HashMap.of(
          "non-river-land",
          P7_P8_NON_RIVER_LAND_TILES,
          "non-river-harbor",
          P7_P8_NON_RIVER_HARBOR_TILES,
          "river-mountain",
          P7_P8_RIVER_MOUNTAIN_TILES,
          "river-hill",
          P7_P8_RIVER_HILL_TILES,
          "river-pasture",
          P7_P8_RIVER_PASTURE_TILES,
          "river-swamp",
          P7_P8_RIVER_SWAMP_TILES,
          "river-river",
          P7_P8_RIVER_RIVER_TILES);

  private static final Array<Coordinate> P7_P8_NON_RIVER_LAND_COORDINATES =
      Array.of(
          Coordinates.of(5, 1),
          Coordinates.of(9, 1),
          Coordinates.of(11, 1),
          Coordinates.of(4, 2),
          Coordinates.of(8, 2),
          Coordinates.of(10, 2),
          Coordinates.of(12, 2),
          Coordinates.of(3, 3),
          Coordinates.of(7, 3),
          Coordinates.of(9, 3),
          Coordinates.of(11, 3),
          Coordinates.of(13, 3),
          Coordinates.of(2, 4),
          Coordinates.of(4, 4),
          Coordinates.of(8, 4),
          Coordinates.of(3, 5),
          Coordinates.of(5, 5),
          Coordinates.of(9, 5),
          Coordinates.of(11, 5),
          Coordinates.of(13, 5),
          Coordinates.of(4, 6),
          Coordinates.of(6, 6),
          Coordinates.of(10, 6),
          Coordinates.of(12, 6),
          Coordinates.of(5, 7),
          Coordinates.of(7, 7),
          Coordinates.of(11, 7));
  private static final Array<Coordinate> P7_P8_NON_RIVER_HARBOR_COORDINATES =
      Base.P7_P8_HARBOR_COORDINATES;
  private static final Array<Coordinate> P7_P8_NON_RIVER_FISHERY_COORDINATES =
      Base.P7_P8_FISHERY_COORDINATES;
  private static final Array<Coordinate> P7_P8_RIVER_MOUNTAIN_COORDINATES =
      Array.of(Coordinates.of(5, 3), Coordinates.of(6, 4), Coordinates.of(10, 4));
  private static final Array<Coordinate> P7_P8_RIVER_HILL_COORDINATES =
      Array.of(Coordinates.of(6, 2), Coordinates.of(7, 5));
  private static final Array<Coordinate> P7_P8_RIVER_PASTURE_COORDINATES =
      Array.of(Coordinates.of(12, 4), Coordinates.of(14, 4), Coordinates.of(8, 6));
  private static final Array<Coordinate> P7_P8_RIVER_SWAMP_COORDINATES =
      Array.of(Coordinates.of(7, 1), Coordinates.of(9, 7));
  private static final Array<Coordinate> P7_P8_RIVER_RIVER_COORDINATES =
      Array.of(
          Coordinates.onEdges(7, 1, BOTTOM_LEFT, TOP_LEFT),
          Coordinates.onEdges(6, 2, TOP_RIGHT, BOTTOM_LEFT),
          Coordinates.onEdges(5, 3, TOP_RIGHT),
          Coordinates.onEdges(6, 4, BOTTOM_RIGHT),
          Coordinates.onEdges(10, 4, RIGHT),
          Coordinates.onEdges(12, 4, RIGHT, LEFT),
          Coordinates.onEdges(14, 4, TOP_RIGHT, LEFT),
          Coordinates.onEdges(7, 5, BOTTOM_RIGHT, TOP_LEFT),
          Coordinates.onEdges(8, 6, BOTTOM_RIGHT, TOP_LEFT),
          Coordinates.onEdges(9, 7, BOTTOM_LEFT, TOP_LEFT));
  private static final Map<String, Array<Coordinate>> P7_P8_COORDINATES =
      HashMap.of(
          "non-river-land",
          P7_P8_NON_RIVER_LAND_COORDINATES,
          "non-river-harbor",
          P7_P8_NON_RIVER_HARBOR_COORDINATES,
          "river-mountain",
          P7_P8_RIVER_MOUNTAIN_COORDINATES,
          "river-hill",
          P7_P8_RIVER_HILL_COORDINATES,
          "river-pasture",
          P7_P8_RIVER_PASTURE_COORDINATES,
          "river-swamp",
          P7_P8_RIVER_SWAMP_COORDINATES,
          "river-river",
          P7_P8_RIVER_RIVER_COORDINATES);

  private static final Array<Chit> P7_P8_PRODUCING_CHITS =
      Array.of(CHIT_2, CHIT_12, CHITS_2_12)
          .appendAll(Array.fill(4, CHIT_3))
          .appendAll(Array.fill(4, CHIT_4))
          .appendAll(Array.fill(4, CHIT_5))
          .appendAll(Array.fill(4, CHIT_6))
          .appendAll(Array.fill(4, CHIT_8))
          .appendAll(Array.fill(4, CHIT_9))
          .appendAll(Array.fill(4, CHIT_10))
          .appendAll(Array.fill(4, CHIT_11));
  private static final Map<String, Array<Chit>> P7_P8_CHITS =
      HashMap.of("producing", P7_P8_PRODUCING_CHITS);

  private static final SpecificationImpl.Builder P7_P8_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P7_P8_TILES,
          P7_P8_COORDINATES,
          P7_P8_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("non-river-land"),
              TileMappingUtils.newSelfReferringEntry("non-river-harbor"),
              TileMappingUtils.newSelfReferringEntry("river-mountain"),
              TileMappingUtils.newSelfReferringEntry("river-hill"),
              TileMappingUtils.newSelfReferringEntry("river-pasture"),
              TileMappingUtils.newSelfReferringEntry("river-swamp"),
              TileMappingUtils.newSelfReferringEntry("river-river")),
          HashMap.ofEntries(
              TileMappingUtils.newEntry(
                  "producing", "non-river-land", "river-mountain", "river-hill", "river-pasture")));

  public static final SpecificationImpl P7_P8_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P7_P8_FISHERMEN_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.withFisheries(P7_P8_NON_RIVER_FISHERY_COORDINATES).build();
}
