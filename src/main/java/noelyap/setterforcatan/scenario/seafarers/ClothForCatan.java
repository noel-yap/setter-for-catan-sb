package noelyap.setterforcatan.scenario.seafarers;

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
import static noelyap.setterforcatan.component.Tiles.CHIT;
import static noelyap.setterforcatan.component.Tiles.DESERT;
import static noelyap.setterforcatan.component.Tiles.FIELD;
import static noelyap.setterforcatan.component.Tiles.FOREST;
import static noelyap.setterforcatan.component.Tiles.GENERIC_HARBOR;
import static noelyap.setterforcatan.component.Tiles.GOLD_FIELD;
import static noelyap.setterforcatan.component.Tiles.HILL;
import static noelyap.setterforcatan.component.Tiles.LUMBER_HARBOR;
import static noelyap.setterforcatan.component.Tiles.MOUNTAIN;
import static noelyap.setterforcatan.component.Tiles.PASTURE;
import static noelyap.setterforcatan.component.Tiles.TWO_FOR_ONE_HARBORS;

import io.vavr.collection.Array;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import noelyap.setterforcatan.component.Coordinates;
import noelyap.setterforcatan.component.SpecificationImpl;
import noelyap.setterforcatan.protogen.ChitOuterClass.Chit;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Coordinate;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Vertex;
import noelyap.setterforcatan.protogen.TileOuterClass.Tile;
import noelyap.setterforcatan.scenario.Base;
import noelyap.setterforcatan.util.TileMappingUtils;

public class ClothForCatan {
  private static final Array<Tile> TWO_CHIT_TILES = Array.fill(2, CHIT);
  private static final Array<Tile> FOUR_CHIT_TILES = TWO_CHIT_TILES.appendAll(TWO_CHIT_TILES);

  private static final Array<Tile> P3_P4_MAIN_ISLAND_LAND_TILES =
      Array.fill(3, HILL)
          .appendAll(Array.fill(4, FOREST))
          .appendAll(Array.fill(4, MOUNTAIN))
          .appendAll(Array.fill(4, PASTURE))
          .appendAll(Array.fill(5, FIELD));
  private static final Array<Tile> P3_P4_MAIN_ISLAND_HARBOR_TILES = Base.P3_P4_HARBOR_TILES;
  private static final Array<Tile> P3_P4_SMALL_ISLAND_LAND_TILES =
      Array.fill(2, DESERT).appendAll(Array.fill(2, GOLD_FIELD));
  private static final Array<Tile> P3_P4_SMALL_ISLAND_3_11_CHIT_TILES = TWO_CHIT_TILES;
  private static final Array<Tile> P3_P4_SMALL_ISLAND_4_10_CHIT_TILES = TWO_CHIT_TILES;
  private static final Array<Tile> P3_P4_SMALL_ISLAND_5_9_CHIT_TILES = TWO_CHIT_TILES;
  private static final Array<Tile> P3_P4_SMALL_ISLAND_6_8_CHIT_TILES = TWO_CHIT_TILES;
  private static final Map<String, Array<Tile>> P3_P4_TILES =
      HashMap.of(
          "main-island-land",
          P3_P4_MAIN_ISLAND_LAND_TILES,
          "main-island-harbor",
          P3_P4_MAIN_ISLAND_HARBOR_TILES,
          "small-island-land",
          P3_P4_SMALL_ISLAND_LAND_TILES,
          "small-island-3-11-chit",
          P3_P4_SMALL_ISLAND_3_11_CHIT_TILES,
          "small-island-4-10-chit",
          P3_P4_SMALL_ISLAND_4_10_CHIT_TILES,
          "small-island-5-9-chit",
          P3_P4_SMALL_ISLAND_5_9_CHIT_TILES,
          "small-island-6-8-chit",
          P3_P4_SMALL_ISLAND_6_8_CHIT_TILES);

  private static final Array<Coordinate> P3_P4_MAIN_ISLAND_LAND_COORDINATES =
      Array.of(
          Coordinates.of(3, 1),
          Coordinates.of(5, 1),
          Coordinates.of(7, 1),
          Coordinates.of(9, 1),
          Coordinates.of(11, 1),
          Coordinates.of(2, 2),
          Coordinates.of(4, 2),
          Coordinates.of(10, 2),
          Coordinates.of(12, 2),
          Coordinates.of(1, 3),
          Coordinates.of(13, 5),
          Coordinates.of(2, 6),
          Coordinates.of(4, 6),
          Coordinates.of(10, 6),
          Coordinates.of(12, 6),
          Coordinates.of(3, 7),
          Coordinates.of(5, 7),
          Coordinates.of(7, 7),
          Coordinates.of(9, 7),
          Coordinates.of(11, 7));
  private static final Array<Coordinate> P3_P4_MAIN_ISLAND_HARBOR_COORDINATES =
      Array.of(
          Coordinates.onEdges(4, 0, Edge.Position.BOTTOM_LEFT),
          Coordinates.onEdges(8, 0, Edge.Position.BOTTOM_RIGHT),
          Coordinates.onEdges(0, 2, Edge.Position.RIGHT),
          Coordinates.onEdges(14, 2, Edge.Position.LEFT),
          Coordinates.onEdges(15, 5, Edge.Position.LEFT),
          Coordinates.onEdges(0, 6, Edge.Position.RIGHT),
          Coordinates.onEdges(13, 7, Edge.Position.TOP_LEFT),
          Coordinates.onEdges(4, 8, Edge.Position.TOP_RIGHT),
          Coordinates.onEdges(10, 8, Edge.Position.TOP_LEFT));
  private static final Array<Coordinate> P3_P4_MAIN_ISLAND_FISHERY_COORDINATES =
      Array.of(
          Coordinates.onEdges(6, 0, Edge.Position.BOTTOM_RIGHT, Edge.Position.BOTTOM_LEFT),
          Coordinates.onEdges(13, 1, Edge.Position.BOTTOM_LEFT, Edge.Position.LEFT),
          Coordinates.onEdges(1, 3, Edge.Position.LEFT, Edge.Position.TOP_LEFT),
          Coordinates.onEdges(14, 6, Edge.Position.LEFT, Edge.Position.TOP_LEFT),
          Coordinates.onEdges(1, 7, Edge.Position.TOP_RIGHT, Edge.Position.RIGHT),
          Coordinates.onEdges(8, 8, Edge.Position.TOP_LEFT, Edge.Position.TOP_RIGHT));
  private static final Array<Coordinate> P3_P4_SMALL_ISLAND_LAND_COORDINATES =
      Array.of(
          Coordinates.of(7, 3), Coordinates.of(4, 4), Coordinates.of(10, 4), Coordinates.of(7, 5));
  private static final Array<Coordinate> P3_P4_SMALL_ISLAND_3_11_CHIT_COORDINATES =
      Array.of(
          Coordinates.onVertices(7, 3, Vertex.Position.TOP),
          Coordinates.onVertices(7, 5, Vertex.Position.BOTTOM));
  private static final Array<Coordinate> P3_P4_SMALL_ISLAND_4_10_CHIT_COORDINATES =
      Array.of(
          Coordinates.onVertices(4, 4, Vertex.Position.BOTTOM),
          Coordinates.onVertices(10, 4, Vertex.Position.BOTTOM));
  private static final Array<Coordinate> P3_P4_SMALL_ISLAND_5_9_CHIT_COORDINATES =
      Array.of(
          Coordinates.onVertices(4, 4, Vertex.Position.TOP),
          Coordinates.onVertices(10, 4, Vertex.Position.TOP));
  private static final Array<Coordinate> P3_P4_SMALL_ISLAND_6_8_CHIT_COORDINATES =
      Array.of(
          Coordinates.onVertices(7, 3, Vertex.Position.BOTTOM),
          Coordinates.onVertices(7, 5, Vertex.Position.TOP));
  private static final Map<String, Array<Coordinate>> P3_P4_COORDINATES =
      HashMap.of(
          "main-island-land",
          P3_P4_MAIN_ISLAND_LAND_COORDINATES,
          "main-island-harbor",
          P3_P4_MAIN_ISLAND_HARBOR_COORDINATES,
          "small-island-land",
          P3_P4_SMALL_ISLAND_LAND_COORDINATES,
          "small-island-3-11-chit",
          P3_P4_SMALL_ISLAND_3_11_CHIT_COORDINATES,
          "small-island-4-10-chit",
          P3_P4_SMALL_ISLAND_4_10_CHIT_COORDINATES,
          "small-island-5-9-chit",
          P3_P4_SMALL_ISLAND_5_9_CHIT_COORDINATES,
          "small-island-6-8-chit",
          P3_P4_SMALL_ISLAND_6_8_CHIT_COORDINATES);

  private static final Array<Chit> P3_P4_MAIN_ISLAND_LAND_CHITS =
      Base.P3_P4_PRODUCING_CHITS.appendAll(Array.of(CHIT_2, CHIT_12));
  private static final Array<Chit> P3_P4_SMALL_ISLAND_3_11_CHIT_CHITS = Array.of(CHIT_3, CHIT_11);
  private static final Array<Chit> P3_P4_SMALL_ISLAND_4_10_CHIT_CHITS = Array.of(CHIT_4, CHIT_10);
  private static final Array<Chit> P3_P4_SMALL_ISLAND_5_9_CHIT_CHITS = Array.of(CHIT_5, CHIT_9);
  private static final Array<Chit> P3_P4_SMALL_ISLAND_6_8_CHIT_CHITS = Array.of(CHIT_6, CHIT_8);
  private static final Map<String, Array<Chit>> P3_P4_CHITS =
      HashMap.of(
          "main-island-land",
          P3_P4_MAIN_ISLAND_LAND_CHITS,
          "small-island-3-11-chit",
          P3_P4_SMALL_ISLAND_3_11_CHIT_CHITS,
          "small-island-4-10-chit",
          P3_P4_SMALL_ISLAND_4_10_CHIT_CHITS,
          "small-island-5-9-chit",
          P3_P4_SMALL_ISLAND_5_9_CHIT_CHITS,
          "small-island-6-8-chit",
          P3_P4_SMALL_ISLAND_6_8_CHIT_CHITS);

  private static final SpecificationImpl.Builder P3_P4_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P3_P4_TILES,
          P3_P4_COORDINATES,
          P3_P4_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("main-island-land"),
              TileMappingUtils.newSelfReferringEntry("main-island-harbor"),
              TileMappingUtils.newSelfReferringEntry("small-island-land"),
              TileMappingUtils.newSelfReferringEntry("small-island-3-11-chit"),
              TileMappingUtils.newSelfReferringEntry("small-island-4-10-chit"),
              TileMappingUtils.newSelfReferringEntry("small-island-5-9-chit"),
              TileMappingUtils.newSelfReferringEntry("small-island-6-8-chit")),
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("main-island-land"),
              TileMappingUtils.newSelfReferringEntry("small-island-3-11-chit"),
              TileMappingUtils.newSelfReferringEntry("small-island-4-10-chit"),
              TileMappingUtils.newSelfReferringEntry("small-island-5-9-chit"),
              TileMappingUtils.newSelfReferringEntry("small-island-6-8-chit")));

  public static final SpecificationImpl P3_P4_SPECIFICATION_IMPL =
      P3_P4_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P3_P4_FISHERMEN_SPECIFICATION_IMPL =
      P3_P4_SPECIFICATION_BUILDER.withFisheries(P3_P4_MAIN_ISLAND_FISHERY_COORDINATES).build();

  private static final Array<Tile> P5_P6_MAIN_ISLAND_LAND_TILES =
      Array.fill(4, HILL)
          .appendAll(Array.fill(5, MOUNTAIN))
          .appendAll(Array.fill(5, PASTURE))
          .appendAll(Array.fill(6, FIELD))
          .appendAll(Array.fill(6, FOREST));
  private static final Array<Tile> P5_P6_MAIN_ISLAND_HARBOR_TILES =
      TWO_FOR_ONE_HARBORS.append(LUMBER_HARBOR).appendAll(Array.fill(5, GENERIC_HARBOR));
  private static final Array<Tile> P5_P6_SMALL_ISLAND_LAND_TILES =
      P3_P4_SMALL_ISLAND_LAND_TILES.appendAll(Array.fill(2, DESERT));
  private static final Array<Tile> P5_P6_SMALL_ISLAND_2_12_CHIT_TILES = TWO_CHIT_TILES;
  private static final Array<Tile> P5_P6_SMALL_ISLAND_4_10_CHIT_TILES = FOUR_CHIT_TILES;
  private static final Array<Tile> P5_P6_SMALL_ISLAND_5_9_CHIT_TILES = FOUR_CHIT_TILES;
  private static final Array<Tile> P5_P6_SMALL_ISLAND_6_8_CHIT_TILES = TWO_CHIT_TILES;
  private static final Map<String, Array<Tile>> P5_P6_TILES =
      HashMap.of(
          "main-island-land",
          P5_P6_MAIN_ISLAND_LAND_TILES,
          "main-island-harbor",
          P5_P6_MAIN_ISLAND_HARBOR_TILES,
          "small-island-land",
          P5_P6_SMALL_ISLAND_LAND_TILES,
          "small-island-2-12-chit",
          P5_P6_SMALL_ISLAND_2_12_CHIT_TILES,
          "small-island-4-10-chit",
          P5_P6_SMALL_ISLAND_4_10_CHIT_TILES,
          "small-island-5-9-chit",
          P5_P6_SMALL_ISLAND_5_9_CHIT_TILES,
          "small-island-6-8-chit",
          P5_P6_SMALL_ISLAND_6_8_CHIT_TILES);

  private static final Array<Coordinate> P5_P6_MAIN_ISLAND_LAND_COORDINATES =
      Array.of(
          Coordinates.of(3, 1),
          Coordinates.of(5, 1),
          Coordinates.of(7, 1),
          Coordinates.of(9, 1),
          Coordinates.of(11, 1),
          Coordinates.of(13, 1),
          Coordinates.of(15, 1),
          Coordinates.of(2, 2),
          Coordinates.of(4, 2),
          Coordinates.of(14, 2),
          Coordinates.of(16, 2),
          Coordinates.of(1, 3),
          Coordinates.of(17, 3),
          Coordinates.of(1, 5),
          Coordinates.of(17, 5),
          Coordinates.of(2, 6),
          Coordinates.of(4, 6),
          Coordinates.of(14, 6),
          Coordinates.of(16, 6),
          Coordinates.of(3, 7),
          Coordinates.of(5, 7),
          Coordinates.of(7, 7),
          Coordinates.of(9, 7),
          Coordinates.of(11, 7),
          Coordinates.of(13, 7),
          Coordinates.of(15, 7));
  private static final Array<Coordinate> P5_P6_MAIN_ISLAND_HARBOR_COORDINATES =
      Array.of(
          Coordinates.onEdges(4, 0, Edge.Position.BOTTOM_LEFT),
          Coordinates.onEdges(8, 0, Edge.Position.BOTTOM_RIGHT),
          Coordinates.onEdges(12, 0, Edge.Position.BOTTOM_RIGHT),
          Coordinates.onEdges(0, 2, Edge.Position.RIGHT),
          Coordinates.onEdges(18, 2, Edge.Position.LEFT),
          Coordinates.onEdges(19, 5, Edge.Position.LEFT),
          Coordinates.onEdges(0, 6, Edge.Position.RIGHT),
          Coordinates.onEdges(17, 7, Edge.Position.TOP_LEFT),
          Coordinates.onEdges(4, 8, Edge.Position.TOP_RIGHT),
          Coordinates.onEdges(8, 8, Edge.Position.TOP_LEFT),
          Coordinates.onEdges(14, 8, Edge.Position.TOP_LEFT));
  private static final Array<Coordinate> P5_P6_MAIN_ISLAND_FISHERY_COORDINATES =
      Array.of(
          Coordinates.onEdges(6, 0, Edge.Position.BOTTOM_RIGHT, Edge.Position.BOTTOM_LEFT),
          Coordinates.onEdges(10, 0, Edge.Position.BOTTOM_RIGHT, Edge.Position.BOTTOM_LEFT),
          Coordinates.onEdges(1, 1, Edge.Position.RIGHT, Edge.Position.BOTTOM_RIGHT),
          Coordinates.onEdges(17, 1, Edge.Position.BOTTOM_LEFT, Edge.Position.LEFT),
          Coordinates.onEdges(18, 6, Edge.Position.LEFT, Edge.Position.TOP_LEFT),
          Coordinates.onEdges(1, 7, Edge.Position.TOP_RIGHT, Edge.Position.RIGHT),
          Coordinates.onEdges(6, 8, Edge.Position.TOP_LEFT, Edge.Position.TOP_RIGHT),
          Coordinates.onEdges(12, 8, Edge.Position.TOP_LEFT, Edge.Position.TOP_RIGHT));
  private static final Array<Coordinate> P5_P6_SMALL_ISLAND_LAND_COORDINATES =
      Array.of(
          Coordinates.of(7, 3),
          Coordinates.of(11, 3),
          Coordinates.of(4, 4),
          Coordinates.of(14, 4),
          Coordinates.of(7, 5),
          Coordinates.of(11, 5));
  private static final Array<Coordinate> P5_P6_SMALL_ISLAND_2_12_CHIT_COORDINATES =
      Array.of(
          Coordinates.onVertices(7, 3, Vertex.Position.TOP),
          Coordinates.onVertices(7, 5, Vertex.Position.BOTTOM));
  private static final Array<Coordinate> P5_P6_SMALL_ISLAND_4_10_CHIT_COORDINATES =
      Array.of(
          Coordinates.onVertices(11, 3, Vertex.Position.TOP),
          Coordinates.onVertices(4, 4, Vertex.Position.TOP),
          Coordinates.onVertices(14, 4, Vertex.Position.TOP),
          Coordinates.onVertices(11, 5, Vertex.Position.BOTTOM));
  private static final Array<Coordinate> P5_P6_SMALL_ISLAND_5_9_CHIT_COORDINATES =
      Array.of(
          Coordinates.onVertices(7, 3, Vertex.Position.BOTTOM),
          Coordinates.onVertices(4, 4, Vertex.Position.BOTTOM),
          Coordinates.onVertices(14, 4, Vertex.Position.BOTTOM),
          Coordinates.onVertices(11, 5, Vertex.Position.TOP));
  private static final Array<Coordinate> P5_P6_SMALL_ISLAND_6_8_CHIT_COORDINATES =
      Array.of(
          Coordinates.onVertices(11, 3, Vertex.Position.BOTTOM),
          Coordinates.onVertices(7, 5, Vertex.Position.TOP));
  private static final Map<String, Array<Coordinate>> P5_P6_COORDINATES =
      HashMap.of(
          "main-island-land",
          P5_P6_MAIN_ISLAND_LAND_COORDINATES,
          "main-island-harbor",
          P5_P6_MAIN_ISLAND_HARBOR_COORDINATES,
          "small-island-land",
          P5_P6_SMALL_ISLAND_LAND_COORDINATES,
          "small-island-2-12-chit",
          P5_P6_SMALL_ISLAND_2_12_CHIT_COORDINATES,
          "small-island-4-10-chit",
          P5_P6_SMALL_ISLAND_4_10_CHIT_COORDINATES,
          "small-island-5-9-chit",
          P5_P6_SMALL_ISLAND_5_9_CHIT_COORDINATES,
          "small-island-6-8-chit",
          P5_P6_SMALL_ISLAND_6_8_CHIT_COORDINATES);

  private static final Array<Chit> P5_P6_MAIN_ISLAND_LAND_CHITS =
      P3_P4_MAIN_ISLAND_LAND_CHITS
          .appendAll(Array.of(CHIT_6, CHIT_8))
          .appendAll(Array.fill(2, CHIT_3))
          .appendAll(Array.fill(2, CHIT_11));
  private static final Array<Chit> P5_P6_SMALL_ISLAND_2_12_CHIT_CHITS = Array.of(CHIT_2, CHIT_12);
  private static final Array<Chit> P5_P6_SMALL_ISLAND_4_10_CHIT_CHITS =
      P3_P4_SMALL_ISLAND_4_10_CHIT_CHITS.appendAll(P3_P4_SMALL_ISLAND_4_10_CHIT_CHITS);
  private static final Array<Chit> P5_P6_SMALL_ISLAND_5_9_CHIT_CHITS =
      P3_P4_SMALL_ISLAND_5_9_CHIT_CHITS.appendAll(P3_P4_SMALL_ISLAND_5_9_CHIT_CHITS);
  private static final Array<Chit> P5_P6_SMALL_ISLAND_6_8_CHIT_CHITS =
      P3_P4_SMALL_ISLAND_6_8_CHIT_CHITS;
  private static final Map<String, Array<Chit>> P5_P6_CHITS =
      HashMap.of(
          "main-island-land",
          P5_P6_MAIN_ISLAND_LAND_CHITS,
          "small-island-2-12-chit",
          P5_P6_SMALL_ISLAND_2_12_CHIT_CHITS,
          "small-island-4-10-chit",
          P5_P6_SMALL_ISLAND_4_10_CHIT_CHITS,
          "small-island-5-9-chit",
          P5_P6_SMALL_ISLAND_5_9_CHIT_CHITS,
          "small-island-6-8-chit",
          P5_P6_SMALL_ISLAND_6_8_CHIT_CHITS);

  private static final SpecificationImpl.Builder P5_P6_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P5_P6_TILES,
          P5_P6_COORDINATES,
          P5_P6_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("main-island-land"),
              TileMappingUtils.newSelfReferringEntry("main-island-harbor"),
              TileMappingUtils.newSelfReferringEntry("small-island-land"),
              TileMappingUtils.newSelfReferringEntry("small-island-2-12-chit"),
              TileMappingUtils.newSelfReferringEntry("small-island-4-10-chit"),
              TileMappingUtils.newSelfReferringEntry("small-island-5-9-chit"),
              TileMappingUtils.newSelfReferringEntry("small-island-6-8-chit")),
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("main-island-land"),
              TileMappingUtils.newSelfReferringEntry("small-island-2-12-chit"),
              TileMappingUtils.newSelfReferringEntry("small-island-4-10-chit"),
              TileMappingUtils.newSelfReferringEntry("small-island-5-9-chit"),
              TileMappingUtils.newSelfReferringEntry("small-island-6-8-chit")));

  public static final SpecificationImpl P5_P6_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P5_P6_FISHERMEN_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.withFisheries(P5_P6_MAIN_ISLAND_FISHERY_COORDINATES).build();

  private static final Array<Tile> P7_P8_MAIN_ISLAND_LAND_TILES =
      Array.fill(6, FIELD)
          .appendAll(Array.fill(6, HILL))
          .appendAll(Array.fill(6, MOUNTAIN))
          .appendAll(Array.fill(6, PASTURE))
          .appendAll(Array.fill(8, FOREST));
  private static final Array<Tile> P7_P8_MAIN_ISLAND_HARBOR_TILES = Base.P7_P8_HARBOR_TILES;
  private static final Array<Tile> P7_P8_SMALL_ISLAND_LAND_TILES =
      P5_P6_SMALL_ISLAND_LAND_TILES.appendAll(Array.fill(2, DESERT));
  private static final Array<Tile> P7_P8_SMALL_ISLAND_2_12_CHIT_TILES = FOUR_CHIT_TILES;
  private static final Array<Tile> P7_P8_SMALL_ISLAND_4_10_CHIT_TILES = FOUR_CHIT_TILES;
  private static final Array<Tile> P7_P8_SMALL_ISLAND_5_9_CHIT_TILES = FOUR_CHIT_TILES;
  private static final Array<Tile> P7_P8_SMALL_ISLAND_6_8_CHIT_TILES = FOUR_CHIT_TILES;
  private static final Map<String, Array<Tile>> P7_P8_TILES =
      HashMap.of(
          "main-island-land",
          P7_P8_MAIN_ISLAND_LAND_TILES,
          "main-island-harbor",
          P7_P8_MAIN_ISLAND_HARBOR_TILES,
          "small-island-land",
          P7_P8_SMALL_ISLAND_LAND_TILES,
          "small-island-2-12-chit",
          P7_P8_SMALL_ISLAND_2_12_CHIT_TILES,
          "small-island-4-10-chit",
          P7_P8_SMALL_ISLAND_4_10_CHIT_TILES,
          "small-island-5-9-chit",
          P7_P8_SMALL_ISLAND_5_9_CHIT_TILES,
          "small-island-6-8-chit",
          P7_P8_SMALL_ISLAND_6_8_CHIT_TILES);

  private static final Array<Coordinate> P7_P8_MAIN_ISLAND_LAND_COORDINATES =
      Array.of(
          Coordinates.of(3, 1),
          Coordinates.of(5, 1),
          Coordinates.of(7, 1),
          Coordinates.of(9, 1),
          Coordinates.of(11, 1),
          Coordinates.of(13, 1),
          Coordinates.of(15, 1),
          Coordinates.of(17, 1),
          Coordinates.of(19, 1),
          Coordinates.of(21, 1),
          Coordinates.of(2, 2),
          Coordinates.of(4, 2),
          Coordinates.of(20, 2),
          Coordinates.of(22, 2),
          Coordinates.of(1, 3),
          Coordinates.of(23, 3),
          Coordinates.of(1, 5),
          Coordinates.of(23, 5),
          Coordinates.of(2, 6),
          Coordinates.of(4, 6),
          Coordinates.of(20, 6),
          Coordinates.of(22, 6),
          Coordinates.of(3, 7),
          Coordinates.of(5, 7),
          Coordinates.of(7, 7),
          Coordinates.of(9, 7),
          Coordinates.of(11, 7),
          Coordinates.of(13, 7),
          Coordinates.of(15, 7),
          Coordinates.of(17, 7),
          Coordinates.of(19, 7),
          Coordinates.of(21, 7));
  private static final Array<Coordinate> P7_P8_MAIN_ISLAND_HARBOR_COORDINATES =
      Array.of(
          Coordinates.onEdges(4, 0, Edge.Position.BOTTOM_RIGHT),
          Coordinates.onEdges(10, 0, Edge.Position.BOTTOM_LEFT),
          Coordinates.onEdges(14, 0, Edge.Position.BOTTOM_RIGHT),
          Coordinates.onEdges(20, 0, Edge.Position.BOTTOM_LEFT),
          Coordinates.onEdges(0, 2, Edge.Position.RIGHT),
          Coordinates.onEdges(24, 2, Edge.Position.LEFT),
          Coordinates.onEdges(0, 6, Edge.Position.RIGHT),
          Coordinates.onEdges(24, 6, Edge.Position.LEFT),
          Coordinates.onEdges(4, 8, Edge.Position.TOP_RIGHT),
          Coordinates.onEdges(10, 8, Edge.Position.TOP_LEFT),
          Coordinates.onEdges(14, 8, Edge.Position.TOP_RIGHT),
          Coordinates.onEdges(20, 8, Edge.Position.TOP_LEFT));
  private static final Array<Coordinate> P7_P8_MAIN_ISLAND_FISHERY_COORDINATES =
      Array.of(
          Coordinates.onEdges(8, 0, Edge.Position.BOTTOM_RIGHT, Edge.Position.BOTTOM_LEFT),
          Coordinates.onEdges(16, 0, Edge.Position.BOTTOM_RIGHT, Edge.Position.BOTTOM_LEFT),
          Coordinates.onEdges(1, 1, Edge.Position.RIGHT, Edge.Position.BOTTOM_RIGHT),
          Coordinates.onEdges(23, 1, Edge.Position.BOTTOM_LEFT, Edge.Position.LEFT),
          Coordinates.onEdges(1, 7, Edge.Position.TOP_RIGHT, Edge.Position.RIGHT),
          Coordinates.onEdges(23, 7, Edge.Position.LEFT, Edge.Position.TOP_LEFT),
          Coordinates.onEdges(8, 8, Edge.Position.TOP_LEFT, Edge.Position.TOP_RIGHT),
          Coordinates.onEdges(16, 8, Edge.Position.TOP_LEFT, Edge.Position.TOP_RIGHT));
  private static final Array<Coordinate> P7_P8_SMALL_ISLAND_LAND_COORDINATES =
      Array.of(
          Coordinates.of(7, 3),
          Coordinates.of(17, 3),
          Coordinates.of(4, 4),
          Coordinates.of(10, 4),
          Coordinates.of(14, 4),
          Coordinates.of(20, 4),
          Coordinates.of(7, 5),
          Coordinates.of(17, 5));
  private static final Array<Coordinate> P7_P8_SMALL_ISLAND_2_12_CHIT_COORDINATES =
      Array.of(
          Coordinates.onVertices(4, 4, Vertex.Position.TOP),
          Coordinates.onVertices(4, 4, Vertex.Position.BOTTOM),
          Coordinates.onVertices(20, 4, Vertex.Position.TOP),
          Coordinates.onVertices(20, 4, Vertex.Position.BOTTOM));
  private static final Array<Coordinate> P7_P8_SMALL_ISLAND_4_10_CHIT_COORDINATES =
      Array.of(
          Coordinates.onVertices(7, 3, Vertex.Position.TOP_RIGHT),
          Coordinates.onVertices(17, 3, Vertex.Position.TOP_LEFT),
          Coordinates.onVertices(7, 5, Vertex.Position.TOP_LEFT),
          Coordinates.onVertices(17, 5, Vertex.Position.TOP_RIGHT));
  private static final Array<Coordinate> P7_P8_SMALL_ISLAND_5_9_CHIT_COORDINATES =
      Array.of(
          Coordinates.onVertices(7, 3, Vertex.Position.BOTTOM_LEFT),
          Coordinates.onVertices(17, 3, Vertex.Position.BOTTOM_RIGHT),
          Coordinates.onVertices(7, 5, Vertex.Position.BOTTOM_RIGHT),
          Coordinates.onVertices(17, 5, Vertex.Position.BOTTOM_LEFT));
  private static final Array<Coordinate> P7_P8_SMALL_ISLAND_6_8_CHIT_COORDINATES =
      Array.of(
          Coordinates.onVertices(10, 4, Vertex.Position.TOP),
          Coordinates.onVertices(10, 4, Vertex.Position.BOTTOM),
          Coordinates.onVertices(14, 4, Vertex.Position.TOP),
          Coordinates.onVertices(14, 4, Vertex.Position.BOTTOM));
  private static final Map<String, Array<Coordinate>> P7_P8_COORDINATES =
      HashMap.of(
          "main-island-land",
          P7_P8_MAIN_ISLAND_LAND_COORDINATES,
          "main-island-harbor",
          P7_P8_MAIN_ISLAND_HARBOR_COORDINATES,
          "small-island-land",
          P7_P8_SMALL_ISLAND_LAND_COORDINATES,
          "small-island-2-12-chit",
          P7_P8_SMALL_ISLAND_2_12_CHIT_COORDINATES,
          "small-island-4-10-chit",
          P7_P8_SMALL_ISLAND_4_10_CHIT_COORDINATES,
          "small-island-5-9-chit",
          P7_P8_SMALL_ISLAND_5_9_CHIT_COORDINATES,
          "small-island-6-8-chit",
          P7_P8_SMALL_ISLAND_6_8_CHIT_COORDINATES);

  private static final Array<Chit> P7_P8_MAIN_ISLAND_LAND_CHITS =
      P5_P6_MAIN_ISLAND_LAND_CHITS.appendAll(
          Array.of(CHIT_2, CHIT_4, CHIT_5, CHIT_9, CHIT_10, CHIT_12));
  private static final Array<Chit> P7_P8_SMALL_ISLAND_2_12_CHIT_CHITS =
      P5_P6_SMALL_ISLAND_2_12_CHIT_CHITS.appendAll(P5_P6_SMALL_ISLAND_2_12_CHIT_CHITS);
  private static final Array<Chit> P7_P8_SMALL_ISLAND_4_10_CHIT_CHITS =
      P5_P6_SMALL_ISLAND_4_10_CHIT_CHITS;
  private static final Array<Chit> P7_P8_SMALL_ISLAND_5_9_CHIT_CHITS =
      P5_P6_SMALL_ISLAND_5_9_CHIT_CHITS;
  private static final Array<Chit> P7_P8_SMALL_ISLAND_6_8_CHIT_CHITS =
      P5_P6_SMALL_ISLAND_6_8_CHIT_CHITS.appendAll(P5_P6_SMALL_ISLAND_6_8_CHIT_CHITS);
  private static final Map<String, Array<Chit>> P7_P8_CHITS =
      HashMap.of(
          "main-island-land",
          P7_P8_MAIN_ISLAND_LAND_CHITS,
          "small-island-2-12-chit",
          P7_P8_SMALL_ISLAND_2_12_CHIT_CHITS,
          "small-island-4-10-chit",
          P7_P8_SMALL_ISLAND_4_10_CHIT_CHITS,
          "small-island-5-9-chit",
          P7_P8_SMALL_ISLAND_5_9_CHIT_CHITS,
          "small-island-6-8-chit",
          P7_P8_SMALL_ISLAND_6_8_CHIT_CHITS);

  private static final SpecificationImpl.Builder P7_P8_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P7_P8_TILES,
          P7_P8_COORDINATES,
          P7_P8_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("main-island-land"),
              TileMappingUtils.newSelfReferringEntry("main-island-harbor"),
              TileMappingUtils.newSelfReferringEntry("small-island-land"),
              TileMappingUtils.newSelfReferringEntry("small-island-2-12-chit"),
              TileMappingUtils.newSelfReferringEntry("small-island-4-10-chit"),
              TileMappingUtils.newSelfReferringEntry("small-island-5-9-chit"),
              TileMappingUtils.newSelfReferringEntry("small-island-6-8-chit")),
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("main-island-land"),
              TileMappingUtils.newSelfReferringEntry("small-island-2-12-chit"),
              TileMappingUtils.newSelfReferringEntry("small-island-4-10-chit"),
              TileMappingUtils.newSelfReferringEntry("small-island-5-9-chit"),
              TileMappingUtils.newSelfReferringEntry("small-island-6-8-chit")));

  public static final SpecificationImpl P7_P8_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P7_P8_FISHERMEN_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.withFisheries(P7_P8_MAIN_ISLAND_FISHERY_COORDINATES).build();
}
