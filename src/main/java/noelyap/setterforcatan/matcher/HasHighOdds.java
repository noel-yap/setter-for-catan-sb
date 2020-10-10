package noelyap.setterforcatan.matcher;

import noelyap.setterforcatan.protogen.ConfigurationOuterClass.Configuration;
import noelyap.setterforcatan.util.ChitUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class HasHighOdds extends TypeSafeMatcher<Configuration> {
  public static HasHighOdds hasHighOdds() {
    return new HasHighOdds();
  }

  @Override
  public boolean matchesSafely(final Configuration configuration) {
    return ChitUtils.odds(configuration.getChit()) > 4;
  }

  @Override
  public void describeTo(final Description description) {
    description.appendText("chit odds greater than 4/36");
  }
}
