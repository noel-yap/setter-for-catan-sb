package com.github.noelyap.setterforcatan.grader;

import com.github.noelyap.setterforcatan.protogen.ConfigurationOuterClass.Configuration;
import io.vavr.collection.Array;

public interface GraderStrategy {
  static class Grade {
    private final double score;
    private final boolean passed;

    public Grade(final double score, final boolean passed) {
      this.score = score;
      this.passed = passed;
    }

    /** Within [0, 1], 0 being the worst and 1 being the best. */
    public double getScore() {
      return score;
    }

    /** If it passed the implementation's threshold at the time of grading. */
    public boolean passed() {
      return passed;
    }
  }

  /**
   * @return Grade of the configuration. The score must be idempotent. Whether or not it passed may
   *     be dependent on a threshold that changes.
   */
  Grade gradeConfiguration(final Array<Configuration> configuration, final int attempt);
}
