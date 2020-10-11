package noelyap.setterforcatan.scenario.seafarers;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HeadingForNewShoresTest {
  @Test
  @DisplayName("Should not have invalid specifications.")
  public void shouldNotHaveInvalidSpecifications() {
    assertThatCode(HeadingForNewShores::new).doesNotThrowAnyException();
  }
}
