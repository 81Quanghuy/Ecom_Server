package vn.iotstar.controller;

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
import vn.iotstar.entity.StatusOrder;
import vn.iotstar.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderService service;
	
	@PostMapping("add")
	public Order addOrder(@RequestBody Order order) {
		Order entity = order;
		entity.setId(UUID.randomUUID().toString().split("-")[0]);
		return service.save(entity);
	}
	@PostMapping("/getList")
	public List<Order> getListHuy(@RequestParam(name = "status",required = false)StatusOrder Status ){
		return service.findByStatusOrder(Status);
	}
	@PostMapping("changeStatus")
	public Order changeStatus(@RequestParam(name = "id",required = false)String id,
			@RequestParam(name = "status",required = false)StatusOrder Status) {
		Optional<Order> order = service.findById(id);
		if(order!= null) {
			Order entity = order.get();
			entity.setStatusOrder(Status);
			return service.save(entity);
		}
		return null;
	}
	@PostMapping("delete")
	public String delete(@RequestBody Order order) {
		service.delete(order);
		return "Succes";
	}
	@PostMapping("updateStatusAll")
	public List<Order> updateStatus(@RequestParam(name = "status",required = false)StatusOrder Status) {
		List<Order> list = service.findByStatusOrder(Status);
		for(Order order: list) {
			order.setStatusOrder(StatusOrder.DANGGIAO);
		}
		return list;
	}
}
