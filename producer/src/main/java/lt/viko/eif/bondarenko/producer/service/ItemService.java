package lt.viko.eif.bondarenko.producer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.viko.eif.bondarenko.producer.entety.Item;
import lt.viko.eif.bondarenko.producer.repository.ItemRepository;
import org.springframework.stereotype.Service;
import task2.shop.CreateItemDto;

import java.util.List;


/**
 * Service class that handles business logic related to {@link Item} entities.
 * Provides methods for retrieving and creating items.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ItemService {

    /**
     * Repository used to interact with the item data in the database.
     */
    private final ItemRepository itemRepository;

    /**
     * Retrieves all items from the database.
     *
     * @return a list of all {@link Item} entities
     */
    public List<Item> getAllItems() {
        log.info("Getting all items");
        return itemRepository.findAll();
    }

    /**
     * Creates a new item based on the provided request data.
     *
     * @param request the {@link CreateItemDto} containing item details
     * @return the ID of the newly created item
     */
    public Long createItem(CreateItemDto request) {
        log.info("Creating item {}", request.getName());

        Item item = new Item(
                request.getName(),
                request.getPrice(),
                request.getDescription()
        );

        itemRepository.save(item);

        log.info("Item {} created", item.getId());

        return item.getId();
    }
}

