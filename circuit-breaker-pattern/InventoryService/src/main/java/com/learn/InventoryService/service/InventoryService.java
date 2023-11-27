package com.learn.InventoryService.service;

import com.learn.InventoryService.model.Inventory;
import com.learn.InventoryService.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository repository;

    public Inventory getItemByItemId(String itemId) {
        return repository.findByItemId(itemId).orElseThrow(() -> new RuntimeException("Item Not Found: " + itemId));
    }

    public Inventory updateInventory(Inventory inventory) {
        return repository.save(inventory);
    }
}
