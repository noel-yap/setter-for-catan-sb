package noelyap.setterforcatan.util;

import io.vavr.collection.Array;
import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
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
    final Set<Vertex.Position> vertexPositions =
        HashSet.of(edgePositions)
            .flatMap(ep ->
                    Array.of(
                        Vertex.Position.forNumber(ep.getNumber()),
                        Vertex.Position.forNumber((ep.getNumber() + 1) % 6)));

    return Coordinate.newBuilder()
        .setX(x)
        .setY(y)
        .addAllEdgePositions(Array.of(edgePositions))
        .addAllVertexPositions(vertexPositions)
        .build();
  }

  public static Array<Coordinate> newCoordinates(final Coordinate... coordinates) {
    return Array.of(coordinates);
  }
}
