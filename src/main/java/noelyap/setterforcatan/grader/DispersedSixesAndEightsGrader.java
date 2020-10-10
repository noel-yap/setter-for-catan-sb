package noelyap.setterforcatan.grader;

import io.vavr.collection.Array;
import noelyap.setterforcatan.protogen.ConfigurationOuterClass.Configuration;

public class DispersedSixesAndEightsGrader implements GraderStrategy {
  @Override
  public Grade _gradeConfiguration(
      final Array<Configuration> configurations, final double threshold) {
    // TODO(noel-yap): Implement.
    throw new IllegalArgumentException(
        String.format(
            "GraderStrategy `%s` not yet implemented.",
            noelyap.setterforcatan.protogen.GraderStrategyOuterClass.GraderStrategy
                .DISPERSED_SIXES_AND_EIGHTS_DISTRIBUTION
                .toString()));
  }
}
