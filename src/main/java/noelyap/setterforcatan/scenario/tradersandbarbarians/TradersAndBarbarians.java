package noelyap.setterforcatan.scenario.tradersandbarbarians;

import static noelyap.setterforcatan.component.Chits.CHIT_10;
import static noelyap.setterforcatan.component.Chits.CHIT_11;
import static noelyap.setterforcatan.component.Chits.CHIT_3;
import static noelyap.setterforcatan.component.Chits.CHIT_4;
import static noelyap.setterforcatan.component.Chits.CHIT_5;
import static noelyap.setterforcatan.component.Chits.CHIT_6;
import static noelyap.setterforcatan.component.Chits.CHIT_8;
import static noelyap.setterforcatan.component.Chits.CHIT_9;
import static noelyap.setterforcatan.component.Tiles.CASTLE;
import static noelyap.setterforcatan.component.Tiles.DESERT;
import static noelyap.setterforcatan.component.Tiles.DESERT_OR_LAKE_NAME;
import static noelyap.setterforcatan.component.Tiles.FIELD;
import static noelyap.setterforcatan.component.Tiles.FOREST;
import static noelyap.setterforcatan.component.Tiles.GENERIC_HARBOR;
import static noelyap.setterforcatan.component.Tiles.GLASSWORKS;
import static noelyap.setterforcatan.component.Tiles.HILL;
import static noelyap.setterforcatan.component.Tiles.MOUNTAIN;
import static noelyap.setterforcatan.component.Tiles.PASTURE;
import static noelyap.setterforcatan.component.Tiles.QUARRY;
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

public class TradersAndBarbarians {
  private static final Array<Tile> P3_P4_NON_TRADE_TILES =
      Array.fill(3, FIELD)
          .appendAll(Array.fill(3, HILL))
          .appendAll(Array.fill(3, MOUNTAIN))
          .appendAll(Array.fill(3, PASTURE))
          .appendAll(Array.fill(4, FOREST));
  private static final Array<Tile> P3_P4_TRADE_TILES = Array.of(CASTLE, GLASSWORKS, QUARRY);
  private static final Array<Tile> P3_P4_HARBOR_TILES = Base.P3_P4_HARBOR_TILES;
  private static final Map<String, Array<Tile>> P3_P4_TILES =
      HashMap.of(
          "non-trade",
          P3_P4_NON_TRADE_TILES,
          "trade",
          P3_P4_TRADE_TILES,
          "harbor",
          P3_P4_HARBOR_TILES);

  private static final Array<Coordinate> P3_P4_NON_TRADE_COORDINATES =
      Array.of(
          Coordinates.of(3, 1),
          Coordinates.of(5, 1),
          Coordinates.of(2, 2),
          Coordinates.of(4, 2),
          Coordinates.of(6, 2),
          Coordinates.of(8, 2),
          Coordinates.of(3, 3),
          Coordinates.of(5, 3),
          Coordinates.of(7, 3),
          Coordinates.of(9, 3),
          Coordinates.of(2, 4),
          Coordinates.of(4, 4),
          Coordinates.of(6, 4),
          Coordinates.of(8, 4),
          Coordinates.of(3, 5),
          Coordinates.of(5, 5));
  private static final Array<Coordinate> P3_P4_TRADE_COORDINATES =
      Array.of(Coordinates.of(7, 1), Coordinates.of(1, 3), Coordinates.of(7, 5));
  private static final Array<Coordinate> P3_P4_HARBOR_COORDINATES = Base.P3_P4_HARBOR_COORDINATES;
  private static final Array<Coordinate> P3_P4_FISHERY_COORDINATES = Base.P3_P4_FISHERY_COORDINATES;
  private static final Map<String, Array<Coordinate>> P3_P4_COORDINATES =
      HashMap.of(
          "non-trade",
          P3_P4_NON_TRADE_COORDINATES,
          "trade",
          P3_P4_TRADE_COORDINATES,
          "harbor",
          P3_P4_HARBOR_COORDINATES);

  private static final Array<Chit> P3_P4_NON_TRADE_CHITS =
      Array.fill(2, CHIT_3)
          .appendAll(Array.fill(2, CHIT_4))
          .appendAll(Array.fill(2, CHIT_5))
          .appendAll(Array.fill(2, CHIT_6))
          .appendAll(Array.fill(2, CHIT_8))
          .appendAll(Array.fill(2, CHIT_9))
          .appendAll(Array.fill(2, CHIT_10))
          .appendAll(Array.fill(2, CHIT_11));
  private static final Map<String, Array<Chit>> P3_P4_CHITS =
      HashMap.of("non-trade", P3_P4_NON_TRADE_CHITS);

  private static final SpecificationImpl.Builder P3_P4_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P3_P4_TILES,
          P3_P4_COORDINATES,
          P3_P4_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("non-trade"),
              TileMappingUtils.newSelfReferringEntry("trade"),
              TileMappingUtils.newSelfReferringEntry("harbor")),
          HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("non-trade")));

  public static final SpecificationImpl P3_P4_SPECIFICATION_IMPL =
      P3_P4_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P3_P4_FISHERMEN_SPECIFICATION_IMPL =
      P3_P4_SPECIFICATION_BUILDER.withFisheries(P3_P4_FISHERY_COORDINATES).build();

  private static final Array<Tile> P5_P6_NON_TRADE_PRODUCING_TILES = Base.P5_P6_PRODUCING_TILES;
  private static final Array<Tile> P5_P6_NON_TRADE_NON_PRODUCING_TILES = Array.fill(2, DESERT);
  private static final Array<Tile> P5_P6_TRADE_CASTLE_TILES = Array.of(CASTLE);
  private static final Array<Tile> P5_P6_TRADE_GLASSWORKS_TILES = Array.fill(3, GLASSWORKS);
  private static final Array<Tile> P5_P6_TRADE_QUARRY_TILES = Array.fill(3, QUARRY);
  private static final Array<Tile> P5_P6_HARBOR_TILES = Base.P5_P6_HARBOR_TILES;
  private static final Map<String, Array<Tile>> P5_P6_TILES =
      HashMap.of(
          "non-trade-producing",
          P5_P6_NON_TRADE_PRODUCING_TILES,
          DESERT_OR_LAKE_NAME,
          P5_P6_NON_TRADE_NON_PRODUCING_TILES,
          "trade-castle",
          P5_P6_TRADE_CASTLE_TILES,
          "trade-glassworks",
          P5_P6_TRADE_GLASSWORKS_TILES,
          "trade-quarry",
          P5_P6_TRADE_QUARRY_TILES,
          "harbor",
          P5_P6_HARBOR_TILES);

  private static final Array<Coordinate> P5_P6_NON_TRADE_COORDINATES =
      Array.of(
          Coordinates.of(7, 1),
          Coordinates.of(9, 1),
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
          Coordinates.of(4, 4),
          Coordinates.of(6, 4),
          Coordinates.of(10, 4),
          Coordinates.of(12, 4),
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
          Coordinates.of(7, 7),
          Coordinates.of(9, 7));
  private static final Array<Coordinate> P5_P6_TRADE_CASTLE_COORDINATES =
      Array.of(Coordinates.of(8, 4));
  private static final Array<Coordinate> P5_P6_TRADE_GLASSWORKS_COORDINATES =
      Array.of(Coordinates.of(5, 1), Coordinates.of(14, 4), Coordinates.of(5, 7));
  private static final Array<Coordinate> P5_P6_TRADE_QUARRY_COORDINATES =
      Array.of(Coordinates.of(11, 1), Coordinates.of(2, 4), Coordinates.of(11, 7));
  private static final Array<Coordinate> P5_P6_HARBOR_COORDINATES =
      Array.of(
          Coordinates.onEdges(4, 0, BOTTOM_RIGHT),
          Coordinates.onEdges(8, 0, BOTTOM_LEFT),
          Coordinates.onEdges(13, 1, BOTTOM_LEFT),
          Coordinates.onEdges(1, 3, RIGHT),
          Coordinates.onEdges(16, 4, LEFT),
          Coordinates.onEdges(1, 5, TOP_RIGHT),
          Coordinates.onEdges(2, 6, RIGHT),
          Coordinates.onEdges(14, 6, TOP_LEFT),
          Coordinates.onEdges(13, 7, LEFT),
          Coordinates.onEdges(4, 8, TOP_RIGHT),
          Coordinates.onEdges(10, 8, TOP_LEFT));
  private static final Array<Coordinate> P5_P6_FISHERY_COORDINATES =
      Array.of(
          Coordinates.onEdges(6, 0, BOTTOM_RIGHT, BOTTOM_LEFT),
          Coordinates.onEdges(11, 1, TOP_LEFT, TOP_RIGHT),
          Coordinates.onEdges(4, 2, LEFT, TOP_LEFT),
          Coordinates.onEdges(15, 3, BOTTOM_LEFT, LEFT),
          Coordinates.onEdges(2, 4, LEFT, TOP_LEFT),
          Coordinates.onEdges(12, 6, RIGHT, BOTTOM_RIGHT),
          Coordinates.onEdges(3, 7, TOP_RIGHT, RIGHT),
          Coordinates.onEdges(8, 8, TOP_LEFT, TOP_RIGHT));
  private static final Map<String, Array<Coordinate>> P5_P6_COORDINATES =
      HashMap.of(
          "non-trade",
          P5_P6_NON_TRADE_COORDINATES,
          "trade-castle",
          P5_P6_TRADE_CASTLE_COORDINATES,
          "trade-glassworks",
          P5_P6_TRADE_GLASSWORKS_COORDINATES,
          "trade-quarry",
          P5_P6_TRADE_QUARRY_COORDINATES,
          "harbor",
          P5_P6_HARBOR_COORDINATES);

  private static final Array<Chit> P5_P6_NON_TRADE_PRODUCING_CHITS = Base.P5_P6_PRODUCING_CHITS;
  private static final Map<String, Array<Chit>> P5_P6_CHITS =
      HashMap.of("non-trade-producing", P5_P6_NON_TRADE_PRODUCING_CHITS);

  private static final SpecificationImpl.Builder P5_P6_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P5_P6_TILES,
          P5_P6_COORDINATES,
          P5_P6_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("trade-castle"),
              TileMappingUtils.newSelfReferringEntry("trade-glassworks"),
              TileMappingUtils.newSelfReferringEntry("trade-quarry"),
              TileMappingUtils.newSelfReferringEntry("harbor"),
              TileMappingUtils.newEntry("non-trade", "non-trade-producing", DESERT_OR_LAKE_NAME)),
          HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("non-trade-producing")));

  public static final SpecificationImpl P5_P6_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P5_P6_FISHERMEN_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.withFisheries(P5_P6_FISHERY_COORDINATES).build();

  private static final Array<Tile> P7_P8_NON_TRADE_TILES =
      Base.P7_P8_PRODUCING_TILES.appendAll(Base.P3_P4_PRODUCING_TILES);
  private static final Array<Tile> P7_P8_TRADE_CASTLE_TILES = P5_P6_TRADE_CASTLE_TILES;
  private static final Array<Tile> P7_P8_TRADE_GLASSWORKS_TILES = P5_P6_TRADE_GLASSWORKS_TILES;
  private static final Array<Tile> P7_P8_TRADE_QUARRY_TILES = P5_P6_TRADE_QUARRY_TILES;
  private static final Array<Tile> P7_P8_HARBOR_TILES =
      Base.P3_P4_HARBOR_TILES.appendAll(Array.of(GENERIC_HARBOR)).appendAll(TWO_FOR_ONE_HARBORS);
  private static final Map<String, Array<Tile>> P7_P8_TILES =
      HashMap.of(
          "non-trade",
          P7_P8_NON_TRADE_TILES,
          "trade-castle",
          P7_P8_TRADE_CASTLE_TILES,
          "trade-glassworks",
          P7_P8_TRADE_GLASSWORKS_TILES,
          "trade-quarry",
          P7_P8_TRADE_QUARRY_TILES,
          "harbor",
          P7_P8_HARBOR_TILES);

  private static final Array<Coordinate> P7_P8_NON_TRADE_COORDINATES =
      Array.of(
          Coordinates.of(9, 1),
          Coordinates.of(11, 1),
          Coordinates.of(13, 1),
          Coordinates.of(6, 2),
          Coordinates.of(8, 2),
          Coordinates.of(10, 2),
          Coordinates.of(12, 2),
          Coordinates.of(14, 2),
          Coordinates.of(16, 2),
          Coordinates.of(5, 3),
          Coordinates.of(7, 3),
          Coordinates.of(9, 3),
          Coordinates.of(11, 3),
          Coordinates.of(13, 3),
          Coordinates.of(15, 3),
          Coordinates.of(17, 3),
          Coordinates.of(4, 4),
          Coordinates.of(6, 4),
          Coordinates.of(8, 4),
          Coordinates.of(10, 4),
          Coordinates.of(12, 4),
          Coordinates.of(14, 4),
          Coordinates.of(16, 4),
          Coordinates.of(18, 4),
          Coordinates.of(5, 5),
          Coordinates.of(7, 5),
          Coordinates.of(9, 5),
          Coordinates.of(13, 5),
          Coordinates.of(15, 5),
          Coordinates.of(17, 5),
          Coordinates.of(4, 6),
          Coordinates.of(6, 6),
          Coordinates.of(8, 6),
          Coordinates.of(10, 6),
          Coordinates.of(12, 6),
          Coordinates.of(14, 6),
          Coordinates.of(16, 6),
          Coordinates.of(18, 6),
          Coordinates.of(5, 7),
          Coordinates.of(7, 7),
          Coordinates.of(9, 7),
          Coordinates.of(11, 7),
          Coordinates.of(13, 7),
          Coordinates.of(15, 7),
          Coordinates.of(17, 7),
          Coordinates.of(6, 8),
          Coordinates.of(8, 8),
          Coordinates.of(10, 8),
          Coordinates.of(12, 8),
          Coordinates.of(14, 8),
          Coordinates.of(16, 8),
          Coordinates.of(9, 9),
          Coordinates.of(11, 9),
          Coordinates.of(13, 9));
  private static final Array<Coordinate> P7_P8_TRADE_CASTLE_COORDINATES =
      Array.of(Coordinates.of(11, 5));
  private static final Array<Coordinate> P7_P8_TRADE_GLASSWORKS_COORDINATES =
      Array.of(Coordinates.of(7, 1), Coordinates.of(19, 5), Coordinates.of(7, 9));
  private static final Array<Coordinate> P7_P8_TRADE_QUARRY_COORDINATES =
      Array.of(Coordinates.of(15, 1), Coordinates.of(3, 5), Coordinates.of(15, 9));
  private static final Array<Coordinate> P7_P8_HARBOR_COORDINATES =
      Array.of(
          Coordinates.onEdges(6, 0, BOTTOM_RIGHT),
          Coordinates.onEdges(10, 0, BOTTOM_LEFT),
          Coordinates.onEdges(14, 0, BOTTOM_RIGHT),
          Coordinates.onEdges(17, 1, BOTTOM_LEFT),
          Coordinates.onEdges(4, 2, RIGHT),
          Coordinates.onEdges(19, 3, BOTTOM_LEFT),
          Coordinates.onEdges(2, 4, RIGHT),
          Coordinates.onEdges(21, 5, LEFT),
          Coordinates.onEdges(2, 6, RIGHT),
          Coordinates.onEdges(19, 7, TOP_LEFT),
          Coordinates.onEdges(4, 8, RIGHT),
          Coordinates.onEdges(17, 9, LEFT),
          Coordinates.onEdges(6, 10, TOP_RIGHT),
          Coordinates.onEdges(10, 10, TOP_LEFT),
          Coordinates.onEdges(14, 10, TOP_LEFT));
  private static final Array<Coordinate> P7_P8_FISHERY_COORDINATES =
      Array.of(
          Coordinates.onEdges(5, 1, RIGHT, BOTTOM_RIGHT),
          Coordinates.onEdges(11, 1, TOP_LEFT, TOP_RIGHT),
          Coordinates.onEdges(18, 2, BOTTOM_LEFT, LEFT),
          Coordinates.onEdges(3, 5, LEFT, TOP_LEFT),
          Coordinates.onEdges(20, 6, LEFT, TOP_LEFT),
          Coordinates.onEdges(16, 8, RIGHT, BOTTOM_RIGHT),
          Coordinates.onEdges(5, 9, TOP_RIGHT, RIGHT),
          Coordinates.onEdges(11, 9, BOTTOM_RIGHT, BOTTOM_LEFT));
  private static final Map<String, Array<Coordinate>> P7_P8_COORDINATES =
      HashMap.of(
          "non-trade",
          P7_P8_NON_TRADE_COORDINATES,
          "trade-castle",
          P7_P8_TRADE_CASTLE_COORDINATES,
          "trade-glassworks",
          P7_P8_TRADE_GLASSWORKS_COORDINATES,
          "trade-quarry",
          P7_P8_TRADE_QUARRY_COORDINATES,
          "harbor",
          P7_P8_HARBOR_COORDINATES);

  private static final Array<Chit> P7_P8_NON_TRADE_CHITS =
      Base.P7_P8_PRODUCING_CHITS.appendAll(Base.P3_P4_PRODUCING_CHITS);
  private static final Map<String, Array<Chit>> P7_P8_CHITS =
      HashMap.of("non-trade", P7_P8_NON_TRADE_CHITS);

  private static final SpecificationImpl.Builder P7_P8_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
          P7_P8_TILES,
          P7_P8_COORDINATES,
          P7_P8_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("non-trade"),
              TileMappingUtils.newSelfReferringEntry("trade-castle"),
              TileMappingUtils.newSelfReferringEntry("trade-glassworks"),
              TileMappingUtils.newSelfReferringEntry("trade-quarry"),
              TileMappingUtils.newSelfReferringEntry("harbor")),
          HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("non-trade")));

  public static final SpecificationImpl P7_P8_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P7_P8_FISHERMEN_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.withFisheries(P7_P8_FISHERY_COORDINATES).build();
}
