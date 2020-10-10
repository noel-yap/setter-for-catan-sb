package noelyap.setterforcatan.util;

import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.BRICK_HARBOR;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.GRAIN_HARBOR;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.LUMBER_HARBOR;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.ORE_HARBOR;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.WOOL_HARBOR;

import io.vavr.collection.Array;
import noelyap.setterforcatan.protogen.TileOuterClass.Tile;

public class TileUtils {
  public static final String DESERT_OR_LAKE_NAME = "«DESERT»|«LAKE»";
  public static final String FISHERY_NAME = "«FISHERY»";
  public static final String LAKE_NAME = "«LAKE»";

  public static final Array<Tile> TWO_FOR_ONE_HARBORS =
      Array.of(
          TileUtils.newTile(GRAIN_HARBOR),
          TileUtils.newTile(LUMBER_HARBOR),
          TileUtils.newTile(WOOL_HARBOR),
          TileUtils.newTile(BRICK_HARBOR),
          TileUtils.newTile(ORE_HARBOR));

  public static Tile newTile(final Tile.Type type) {
    return Tile.newBuilder().setType(type).build();
  }

  public static Array<Tile> newTiles(final int n, final Tile.Type type) {
    return Array.fill(n, newTile(type));
  }
}
