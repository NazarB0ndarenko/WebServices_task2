package lt.viko.eif.bondarenko.producer.repository;

import lt.viko.eif.bondarenko.producer.entety.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for {@link Order} entities.
 * Provides basic CRUD operations for managing orders in the database.
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
