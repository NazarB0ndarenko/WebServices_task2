package lt.viko.eif.bondarenko.producer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import lt.viko.eif.bondarenko.producer.entety.Order;
import lt.viko.eif.bondarenko.producer.entety.OrderItem;
import lt.viko.eif.bondarenko.producer.repository.OrderRepository;
import org.springframework.stereotype.Service;
import task2.shop.CreateOrderDto;

import java.time.LocalDate;
import java.util.stream.Collectors;

/**
 * Service class that handles the business logic related to {@link Order} entities.
 * Provides methods for creating new orders.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    /**
     * Repository used to interact with the order data in the database.
     */
    private final OrderRepository orderRepository;

    /**
     * Creates a new order based on the provided order data.
     * The method calculates the delivery date and maps ordered items to order items.
     *
     * @param dto the {@link CreateOrderDto} containing order details
     * @return the ID of the newly created order
     */
    public Long createOrder(CreateOrderDto dto) {
        log.info("Creating order for customer {}", dto.getCustomerName());

        Order order = Order.builder()
                .deliveryAddress(dto.getDeliveryAddress())
                .totalPrice(dto.getTotalPrice())
                .customerName(dto.getCustomerName())
                .deliveryDate(LocalDate.now().plusDays(3))  // Delivery date is set to 3 days from today
                .items(dto.getOrderedItems().stream()
                        .map(item -> new OrderItem(item.getItemId(), item.getQuantity())) // Convert ordered items to order items
                        .collect(Collectors.toList()))
                .build();

        orderRepository.save(order);

        log.info("Order {} created", order.getId());

        return order.getId();
    }
}

