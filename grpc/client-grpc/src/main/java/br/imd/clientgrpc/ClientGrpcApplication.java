package br.imd.clientgrpc;

import br.imd.servergrpc.proto.GameDayServiceGrpc;
import io.grpc.netty.NettyChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClientGrpcApplication {

  public static void main(String[] args) {
    SpringApplication.run(ClientGrpcApplication.class, args);
  }

  @Bean
  public GameDayServiceGrpc.GameDayServiceBlockingStub stup(@Value("${server.host}") String host) {
    final var channel = NettyChannelBuilder.forAddress(host, 5050)
        .usePlaintext()
        .build();
    return GameDayServiceGrpc.newBlockingStub(channel);
  }
}
