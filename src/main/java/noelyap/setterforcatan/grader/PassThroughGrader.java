package noelyap.setterforcatan.grader;

import io.vavr.collection.Array;
import noelyap.setterforcatan.protogen.ConfigurationOuterClass.Configuration;

public class PassThroughGrader implements GraderStrategy {
  @Override
  public Grade gradeConfiguration(
      final Array<Configuration> configuration, final double threshold) {
    return new Grade(1.0, true);
  }
}
