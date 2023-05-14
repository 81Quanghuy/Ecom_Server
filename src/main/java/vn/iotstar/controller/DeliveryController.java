package vn.iotstar.controller;

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

import vn.iotstar.entity.Delivery;
import vn.iotstar.entity.User;
import vn.iotstar.service.DeliveryService;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
	
	@Autowired
	DeliveryService service;
	
	@PostMapping("getByUser")
	public List<Delivery> getDeliveryByUser(@RequestBody User user){
		return service.findByUser(user);
	}
	
	
	@PostMapping("/add")
	public Delivery addDelivery(@RequestBody Delivery delivery) {
		Delivery entity = delivery;
		entity.setId(UUID.randomUUID().toString().split("-")[0]);
		return service.save(delivery);
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<String>  deleteDelivery(@RequestParam("id") String id) {
		Optional<Delivery> entity = service.findById(id);
		if(entity.isEmpty()) {
			return ResponseEntity.badRequest().body("Sản phẩm không tồn tại");
		}
		else {
			service.deleteById(id);
			return ResponseEntity.ok("Đã xóa thành công");
		}
		
	}


}
