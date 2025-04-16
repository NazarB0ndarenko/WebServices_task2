package lt.viko.eif.bondarenko.producer.repository;

import lt.viko.eif.bondarenko.producer.entety.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for {@link Item} entities.
 * Provides CRUD operations and custom query methods for interacting with the database.
 */
@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

    /**
     * Retrieves all items from the database.
     *
     * @return a list of all {@link Item} entities
     */
    @Query("SELECT i FROM Item i")
    List<Item> findAll();

}

