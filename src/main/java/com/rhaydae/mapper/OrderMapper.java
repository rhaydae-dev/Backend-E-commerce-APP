package com.rhaydae.mapper;

import com.rhaydae.dto.OrderRequest;
import com.rhaydae.dto.OrderResponse;
import com.rhaydae.entity.Order;
import com.rhaydae.entity.Product;

public class OrderMapper {
	
	public static OrderResponse toResponse(Order order) {
		return new OrderResponse(order.getId(), 
				order.getOrderDate(), 
				order.getTotalAmount(), 
				order.getProduct().stream().map(Product ::getName).toList(),
		        order.getUser().getUsername());
	}

}
