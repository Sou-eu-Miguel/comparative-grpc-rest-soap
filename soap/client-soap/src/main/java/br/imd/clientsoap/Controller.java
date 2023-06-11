package br.imd.clientsoap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

  private final SoapClient soapClient;

  public Controller(SoapClient soapClient) {this.soapClient = soapClient;}

  @GetMapping("/")
  public void getResource() {
    soapClient.findGameDay();
  }
}
