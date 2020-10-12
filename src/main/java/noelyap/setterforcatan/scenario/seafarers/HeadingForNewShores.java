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

public class HeadingForNewShores {
  private static final Array<Tile> P3_BIG_ISLAND_LAND_TILES =
      Array.fill(3, FIELD)
          .appendAll(Array.fill(3, FOREST))
          .appendAll(Array.fill(4, PASTURE))
          .appendAll(Array.fill(2, HILL))
          .appendAll(Array.fill(2, MOUNTAIN));
  private static final Array<Tile> P3_BIG_ISLAND_HARBOR_TILES =
      Array.fill(3, GENERIC_HARBOR).appendAll(TWO_FOR_ONE_HARBORS);
  private static final Array<Tile> P3_SMALL_ISLAND_LAND_TILES =
      Array.of(FIELD)
          .appendAll(Array.of(PASTURE))
          .appendAll(Array.fill(2, GOLD_FIELD))
          .appendAll(Array.fill(2, HILL))
          .appendAll(Array.fill(2, MOUNTAIN));
  private static final Array<Tile> P3_SMALL_ISLAND_SEA_TILES = Array.fill(5, SEA);
  private static final Map<String, Array<Tile>> P3_TILES =
      HashMap.of(
          "big-island-land",
          P3_BIG_ISLAND_LAND_TILES,
          "big-island-harbor",
          P3_BIG_ISLAND_HARBOR_TILES,
          "small-island-land",
          P3_SMALL_ISLAND_LAND_TILES,
          "small-island-sea",
          P3_SMALL_ISLAND_SEA_TILES);

  private static final Array<Coordinate> P3_BIG_ISLAND_LAND_COORDINATES =
      Array.of(
          Coordinates.of(3, 1),
          Coordinates.of(5, 1),
          Coordinates.of(2, 2),
          Coordinates.of(4, 2),
          Coordinates.of(6, 2),
          Coordinates.of(1, 3),
          Coordinates.of(3, 3),
          Coordinates.of(5, 3),
          Coordinates.of(7, 3),
          Coordinates.of(2, 4),
          Coordinates.of(4, 4),
          Coordinates.of(6, 4),
          Coordinates.of(3, 5),
          Coordinates.of(5, 5));
  private static final Array<Coordinate> P3_BIG_ISLAND_HARBOR_COORDINATES =
      Array.of(
          Coordinates.of(4, 0, BOTTOM_RIGHT),
          Coordinates.of(1, 1, RIGHT),
          Coordinates.of(7, 1, LEFT),
          Coordinates.of(0, 2, BOTTOM_RIGHT),
          Coordinates.of(0, 4, TOP_RIGHT),
          Coordinates.of(8, 4, TOP_LEFT),
          Coordinates.of(1, 5, RIGHT),
          Coordinates.of(7, 5, LEFT));
  private static final Array<Coordinate> P3_BIG_ISLAND_FISHERY_COORDINATES =
      Array.of(
          Coordinates.of(2, 2, LEFT, TOP_LEFT),
          Coordinates.of(8, 2, BOTTOM_LEFT, LEFT),
          Coordinates.of(6, 4, RIGHT, BOTTOM_RIGHT),
          Coordinates.of(4, 6, TOP_LEFT, TOP_RIGHT));
  private static final Array<Coordinate> P3_SMALL_ISLAND_COORDINATES =
      Array.of(
          Coordinates.of(9, 1),
          Coordinates.of(10, 2),
          Coordinates.of(11, 3),
          Coordinates.of(10, 4),
          Coordinates.of(12, 4),
          Coordinates.of(11, 5),
          Coordinates.of(13, 5),
          Coordinates.of(10, 6),
          Coordinates.of(12, 6),
          Coordinates.of(3, 7),
          Coordinates.of(5, 7),
          Coordinates.of(7, 7),
          Coordinates.of(9, 7));
  private static final Map<String, Array<Coordinate>> P3_COORDINATES =
      HashMap.of(
          "big-island-land",
          P3_BIG_ISLAND_LAND_COORDINATES,
          "big-island-harbor",
          P3_BIG_ISLAND_HARBOR_COORDINATES,
          "small-island",
          P3_SMALL_ISLAND_COORDINATES);

  private static final Array<Chit> P3_BIG_ISLAND_LAND_CHITS =
      Array.of(CHIT_2, CHIT_3, CHIT_4, CHIT_9)
          .appendAll(Array.fill(2, CHIT_5))
          .appendAll(Array.fill(2, CHIT_6))
          .appendAll(Array.fill(2, CHIT_8))
          .appendAll(Array.fill(2, CHIT_10))
          .appendAll(Array.fill(2, CHIT_11));
  private static final Array<Chit> P3_SMALL_ISLAND_LAND_CHITS =
      Array.of(CHIT_3, CHIT_5, CHIT_8, CHIT_9, CHIT_10, CHIT_12).appendAll(Array.fill(2, CHIT_4));
  private static final Map<String, Array<Chit>> P3_CHITS =
      HashMap.of(
          "big-island-land",
          P3_BIG_ISLAND_LAND_CHITS,
          "small-island-land",
          P3_SMALL_ISLAND_LAND_CHITS);

  private static final SpecificationImpl.Builder P3_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P3_TILES,
          P3_COORDINATES,
          P3_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("big-island-land"),
              TileMappingUtils.newSelfReferringEntry("big-island-harbor"),
              TileMappingUtils.newEntry("small-island", "small-island-land", "small-island-sea")),
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("big-island-land"),
              TileMappingUtils.newSelfReferringEntry("small-island-land")));

  public static final SpecificationImpl P3_SPECIFICATION_IMPL = P3_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P3_FISHERMEN_SPECIFICATION_IMPL =
      P3_SPECIFICATION_BUILDER.withFisheries(P3_BIG_ISLAND_FISHERY_COORDINATES).build();

  private static final Array<Tile> P4_BIG_ISLAND_PRODUCING_LAND_TILES = Base.P3_P4_PRODUCING_TILES;
  private static final Array<Tile> P4_BIG_ISLAND_UNBEARING_LAND_TILES = Base.P3_P4_UNBEARING_TILES;
  private static final Array<Tile> P4_BIG_ISLAND_HARBOR_TILES = Base.P3_P4_HARBOR_TILES;
  private static final Array<Tile> P4_SMALL_ISLAND_LAND_TILES =
      P3_SMALL_ISLAND_LAND_TILES.append(FOREST);
  private static final Array<Tile> P4_SMALL_ISLAND_SEA_TILES = Array.fill(4, SEA);
  private static final Map<String, Array<Tile>> P4_TILES =
      HashMap.of(
          "big-island-producing-land",
          P4_BIG_ISLAND_PRODUCING_LAND_TILES,
          DESERT_OR_LAKE_NAME,
          P4_BIG_ISLAND_UNBEARING_LAND_TILES,
          "big-island-harbor",
          P4_BIG_ISLAND_HARBOR_TILES,
          "small-island-land",
          P4_SMALL_ISLAND_LAND_TILES,
          "small-island-sea",
          P4_SMALL_ISLAND_SEA_TILES);

  private static final Array<Coordinate> P4_BIG_ISLAND_LAND_COORDINATES =
      Base.P3_P4_LAND_COORDINATES;
  private static final Array<Coordinate> P4_BIG_ISLAND_HARBOR_COORDINATES =
      Base.P3_P4_HARBOR_COORDINATES;
  private static final Array<Coordinate> P4_BIG_ISLAND_FISHERY_COORDINATES =
      Base.P3_P4_FISHERY_COORDINATES;
  private static final Array<Coordinate> P4_SMALL_ISLAND_COORDINATES =
      Array.of(
          Coordinates.of(11, 1),
          Coordinates.of(12, 2),
          Coordinates.of(13, 3),
          Coordinates.of(12, 4),
          Coordinates.of(11, 5),
          Coordinates.of(13, 5),
          Coordinates.of(10, 6),
          Coordinates.of(12, 6),
          Coordinates.of(3, 7),
          Coordinates.of(5, 7),
          Coordinates.of(7, 7),
          Coordinates.of(9, 7),
          Coordinates.of(11, 7));
  private static final Map<String, Array<Coordinate>> P4_COORDINATES =
      HashMap.of(
          "big-island-land",
          P4_BIG_ISLAND_LAND_COORDINATES,
          "big-island-harbor",
          P4_BIG_ISLAND_HARBOR_COORDINATES,
          "small-island",
          P4_SMALL_ISLAND_COORDINATES);

  private static final Array<Chit> P4_BIG_ISLAND_PRODUCING_LAND_CHITS = Base.P3_P4_PRODUCING_CHITS;
  private static final Array<Chit> P4_SMALL_ISLAND_LAND_CHITS =
      Array.of(CHIT_2, CHIT_3, CHIT_4, CHIT_5, CHIT_6, CHIT_8, CHIT_9, CHIT_10, CHIT_11);
  private static final Map<String, Array<Chit>> P4_CHITS =
      HashMap.of(
          "big-island-producing-land",
          P4_BIG_ISLAND_PRODUCING_LAND_CHITS,
          "small-island-land",
          P4_SMALL_ISLAND_LAND_CHITS);

  private static final SpecificationImpl.Builder P4_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P4_TILES,
          P4_COORDINATES,
          P4_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("big-island-harbor"),
              TileMappingUtils.newEntry(
                  "big-island-land", "big-island-producing-land", DESERT_OR_LAKE_NAME),
              TileMappingUtils.newEntry("small-island", "small-island-land", "small-island-sea")),
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("big-island-producing-land"),
              TileMappingUtils.newSelfReferringEntry("small-island-land")));

  public static final SpecificationImpl P4_SPECIFICATION_IMPL = P4_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P4_FISHERMEN_SPECIFICATION_IMPL =
      P4_SPECIFICATION_BUILDER.withFisheries(P4_BIG_ISLAND_FISHERY_COORDINATES).build();

  private static final Array<Tile> P5_P6_BIG_ISLAND_PRODUCING_LAND_TILES =
      Base.P5_P6_PRODUCING_TILES;
  private static final Array<Tile> P5_P6_BIG_ISLAND_UNBEARING_LAND_TILES = Array.fill(2, DESERT);
  private static final Array<Tile> P5_P6_BIG_ISLAND_HARBOR_TILES = Base.P5_P6_HARBOR_TILES;
  private static final Array<Tile> P5_P6_SMALL_ISLAND_LAND_TILES =
      Array.of(FIELD)
          .append(FOREST)
          .append(PASTURE)
          .appendAll(Array.fill(2, HILL))
          .appendAll(Array.fill(2, MOUNTAIN))
          .appendAll(Array.fill(3, GOLD_FIELD));
  private static final Array<Tile> P5_P6_SMALL_ISLAND_SEA_TILES = Array.fill(2, SEA);
  private static final Map<String, Array<Tile>> P5_P6_TILES =
      HashMap.of(
          "big-island-producing-land",
          P5_P6_BIG_ISLAND_PRODUCING_LAND_TILES,
          DESERT_OR_LAKE_NAME,
          P5_P6_BIG_ISLAND_UNBEARING_LAND_TILES,
          "big-island-harbor",
          P5_P6_BIG_ISLAND_HARBOR_TILES,
          "small-island-land",
          P5_P6_SMALL_ISLAND_LAND_TILES,
          "small-island-sea",
          P5_P6_SMALL_ISLAND_SEA_TILES);

  private static final Array<Coordinate> P5_P6_BIG_ISLAND_LAND_COORDINATES =
      Base.P5_P6_LAND_COORDINATES.map(c ->
              Coordinate.newBuilder()
                  .setX(c.getX() + 2)
                  .setY(c.getY())
                  .addAllEdgePositions(c.getEdgePositionsList())
                  .build());
  private static final Array<Coordinate> P5_P6_BIG_ISLAND_HARBOR_COORDINATES =
      Array.of(
          Coordinates.of(6, 0, BOTTOM_RIGHT),
          Coordinates.of(10, 0, BOTTOM_LEFT),
          Coordinates.of(13, 1, BOTTOM_LEFT),
          Coordinates.of(4, 2, RIGHT),
          Coordinates.of(3, 3, BOTTOM_RIGHT),
          Coordinates.of(15, 3, BOTTOM_LEFT),
          Coordinates.of(3, 5, RIGHT),
          Coordinates.of(15, 5, LEFT),
          Coordinates.of(5, 7, TOP_RIGHT),
          Coordinates.of(13, 7, TOP_LEFT),
          Coordinates.of(8, 8, TOP_RIGHT));
  private static final Array<Coordinate> P5_P6_BIG_ISLAND_FISHERY_COORDINATES =
      Array.of(
          Coordinates.of(5, 1, RIGHT, BOTTOM_RIGHT),
          Coordinates.of(14, 2, BOTTOM_LEFT, LEFT),
          Coordinates.of(5, 3, LEFT, TOP_LEFT),
          Coordinates.of(4, 4, BOTTOM_LEFT, LEFT),
          Coordinates.of(14, 4, RIGHT, BOTTOM_RIGHT),
          Coordinates.of(14, 6, LEFT, TOP_LEFT),
          Coordinates.of(4, 6, TOP_RIGHT, RIGHT),
          Coordinates.of(11, 7, RIGHT, BOTTOM_RIGHT));
  private static final Array<Coordinate> P5_P6_SMALL_ISLAND_COORDINATES =
      Array.of(
          Coordinates.of(3, 1),
          Coordinates.of(15, 1),
          Coordinates.of(2, 2),
          Coordinates.of(16, 2),
          Coordinates.of(1, 3),
          Coordinates.of(17, 3),
          Coordinates.of(1, 5),
          Coordinates.of(17, 5),
          Coordinates.of(2, 6),
          Coordinates.of(16, 6),
          Coordinates.of(3, 7),
          Coordinates.of(15, 7));
  private static final Map<String, Array<Coordinate>> P5_P6_COORDINATES =
      HashMap.of(
          "big-island-land",
          P5_P6_BIG_ISLAND_LAND_COORDINATES,
          "big-island-harbor",
          P5_P6_BIG_ISLAND_HARBOR_COORDINATES,
          "small-island",
          P5_P6_SMALL_ISLAND_COORDINATES);

  private static final Array<Chit> P5_P6_BIG_ISLAND_PRODUCING_LAND_CHITS =
      Base.P5_P6_PRODUCING_CHITS;
  private static final Array<Chit> P5_P6_SMALL_ISLAND_LAND_CHITS =
      P4_SMALL_ISLAND_LAND_CHITS.append(CHIT_12);
  private static final Map<String, Array<Chit>> P5_P6_CHITS =
      HashMap.of(
          "big-island-producing-land",
          P5_P6_BIG_ISLAND_PRODUCING_LAND_CHITS,
          "small-island-land",
          P5_P6_SMALL_ISLAND_LAND_CHITS);

  private static final SpecificationImpl.Builder P5_P6_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P5_P6_TILES,
          P5_P6_COORDINATES,
          P5_P6_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("big-island-harbor"),
              TileMappingUtils.newEntry(
                  "big-island-land", "big-island-producing-land", DESERT_OR_LAKE_NAME),
              TileMappingUtils.newEntry("small-island", "small-island-land", "small-island-sea")),
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("big-island-producing-land"),
              TileMappingUtils.newSelfReferringEntry("small-island-land")));

  public static final SpecificationImpl P5_P6_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P5_P6_FISHERMEN_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.withFisheries(P5_P6_BIG_ISLAND_FISHERY_COORDINATES).build();

  private static final Array<Tile> P7_P8_BIG_ISLAND_PRODUCING_LAND_TILES =
      Base.P7_P8_PRODUCING_TILES;
  private static final Array<Tile> P7_P8_BIG_ISLAND_UNBEARING_LAND_TILES =
      Base.P7_P8_UNBEARING_LAND_TILES;
  private static final Array<Tile> P7_P8_BIG_ISLAND_HARBOR_TILES = Base.P7_P8_HARBOR_TILES;
  private static final Array<Tile> P7_P8_SMALL_ISLAND_LAND_TILES =
      P5_P6_SMALL_ISLAND_LAND_TILES.appendAll(P5_P6_SMALL_ISLAND_LAND_TILES);
  private static final Array<Tile> P7_P8_SMALL_ISLAND_SEA_TILES = Array.fill(10, SEA);
  private static final Map<String, Array<Tile>> P7_P8_TILES =
      HashMap.of(
          "big-island-producing-land",
          P7_P8_BIG_ISLAND_PRODUCING_LAND_TILES,
          DESERT_OR_LAKE_NAME,
          P7_P8_BIG_ISLAND_UNBEARING_LAND_TILES,
          "big-island-harbor",
          P7_P8_BIG_ISLAND_HARBOR_TILES,
          "small-island-land",
          P7_P8_SMALL_ISLAND_LAND_TILES,
          "small-island-sea",
          P7_P8_SMALL_ISLAND_SEA_TILES);

  private static final Array<Coordinate> P7_P8_BIG_ISLAND_LAND_COORDINATES =
      Base.P7_P8_LAND_COORDINATES.map(c ->
              Coordinate.newBuilder()
                  .setX(c.getX() + 3)
                  .setY(c.getY() + 1)
                  .addAllEdgePositions(c.getEdgePositionsList())
                  .build());
  private static final Array<Coordinate> P7_P8_BIG_ISLAND_HARBOR_COORDINATES =
      Base.P7_P8_HARBOR_COORDINATES.map(c ->
              Coordinate.newBuilder()
                  .setX(c.getX() + 3)
                  .setY(c.getY() + 1)
                  .addAllEdgePositions(c.getEdgePositionsList())
                  .build());
  private static final Array<Coordinate> P7_P8_BIG_ISLAND_FISHERY_COORDINATES =
      Base.P7_P8_FISHERY_COORDINATES.map(c ->
              Coordinate.newBuilder()
                  .setX(c.getX() + 3)
                  .setY(c.getY() + 1)
                  .addAllEdgePositions(c.getEdgePositionsList())
                  .build());
  private static final Array<Coordinate> P7_P8_SMALL_ISLAND_COORDINATES =
      Array.of(
          Coordinates.of(6, 0),
          Coordinates.of(8, 0),
          Coordinates.of(10, 0),
          Coordinates.of(12, 0),
          Coordinates.of(14, 0),
          Coordinates.of(16, 0),
          Coordinates.of(5, 1),
          Coordinates.of(17, 1),
          Coordinates.of(4, 2),
          Coordinates.of(18, 2),
          Coordinates.of(3, 3),
          Coordinates.of(19, 3),
          Coordinates.of(2, 4),
          Coordinates.of(20, 4),
          Coordinates.of(1, 5),
          Coordinates.of(21, 5),
          Coordinates.of(2, 6),
          Coordinates.of(20, 6),
          Coordinates.of(3, 7),
          Coordinates.of(19, 7),
          Coordinates.of(4, 8),
          Coordinates.of(18, 8),
          Coordinates.of(5, 9),
          Coordinates.of(17, 9),
          Coordinates.of(6, 10),
          Coordinates.of(8, 10),
          Coordinates.of(10, 10),
          Coordinates.of(12, 10),
          Coordinates.of(14, 10),
          Coordinates.of(16, 10));
  private static final Map<String, Array<Coordinate>> P7_P8_COORDINATES =
      HashMap.of(
          "big-island-land",
          P7_P8_BIG_ISLAND_LAND_COORDINATES,
          "big-island-harbor",
          P7_P8_BIG_ISLAND_HARBOR_COORDINATES,
          "small-island",
          P7_P8_SMALL_ISLAND_COORDINATES);

  private static final Array<Chit> P7_P8_BIG_ISLAND_PRODUCING_LAND_CHITS =
      Base.P7_P8_PRODUCING_CHITS;
  private static final Array<Chit> P7_P8_SMALL_ISLAND_LAND_CHITS =
      P5_P6_SMALL_ISLAND_LAND_CHITS.appendAll(P5_P6_SMALL_ISLAND_LAND_CHITS);
  private static final Map<String, Array<Chit>> P7_P8_CHITS =
      HashMap.of(
          "big-island-producing-land",
          P7_P8_BIG_ISLAND_PRODUCING_LAND_CHITS,
          "small-island-land",
          P7_P8_SMALL_ISLAND_LAND_CHITS);

  private static final SpecificationImpl.Builder P7_P8_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P7_P8_TILES,
          P7_P8_COORDINATES,
          P7_P8_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("big-island-harbor"),
              TileMappingUtils.newEntry(
                  "big-island-land", "big-island-producing-land", DESERT_OR_LAKE_NAME),
              TileMappingUtils.newEntry("small-island", "small-island-land", "small-island-sea")),
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("big-island-producing-land"),
              TileMappingUtils.newSelfReferringEntry("small-island-land")));

  public static final SpecificationImpl P7_P8_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P7_P8_FISHERMEN_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.withFisheries(P7_P8_BIG_ISLAND_FISHERY_COORDINATES).build();
}
