package noelyap.setterforcatan.component;

import io.vavr.collection.Array;
import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import io.vavr.collection.TreeSet;
import noelyap.setterforcatan.protogen.CoordinateOuterClass;

public class Coordinates {
  public static CoordinateOuterClass.Coordinate of(final int x, final int y) {
    return withEdges(
        x,
        y,
        CoordinateOuterClass.Edge.Position.TOP_RIGHT,
        CoordinateOuterClass.Edge.Position.RIGHT,
        CoordinateOuterClass.Edge.Position.BOTTOM_RIGHT,
        CoordinateOuterClass.Edge.Position.BOTTOM_LEFT,
        CoordinateOuterClass.Edge.Position.LEFT,
        CoordinateOuterClass.Edge.Position.TOP_LEFT);
  }

  public static CoordinateOuterClass.Coordinate withEdges(
      final int x, final int y, final CoordinateOuterClass.Edge.Position... edgePositions) {
    final Set<CoordinateOuterClass.Vertex.Position> vertexPositions =
        TreeSet.of(edgePositions)
            .flatMap(ep ->
                    Array.of(
                        CoordinateOuterClass.Vertex.Position.forNumber(ep.getNumber()),
                        CoordinateOuterClass.Vertex.Position.forNumber((ep.getNumber() + 1) % 6)));

    return CoordinateOuterClass.Coordinate.newBuilder()
        .setX(x)
        .setY(y)
        .addAllEdgePositions(Array.of(edgePositions))
        .addAllVertexPositions(vertexPositions)
        .build();
  }

  public static CoordinateOuterClass.Coordinate withVertices(
      final int x, final int y, final CoordinateOuterClass.Vertex.Position... vertexPositions) {
    return CoordinateOuterClass.Coordinate.newBuilder()
        .setX(x)
        .setY(y)
        .addAllEdgePositions(Array.empty())
        .addAllVertexPositions(Array.of(vertexPositions))
        .build();
  }

  public static CoordinateOuterClass.Coordinate faceDownOf(
      final int x, final int y, final CoordinateOuterClass.Edge.Position... edgePositions) {
    final Set<CoordinateOuterClass.Vertex.Position> vertexPositions =
        HashSet.of(edgePositions)
            .flatMap(ep ->
                    Array.of(
                        CoordinateOuterClass.Vertex.Position.forNumber(ep.getNumber()),
                        CoordinateOuterClass.Vertex.Position.forNumber((ep.getNumber() + 1) % 6)));

    return CoordinateOuterClass.Coordinate.newBuilder()
        .setX(x)
        .setY(y)
        .addAllEdgePositions(Array.of(edgePositions))
        .addAllVertexPositions(vertexPositions)
        .setFacePosition(CoordinateOuterClass.Face.Position.FACE_DOWN)
        .build();
  }
}
