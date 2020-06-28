package com.github.noelyap.setterforcatan.board;

import com.github.noelyap.setterforcatan.board.protogen.BoardServiceGrpc.BoardServiceImplBase;
import com.github.noelyap.setterforcatan.board.protogen.GenerateBoardRequest;
import com.github.noelyap.setterforcatan.board.protogen.GenerateBoardResponse;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class BoardServiceImpl extends BoardServiceImplBase {
  @Override
  public void generateBoard(
      final GenerateBoardRequest request,
      final StreamObserver<GenerateBoardResponse> responseObserver) {
    final GenerateBoardResponse response = GenerateBoardResponse.newBuilder()
        .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }
}
