package noelyap.setterforcatan.component;

import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Vertex.Position.BOTTOM;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Vertex.Position.TOP_LEFT;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Vertex.Position.TOP_RIGHT;

import io.vavr.collection.Array;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Vertex;
import noelyap.setterforcatan.protogen.TileOuterClass;
import noelyap.setterforcatan.protogen.TileOuterClass.Tile;
import noelyap.setterforcatan.protogen.TileOuterClass.Tile.ReservedName;
import noelyap.setterforcatan.protogen.TileOuterClass.Tile.Shape;
import noelyap.setterforcatan.protogen.TileOuterClass.Tile.Type;

public class Tiles {
  public static final String FISHERMEN_OF_CATAN_DESERT_CONVERTIBLE_TO_LAKE =
      ReservedName.FISHERMEN_OF_CATAN_DESERT_CONVERTIBLE_TO_LAKE
          .getValueDescriptor()
          .getOptions()
          .getExtension(TileOuterClass.name);
  public static final String FISHERMEN_OF_CATAN_LAKE =
      ReservedName.FISHERMEN_OF_CATAN_LAKE_CONVERTED_FROM_DESERT
          .getValueDescriptor()
          .getOptions()
          .getExtension(TileOuterClass.name);
  public static final String FISHERMEN_OF_CATAN_FISHERY =
      ReservedName.FISHERMEN_OF_CATAN_FISHERY
          .getValueDescriptor()
          .getOptions()
          .getExtension(TileOuterClass.name);

  public static final Tile GENERIC_HARBOR = Tiles.ofType(Type.GENERIC_HARBOR, Shape.TRIANGLE);
  public static final Tile BRICK_HARBOR = Tiles.ofType(Type.BRICK_HARBOR, Shape.TRIANGLE);
  public static final Tile GRAIN_HARBOR = Tiles.ofType(Type.GRAIN_HARBOR, Shape.TRIANGLE);
  public static final Tile LUMBER_HARBOR = Tiles.ofType(Type.LUMBER_HARBOR, Shape.TRIANGLE);
  public static final Tile ORE_HARBOR = Tiles.ofType(Type.ORE_HARBOR, Shape.TRIANGLE);
  public static final Tile WOOL_HARBOR = Tiles.ofType(Type.WOOL_HARBOR, Shape.TRIANGLE);

  public static final Tile DESERT = Tiles.ofType(Type.DESERT, Shape.HEXAGON);
  public static final Tile FIELD = Tiles.ofType(Type.FIELD, Shape.HEXAGON);
  public static final Tile FOREST = Tiles.ofType(Type.FOREST, Shape.HEXAGON);
  public static final Tile HILL = Tiles.ofType(Type.HILL, Shape.HEXAGON);
  public static final Tile MOUNTAIN = Tiles.ofType(Type.MOUNTAIN, Shape.HEXAGON);
  public static final Tile PASTURE = Tiles.ofType(Type.PASTURE, Shape.HEXAGON);

  public static final Tile SEA = Tiles.ofType(Type.SEA, Shape.HEXAGON);
  public static final Tile LAKE = Tiles.ofType(Type.LAKE, Shape.HEXAGON);
  public static final Tile FISHERY = Tiles.ofType(Type.FISHERY, Shape.CHEVRON);
  public static final Tile RIVER = Tiles.ofType(Type.RIVER, Shape.POLYMORPHIC);

  public static final Tile GOLD_FIELD = Tiles.ofType(Type.GOLD_FIELD, Shape.HEXAGON);
  public static final Tile SWAMP = Tiles.ofType(Type.SWAMP, Shape.HEXAGON);
  public static final Tile OASIS =
      Tiles.withSpecialVertices(Type.OASIS, TOP_RIGHT, BOTTOM, TOP_LEFT);
  public static final Tile CASTLE = Tiles.ofType(Type.CASTLE, Shape.HEXAGON);
  public static final Tile GLASSWORKS = Tiles.ofType(Type.GLASSWORKS, Shape.HEXAGON);
  public static final Tile QUARRY = Tiles.ofType(Type.QUARRY, Shape.HEXAGON);

  public static final Tile DEVELOPMENT_CARD = Tiles.ofType(Type.DEVELOPMENT_CARD, Shape.RECTANGLE);
  public static final Tile VICTORY_POINT = Tiles.ofType(Type.VICTORY_POINT, Shape.POLYMORPHIC);
  public static final Tile CHIT = Tiles.ofType(Type.CHIT, Shape.POINT);

  public static final Array<Tile> TWO_FOR_ONE_HARBORS =
      Array.of(GRAIN_HARBOR, LUMBER_HARBOR, WOOL_HARBOR, BRICK_HARBOR, ORE_HARBOR);

  private static Tile ofType(final Type type, final Tile.Shape shape) {
    return Tile.newBuilder().setType(type).setShape(shape).build();
  }

  private static Tile withSpecialVertices(final Type type, Vertex.Position... specialVertices) {
    return Tile.newBuilder().setType(type).addAllSpecialVertices(Array.of(specialVertices)).build();
  }
}
