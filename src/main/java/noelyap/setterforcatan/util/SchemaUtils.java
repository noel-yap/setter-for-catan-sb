package noelyap.setterforcatan.util;

import io.vavr.Tuple2;
import io.vavr.collection.Map;
import noelyap.setterforcatan.component.Schemas;
import noelyap.setterforcatan.component.SpecificationImpl;
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
        Schemas.OFFICIAL_SCHEMAS
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
