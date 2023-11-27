package com.learn.InventoryService.controller;

import com.learn.InventoryService.model.Inventory;
import com.learn.InventoryService.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping(path = "/inventory/{itemId}")
    public ResponseEntity<Inventory> getItemById(@PathVariable("itemId") String itemId) {
        return ResponseEntity.ok().body(inventoryService.getItemByItemId(itemId));
    }

    @PutMapping(path = "/inventory/update")
    public ResponseEntity<Inventory> updateInventory(@RequestBody Inventory inventory) {
        return ResponseEntity.ok().body(inventoryService.updateInventory(inventory));
    }
}
