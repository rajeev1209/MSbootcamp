package com.learn.OrderService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemInventory {
    Integer id;
    String itemId;
    String itemName;
    int availableQty;
}
