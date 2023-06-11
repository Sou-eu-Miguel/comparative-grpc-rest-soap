package br.imd.clientsoap;

import gameDay.wsdl.GetGameResponse;
import gameDay.wsdl.GetRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class SoapClient extends WebServiceGatewaySupport {

  private static final Logger logger = LogManager.getLogger(SoapClient.class);

  public void findGameDay() {
    final var request = new GetRequest();
    final var response =
        (GetGameResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    logger.info(response.getDate());
  }
}
