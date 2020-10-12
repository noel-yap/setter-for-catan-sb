package noelyap.setterforcatan.component;

import static noelyap.setterforcatan.component.Chits.CHITS_2_3_11_12;
import static noelyap.setterforcatan.component.Chits.CHITS_4_10;
import static noelyap.setterforcatan.component.Chits.CHIT_12;
import static noelyap.setterforcatan.component.Chits.CHIT_2;
import static noelyap.setterforcatan.component.Chits.CHIT_6;
import static noelyap.setterforcatan.component.Chits.CHIT_8;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ChitsTest {
  @Test
  public void shouldReturnZeroForNoChits() {
    final int actual = Chits.odds(Chits.empty());

    assertThat(actual).isZero();
  }

  @Test
  public void shouldReturnOneForTwoChit() {
    final int actual = Chits.odds(CHIT_2);

    assertThat(actual).isEqualTo(1);
  }

  @Test
  public void shouldReturnFiveForSixChit() {
    final int actual = Chits.odds(CHIT_6);

    assertThat(actual).isEqualTo(5);
  }

  @Test
  public void shouldReturnFiveForEightChit() {
    final int actual = Chits.odds(CHIT_8);

    assertThat(actual).isEqualTo(5);
  }

  @Test
  public void shouldReturnOneForTwelveChit() {
    final int actual = Chits.odds(CHIT_12);

    assertThat(actual).isEqualTo(1);
  }

  @Test
  public void shouldReturnSixForTwoThreeElevenTwelveChit() {
    final int actual = Chits.odds(CHITS_2_3_11_12);

    assertThat(actual).isEqualTo(6);
  }

  @Test
  public void shouldReturnSixForFourTenChit() {
    final int actual = Chits.odds(CHITS_4_10);

    assertThat(actual).isEqualTo(6);
  }
}
