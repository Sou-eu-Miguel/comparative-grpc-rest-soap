package br.imd.servergrpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;
import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerGrpcApplication {

  public static void main(String[] args) {
    final var context = SpringApplication.run(ServerGrpcApplication.class, args);
    final var server = context.getBean(Server.class);
    try {
      server.start();
      server.awaitTermination();
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Bean
  public Server startGrpcServer(GameDayImpl gameDay) {
    return ServerBuilder.forPort(9090)
        .addService(gameDay)
        .addService(ProtoReflectionService.newInstance())
        .build();
  }
}

