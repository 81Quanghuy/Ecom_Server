package vn.iotstar.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.iotstar.entity.Order;
import vn.iotstar.entity.OrderItem;
import vn.iotstar.service.OrderItemService;
import vn.iotstar.service.OrderService;

@RestController
@RequestMapping("/orderItem")
public class OrderItemController {
	@Autowired
	OrderItemService service;

	@Autowired
	OrderService orderSerive;
	
	@PostMapping("/add")
	public OrderItem addOrderItem(@RequestBody OrderItem orderItem) {
		OrderItem entity = orderItem;
		entity.setId(UUID.randomUUID().toString().split("-")[0]);
		return service.save(orderItem);
	}
	@PostMapping("get")
	public List<OrderItem> getAllList(@RequestParam(name = "id") String id){
		Optional<Order> entity = orderSerive.findById(id);
		System.out.print(entity.get().getStatusOrder());
		if(entity.get()!= null) {
			List<OrderItem> list = service.findByOrder(entity.get());
			return list;
		}
		
		return null;
	}
	
	@PostMapping("/addOrderItem")
	public List<OrderItem> addListOrderItem(@RequestBody List<OrderItem> orderItems) {
		List<OrderItem> mOrderItem = new ArrayList<>();
		for(OrderItem orderItem :orderItems) {
			OrderItem entity = orderItem;
			entity.setId(UUID.randomUUID().toString().split("-")[0]);
			mOrderItem.add(service.save(orderItem));
		}
		
		return mOrderItem;
	}

	@SuppressWarnings("null")
	@PostMapping("getAll")
	public List<OrderItem> getAll(@RequestBody List<Order> order){
		List<OrderItem> list =  new ArrayList<OrderItem>();
		for (Order entity : order) {
			List<OrderItem> orderItem = service.findByOrder(entity);
			for (OrderItem orderItem2 : orderItem) {
				list.add(orderItem2);
			}
		}
		return list;
	}
}
