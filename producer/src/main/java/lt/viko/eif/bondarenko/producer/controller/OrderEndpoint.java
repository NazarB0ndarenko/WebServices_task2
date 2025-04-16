package lt.viko.eif.bondarenko.producer.controller;

import lombok.RequiredArgsConstructor;
import lt.viko.eif.bondarenko.producer.service.OrderService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import task2.shop.CreateOrderRequest;
import task2.shop.CreateOrderResponse;

/**
 * SOAP Web Service Endpoint for handling order-related operations.
 * This class exposes a method to create new orders via SOAP requests.
 */
@Endpoint
@RequiredArgsConstructor
public class OrderEndpoint {

    /**
     * The namespace URI used for SOAP communication.
     */
    private static final String NAMESPACE_URI = "http://task2/shop";

    /**
     * Service responsible for business logic related to orders.
     */
    private final OrderService orderService;

    /**
     * Handles the SOAP request to create a new order.
     *
     * @param request the incoming {@link CreateOrderRequest} containing order details
     * @return a {@link CreateOrderResponse} with the ID of the newly created order
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createOrderRequest")
    @ResponsePayload
    public CreateOrderResponse createOrder(@RequestPayload CreateOrderRequest request) {
        Long id = orderService.createOrder(request.getCreateOrderDto());
        CreateOrderResponse response = new CreateOrderResponse();
        response.setId(id);
        return response;
    }

}

