package lt.viko.eif.bondarenko.consumer.proxy;

import lombok.extern.slf4j.Slf4j;
import lt.viko.eif.bondarenko.consumer.wsdl.CreateItemDto;
import lt.viko.eif.bondarenko.consumer.wsdl.CreateItemRequest;
import lt.viko.eif.bondarenko.consumer.wsdl.CreateItemResponse;
import lt.viko.eif.bondarenko.consumer.wsdl.GetAllItemsRequest;
import lt.viko.eif.bondarenko.consumer.wsdl.GetAllItemsResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;


/**
 * Proxy class for interacting with the remote SOAP web service to retrieve and create items.
 * This class sends {@link GetAllItemsRequest} and {@link CreateItemRequest} to the specified web service and receives their respective responses.
 */
@Slf4j
public class ItemProxy extends WebServiceGatewaySupport {

    /**
     * Sends a SOAP request to retrieve all items from the remote service.
     *
     * @return a {@link GetAllItemsResponse} containing the list of all items
     */
    public GetAllItemsResponse getAllItems() {
        // Create the request object for retrieving all items
        GetAllItemsRequest request = new GetAllItemsRequest();

        // Log the action for tracking
        log.info("Requesting all items");

        // Send the request and receive the response using WebServiceTemplate
        return (GetAllItemsResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/getAllItems", request,
                        new SoapActionCallback("http://task2/shop"));
    }

    /**
     * Sends a SOAP request to create a new item on the remote service.
     *
     * @param createItemDto the {@link CreateItemDto} containing the item details
     * @return a {@link CreateItemResponse} with the details of the newly created item
     */
    public CreateItemResponse createItem(CreateItemDto createItemDto) {
        CreateItemRequest request = new CreateItemRequest();
        request.setCreateItemDto(createItemDto);

        log.info("Requesting item creation");

        return (CreateItemResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/createItem", request,
                        new SoapActionCallback("http://task2/shop"));
    }
}

