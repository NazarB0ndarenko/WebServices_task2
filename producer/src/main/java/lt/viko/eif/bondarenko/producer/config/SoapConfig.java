package lt.viko.eif.bondarenko.producer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * Configuration class for setting up SOAP web services.
 * This class enables Spring Web Services and defines beans
 * necessary for handling SOAP requests and WSDL generation.
 */
@EnableWs
@Configuration
public class SoapConfig extends WsConfigurerAdapter {

    /**
     * Registers the {@link MessageDispatcherServlet} with the application context.
     * This servlet is responsible for dispatching incoming SOAP messages to appropriate endpoints.
     *
     * @param applicationContext the Spring application context
     * @return ServletRegistrationBean for the MessageDispatcherServlet mapped to /ws/*
     */
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    /**
     * Defines the default WSDL definition for the SOAP service.
     * It maps the schema and namespace used by the service.
     *
     * @param shopSchema the XSD schema for the SOAP service
     * @return the configured DefaultWsdl11Definition
     */
    @Bean(name = "shop")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema shopSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("ShopPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://task2/shop");
        wsdl11Definition.setSchema(shopSchema);
        return wsdl11Definition;
    }

    /**
     * Loads the XML schema used by the SOAP service from the classpath.
     *
     * @return the XsdSchema loaded from communication.xsd
     */
    @Bean
    public XsdSchema shopSchema() {
        return new SimpleXsdSchema(new ClassPathResource("communication.xsd"));
    }

}

