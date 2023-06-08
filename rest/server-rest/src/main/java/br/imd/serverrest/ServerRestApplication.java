package br.imd.serverrest;

import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ServerRestApplication {

  public static void main(String[] args) {
    SpringApplication.run(ServerRestApplication.class, args);
  }

  @GetMapping("/game-day")
  public Map<String, String> gameDay() {
    return Map.of("date", "27/05/2001");
  }
}
