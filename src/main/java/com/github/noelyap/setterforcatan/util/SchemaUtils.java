package com.github.noelyap.setterforcatan.util;

import com.github.noelyap.setterforcatan.component.Specification;
import com.github.noelyap.setterforcatan.component.scenario.Base;
import com.github.noelyap.setterforcatan.protogen.ScenarioOuterClass.Scenario;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import org.apache.commons.lang3.Range;

public class SchemaUtils {
  public static Specification toSpecification(
      final com.github.noelyap.setterforcatan.protogen.ScenarioOuterClass.Scenario scenario,
      final int playerCount,
      final boolean fishermenOfCatan) {
    if (scenario == Scenario.UNRECOGNIZED) {
      throw new InternalError(String.format("Unrecognized Scenario `%s`.", scenario.toString()));
    }

    final String scenarioName = scenario.getValueDescriptor().getName();

    final Map<Range<Integer>, Tuple2<Specification, Specification>> extensions =
        OFFICIAL_SCHEMAS
            .get(scenario)
            .getOrElseThrow(() ->
                    new IllegalArgumentException(
                        String.format("Scenario `%s` not yet implemented.", scenarioName)));
    final Tuple2<Specification, Specification> extensionSpecifications =
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

  private static final Map<Scenario, Map<Range<Integer>, Tuple2<Specification, Specification>>>
      OFFICIAL_SCHEMAS =
          HashMap.of(
              Scenario.BASE,
              HashMap.of(
                  Range.between(3, 4),
                  Tuple.of(Base.P3_P4_SPECIFICATION, Base.P3_P4_FISHERMEN_SPECIFICATION)));
}
