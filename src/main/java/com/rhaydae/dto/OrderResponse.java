package com.rhaydae.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public record OrderResponse(
		Long id,
        LocalDate orderDate,
        BigDecimal totalAmount,
        List<String> productNames,
        String username) {

}
