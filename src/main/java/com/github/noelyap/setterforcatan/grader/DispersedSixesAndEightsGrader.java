package com.github.noelyap.setterforcatan.grader;

import com.github.noelyap.setterforcatan.protogen.ConfigurationOuterClass.Configuration;
import io.vavr.collection.Array;

public class DispersedSixesAndEightsGrader implements GraderStrategy {
  @Override
  public Grade gradeConfiguration(final Array<Configuration> configuration, final int attempt) {
    // TODO(nyap): Implement.
    throw new IllegalArgumentException(
        String.format(
            "GraderStrategy `%s` not yet implemented.",
            com.github.noelyap.setterforcatan.protogen.GraderStrategyOuterClass.GraderStrategy
                .DISPERSED_SIXES_AND_EIGHTS_DISTRIBUTION
                .toString()));
  }
}
