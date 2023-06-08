package br.imd.serversoap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.xmlsoap.schemas.soap.GetGameResponse;

@Endpoint
@SpringBootApplication
public class ServerSoapApplication {
  private static final String NAMESPACE_URI = "http://schemas.xmlsoap.org/soap/";

  public static void main(String[] args) {
    SpringApplication.run(ServerSoapApplication.class, args);
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetRequest")
  @ResponsePayload
  public GetGameResponse gameDay() {
    final var response = new GetGameResponse();
    response.setDate("27/05/2001");
    return response;
  }
}
