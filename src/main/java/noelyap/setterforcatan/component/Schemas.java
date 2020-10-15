package noelyap.setterforcatan.component;

import static noelyap.setterforcatan.protogen.ScenarioOuterClass.Scenario.BASE;
import static noelyap.setterforcatan.protogen.ScenarioOuterClass.Scenario.SEAFARERS_CLOTH_FOR_CATAN;
import static noelyap.setterforcatan.protogen.ScenarioOuterClass.Scenario.SEAFARERS_HEADING_FOR_NEW_SHORES;
import static noelyap.setterforcatan.protogen.ScenarioOuterClass.Scenario.SEAFARERS_OCEANIA;
import static noelyap.setterforcatan.protogen.ScenarioOuterClass.Scenario.SEAFARERS_THE_FORGOTTEN_TRIBE;
import static noelyap.setterforcatan.protogen.ScenarioOuterClass.Scenario.SEAFARERS_THE_N_ISLANDS;
import static noelyap.setterforcatan.protogen.ScenarioOuterClass.Scenario.SEAFARERS_THROUGH_THE_DESERT;
import static noelyap.setterforcatan.protogen.ScenarioOuterClass.Scenario.SEAFARERS_WONDERS_OF_CATAN;
import static noelyap.setterforcatan.protogen.ScenarioOuterClass.Scenario.TRADERS_AND_BARBARIANS_RIVERS_OF_CATAN;
import static noelyap.setterforcatan.protogen.ScenarioOuterClass.Scenario.TRADERS_AND_BARBARIANS_THE_CARAVANS;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import noelyap.setterforcatan.protogen.ScenarioOuterClass.Scenario;
import noelyap.setterforcatan.scenario.Base;
import noelyap.setterforcatan.scenario.seafarers.ClothForCatan;
import noelyap.setterforcatan.scenario.seafarers.HeadingForNewShores;
import noelyap.setterforcatan.scenario.seafarers.Oceania;
import noelyap.setterforcatan.scenario.seafarers.TheForgottenTribe;
import noelyap.setterforcatan.scenario.seafarers.TheNIslands;
import noelyap.setterforcatan.scenario.seafarers.ThroughTheDesert;
import noelyap.setterforcatan.scenario.seafarers.WondersOfCatan;
import noelyap.setterforcatan.scenario.tradersandbarbarians.RiversOfCatan;
import noelyap.setterforcatan.scenario.tradersandbarbarians.TheCaravans;
import org.apache.commons.lang3.Range;

public class Schemas {
  public static final Map<
          Scenario, Map<Range<Integer>, Tuple2<SpecificationImpl, SpecificationImpl>>>
      OFFICIAL_SCHEMAS =
          HashMap.of(
              BASE,
              HashMap.of(
                  Range.between(3, 4),
                  Tuple.of(Base.P3_P4_SPECIFICATION_IMPL, Base.P3_P4_FISHERMEN_SPECIFICATION_IMPL),
                  Range.between(5, 6),
                  Tuple.of(Base.P5_P6_SPECIFICATION_IMPL, Base.P5_P6_FISHERMEN_SPECIFICATION_IMPL),
                  Range.between(7, 8),
                  Tuple.of(Base.P7_P8_SPECIFICATION_IMPL, Base.P7_P8_FISHERMEN_SPECIFICATION_IMPL)),
              SEAFARERS_HEADING_FOR_NEW_SHORES,
              HashMap.of(
                  Range.is(3),
                  Tuple.of(
                      HeadingForNewShores.P3_SPECIFICATION_IMPL,
                      HeadingForNewShores.P3_FISHERMEN_SPECIFICATION_IMPL),
                  Range.is(4),
                  Tuple.of(
                      HeadingForNewShores.P4_SPECIFICATION_IMPL,
                      HeadingForNewShores.P4_FISHERMEN_SPECIFICATION_IMPL),
                  Range.between(5, 6),
                  Tuple.of(
                      HeadingForNewShores.P5_P6_SPECIFICATION_IMPL,
                      HeadingForNewShores.P5_P6_FISHERMEN_SPECIFICATION_IMPL),
                  Range.between(7, 8),
                  Tuple.of(
                      HeadingForNewShores.P7_P8_SPECIFICATION_IMPL,
                      HeadingForNewShores.P7_P8_FISHERMEN_SPECIFICATION_IMPL)),
              SEAFARERS_THE_N_ISLANDS,
              HashMap.of(
                  Range.is(3),
                  Tuple.of(
                      TheNIslands.P3_SPECIFICATION_IMPL,
                      TheNIslands.P3_FISHERMEN_SPECIFICATION_IMPL),
                  Range.is(4),
                  Tuple.of(
                      TheNIslands.P4_SPECIFICATION_IMPL,
                      TheNIslands.P4_FISHERMEN_SPECIFICATION_IMPL),
                  Range.between(5, 6),
                  Tuple.of(
                      TheNIslands.P5_P6_SPECIFICATION_IMPL,
                      TheNIslands.P5_P6_FISHERMEN_SPECIFICATION_IMPL),
                  Range.between(7, 8),
                  Tuple.of(
                      TheNIslands.P7_P8_SPECIFICATION_IMPL,
                      TheNIslands.P7_P8_FISHERMEN_SPECIFICATION_IMPL)),
              SEAFARERS_OCEANIA,
              HashMap.of(
                  Range.is(3),
                  Tuple.of(Oceania.P3_SPECIFICATION_IMPL, Oceania.P3_FISHERMEN_SPECIFICATION_IMPL),
                  Range.is(4),
                  Tuple.of(Oceania.P4_SPECIFICATION_IMPL, Oceania.P4_FISHERMEN_SPECIFICATION_IMPL),
                  Range.between(5, 6),
                  Tuple.of(
                      Oceania.P5_P6_SPECIFICATION_IMPL, Oceania.P5_P6_FISHERMEN_SPECIFICATION_IMPL),
                  Range.between(7, 8),
                  Tuple.of(
                      Oceania.P7_P8_SPECIFICATION_IMPL,
                      Oceania.P7_P8_FISHERMEN_SPECIFICATION_IMPL)),
              SEAFARERS_THROUGH_THE_DESERT,
              HashMap.of(
                  Range.is(3),
                  Tuple.of(
                      ThroughTheDesert.P3_SPECIFICATION_IMPL,
                      ThroughTheDesert.P3_FISHERMEN_SPECIFICATION_IMPL),
                  Range.is(4),
                  Tuple.of(
                      ThroughTheDesert.P4_SPECIFICATION_IMPL,
                      ThroughTheDesert.P4_FISHERMEN_SPECIFICATION_IMPL),
                  Range.between(5, 6),
                  Tuple.of(
                      ThroughTheDesert.P5_P6_SPECIFICATION_IMPL,
                      ThroughTheDesert.P5_P6_FISHERMEN_SPECIFICATION_IMPL),
                  Range.between(7, 8),
                  Tuple.of(
                      ThroughTheDesert.P7_P8_SPECIFICATION_IMPL,
                      ThroughTheDesert.P7_P8_FISHERMEN_SPECIFICATION_IMPL)),
              SEAFARERS_THE_FORGOTTEN_TRIBE,
              HashMap.of(
                  Range.between(3, 4),
                  Tuple.of(
                      TheForgottenTribe.P3_P4_SPECIFICATION_IMPL,
                      TheForgottenTribe.P3_P4_FISHERMEN_SPECIFICATION_IMPL),
                  Range.between(5, 6),
                  Tuple.of(
                      TheForgottenTribe.P5_P6_SPECIFICATION_IMPL,
                      TheForgottenTribe.P5_P6_FISHERMEN_SPECIFICATION_IMPL),
                  Range.between(7, 8),
                  Tuple.of(
                      TheForgottenTribe.P7_P8_SPECIFICATION_IMPL,
                      TheForgottenTribe.P7_P8_FISHERMEN_SPECIFICATION_IMPL)),
              SEAFARERS_CLOTH_FOR_CATAN,
              HashMap.of(
                  Range.between(3, 4),
                  Tuple.of(
                      ClothForCatan.P3_P4_SPECIFICATION_IMPL,
                      ClothForCatan.P3_P4_FISHERMEN_SPECIFICATION_IMPL),
                  Range.between(5, 6),
                  Tuple.of(
                      ClothForCatan.P5_P6_SPECIFICATION_IMPL,
                      ClothForCatan.P5_P6_FISHERMEN_SPECIFICATION_IMPL),
                  Range.between(7, 8),
                  Tuple.of(
                      ClothForCatan.P7_P8_SPECIFICATION_IMPL,
                      ClothForCatan.P7_P8_FISHERMEN_SPECIFICATION_IMPL)),
              SEAFARERS_WONDERS_OF_CATAN,
              HashMap.of(
                  Range.between(3, 4),
                  Tuple.of(
                      WondersOfCatan.P3_P4_SPECIFICATION_IMPL,
                      WondersOfCatan.P3_P4_FISHERMEN_SPECIFICATION_IMPL),
                  Range.between(5, 6),
                  Tuple.of(
                      WondersOfCatan.P5_P6_SPECIFICATION_IMPL,
                      WondersOfCatan.P5_P6_FISHERMEN_SPECIFICATION_IMPL),
                  Range.between(7, 8),
                  Tuple.of(
                      WondersOfCatan.P7_P8_SPECIFICATION_IMPL,
                      WondersOfCatan.P7_P8_FISHERMEN_SPECIFICATION_IMPL)),
              TRADERS_AND_BARBARIANS_RIVERS_OF_CATAN,
              HashMap.of(
                  Range.between(3, 4),
                  Tuple.of(
                      RiversOfCatan.P3_P4_SPECIFICATION_IMPL,
                      RiversOfCatan.P3_P4_FISHERMEN_SPECIFICATION_IMPL),
                  Range.between(5, 6),
                  Tuple.of(
                      RiversOfCatan.P5_P6_SPECIFICATION_IMPL,
                      RiversOfCatan.P5_P6_FISHERMEN_SPECIFICATION_IMPL),
                  Range.between(7, 8),
                  Tuple.of(
                      RiversOfCatan.P7_P8_SPECIFICATION_IMPL,
                      RiversOfCatan.P7_P8_FISHERMEN_SPECIFICATION_IMPL)),
              TRADERS_AND_BARBARIANS_THE_CARAVANS,
              HashMap.of(
                  Range.between(3, 4),
                  Tuple.of(
                      TheCaravans.P3_P4_SPECIFICATION_IMPL,
                      TheCaravans.P3_P4_FISHERMEN_SPECIFICATION_IMPL),
                  Range.between(5, 6),
                  Tuple.of(
                      TheCaravans.P5_P6_SPECIFICATION_IMPL,
                      TheCaravans.P5_P6_FISHERMEN_SPECIFICATION_IMPL),
                  Range.between(7, 8),
                  Tuple.of(
                      TheCaravans.P7_P8_SPECIFICATION_IMPL,
                      TheCaravans.P7_P8_FISHERMEN_SPECIFICATION_IMPL)));

  public static SpecificationImpl toSpecification(
      final Scenario scenario, final int playerCount, final boolean fishermenOfCatan) {
    if (scenario == Scenario.UNRECOGNIZED) {
      throw new InternalError(String.format("Unrecognized Scenario `%s`.", scenario.toString()));
    }

    final String scenarioName = scenario.getValueDescriptor().getName();

    final Map<Range<Integer>, Tuple2<SpecificationImpl, SpecificationImpl>> extensions =
        OFFICIAL_SCHEMAS
            .get(scenario)
            .getOrElseThrow(() ->
                    new IllegalArgumentException(
                        String.format("Scenario `%s` not yet implemented.", scenarioName)));
    final Tuple2<SpecificationImpl, SpecificationImpl> extensionSpecifications =
        extensions
            .find(t2 -> {
                  final Range<Integer> range = t2._1;

                  return range.contains(playerCount);
                })
            .getOrElseThrow(() ->
                    new IllegalArgumentException(
                        String.format(
                            "%d number of players specification not found for Scenario `%s`.",
                            playerCount, scenarioName)))
            ._2;

    return fishermenOfCatan ? extensionSpecifications._2 : extensionSpecifications._1;
  }
}
