package noelyap.setterforcatan.grader;

import io.vavr.collection.Array;
import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import noelyap.setterforcatan.protogen.ConfigurationOuterClass.Configuration;

public class CompositeGrader implements GraderStrategy {
  private final Set<GraderStrategy> graders;

  private CompositeGrader(final Set<GraderStrategy> graders) {
    this.graders = graders;
  }

  public static CompositeGrader ofAll(final Set<GraderStrategy> graders) {
    return new CompositeGrader(graders.orElse(HashSet.of(new PassThroughGrader())));
  }

  @Override
  public Grade _gradeConfiguration(
      final Array<Configuration> configurations, final double threshold) {
    final Array<Grade> grades =
        Array.ofAll(graders)
            .map(g -> g._gradeConfiguration(configurations, threshold));

    return GradeUtils.aggregate(grades);
  }
}
