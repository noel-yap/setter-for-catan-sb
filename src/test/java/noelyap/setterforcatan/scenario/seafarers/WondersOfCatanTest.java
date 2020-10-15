package noelyap.setterforcatan.scenario.seafarers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import noelyap.setterforcatan.component.Coordinates;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WondersOfCatanTest {
  @Test
  @DisplayName("Should not have invalid specifications.")
  public void shouldNotHaveInvalidSpecifications() {
    assertThatCode(WondersOfCatan::new).doesNotThrowAnyException();
  }

  @Test
  @DisplayName("Should bound odds for producing tiles bordering desert on P3/P4 main island.")
  public void shouldBoundOddsForProducingTilesBorderingDesertOnP3P4MainIsland() {
    assertThat(WondersOfCatan.P3_P4_BOUNDED_ODDS_COORDINATES)
        .containsExactlyInAnyOrder(Coordinates.of(11, 3), Coordinates.of(10, 4));
  }

  @Test
  @DisplayName("Should bound odds for producing tiles bordering desert on P5/P6 main island.")
  public void shouldBoundOddsForProducingTilesBorderingDesertOnP5P6MainIsland() {
    assertThat(WondersOfCatan.P5_P6_BOUNDED_ODDS_COORDINATES)
        .containsExactlyInAnyOrder(Coordinates.of(15, 3), Coordinates.of(16, 4));
  }

  @Test
  @DisplayName("Should bound odds for producing tiles bordering desert on P7/P8 main island.")
  public void shouldBoundOddsForProducingTilesBorderingDesertOnP7P8MainIsland() {
    assertThat(WondersOfCatan.P7_P8_BOUNDED_ODDS_COORDINATES)
        .containsExactlyInAnyOrder(
            Coordinates.of(17, 3),
            Coordinates.of(2, 4),
            Coordinates.of(18, 4),
            Coordinates.of(3, 5));
  }
}
