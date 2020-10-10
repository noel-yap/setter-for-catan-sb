package noelyap.setterforcatan.grader;

import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Face.Position.FACE_UP;

import com.google.common.annotations.VisibleForTesting;
import io.vavr.collection.Array;
import noelyap.setterforcatan.protogen.ConfigurationOuterClass.Configuration;

public interface GraderStrategy {
  class Grade {
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

  default Grade gradeConfiguration(
      final Array<Configuration> configurations, final double threshold) {
    return _gradeConfiguration(
        configurations.filter(c ->
                // Filter out chitless coordinates.
                c.getChit().getValuesCount() > 0
                    // Filter out face-down tiles.
                    && c.getCoordinate().getFacePosition() == FACE_UP),
        threshold);
  }

  /**
   * @param threshold Within [0, 1] monotonically decreasing from 1 to 0. 1 means the grader should
   *     use the strictest constraints for passing. 0 means the grader should use the most lenient
   *     constraints for passing (ideally it should always pass at a threshold of 0).
   * @return Grade of the configurations. The score must be idempotent. Whether or not it passed may
   *     be dependent on a threshold that changes.
   */
  @VisibleForTesting
  Grade _gradeConfiguration(final Array<Configuration> configurations, final double threshold);
}
