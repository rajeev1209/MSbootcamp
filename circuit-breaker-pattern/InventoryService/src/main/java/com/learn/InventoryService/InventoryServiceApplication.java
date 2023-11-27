package com.learn.InventoryService;

import com.learn.InventoryService.model.Inventory;
import com.learn.InventoryService.repository.InventoryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class InventoryServiceApplication {

    @Autowired
    private InventoryRepository inventoryRepository;

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @PostConstruct
    public void setupData() {
        inventoryRepository.saveAll(Arrays.asList(
                Inventory.builder().id(1).itemId("a12345").itemName("iPhone 14").availableQty(10).build(),
                Inventory.builder().id(2).itemId("b12345").itemName("iPhone 15").availableQty(50).build()
        ));
    }

}
