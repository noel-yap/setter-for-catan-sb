package noelyap.setterforcatan.util;

import io.vavr.collection.Array;
import noelyap.setterforcatan.protogen.TileOuterClass.Tile;

public class TileUtils {
  public static final String DESERT_OR_LAKE_NAME = "«DESERT»|«LAKE»";
  public static final String FISHERY_NAME = "«FISHERY»";
  public static final String LAKE_NAME = "«LAKE»";

  public static Tile newTile(final Tile.Type type) {
    return Tile.newBuilder().setType(type).build();
  }

  public static Array<Tile> newTiles(final int n, final Tile.Type type) {
    return Array.fill(n, newTile(type));
  }
}
