package noelyap.setterforcatan.matcher;

import noelyap.setterforcatan.protogen.ConfigurationOuterClass.Configuration;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class PassThroughMatcher extends TypeSafeMatcher<Configuration> {
  @Override
  public boolean matchesSafely(final Configuration __) {
    return true;
  }

  @Override
  public void describeTo(final Description description) {}
}
