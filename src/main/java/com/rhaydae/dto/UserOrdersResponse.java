package com.rhaydae.dto;

import java.math.BigDecimal;
import java.util.List;

public record UserOrdersResponse(
		String username,
		List<OrderResponse> orders,
		BigDecimal totalAmount)
{}
