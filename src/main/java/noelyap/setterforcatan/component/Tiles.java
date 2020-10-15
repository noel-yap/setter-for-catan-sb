package noelyap.setterforcatan.component;

import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Vertex.Position.BOTTOM;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Vertex.Position.TOP_LEFT;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Vertex.Position.TOP_RIGHT;

import io.vavr.collection.Array;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Vertex;
import noelyap.setterforcatan.protogen.TileOuterClass.Tile;

public class Tiles {
  // TODO: Move into proto definitions.
  public static final String DESERT_OR_LAKE_NAME = "«DESERT»|«LAKE»";
  public static final String FISHERY_NAME = "«FISHERY»";
  public static final String LAKE_NAME = "«LAKE»";

  public static final Tile GENERIC_HARBOR = Tiles.ofType(Tile.Type.GENERIC_HARBOR);
  public static final Tile BRICK_HARBOR = Tiles.ofType(Tile.Type.BRICK_HARBOR);
  public static final Tile GRAIN_HARBOR = Tiles.ofType(Tile.Type.GRAIN_HARBOR);
  public static final Tile LUMBER_HARBOR = Tiles.ofType(Tile.Type.LUMBER_HARBOR);
  public static final Tile ORE_HARBOR = Tiles.ofType(Tile.Type.ORE_HARBOR);
  public static final Tile WOOL_HARBOR = Tiles.ofType(Tile.Type.WOOL_HARBOR);

  public static final Tile DESERT = Tiles.ofType(Tile.Type.DESERT);
  public static final Tile FIELD = Tiles.ofType(Tile.Type.FIELD);
  public static final Tile FOREST = Tiles.ofType(Tile.Type.FOREST);
  public static final Tile HILL = Tiles.ofType(Tile.Type.HILL);
  public static final Tile MOUNTAIN = Tiles.ofType(Tile.Type.MOUNTAIN);
  public static final Tile PASTURE = Tiles.ofType(Tile.Type.PASTURE);

  public static final Tile SEA = Tiles.ofType(Tile.Type.SEA);
  public static final Tile LAKE = Tiles.ofType(Tile.Type.LAKE);
  public static final Tile FISHERY = Tiles.ofType(Tile.Type.FISHERY);
  public static final Tile RIVER = Tiles.ofType(Tile.Type.RIVER);

  public static final Tile GOLD_FIELD = Tiles.ofType(Tile.Type.GOLD_FIELD);
  public static final Tile SWAMP = Tiles.ofType(Tile.Type.SWAMP);
  public static final Tile OASIS =
      Tiles.withSpecialVertices(Tile.Type.OASIS, TOP_RIGHT, BOTTOM, TOP_LEFT);
  public static final Tile CASTLE = Tiles.ofType(Tile.Type.CASTLE);
  public static final Tile GLASSWORKS = Tiles.ofType(Tile.Type.GLASSWORKS);
  public static final Tile QUARRY = Tiles.ofType(Tile.Type.QUARRY);

  public static final Tile DEVELOPMENT_CARD = Tiles.ofType(Tile.Type.DEVELOPMENT_CARD);
  public static final Tile VICTORY_POINT = Tiles.ofType(Tile.Type.VICTORY_POINT);
  public static final Tile CHIT = Tiles.ofType(Tile.Type.CHIT);

  public static final Array<Tile> TWO_FOR_ONE_HARBORS =
      Array.of(GRAIN_HARBOR, LUMBER_HARBOR, WOOL_HARBOR, BRICK_HARBOR, ORE_HARBOR);

  private static Tile ofType(final Tile.Type type) {
    return Tile.newBuilder().setType(type).build();
  }

  private static Tile withSpecialVertices(
      final Tile.Type type, Vertex.Position... specialVertices) {
    return Tile.newBuilder().setType(type).addAllSpecialVertices(Array.of(specialVertices)).build();
  }
}
