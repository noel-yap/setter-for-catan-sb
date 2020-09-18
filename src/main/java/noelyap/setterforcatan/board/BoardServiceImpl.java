package noelyap.setterforcatan.board;

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
                .map(g ->
                        switch (g) {
                          case UNIFORM_ODDS_DISTRIBUTION -> new UniformOddsGrader();
                          case DISPERSED_SIXES_AND_EIGHTS_DISTRIBUTION -> new DispersedSixesAndEightsGrader();
                          case DISPERSED_TWOS_AND_TWELVES_DISTRIBUTION -> new DispersedTwosAndTwelvesGrader();
                          default -> new PassThroughGrader();
                        }));

    final Try<Tuple2<Specification, Board>> result =
        Try.of(() ->
                switch (request.getArgCase()) {
                  case SCHEMA -> newBoard(request.getSchema(), compositeGrader);
                  case SPECIFICATION -> newBoard(request.getSpecification(), compositeGrader);
                  default -> throw new IllegalArgumentException(
                      "Must specify `schema` or `specification` in the request.");
                });

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
