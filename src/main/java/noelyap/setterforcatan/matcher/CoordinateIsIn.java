package noelyap.setterforcatan.matcher;

import io.vavr.collection.Array;
import noelyap.setterforcatan.protogen.ConfigurationOuterClass.Configuration;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Coordinate;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class CoordinateIsIn extends TypeSafeMatcher<Configuration> {
  final Array<Coordinate> coordinates;

  public static CoordinateIsIn coordinateIsIn(final Array<Coordinate> coordinates) {
    return new CoordinateIsIn(coordinates);
  }

  public CoordinateIsIn(final Array<Coordinate> coordinates) {
    this.coordinates = coordinates;
  }

  @Override
  protected boolean matchesSafely(final Configuration configuration) {
    return coordinates.contains(configuration.getCoordinate());
  }

  @Override
  public void describeTo(final Description description) {
    description.appendText("coordinate is in " + coordinates);
  }
}
