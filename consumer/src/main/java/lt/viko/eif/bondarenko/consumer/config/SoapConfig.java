package lt.viko.eif.bondarenko.consumer.config;

import lt.viko.eif.bondarenko.consumer.proxy.ItemProxy;
import lt.viko.eif.bondarenko.consumer.proxy.OrderProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;


/**
 * Configuration class responsible for setting up SOAP clients for interacting with remote SOAP web services.
 * This configuration creates beans for the necessary marshaller and SOAP client proxies.
 */
@Configuration
public class SoapConfig {

    /**
     * Creates a JAXB2 marshaller to convert Java objects to XML and vice versa.
     * The marshaller is used for serializing and deserializing requests and responses.
     *
     * @return a configured {@link Jaxb2Marshaller} instance
     */
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

        marshaller.setContextPath("lt.viko.eif.bondarenko.consumer.wsdl");
        return marshaller;
    }

    /**
     * Creates a SOAP client proxy for interacting with the item-related SOAP service.
     * This client will use the provided {@link Jaxb2Marshaller} for request/response marshalling.
     *
     * @param marshaller the {@link Jaxb2Marshaller} used for marshalling/unmarshalling SOAP messages
     * @return a configured {@link ItemProxy} SOAP client
     */
    @Bean
    public ItemProxy countryClient(Jaxb2Marshaller marshaller) {
        ItemProxy client = new ItemProxy();
        client.setDefaultUri("http://localhost:8080/ws");  // Set the service URI
        client.setMarshaller(marshaller);  // Set the marshaller
        client.setUnmarshaller(marshaller);  // Set the unmarshaller
        return client;
    }

    /**
     * Creates a SOAP client proxy for interacting with the order-related SOAP service.
     * This client will use the provided {@link Jaxb2Marshaller} for request/response marshalling.
     *
     * @param marshaller the {@link Jaxb2Marshaller} used for marshalling/unmarshalling SOAP messages
     * @return a configured {@link OrderProxy} SOAP client
     */
    @Bean
    public OrderProxy orderClient(Jaxb2Marshaller marshaller) {
        OrderProxy client = new OrderProxy();
        client.setDefaultUri("http://localhost:8080/ws");  // Set the service URI
        client.setMarshaller(marshaller);  // Set the marshaller
        client.setUnmarshaller(marshaller);  // Set the unmarshaller
        return client;
    }
}

