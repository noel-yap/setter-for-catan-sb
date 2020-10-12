package noelyap.setterforcatan.grader;

import static noelyap.setterforcatan.component.Chits.CHITS_2_3_11_12;
import static noelyap.setterforcatan.component.Chits.CHITS_4_10;
import static noelyap.setterforcatan.component.Chits.CHIT_12;
import static noelyap.setterforcatan.component.Chits.CHIT_2;
import static noelyap.setterforcatan.component.Chits.CHIT_4;
import static noelyap.setterforcatan.component.Chits.CHIT_8;
import static noelyap.setterforcatan.component.Tiles.GOLD_FIELD;
import static noelyap.setterforcatan.component.Tiles.LAKE;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.Array;
import noelyap.setterforcatan.component.Coordinates;
import noelyap.setterforcatan.grader.GraderStrategy.Grade;
import noelyap.setterforcatan.protogen.ConfigurationOuterClass.Configuration;
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
            Configuration.newBuilder()
                .setTile(GOLD_FIELD)
                .setCoordinate(Coordinates.of(1, 1))
                .setChit(CHIT_2)
                .build(),
            Configuration.newBuilder()
                .setTile(GOLD_FIELD)
                .setCoordinate(Coordinates.of(1, 3))
                .setChit(CHIT_4)
                .build(),
            Configuration.newBuilder()
                .setTile(GOLD_FIELD)
                .setCoordinate(Coordinates.of(2, 2))
                .setChit(CHIT_8)
                .build());
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
            Configuration.newBuilder()
                .setTile(LAKE)
                .setCoordinate(Coordinates.of(1, 1))
                .setChit(CHITS_2_3_11_12)
                .build(),
            Configuration.newBuilder()
                .setTile(LAKE)
                .setCoordinate(Coordinates.of(1, 3))
                .setChit(CHITS_4_10)
                .build(),
            Configuration.newBuilder()
                .setTile(LAKE)
                .setCoordinate(Coordinates.of(2, 2))
                .setChit(CHITS_2_3_11_12)
                .build());
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
            Configuration.newBuilder()
                .setTile(GOLD_FIELD)
                .setCoordinate(Coordinates.of(2, 2))
                .setChit(CHIT_2)
                .build(),
            Configuration.newBuilder()
                .setTile(GOLD_FIELD)
                .setCoordinate(Coordinates.of(3, 1))
                .setChit(CHIT_12)
                .build(),
            Configuration.newBuilder()
                .setTile(GOLD_FIELD)
                .setCoordinate(Coordinates.of(4, 2))
                .setChit(CHIT_2)
                .build());
    final Array<Tuple2<Integer, Integer>> validOddsRanges =
        Array.of(Tuple.of(0, 6), Tuple.of(3, 9), Tuple.of(6, 12));

    final Grade actual = UniformOddsGrader.gradeConfiguration(configurations, validOddsRanges);

    softly.assertThat(actual.getScore()).isBetween(0.0, 1.0);
    softly.assertThat(actual.passed()).isFalse();
  }
}
