package noelyap.setterforcatan.board;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.is;
import static noelyap.setterforcatan.board.protogen.GenerateBoardRequest.*;
import static noelyap.setterforcatan.board.protogen.GenerateBoardRequest.ArgCase.*;

import com.google.common.annotations.VisibleForTesting;
import io.grpc.stub.StreamObserver;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.HashSet;
import io.vavr.control.Try;
import net.devh.boot.grpc.server.service.GrpcService;
import noelyap.setterforcatan.board.protogen.BoardServiceGrpc.BoardServiceImplBase;
import noelyap.setterforcatan.board.protogen.GenerateBoardRequest;
import noelyap.setterforcatan.board.protogen.GenerateBoardResponse;
import noelyap.setterforcatan.component.BoardGenerator;
import noelyap.setterforcatan.component.SpecificationImpl;
import noelyap.setterforcatan.grader.CompositeGrader;
import noelyap.setterforcatan.grader.DispersedSixesAndEightsGrader;
import noelyap.setterforcatan.grader.DispersedTwosAndTwelvesGrader;
import noelyap.setterforcatan.grader.PassThroughGrader;
import noelyap.setterforcatan.grader.UniformOddsGrader;
import noelyap.setterforcatan.protogen.BoardOuterClass.Board;
import noelyap.setterforcatan.protogen.GraderStrategyOuterClass.GraderStrategy;
import noelyap.setterforcatan.protogen.SchemaOuterClass.Schema;
import noelyap.setterforcatan.protogen.SpecificationOuterClass.Specification;

@GrpcService
public class BoardServiceImpl extends BoardServiceImplBase {
  @Override
  public void generateBoard(
      final GenerateBoardRequest request,
      final StreamObserver<GenerateBoardResponse> responseObserver) {
    final CompositeGrader compositeGrader =
        CompositeGrader.ofAll(
            HashSet.ofAll(request.getGradersList())
                .map(g -> {
                      return Match(g)
                          .of(
                              Case(
                                  $(is(GraderStrategy.UNIFORM_ODDS_DISTRIBUTION)),
                                  new UniformOddsGrader()),
                              Case(
                                  $(is(GraderStrategy.DISPERSED_SIXES_AND_EIGHTS_DISTRIBUTION)),
                                  new DispersedSixesAndEightsGrader()),
                              Case(
                                  $(is(GraderStrategy.DISPERSED_TWOS_AND_TWELVES_DISTRIBUTION)),
                                  new DispersedTwosAndTwelvesGrader()),
                              Case($(), new PassThroughGrader()));
                    }));

    final Try<Tuple2<Specification, Board>> result =
        Try.of(() ->
                Match(request.getArgCase())
                    .of(Case($(is(SCHEMA)), () -> newBoard(request.getSchema(), compositeGrader)),
                        Case(
                            $(is(SPECIFICATION)),
                            () -> newBoard(request.getSpecification(), compositeGrader)),
                        Case(
                            $(),
                            () -> {
                              throw new IllegalArgumentException(
                                  "Must specify `schema` or `specification` in the request.");
                            })));

    final GenerateBoardResponse response = newResponse(result);

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  private static Tuple2<Specification, Board> newBoard(
      final Schema schema, final CompositeGrader compositeGrader) {
    final SpecificationImpl specificationImpl =
        noelyap.setterforcatan.util.SchemaUtils.toSpecification(
            schema.getScenario(), schema.getPlayerCount(), schema.getFishermenOfCatan());

    return newBoard(specificationImpl.toProto(), compositeGrader);
  }

  private static Tuple2<Specification, Board> newBoard(
      final Specification specification, final CompositeGrader compositeGrader) {
    final BoardGenerator boardGenerator =
        new BoardGenerator(SpecificationImpl.newBuilder(specification).build(), compositeGrader);

    return Tuple.of(specification, boardGenerator.generateBoard());
  }

  @VisibleForTesting
  static GenerateBoardResponse newResponse(final Try<Tuple2<Specification, Board>> response) {
    final GenerateBoardResponse.Builder generateBoardResponseBuilder =
        GenerateBoardResponse.newBuilder();

    if (response.isSuccess()) {
      generateBoardResponseBuilder.setSuccess(
          GenerateBoardResponse.Success.newBuilder()
              .setSpecification(response.get()._1)
              .setBoard(response.get()._2)
              .build());
    } else {
      generateBoardResponseBuilder.setFailure(
          GenerateBoardResponse.Failure.newBuilder()
              .setErrorMessage(response.getCause().getMessage())
              .build());
    }

    return generateBoardResponseBuilder.build();
  }
}
