package noelyap.setterforcatan.matcher;

import io.vavr.collection.Traversable;
import noelyap.setterforcatan.protogen.ConfigurationOuterClass.Configuration;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Coordinate;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class CoordinateIsIn extends TypeSafeMatcher<Configuration> {
  final Traversable<Coordinate> coordinates;

  public static CoordinateIsIn coordinateIsIn(final Traversable<Coordinate> coordinates) {
    return new CoordinateIsIn(coordinates);
  }

  public CoordinateIsIn(final Traversable<Coordinate> coordinates) {
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
