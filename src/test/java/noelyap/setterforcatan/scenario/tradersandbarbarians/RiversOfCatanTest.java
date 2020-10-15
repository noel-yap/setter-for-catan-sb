package noelyap.setterforcatan.scenario.tradersandbarbarians;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RiversOfCatanTest {
  @Test
  @DisplayName("Should not have invalid specifications.")
  public void shouldNotHaveInvalidSpecifications() {
    assertThatCode(RiversOfCatan::new).doesNotThrowAnyException();
  }
}
