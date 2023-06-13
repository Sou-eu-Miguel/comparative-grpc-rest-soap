package br.imd.servergrpc;

import br.imd.servergrpc.proto.GameDayServiceGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;
import io.grpc.protobuf.services.ProtoReflectionService;
import io.prometheus.client.CollectorRegistry;
import java.io.IOException;
import me.dinowernli.grpc.prometheus.Configuration;
import me.dinowernli.grpc.prometheus.MonitoringServerInterceptor;
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
  public Server startGrpcServer(GameDayImpl gameDay, CollectorRegistry collectorRegistry) {
    final var monitoringInterceptor = MonitoringServerInterceptor.create(Configuration.cheapMetricsOnly()
        .withCollectorRegistry(collectorRegistry));

    return ServerBuilder.forPort(5050)
        .addService(ServerInterceptors.intercept(
            GameDayServiceGrpc.bindService(gameDay), monitoringInterceptor))
        .addService(ProtoReflectionService.newInstance())
        .build();
  }
}

