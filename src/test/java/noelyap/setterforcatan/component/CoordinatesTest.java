package noelyap.setterforcatan.component;

import static org.assertj.core.api.Assertions.assertThat;

import io.vavr.collection.Array;
import noelyap.setterforcatan.protogen.CoordinateOuterClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CoordinatesTest {
  @Test
  @DisplayName("Should get adjacent coordinates.")
  public void shouldGetAdjacentCoordinates() {
    final var expected =
        Array.of(
            Coordinates.of(1, 1),
            Coordinates.of(3, 1),
            Coordinates.of(0, 2),
            Coordinates.of(4, 2),
            Coordinates.of(1, 3),
            Coordinates.of(3, 3));

    final var actual = Coordinates.adjacentOf(Coordinates.of(2, 2));

    assertThat(actual)
        .containsExactlyInAnyOrder(expected.toJavaArray(CoordinateOuterClass.Coordinate[]::new));
  }
}
