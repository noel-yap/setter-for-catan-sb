package noelyap.setterforcatan.board;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.is;

import com.google.common.annotations.VisibleForTesting;
import io.grpc.stub.StreamObserver;
import io.vavr.collection.HashSet;
import io.vavr.control.Try;
import net.devh.boot.grpc.server.service.GrpcService;
import noelyap.setterforcatan.board.protogen.BoardServiceGrpc.BoardServiceImplBase;
import noelyap.setterforcatan.board.protogen.GenerateBoardRequest;
import noelyap.setterforcatan.board.protogen.GenerateBoardResponse;
import noelyap.setterforcatan.component.BoardGenerator;
import noelyap.setterforcatan.component.Specification;
import noelyap.setterforcatan.grader.CompositeGrader;
import noelyap.setterforcatan.grader.DispersedSixesAndEightsGrader;
import noelyap.setterforcatan.grader.DispersedTwosAndTwelvesGrader;
import noelyap.setterforcatan.grader.PassThroughGrader;
import noelyap.setterforcatan.grader.UniformOddsGrader;
import noelyap.setterforcatan.protogen.BoardOuterClass.Board;
import noelyap.setterforcatan.protogen.GraderStrategyOuterClass.GraderStrategy;
import noelyap.setterforcatan.protogen.SchemaOuterClass.Schema;

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

    final Try<Board> board =
        Try.of(() ->
                Match(request.getArgCase())
                    .of(
                        Case(
                            $(is(GenerateBoardRequest.ArgCase.SCHEMA)),
                            () -> newBoard(request.getSchema(), compositeGrader)),
                        Case(
                            $(is(GenerateBoardRequest.ArgCase.SPECIFICATION)),
                            () ->
                                newBoard(
                                    new Specification(request.getSpecification()),
                                    compositeGrader)),
                        Case(
                            $(),
                            () -> {
                              throw new IllegalArgumentException(
                                  "Must specify `schema` or `specification` in the request.");
                            })));

    final GenerateBoardResponse response = newResponse(board);

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  private static Board newBoard(final Schema schema, final CompositeGrader compositeGrader) {
    final Specification specification =
        noelyap.setterforcatan.util.SchemaUtils.toSpecification(
            schema.getScenario(), schema.getPlayerCount(), schema.getFishermenOfCatan());

    return newBoard(specification, compositeGrader);
  }

  private static Board newBoard(
      final Specification specification, final CompositeGrader compositeGrader) {
    final BoardGenerator boardGenerator = new BoardGenerator(specification, compositeGrader);

    return boardGenerator.generateBoard();
  }

  @VisibleForTesting
  static GenerateBoardResponse newResponse(final Try<Board> board) {
    final GenerateBoardResponse.Builder generateBoardResponseBuilder =
        GenerateBoardResponse.newBuilder();

    if (board.isSuccess()) {
      generateBoardResponseBuilder.setBoard(board.get());
    } else {
      generateBoardResponseBuilder.setErrorMessage(board.getCause().getMessage());
    }

    return generateBoardResponseBuilder.build();
  }
}