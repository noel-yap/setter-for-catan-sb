package noelyap.setterforcatan.generator;

import io.vavr.Tuple;
import io.vavr.collection.Array;
import io.vavr.collection.Stream;
import lombok.extern.slf4j.Slf4j;
import noelyap.setterforcatan.component.SpecificationImpl;
import noelyap.setterforcatan.grader.GraderStrategy;
import noelyap.setterforcatan.grader.GraderStrategy.Grade;
import noelyap.setterforcatan.protogen.BoardOuterClass.Board;
import noelyap.setterforcatan.protogen.ConfigurationOuterClass.Configuration;
import org.apache.commons.math3.util.ArithmeticUtils;

@Slf4j
public class BoardGenerator {
  private final SpecificationImpl specificationImpl;
  private final GraderStrategy graderStrategy;

  public BoardGenerator(
      final SpecificationImpl specification, final GraderStrategy graderStrategy) {
    this.specificationImpl = specification;
    this.graderStrategy = graderStrategy;
  }

  public Board generateBoard() {
    final int attemptsCount = ArithmeticUtils.pow(7, 7);
    final Array<Configuration> boardConfigurations =
        Stream.range(0, attemptsCount)
            .map(attempt -> {
                  log.debug("Generating board at attempt " + attempt);

                  return Tuple.of(attempt, specificationImpl.toConfiguration());
                })
            .filter(t2 -> !t2._2.isEmpty())
            .map(t2 -> {
                  final int attempt = t2._1;
                  final Array<Configuration> configurations = t2._2.get();

                  final double threshold = 1.0 - ((double) attempt / attemptsCount);
                  final Grade grade = graderStrategy.gradeConfiguration(configurations, threshold);

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

    log.debug("Building board with " + boardConfigurations);

    return Board.newBuilder()
        .addAllConfigurations(boardConfigurations)
        .addAllMarkers(specificationImpl.getMarkers())
        .build();
  }
}
