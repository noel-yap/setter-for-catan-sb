package noelyap.setterforcatan.service.board;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.Array;
import io.vavr.control.Try;
import noelyap.setterforcatan.protogen.BoardOuterClass.Board;
import noelyap.setterforcatan.protogen.ConfigurationOuterClass.Configuration;
import noelyap.setterforcatan.protogen.SpecificationOuterClass.Specification;
import noelyap.setterforcatan.protogen.TileOuterClass.Tiles;
import noelyap.setterforcatan.service.board.protogen.GenerateBoardResponse;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SoftAssertionsExtension.class)
public class BoardServiceImplTest {
  @Test
  public void shouldSetSpecificationAndBoard(final SoftAssertions softly) {
    final Tuple2<Specification, Board> expected =
        Tuple.of(
            Specification.newBuilder().addAllTiles(Array.of(Tiles.newBuilder().build())).build(),
            Board.newBuilder().addConfigurations(Configuration.newBuilder().build()).build());

    final GenerateBoardResponse actual = BoardServiceImpl.newResponse(Try.success(expected));

    softly.assertThat(actual.getSuccess().getSpecification()).isEqualTo(expected._1);
    softly.assertThat(actual.getSuccess().getBoard()).isEqualTo(expected._2);
    softly.assertThat(actual.getFailure().getErrorMessage()).isEmpty();
  }

  @Test
  public void shouldSetErrorMessage(final SoftAssertions softly) {
    final String expected = "expected";

    final GenerateBoardResponse actual =
        BoardServiceImpl.newResponse(Try.failure(new IllegalArgumentException(expected)));

    softly
        .assertThat(actual.getSuccess().getSpecification())
        .isEqualTo(Specification.newBuilder().build());
    softly.assertThat(actual.getSuccess().getBoard()).isEqualTo(Board.newBuilder().build());
    softly.assertThat(actual.getFailure().getErrorMessage()).isEqualTo(expected);
  }
}
