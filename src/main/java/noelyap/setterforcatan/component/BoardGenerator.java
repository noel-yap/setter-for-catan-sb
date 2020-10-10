package noelyap.setterforcatan.component;

import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Face.Position.FACE_UP;

import io.vavr.Tuple;
import io.vavr.collection.Array;
import io.vavr.collection.Stream;
import noelyap.setterforcatan.grader.GraderStrategy;
import noelyap.setterforcatan.grader.GraderStrategy.Grade;
import noelyap.setterforcatan.protogen.BoardOuterClass.Board;
import noelyap.setterforcatan.protogen.ConfigurationOuterClass.Configuration;
import org.apache.commons.math3.util.ArithmeticUtils;

public class BoardGenerator {
  private final SpecificationImpl specificationImpl;
  private final GraderStrategy graderStrategy;

  public BoardGenerator(final SpecificationImpl specfication, final GraderStrategy graderStrategy) {
    this.specificationImpl = specfication;
    this.graderStrategy = graderStrategy;
  }

  public Board generateBoard() {
    final int attemptsCount = ArithmeticUtils.pow(6, 6);
    final Array<Configuration> boardConfigurations =
        Stream.range(0, attemptsCount)
            .map(attempt -> {
                  final double threshold = 1.0 - ((double) attempt / attemptsCount);
                  final Array<Configuration> configurations =
                      specificationImpl
                          .toConfiguration();
                  final Grade grade = graderStrategy.gradeConfiguration(
                      configurations.filter(c ->
                          c.getCoordinate().getFacePosition()
                              == FACE_UP), // Filter out face-down tiles.
                      threshold);

                  return Tuple.of(configurations, attempt, grade, threshold);
                })
            .takeUntil(t4 -> t4._2 > 0 && t4._3.passed())
            .foldLeft(
                Tuple.of(
                    Array.of(Configuration.newBuilder().build()), 0, new Grade(0.0, false), 1.0),
                (currentBest, next) -> {
                  final double currentBestScore = currentBest._3.getScore();
                  final double nextScore = next._3.getScore();

                  return currentBestScore >= nextScore ? currentBest : next;
                })
            ._1;

    return Board.newBuilder()
        .addAllConfigurations(boardConfigurations)
        .addAllMarkers(specificationImpl.getMarkers())
        .build();
  }
}
