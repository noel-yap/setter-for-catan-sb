package noelyap.setterforcatan.util;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.Array;

public class TileMappingUtils {
  public static Tuple2<String, Array<String>> newSelfReferringEntry(final String keyValue) {
    return newEntry(keyValue, keyValue);
  }

  public static Tuple2<String, Array<String>> newEntry(final String key, final String... values) {
    return Tuple.of(key, Array.of(values));
  }
}
