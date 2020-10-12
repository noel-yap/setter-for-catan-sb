package noelyap.setterforcatan.scenario.seafarers;

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
import static noelyap.setterforcatan.component.Tiles.GENERIC_HARBOR;
import static noelyap.setterforcatan.component.Tiles.GOLD_FIELD;
import static noelyap.setterforcatan.component.Tiles.HILL;
import static noelyap.setterforcatan.component.Tiles.MOUNTAIN;
import static noelyap.setterforcatan.component.Tiles.PASTURE;
import static noelyap.setterforcatan.component.Tiles.SEA;
import static noelyap.setterforcatan.component.Tiles.TWO_FOR_ONE_HARBORS;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge.Position.BOTTOM_LEFT;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge.Position.BOTTOM_RIGHT;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge.Position.LEFT;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge.Position.RIGHT;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge.Position.TOP_LEFT;
import static noelyap.setterforcatan.protogen.CoordinateOuterClass.Edge.Position.TOP_RIGHT;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.Array;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import noelyap.setterforcatan.component.Coordinates;
import noelyap.setterforcatan.component.SpecificationImpl;
import noelyap.setterforcatan.matcher.HasOddsGreaterThan;
import noelyap.setterforcatan.protogen.ChitOuterClass;
import noelyap.setterforcatan.protogen.ConfigurationOuterClass.Configuration;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Coordinate;
import noelyap.setterforcatan.protogen.TileOuterClass.Tile;
import noelyap.setterforcatan.scenario.Base;
import noelyap.setterforcatan.util.TileMappingUtils;
import org.hamcrest.Matcher;
import org.hamcrest.core.AllOf;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;

public class ThroughTheDesert {
  private static final Matcher<Configuration> CONFIGURATION_MATCHER =
      IsNot.not(
          AllOf.<Configuration>allOf(Is.is(GOLD_FIELD), HasOddsGreaterThan.hasOddsGreaterThan(4)));

  private static final Tuple2<Array<Tile>, Boolean> P3_INDIGENOUS_LAND_TILES =
      Tuple.of(
          Array.fill(2, FIELD)
              .appendAll(Array.fill(2, MOUNTAIN))
              .appendAll(Array.fill(3, HILL))
              .appendAll(Array.fill(3, PASTURE))
              .appendAll(Array.fill(4, FOREST)),
          false);
  private static final Tuple2<Array<Tile>, Boolean> P3_INDIGENOUS_HARBOR_TILES =
      Tuple.of(Array.fill(3, GENERIC_HARBOR).appendAll(TWO_FOR_ONE_HARBORS), true);
  private static final Tuple2<Array<Tile>, Boolean> P3_DESERT_TILES =
      Tuple.of(Array.fill(3, DESERT), true);
  private static final Tuple2<Array<Tile>, Boolean> P3_FOREIGN_LAND_TILES =
      Tuple.of(
          Array.of(FOREST, PASTURE)
              .appendAll(Array.fill(2, FIELD))
              .appendAll(Array.fill(2, GOLD_FIELD))
              .appendAll(Array.fill(2, MOUNTAIN)),
          false);
  private static final Tuple2<Array<Tile>, Boolean> P3_FOREIGN_SEA_TILES =
      Tuple.of(Array.fill(2, SEA), true);
  private static final Map<String, Tuple2<Array<Tile>, Boolean>> P3_TILES =
      HashMap.of(
          "indigenous-land",
          P3_INDIGENOUS_LAND_TILES,
          "indigenous-harbor",
          P3_INDIGENOUS_HARBOR_TILES,
          "desert",
          P3_DESERT_TILES,
          "foreign-land",
          P3_FOREIGN_LAND_TILES,
          "foreign-sea",
          P3_FOREIGN_SEA_TILES);

  private static final Array<Coordinate> P3_INDIGENOUS_LAND_COORDINATES =
      Array.of(
          Coordinates.of(2, 2),
          Coordinates.of(4, 2),
          Coordinates.of(6, 2),
          Coordinates.of(1, 3),
          Coordinates.of(3, 3),
          Coordinates.of(5, 3),
          Coordinates.of(7, 3),
          Coordinates.of(9, 3),
          Coordinates.of(2, 4),
          Coordinates.of(4, 4),
          Coordinates.of(6, 4),
          Coordinates.of(8, 4),
          Coordinates.of(10, 4),
          Coordinates.of(3, 5));
  private static final Array<Coordinate> P3_INDIGENOUS_HARBOR_COORDINATES =
      Array.of(
          Coordinates.of(3, 1, BOTTOM_LEFT),
          Coordinates.of(5, 1, BOTTOM_RIGHT),
          Coordinates.of(0, 2, BOTTOM_RIGHT),
          Coordinates.of(8, 2, LEFT),
          Coordinates.of(0, 4, TOP_RIGHT),
          Coordinates.of(12, 4, LEFT),
          Coordinates.of(1, 5, RIGHT),
          Coordinates.of(5, 5, TOP_LEFT));
  private static final Array<Coordinate> P3_INDIGENOUS_FISHERY_COORDINATES =
      Array.of(Coordinates.of(2, 2, LEFT, TOP_LEFT), Coordinates.of(2, 4, BOTTOM_LEFT, LEFT));
  private static final Array<Coordinate> P3_DESERT_COORDINATES =
      Array.of(Coordinates.of(7, 5), Coordinates.of(9, 5), Coordinates.of(11, 5));
  private static final Array<Coordinate> P3_FOREIGN_COORDINATES =
      Array.of(
          Coordinates.of(4, 0),
          Coordinates.of(6, 0),
          Coordinates.of(8, 0),
          Coordinates.of(9, 1),
          Coordinates.of(11, 1),
          Coordinates.of(12, 2),
          Coordinates.of(13, 3),
          Coordinates.of(6, 6),
          Coordinates.of(8, 6),
          Coordinates.of(10, 6));
  private static final Map<String, Array<Coordinate>> P3_COORDINATES =
      HashMap.of(
          "indigenous-land",
          P3_INDIGENOUS_LAND_COORDINATES,
          "indigenous-harbor",
          P3_INDIGENOUS_HARBOR_COORDINATES,
          "desert",
          P3_DESERT_COORDINATES,
          "foreign",
          P3_FOREIGN_COORDINATES);

  private static final Array<ChitOuterClass.Chit> P3_INDIGENOUS_LAND_CHITS =
      Array.of(CHIT_2, CHIT_3, CHIT_5, CHIT_11)
          .appendAll(Array.fill(2, CHIT_4))
          .appendAll(Array.fill(2, CHIT_6))
          .appendAll(Array.fill(2, CHIT_8))
          .appendAll(Array.fill(2, CHIT_9))
          .appendAll(Array.fill(2, CHIT_10));
  private static final Array<ChitOuterClass.Chit> P3_FOREIGN_LAND_CHITS =
      Array.of(CHIT_3, CHIT_4, CHIT_6, CHIT_8, CHIT_9, CHIT_11).appendAll(Array.fill(2, CHIT_5));
  private static final Map<String, Array<ChitOuterClass.Chit>> P3_CHITS =
      HashMap.of(
          "indigenous-land", P3_INDIGENOUS_LAND_CHITS,
          "foreign-land", P3_FOREIGN_LAND_CHITS);

  private static final SpecificationImpl.Builder P3_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
              P3_TILES,
              P3_COORDINATES,
              P3_CHITS,
              HashMap.ofEntries(
                  TileMappingUtils.newSelfReferringEntry("indigenous-land"),
                  TileMappingUtils.newSelfReferringEntry("indigenous-harbor"),
                  TileMappingUtils.newSelfReferringEntry("desert"),
                  TileMappingUtils.newEntry("foreign", "foreign-land", "foreign-sea")),
              HashMap.ofEntries(
                  TileMappingUtils.newSelfReferringEntry("indigenous-land"),
                  TileMappingUtils.newSelfReferringEntry("foreign-land")))
          .withConfigurationMatcher(CONFIGURATION_MATCHER);

  public static final SpecificationImpl P3_SPECIFICATION_IMPL = P3_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P3_FISHERMEN_SPECIFICATION_IMPL =
      P3_SPECIFICATION_BUILDER.withFisheries(P3_INDIGENOUS_FISHERY_COORDINATES).build();

  private static final Tuple2<Array<Tile>, Boolean> P4_INDIGENOUS_LAND_TILES =
      Tuple.of(
          Array.fill(2, FIELD)
              .appendAll(Array.fill(2, MOUNTAIN))
              .appendAll(Array.fill(4, HILL))
              .appendAll(Array.fill(4, PASTURE))
              .appendAll(Array.fill(5, FOREST)),
          false);
  private static final Tuple2<Array<Tile>, Boolean> P4_INDIGENOUS_HARBOR_TILES =
      Base.P3_P4_HARBOR_TILES;
  private static final Tuple2<Array<Tile>, Boolean> P4_DESERT_TILES = P3_DESERT_TILES;
  private static final Tuple2<Array<Tile>, Boolean> P4_FOREIGN_LAND_TILES =
      Tuple.of(
          Array.of(HILL, PASTURE)
              .appendAll(Array.fill(2, GOLD_FIELD))
              .appendAll(Array.fill(3, FIELD))
              .appendAll(Array.fill(3, MOUNTAIN)),
          false);
  private static final Tuple2<Array<Tile>, Boolean> P4_FOREIGN_SEA_TILES = P3_FOREIGN_SEA_TILES;
  private static final Map<String, Tuple2<Array<Tile>, Boolean>> P4_TILES =
      HashMap.of(
          "indigenous-land",
          P4_INDIGENOUS_LAND_TILES,
          "indigenous-harbor",
          P4_INDIGENOUS_HARBOR_TILES,
          "desert",
          P4_DESERT_TILES,
          "foreign-land",
          P4_FOREIGN_LAND_TILES,
          "foreign-sea",
          P4_FOREIGN_SEA_TILES);

  private static final Array<Coordinate> P4_INDIGENOUS_LAND_COORDINATES =
      Array.of(
          Coordinates.of(3, 3),
          Coordinates.of(5, 3),
          Coordinates.of(7, 3),
          Coordinates.of(9, 3),
          Coordinates.of(11, 3),
          Coordinates.of(4, 4),
          Coordinates.of(6, 4),
          Coordinates.of(8, 4),
          Coordinates.of(10, 4),
          Coordinates.of(12, 4),
          Coordinates.of(3, 5),
          Coordinates.of(5, 5),
          Coordinates.of(7, 5),
          Coordinates.of(9, 5),
          Coordinates.of(11, 5),
          Coordinates.of(13, 5),
          Coordinates.of(4, 6));
  private static final Array<Coordinate> P4_INDIGENOUS_HARBOR_COORDINATES =
      Array.of(
          Coordinates.of(4, 2, BOTTOM_LEFT),
          Coordinates.of(6, 2, BOTTOM_RIGHT),
          Coordinates.of(10, 2, BOTTOM_RIGHT),
          Coordinates.of(1, 3, RIGHT),
          Coordinates.of(13, 3, LEFT),
          Coordinates.of(2, 4, BOTTOM_RIGHT),
          Coordinates.of(14, 4, BOTTOM_LEFT),
          Coordinates.of(2, 6, TOP_RIGHT),
          Coordinates.of(6, 6, TOP_RIGHT));
  private static final Array<Coordinate> P4_INDIGENOUS_FISHERY_COORDINATES =
      Array.of(Coordinates.of(13, 5, RIGHT, BOTTOM_RIGHT), Coordinates.of(4, 6, BOTTOM_LEFT, LEFT));
  private static final Array<Coordinate> P4_DESERT_COORDINATES =
      Array.of(Coordinates.of(8, 6), Coordinates.of(10, 6), Coordinates.of(12, 6));
  private static final Array<Coordinate> P4_FOREIGN_COORDINATES =
      Array.of(
          Coordinates.of(6, 0),
          Coordinates.of(8, 0),
          Coordinates.of(10, 0),
          Coordinates.of(5, 1),
          Coordinates.of(9, 1),
          Coordinates.of(11, 1),
          Coordinates.of(13, 1),
          Coordinates.of(14, 2),
          Coordinates.of(15, 3),
          Coordinates.of(7, 7),
          Coordinates.of(9, 7),
          Coordinates.of(11, 7));
  private static final Map<String, Array<Coordinate>> P4_COORDINATES =
      HashMap.of(
          "indigenous-land",
          P4_INDIGENOUS_LAND_COORDINATES,
          "indigenous-harbor",
          P4_INDIGENOUS_HARBOR_COORDINATES,
          "desert",
          P4_DESERT_COORDINATES,
          "foreign",
          P4_FOREIGN_COORDINATES);

  private static final Array<ChitOuterClass.Chit> P4_INDIGENOUS_LAND_CHITS =
      Array.of(CHIT_12)
          .appendAll(Array.fill(2, CHIT_3))
          .appendAll(Array.fill(2, CHIT_4))
          .appendAll(Array.fill(2, CHIT_5))
          .appendAll(Array.fill(2, CHIT_6))
          .appendAll(Array.fill(2, CHIT_8))
          .appendAll(Array.fill(2, CHIT_9))
          .appendAll(Array.fill(2, CHIT_10))
          .appendAll(Array.fill(2, CHIT_11));
  private static final Array<ChitOuterClass.Chit> P4_FOREIGN_LAND_CHITS =
      Array.of(CHIT_2, CHIT_3, CHIT_4, CHIT_5, CHIT_6, CHIT_8, CHIT_9, CHIT_10, CHIT_11, CHIT_12);
  private static final Map<String, Array<ChitOuterClass.Chit>> P4_CHITS =
      HashMap.of(
          "indigenous-land", P4_INDIGENOUS_LAND_CHITS,
          "foreign-land", P4_FOREIGN_LAND_CHITS);

  private static final SpecificationImpl.Builder P4_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
              P4_TILES,
              P4_COORDINATES,
              P4_CHITS,
              HashMap.ofEntries(
                  TileMappingUtils.newSelfReferringEntry("indigenous-land"),
                  TileMappingUtils.newSelfReferringEntry("indigenous-harbor"),
                  TileMappingUtils.newSelfReferringEntry("desert"),
                  TileMappingUtils.newEntry("foreign", "foreign-land", "foreign-sea")),
              HashMap.ofEntries(
                  TileMappingUtils.newSelfReferringEntry("indigenous-land"),
                  TileMappingUtils.newSelfReferringEntry("foreign-land")))
          .withConfigurationMatcher(CONFIGURATION_MATCHER);

  public static final SpecificationImpl P4_SPECIFICATION_IMPL = P4_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P4_FISHERMEN_SPECIFICATION_IMPL =
      P4_SPECIFICATION_BUILDER.withFisheries(P4_INDIGENOUS_FISHERY_COORDINATES).build();

  private static final Tuple2<Array<Tile>, Boolean> P5_P6_INDIGENOUS_LAND_TILES =
      Tuple.of(
          Array.fill(4, FIELD)
              .appendAll(Array.fill(4, FOREST))
              .appendAll(Array.fill(4, MOUNTAIN))
              .appendAll(Array.fill(4, PASTURE))
              .appendAll(Array.fill(5, HILL)),
          false);
  private static final Tuple2<Array<Tile>, Boolean> P5_P6_INDIGENOUS_HARBOR_TILES =
      Base.P5_P6_HARBOR_TILES;
  private static final Tuple2<Array<Tile>, Boolean> P5_P6_DESERT_TILES =
      Tuple.of(Array.fill(5, DESERT), true);
  private static final Tuple2<Array<Tile>, Boolean> P5_P6_FOREIGN_LAND_TILES =
      Tuple.of(
          Array.fill(2, HILL)
              .appendAll(Array.fill(3, GOLD_FIELD))
              .appendAll(Array.fill(3, FIELD))
              .appendAll(Array.fill(3, FOREST))
              .appendAll(Array.fill(3, MOUNTAIN))
              .appendAll(Array.fill(3, PASTURE)),
          false);
  private static final Tuple2<Array<Tile>, Boolean> P5_P6_FOREIGN_SEA_TILES =
      Tuple.of(Array.fill(5, SEA), true);
  private static final Map<String, Tuple2<Array<Tile>, Boolean>> P5_P6_TILES =
      HashMap.of(
          "indigenous-land",
          P5_P6_INDIGENOUS_LAND_TILES,
          "indigenous-harbor",
          P5_P6_INDIGENOUS_HARBOR_TILES,
          "desert",
          P5_P6_DESERT_TILES,
          "foreign-land",
          P5_P6_FOREIGN_LAND_TILES,
          "foreign-sea",
          P5_P6_FOREIGN_SEA_TILES);

  private static final Array<Coordinate> P5_P6_INDIGENOUS_LAND_COORDINATES =
      Array.of(
          Coordinates.of(3, 1),
          Coordinates.of(2, 2),
          Coordinates.of(4, 2),
          Coordinates.of(1, 3),
          Coordinates.of(3, 3),
          Coordinates.of(5, 3),
          Coordinates.of(7, 3),
          Coordinates.of(9, 3),
          Coordinates.of(11, 3),
          Coordinates.of(13, 3),
          Coordinates.of(2, 4),
          Coordinates.of(4, 4),
          Coordinates.of(6, 4),
          Coordinates.of(8, 4),
          Coordinates.of(10, 4),
          Coordinates.of(12, 4),
          Coordinates.of(14, 4),
          Coordinates.of(7, 5),
          Coordinates.of(9, 5),
          Coordinates.of(11, 5),
          Coordinates.of(13, 5));
  private static final Array<Coordinate> P5_P6_INDIGENOUS_HARBOR_COORDINATES =
      Array.of(
          Coordinates.of(2, 0, BOTTOM_RIGHT),
          Coordinates.of(5, 1, BOTTOM_LEFT),
          Coordinates.of(0, 2, RIGHT),
          Coordinates.of(6, 2, BOTTOM_LEFT),
          Coordinates.of(8, 2, BOTTOM_RIGHT),
          Coordinates.of(12, 2, BOTTOM_RIGHT),
          Coordinates.of(15, 3, BOTTOM_LEFT),
          Coordinates.of(0, 4, TOP_RIGHT),
          Coordinates.of(1, 5, TOP_RIGHT),
          Coordinates.of(5, 5, TOP_RIGHT),
          Coordinates.of(15, 5, LEFT));
  private static final Array<Coordinate> P5_P6_INDIGENOUS_FISHERY_COORDINATES =
      Array.of(Coordinates.of(1, 1, RIGHT, BOTTOM_RIGHT), Coordinates.of(1, 3, LEFT, TOP_LEFT));
  private static final Array<Coordinate> P5_P6_DESERT_COORDINATES =
      Array.of(
          Coordinates.of(6, 6),
          Coordinates.of(8, 6),
          Coordinates.of(10, 6),
          Coordinates.of(12, 6),
          Coordinates.of(14, 6));
  private static final Array<Coordinate> P5_P6_FOREIGN_COORDINATES =
      Array.of(
          Coordinates.of(7, 1),
          Coordinates.of(9, 1),
          Coordinates.of(11, 1),
          Coordinates.of(13, 1),
          Coordinates.of(15, 1),
          Coordinates.of(17, 1),
          Coordinates.of(16, 2),
          Coordinates.of(18, 2),
          Coordinates.of(19, 3),
          Coordinates.of(18, 4),
          Coordinates.of(19, 5),
          Coordinates.of(2, 6),
          Coordinates.of(4, 6),
          Coordinates.of(18, 6),
          Coordinates.of(3, 7),
          Coordinates.of(5, 7),
          Coordinates.of(7, 7),
          Coordinates.of(9, 7),
          Coordinates.of(11, 7),
          Coordinates.of(13, 7),
          Coordinates.of(15, 7),
          Coordinates.of(17, 7));
  private static final Map<String, Array<Coordinate>> P5_P6_COORDINATES =
      HashMap.of(
          "indigenous-land",
          P5_P6_INDIGENOUS_LAND_COORDINATES,
          "indigenous-harbor",
          P5_P6_INDIGENOUS_HARBOR_COORDINATES,
          "desert",
          P5_P6_DESERT_COORDINATES,
          "foreign",
          P5_P6_FOREIGN_COORDINATES);

  private static final Array<ChitOuterClass.Chit> P5_P6_INDIGENOUS_LAND_CHITS =
      Array.of(CHIT_2)
          .appendAll(Array.fill(2, CHIT_3))
          .appendAll(Array.fill(2, CHIT_4))
          .appendAll(Array.fill(2, CHIT_5))
          .appendAll(Array.fill(2, CHIT_6))
          .appendAll(Array.fill(2, CHIT_8))
          .appendAll(Array.fill(3, CHIT_9))
          .appendAll(Array.fill(3, CHIT_10))
          .appendAll(Array.fill(2, CHIT_11))
          .appendAll(Array.fill(2, CHIT_12));
  private static final Array<ChitOuterClass.Chit> P5_P6_FOREIGN_LAND_CHITS =
      Array.of(CHIT_9, CHIT_10, CHIT_12)
          .appendAll(Array.fill(2, CHIT_2))
          .appendAll(Array.fill(2, CHIT_3))
          .appendAll(Array.fill(2, CHIT_4))
          .appendAll(Array.fill(2, CHIT_5))
          .appendAll(Array.fill(2, CHIT_6))
          .appendAll(Array.fill(2, CHIT_8))
          .appendAll(Array.fill(2, CHIT_11));
  private static final Map<String, Array<ChitOuterClass.Chit>> P5_P6_CHITS =
      HashMap.of(
          "indigenous-land", P5_P6_INDIGENOUS_LAND_CHITS,
          "foreign-land", P5_P6_FOREIGN_LAND_CHITS);

  private static final SpecificationImpl.Builder P5_P6_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
              P5_P6_TILES,
              P5_P6_COORDINATES,
              P5_P6_CHITS,
              HashMap.ofEntries(
                  TileMappingUtils.newSelfReferringEntry("indigenous-land"),
                  TileMappingUtils.newSelfReferringEntry("indigenous-harbor"),
                  TileMappingUtils.newSelfReferringEntry("desert"),
                  TileMappingUtils.newEntry("foreign", "foreign-land", "foreign-sea")),
              HashMap.ofEntries(
                  TileMappingUtils.newSelfReferringEntry("indigenous-land"),
                  TileMappingUtils.newSelfReferringEntry("foreign-land")))
          .withConfigurationMatcher(CONFIGURATION_MATCHER);

  public static final SpecificationImpl P5_P6_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P5_P6_FISHERMEN_SPECIFICATION_IMPL =
      P5_P6_SPECIFICATION_BUILDER.withFisheries(P5_P6_INDIGENOUS_FISHERY_COORDINATES).build();

  private static final Tuple2<Array<Tile>, Boolean> P7_P8_INDIGENOUS_LAND_TILES =
      Tuple.of(
          Array.fill(5, FIELD)
              .appendAll(Array.fill(5, FOREST))
              .appendAll(Array.fill(5, MOUNTAIN))
              .appendAll(Array.fill(5, PASTURE))
              .appendAll(Array.fill(6, HILL)),
          false);
  private static final Tuple2<Array<Tile>, Boolean> P7_P8_INDIGENOUS_HARBOR_TILES =
      Base.P7_P8_HARBOR_TILES;
  private static final Tuple2<Array<Tile>, Boolean> P7_P8_DESERT_TILES =
      Tuple.of(Array.fill(7, DESERT), true);
  private static final Tuple2<Array<Tile>, Boolean> P7_P8_FOREIGN_LAND_TILES =
      Tuple.of(
          Array.fill(4, HILL)
              .appendAll(Array.fill(4, PASTURE))
              .appendAll(Array.fill(5, FIELD))
              .appendAll(Array.fill(5, FOREST))
              .appendAll(Array.fill(5, MOUNTAIN))
              .appendAll(Array.fill(5, GOLD_FIELD)),
          false);
  private static final Tuple2<Array<Tile>, Boolean> P7_P8_FOREIGN_SEA_TILES =
      Tuple.of(Array.fill(8, SEA), true);
  private static final Map<String, Tuple2<Array<Tile>, Boolean>> P7_P8_TILES =
      HashMap.of(
          "indigenous-land",
          P7_P8_INDIGENOUS_LAND_TILES,
          "indigenous-harbor",
          P7_P8_INDIGENOUS_HARBOR_TILES,
          "desert",
          P7_P8_DESERT_TILES,
          "foreign-land",
          P7_P8_FOREIGN_LAND_TILES,
          "foreign-sea",
          P7_P8_FOREIGN_SEA_TILES);

  private static final Array<Coordinate> P7_P8_INDIGENOUS_LAND_COORDINATES =
      Array.of(
          Coordinates.of(3, 1),
          Coordinates.of(2, 2),
          Coordinates.of(4, 2),
          Coordinates.of(1, 3),
          Coordinates.of(3, 3),
          Coordinates.of(5, 3),
          Coordinates.of(7, 3),
          Coordinates.of(9, 3),
          Coordinates.of(11, 3),
          Coordinates.of(13, 3),
          Coordinates.of(15, 3),
          Coordinates.of(2, 4),
          Coordinates.of(4, 4),
          Coordinates.of(6, 4),
          Coordinates.of(8, 4),
          Coordinates.of(10, 4),
          Coordinates.of(12, 4),
          Coordinates.of(14, 4),
          Coordinates.of(16, 4),
          Coordinates.of(18, 4),
          Coordinates.of(7, 5),
          Coordinates.of(9, 5),
          Coordinates.of(11, 5),
          Coordinates.of(13, 5),
          Coordinates.of(15, 5),
          Coordinates.of(17, 5));
  private static final Array<Coordinate> P7_P8_INDIGENOUS_HARBOR_COORDINATES =
      Array.of(
          Coordinates.of(2, 0, BOTTOM_RIGHT),
          Coordinates.of(5, 1, BOTTOM_LEFT),
          Coordinates.of(0, 2, RIGHT),
          Coordinates.of(6, 2, BOTTOM_RIGHT),
          Coordinates.of(10, 2, BOTTOM_LEFT),
          Coordinates.of(12, 2, BOTTOM_RIGHT),
          Coordinates.of(16, 2, BOTTOM_LEFT),
          Coordinates.of(17, 3, BOTTOM_RIGHT),
          Coordinates.of(0, 4, TOP_RIGHT),
          Coordinates.of(3, 5, TOP_LEFT),
          Coordinates.of(5, 5, TOP_RIGHT),
          Coordinates.of(19, 5, TOP_LEFT));
  private static final Array<Coordinate> P7_P8_INDIGENOUS_FISHERY_COORDINATES =
      P5_P6_INDIGENOUS_FISHERY_COORDINATES;
  private static final Array<Coordinate> P7_P8_DESERT_COORDINATES =
      Array.of(
          Coordinates.of(6, 6),
          Coordinates.of(8, 6),
          Coordinates.of(10, 6),
          Coordinates.of(12, 6),
          Coordinates.of(14, 6),
          Coordinates.of(16, 6),
          Coordinates.of(18, 6));
  private static final Array<Coordinate> P7_P8_FOREIGN_COORDINATES =
      Array.of(
          Coordinates.of(7, 1),
          Coordinates.of(9, 1),
          Coordinates.of(11, 1),
          Coordinates.of(13, 1),
          Coordinates.of(15, 1),
          Coordinates.of(17, 1),
          Coordinates.of(19, 1),
          Coordinates.of(21, 1),
          Coordinates.of(20, 2),
          Coordinates.of(22, 2),
          Coordinates.of(24, 2),
          Coordinates.of(23, 3),
          Coordinates.of(25, 3),
          Coordinates.of(22, 4),
          Coordinates.of(24, 4),
          Coordinates.of(23, 5),
          Coordinates.of(2, 6),
          Coordinates.of(4, 6),
          Coordinates.of(22, 6),
          Coordinates.of(24, 6),
          Coordinates.of(1, 7),
          Coordinates.of(3, 7),
          Coordinates.of(5, 7),
          Coordinates.of(7, 7),
          Coordinates.of(9, 7),
          Coordinates.of(11, 7),
          Coordinates.of(13, 7),
          Coordinates.of(15, 7),
          Coordinates.of(17, 7),
          Coordinates.of(19, 7),
          Coordinates.of(21, 7),
          Coordinates.of(0, 8),
          Coordinates.of(6, 8),
          Coordinates.of(12, 8),
          Coordinates.of(18, 8),
          Coordinates.of(24, 8));
  private static final Map<String, Array<Coordinate>> P7_P8_COORDINATES =
      HashMap.of(
          "indigenous-land",
          P7_P8_INDIGENOUS_LAND_COORDINATES,
          "indigenous-harbor",
          P7_P8_INDIGENOUS_HARBOR_COORDINATES,
          "desert",
          P7_P8_DESERT_COORDINATES,
          "foreign",
          P7_P8_FOREIGN_COORDINATES);

  private static final Array<ChitOuterClass.Chit> P7_P8_INDIGENOUS_LAND_CHITS =
      Array.of(CHIT_2, CHIT_12)
          .appendAll(Array.fill(2, CHIT_3))
          .appendAll(Array.fill(3, CHIT_4))
          .appendAll(Array.fill(4, CHIT_5))
          .appendAll(Array.fill(3, CHIT_6))
          .appendAll(Array.fill(3, CHIT_8))
          .appendAll(Array.fill(4, CHIT_9))
          .appendAll(Array.fill(3, CHIT_10))
          .appendAll(Array.fill(2, CHIT_11));
  private static final Array<ChitOuterClass.Chit> P7_P8_FOREIGN_LAND_CHITS =
      Array.fill(2, CHIT_2)
          .appendAll(Array.fill(3, CHIT_3))
          .appendAll(Array.fill(3, CHIT_4))
          .appendAll(Array.fill(3, CHIT_5))
          .appendAll(Array.fill(3, CHIT_6))
          .appendAll(Array.fill(3, CHIT_8))
          .appendAll(Array.fill(3, CHIT_9))
          .appendAll(Array.fill(3, CHIT_10))
          .appendAll(Array.fill(3, CHIT_11))
          .appendAll(Array.fill(2, CHIT_12));
  private static final Map<String, Array<ChitOuterClass.Chit>> P7_P8_CHITS =
      HashMap.of(
          "indigenous-land", P7_P8_INDIGENOUS_LAND_CHITS,
          "foreign-land", P7_P8_FOREIGN_LAND_CHITS);

  private static final SpecificationImpl.Builder P7_P8_SPECIFICATION_BUILDER =
      SpecificationImpl.newBuilder(
              P7_P8_TILES,
              P7_P8_COORDINATES,
              P7_P8_CHITS,
              HashMap.ofEntries(
                  TileMappingUtils.newSelfReferringEntry("indigenous-land"),
                  TileMappingUtils.newSelfReferringEntry("indigenous-harbor"),
                  TileMappingUtils.newSelfReferringEntry("desert"),
                  TileMappingUtils.newEntry("foreign", "foreign-land", "foreign-sea")),
              HashMap.ofEntries(
                  TileMappingUtils.newSelfReferringEntry("indigenous-land"),
                  TileMappingUtils.newSelfReferringEntry("foreign-land")))
          .withConfigurationMatcher(CONFIGURATION_MATCHER);

  public static final SpecificationImpl P7_P8_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.build();
  public static final SpecificationImpl P7_P8_FISHERMEN_SPECIFICATION_IMPL =
      P7_P8_SPECIFICATION_BUILDER.withFisheries(P7_P8_INDIGENOUS_FISHERY_COORDINATES).build();
}
