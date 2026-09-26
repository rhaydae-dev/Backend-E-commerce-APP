package com.rhaydae.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rhaydae.dto.OrderRequest;
import com.rhaydae.dto.OrderResponse;
import com.rhaydae.dto.UserOrdersResponse;
import com.rhaydae.entity.Order;
import com.rhaydae.entity.Product;
import com.rhaydae.entity.User;
import com.rhaydae.mapper.OrderMapper;
import com.rhaydae.repository.OrderRepository;
import com.rhaydae.repository.ProductRepository;
import com.rhaydae.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository orderRepository;
	private final UserRepository userRepository;
	private final ProductRepository productRepository;
	
	
	public OrderResponse saveOrder(OrderRequest dto) {
		
		Authentication authentication =
	            SecurityContextHolder.getContext().getAuthentication();

	    String username = authentication.getName();
	    
		User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
		List<Product> product = productRepository.findAllById(dto.productId());
		
		Order order = new Order(dto, user, product);
		Order savedOrder = orderRepository.save(order);
		
		return OrderMapper.toResponse(savedOrder);
	}
	
	public OrderResponse getOrder(Long id) {
		Order order =  orderRepository.findById(id).orElseThrow(() -> new RuntimeException("order id not found"));
		 return OrderMapper.toResponse(order);
		
	}
	
	public List<OrderResponse> getAllOrders(){
		return  orderRepository.findAll()
	             .stream()
	             .map(OrderMapper :: toResponse)
	             .toList();
	}
	
	public UserOrdersResponse getOrderByUser(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("userId not found"));
		
		List<Order> orders = orderRepository.findByUser(user);
	    List<OrderResponse> responses = orders.stream()
	            .map(OrderMapper::toResponse)
	            .toList();
	    
	    BigDecimal totalAmount = orders.stream()
	            .map(Order::getTotalAmount)
	            .reduce(BigDecimal.ZERO, BigDecimal::add);
	    
	    return new UserOrdersResponse(
	            user.getUsername(),
	            responses,
	            totalAmount
	    );
		
		
	}
	
	 public void deleteOrder(Long orderId) {
	        if (!orderRepository.existsById(orderId)) {
	            throw new RuntimeException("Order not found");
	        }
	        orderRepository.deleteById(orderId);
	    }
	 
	 
	 
	 public OrderResponse updateOrder(Long id, OrderRequest dto) {
		 Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("orderId not found"));
		 List<Product> product = productRepository.findAllById(dto.productId());
		 
		 order.setProduct(product);
		 order.setTotalAmount(product.stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
		 
		 Order updatedOrder = orderRepository.save(order);
	        return OrderMapper.toResponse(updatedOrder);
		 
	 }
	
	

}
