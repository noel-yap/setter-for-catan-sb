package noelyap.setterforcatan.grader;

import io.vavr.collection.Array;
import noelyap.setterforcatan.protogen.ConfigurationOuterClass.Configuration;

public class DispersedTwosAndTwelvesGrader implements GraderStrategy {
  @Override
  public Grade _gradeConfiguration(
      final Array<Configuration> configurations, final double threshold) {
    // TODO(noel-yap): Implement.
    throw new IllegalArgumentException(
        String.format(
            "GraderStrategy `%s` not yet implemented.",
            noelyap.setterforcatan.protogen.GraderStrategyOuterClass.GraderStrategy
                .DISPERSED_TWOS_AND_TWELVES_DISTRIBUTION
                .toString()));
  }
}
