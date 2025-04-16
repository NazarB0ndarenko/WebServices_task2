package lt.viko.eif.bondarenko.producer.initialization;

import lombok.RequiredArgsConstructor;
import lt.viko.eif.bondarenko.producer.entety.Item;
import lt.viko.eif.bondarenko.producer.repository.ItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class ItemInitializer implements CommandLineRunner {

    private final ItemRepository itemRepository;

    @Override
    public void run(String[] args) {

        if (itemRepository.findAll().isEmpty()) {
            this.initItem("Apple", BigDecimal.valueOf(1.99), "Fresh apple from the garden");
            this.initItem("Banana", BigDecimal.valueOf(2.99), "Fresh banana from the garden");
            this.initItem("Orange", BigDecimal.valueOf(3.99), "Fresh orange from the garden");
            this.initItem("Pineapple", BigDecimal.valueOf(4.99), "Fresh pineapple from the garden");
            this.initItem("Strawberry", BigDecimal.valueOf(5.99), "Fresh strawberry from the garden");
            this.initItem("Blueberry", BigDecimal.valueOf(6.99), "Fresh blueberry from the garden");
            this.initItem("Raspberry", BigDecimal.valueOf(4.99), "Fresh raspberry from the garden");
            this.initItem("Blackberry", BigDecimal.valueOf(8.99), "Fresh blackberry from the garden");
            this.initItem("Cherry", BigDecimal.valueOf(8.99), "Fresh cherry from the garden");
            this.initItem("Grape", BigDecimal.valueOf(3.99), "Fresh grape from the garden");
        }

    }

    private void initItem(String name, BigDecimal price, String description) {
        Item item = new Item(name, price, description);
        itemRepository.save(item);
    }
}
