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
import vn.iotstar.entity.Product;
import vn.iotstar.service.ProductService;
import vn.iotstar.service.StorageService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	ProductService product;
	
	@Autowired
	StorageService storage;
	
	@GetMapping("list")
	public List<Product> getProductAll() {
		return product.findAll();
	}
	@PostMapping("active")
	public Product producChangeActive(@RequestParam(name = "id", required = false) String id,
			@RequestParam(name = "isselling", required = false) Boolean isselling) {
		Optional<Product> product1 = product.findById(id);
		Product entity = product1.get();
		entity.setIsselling(isselling);
		return product.save(entity);
		
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
	
	@PostMapping("byCate")
	public List<Product> getProductByCategory(@RequestBody Category category){
		return product.findByCategory(category);
	}
	//,@RequestParam("category")Category category 
	
	@PostMapping("add")
	public Product add(@RequestBody Product product1) {
		Product entity = product1;
		entity.setId(UUID.randomUUID().toString().split("-")[0]);
		return product.save(entity);
	}
	
//	public Product addProduct(@RequestParam("name")String name,@RequestParam("desciption")String desciption
//			,@RequestParam("price")Integer price ,@RequestParam("promotionaprice")Integer promotionaprice  
//			,@RequestParam("quantity")Integer quantity ,@RequestParam("sold")Integer sold ,
//			@RequestParam("barcode")String barcode 
//			,@RequestParam("image")MultipartFile file) throws IOException {
//		Date date = new Date();
//		ImageData images = storage.uploadImage(file);
//		Product entity = new Product();
//		entity.setId(UUID.randomUUID().toString().split("-")[0]);
//		entity.setName(name);
//		entity.setDesciption(desciption);
//		entity.setPrice(price);
//		entity.setPromotionaprice(promotionaprice);
//		entity.setQuantity(quantity);
//		entity.setSold(sold);
//		entity.setIsselling(true);
//		entity.setListimage("https://ecomserver.up.railway.app/images/"+images.getName());		
//		//entity.setCategory(category);
//		entity.setRating(0.0);
//		entity.setCreateat(date);
//		entity.setUpdateat(date);
//		entity.setBarcode(barcode);		
//		product.save(entity);
//		return entity;
//	}
	
	@PostMapping("upload")
	public Product updateProduct(@RequestBody Product productEntity) {		
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
	
	@PostMapping("listbyCategory")
	public List<Product> getListByCate(@RequestBody Category category){
		return product.findByCategory(category);
	}
}
