package noelyap.setterforcatan.matcher;

import noelyap.setterforcatan.protogen.ConfigurationOuterClass.Configuration;
import noelyap.setterforcatan.protogen.TileOuterClass.Tile;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class IsTileType extends TypeSafeMatcher<Configuration> {
  private final Tile.Type tileType;

  public static IsTileType isTileType(final Tile.Type tileType) {
    return new IsTileType(tileType);
  }

  public IsTileType(final Tile.Type tileType) {
    this.tileType = tileType;
  }

  @Override
  public boolean matchesSafely(final Configuration configuration) {
    return configuration.getTile().getType() == tileType;
  }

  @Override
  public void describeTo(final Description description) {
    description.appendText("tile type of " + tileType);
  }
}
