package com.github.noelyap.setterforcatan.grader;

import com.github.noelyap.setterforcatan.protogen.ConfigurationOuterClass.Configuration;
import io.vavr.collection.Array;
import io.vavr.collection.HashSet;
import io.vavr.collection.Set;

public class CompositeGrader implements GraderStrategy {
  private final Set<GraderStrategy> graders;

  private CompositeGrader(final Set<GraderStrategy> graders) {
    this.graders = graders;
  }

  public static CompositeGrader ofAll(final Set<GraderStrategy> graders) {
    return new CompositeGrader(graders.orElse(HashSet.of(new PassThroughGrader())));
  }

  @Override
  public Grade gradeConfiguration(Array<Configuration> configuration, int attempt) {
    final Array<Grade> grades =
        Array.ofAll(graders)
            .map(g -> {
                  return g.gradeConfiguration(configuration, attempt);
                });

    return GradeUtils.aggregate(grades);
  }
}
