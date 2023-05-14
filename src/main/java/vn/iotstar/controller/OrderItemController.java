package vn.iotstar.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.iotstar.entity.Order;
import vn.iotstar.entity.OrderItem;
import vn.iotstar.entity.Review;
import vn.iotstar.entity.StatusOrder;
import vn.iotstar.entity.User;
import vn.iotstar.model.ReponseThongKeProduct;
import vn.iotstar.service.OrderItemService;
import vn.iotstar.service.OrderService;
import vn.iotstar.service.ReviewService;

@RestController
@RequestMapping("/orderItem")
public class OrderItemController {
	@Autowired
	OrderItemService service;
	
	@Autowired
	ReviewService reviewService;

	@Autowired
	OrderService orderSerive;
	@GetMapping("listall")
	public List<OrderItem> order(){
		return service.findAll();
	}
	
//	@PostMapping("getListByTime")
//	public List<ReponseThongKeProduct> getProductByDate(@RequestParam(name = "date") String Time){
//		 List<ReponseThongKeProduct> reponse = new ArrayList<>();
//		 
//		 List<OrderItem> listOrderItem = service.findAll();
//		 List<OrderItem> orderItems = new ArrayList<>();
//		 //lấy ra các đơn hàng đã giao thành công
//		 for (OrderItem orderItem : listOrderItem) {
//			if(orderItem.getOrder().getStatusOrder().equals(StatusOrder.DAGIAO)) {
//				orderItems.add(orderItem);
//			}
//		}
//		 
//		 // theo tuần
//		if(Time.equals("Date")) {
//			// lấy 7 ngày gần nhất
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//			List<String> lastSevenDays = new ArrayList<>();
//		    LocalDate currentDate = LocalDate.now();		 
//		    for (int i = 0; i < 7; i++) {
//		    	String formattedDate = currentDate.minusDays(i).format(formatter);
//		    	lastSevenDays.add(formattedDate);
//		    }		    
//		    Collections.reverse(lastSevenDays);
//		    
//		    // lặp 7 ngày 
//			for (int i = 0; i < 7; i++) {
//				List<ReponseThongKeProduct> templ = new ArrayList<>();
//				ReponseThongKeProduct ThongkeProduct = new ReponseThongKeProduct();
//				String Day = lastSevenDays.get(i);
//				// khai báo 1 sản phẩm 
//			    // lặp qua từng đơn hàng chi tiết
//				for (OrderItem entity : orderItems) {
//					// nếu có đơn hàng ở ngày đó
//					if(entity.getOrder().getUpdateat().equals(Day)) {
//						System.out.println("Ngày mua"+entity.getOrder().getUpdateat());
//						System.out.println("Date"+Day);
//						if(templ.size()>0) 
//						{
//							for (ReponseThongKeProduct orderItem : templ) {
//							}
//							Boolean flag = false;
//							for (ReponseThongKeProduct thongke : templ) 
//							{
//								if(thongke.getProduct().getId().equals(entity.getProduct().getId())) {
//									flag = true;
//									thongke.setCount(thongke.getCount()+ entity.getCount());
//
//									break;
//								}
//							}
//							if(!flag) {
//								flag = false;
//								templ.add(new ReponseThongKeProduct(entity.getProduct(), entity.getCount()));
//							}
//						}
//						else {
//							templ.add(new ReponseThongKeProduct(entity.getProduct(), entity.getCount()));
//						}										
//					}
//				}
//				if(templ.size()>0) {
//					ThongkeProduct = templ.get(0);
//					for (ReponseThongKeProduct Thongke : templ) {
//						if(Thongke.getCount()> ThongkeProduct.getCount()) {
//							ThongkeProduct.setProduct(Thongke.getProduct());
//							ThongkeProduct.setCount(Thongke.getCount());
//						}
//					}
//				}				
//				// thêm vào biến trả về theo từng ngày 
//				reponse.add(ThongkeProduct);
//			}
//			
//		}
//		
//		// theo 12 tháng
//		if(Time.equals("Month")) {
//			 // logic
//			for (int i = 1; i <= 12; i++) {
//				List<ReponseThongKeProduct> templ = new ArrayList<>();
//				ReponseThongKeProduct ThongkeProduct = new ReponseThongKeProduct();
//				int month = 0;
//			    ThongkeProduct.setCount(0);
//				for (OrderItem entity : orderItems) {
//					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//			        try {
//			            Date date = formatter.parse(entity.getOrder().getUpdateat());
//			            Calendar calendar = Calendar.getInstance();
//			            calendar.setTime(date);
//			            month = calendar.get(Calendar.MONTH) + 1;
//			        } catch (ParseException e) {
//			            e.printStackTrace();
//			        }
//			        
//					if(month ==i){
//						if(templ.size()>0) 
//						{
//							for (ReponseThongKeProduct orderItem : templ) {
//							}
//							Boolean flag = false;
//							for (ReponseThongKeProduct thongke : templ) 
//							{
//								if(thongke.getProduct().getId().equals(entity.getProduct().getId())) {
//									flag = true;
//									thongke.setCount(thongke.getCount()+ entity.getCount());
//
//									break;
//								}
//							}
//							if(!flag) {
//								flag = false;
//								templ.add(new ReponseThongKeProduct(entity.getProduct(), entity.getCount()));
//							}
//						}
//						else {
//							templ.add(new ReponseThongKeProduct(entity.getProduct(), entity.getCount()));
//						}										
//					}
//				}
//				if(templ.size()>0) {
//					ThongkeProduct = templ.get(0);
//					for (ReponseThongKeProduct Thongke : templ) {
//						if(Thongke.getCount()> ThongkeProduct.getCount()) {
//							ThongkeProduct.setProduct(Thongke.getProduct());
//							ThongkeProduct.setCount(Thongke.getCount());
//						}
//					}
//				}				
//				// thêm vào biến trả về theo từng ngày 
//				reponse.add(ThongkeProduct);
//			}
//						       
//	      }	
//		return reponse;
//	}
	@PostMapping("getListByTime")
	public List<ReponseThongKeProduct> getProductByDate(@RequestParam(name = "date") String time) {
	    List<OrderItem> orderItems = service.findAll()
	        .stream()
	        .filter(item -> item.getOrder().getStatusOrder().equals(StatusOrder.DAGIAO))
	        .collect(Collectors.toList());
	        
	    List<String> dateRange = new ArrayList<>();
	    if (time.equals("Date")) {
	        // lấy 7 ngày gần nhất
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        LocalDate currentDate = LocalDate.now();		 
	        for (int i = 0; i < 7; i++) {
	            String formattedDate = currentDate.minusDays(i).format(formatter);
	            dateRange.add(formattedDate);
	        }
	        Collections.reverse(dateRange);
	    } else if (time.equals("Month")) {
	        // lấy 12 tháng		 
	        for (int i = 0; i < 12; i++) {
	            dateRange.add(i+1+"");
	        }
	        System.out.print(dateRange);
	    }
	    
	    List<ReponseThongKeProduct> response = dateRange.stream()
	        .map(date -> {
	            List<ReponseThongKeProduct> templ = new ArrayList<>();
	            orderItems.stream()
	                .filter(item -> {	
	                	if(time.equals("Month")) {
	                		int month = Arrays.stream(item.getOrder().getUpdateat().split("/"))
	                                .mapToInt(Integer::parseInt)
	                                .toArray()[1];
		                     return String.valueOf(month).equalsIgnoreCase(date);
	                	}
	                	else 
	                		return item.getOrder().getUpdateat().equals(date);
	                
	                	 
	                })
	                .forEach(item -> {
	                    boolean flag = false;
	                    for (ReponseThongKeProduct thongke : templ) {
	                        if(thongke.getProduct().getId().equals(item.getProduct().getId())) {
	                            flag = true;
	                            thongke.setCount(thongke.getCount() + item.getCount());
	                            break;
	                        }
	                    }
	                    if(!flag) {
	                        templ.add(new ReponseThongKeProduct(item.getProduct(), item.getCount()));
	                    }
	                });
	            if (templ.size() > 0) {
	                ReponseThongKeProduct thongKeProduct = templ.get(0);
	                for (ReponseThongKeProduct thongke : templ) {
	                    if (thongke.getCount() > thongKeProduct.getCount()) {
	                        thongKeProduct.setProduct(thongke.getProduct());
	                        thongKeProduct.setCount(thongke.getCount());
	                    }
	                }
	                return thongKeProduct;
	            } else {
	                return new ReponseThongKeProduct();
	            }
	        })
	        .collect(Collectors.toList());

	    return response;
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
	
	@PostMapping("user/notreview")
	public List<OrderItem> getOrderByUserNotReview(@RequestBody User user) {
	    List<Order> orders = orderSerive.findByUser(user);
	    List<OrderItem> orderItems = new ArrayList<>();
	    List<Review> lview = reviewService.findByUser(user);

	    for (Order order : orders) {
	        orderItems.addAll(service.findByOrder(order));
	    }

	    List<OrderItem> chuadanhgia = new ArrayList<>(orderItems);
	    chuadanhgia.removeAll(lview);

	    Iterator<OrderItem> iterator = chuadanhgia.iterator();
	    while (iterator.hasNext()) {
	        OrderItem item = iterator.next();
	        if (item.getOrder().getStatusOrder() != StatusOrder.DAGIAO) {
	            iterator.remove();
	        }
	    }

	    return chuadanhgia;
	}
	
}
