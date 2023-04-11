package vn.iotstar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.iotstar.entity.Delivery;
import vn.iotstar.service.DeliveryService;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
	
	@Autowired
	DeliveryService service;
	
	@PostMapping("/add")
	public Delivery addDelivery(@RequestBody Delivery delivery) {
		
		return service.save(delivery);
	}

}
