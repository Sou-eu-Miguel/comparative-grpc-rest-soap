package br.imd.servergrpc;

import br.imd.servergrpc.proto.DateRequest;
import br.imd.servergrpc.proto.DateResponse;
import br.imd.servergrpc.proto.GameDayServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Component;

@Component
public class GameDayImpl extends GameDayServiceGrpc.GameDayServiceImplBase {

  @Override
  public void getGameDay(DateRequest request, StreamObserver<DateResponse> responseObserver) {
    final var response = DateResponse.newBuilder()
        .setDate("27/05/2001")
        .build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }
}
