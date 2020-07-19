package noelyap.setterforcatan.board;

import io.vavr.control.Try;
import noelyap.setterforcatan.board.protogen.GenerateBoardResponse;
import noelyap.setterforcatan.protogen.BoardOuterClass.Board;
import noelyap.setterforcatan.protogen.ConfigurationOuterClass.Configuration;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SoftAssertionsExtension.class)
public class BoardServiceImplTest {
  @Test
  public void shouldSetBoard(final SoftAssertions softly) {
    final Board expected =
        Board.newBuilder().addConfigurations(Configuration.newBuilder().build()).build();

    final GenerateBoardResponse actual = BoardServiceImpl.newResponse(Try.success(expected));

    softly.assertThat(actual.getBoard()).isEqualTo(expected);
    softly.assertThat(actual.getErrorMessage()).isEmpty();
  }

  @Test
  public void shouldSetErrorMessage(final SoftAssertions softly) {
    final String expected = "expected";

    final GenerateBoardResponse actual =
        BoardServiceImpl.newResponse(Try.failure(new IllegalArgumentException(expected)));

    softly.assertThat(actual.getBoard()).isEqualTo(Board.newBuilder().build());
    softly.assertThat(actual.getErrorMessage()).isEqualTo(expected);
  }
}
