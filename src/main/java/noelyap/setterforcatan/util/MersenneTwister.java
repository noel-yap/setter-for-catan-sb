package noelyap.setterforcatan.util;

import java.util.Random;

@SuppressWarnings("serial")
public class MersenneTwister extends Random {
  private final MT prng;

  public MersenneTwister() {
    this.prng = new MT();
  }

  public MersenneTwister(final int seed) {
    this.prng = new MT(seed);
  }

  @Override
  public int next(final int bits) {
    return prng.next(bits);
  }

  private static class MT extends org.apache.commons.math3.random.MersenneTwister {
    public MT() {
      super();
    }

    public MT(final int seed) {
      super(seed);
    }

    @Override
    public int next(final int bits) {
      return super.next(bits);
    }
  }
}
