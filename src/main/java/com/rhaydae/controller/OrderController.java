package com.rhaydae.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rhaydae.dto.OrderRequest;
import com.rhaydae.dto.OrderResponse;
import com.rhaydae.dto.UserOrdersResponse;
import com.rhaydae.service.OrderService;
import com.rhaydae.service.ProductService;
import com.rhaydae.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
	
	private final OrderService orderService;
	private final ProductService productService;
	private final UserService userService;
	
	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
    public OrderResponse saveOrder(@RequestBody OrderRequest dto) {
		return orderService.saveOrder(dto);
	}
	
	@GetMapping("/getOrder/{id}")
	public OrderResponse getOrder(@PathVariable Long id) {
		return orderService.getOrder(id);
	}
	
	@GetMapping("/allOrders")
	public List<OrderResponse> getAllOrders(){
		return orderService.getAllOrders();
		}
	
	@PutMapping("/update/{id}")
	public OrderResponse updateOrder(@PathVariable Long id,@RequestBody OrderRequest dto) {
		return orderService.updateOrder(id, dto);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteOrder(@PathVariable Long id) {
		orderService.deleteOrder(id);
	}
	
	@GetMapping("/user/{userId}")
	public UserOrdersResponse OrderByUser(@PathVariable Long userId){
		return orderService.getOrderByUser(userId);
	}

}
