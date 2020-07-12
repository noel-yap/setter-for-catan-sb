package com.github.noelyap.setterforcatan.grader;

import com.github.noelyap.setterforcatan.protogen.ConfigurationOuterClass.Configuration;
import io.vavr.collection.Array;

public class PassThroughGrader implements GraderStrategy {
  @Override
  public Grade gradeConfiguration(Array<Configuration> configuration, int attempt) {
    return new Grade(1.0, true);
  }
}
