package noelyap.setterforcatan.util;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import noelyap.setterforcatan.component.SpecificationImpl;
import noelyap.setterforcatan.component.scenario.Base;
import noelyap.setterforcatan.component.scenario.seafarers.HeadingForNewShores;
import noelyap.setterforcatan.component.scenario.seafarers.TheNIslands;
import noelyap.setterforcatan.protogen.ScenarioOuterClass.Scenario;
import org.apache.commons.lang3.Range;

public class SchemaUtils {
  public static SpecificationImpl toSpecification(
      final noelyap.setterforcatan.protogen.ScenarioOuterClass.Scenario scenario,
      final int playerCount,
      final boolean fishermenOfCatan) {
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

  private static final Map<
          Scenario, Map<Range<Integer>, Tuple2<SpecificationImpl, SpecificationImpl>>>
      OFFICIAL_SCHEMAS =
          HashMap.of(
              Scenario.BASE,
              HashMap.of(
                  Range.between(3, 4),
                  Tuple.of(Base.P3_P4_SPECIFICATION_IMPL, Base.P3_P4_FISHERMEN_SPECIFICATION_IMPL),
                  Range.between(5, 6),
                  Tuple.of(Base.P5_P6_SPECIFICATION_IMPL, Base.P5_P6_FISHERMEN_SPECIFICATION_IMPL),
                  Range.between(7, 8),
                  Tuple.of(Base.P7_P8_SPECIFICATION_IMPL, Base.P7_P8_FISHERMEN_SPECIFICATION_IMPL)),
              Scenario.SEAFARERS_HEADING_FOR_NEW_SHORES,
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
              Scenario.SEAFARERS_THE_N_ISLANDS,
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
                      TheNIslands.P7_P8_FISHERMEN_SPECIFICATION_IMPL)));
}
