package vn.iotstar.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.iotstar.entity.Category;
import vn.iotstar.entity.User;
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
	@PostMapping("getById")
	public Category getById(@RequestParam(name = "id", required = false) String id) {
		Optional<Category> category= cate.findById(id);
		if(category !=null) {
			return category.get();
		}
		return null;
	}
	@PostMapping("add")
	public Category add(@RequestBody Category category) {
		Category entity = category;
		List<Category> list = cate.findAll();
		
		System.out.print("Name in "+ category.getName());
		for (Category category2 : list) {
			System.out.print("Name "+ category2.getName());
			if(category2.getName().toString().equals(category.getName())) {
				return null;
			}
		}
		entity.setId(UUID.randomUUID().toString().split("-")[0]);
		return cate.save(entity);
	}
	@PostMapping("update")
	public Category update(@RequestBody Category category) {
		return cate.save(category);
	}

	@PostMapping("delete")
	public String delete(@RequestParam(name = "id", required = false) String id) {
		List<Category> list = cate.findAll();
		Boolean flag= false;
		for (Category category : list) {
			if(category.getId().equals(id)) {
				flag = true;
				break;
			}
		}
		if(flag) {
			cate.deleteById(id);
			 return "Success";
		}
		else {
			return null;
		}
	}
}
