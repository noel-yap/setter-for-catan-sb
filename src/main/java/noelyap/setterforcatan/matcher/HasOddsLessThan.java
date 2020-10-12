package noelyap.setterforcatan.matcher;

import noelyap.setterforcatan.component.Chits;
import noelyap.setterforcatan.protogen.ConfigurationOuterClass.Configuration;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class HasOddsLessThan extends TypeSafeMatcher<Configuration> {
  private final int oddsThreshold;

  public static HasOddsLessThan hasOddsLessThan(final int oddsThreshold) {
    return new HasOddsLessThan(oddsThreshold);
  }

  public HasOddsLessThan(final int oddsThreshold) {
    this.oddsThreshold = oddsThreshold;
  }

  @Override
  public boolean matchesSafely(final Configuration configuration) {
    return Chits.odds(configuration.getChit()) < oddsThreshold;
  }

  @Override
  public void describeTo(final Description description) {
    description.appendText("chit odds less than " + oddsThreshold + "/36");
  }
}
