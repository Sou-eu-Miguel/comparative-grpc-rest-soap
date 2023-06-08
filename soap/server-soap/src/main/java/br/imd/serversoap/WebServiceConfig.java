package br.imd.serversoap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.soap.SoapMessageFactory;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

  @Bean
  public SoapMessageFactory messageFactory() {
    return new SaajSoapMessageFactory();
  }

  @Bean
  public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
      ApplicationContext applicationContext) {
    final var servlet = new MessageDispatcherServlet();
    servlet.setApplicationContext(applicationContext);
    servlet.setTransformWsdlLocations(true);
    return new ServletRegistrationBean<>(servlet, "/ws/*");
  }

  @Bean(name = "gameDay")
  public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema xsdSchema) {
    final var wsdl11Definition = new DefaultWsdl11Definition();
    wsdl11Definition.setPortTypeName("GameDayPort");
    wsdl11Definition.setLocationUri("/ws");
    wsdl11Definition.setTargetNamespace("http://schemas.xmlsoap.org/soap");
    wsdl11Definition.setSchema(xsdSchema);
    return wsdl11Definition;
  }

  @Bean
  public XsdSchema xsdSchema() {
    return new SimpleXsdSchema(new ClassPathResource("game.xsd"));
  }
}
