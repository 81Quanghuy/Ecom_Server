package vn.iotstar.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import vn.iotstar.entity.StatusOrder;
import vn.iotstar.entity.User;
import vn.iotstar.model.ResponseOrder;
import vn.iotstar.service.OrderItemService;
import vn.iotstar.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService service;
	@Autowired
	OrderItemService orderItemService;

	@PostMapping("add")
	public Order addOrder(@RequestBody Order order) {
		// chỉnh ngày tháng năm thành dạng dd/mm/yyyy
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = dateFormat.format(date);
		Order entity = order;
		entity.setCreateat(strDate);
		entity.setUpdateat(strDate);
		entity.setId(UUID.randomUUID().toString().split("-")[0]);
		
		return service.save(entity);
	}

	@PostMapping("deleteByStatus")
	public String DeleteByStatus(@RequestParam(name = "status", required = false) StatusOrder Status) {
		List<Order> list = service.findByStatusOrder(Status);
		if(list.size()>0) {
			service.deleteAll();
			return "success";
		}
		else {
			return "Error";
		}
		
	}
	@PostMapping("/getList")
	public List<Order> getListHuy(@RequestParam(name = "status", required = false) StatusOrder Status) {
		return service.findByStatusOrder(Status);
	}

	@PostMapping("changeStatus")
	public Order changeStatus(@RequestParam(name = "id", required = false) String id,
			@RequestParam(name = "status", required = false) StatusOrder Status) {
		Optional<Order> order = service.findById(id);
		
		if (order != null) {
			Order entity = order.get();
			entity.setStatusOrder(Status);
			List<OrderItem> list = orderItemService.findByOrder(entity);
			for (OrderItem orderItem : list) {
				orderItem.setOrder(entity);
				orderItemService.save(orderItem);
			}
			return service.save(entity);
		}
		return null;
	}

	@PostMapping("delete")
	public String delete(@RequestBody Order order) {
		Optional<Order> entity = service.findById(order.getId());
		if(entity.isEmpty()) {
			return "ERROR";
		}
		else {
			service.delete(order);
			return "Succes";
		}
		
	}

	
	@PostMapping("userOders")
	public ResponseOrder getOrderByUser(@RequestBody User user) {
		List<Order> orders = service.findByUser(user);
		String message = "Fail";
		if(orders != null) {
			message = "Success";
			return new ResponseOrder(message, orders);
		}
		return new ResponseOrder(message, null);
	}
	
}
