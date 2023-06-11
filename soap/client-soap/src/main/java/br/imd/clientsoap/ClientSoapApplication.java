package br.imd.clientsoap;

import java.util.Map;
import javax.xml.bind.Marshaller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@SpringBootApplication
public class ClientSoapApplication {

  public static void main(String[] args) {
    SpringApplication.run(ClientSoapApplication.class, args);
  }

  @Bean
  public Jaxb2Marshaller marshaller() {
    final var marshaller = new Jaxb2Marshaller();

    marshaller.setMarshallerProperties(Map.of(Marshaller.JAXB_FORMATTED_OUTPUT, true));
    marshaller.setContextPath("gameDay.wsdl");
    return marshaller;
  }

  @Bean
  public SoapClient soapClient(Jaxb2Marshaller marshaller,
                               @Value("${wsdl.domain}") String defaultUrl) {
    final var client = new SoapClient();
    client.setDefaultUri(defaultUrl);
    client.setMarshaller(marshaller);
    client.setUnmarshaller(marshaller);
    return client;
  }
}
