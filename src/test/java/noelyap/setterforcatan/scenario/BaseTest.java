package noelyap.setterforcatan.scenario;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.Test;

public class BaseTest {
  @Test
  public void shouldNotHaveInvalidSpecifications() {
    assertThatCode(Base::new).doesNotThrowAnyException();
  }
}
