package noelyap.setterforcatan.matcher;

import noelyap.setterforcatan.component.Chits;
import noelyap.setterforcatan.protogen.ConfigurationOuterClass.Configuration;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class HasOddsGreaterThan extends TypeSafeMatcher<Configuration> {
  private final int oddsThreshold;

  public static HasOddsGreaterThan hasOddsGreaterThan(final int oddsThreshold) {
    return new HasOddsGreaterThan(oddsThreshold);
  }

  public HasOddsGreaterThan(final int oddsThreshold) {
    this.oddsThreshold = oddsThreshold;
  }

  @Override
  public boolean matchesSafely(final Configuration configuration) {
    return Chits.odds(configuration.getChit()) > oddsThreshold;
  }

  @Override
  public void describeTo(final Description description) {
    description.appendText("chit odds greater than " + oddsThreshold + "/36");
  }
}
