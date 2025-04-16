package lt.viko.eif.bondarenko.consumer.proxy;

import lombok.extern.slf4j.Slf4j;
import lt.viko.eif.bondarenko.consumer.wsdl.CreateOrderDto;
import lt.viko.eif.bondarenko.consumer.wsdl.CreateOrderRequest;
import lt.viko.eif.bondarenko.consumer.wsdl.CreateOrderResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

/**
 * Proxy class for interacting with the remote SOAP web service to create an order.
 * This class sends a {@link CreateOrderRequest} to the specified web service and receives a {@link CreateOrderResponse}.
 */
@Slf4j
public class OrderProxy extends WebServiceGatewaySupport {

    /**
     * Sends a SOAP request to create an order on the remote service and returns the response.
     *
     * @param createOrderDto the {@link CreateOrderDto} containing order details
     * @return a {@link CreateOrderResponse} with the details of the newly created order
     */
    public CreateOrderResponse createOrder(CreateOrderDto createOrderDto) {
        CreateOrderRequest request = new CreateOrderRequest();
        request.setCreateOrderDto(createOrderDto);

        log.info("Requesting order creation");

        return (CreateOrderResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/createOrder", request,
                        new SoapActionCallback("http://task2/shop"));
    }
}

