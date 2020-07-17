package noelyap.setterforcatan.component;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import io.vavr.collection.Array;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import io.vavr.collection.Multimap;
import io.vavr.collection.Set;
import noelyap.setterforcatan.component.Specification.InvalidSpecificationError;
import noelyap.setterforcatan.protogen.ChitOuterClass.Chit;
import noelyap.setterforcatan.protogen.ConfigurationOuterClass.Configuration;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Coordinate;
import noelyap.setterforcatan.protogen.CoordinateOuterClass.Vertex;
import noelyap.setterforcatan.protogen.TileOuterClass.Tile;
import noelyap.setterforcatan.util.ChitUtils;
import noelyap.setterforcatan.util.MersenneTwister;
import noelyap.setterforcatan.util.TileMappingUtils;
import noelyap.setterforcatan.util.TileUtils;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SoftAssertionsExtension.class)
@SuppressWarnings({"deprecation", "unchecked"})
public class SpecificationTest {
  final MersenneTwister prng = new MersenneTwister();

  @Test
  public void shouldCreateSpecificationWithFisheries(final SoftAssertions softly) throws Exception {
    final Array<Coordinate> goldFieldCoordinates = newCoordinates(Tuple.of(6, 6));
    final Array<Coordinate> desertCoordinates = newCoordinates(Tuple.of(1, 1));
    final Array<Coordinate> desertOrLakeCoordinates =
        newCoordinates(Tuple.of(2, 2), Tuple.of(3, 3), Tuple.of(4, 4), Tuple.of(5, 5));

    final Map<String, Tuple2<Array<Tile>, Boolean>> tiles =
        HashMap.of(
            "gold-field",
            newTiles(Tile.Type.GOLD_FIELD),
            "desert",
            newChitlessTiles(Tile.Type.DESERT),
            TileUtils.DESERT_OR_LAKE_NAME,
            newChitlessTiles(
                Tile.Type.DESERT, Tile.Type.DESERT, Tile.Type.DESERT, Tile.Type.DESERT));
    final Map<String, Array<Chit>> chits = HashMap.of("gold-field", ChitUtils.newChits(6));
    final Map<String, Array<String>> chitsTilesMap =
        HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("gold-field"));
    final Map<String, Array<Coordinate>> coordinates =
        HashMap.of(
            "gold-field",
            goldFieldCoordinates,
            "desert",
            desertCoordinates,
            TileUtils.DESERT_OR_LAKE_NAME,
            desertOrLakeCoordinates);
    final Map<String, Array<String>> coordinatesTilesMap =
        HashMap.ofEntries(
            TileMappingUtils.newSelfReferringEntry("gold-field"),
            TileMappingUtils.newSelfReferringEntry("desert"),
            TileMappingUtils.newSelfReferringEntry(TileUtils.DESERT_OR_LAKE_NAME));

    final Array<Coordinate> fisheriesCoordinates =
        newCoordinates(
            Tuple.of(6, 6, Vertex.Position.TOP),
            Tuple.of(7, 7, Vertex.Position.TOP_RIGHT),
            Tuple.of(8, 8, Vertex.Position.BOTTOM_RIGHT),
            Tuple.of(9, 9, Vertex.Position.BOTTOM),
            Tuple.of(10, 10, Vertex.Position.BOTTOM_LEFT),
            Tuple.of(11, 11, Vertex.Position.TOP_LEFT),
            Tuple.of(12, 12, Vertex.Position.TOP),
            Tuple.of(13, 13, Vertex.Position.BOTTOM_RIGHT),
            Tuple.of(14, 14, Vertex.Position.BOTTOM_LEFT));

    final Array<Configuration> actual =
        new Specification(tiles, coordinates, chits, coordinatesTilesMap, chitsTilesMap)
            .withFisheries(fisheriesCoordinates)
            .toConfiguration();

    final Set<Configuration> actualDesertConfigurations =
        actual.filter(c -> c.getTile().getType() == Tile.Type.DESERT).toSet();
    final Array<Chit> actualDesertChits =
        Array.ofAll(actualDesertConfigurations)
            .map(c -> c.getChit())
            .filter(c -> c.getValueCount() != 0);
    final Set<Coordinate> actualDesertCoordinates =
        actualDesertConfigurations.map(c -> c.getCoordinate());

    final Set<Configuration> actualLakeConfigurations =
        actual.filter(c -> c.getTile().getType() == Tile.Type.LAKE).toSet();
    final Array<Chit> actualLakeChits = Array.ofAll(actualLakeConfigurations).map(c -> c.getChit());
    final Set<Coordinate> actualLakeCoordinates =
        actualLakeConfigurations.map(c -> c.getCoordinate());

    final Set<Configuration> actualFisheryConfigurations =
        actual.filter(c -> c.getTile().getType() == Tile.Type.FISHERY).toSet();
    final Array<Chit> actualFisheryChits =
        Array.ofAll(actualFisheryConfigurations).map(c -> c.getChit());

    softly.assertThat(actual).hasSize(15);
    softly.assertThat(actualDesertChits).isEmpty();
    softly
        .assertThat(actualDesertCoordinates)
        .containsExactlyInAnyOrder(desertCoordinates.toJavaArray(Coordinate[]::new));
    softly
        .assertThat(actualLakeChits)
        .containsExactlyInAnyOrder(
            Array.of(
                    ChitUtils.newChit(2, 3, 11, 12),
                    ChitUtils.newChit(2, 3, 11, 12),
                    ChitUtils.newChit(4, 10),
                    ChitUtils.newChit(4, 10))
                .toJavaArray(Chit[]::new));
    softly
        .assertThat(actualLakeCoordinates)
        .containsExactlyInAnyOrder(desertOrLakeCoordinates.toJavaArray(Coordinate[]::new));
    softly
        .assertThat(actualFisheryChits)
        .containsExactlyInAnyOrder(
            ChitUtils.newChits(4, 4, 5, 5, 6, 8, 9, 9, 10).toJavaArray(Chit[]::new));
  }

  @Test
  public void shouldShuffleTilesWithArtifacts(final SoftAssertions softly) {
    final Map<String, Tuple2<Array<Tile>, Boolean>> tiles =
        HashMap.of(
            "desert-and-lake",
            newTiles(Tile.Type.DESERT, Tile.Type.LAKE),
            "gold",
            newTiles(Tile.Type.GOLD_FIELD),
            "sea",
            newTiles(Tile.Type.SEA),
            "caravans",
            newTiles(Tile.Type.OASIS),
            "rivers-of-catan",
            newTiles(Tile.Type.SWAMP),
            "traders-and-barbarians",
            newTiles(Tile.Type.CASTLE, Tile.Type.GLASSWORKS, Tile.Type.QUARRY));
    final Map<String, Array<Coordinate>> coordinates =
        HashMap.of(
            "desert-and-lake",
            newCoordinates(Tuple.of(1, 1), Tuple.of(2, 2)),
            "seafarers",
            newCoordinates(Tuple.of(3, 3), Tuple.of(4, 4)),
            "traders-and-barbarians",
            newCoordinates(
                Tuple.of(5, 5), Tuple.of(6, 6), Tuple.of(7, 7), Tuple.of(8, 8), Tuple.of(9, 9)));
    final Map<String, Array<String>> coordinatesTilesMap =
        HashMap.ofEntries(
            TileMappingUtils.newSelfReferringEntry("desert-and-lake"),
            TileMappingUtils.newEntry("seafarers", "gold", "sea"),
            TileMappingUtils.newEntry(
                "traders-and-barbarians", "caravans", "rivers-of-catan", "traders-and-barbarians"));

    final Multimap<Tile, Coordinate> actual =
        Specification.shuffleTiles(prng, tiles, coordinates, coordinatesTilesMap);

    softly
        .assertThat(actual.keySet())
        .containsExactlyInAnyOrder(
            newTiles(
                    Tile.Type.DESERT,
                    Tile.Type.LAKE,
                    Tile.Type.GOLD_FIELD,
                    Tile.Type.SEA,
                    Tile.Type.OASIS,
                    Tile.Type.SWAMP,
                    Tile.Type.CASTLE,
                    Tile.Type.GLASSWORKS,
                    Tile.Type.QUARRY)
                ._1
                .toJavaArray(Tile[]::new));
    softly
        .assertThat(actual.values())
        .containsExactlyInAnyOrder(
            newCoordinates(
                    Tuple.of(1, 1),
                    Tuple.of(2, 2),
                    Tuple.of(3, 3),
                    Tuple.of(4, 4),
                    Tuple.of(5, 5),
                    Tuple.of(6, 6),
                    Tuple.of(7, 7),
                    Tuple.of(8, 8),
                    Tuple.of(9, 9))
                .toJavaArray(Coordinate[]::new));

    softly
        .assertThat(actual.get(newTile(Tile.Type.DESERT)).get())
        .allSatisfy(c -> assertThat(c).isIn(newCoordinate(1, 1), newCoordinate(2, 2)));
    softly
        .assertThat(actual.get(newTile(Tile.Type.LAKE)).get())
        .allSatisfy(c -> assertThat(c).isIn(newCoordinate(1, 1), newCoordinate(2, 2)));
    softly
        .assertThat(actual.get(newTile(Tile.Type.GOLD_FIELD)).get())
        .allSatisfy(c -> assertThat(c).isIn(newCoordinate(3, 3), newCoordinate(4, 4)));
    softly
        .assertThat(actual.get(newTile(Tile.Type.SEA)).get())
        .allSatisfy(c -> assertThat(c).isIn(newCoordinate(3, 3), newCoordinate(4, 4)));
    softly
        .assertThat(actual.get(newTile(Tile.Type.OASIS)).get())
        .allSatisfy(c ->
                assertThat(c)
                    .isIn(
                        newCoordinate(5, 5),
                        newCoordinate(6, 6),
                        newCoordinate(7, 7),
                        newCoordinate(8, 8),
                        newCoordinate(9, 9)));
    softly
        .assertThat(actual.get(newTile(Tile.Type.SWAMP)).get())
        .allSatisfy(c ->
                assertThat(c)
                    .isIn(
                        newCoordinate(5, 5),
                        newCoordinate(6, 6),
                        newCoordinate(7, 7),
                        newCoordinate(8, 8),
                        newCoordinate(9, 9)));
    softly
        .assertThat(actual.get(newTile(Tile.Type.CASTLE)).get())
        .allSatisfy(c ->
                assertThat(c)
                    .isIn(
                        newCoordinate(5, 5),
                        newCoordinate(6, 6),
                        newCoordinate(7, 7),
                        newCoordinate(8, 8),
                        newCoordinate(9, 9)));
    softly
        .assertThat(actual.get(newTile(Tile.Type.GLASSWORKS)).get())
        .allSatisfy(c ->
                assertThat(c)
                    .isIn(
                        newCoordinate(5, 5),
                        newCoordinate(6, 6),
                        newCoordinate(7, 7),
                        newCoordinate(8, 8),
                        newCoordinate(9, 9)));
    softly
        .assertThat(actual.get(newTile(Tile.Type.QUARRY)).get())
        .allSatisfy(c ->
                assertThat(c)
                    .isIn(
                        newCoordinate(5, 5),
                        newCoordinate(6, 6),
                        newCoordinate(7, 7),
                        newCoordinate(8, 8),
                        newCoordinate(9, 9)));
  }

  @Test
  public void shouldNotDedupTiles() {
    final Map<String, Tuple2<Array<Tile>, Boolean>> tiles =
        HashMap.of("gold-field", newTiles(Tile.Type.GOLD_FIELD, Tile.Type.GOLD_FIELD));
    final Map<String, Array<Chit>> chits = HashMap.of("gold-field", ChitUtils.newChits(1, 1));
    final Map<String, Array<String>> chitsTilesMap =
        HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("gold-field"));

    final Multimap<Tile, Chit> actual =
        Specification.shuffleTiles(prng, tiles, chits, chitsTilesMap);

    assertThat(actual).hasSize(2);
  }

  @Test
  public void shouldValidateDuplicateCoordinates(final SoftAssertions softly) {
    final Map<String, Array<Coordinate>> coordinates =
        HashMap.of(
            "key-1",
            Array.of(newCoordinate(1, 2), newCoordinate(1, 2), newCoordinate(3, 5)),
            "key-2",
            Array.of(newCoordinate(3, 5), newCoordinate(8, 13)));

    final Set<String> actual = Specification.checkForDuplicateCoordinates(coordinates);

    softly.assertThat(actual).hasSize(2);
    softly
        .assertThat(actual)
        .containsExactlyInAnyOrder(
            "Coordinate `x: 1\ny: 2\n` referenced more than once in [key-1]",
            "Coordinate `x: 3\ny: 5\n` referenced more than once across [key-1, key-2]");
  }

  @Test
  public void shouldValidateCountMismatch() {
    final Map<String, Tuple2<Array<Tile>, Boolean>> tiles =
        HashMap.of("sea", newTiles(Tile.Type.SEA));
    final Map<String, Array<Chit>> chits = HashMap.of("sea", ChitUtils.newChits(1, 2));
    final Map<String, Array<String>> chitsTilesMap =
        HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("sea"));
    final Map<String, Array<Coordinate>> coordinates =
        HashMap.of("sea", newCoordinates(Tuple.of(1, 1), Tuple.of(2, 2)));
    final Map<String, Array<String>> coordinatesTilesMap =
        HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("sea"));

    final Set<String> actual =
        Specification.checkForFeaturesVersusTilesCountMismatchError(
                "chits", tiles, Specification.widenFeaturesMap(chits), chitsTilesMap)
            .union(
                Specification.checkForFeaturesVersusTilesCountMismatchError(
                    "coordinates",
                    tiles,
                    Specification.widenFeaturesMap(coordinates),
                    coordinatesTilesMap));

    assertThat(actual)
        .containsExactlyInAnyOrder(
            "2 chits in \"sea\" do not match 1 tiles in [sea].",
            "2 coordinates in \"sea\" do not match 1 tiles in [sea].");
  }

  @Test
  public void shouldValidateUndefinedTiles() {
    final Map<String, Tuple2<Array<Tile>, Boolean>> tiles =
        HashMap.of(
            "desert",
            newTiles(Tile.Type.DESERT, Tile.Type.DESERT),
            "gold-field",
            newTiles(Tile.Type.GOLD_FIELD, Tile.Type.GOLD_FIELD));
    final Map<String, Array<Chit>> chits = HashMap.of("oasis", ChitUtils.newChits(1, 2));
    final Map<String, Array<String>> chitsTilesMap =
        HashMap.ofEntries(TileMappingUtils.newEntry("swamp", "desert", "lake"));
    final Map<String, Array<Coordinate>> coordinates =
        HashMap.of("swamp", newCoordinates(Tuple.of(1, 1), Tuple.of(2, 2)));
    final Map<String, Array<String>> coordinatesTilesMap =
        HashMap.ofEntries(TileMappingUtils.newEntry("oasis", "desert", "lake"));

    final Set<String> actual =
        Specification.checkForUndefinedTilesErrors(
                "chits", tiles, Specification.widenFeaturesMap(chits), chitsTilesMap)
            .union(
                Specification.checkForUndefinedTilesErrors(
                    "coordinates",
                    tiles,
                    Specification.widenFeaturesMap(coordinates),
                    coordinatesTilesMap));

    assertThat(actual)
        .containsExactlyInAnyOrder(
            "[lake] tiles referenced in chitsTilesMap \"swamp\" are not defined.",
            "[lake] tiles referenced in coordinatesTilesMap \"oasis\" are not defined.");
  }

  @Test
  public void shouldValidateUnreferencedTiles() {
    final Map<String, Tuple2<Array<Tile>, Boolean>> tiles =
        HashMap.of(
            "desert",
            newTiles(Tile.Type.DESERT, Tile.Type.DESERT),
            "gold-field",
            newTiles(Tile.Type.GOLD_FIELD, Tile.Type.GOLD_FIELD));
    final Map<String, Array<Chit>> chits = HashMap.of("oasis", ChitUtils.newChits(1, 2));
    final Map<String, Array<String>> chitsTilesMap =
        HashMap.ofEntries(TileMappingUtils.newEntry("swamp", "desert", "lake"));
    final Map<String, Array<Coordinate>> coordinates =
        HashMap.of("swamp", newCoordinates(Tuple.of(1, 1), Tuple.of(2, 2)));
    final Map<String, Array<String>> coordinatesTilesMap =
        HashMap.ofEntries(TileMappingUtils.newEntry("oasis", "desert", "lake"));

    final Set<String> actual =
        Specification.checkForUnreferencedTilesErrors(
                "chits", tiles, Specification.widenFeaturesMap(chits), chitsTilesMap)
            .union(
                Specification.checkForUnreferencedTilesErrors(
                    "coordinates",
                    tiles,
                    Specification.widenFeaturesMap(coordinates),
                    coordinatesTilesMap));

    assertThat(actual)
        .containsExactlyInAnyOrder(
            "\"gold-field\" tiles is not referenced in chitsTilesMap.",
            "\"gold-field\" tiles is not referenced in coordinatesTilesMap.");
  }

  @Test
  public void shouldValidateUndefinedFeatures() {
    final Map<String, Tuple2<Array<Tile>, Boolean>> tiles =
        HashMap.of(
            "desert",
            newTiles(Tile.Type.DESERT, Tile.Type.DESERT),
            "gold-field",
            newTiles(Tile.Type.GOLD_FIELD, Tile.Type.GOLD_FIELD));
    final Map<String, Array<Chit>> chits = HashMap.of("oasis", ChitUtils.newChits(1, 2));
    final Map<String, Array<String>> chitsTilesMap =
        HashMap.ofEntries(TileMappingUtils.newEntry("swamp", "desert", "lake"));
    final Map<String, Array<Coordinate>> coordinates =
        HashMap.of("swamp", newCoordinates(Tuple.of(1, 1), Tuple.of(2, 2)));
    final Map<String, Array<String>> coordinatesTilesMap =
        HashMap.ofEntries(TileMappingUtils.newEntry("oasis", "desert", "lake"));

    final Set<String> actual =
        Specification.checkForUndefinedFeaturesErrors(
                "chits", tiles, Specification.widenFeaturesMap(chits), chitsTilesMap)
            .union(
                Specification.checkForUndefinedFeaturesErrors(
                    "coordinates",
                    tiles,
                    Specification.widenFeaturesMap(coordinates),
                    coordinatesTilesMap));

    assertThat(actual)
        .containsExactlyInAnyOrder(
            "\"swamp\" referenced in chitsTilesMap not defined in chits.",
            "\"oasis\" referenced in coordinatesTilesMap not defined in coordinates.");
  }

  @Test
  public void shouldValidateUnreferencedFeatures() {
    final Map<String, Tuple2<Array<Tile>, Boolean>> tiles =
        HashMap.of(
            "desert",
            newTiles(Tile.Type.DESERT, Tile.Type.DESERT),
            "gold-field",
            newTiles(Tile.Type.GOLD_FIELD, Tile.Type.GOLD_FIELD));
    final Map<String, Array<Chit>> chits = HashMap.of("oasis", ChitUtils.newChits(1, 2));
    final Map<String, Array<String>> chitsTilesMap =
        HashMap.ofEntries(TileMappingUtils.newEntry("swamp", "desert", "lake"));
    final Map<String, Array<Coordinate>> coordinates =
        HashMap.of("swamp", newCoordinates(Tuple.of(1, 1), Tuple.of(2, 2)));
    final Map<String, Array<String>> coordinatesTilesMap =
        HashMap.ofEntries(TileMappingUtils.newEntry("oasis", "desert", "lake"));

    final Set<String> actual =
        Specification.checkForUnreferencedFeaturesErrors(
                "chits", tiles, Specification.widenFeaturesMap(chits), chitsTilesMap)
            .union(
                Specification.checkForUnreferencedFeaturesErrors(
                    "coordinates",
                    tiles,
                    Specification.widenFeaturesMap(coordinates),
                    coordinatesTilesMap));

    assertThat(actual)
        .containsExactlyInAnyOrder(
            "\"swamp\" in coordinates not referenced in coordinatesTilesMap.",
            "\"oasis\" in chits not referenced in chitsTilesMap.");
  }

  @Test
  public void shouldValidate(final SoftAssertions softly) {
    final Map<String, Tuple2<Array<Tile>, Boolean>> tiles =
        HashMap.of(
            "desert",
            newTiles(Tile.Type.DESERT, Tile.Type.DESERT),
            "gold-field",
            newTiles(Tile.Type.GOLD_FIELD, Tile.Type.GOLD_FIELD));
    final Map<String, Array<Chit>> chits = HashMap.of("oasis", ChitUtils.newChits(1, 2));
    final Map<String, Array<String>> chitsTilesMap =
        HashMap.ofEntries(TileMappingUtils.newEntry("swamp", "desert", "lake"));
    final Map<String, Array<Coordinate>> coordinates =
        HashMap.of("swamp", newCoordinates(Tuple.of(1, 1), Tuple.of(2, 2)));
    final Map<String, Array<String>> coordinatesTilesMap =
        HashMap.ofEntries(TileMappingUtils.newEntry("oasis", "desert", "lake"));

    assertThatThrownBy(
            () -> new Specification(tiles, coordinates, chits, coordinatesTilesMap, chitsTilesMap))
        .isInstanceOf(InvalidSpecificationError.class)
        .hasMessageContainingAll(
            "0 chits in \"swamp\" do not match 2 tiles in [desert, lake].",
            "0 coordinates in \"oasis\" do not match 2 tiles in [desert, lake].",
            "[lake] tiles referenced in chitsTilesMap \"swamp\" are not defined.",
            "[lake] tiles referenced in coordinatesTilesMap \"oasis\" are not defined.",
            "\"gold-field\" tiles is not referenced in coordinatesTilesMap.",
            "\"swamp\" referenced in chitsTilesMap not defined in chits.",
            "\"oasis\" referenced in coordinatesTilesMap not defined in coordinates.",
            "\"oasis\" in chits not referenced in chitsTilesMap.",
            "\"swamp\" in coordinates not referenced in coordinatesTilesMap.");
  }

  @Test
  public void shouldCreateConfiguration(final SoftAssertions softly) throws Exception {
    final Tuple2<Array<Tile>, Boolean> producingTerrainTiles =
        newTiles(
            Tile.Type.FIELD,
            Tile.Type.FOREST,
            Tile.Type.HILL,
            Tile.Type.MOUNTAIN,
            Tile.Type.PASTURE,
            Tile.Type.GOLD_FIELD,
            Tile.Type.LAKE);
    final Tuple2<Array<Tile>, Boolean> barrenTerrainTiles =
        newChitlessTiles(Tile.Type.DESERT, Tile.Type.SWAMP, Tile.Type.OASIS);
    final Tuple2<Array<Tile>, Boolean> tradersAndBarbariansDestinationTiles =
        newChitlessTiles(Tile.Type.CASTLE, Tile.Type.GLASSWORKS, Tile.Type.QUARRY);

    final Array<Chit> producingTerrainChits = ChitUtils.newChits(1, 2, 3, 4, 5, 6, 7);

    final Array<Coordinate> terrainCoordinates =
        newCoordinates(
            Tuple.of(1, 1),
            Tuple.of(2, 2),
            Tuple.of(3, 3),
            Tuple.of(4, 4),
            Tuple.of(5, 5),
            Tuple.of(6, 6),
            Tuple.of(7, 7),
            Tuple.of(8, 8),
            Tuple.of(9, 9),
            Tuple.of(10, 10));
    final Array<Coordinate> tradersAndBarbariansDestinationCoordinates =
        newCoordinates(Tuple.of(11, 11), Tuple.of(12, 12), Tuple.of(13, 13));

    final Map<String, Tuple2<Array<Tile>, Boolean>> tiles =
        HashMap.of(
            "producing-terrain",
            producingTerrainTiles,
            "barren-terrain",
            barrenTerrainTiles,
            "traders-and-barbarians-destination",
            tradersAndBarbariansDestinationTiles);
    final Map<String, Array<Chit>> chits = HashMap.of("producing-terrain", producingTerrainChits);
    final Map<String, Array<String>> chitsTilesMap =
        HashMap.ofEntries(TileMappingUtils.newSelfReferringEntry("producing-terrain"));
    final Map<String, Array<Coordinate>> coordinates =
        HashMap.of(
            "terrain",
            terrainCoordinates,
            "traders-and-barbarians-destination",
            tradersAndBarbariansDestinationCoordinates);
    final Map<String, Array<String>> coordinatesTilesMap =
        HashMap.ofEntries(
            TileMappingUtils.newEntry("terrain", "producing-terrain", "barren-terrain"),
            TileMappingUtils.newSelfReferringEntry("traders-and-barbarians-destination"));

    final Specification specification =
        new Specification(tiles, coordinates, chits, coordinatesTilesMap, chitsTilesMap);

    final Array<Configuration> actual = specification.toConfiguration();

    final Set<Configuration> producingTerrainConfigurationsFromTiles =
        actual.filter(c -> producingTerrainTiles._1.contains(c.getTile())).toSet();
    final Set<Configuration> barrenTerrainConfigurationsFromTiles =
        actual.filter(c -> barrenTerrainTiles._1.contains(c.getTile())).toSet();
    final Set<Configuration> tradersAndBarbariansDestinationConfigurationsFromTiles =
        actual.filter(c -> tradersAndBarbariansDestinationTiles._1.contains(c.getTile())).toSet();

    final Set<Configuration> producingTerrainConfigurationsFromChits =
        actual.filter(c -> producingTerrainChits.contains(c.getChit())).toSet();
    final Set<Configuration> configurationsWithNoChits =
        actual.filter(c -> c.getChit().getValueCount() == 0).toSet();

    final Set<Configuration> terrainConfigurationsFromCoordinates =
        actual.filter(c -> terrainCoordinates.contains(c.getCoordinate())).toSet();
    final Set<Configuration> tradersAndBarbariansDestinationConfigurationsFromCoordinates =
        actual
            .filter(c -> tradersAndBarbariansDestinationCoordinates.contains(c.getCoordinate()))
            .toSet();

    softly.assertThat(actual).hasSize(13);
    softly
        .assertThat(producingTerrainConfigurationsFromTiles)
        .containsExactlyInAnyOrder(
            producingTerrainConfigurationsFromChits.toJavaArray(Configuration[]::new));
    softly
        .assertThat(
            barrenTerrainConfigurationsFromTiles.union(
                tradersAndBarbariansDestinationConfigurationsFromTiles))
        .containsExactlyInAnyOrder(configurationsWithNoChits.toJavaArray(Configuration[]::new));
    softly
        .assertThat(
            producingTerrainConfigurationsFromTiles.union(barrenTerrainConfigurationsFromTiles))
        .containsExactlyInAnyOrder(
            terrainConfigurationsFromCoordinates.toJavaArray(Configuration[]::new));
    softly
        .assertThat(tradersAndBarbariansDestinationConfigurationsFromTiles)
        .containsExactlyInAnyOrder(
            tradersAndBarbariansDestinationConfigurationsFromCoordinates.toJavaArray(
                Configuration[]::new));
  }

  private Array<Coordinate> newCoordinates(final Tuple2<Integer, Integer>... tuple2s) {
    return Array.of(tuple2s).map(t2 -> newCoordinate(t2._1, t2._2));
  }

  private Coordinate newCoordinate(final int x, final int y) {
    return Coordinate.newBuilder().setX(x).setY(y).build();
  }

  private Array<Coordinate> newCoordinates(
      final Tuple3<Integer, Integer, Vertex.Position>... tuple3s) {
    return Array.of(tuple3s).map(t3 -> newCoordinate(t3._1, t3._2, t3._3));
  }

  private Coordinate newCoordinate(final int x, final int y, final Vertex.Position p) {
    return Coordinate.newBuilder().setX(x).setY(y).addVertexPositions(p).build();
  }

  private Tuple2<Array<Tile>, Boolean> newTiles(final Tile.Type... tileTypes) {
    return Tuple.of(Array.of(tileTypes).map(tt -> newTile(tt)), false);
  }

  private Tuple2<Array<Tile>, Boolean> newChitlessTiles(final Tile.Type... tileTypes) {
    return Tuple.of(Array.of(tileTypes).map(tt -> newTile(tt)), true);
  }

  private Tile newTile(final Tile.Type tileType) {
    return Tile.newBuilder().setType(tileType).build();
  }
}
