package com.github.noelyap.setterforcatan.board;

import java.util.concurrent.atomic.AtomicReference;

import com.github.noelyap.setterforcatan.board.protogen.GenerateBoardRequest;
import com.github.noelyap.setterforcatan.board.protogen.GenerateBoardResponse;
import com.google.protobuf.util.JsonFormat;

import org.springframework.stereotype.Service;

import io.grpc.stub.StreamObserver;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RestBoardService {
  public GenerateBoardResponse generateBoard(final GenerateBoardRequest request) {
    return Try.of(() -> {
      final AtomicReference<GenerateBoardResponse> generateBoardResponse = new AtomicReference();

      log.info("Generating board from " + JsonFormat.printer().print(request));

      new BoardServiceImpl().generateBoard(
          request,
          new StreamObserver<GenerateBoardResponse>() {
            final GenerateBoardResponse.Builder generateBoardResponseBuilder = GenerateBoardResponse.newBuilder();

            @Override
            public void onNext(GenerateBoardResponse value) {
              generateBoardResponseBuilder.mergeFrom(value);
            }

            @Override
            public void onError(Throwable t) {
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
