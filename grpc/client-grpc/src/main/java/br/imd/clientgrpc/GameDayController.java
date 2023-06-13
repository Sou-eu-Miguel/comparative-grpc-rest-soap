package br.imd.clientgrpc;

import br.imd.servergrpc.proto.DateRequest;
import br.imd.servergrpc.proto.GameDayServiceGrpc;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameDayController {
  private static final Logger logger = LogManager.getLogger(GameDayController.class);

  private final GameDayServiceGrpc.GameDayServiceBlockingStub gameDayServiceBlockingStub;

  public GameDayController(GameDayServiceGrpc.GameDayServiceBlockingStub gameDayServiceBlockingStub) {
    this.gameDayServiceBlockingStub = gameDayServiceBlockingStub;
  }

  @GetMapping("/")
  public void getResource(@Value("${server.host}") String host) {
    final var gameDayFuture = gameDayServiceBlockingStub.getGameDay(DateRequest.newBuilder().build());

    logger.info(gameDayFuture.getDate());
  }
}
