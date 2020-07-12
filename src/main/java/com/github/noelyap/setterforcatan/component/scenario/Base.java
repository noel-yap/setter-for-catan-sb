package com.github.noelyap.setterforcatan.component.scenario;

import com.github.noelyap.setterforcatan.component.Specification;
import com.github.noelyap.setterforcatan.protogen.ChitOuterClass.Chit;
import com.github.noelyap.setterforcatan.protogen.CoordinateOuterClass.Coordinate;
import com.github.noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge;
import com.github.noelyap.setterforcatan.protogen.TileOuterClass.Tile;
import com.github.noelyap.setterforcatan.util.ChitUtils;
import com.github.noelyap.setterforcatan.util.CoordinateUtils;
import com.github.noelyap.setterforcatan.util.TileMappingUtils;
import com.github.noelyap.setterforcatan.util.TileUtils;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.Array;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;

public class Base {
  public static final Tuple2<Array<Tile>, Boolean> P3_P4_PRODUCING_TILES =
      Tuple.of(
          TileUtils.newTiles(4, Tile.Type.FIELD)
              .appendAll(TileUtils.newTiles(4, Tile.Type.FOREST))
              .appendAll(TileUtils.newTiles(4, Tile.Type.PASTURE))
              .appendAll(TileUtils.newTiles(3, Tile.Type.HILL))
              .appendAll(TileUtils.newTiles(3, Tile.Type.MOUNTAIN)),
          false);
  public static final Tuple2<Array<Tile>, Boolean> P3_P4_DESERT_TILES =
      Tuple.of(TileUtils.newTiles(1, Tile.Type.DESERT), true);
  public static final Tuple2<Array<Tile>, Boolean> P3_P4_HARBOR_TILES =
      Tuple.of(
          TileUtils.newTiles(4, Tile.Type.GENERIC_HARBOR)
              .append(TileUtils.newTile(Tile.Type.GRAIN_HARBOR))
              .append(TileUtils.newTile(Tile.Type.LUMBER_HARBOR))
              .append(TileUtils.newTile(Tile.Type.WOOL_HARBOR))
              .append(TileUtils.newTile(Tile.Type.BRICK_HARBOR))
              .append(TileUtils.newTile(Tile.Type.ORE_HARBOR)),
          true);
  public static final Map<String, Tuple2<Array<Tile>, Boolean>> P3_P4_TILES =
      HashMap.of(
          "producing-terrain",
          P3_P4_PRODUCING_TILES,
          TileUtils.DESERT_OR_LAKE_NAME,
          P3_P4_DESERT_TILES,
          "harbor",
          P3_P4_HARBOR_TILES);

  public static final Array<Coordinate> P3_P4_TERRAIN_COORDINATES =
      CoordinateUtils.newCoordinates(
          CoordinateUtils.newCoordinate(3, 1),
          CoordinateUtils.newCoordinate(5, 1),
          CoordinateUtils.newCoordinate(7, 1),
          CoordinateUtils.newCoordinate(2, 2),
          CoordinateUtils.newCoordinate(4, 2),
          CoordinateUtils.newCoordinate(6, 2),
          CoordinateUtils.newCoordinate(8, 2),
          CoordinateUtils.newCoordinate(1, 3),
          CoordinateUtils.newCoordinate(3, 3),
          CoordinateUtils.newCoordinate(5, 3),
          CoordinateUtils.newCoordinate(7, 3),
          CoordinateUtils.newCoordinate(9, 3),
          CoordinateUtils.newCoordinate(2, 4),
          CoordinateUtils.newCoordinate(4, 4),
          CoordinateUtils.newCoordinate(6, 4),
          CoordinateUtils.newCoordinate(8, 4),
          CoordinateUtils.newCoordinate(3, 5),
          CoordinateUtils.newCoordinate(5, 5),
          CoordinateUtils.newCoordinate(7, 5));
  public static final Array<Coordinate> P3_P4_HARBOR_COORDINATES =
      CoordinateUtils.newCoordinates(
          CoordinateUtils.newCoordinate(2, 0, Edge.Position.BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(6, 0, Edge.Position.BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(9, 1, Edge.Position.BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(0, 2, Edge.Position.RIGHT),
          CoordinateUtils.newCoordinate(11, 3, Edge.Position.LEFT),
          CoordinateUtils.newCoordinate(0, 4, Edge.Position.RIGHT),
          CoordinateUtils.newCoordinate(9, 5, Edge.Position.TOP_LEFT),
          CoordinateUtils.newCoordinate(6, 6, Edge.Position.TOP_LEFT),
          CoordinateUtils.newCoordinate(2, 6, Edge.Position.TOP_RIGHT));
  public static final Array<Coordinate> P3_P4_FISHERY_COORDINATES =
      CoordinateUtils.newCoordinates(
          CoordinateUtils.newCoordinate(
              4, 0, Edge.Position.BOTTOM_RIGHT, Edge.Position.BOTTOM_LEFT),
          CoordinateUtils.newCoordinate(1, 1, Edge.Position.RIGHT, Edge.Position.BOTTOM_RIGHT),
          CoordinateUtils.newCoordinate(10, 2, Edge.Position.BOTTOM_LEFT, Edge.Position.LEFT),
          CoordinateUtils.newCoordinate(10, 4, Edge.Position.LEFT, Edge.Position.TOP_LEFT),
          CoordinateUtils.newCoordinate(1, 5, Edge.Position.TOP_RIGHT, Edge.Position.RIGHT),
          CoordinateUtils.newCoordinate(4, 6, Edge.Position.TOP_LEFT, Edge.Position.TOP_RIGHT));
  public static final Map<String, Array<Coordinate>> P3_P4_COORDINATES =
      HashMap.of("terrain", P3_P4_TERRAIN_COORDINATES, "harbor", P3_P4_HARBOR_COORDINATES);

  public static final Array<Chit> P3_P4_PRODUCING_TERRAIN_CHITS =
      Array.of(ChitUtils.CHITS_2, ChitUtils.CHITS_12)
          .appendAll(Array.fill(2, ChitUtils.CHITS_3))
          .appendAll(Array.fill(2, ChitUtils.CHITS_4))
          .appendAll(Array.fill(2, ChitUtils.CHITS_5))
          .appendAll(Array.fill(2, ChitUtils.CHITS_6))
          .appendAll(Array.fill(2, ChitUtils.CHITS_8))
          .appendAll(Array.fill(2, ChitUtils.CHITS_9))
          .appendAll(Array.fill(2, ChitUtils.CHITS_10))
          .appendAll(Array.fill(2, ChitUtils.CHITS_11));
  public static final Map<String, Array<Chit>> P3_P4_CHITS =
      HashMap.of("producing-terrain", P3_P4_PRODUCING_TERRAIN_CHITS);

  public static final Specification P3_P4_SPECIFICATION =
      new Specification(
          P3_P4_TILES,
          P3_P4_COORDINATES,
          P3_P4_CHITS,
          HashMap.ofEntries(
              TileMappingUtils.newSelfReferringEntry("harbor"),
              TileMappingUtils.newEntry(
                  "terrain", "producing-terrain", TileUtils.DESERT_OR_LAKE_NAME)),
          HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("producing-terrain")));
  public static final Specification P3_P4_FISHERMEN_SPECIFICATION =
      P3_P4_SPECIFICATION.withFisheries(P3_P4_FISHERY_COORDINATES);
}
