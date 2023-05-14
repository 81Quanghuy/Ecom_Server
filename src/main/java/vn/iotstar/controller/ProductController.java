package vn.iotstar.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import vn.iotstar.entity.ImageData;
import vn.iotstar.entity.OrderItem;
import vn.iotstar.entity.Product;
import vn.iotstar.service.OrderItemService;
import vn.iotstar.service.ProductService;
import vn.iotstar.service.StorageService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductService product;
	@Autowired
	OrderItemService orderItemService;

	@Autowired
	StorageService storage;

	// lấy tất cả sản phẩm
	@GetMapping("listAll")
	public List<Product> getListAll(){
		return product.findAll();
	}
	
	
	// lấy danh sách sản phẩm đang được bán
	@GetMapping("list")
	public List<Product> getProductAll() {
		List<Product> list = product.findAll();
		List<Product> products = new ArrayList<>();
		for (Product product : list) {
			if(product.getIsselling()) {
				products.add(product);
			}
			
		}
		return products;
	}
	
	 @GetMapping("/byCreatedAt")
	    public List<Product> getProductsByCreatedAt() {
	        return product.findAllByOrderByCreateatDesc();
	 }

	@GetMapping("gettop5")
	public List<Product> getTop5() {
		return product.findTop5ByOrderBySoldDesc();
	}
	
	@GetMapping("gettop5discount")
	public List<Product> getTop5discount() {
		return product.findTop5Discount();
	}

	@PostMapping("active")
	public Product producChangeActive(@RequestParam(name = "id", required = false) String id,
			@RequestParam(name = "isselling", required = false) Boolean isselling) {
		// đặt cờ để kiểm tra sản phẩm chưa được người dùng mua
		Boolean flag = true;

		Optional<Product> product1 = null;
		product1 = product.findById(id);
		// kiểm tra sản phẩm tồn tại
		if (!product1.equals(null)) {

			// kiểm tra sản phẩm có được người dùng mua chưa
			List<OrderItem> list = orderItemService.findAll();
			for (OrderItem orderItem : list) {
				if (orderItem.getProduct().getId().equals(id)) {
					flag = false;
					break;
				}
			}
			// Chưa mua
			if (flag) {
				Product entity = product1.get();
				entity.setIsselling(isselling);
				return product.save(entity);
			}
		}

		return null;
	}

	@PostMapping("listByStatus")
	public List<Product> getProductByStatus(@RequestParam(name = "status", required = false) String status){
		if(status.trim().equals("Đang kinh doanh")) {
			return product.findByIsselling(true);
		}
		else if(status.trim().equals("Tạm ngưng")) {
			return product.findByIsselling(false);
		}
		else {
			List<Product> productlist =product.findByQuantity(0);
			List<Product> productentity = new ArrayList<>();
			for (Product product : productlist) {
				if(product.getIsselling()) {
					productentity.add(product);
				}
			}
			return productentity;
		}
	}
	@GetMapping("/my/{barcode}")
	public ResponseEntity<Product> getProductByBarcode(@PathVariable("barcode") String barcode) {
		List<Product> lists = product.getProductByBarcode(barcode);
		if (lists != null) {
			return new ResponseEntity<>(lists.get(0), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@PostMapping("get")
	public List<Product> getProductByBar(@RequestParam(name = "barcode", required = false) String barcode) {
		return product.getProductByBarcode(barcode);
	}

	@PostMapping("getId")
	public Optional<Product> getProductById(@RequestParam(name = "id", required = false) String id) {
		return product.findById(id);
	}

	@PostMapping("notify")
	public Product updateProduct(@RequestBody Product productEntity) {
		Optional<Product> entity = product.findById(productEntity.getId());

		// chỉnh ngày tháng năm thành dạng dd/mm/yyyy
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = dateFormat.format(date);
		// tạo mới
		if (entity.isEmpty()) {
			productEntity.setId(UUID.randomUUID().toString().split("-")[0]);
			productEntity.setRating(0.0);
			productEntity.setIsselling(true);
			productEntity.setSold(0);

			productEntity.setCreateat(strDate);
			productEntity.setUpdateat(strDate);
			Random random = new Random();
			productEntity.setBarcode(String.valueOf(random.nextInt(9000000) + 1000000));
		}
		// sửa 
		else {
			productEntity.setUpdateat(strDate);
		}
		if(productEntity.getPrice()<0) {
			productEntity.setPrice(0);
		}
		if(productEntity.getPromotionaprice()<0) {
			productEntity.setPromotionaprice(0);
		}
		if(productEntity.getQuantity()<0) {
			productEntity.setQuantity(0);
		}
		return product.save(productEntity);
	}

	@DeleteMapping("delete")
	public ResponseEntity<String>  deleteProduct(@RequestParam("id") String id) {
		Optional<Product> entity = product.findById(id);
		if(entity.isEmpty()) {
			return ResponseEntity.badRequest().body("Sản phẩm không tồn tại");
		}
		else {
			product.deleteById(id);
			return ResponseEntity.ok("Đã xóa thành công");
		}
		
		
	}

	@PostMapping("categoryName")
	public List<Product> getListByCate(@RequestParam("name") String name) {
		return product.findByCategory(name);
	}
}
