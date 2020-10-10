package noelyap.setterforcatan.matcher;

import noelyap.setterforcatan.protogen.ConfigurationOuterClass.Configuration;
import noelyap.setterforcatan.util.ChitUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class HasLowOdds extends TypeSafeMatcher<Configuration> {
  public static HasLowOdds hasLowOdds() {
    return new HasLowOdds();
  }

  @Override
  public boolean matchesSafely(final Configuration configuration) {
    return ChitUtils.odds(configuration.getChit()) < 2;
  }

  @Override
  public void describeTo(final Description description) {
    description.appendText("chit odds less than 2/36");
  }
}
