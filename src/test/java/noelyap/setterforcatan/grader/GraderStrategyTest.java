package noelyap.setterforcatan.grader;

import static noelyap.setterforcatan.component.Chits.CHITS_2_3_11_12;
import static noelyap.setterforcatan.component.Chits.CHITS_4_10;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Face.Position.FACE_DOWN;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.GOLD_FIELD;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.LAKE;
import static noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type.SWAMP;

import io.vavr.collection.Array;
import noelyap.setterforcatan.protogen.ConfigurationOuterClass.Configuration;
import noelyap.setterforcatan.protogen.CoordinateOuterClass;
import noelyap.setterforcatan.protogen.TileOuterClass.Tile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GraderStrategyTest {
  @Spy private final GraderStrategy graderStrategySpy = new GraderStrategySut();

  @Test
  @DisplayName("Should ignore chitless tiles.")
  public void shouldIgnoreChitlessTiles() {
    final var expectedConfiguration =
        Configuration.newBuilder()
            .setTile(Tile.newBuilder().setType(GOLD_FIELD).build())
            .setCoordinate(CoordinateOuterClass.Coordinate.newBuilder().setX(2).setY(2).build())
            .setChit(CHITS_2_3_11_12)
            .build();
    final var expectedConfigurations = Array.of(expectedConfiguration);

    graderStrategySpy.gradeConfiguration(
        Array.of(
            Configuration.newBuilder()
                .setTile(Tile.newBuilder().setType(SWAMP).build())
                .setCoordinate(CoordinateOuterClass.Coordinate.newBuilder().build())
                .build(),
            expectedConfiguration),
        .5);

    Mockito.verify(graderStrategySpy)._gradeConfiguration(expectedConfigurations, .5);
  }

  @Test
  @DisplayName("Should ignore face down tiles.")
  public void shouldIgnoreFaceDownTiles() {
    final var expectedConfiguration =
        Configuration.newBuilder()
            .setTile(Tile.newBuilder().setType(GOLD_FIELD).build())
            .setCoordinate(CoordinateOuterClass.Coordinate.newBuilder().setX(2).setY(2).build())
            .setChit(CHITS_2_3_11_12)
            .build();
    final var expectedConfigurations = Array.of(expectedConfiguration);

    graderStrategySpy.gradeConfiguration(
        Array.of(
            Configuration.newBuilder()
                .setTile(Tile.newBuilder().setType(LAKE).build())
                .setCoordinate(
                    CoordinateOuterClass.Coordinate.newBuilder().setFacePosition(FACE_DOWN).build())
                .setChit(CHITS_4_10)
                .build(),
            expectedConfiguration),
        .5);

    Mockito.verify(graderStrategySpy)._gradeConfiguration(expectedConfigurations, .5);
  }

  private class GraderStrategySut implements GraderStrategy {
    @Override
    public Grade _gradeConfiguration(
        final Array<Configuration> configurations, final double threshold) {
      return new Grade(threshold, true);
    }
  }
}
