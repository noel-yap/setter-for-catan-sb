package noelyap.setterforcatan.service.board;

import com.google.protobuf.util.JsonFormat;
import io.grpc.stub.StreamObserver;
import io.vavr.control.Try;
import java.util.concurrent.atomic.AtomicReference;
import lombok.extern.slf4j.Slf4j;
import noelyap.setterforcatan.service.board.protogen.GenerateBoardRequest;
import noelyap.setterforcatan.service.board.protogen.GenerateBoardResponse;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BoardRestService {
  public GenerateBoardResponse generateBoard(final GenerateBoardRequest request) {
    return Try.of(() -> {
              final var generateBoardResponse = new AtomicReference<GenerateBoardResponse>();

              log.info("Generating board from " + JsonFormat.printer().print(request));

              new BoardServiceImpl()
                  .generateBoard(
                      request,
                      new StreamObserver<>() {
                        final GenerateBoardResponse.Builder generateBoardResponseBuilder =
                            GenerateBoardResponse.newBuilder();

                        @Override
                        public void onNext(final GenerateBoardResponse value) {
                          generateBoardResponseBuilder.mergeFrom(value);
                        }

                        @Override
                        public void onError(final Throwable t) {
                          log.error(t.getMessage(), t);
                        }

                        @Override
                        public void onCompleted() {
                          generateBoardResponse.set(generateBoardResponseBuilder.build());
                        }
                      });

              return generateBoardResponse.get();
            })
        .onFailure(e -> log.error(e.getMessage(), e))
        .get();
  }
}
