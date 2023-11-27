package com.learn.OrderService.service;

import com.learn.OrderService.dto.ItemInventory;
import com.learn.OrderService.model.Order;
import com.learn.OrderService.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String SERVICE_NAME = "inventory-service";

    private static final String RATE_SERVICE_URL = "http://localhost:9000/api/inventory/";

    public Order getOrder(String orderId) {
        return orderRepository.findById(Integer.parseInt(orderId)).orElse(null);
    }

    public Order createOrder(Order order) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ItemInventory> entity = new HttpEntity<>(null, headers);
        ResponseEntity<ItemInventory> response = restTemplate.exchange(
                (RATE_SERVICE_URL + order.getItemId()),
                HttpMethod.GET, entity,
                ItemInventory.class
        );
        ItemInventory itemInventory = response.getBody();

        if (null != itemInventory && itemInventory.getAvailableQty() > 0 && itemInventory.getAvailableQty() >= order.getOrderQty()) {

            orderRepository.save(order);

            itemInventory.setItemId(order.getItemId());
            int currentAvailable = itemInventory.getAvailableQty() - order.getOrderQty();
            itemInventory.setAvailableQty(currentAvailable);
            HttpEntity<ItemInventory> updateEntity = new HttpEntity<>(itemInventory, headers);
            ResponseEntity<ItemInventory> updatedResponse = restTemplate.exchange(
                    (RATE_SERVICE_URL + "update"),
                    HttpMethod.PUT, updateEntity,
                    ItemInventory.class
            );
            log.info("Inventory updated with available Qty " + currentAvailable + " Updated Response " + updatedResponse.getBody().getAvailableQty());
            return order;
        } else {
            log.info("sufficient inventory not available");
        }
        return null;
    }
}
