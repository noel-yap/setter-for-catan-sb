package noelyap.setterforcatan.component.scenario.seafarers;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.Test;

public class TheNIslandsTest {
  @Test
  public void shouldNotHaveInvalidSpecifications() {
    assertThatCode(TheNIslands::new).doesNotThrowAnyException();
  }
}
