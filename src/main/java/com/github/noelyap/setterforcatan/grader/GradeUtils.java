package com.github.noelyap.setterforcatan.grader;

import com.github.noelyap.setterforcatan.grader.GraderStrategy.Grade;
import io.vavr.collection.Array;

@SuppressWarnings("deprecation")
public class GradeUtils {
  public static Grade aggregate(final Array<Grade> grades) {
    final double score = grades.map(g -> g.getScore()).average().getOrElse(0.0);
    final boolean passed = grades.forAll(g -> g.passed());

    return new Grade(score, passed);
  }
}
