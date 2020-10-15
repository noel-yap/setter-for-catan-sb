package noelyap.setterforcatan.scenario.seafarers;

import static noelyap.setterforcatan.component.Chits.CHITS_2_12;
import static noelyap.setterforcatan.component.Chits.CHIT_10;
import static noelyap.setterforcatan.component.Chits.CHIT_11;
import static noelyap.setterforcatan.component.Chits.CHIT_12;
import static noelyap.setterforcatan.component.Chits.CHIT_2;
import static noelyap.setterforcatan.component.Chits.CHIT_3;
import static noelyap.setterforcatan.component.Chits.CHIT_4;
import static noelyap.setterforcatan.component.Chits.CHIT_5;
import static noelyap.setterforcatan.component.Chits.CHIT_6;
import static noelyap.setterforcatan.component.Chits.CHIT_8;
import static noelyap.setterforcatan.component.Chits.CHIT_9;
import static noelyap.setterforcatan.component.Tiles.DESERT;
import static noelyap.setterforcatan.component.Tiles.FIELD;
import static noelyap.setterforcatan.component.Tiles.FOREST;
import static noelyap.setterforcatan.component.Tiles.GOLD_FIELD;
import static noelyap.setterforcatan.component.Tiles.HILL;
import static noelyap.setterforcatan.component.Tiles.MOUNTAIN;
import static noelyap.setterforcatan.component.Tiles.PASTURE;
import static noelyap.setterforcatan.component.Tiles.TWO_FOR_ONE_HARBORS;
import static noelyap.setterforcatan.protogen.MarkerOuterClass.Marker.Type.GREAT_BRIDGE_SETTLEMENT_REQUIREMENT;
import static noelyap.setterforcatan.protogen.MarkerOuterClass.Marker.Type.GREAT_WALL_SETTLEMENTS_REQUIREMENT;
import static noelyap.setterforcatan.protogen.MarkerOuterClass.Marker.Type.SETUP_SETTLEMENTS_PROHIBITION;

import com.google.common.annotations.VisibleForTesting;
import io.vavr.collection.Array;
import io.vavr.collection.HashMap;
import io.vavr.collection.HashSet;
import io.vavr.collection.Map;
import io.vavr.collection.Set;
import noelyap.setterforcatan.component.Coordinates;
import noelyap.setterforcatan.component.Markers;
import noelyap.setterforcatan.component.SpecificationImpl;
import noelyap.setterforcatan.matcher.CoordinateIsIn;
import noelyap.setterforcatan.matcher.HasOddsGreaterThan;
import noelyap.setterforcatan.protogen.ChitOuterClass.Chit;
import noelyap.setterforcatan.protogen.ConfigurationOuterClass.Configuration;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Coordinate;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Vertex;
import noelyap.setterforcatan.protogen.MarkerOuterClass.Marker;
import noelyap.setterforcatan.protogen.TileOuterClass.Tile;
import noelyap.setterforcatan.scenario.Base;
import noelyap.setterforcatan.util.TileMappingUtils;
import org.hamcrest.Matcher;
import org.hamcrest.core.AllOf;
import org.hamcrest.core.IsNot;

public class WondersOfCatan {
  private static final Matcher<Configuration> HAS_ODDS_GREATER_THAN_FOUR_CONFIGURATION_MATCHER =
      HasOddsGreaterThan.hasOddsGreaterThan(4);

  private static final Array<Tile> P3_P4_MAIN_ISLAND_PRODUCING_LAND_TILES =
      Array.fill(4, FIELD)
          .appendAll(Array.fill(4, FOREST))
          .appendAll(Array.fill(4, HILL))
          .appendAll(Array.fill(5, MOUNTAIN))
          .appendAll(Array.fill(5, PASTURE));
  private static final Array<Tile> P3_P4_MAIN_ISLAND_NON_PRODUCING_LAND_TILES =
      Array.fill(3, DESERT);
  private static final Array<Tile> P3_P4_MAIN_ISLAND_HARBOR_TILES = Base.P3_P4_HARBOR_TILES;
  private static final Array<Tile> P3_P4_SMALL_ISLAND_GOLD_TILES = Array.fill(2, GOLD_FIELD);
  private static final Array<Tile> P3_P4_SMALL_ISLAND_NON_GOLD_TILES =
      Array.of(FIELD, FOREST, HILL);
  private static final Map<String, Array<Tile>> P3_P4_TILES =
      HashMap.of(
          "main-island-producing-land",
          P3_P4_MAIN_ISLAND_PRODUCING_LAND_TILES,
          "main-island-non-producing-land",
          P3_P4_MAIN_ISLAND_NON_PRODUCING_LAND_TILES,
          "main-island-harbor",
          P3_P4_MAIN_ISLAND_HARBOR_TILES,
          "small-island-gold",
          P3_P4_SMALL_ISLAND_GOLD_TILES,
          "small-island-non-gold",
          P3_P4_SMALL_ISLAND_NON_GOLD_TILES);

  private static final Array<Coordinate> P3_P4_MAIN_ISLAND_PRODUCING_LAND_COORDINATES =
      Array.of(
          Coordinates.of(9, 1),
          Coordinates.of(8, 2),
          Coordinates.of(1, 3),
          Coordinates.of(3, 3),
          Coordinates.of(5, 3),
          Coordinates.of(7, 3),
          Coordinates.of(9, 3),
          Coordinates.of(11, 3),
          Coordinates.of(2, 4),
          Coordinates.of(4, 4),
          Coordinates.of(6, 4),
          Coordinates.of(8, 4),
          Coordinates.of(10, 4),
          Coordinates.of(1, 5),
          Coordinates.of(3, 5),
          Coordinates.of(9, 5),
          Coordinates.of(4, 6),
          Coordinates.of(8, 6),
          Coordinates.of(10, 6),
          Coordinates.of(3, 7),
          Coordinates.of(7, 7),
          Coordinates.of(9, 7));
  private static final Array<Coordinate> P3_P4_MAIN_ISLAND_NON_PRODUCING_LAND_COORDINATES =
      Array.of(Coordinates.of(12, 2), Coordinates.of(13, 3), Coordinates.of(12, 4));
  private static final Array<Coordinate> P3_P4_MAIN_ISLAND_HARBOR_COORDINATES =
      Array.of(
          Coordinates.onEdges(2, 2, Edge.Position.BOTTOM_RIGHT),
          Coordinates.onEdges(6, 2, Edge.Position.BOTTOM_RIGHT),
          Coordinates.onEdges(10, 2, Edge.Position.BOTTOM_LEFT),
          Coordinates.onEdges(0, 4, Edge.Position.RIGHT),
          Coordinates.onEdges(5, 5, Edge.Position.TOP_LEFT),
          Coordinates.onEdges(11, 5, Edge.Position.BOTTOM_LEFT),
          Coordinates.onEdges(2, 6, Edge.Position.RIGHT),
          Coordinates.onEdges(11, 7, Edge.Position.TOP_LEFT),
          Coordinates.onEdges(8, 8, Edge.Position.TOP_LEFT));
  private static final Array<Coordinate> P3_P4_MAIN_ISLAND_FISHERY_COORDINATES =
      Array.of(
          Coordinates.onEdges(9, 1, Edge.Position.TOP_LEFT, Edge.Position.TOP_RIGHT),
          Coordinates.onEdges(1, 3, Edge.Position.LEFT, Edge.Position.TOP_LEFT),
          Coordinates.onEdges(7, 5, Edge.Position.TOP_RIGHT, Edge.Position.RIGHT),
          Coordinates.onEdges(3, 7, Edge.Position.BOTTOM_LEFT, Edge.Position.LEFT));
  private static final Array<Coordinate> P3_P4_SMALL_ISLAND_COORDINATES =
      Array.of(
          Coordinates.of(3, 1),
          Coordinates.of(5, 1),
          Coordinates.of(15, 5),
          Coordinates.of(14, 6),
          Coordinates.of(13, 7));
  private static final Map<String, Array<Coordinate>> P3_P4_COORDINATES =
      HashMap.of(
          "main-island-producing-land",
          P3_P4_MAIN_ISLAND_PRODUCING_LAND_COORDINATES,
          "main-island-non-producing-land",
          P3_P4_MAIN_ISLAND_NON_PRODUCING_LAND_COORDINATES,
          "main-island-harbor",
          P3_P4_MAIN_ISLAND_HARBOR_COORDINATES,
          "small-island",
          P3_P4_SMALL_ISLAND_COORDINATES);

  private static final Array<Chit> P3_P4_MAIN_ISLAND_PRODUCING_LAND_CHITS =
      Array.of(CHIT_2, CHIT_12)
          .appendAll(Array.fill(3, CHIT_3))
          .appendAll(Array.fill(2, CHIT_4))
          .appendAll(Array.fill(2, CHIT_5))
          .appendAll(Array.fill(2, CHIT_6))
          .appendAll(Array.fill(2, CHIT_8))
          .appendAll(Array.fill(3, CHIT_9))
          .appendAll(Array.fill(3, CHIT_10))
          .appendAll(Array.fill(3, CHIT_11));
  private static final Array<Chit> P3_P4_SMALL_ISLAND_GOLD_CHITS = Array.of(CHIT_6, CHIT_8);
  private static final Array<Chit> P3_P4_SMALL_ISLAND_NON_GOLD_CHITS =
      Array.of(CHIT_2, CHIT_4, CHIT_5);
  private static final Map<String, Array<Chit>> P3_P4_CHITS =
      HashMap.of(
          "main-island-producing-land",
          P3_P4_MAIN_ISLAND_PRODUCING_LAND_CHITS,
          "small-island-gold",
          P3_P4_SMALL_ISLAND_GOLD_CHITS,
          "small-island-non-gold",
          P3_P4_SMALL_ISLAND_NON_GOLD_CHITS);

  private static final Set<Marker> P3_P4_MARKERS =
      HashSet.of(
          Markers.of(
              GREAT_BRIDGE_SETTLEMENT_REQUIREMENT,
              Array.of(
                  Coordinates.onVertices(5, 7, Vertex.Position.TOP, Vertex.Position.TOP_RIGHT))),
          Markers.of(
              GREAT_WALL_SETTLEMENTS_REQUIREMENT,
              Array.of(
                  Coordinates.onVertices(
                      11,
                      3,
                      Vertex.Position.TOP,
                      Vertex.Position.TOP_RIGHT,
                      Vertex.Position.BOTTOM_RIGHT),
                  Coordinates.onVertices(
                      10, 4, Vertex.Position.TOP_RIGHT, Vertex.Position.BOTTOM_RIGHT))),
          Markers.of(
              SETUP_SETTLEMENTS_PROHIBITION,
              Array.of(
                  Coordinates.onVertices(4, 6, Vertex.Position.TOP_RIGHT, Vertex.Position.BOTTOM),
                  Coordinates.onVertices(7, 7, Vertex.Position.BOTTOM_LEFT, Vertex.Position.TOP))));

  @VisibleForTesting
  static final Set<Coordinate> P3_P4_BOUNDED_ODDS_COORDINATES =
      P3_P4_MAIN_ISLAND_PRODUCING_LAND_COORDINATES
          .toSet()
          .intersect(
              Coordinates.adjacentOf(P3_P4_MAIN_ISLAND_NON_PRODUCING_LAND_COORDINATES.toSet()));

  private static final Matcher<Configuration> P3_P4_CONFIGURATION_MATCHER =
      IsNot.not(
          AllOf.allOf(
              CoordinateIsIn.coordinateIsIn(P3_P4_BOUNDED_ODDS_COORDINATES),
              HAS_ODDS_GREATER_THAN_FOUR_CONFIGURATION_MATCHER));

  private static final SpecificationImpl.Builder P3_P4_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
              P3_P4_TILES,
              P3_P4_COORDINATES,
              P3_P4_CHITS,
              HashMap.ofEntries(
                  TileMappingUtils.newSelfReferringEntry("main-island-producing-land"),
                  TileMappingUtils.newSelfReferringEntry("main-island-non-producing-land"),
                  TileMappingUtils.newSelfReferringEntry("main-island-harbor"),
                  TileMappingUtils.newEntry(
                      "small-island", "small-island-gold", "small-island-non-gold")),
              HashMap.ofEntries(
                  TileMappingUtils.newSelfReferringEntry("main-island-producing-land"),
                  TileMappingUtils.newSelfReferringEntry("small-island-gold"),
                  TileMappingUtils.newSelfReferringEntry("small-island-non-gold")),
              P3_P4_MARKERS)
          .withConfigurationMatcher(P3_P4_CONFIGURATION_MATCHER);

  public static final SpecificationImpl P3_P4_SPECIFICATION_IMPL =
      P3_P4_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P3_P4_FISHERMEN_SPECIFICATION_IMPL =
      P3_P4_SPECIFICATION_BUILDER.withFisheries(P3_P4_MAIN_ISLAND_FISHERY_COORDINATES).build();

  private static final Array<Tile> P5_P6_MAIN_ISLAND_PRODUCING_LAND_TILES =
      Array.fill(6, FIELD)
          .appendAll(Array.fill(6, FOREST))
          .appendAll(Array.fill(6, HILL))
          .appendAll(Array.fill(6, MOUNTAIN))
          .appendAll(Array.fill(7, PASTURE));
  private static final Array<Tile> P5_P6_MAIN_ISLAND_NON_PRODUCING_LAND_TILES =
      Array.fill(4, DESERT);
  private static final Array<Tile> P5_P6_MAIN_ISLAND_HARBOR_TILES = Base.P5_P6_HARBOR_TILES;
  private static final Array<Tile> P5_P6_SMALL_ISLAND_GOLD_TILES = Array.fill(3, GOLD_FIELD);
  private static final Array<Tile> P5_P6_SMALL_ISLAND_NON_GOLD_TILES = Array.of(FOREST);
  private static final Map<String, Array<Tile>> P5_P6_TILES =
      HashMap.of(
          "main-island-producing-land",
          P5_P6_MAIN_ISLAND_PRODUCING_LAND_TILES,
          "main-island-non-producing-land",
          P5_P6_MAIN_ISLAND_NON_PRODUCING_LAND_TILES,
          "main-island-harbor",
          P5_P6_MAIN_ISLAND_HARBOR_TILES,
          "small-island-gold",
          P5_P6_SMALL_ISLAND_GOLD_TILES,
          "small-island-non-gold",
          P5_P6_SMALL_ISLAND_NON_GOLD_TILES);

  private static final Array<Coordinate> P5_P6_MAIN_ISLAND_PRODUCING_LAND_COORDINATES =
      Array.of(
          Coordinates.of(7, 1),
          Coordinates.of(11, 1),
          Coordinates.of(6, 2),
          Coordinates.of(10, 2),
          Coordinates.of(3, 3),
          Coordinates.of(5, 3),
          Coordinates.of(7, 3),
          Coordinates.of(9, 3),
          Coordinates.of(11, 3),
          Coordinates.of(13, 3),
          Coordinates.of(15, 3),
          Coordinates.of(4, 4),
          Coordinates.of(6, 4),
          Coordinates.of(8, 4),
          Coordinates.of(10, 4),
          Coordinates.of(12, 4),
          Coordinates.of(14, 4),
          Coordinates.of(16, 4),
          Coordinates.of(5, 5),
          Coordinates.of(7, 5),
          Coordinates.of(11, 5),
          Coordinates.of(13, 5),
          Coordinates.of(15, 5),
          Coordinates.of(6, 6),
          Coordinates.of(8, 6),
          Coordinates.of(12, 6),
          Coordinates.of(14, 6),
          Coordinates.of(5, 7),
          Coordinates.of(7, 7),
          Coordinates.of(11, 7),
          Coordinates.of(13, 7));
  private static final Array<Coordinate> P5_P6_MAIN_ISLAND_NON_PRODUCING_LAND_COORDINATES =
      Array.of(
          Coordinates.of(16, 2),
          Coordinates.of(18, 2),
          Coordinates.of(17, 3),
          Coordinates.of(18, 4));
  private static final Array<Coordinate> P5_P6_MAIN_ISLAND_HARBOR_COORDINATES =
      Array.of(
          Coordinates.onEdges(4, 2, Edge.Position.BOTTOM_RIGHT),
          Coordinates.onEdges(8, 2, Edge.Position.BOTTOM_LEFT),
          Coordinates.onEdges(12, 2, Edge.Position.BOTTOM_LEFT),
          Coordinates.onEdges(1, 3, Edge.Position.RIGHT),
          Coordinates.onEdges(3, 5, Edge.Position.RIGHT),
          Coordinates.onEdges(9, 5, Edge.Position.TOP_LEFT),
          Coordinates.onEdges(4, 6, Edge.Position.RIGHT),
          Coordinates.onEdges(16, 6, Edge.Position.TOP_LEFT),
          Coordinates.onEdges(15, 7, Edge.Position.TOP_LEFT),
          Coordinates.onEdges(6, 8, Edge.Position.TOP_LEFT),
          Coordinates.onEdges(12, 8, Edge.Position.TOP_LEFT));
  private static final Array<Coordinate> P5_P6_MAIN_ISLAND_FISHERY_COORDINATES =
      Array.of(
          Coordinates.onEdges(7, 1, Edge.Position.TOP_LEFT, Edge.Position.TOP_RIGHT),
          Coordinates.onEdges(11, 1, Edge.Position.TOP_RIGHT, Edge.Position.RIGHT),
          Coordinates.onEdges(2, 4, Edge.Position.TOP_RIGHT, Edge.Position.RIGHT),
          Coordinates.onEdges(11, 5, Edge.Position.BOTTOM_LEFT, Edge.Position.LEFT),
          Coordinates.onEdges(7, 7, Edge.Position.BOTTOM_RIGHT, Edge.Position.BOTTOM_LEFT),
          Coordinates.onEdges(13, 7, Edge.Position.BOTTOM_RIGHT, Edge.Position.BOTTOM_LEFT));
  private static final Array<Coordinate> P5_P6_SMALL_ISLAND_COORDINATES =
      Array.of(
          Coordinates.of(3, 1), Coordinates.of(2, 6), Coordinates.of(18, 6), Coordinates.of(17, 7));
  private static final Map<String, Array<Coordinate>> P5_P6_COORDINATES =
      HashMap.of(
          "main-island-producing-land",
          P5_P6_MAIN_ISLAND_PRODUCING_LAND_COORDINATES,
          "main-island-non-producing-land",
          P5_P6_MAIN_ISLAND_NON_PRODUCING_LAND_COORDINATES,
          "main-island-harbor",
          P5_P6_MAIN_ISLAND_HARBOR_COORDINATES,
          "small-island",
          P5_P6_SMALL_ISLAND_COORDINATES);

  private static final Array<Chit> P5_P6_MAIN_ISLAND_PRODUCING_LAND_CHITS =
      Array.fill(2, CHIT_2)
          .appendAll(Array.fill(3, CHIT_3))
          .appendAll(Array.fill(3, CHIT_4))
          .appendAll(Array.fill(4, CHIT_5))
          .appendAll(Array.fill(2, CHIT_6))
          .appendAll(Array.fill(3, CHIT_8))
          .appendAll(Array.fill(4, CHIT_9))
          .appendAll(Array.fill(4, CHIT_10))
          .appendAll(Array.fill(4, CHIT_11))
          .appendAll(Array.fill(2, CHIT_12));
  private static final Array<Chit> P5_P6_SMALL_ISLAND_GOLD_CHITS =
      Array.fill(2, CHIT_6).append(CHIT_8);
  private static final Array<Chit> P5_P6_SMALL_ISLAND_NON_GOLD_CHITS = Array.of(CHIT_4);
  private static final Map<String, Array<Chit>> P5_P6_CHITS =
      HashMap.of(
          "main-island-producing-land",
          P5_P6_MAIN_ISLAND_PRODUCING_LAND_CHITS,
          "small-island-gold",
          P5_P6_SMALL_ISLAND_GOLD_CHITS,
          "small-island-non-gold",
          P5_P6_SMALL_ISLAND_NON_GOLD_CHITS);

  private static final Set<Marker> P5_P6_MARKERS =
      HashSet.of(
          Markers.of(
              GREAT_BRIDGE_SETTLEMENT_REQUIREMENT,
              Array.of(
                  Coordinates.onVertices(8, 2, Vertex.Position.TOP, Vertex.Position.TOP_RIGHT),
                  Coordinates.onVertices(
                      10, 6, Vertex.Position.BOTTOM, Vertex.Position.BOTTOM_LEFT))),
          Markers.of(
              GREAT_WALL_SETTLEMENTS_REQUIREMENT,
              Array.of(
                  Coordinates.onVertices(
                      15,
                      3,
                      Vertex.Position.TOP,
                      Vertex.Position.TOP_RIGHT,
                      Vertex.Position.BOTTOM_RIGHT),
                  Coordinates.onVertices(
                      16, 4, Vertex.Position.TOP_RIGHT, Vertex.Position.BOTTOM_RIGHT))),
          Markers.of(
              SETUP_SETTLEMENTS_PROHIBITION,
              Array.of(
                  Coordinates.onVertices(7, 1, Vertex.Position.TOP_RIGHT, Vertex.Position.BOTTOM),
                  Coordinates.onVertices(10, 2, Vertex.Position.BOTTOM_LEFT, Vertex.Position.TOP),
                  Coordinates.onVertices(8, 6, Vertex.Position.TOP_RIGHT, Vertex.Position.BOTTOM),
                  Coordinates.onVertices(
                      11, 7, Vertex.Position.BOTTOM_LEFT, Vertex.Position.TOP))));

  @VisibleForTesting
  static final Set<Coordinate> P5_P6_BOUNDED_ODDS_COORDINATES =
      P5_P6_MAIN_ISLAND_PRODUCING_LAND_COORDINATES
          .toSet()
          .intersect(
              Coordinates.adjacentOf(P5_P6_MAIN_ISLAND_NON_PRODUCING_LAND_COORDINATES.toSet()));

  private static final Matcher<Configuration> P5_P6_CONFIGURATION_MATCHER =
      IsNot.not(
          AllOf.allOf(
              CoordinateIsIn.coordinateIsIn(P5_P6_BOUNDED_ODDS_COORDINATES),
              HAS_ODDS_GREATER_THAN_FOUR_CONFIGURATION_MATCHER));

  private static final SpecificationImpl.Builder P5_P6_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
              P5_P6_TILES,
              P5_P6_COORDINATES,
              P5_P6_CHITS,
              HashMap.ofEntries(
                  TileMappingUtils.newSelfReferringEntry("main-island-producing-land"),
                  TileMappingUtils.newSelfReferringEntry("main-island-non-producing-land"),
                  TileMappingUtils.newSelfReferringEntry("main-island-harbor"),
                  TileMappingUtils.newEntry(
                      "small-island", "small-island-gold", "small-island-non-gold")),
              HashMap.ofEntries(
                  TileMappingUtils.newSelfReferringEntry("main-island-producing-land"),
                  TileMappingUtils.newSelfReferringEntry("small-island-gold"),
                  TileMappingUtils.newSelfReferringEntry("small-island-non-gold")),
              P5_P6_MARKERS)
          .withConfigurationMatcher(P5_P6_CONFIGURATION_MATCHER);

  public static final SpecificationImpl P5_P6_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P5_P6_FISHERMEN_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.withFisheries(P5_P6_MAIN_ISLAND_FISHERY_COORDINATES).build();

  private static final Array<Tile> P7_P8_MAIN_ISLAND_PRODUCING_LAND_TILES =
      Array.fill(7, FIELD)
          .appendAll(Array.fill(7, HILL))
          .appendAll(Array.fill(7, MOUNTAIN))
          .appendAll(Array.fill(8, FOREST))
          .appendAll(Array.fill(8, PASTURE));
  private static final Array<Tile> P7_P8_MAIN_ISLAND_NON_PRODUCING_LAND_TILES =
      Array.fill(6, DESERT);
  private static final Array<Tile> P7_P8_MAIN_ISLAND_HARBOR_TILES =
      Base.P3_P4_HARBOR_TILES.appendAll(TWO_FOR_ONE_HARBORS);
  private static final Array<Tile> P7_P8_SMALL_ISLAND_TILES = Array.fill(4, GOLD_FIELD);
  private static final Map<String, Array<Tile>> P7_P8_TILES =
      HashMap.of(
          "main-island-producing-land",
          P7_P8_MAIN_ISLAND_PRODUCING_LAND_TILES,
          "main-island-non-producing-land",
          P7_P8_MAIN_ISLAND_NON_PRODUCING_LAND_TILES,
          "main-island-harbor",
          P7_P8_MAIN_ISLAND_HARBOR_TILES,
          "small-island",
          P7_P8_SMALL_ISLAND_TILES);

  private static final Array<Coordinate> P7_P8_MAIN_ISLAND_PRODUCING_LAND_COORDINATES =
      Array.of(
          Coordinates.of(7, 1),
          Coordinates.of(9, 1),
          Coordinates.of(13, 1),
          Coordinates.of(6, 2),
          Coordinates.of(8, 2),
          Coordinates.of(12, 2),
          Coordinates.of(14, 2),
          Coordinates.of(5, 3),
          Coordinates.of(7, 3),
          Coordinates.of(9, 3),
          Coordinates.of(11, 3),
          Coordinates.of(13, 3),
          Coordinates.of(15, 3),
          Coordinates.of(17, 3),
          Coordinates.of(2, 4),
          Coordinates.of(4, 4),
          Coordinates.of(6, 4),
          Coordinates.of(8, 4),
          Coordinates.of(10, 4),
          Coordinates.of(12, 4),
          Coordinates.of(14, 4),
          Coordinates.of(16, 4),
          Coordinates.of(18, 4),
          Coordinates.of(3, 5),
          Coordinates.of(5, 5),
          Coordinates.of(7, 5),
          Coordinates.of(9, 5),
          Coordinates.of(11, 5),
          Coordinates.of(13, 5),
          Coordinates.of(15, 5),
          Coordinates.of(6, 6),
          Coordinates.of(8, 6),
          Coordinates.of(12, 6),
          Coordinates.of(14, 6),
          Coordinates.of(7, 7),
          Coordinates.of(11, 7),
          Coordinates.of(13, 7));
  private static final Array<Coordinate> P7_P8_MAIN_ISLAND_NON_PRODUCING_LAND_COORDINATES =
      Array.of(
          Coordinates.of(1, 3),
          Coordinates.of(19, 3),
          Coordinates.of(0, 4),
          Coordinates.of(20, 4),
          Coordinates.of(1, 5),
          Coordinates.of(19, 5));
  private static final Array<Coordinate> P7_P8_MAIN_ISLAND_HARBOR_COORDINATES =
      Array.of(
          Coordinates.onEdges(8, 0, Edge.Position.BOTTOM_RIGHT),
          Coordinates.onEdges(12, 0, Edge.Position.BOTTOM_RIGHT),
          Coordinates.onEdges(5, 1, Edge.Position.BOTTOM_RIGHT),
          Coordinates.onEdges(15, 1, Edge.Position.BOTTOM_LEFT),
          Coordinates.onEdges(4, 2, Edge.Position.BOTTOM_RIGHT),
          Coordinates.onEdges(10, 2, Edge.Position.BOTTOM_LEFT),
          Coordinates.onEdges(16, 2, Edge.Position.BOTTOM_LEFT),
          Coordinates.onEdges(4, 6, Edge.Position.TOP_RIGHT),
          Coordinates.onEdges(10, 6, Edge.Position.TOP_RIGHT),
          Coordinates.onEdges(16, 6, Edge.Position.TOP_LEFT),
          Coordinates.onEdges(5, 7, Edge.Position.TOP_RIGHT),
          Coordinates.onEdges(15, 7, Edge.Position.TOP_LEFT),
          Coordinates.onEdges(8, 8, Edge.Position.TOP_LEFT),
          Coordinates.onEdges(12, 8, Edge.Position.TOP_LEFT));
  private static final Array<Coordinate> P7_P8_MAIN_ISLAND_FISHERY_COORDINATES =
      Array.of(
          Coordinates.onEdges(7, 1, Edge.Position.LEFT, Edge.Position.TOP_LEFT),
          Coordinates.onEdges(13, 1, Edge.Position.TOP_RIGHT, Edge.Position.RIGHT),
          Coordinates.onEdges(7, 7, Edge.Position.BOTTOM_LEFT, Edge.Position.LEFT),
          Coordinates.onEdges(13, 7, Edge.Position.RIGHT, Edge.Position.BOTTOM_RIGHT));
  private static final Array<Coordinate> P7_P8_SMALL_ISLAND_COORDINATES =
      Array.of(
          Coordinates.of(3, 1), Coordinates.of(17, 1), Coordinates.of(3, 7), Coordinates.of(17, 7));
  private static final Map<String, Array<Coordinate>> P7_P8_COORDINATES =
      HashMap.of(
          "main-island-producing-land",
          P7_P8_MAIN_ISLAND_PRODUCING_LAND_COORDINATES,
          "main-island-non-producing-land",
          P7_P8_MAIN_ISLAND_NON_PRODUCING_LAND_COORDINATES,
          "main-island-harbor",
          P7_P8_MAIN_ISLAND_HARBOR_COORDINATES,
          "small-island",
          P7_P8_SMALL_ISLAND_COORDINATES);

  private static final Array<Chit> P7_P8_MAIN_ISLAND_PRODUCING_LAND_CHITS =
      Array.of(CHITS_2_12)
          .appendAll(Array.fill(3, CHIT_2))
          .appendAll(Array.fill(4, CHIT_3))
          .appendAll(Array.fill(4, CHIT_4))
          .appendAll(Array.fill(4, CHIT_5))
          .appendAll(Array.fill(3, CHIT_6))
          .appendAll(Array.fill(3, CHIT_8))
          .appendAll(Array.fill(4, CHIT_9))
          .appendAll(Array.fill(4, CHIT_10))
          .appendAll(Array.fill(4, CHIT_11))
          .appendAll(Array.fill(3, CHIT_12));
  private static final Array<Chit> P7_P8_SMALL_ISLAND_CHITS =
      Array.fill(2, CHIT_6).appendAll(Array.fill(2, CHIT_8));
  private static final Map<String, Array<Chit>> P7_P8_CHITS =
      HashMap.of(
          "main-island-producing-land",
          P7_P8_MAIN_ISLAND_PRODUCING_LAND_CHITS,
          "small-island",
          P7_P8_SMALL_ISLAND_CHITS);

  private static final Set<Marker> P7_P8_MARKERS =
      HashSet.of(
          Markers.of(
              GREAT_BRIDGE_SETTLEMENT_REQUIREMENT,
              Array.of(
                  Coordinates.onVertices(10, 2, Vertex.Position.TOP, Vertex.Position.TOP_RIGHT),
                  Coordinates.onVertices(
                      10, 6, Vertex.Position.BOTTOM, Vertex.Position.BOTTOM_LEFT))),
          Markers.of(
              GREAT_WALL_SETTLEMENTS_REQUIREMENT,
              Array.of(
                  Coordinates.onVertices(
                      17, 3, Vertex.Position.TOP_RIGHT, Vertex.Position.BOTTOM_RIGHT),
                  Coordinates.onVertices(
                      2,
                      4,
                      Vertex.Position.BOTTOM_LEFT,
                      Vertex.Position.TOP_LEFT,
                      Vertex.Position.TOP),
                  Coordinates.onVertices(
                      18,
                      4,
                      Vertex.Position.TOP_RIGHT,
                      Vertex.Position.BOTTOM_RIGHT,
                      Vertex.Position.BOTTOM),
                  Coordinates.onVertices(
                      3, 5, Vertex.Position.BOTTOM_LEFT, Vertex.Position.TOP_LEFT))),
          Markers.of(
              SETUP_SETTLEMENTS_PROHIBITION,
              Array.of(
                  Coordinates.onVertices(9, 1, Vertex.Position.TOP_RIGHT, Vertex.Position.BOTTOM),
                  Coordinates.onVertices(12, 2, Vertex.Position.BOTTOM_LEFT, Vertex.Position.TOP),
                  Coordinates.onVertices(8, 6, Vertex.Position.TOP_RIGHT, Vertex.Position.BOTTOM),
                  Coordinates.onVertices(
                      11, 7, Vertex.Position.BOTTOM_LEFT, Vertex.Position.TOP))));

  @VisibleForTesting
  static final Set<Coordinate> P7_P8_BOUNDED_ODDS_COORDINATES =
      P7_P8_MAIN_ISLAND_PRODUCING_LAND_COORDINATES
          .toSet()
          .intersect(
              Coordinates.adjacentOf(P7_P8_MAIN_ISLAND_NON_PRODUCING_LAND_COORDINATES.toSet()));

  private static final Matcher<Configuration> P7_P8_CONFIGURATION_MATCHER =
      IsNot.not(
          AllOf.allOf(
              CoordinateIsIn.coordinateIsIn(P7_P8_BOUNDED_ODDS_COORDINATES),
              HAS_ODDS_GREATER_THAN_FOUR_CONFIGURATION_MATCHER));

  private static final SpecificationImpl.Builder P7_P8_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
              P7_P8_TILES,
              P7_P8_COORDINATES,
              P7_P8_CHITS,
              HashMap.ofEntries(
                  TileMappingUtils.newSelfReferringEntry("main-island-producing-land"),
                  TileMappingUtils.newSelfReferringEntry("main-island-non-producing-land"),
                  TileMappingUtils.newSelfReferringEntry("main-island-harbor"),
                  TileMappingUtils.newSelfReferringEntry("small-island")),
              HashMap.ofEntries(
                  TileMappingUtils.newSelfReferringEntry("main-island-producing-land"),
                  TileMappingUtils.newSelfReferringEntry("small-island")),
              P7_P8_MARKERS)
          .withConfigurationMatcher(P7_P8_CONFIGURATION_MATCHER);

  public static final SpecificationImpl P7_P8_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P7_P8_FISHERMEN_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.withFisheries(P7_P8_MAIN_ISLAND_FISHERY_COORDINATES).build();
}
