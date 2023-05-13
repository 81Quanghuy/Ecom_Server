package vn.iotstar.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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

import vn.iotstar.entity.Order;
import vn.iotstar.entity.OrderItem;
import vn.iotstar.entity.Product;
import vn.iotstar.entity.StatusOrder;
import vn.iotstar.model.ReponseThongKeProduct;
import vn.iotstar.service.OrderItemService;
import vn.iotstar.service.OrderService;

@RestController
@RequestMapping("/orderItem")
public class OrderItemController {
	@Autowired
	OrderItemService service;

	@Autowired
	OrderService orderSerive;
	@GetMapping("listall")
	public List<OrderItem> order(){
		return service.findAll();
	}
	
	@PostMapping("getListByTime")
	public List<ReponseThongKeProduct> getProductByDate(@RequestParam(name = "date") String Time){
		 List<ReponseThongKeProduct> reponse = new ArrayList<>();
		 
		 List<OrderItem> listOrderItem = service.findAll();
		 List<OrderItem> orderItems = new ArrayList<>();
		 //lấy ra các đơn hàng đã giao thành công
		 for (OrderItem orderItem : listOrderItem) {
			if(orderItem.getOrder().getStatusOrder().equals(StatusOrder.DAGIAO)) {
				orderItems.add(orderItem);
			}
		}
		 
		 // theo tuần
		if(Time.equals("Date")) {
			// lấy 7 ngày gần nhất
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			List<String> lastSevenDays = new ArrayList<>();
		    LocalDate currentDate = LocalDate.now();		 
		    for (int i = 0; i < 7; i++) {
		    	String formattedDate = currentDate.minusDays(i).format(formatter);
		    	lastSevenDays.add(formattedDate);
		    }		    
		    Collections.reverse(lastSevenDays);
		    
		    // lặp 7 ngày 
			for (int i = 0; i < 7; i++) {
				List<ReponseThongKeProduct> templ = new ArrayList<>();
				ReponseThongKeProduct ThongkeProduct = new ReponseThongKeProduct();
				String Day = lastSevenDays.get(i);
				// khai báo 1 sản phẩm 
			    // lặp qua từng đơn hàng chi tiết
				for (OrderItem entity : orderItems) {
					// nếu có đơn hàng ở ngày đó
					if(entity.getOrder().getUpdateat().equals(Day)) {
						System.out.println("Ngày mua"+entity.getOrder().getUpdateat());
						System.out.println("Date"+Day);
						if(templ.size()>0) 
						{
							for (ReponseThongKeProduct orderItem : templ) {
							}
							Boolean flag = false;
							for (ReponseThongKeProduct thongke : templ) 
							{
								if(thongke.getProduct().getId().equals(entity.getProduct().getId())) {
									flag = true;
									thongke.setCount(thongke.getCount()+ entity.getCount());

									break;
								}
							}
							if(!flag) {
								flag = false;
								templ.add(new ReponseThongKeProduct(entity.getProduct(), entity.getCount()));
							}
						}
						else {
							templ.add(new ReponseThongKeProduct(entity.getProduct(), entity.getCount()));
						}										
					}
				}
				if(templ.size()>0) {
					ThongkeProduct = templ.get(0);
					for (ReponseThongKeProduct Thongke : templ) {
						if(Thongke.getCount()> ThongkeProduct.getCount()) {
							ThongkeProduct.setProduct(Thongke.getProduct());
							ThongkeProduct.setCount(Thongke.getCount());
						}
					}
				}				
				// thêm vào biến trả về theo từng ngày 
				reponse.add(ThongkeProduct);
			}
			
		}
		
		// theo 12 tháng
		if(Time.equals("Month")) {
			 // logic
			for (int i = 1; i <= 12; i++) {
				List<ReponseThongKeProduct> templ = new ArrayList<>();
				ReponseThongKeProduct ThongkeProduct = new ReponseThongKeProduct();
				int month = 0;
			    ThongkeProduct.setCount(0);
				for (OrderItem entity : orderItems) {
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			        try {
			            Date date = formatter.parse(entity.getOrder().getUpdateat());
			            Calendar calendar = Calendar.getInstance();
			            calendar.setTime(date);
			            month = calendar.get(Calendar.MONTH) + 1;
			        } catch (ParseException e) {
			            e.printStackTrace();
			        }
			        
					if(month ==i){
						if(templ.size()>0) 
						{
							for (ReponseThongKeProduct orderItem : templ) {
							}
							Boolean flag = false;
							for (ReponseThongKeProduct thongke : templ) 
							{
								if(thongke.getProduct().getId().equals(entity.getProduct().getId())) {
									flag = true;
									thongke.setCount(thongke.getCount()+ entity.getCount());

									break;
								}
							}
							if(!flag) {
								flag = false;
								templ.add(new ReponseThongKeProduct(entity.getProduct(), entity.getCount()));
							}
						}
						else {
							templ.add(new ReponseThongKeProduct(entity.getProduct(), entity.getCount()));
						}										
					}
				}
				if(templ.size()>0) {
					ThongkeProduct = templ.get(0);
					for (ReponseThongKeProduct Thongke : templ) {
						if(Thongke.getCount()> ThongkeProduct.getCount()) {
							ThongkeProduct.setProduct(Thongke.getProduct());
							ThongkeProduct.setCount(Thongke.getCount());
						}
					}
				}				
				// thêm vào biến trả về theo từng ngày 
				reponse.add(ThongkeProduct);
			}
						       
	      }
		
        
		
		return reponse;
	}
	
	@PostMapping("/add")
	public OrderItem addOrderItem(@RequestBody OrderItem orderItem) {
		OrderItem entity = orderItem;
		entity.setId(UUID.randomUUID().toString().split("-")[0]);
		return service.save(orderItem);
	}
	@PostMapping("get")
	public List<OrderItem> getAllList(@RequestParam(name = "id") String id){
		Optional<Order> entity = orderSerive.findById(id);
		System.out.print(entity.get().getStatusOrder());
		if(entity.get()!= null) {
			List<OrderItem> list = service.findByOrder(entity.get());
			return list;
		}
		
		return null;
	}
	
	@PostMapping("/addOrderItem")
	public List<OrderItem> addListOrderItem(@RequestBody List<OrderItem> orderItems) {
		List<OrderItem> mOrderItem = new ArrayList<>();
		for(OrderItem orderItem :orderItems) {
			OrderItem entity = orderItem;
			entity.setId(UUID.randomUUID().toString().split("-")[0]);
			mOrderItem.add(service.save(orderItem));
		}
		return mOrderItem;
	}

	@SuppressWarnings("null")
	@PostMapping("getAll")
	public List<OrderItem> getAll(@RequestBody List<Order> order){
		List<OrderItem> list =  new ArrayList<OrderItem>();
		for (Order entity : order) {
			List<OrderItem> orderItem = service.findByOrder(entity);
			for (OrderItem orderItem2 : orderItem) {
				list.add(orderItem2);
			}
		}
		return list;
	}
	
	@PostMapping("/getByOrder")
	public List<OrderItem> getByOrder(@RequestBody Order order) {
		List<OrderItem> orderItems = service.findByOrder(order);
		if(orderItems != null) {
			return orderItems;
		}
		return null;
	}
	
}
