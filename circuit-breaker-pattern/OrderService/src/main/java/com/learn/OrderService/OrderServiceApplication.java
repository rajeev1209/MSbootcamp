package com.learn.OrderService;

import com.learn.OrderService.model.Order;
import com.learn.OrderService.repository.OrderRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class OrderServiceApplication {

	@Autowired
	private OrderRepository orderRepository;

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@PostConstruct
	public void setupData() {
		orderRepository.saveAll(Arrays.asList(
				Order.builder().id(1)
						.itemId("a12345")
						.itemName("iPhone 14")
						.itemType("Electronic")
						.itemPrice(BigDecimal.valueOf(799.99))
						.orderQty(1).orderDate(LocalDate.now())
						.build(),
				Order.builder().id(2)
						.itemId("a12345")
						.itemName("iPhone 15")
						.itemType("Electronic")
						.itemPrice(BigDecimal.valueOf(1299.99))
						.orderQty(1).orderDate(LocalDate.now())
						.build()
		));
	}

}
