package lt.viko.eif.bondarenko.producer.controller;

import lombok.RequiredArgsConstructor;
import lt.viko.eif.bondarenko.producer.service.ItemService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import task2.shop.CreateItemRequest;
import task2.shop.CreateItemResponse;
import task2.shop.GetAllItemsRequest;
import task2.shop.GetAllItemsResponse;
import task2.shop.GetItemDto;


/**
 * SOAP Web Service Endpoint for handling item-related operations.
 * This endpoint provides methods for retrieving all items and creating new items.
 */
@Endpoint
@RequiredArgsConstructor
public class ItemEndpoint {

    /**
     * The namespace URI used in SOAP requests and responses.
     */
    private static final String NAMESPACE_URI = "http://task2/shop";

    /**
     * Service for handling item business logic.
     */
    private final ItemService itemService;

    /**
     * Handles the SOAP request to retrieve all items.
     *
     * @param request the incoming {@link GetAllItemsRequest}
     * @return a {@link GetAllItemsResponse} containing a list of all items
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllItemsRequest")
    @ResponsePayload
    public GetAllItemsResponse getAllItems(@RequestPayload GetAllItemsRequest request) {
        GetAllItemsResponse response = new GetAllItemsResponse();

        itemService.getAllItems()
                .forEach(item -> {
                    GetItemDto itemResponse = new GetItemDto();
                    itemResponse.setId(item.getId());
                    itemResponse.setName(item.getName());
                    itemResponse.setPrice(item.getPrice());
                    itemResponse.setDescription(item.getDescription());

                    response.getGetItemDto().add(itemResponse);
                });

        return response;
    }

    /**
     * Handles the SOAP request to create a new item.
     *
     * @param request the incoming {@link CreateItemRequest} containing item details
     * @return a {@link CreateItemResponse} with the ID of the newly created item
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createItemRequest")
    @ResponsePayload
    public CreateItemResponse createItem(@RequestPayload CreateItemRequest request){
        CreateItemResponse response = new CreateItemResponse();
        Long id = itemService.createItem(request.getCreateItemDto());

        response.setId(id);
        return response;
    }
}

