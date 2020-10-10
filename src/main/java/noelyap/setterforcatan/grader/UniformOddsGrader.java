package noelyap.setterforcatan.grader;

import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Face.*;

import com.google.common.annotations.VisibleForTesting;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import io.vavr.collection.Array;
import io.vavr.collection.HashMap;
import io.vavr.collection.HashSet;
import io.vavr.collection.Map;
import io.vavr.collection.Set;
import io.vavr.collection.Stream;
import noelyap.setterforcatan.protogen.ConfigurationOuterClass.Configuration;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Coordinate;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Vertex;
import noelyap.setterforcatan.util.ChitUtils;
import org.apache.commons.lang3.Range;

public class UniformOddsGrader implements GraderStrategy {
  @Override
  public Grade _gradeConfiguration(
      final Array<Configuration> configurations, final double threshold) {
    final Array<Array<Tuple2<Integer, Integer>>> steppedValidOddsRanges =
        Array.of(
            Array.of(Tuple.of(1, 6), Tuple.of(3, 11), Tuple.of(6, 15)),
            Array.of(Tuple.of(1, 6), Tuple.of(3, 11), Tuple.of(5, 16)),
            Array.of(Tuple.of(1, 6), Tuple.of(3, 11), Tuple.of(4, 17)),
            Array.of(Tuple.of(1, 6), Tuple.of(2, 12), Tuple.of(4, 17)),
            Array.of(Tuple.of(1, 6), Tuple.of(2, 12), Tuple.of(3, 18)),
            Array.of(
                Tuple.of(0, Integer.MAX_VALUE),
                Tuple.of(0, Integer.MAX_VALUE),
                Tuple.of(0, Integer.MAX_VALUE)));

    final Array<Tuple2<Integer, Integer>> validOddsRanges =
        steppedValidOddsRanges.get((int) ((1 - threshold) * (steppedValidOddsRanges.size() - 1)));

    return gradeConfiguration(configurations, validOddsRanges);
  }

  @VisibleForTesting
  static Grade gradeConfiguration(
      final Array<Configuration> configurations,
      final Array<Tuple2<Integer, Integer>> validOddsRanges) {
    final Array<Coordinate> coordinates = configurations.map(Configuration::getCoordinate);

    final Map<Tuple3<Integer, Integer, Vertex.Position>, Integer> coordinateOddsMap =
        HashMap.ofEntries(
            configurations
                .map(c -> Tuple.of(c.getCoordinate(), ChitUtils.odds(c.getChit())))
                .flatMap(t2 -> {
                      final Coordinate coordinate = t2._1;
                      final int odds = t2._2;

                      return Array.ofAll(coordinate.getVertexPositionsList())
                          .map(vp ->
                                  Tuple.of(
                                      Tuple.of(coordinate.getX(), coordinate.getY(), vp), odds));
                    }));

    final Set<Integer> xs = HashSet.ofAll(coordinates.map(Coordinate::getX));
    final int minX = xs.min().get();
    final int maxX = xs.max().get();

    final Set<Integer> ys = HashSet.ofAll(coordinates.map(Coordinate::getY));
    final int minY = ys.min().get();
    final int maxY = ys.max().get();

    final Array<Array<Integer>> vertexContributions =
        Stream.range(minX - 2, maxX + 2)
            .crossProduct(Stream.range(minY - 1, maxY + 1))
            .flatMap(t2 -> {
                  final int x = t2._1;
                  final int y = t2._2;

                  final Array<Integer> topVertexContributions =
                      Array.of(
                              Tuple.of(x, y, Vertex.Position.TOP),
                              Tuple.of(x - 1, y - 1, Vertex.Position.BOTTOM_RIGHT),
                              Tuple.of(x + 1, y - 1, Vertex.Position.BOTTOM_LEFT))
                          .map(coordinateOddsMap::get)
                          .flatMap(o -> o);
                  final Array<Integer> topRightVertexContributions =
                      Array.of(
                              Tuple.of(x, y, Vertex.Position.TOP_RIGHT),
                              Tuple.of(x + 1, y - 1, Vertex.Position.BOTTOM),
                              Tuple.of(x + 2, y, Vertex.Position.TOP_LEFT))
                          .map(coordinateOddsMap::get)
                          .flatMap(o -> o);

                  return Array.of(topVertexContributions, topRightVertexContributions);
                })
            .filter(ai -> !ai.isEmpty())
            .collect(Array.collector());

    final Array<Grade> vertexGrades =
        vertexContributions.map(ai -> {
              final int tileCount = ai.size();
              final Tuple2<Integer, Integer> validOddsRange = validOddsRanges.get(tileCount - 1);
              final int rangeMin = validOddsRange._1;
              final int rangeMax = validOddsRange._2;

              final int vertexOdds = ai.sum().intValue();
              final int maxOdds = 6 * tileCount;
              final int medianOdds = maxOdds / 2;

              final double score =
                  Math.pow(1.0 - Math.abs((double) (vertexOdds - medianOdds)) / medianOdds, 2);
              final boolean passed = Range.between(rangeMin, rangeMax).contains(vertexOdds);

              return new Grade(score, passed);
            });

    return GradeUtils.aggregate(vertexGrades);
  }
}
