package noelyap.setterforcatan.matcher;

import noelyap.setterforcatan.protogen.ConfigurationOuterClass.Configuration;
import noelyap.setterforcatan.protogen.TileOuterClass.Tile;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.Is;

public class TileIs extends TypeSafeMatcher<Configuration> {
  private final Matcher<Tile> tileIsMatcher;

  public static TileIs tileIs(final Tile tile) {
    return new TileIs(tile);
  }

  public TileIs(final Tile tile) {
    this.tileIsMatcher = Is.is(tile);
  }

  @Override
  public boolean matchesSafely(final Configuration configuration) {
    return tileIsMatcher.matches(configuration.getTile());
  }

  @Override
  public void describeTo(final Description description) {}
}
