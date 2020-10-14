package noelyap.setterforcatan.component;

import io.vavr.collection.Array;
import noelyap.setterforcatan.protogen.ChitOuterClass.Chit;

public class Chits {
  public static final Chit CHIT_2 = Chits.ofValues(2);
  public static final Chit CHIT_3 = Chits.ofValues(3);
  public static final Chit CHIT_4 = Chits.ofValues(4);
  public static final Chit CHIT_5 = Chits.ofValues(5);
  public static final Chit CHIT_6 = Chits.ofValues(6);
  public static final Chit CHIT_8 = Chits.ofValues(8);
  public static final Chit CHIT_9 = Chits.ofValues(9);
  public static final Chit CHIT_10 = Chits.ofValues(10);
  public static final Chit CHIT_11 = Chits.ofValues(11);
  public static final Chit CHIT_12 = Chits.ofValues(12);
  public static final Chit CHITS_2_12 = Chits.ofValues(2, 12);
  public static final Chit CHITS_4_10 = Chits.ofValues(4, 10);
  public static final Chit CHITS_2_3_11_12 = Chits.ofValues(2, 3, 11, 12);

  public static Chit empty() {
    return Chit.newBuilder().build();
  }

  public static int odds(final Chit chit) {
    return Array.ofAll(chit.getValuesList()).map(v -> 6 - Math.abs(7 - v)).sum().intValue();
  }

  private static Chit ofValues(final int... values) {
    return Chit.newBuilder().addAllValues(Array.ofAll(values)).build();
  }
}
