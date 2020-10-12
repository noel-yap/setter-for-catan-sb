package noelyap.setterforcatan.component;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import noelyap.setterforcatan.protogen.ScenarioOuterClass.Scenario;
import org.junit.jupiter.api.Test;

public class SchemasTest {
  @Test
  public void shouldNotHaveInvalidSpecifications() {
    assertThatCode(Schemas::new).doesNotThrowAnyException();
  }

  @Test
  public void shouldHandleUnrecognizedScenario() {
    final Scenario scenario = Scenario.UNRECOGNIZED;

    assertThatThrownBy(() -> Schemas.toSpecification(scenario, 3, false))
        .isInstanceOf(InternalError.class)
        .hasMessage("Unrecognized Scenario `UNRECOGNIZED`.");
  }

  @Test
  public void shouldHandleUnimplementedScenario() {
    final Scenario scenario = Scenario.NOT_IMPLEMENTED;

    assertThatThrownBy(() -> Schemas.toSpecification(scenario, 3, false))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Scenario `NOT_IMPLEMENTED` not yet implemented.");
  }

  @Test
  public void shouldHandleUnimplementedPlayerCount() {
    final Scenario scenario = Scenario.BASE;

    assertThatThrownBy(() -> Schemas.toSpecification(scenario, 0, false))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("0 number of players specification not found for Scenario `BASE`.");
  }

  @Test
  public void shouldCreateScenario() {
    final Scenario scenario = Scenario.BASE;

    assertThat(Schemas.toSpecification(scenario, 4, true)).isNotNull();
  }
}
