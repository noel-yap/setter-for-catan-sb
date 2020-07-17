package noelyap.setterforcatan.util;

import noelyap.setterforcatan.protogen.ChitOuterClass.Chit;
import noelyap.setterforcatan.protogen.ConfigurationOuterClass.Configuration;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Coordinate;
import noelyap.setterforcatan.protogen.TileOuterClass.Tile;

public class ConfigurationUtils {
  public static Configuration newConfiguration(
      final Tile tile, final Coordinate coordinate, final Chit chit) {
    return Configuration.newBuilder().setTile(tile).setCoordinate(coordinate).setChit(chit).build();
  }
}
