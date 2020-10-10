package noelyap.setterforcatan.grader;

import io.vavr.collection.Array;
import noelyap.setterforcatan.grader.GraderStrategy.Grade;

@SuppressWarnings("deprecation")
public class GradeUtils {
  public static Grade aggregate(final Array<Grade> grades) {
    final double score = grades.map(Grade::getScore).average().getOrElse(0.0);
    final boolean passed = grades.forAll(Grade::passed);

    return new Grade(score, passed);
  }
}
