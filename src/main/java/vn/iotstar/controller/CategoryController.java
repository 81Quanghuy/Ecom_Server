package vn.iotstar.controller;

import java.util.ArrayList;
import java.util.Date;
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
import vn.iotstar.entity.Product;
import vn.iotstar.service.CategoryService;
import vn.iotstar.service.ProductService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	CategoryService cate;
	@Autowired
	ProductService productService;

	@GetMapping("list")
	public List<Category> getCategories() {
		return cate.findAll();
	}

	@PostMapping("getById")
	public Category getById(@RequestParam(name = "id", required = false) String id) {
		Optional<Category> category = cate.findById(id);
		if (category != null) {
			return category.get();
		}
		return null;
	}

	@PostMapping("add")
	public Category add(@RequestBody Category category) {
		Category entity = category;
		List<Category> list = cate.findAll();

		System.out.print("Name in " + category.getName());
		for (Category category2 : list) {
			System.out.print("Name " + category2.getName());
			if (category2.getName().toString().equals(category.getName())) {
				return null;
			}
		}
		entity.setId(UUID.randomUUID().toString().split("-")[0]);
		return cate.save(entity);
	}

	@PostMapping("update")
	public Category update(@RequestBody Category category) {
		List<Category> list = cate.findAll();
		List<Category> initList = new ArrayList<Category>();
		for (Category category2 : list) {
			if (!category2.getId().toString().equals(category.getId())) {
				initList.add(category2);
			}
		}
		for (Category category2 : initList) {
			if (category2.getName().toString().equals(category.getName())) {
				return null;
			}
		}
		Category entity = category;
		return cate.save(entity);
	}

	@PostMapping("delete")
	public Category delete(@RequestParam(name = "id", required = false) String id) {
		Optional<Category> entity = cate.findById(id);
		if (entity != null) {
			Category category2 = entity.get();
			List<Product> list = productService.findByCategory(category2.getImage());
			if (list.size() == 0) {

				cate.delete(entity.get());

				return category2;
			} else {
				category2.setName("Category đang có sản phẩm");
				return category2;
			}
		} else {
			return null;
		}
	}
}
