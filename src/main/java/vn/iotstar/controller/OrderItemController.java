package vn.iotstar.controller;

import java.util.List;

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

	@PostMapping("/getHuy")
	public List<OrderItem> getListHuy(@RequestBody Order order){
		return service.getListHuy(order);
	}
	@PostMapping("/getDangGiao")
	public List<OrderItem> getListDangGiao(@RequestBody Order order){
		return service.getListDangGiao(order);
	}
	@PostMapping("/getDaGiao")
	public List<OrderItem> getListDaGiao(@RequestBody Order order){
		return service.getListDaGiao(order);
	}
	@PostMapping("/add")
	public OrderItem addOrderItem(@RequestBody OrderItem orderItem) {
		return service.save(orderItem);
	}
}
