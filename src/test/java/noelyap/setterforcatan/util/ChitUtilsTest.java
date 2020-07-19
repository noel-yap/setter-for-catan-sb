package noelyap.setterforcatan.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ChitUtilsTest {
  @Test
  public void shouldReturnZeroForNoChits() {
    final int actual = ChitUtils.odds(ChitUtils.newChit());

    assertThat(actual).isZero();
  }

  @Test
  public void shouldReturnOneForTwoChit() {
    final int actual = ChitUtils.odds(ChitUtils.CHITS_2);

    assertThat(actual).isEqualTo(1);
  }

  @Test
  public void shouldReturnFiveForSixChit() {
    final int actual = ChitUtils.odds(ChitUtils.CHITS_6);

    assertThat(actual).isEqualTo(5);
  }

  @Test
  public void shouldReturnFiveForEightChit() {
    final int actual = ChitUtils.odds(ChitUtils.CHITS_8);

    assertThat(actual).isEqualTo(5);
  }

  @Test
  public void shouldReturnOneForTwelveChit() {
    final int actual = ChitUtils.odds(ChitUtils.CHITS_12);

    assertThat(actual).isEqualTo(1);
  }

  @Test
  public void shouldReturnSixForTwoThreeElevenTwelveChit() {
    final int actual = ChitUtils.odds(ChitUtils.CHITS_2_3_11_12);

    assertThat(actual).isEqualTo(6);
  }

  @Test
  public void shouldReturnSixForFourTenChit() {
    final int actual = ChitUtils.odds(ChitUtils.CHITS_4_10);

    assertThat(actual).isEqualTo(6);
  }
}
