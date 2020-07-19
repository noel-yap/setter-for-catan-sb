package noelyap.setterforcatan.util;

import io.vavr.collection.Array;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Coordinate;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Vertex;

public class CoordinateUtils {
  public static Coordinate newCoordinate(final int x, final int y) {
    return newCoordinate(
        x,
        y,
        Edge.Position.TOP_RIGHT,
        Edge.Position.RIGHT,
        Edge.Position.BOTTOM_RIGHT,
        Edge.Position.BOTTOM_LEFT,
        Edge.Position.LEFT,
        Edge.Position.TOP_LEFT);
  }

  public static Coordinate newCoordinate(
      final int x, final int y, final Edge.Position... edgePositions) {
    return Coordinate.newBuilder()
        .setX(x)
        .setY(y)
        .addAllEdgePositions(Array.of(edgePositions))
        .addVertexPositions(Vertex.Position.TOP)
        .addVertexPositions(Vertex.Position.TOP_RIGHT)
        .addVertexPositions(Vertex.Position.BOTTOM_RIGHT)
        .addVertexPositions(Vertex.Position.BOTTOM)
        .addVertexPositions(Vertex.Position.BOTTOM_LEFT)
        .addVertexPositions(Vertex.Position.TOP_LEFT)
        .build();
  }

  public static Array<Coordinate> newCoordinates(final Coordinate... coordinates) {
    return Array.of(coordinates);
  }
}
