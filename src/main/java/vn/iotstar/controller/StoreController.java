package vn.iotstar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.iotstar.entity.Store;
import vn.iotstar.service.StoreService;

@RestController
@RequestMapping("/store")
public class StoreController {

	@Autowired
	StoreService service;
	
	@PostMapping("/add")
	public Store addStore(@RequestBody Store store) {
		return service.save(store);
	}
}
