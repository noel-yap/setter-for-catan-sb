package com.github.noelyap.setterforcatan.util;

import com.github.noelyap.setterforcatan.protogen.ChitOuterClass.Chit;
import io.vavr.collection.Array;

public class ChitUtils {
  public static Chit CHITS_2 = newChit(2);
  public static Chit CHITS_3 = newChit(3);
  public static Chit CHITS_4 = newChit(4);
  public static Chit CHITS_5 = newChit(5);
  public static Chit CHITS_6 = newChit(6);
  public static Chit CHITS_8 = newChit(8);
  public static Chit CHITS_9 = newChit(9);
  public static Chit CHITS_10 = newChit(10);
  public static Chit CHITS_11 = newChit(11);
  public static Chit CHITS_12 = newChit(12);
  public static Chit CHITS_2_3_11_12 = newChit(2, 3, 11, 12);
  public static Chit CHITS_4_10 = newChit(4, 10);

  public static Chit newChit(final int... values) {
    return newChit(Array.ofAll(values));
  }

  public static Chit newChit(final Array<Integer> values) {
    return Chit.newBuilder().addAllValue(values).build();
  }

  public static Array<Chit> newChits(final int... values) {
    return Array.ofAll(values).map(v -> newChit(v));
  }

  public static int odds(final Chit chit) {
    return Array.ofAll(chit.getValueList()).map(v -> 6 - Math.abs(7 - v)).sum().intValue();
  }
}
