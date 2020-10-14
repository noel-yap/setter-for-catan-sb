package noelyap.setterforcatan.service.board;

import com.google.common.annotations.VisibleForTesting;
import io.grpc.stub.StreamObserver;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.HashSet;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import noelyap.setterforcatan.component.Schemas;
import noelyap.setterforcatan.component.SpecificationImpl;
import noelyap.setterforcatan.generator.BoardGenerator;
import noelyap.setterforcatan.grader.CompositeGrader;
import noelyap.setterforcatan.grader.DispersedSixesAndEightsGrader;
import noelyap.setterforcatan.grader.DispersedTwosAndTwelvesGrader;
import noelyap.setterforcatan.grader.PassThroughGrader;
import noelyap.setterforcatan.grader.UniformOddsGrader;
import noelyap.setterforcatan.protogen.BoardOuterClass.Board;
import noelyap.setterforcatan.protogen.SchemaOuterClass.Schema;
import noelyap.setterforcatan.protogen.SpecificationOuterClass.Specification;
import noelyap.setterforcatan.service.board.protogen.BoardServiceGrpc.BoardServiceImplBase;
import noelyap.setterforcatan.service.board.protogen.GenerateBoardRequest;
import noelyap.setterforcatan.service.board.protogen.GenerateBoardResponse;

@GrpcService
@Slf4j
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
        Schemas.toSpecification(
            schema.getScenario(), schema.getPlayerCount(), schema.getFishermenOfCatan());

    log.info("Generating board with " + specificationImpl.toProto());

    return newBoard(specificationImpl, compositeGrader);
  }

  private static Tuple2<Specification, Board> newBoard(
      final SpecificationImpl specificationImpl, final CompositeGrader compositeGrader) {
    final BoardGenerator boardGenerator = new BoardGenerator(specificationImpl, compositeGrader);

    final Board board = boardGenerator.generateBoard();

    log.info("Generated board is " + board);

    return Tuple.of(specificationImpl.toProto(), board);
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
