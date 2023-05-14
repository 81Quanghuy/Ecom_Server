package vn.iotstar.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.iotstar.entity.Order;
import vn.iotstar.entity.OrderItem;
import vn.iotstar.entity.Product;
import vn.iotstar.entity.StatusOrder;
import vn.iotstar.entity.User;
import vn.iotstar.model.ResponseOrder;
import vn.iotstar.service.OrderItemService;
import vn.iotstar.service.OrderService;
import vn.iotstar.service.ProductService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService service;
	@Autowired
	OrderItemService orderItemService;
	@Autowired
	ProductService productService;

	@GetMapping("list")
	public List<Order> listOrder(){
		return service.findAll();
	}
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

	@DeleteMapping("deleteByStatus")
	public ResponseEntity<String> DeleteByStatus(@RequestParam(name = "status", required = false) StatusOrder Status) {
		List<Order> list = service.findByStatusOrder(Status);
		if(list.size()>0) {
			for (Order order : list) {
				List<OrderItem> orderItems = orderItemService.findByOrder(order);
				for (OrderItem orderItem : orderItems) {
					orderItemService.delete(orderItem);
				}
			}
			service.deleteAll();
			return ResponseEntity.ok("Đã xóa thành công");
			
		}
		else {
			return ResponseEntity.badRequest().body("Đơn hàng không tồn tại");
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
		
		// chỉnh ngày tháng năm thành dạng dd/mm/yyyy
				Date date = new Date();
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				String strDate = dateFormat.format(date);
				
		if (order != null) {
			Order entity = order.get();
			entity.setStatusOrder(Status);
			entity.setUpdateat(strDate);
			if(Status.equals(StatusOrder.DAGIAO)) {
				List<OrderItem> orderItems = orderItemService.findByOrder(entity);
				for (OrderItem orderItem : orderItems) {
					Optional<Product> product = productService.findById(orderItem.getProduct().getId());
				if(!product.isEmpty()) {
					Integer quantity = product.get().getQuantity();
					Integer sold = product.get().getSold();
					product.get().setQuantity(quantity - orderItem.getCount());
					product.get().setSold(sold+orderItem.getCount());
					product.get().setUpdateat(strDate);
					productService.save(product.get());
				}
				}
			}
			return service.save(entity);
		}
		return null;
	}

	@DeleteMapping("delete")
	public ResponseEntity<String> delete(@RequestParam(name = "id", required = false) String id) {
		Optional<Order> entity = service.findById(id);
		if(entity.isEmpty()) {
			return ResponseEntity.badRequest().body("Đơn hàng không tồn tại");
		}
		else {
			List<OrderItem> orderItems = orderItemService.findByOrder(entity.get());
			for (OrderItem orderItem : orderItems) {
				orderItemService.delete(orderItem);
			}
			service.delete(entity.get());
			return ResponseEntity.ok("Đã xóa thành công");
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
	
	//Thống kê đơn hàng
	@PostMapping("analysis")
	public List<Order> getListOrder(@RequestParam(name = "focus", required = false) String time,
			@RequestParam(name = "date", required = false) String date){
		if(time.toString().equals("date")) {
			List<Order> orders = service.findByUpdateat(date);
			if(orders!= null) {
				return orders;
			}
			else {
				return null;
			}
		}
		else if(time.toString().equals("month")) {
			List<Order> orderList = service.findAll();
			List<Order> entitys = new ArrayList<>();
			String firstMonth  = date.split("/")[1];
			for (Order order : orderList) {
				 String[] dateParts = order.getUpdateat().split("/");
				 String month = dateParts[1]; 
				 if (month.equals(firstMonth)) {
					 entitys.add(order);
				       
				    } else {
				        
				    }
			}
			return entitys;

		}
		else if(time.toString().equals("year")) {
			List<Order> orderList = service.findAll();
			List<Order> entitys = new ArrayList<>();
			String firstYear  = date.split("/")[2];
			for (Order order : orderList) {
				 String[] dateParts = order.getUpdateat().split("/");
				 String year = dateParts[2]; 
				 
				 if (year.equals(firstYear)) {
					 entitys.add(order);
				    
				    } else {
				       
				    }
			}
			return entitys;
		}
		return null;
	}
}
