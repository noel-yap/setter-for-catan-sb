package noelyap.setterforcatan.component;

import io.vavr.collection.Array;
import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import io.vavr.collection.TreeSet;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Coordinate;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Face;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Vertex;

public class Coordinates {
  public static Coordinate of(final int x, final int y) {
    return onEdges(
        x,
        y,
        Edge.Position.TOP_RIGHT,
        Edge.Position.RIGHT,
        Edge.Position.BOTTOM_RIGHT,
        Edge.Position.BOTTOM_LEFT,
        Edge.Position.LEFT,
        Edge.Position.TOP_LEFT);
  }

  public static Coordinate onEdges(final int x, final int y, final Edge.Position... edgePositions) {
    final Set<Vertex.Position> vertexPositions =
        TreeSet.of(edgePositions)
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

  public static Coordinate onVertices(
      final int x, final int y, final Vertex.Position... vertexPositions) {
    return Coordinate.newBuilder()
        .setX(x)
        .setY(y)
        .addAllEdgePositions(Array.empty())
        .addAllVertexPositions(Array.of(vertexPositions))
        .build();
  }

  public static Coordinate faceDownOf(final int x, final int y) {
    return faceDownOnEdges(
        x,
        y,
        Edge.Position.TOP_RIGHT,
        Edge.Position.RIGHT,
        Edge.Position.BOTTOM_RIGHT,
        Edge.Position.BOTTOM_LEFT,
        Edge.Position.LEFT,
        Edge.Position.TOP_LEFT);
  }

  public static Coordinate faceDownOnEdges(
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
        .setFacePosition(Face.Position.FACE_DOWN)
        .build();
  }

  public static Set<Coordinate> adjacentOf(final Coordinate coordinate) {
    return HashSet.of(
        Coordinates.of(coordinate.getX() - 1, coordinate.getY() - 1),
        Coordinates.of(coordinate.getX() + 1, coordinate.getY() - 1),
        Coordinates.of(coordinate.getX() - 2, coordinate.getY()),
        Coordinates.of(coordinate.getX() + 2, coordinate.getY()),
        Coordinates.of(coordinate.getX() - 1, coordinate.getY() + 1),
        Coordinates.of(coordinate.getX() + 1, coordinate.getY() + 1));
  }

  public static Set<Coordinate> adjacentOf(final Set<Coordinate> coordinates) {
    return coordinates.flatMap(Coordinates::adjacentOf);
  }
}
