package br.imd.clientrest;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@SpringBootApplication
public class ClientRestApplication {
  private static final Logger logger = LogManager.getLogger(ClientRestApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(ClientRestApplication.class, args);
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplateBuilder().build();
  }

  @GetMapping("/")
  public void getResource(RestTemplate restTemplate) {
    final var gameDay = restTemplate.getForEntity("http://server-rest:8081/game-day", JsonNode.class).getBody();
    logger.info(gameDay);
  }
}
