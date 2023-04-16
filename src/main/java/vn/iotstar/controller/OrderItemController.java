package vn.iotstar.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.iotstar.entity.Order;
import vn.iotstar.entity.OrderItem;
import vn.iotstar.service.OrderItemService;

@RestController
@RequestMapping("/orderItem")
public class OrderItemController {
	@Autowired
	OrderItemService service;

		
	@PostMapping("/add")
	public OrderItem addOrderItem(@RequestBody OrderItem orderItem) {
		OrderItem entity = orderItem;
		entity.setId(UUID.randomUUID().toString().split("-")[0]);
		return service.save(orderItem);
	}
	@PostMapping("get")
	public List<OrderItem> getAllList(@RequestBody Order order){
		return service.findByOrder(order);
	}

}
