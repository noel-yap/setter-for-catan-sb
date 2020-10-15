package noelyap.setterforcatan.component;

import io.vavr.collection.Array;
import noelyap.setterforcatan.protogen.CoordinateOuterClass;
import noelyap.setterforcatan.protogen.MarkerOuterClass.Marker;
import noelyap.setterforcatan.protogen.MarkerOuterClass.Marker.Type;

public class Markers {
  public static Marker of(Type type, Array<CoordinateOuterClass.Coordinate> coordinates) {
    return Marker.newBuilder().setType(type).addAllCoordinates(coordinates).build();
  }
}
