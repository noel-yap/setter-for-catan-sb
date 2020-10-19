package noelyap.setterforcatan.scenario;

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
import static noelyap.setterforcatan.component.Tiles.FIELD;
import static noelyap.setterforcatan.component.Tiles.FISHERMEN_OF_CATAN_DESERT_CONVERTIBLE_TO_LAKE;
import static noelyap.setterforcatan.component.Tiles.FOREST;
import static noelyap.setterforcatan.component.Tiles.GENERIC_HARBOR;
import static noelyap.setterforcatan.component.Tiles.GRAIN_HARBOR;
import static noelyap.setterforcatan.component.Tiles.HILL;
import static noelyap.setterforcatan.component.Tiles.LUMBER_HARBOR;
import static noelyap.setterforcatan.component.Tiles.MOUNTAIN;
import static noelyap.setterforcatan.component.Tiles.PASTURE;
import static noelyap.setterforcatan.component.Tiles.TWO_FOR_ONE_HARBORS;
import static noelyap.setterforcatan.component.Tiles.WOOL_HARBOR;
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
import noelyap.setterforcatan.util.TileMappingUtils;

public class Base {
  public static final Array<Tile> P3_P4_PRODUCING_TILES =
      Array.fill(4, FIELD)
          .appendAll(Array.fill(4, FOREST))
          .appendAll(Array.fill(4, PASTURE))
          .appendAll(Array.fill(3, HILL))
          .appendAll(Array.fill(3, MOUNTAIN));
  public static final Array<Tile> P3_P4_NON_PRODUCING_TILES = Array.of(DESERT);
  public static final Array<Tile> P3_P4_HARBOR_TILES =
      Array.fill(4, GENERIC_HARBOR).appendAll(TWO_FOR_ONE_HARBORS);
  public static final Map<String, Array<Tile>> P3_P4_TILES =
      HashMap.of(
          "producing",
          P3_P4_PRODUCING_TILES,
          FISHERMEN_OF_CATAN_DESERT_CONVERTIBLE_TO_LAKE,
          P3_P4_NON_PRODUCING_TILES,
          "harbor",
          P3_P4_HARBOR_TILES);

  public static final Array<Coordinate> P3_P4_LAND_COORDINATES =
      Array.of(
          Coordinates.of(3, 1),
          Coordinates.of(5, 1),
          Coordinates.of(7, 1),
          Coordinates.of(2, 2),
          Coordinates.of(4, 2),
          Coordinates.of(6, 2),
          Coordinates.of(8, 2),
          Coordinates.of(1, 3),
          Coordinates.of(3, 3),
          Coordinates.of(5, 3),
          Coordinates.of(7, 3),
          Coordinates.of(9, 3),
          Coordinates.of(2, 4),
          Coordinates.of(4, 4),
          Coordinates.of(6, 4),
          Coordinates.of(8, 4),
          Coordinates.of(3, 5),
          Coordinates.of(5, 5),
          Coordinates.of(7, 5));
  public static final Array<Coordinate> P3_P4_HARBOR_COORDINATES =
      Array.of(
          Coordinates.onEdges(2, 0, BOTTOM_RIGHT),
          Coordinates.onEdges(6, 0, BOTTOM_LEFT),
          Coordinates.onEdges(9, 1, BOTTOM_LEFT),
          Coordinates.onEdges(0, 2, RIGHT),
          Coordinates.onEdges(11, 3, LEFT),
          Coordinates.onEdges(0, 4, RIGHT),
          Coordinates.onEdges(9, 5, TOP_LEFT),
          Coordinates.onEdges(6, 6, TOP_LEFT),
          Coordinates.onEdges(2, 6, TOP_RIGHT));
  public static final Array<Coordinate> P3_P4_FISHERY_COORDINATES =
      Array.of(
          Coordinates.onEdges(4, 0, BOTTOM_RIGHT, BOTTOM_LEFT),
          Coordinates.onEdges(1, 1, RIGHT, BOTTOM_RIGHT),
          Coordinates.onEdges(10, 2, BOTTOM_LEFT, LEFT),
          Coordinates.onEdges(10, 4, LEFT, TOP_LEFT),
          Coordinates.onEdges(1, 5, TOP_RIGHT, RIGHT),
          Coordinates.onEdges(4, 6, TOP_LEFT, TOP_RIGHT));
  public static final Map<String, Array<Coordinate>> P3_P4_COORDINATES =
      HashMap.of("land", P3_P4_LAND_COORDINATES, "harbor", P3_P4_HARBOR_COORDINATES);

  public static final Array<Chit> P3_P4_PRODUCING_CHITS =
      Array.of(CHIT_2, CHIT_12)
          .appendAll(Array.fill(2, CHIT_3))
          .appendAll(Array.fill(2, CHIT_4))
          .appendAll(Array.fill(2, CHIT_5))
          .appendAll(Array.fill(2, CHIT_6))
          .appendAll(Array.fill(2, CHIT_8))
          .appendAll(Array.fill(2, CHIT_9))
          .appendAll(Array.fill(2, CHIT_10))
          .appendAll(Array.fill(2, CHIT_11));
  public static final Map<String, Array<Chit>> P3_P4_CHITS =
      HashMap.of("producing", P3_P4_PRODUCING_CHITS);

  private static final SpecificationImpl.Builder P3_P4_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P3_P4_TILES,
          P3_P4_COORDINATES,
          P3_P4_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("harbor"),
              TileMappingUtils.newEntry(
                  "land", "producing", FISHERMEN_OF_CATAN_DESERT_CONVERTIBLE_TO_LAKE)),
          HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("producing")));

  public static final SpecificationImpl P3_P4_SPECIFICATION_IMPL =
      P3_P4_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P3_P4_FISHERMEN_SPECIFICATION_IMPL =
      P3_P4_SPECIFICATION_BUILDER.withFisheries(P3_P4_FISHERY_COORDINATES).build();

  public static final Array<Tile> P5_P6_PRODUCING_TILES =
      P3_P4_PRODUCING_TILES
          .appendAll(Array.fill(2, FIELD))
          .appendAll(Array.fill(2, FOREST))
          .appendAll(Array.fill(2, PASTURE))
          .appendAll(Array.fill(2, HILL))
          .appendAll(Array.fill(2, MOUNTAIN));
  public static final Array<Tile> P5_P6_NON_PRODUCING_LAND_TILES = Array.fill(2, DESERT);
  public static final Array<Tile> P5_P6_HARBOR_TILES =
      P3_P4_HARBOR_TILES.append(GENERIC_HARBOR).append(WOOL_HARBOR);
  public static final Map<String, Array<Tile>> P5_P6_TILES =
      HashMap.of(
          "producing",
          P5_P6_PRODUCING_TILES,
          FISHERMEN_OF_CATAN_DESERT_CONVERTIBLE_TO_LAKE,
          P5_P6_NON_PRODUCING_LAND_TILES,
          "harbor",
          P5_P6_HARBOR_TILES);

  public static final Array<Coordinate> P5_P6_LAND_COORDINATES =
      Array.of(
          Coordinates.of(5, 1),
          Coordinates.of(7, 1),
          Coordinates.of(9, 1),
          Coordinates.of(4, 2),
          Coordinates.of(6, 2),
          Coordinates.of(8, 2),
          Coordinates.of(10, 2),
          Coordinates.of(3, 3),
          Coordinates.of(5, 3),
          Coordinates.of(7, 3),
          Coordinates.of(9, 3),
          Coordinates.of(11, 3),
          Coordinates.of(2, 4),
          Coordinates.of(4, 4),
          Coordinates.of(6, 4),
          Coordinates.of(8, 4),
          Coordinates.of(10, 4),
          Coordinates.of(12, 4),
          Coordinates.of(3, 5),
          Coordinates.of(5, 5),
          Coordinates.of(7, 5),
          Coordinates.of(9, 5),
          Coordinates.of(11, 5),
          Coordinates.of(4, 6),
          Coordinates.of(6, 6),
          Coordinates.of(8, 6),
          Coordinates.of(10, 6),
          Coordinates.of(5, 7),
          Coordinates.of(7, 7),
          Coordinates.of(9, 7));
  public static final Array<Coordinate> P5_P6_HARBOR_COORDINATES =
      Array.of(
          Coordinates.onEdges(4, 0, BOTTOM_RIGHT),
          Coordinates.onEdges(8, 0, BOTTOM_LEFT),
          Coordinates.onEdges(11, 1, BOTTOM_LEFT),
          Coordinates.onEdges(1, 3, RIGHT),
          Coordinates.onEdges(14, 4, LEFT),
          Coordinates.onEdges(1, 5, TOP_RIGHT),
          Coordinates.onEdges(2, 6, RIGHT),
          Coordinates.onEdges(12, 6, TOP_LEFT),
          Coordinates.onEdges(11, 7, LEFT),
          Coordinates.onEdges(8, 8, TOP_LEFT),
          Coordinates.onEdges(4, 8, TOP_RIGHT));
  public static final Array<Coordinate> P5_P6_FISHERY_COORDINATES =
      Array.of(
          Coordinates.onEdges(6, 0, BOTTOM_RIGHT, BOTTOM_LEFT),
          Coordinates.onEdges(4, 2, LEFT, TOP_LEFT),
          Coordinates.onEdges(11, 3, TOP_RIGHT, RIGHT),
          Coordinates.onEdges(3, 5, BOTTOM_LEFT, LEFT),
          Coordinates.onEdges(13, 5, LEFT, TOP_LEFT),
          Coordinates.onEdges(10, 6, RIGHT, BOTTOM_RIGHT),
          Coordinates.onEdges(3, 7, TOP_RIGHT, RIGHT),
          Coordinates.onEdges(6, 8, TOP_LEFT, TOP_RIGHT));
  public static final Map<String, Array<Coordinate>> P5_P6_COORDINATES =
      HashMap.of("land", P5_P6_LAND_COORDINATES, "harbor", P5_P6_HARBOR_COORDINATES);

  public static final Array<Chit> P5_P6_PRODUCING_CHITS =
      P3_P4_PRODUCING_CHITS.appendAll(
          Array.of(
              CHIT_2, CHIT_3, CHIT_4, CHIT_5, CHIT_6, CHIT_8, CHIT_9, CHIT_10, CHIT_11, CHIT_12));
  public static final Map<String, Array<Chit>> P5_P6_CHITS =
      HashMap.of("producing", P5_P6_PRODUCING_CHITS);

  private static final SpecificationImpl.Builder P5_P6_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P5_P6_TILES,
          P5_P6_COORDINATES,
          P5_P6_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("harbor"),
              TileMappingUtils.newEntry(
                  "land", "producing", FISHERMEN_OF_CATAN_DESERT_CONVERTIBLE_TO_LAKE)),
          HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("producing")));

  public static final SpecificationImpl P5_P6_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P5_P6_FISHERMEN_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.withFisheries(P5_P6_FISHERY_COORDINATES).build();

  public static final Array<Tile> P7_P8_PRODUCING_TILES =
      P3_P4_PRODUCING_TILES.appendAll(P3_P4_PRODUCING_TILES);
  public static final Array<Tile> P7_P8_NON_PRODUCING_LAND_TILES = P3_P4_NON_PRODUCING_TILES;
  public static final Array<Tile> P7_P8_HARBOR_TILES =
      P3_P4_HARBOR_TILES.append(GRAIN_HARBOR).append(LUMBER_HARBOR).append(WOOL_HARBOR);
  public static final Map<String, Array<Tile>> P7_P8_TILES =
      HashMap.of(
          "producing",
          P7_P8_PRODUCING_TILES,
          FISHERMEN_OF_CATAN_DESERT_CONVERTIBLE_TO_LAKE,
          P7_P8_NON_PRODUCING_LAND_TILES,
          "harbor",
          P7_P8_HARBOR_TILES);

  public static final Array<Coordinate> P7_P8_LAND_COORDINATES =
      Array.of(
          Coordinates.of(5, 1),
          Coordinates.of(7, 1),
          Coordinates.of(9, 1),
          Coordinates.of(11, 1),
          Coordinates.of(4, 2),
          Coordinates.of(6, 2),
          Coordinates.of(8, 2),
          Coordinates.of(10, 2),
          Coordinates.of(12, 2),
          Coordinates.of(3, 3),
          Coordinates.of(5, 3),
          Coordinates.of(7, 3),
          Coordinates.of(9, 3),
          Coordinates.of(11, 3),
          Coordinates.of(13, 3),
          Coordinates.of(2, 4),
          Coordinates.of(4, 4),
          Coordinates.of(6, 4),
          Coordinates.of(8, 4),
          Coordinates.of(10, 4),
          Coordinates.of(12, 4),
          Coordinates.of(14, 4),
          Coordinates.of(3, 5),
          Coordinates.of(5, 5),
          Coordinates.of(7, 5),
          Coordinates.of(9, 5),
          Coordinates.of(11, 5),
          Coordinates.of(13, 5),
          Coordinates.of(4, 6),
          Coordinates.of(6, 6),
          Coordinates.of(8, 6),
          Coordinates.of(10, 6),
          Coordinates.of(12, 6),
          Coordinates.of(5, 7),
          Coordinates.of(7, 7),
          Coordinates.of(9, 7),
          Coordinates.of(11, 7));
  public static final Array<Coordinate> P7_P8_HARBOR_COORDINATES =
      Array.of(
          Coordinates.onEdges(4, 0, BOTTOM_RIGHT),
          Coordinates.onEdges(8, 0, BOTTOM_LEFT),
          Coordinates.onEdges(10, 0, BOTTOM_RIGHT),
          Coordinates.onEdges(13, 1, BOTTOM_LEFT),
          Coordinates.onEdges(1, 3, RIGHT),
          Coordinates.onEdges(16, 4, LEFT),
          Coordinates.onEdges(1, 5, TOP_RIGHT),
          Coordinates.onEdges(2, 6, RIGHT),
          Coordinates.onEdges(14, 6, TOP_LEFT),
          Coordinates.onEdges(13, 7, LEFT),
          Coordinates.onEdges(4, 8, TOP_RIGHT),
          Coordinates.onEdges(10, 8, TOP_LEFT));
  public static final Array<Coordinate> P7_P8_FISHERY_COORDINATES =
      Array.of(
          Coordinates.onEdges(6, 0, BOTTOM_RIGHT, BOTTOM_LEFT),
          Coordinates.onEdges(11, 1, TOP_RIGHT, RIGHT),
          Coordinates.onEdges(2, 2, RIGHT, BOTTOM_RIGHT),
          Coordinates.onEdges(13, 3, TOP_RIGHT, RIGHT),
          Coordinates.onEdges(3, 5, BOTTOM_LEFT, LEFT),
          Coordinates.onEdges(15, 5, LEFT, TOP_LEFT),
          Coordinates.onEdges(11, 7, BOTTOM_RIGHT, BOTTOM_LEFT),
          Coordinates.onEdges(6, 8, TOP_LEFT, TOP_RIGHT));
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
              TileMappingUtils.newEntry(
                  "land", "producing", FISHERMEN_OF_CATAN_DESERT_CONVERTIBLE_TO_LAKE)),
          HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("producing")));

  public static final SpecificationImpl P7_P8_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P7_P8_FISHERMEN_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.withFisheries(P7_P8_FISHERY_COORDINATES).build();
}
