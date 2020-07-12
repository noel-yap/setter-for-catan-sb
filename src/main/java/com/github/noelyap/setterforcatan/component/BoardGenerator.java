package com.github.noelyap.setterforcatan.component;

import com.github.noelyap.setterforcatan.grader.GraderStrategy;
import com.github.noelyap.setterforcatan.grader.GraderStrategy.Grade;
import com.github.noelyap.setterforcatan.protogen.BoardOuterClass.Board;
import com.github.noelyap.setterforcatan.protogen.ConfigurationOuterClass.Configuration;
import io.vavr.Tuple;
import io.vavr.collection.Array;
import io.vavr.collection.Stream;
import org.apache.commons.math3.util.ArithmeticUtils;

public class BoardGenerator {
  private final Specification specification;
  private final GraderStrategy graderStrategy;

  public BoardGenerator(final Specification specfication, final GraderStrategy graderStrategy) {
    this.specification = specfication;
    this.graderStrategy = graderStrategy;
  }

  public Board generateBoard() {
    final Array<Configuration> boardConfigurations =
        Stream.range(0, ArithmeticUtils.pow(6, 6))
            .map(iteration -> {
                  final Array<Configuration> configurations = specification.toConfiguration();
                  final Grade grade = graderStrategy.gradeConfiguration(configurations, iteration);

                  return Tuple.of(configurations, iteration, grade);
                })
            .takeUntil(t3 -> t3._2 > 0 && t3._3.passed())
            .foldLeft(
                Tuple.of(Array.of(Configuration.newBuilder().build()), 0, new Grade(0.0, false)),
                (currentBest, next) -> {
                  final double currentBestScore = currentBest._3.getScore();
                  final double nextScore = next._3.getScore();

                  return currentBestScore >= nextScore ? currentBest : next;
                })
            ._1;

    return Board.newBuilder()
        .addAllConfigurations(boardConfigurations)
        .addAllMarkers(specification.getMarkers())
        .build();
  }
}
