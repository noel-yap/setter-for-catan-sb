package com.github.noelyap.setterforcatan.util;

import com.github.noelyap.setterforcatan.protogen.ChitOuterClass.Chit;
import com.github.noelyap.setterforcatan.protogen.ConfigurationOuterClass.Configuration;
import com.github.noelyap.setterforcatan.protogen.CoordinateOuterClass.Coordinate;
import com.github.noelyap.setterforcatan.protogen.TileOuterClass.Tile;

public class ConfigurationUtils {
  public static Configuration newConfiguration(
      final Tile tile, final Coordinate coordinate, final Chit chit) {
    return Configuration.newBuilder().setTile(tile).setCoordinate(coordinate).setChit(chit).build();
  }
}
