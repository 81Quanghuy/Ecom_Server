package vn.iotstar.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import vn.iotstar.entity.Category;
import vn.iotstar.entity.ImageData;
import vn.iotstar.entity.OrderItem;
import vn.iotstar.entity.Product;
import vn.iotstar.entity.Review;
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
	
	@GetMapping("list")
	public List<Product> getProductAll() {
		return product.findAll();
	}
	
	@GetMapping("gettop3")
	public List<Product> getTop3() {
		return product.findTop3ByOrderBySoldDesc();
	}
	
	@PostMapping("active")
	public Product producChangeActive(@RequestParam(name = "id", required = false) String id,
			@RequestParam(name = "isselling", required = false) Boolean isselling) {
		// đặt cờ để kiểm tra sản phẩm chưa được người dùng mua
		Boolean flag = true;
		
		Optional<Product> product1 =null;
		product1= product.findById(id);
		// kiểm tra sản phẩm tồn tại
		if(!product1.equals(null)) {
			
			// kiểm tra sản phẩm có được người dùng mua chưa
			List<OrderItem> list = orderItemService.findAll();
			for (OrderItem orderItem : list) {
				if(orderItem.getProduct().getId().equals(id)) {
					flag = false;
					break;
				}
			}
			//Chưa mua
			if(flag) {
				Product entity = product1.get();
				entity.setIsselling(isselling);
				return product.save(entity);
			}
		}
		
		return null;
	}
	@GetMapping("/my/{barcode}")
	public ResponseEntity<Product> getProductByBarcode(@PathVariable("barcode") String barcode) {
		  List<Product> lists = product.getProductByBarcode(barcode);
		  if(lists != null) {
			  return new ResponseEntity<>(lists.get(0), HttpStatus.OK);
		  }
		  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    
	 }
	
	@PostMapping("get")
	public List<Product> getProductByBar(@RequestParam(name = "barcode", required = false) String barcode){
		return product.getProductByBarcode(barcode);
	}
	
	@PostMapping("getId")
	public Optional<Product> getProductById(@RequestParam(name = "id", required = false) String id){
		return product.findById(id);
	}
	
	
	@PostMapping("notify")
	public Product updateProduct(@RequestBody Product productEntity) {	
		if(productEntity.getId()==null) {
			productEntity.setId(UUID.randomUUID().toString().split("-")[0]);
		}
		return product.save(productEntity);
	}
	
	@PostMapping("uploadImage")
	public Product uploadImageProduct(@RequestParam("id")String id,@RequestParam("image")MultipartFile file) throws IOException {
		Optional<Product> entity = product.findById(id);
		ImageData images = storage.uploadImage(file);
		entity.get().setListimage("https://ecomserver.up.railway.app/images/"+images.getName());	
		product.save(entity.get());
		return entity.get();
	}
	
	@PostMapping("delete")
	public String deleteProduct(@RequestParam("id") String id) {
		product.deleteById(id);
		return "Success";
	}
	

	@PostMapping("categoryName")
	public List<Product> getListByCate(@RequestParam("name") String name){
		return product.findByCategory(name);
	}
}
