package noelyap.setterforcatan.scenario.seafarers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import noelyap.setterforcatan.component.Coordinates;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TheForgottenTribeTest {
  @Test
  @DisplayName("Should not have invalid specifications.")
  public void shouldNotHaveInvalidSpecifications() {
    assertThatCode(TheForgottenTribe::new).doesNotThrowAnyException();
  }

  @Test
  @DisplayName("Should bound odds for Eastern-most tiles on P3/P4 main island.")
  public void shouldBoundOddsForEasternMostTilesOnP3P4MainIsland() {
    assertThat(TheForgottenTribe.P3_P4_BOUNDED_ODDS_COORDINATES)
        .containsExactlyInAnyOrder(
            Coordinates.of(11, 3), Coordinates.of(12, 4), Coordinates.of(11, 5));
  }
}
