package vn.iotstar.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.iotstar.entity.Category;
import vn.iotstar.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	CategoryService cate;
	
	@GetMapping("list")
	public List<Category> getCategories() {
		return cate.findAll();
	}
	@PostMapping("add")
	public Category add(@RequestBody Category category) {
		Category entity = category;
		entity.setId(UUID.randomUUID().toString().split("-")[0]);
		return cate.save(entity);
	}
}
