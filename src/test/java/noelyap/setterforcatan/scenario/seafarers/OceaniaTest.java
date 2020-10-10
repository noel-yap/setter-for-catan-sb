package noelyap.setterforcatan.scenario.seafarers;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.Test;

public class OceaniaTest {
  @Test
  public void shouldNotHaveInvalidSpecifications() {
    assertThatCode(Oceania::new).doesNotThrowAnyException();
  }
}