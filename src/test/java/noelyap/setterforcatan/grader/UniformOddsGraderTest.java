package noelyap.setterforcatan.grader;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.Array;
import noelyap.setterforcatan.grader.GraderStrategy.Grade;
import noelyap.setterforcatan.protogen.ConfigurationOuterClass.Configuration;
import noelyap.setterforcatan.protogen.TileOuterClass.Tile;
import noelyap.setterforcatan.util.ChitUtils;
import noelyap.setterforcatan.util.ConfigurationUtils;
import noelyap.setterforcatan.util.CoordinateUtils;
import noelyap.setterforcatan.util.TileUtils;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SoftAssertionsExtension.class)
class UniformOddsGraderTest {
  @Test
  public void shouldHavePassedGrade(final SoftAssertions softly) {
    final Array<Configuration> configurations =
        Array.of(
            ConfigurationUtils.newConfiguration(
                TileUtils.newTile(Tile.Type.GOLD_FIELD),
                CoordinateUtils.newCoordinate(1, 1),
                ChitUtils.newChit(2)),
            ConfigurationUtils.newConfiguration(
                TileUtils.newTile(Tile.Type.GOLD_FIELD),
                CoordinateUtils.newCoordinate(1, 3),
                ChitUtils.newChit(4)),
            ConfigurationUtils.newConfiguration(
                TileUtils.newTile(Tile.Type.GOLD_FIELD),
                CoordinateUtils.newCoordinate(2, 2),
                ChitUtils.newChit(8)));
    final Array<Tuple2<Integer, Integer>> validOddsRanges =
        Array.of(Tuple.of(0, 6), Tuple.of(3, 9), Tuple.of(6, 12));

    final Grade actual = UniformOddsGrader.gradeConfiguration(configurations, validOddsRanges);

    softly.assertThat(actual.getScore()).isBetween(0.0, 1.0);
    softly.assertThat(actual.passed()).isTrue();
  }

  @Test
  public void shouldUseTopVertexOfBottomTileToCalculatePenalty(final SoftAssertions softly) {
    final Array<Configuration> configurations =
        Array.of(
            ConfigurationUtils.newConfiguration(
                TileUtils.newTile(Tile.Type.LAKE),
                CoordinateUtils.newCoordinate(1, 1),
                ChitUtils.CHITS_2_3_11_12),
            ConfigurationUtils.newConfiguration(
                TileUtils.newTile(Tile.Type.LAKE),
                CoordinateUtils.newCoordinate(1, 3),
                ChitUtils.CHITS_4_10),
            ConfigurationUtils.newConfiguration(
                TileUtils.newTile(Tile.Type.LAKE),
                CoordinateUtils.newCoordinate(2, 2),
                ChitUtils.CHITS_2_3_11_12));
    final Array<Tuple2<Integer, Integer>> validOddsRanges =
        Array.of(Tuple.of(0, 6), Tuple.of(3, 9), Tuple.of(6, 12));

    final Grade actual = UniformOddsGrader.gradeConfiguration(configurations, validOddsRanges);

    softly.assertThat(actual.getScore()).isBetween(0.0, 1.0);
    softly.assertThat(actual.passed()).isFalse();
  }

  @Test
  public void shouldUseBottomVertexOfTopTileToCalculatePenalty(final SoftAssertions softly) {
    final Array<Configuration> configurations =
        Array.of(
            ConfigurationUtils.newConfiguration(
                TileUtils.newTile(Tile.Type.GOLD_FIELD),
                CoordinateUtils.newCoordinate(2, 2),
                ChitUtils.CHITS_2),
            ConfigurationUtils.newConfiguration(
                TileUtils.newTile(Tile.Type.GOLD_FIELD),
                CoordinateUtils.newCoordinate(3, 1),
                ChitUtils.CHITS_12),
            ConfigurationUtils.newConfiguration(
                TileUtils.newTile(Tile.Type.GOLD_FIELD),
                CoordinateUtils.newCoordinate(4, 2),
                ChitUtils.CHITS_2));
    final Array<Tuple2<Integer, Integer>> validOddsRanges =
        Array.of(Tuple.of(0, 6), Tuple.of(3, 9), Tuple.of(6, 12));

    final Grade actual = UniformOddsGrader.gradeConfiguration(configurations, validOddsRanges);

    softly.assertThat(actual.getScore()).isBetween(0.0, 1.0);
    softly.assertThat(actual.passed()).isFalse();
  }
}
