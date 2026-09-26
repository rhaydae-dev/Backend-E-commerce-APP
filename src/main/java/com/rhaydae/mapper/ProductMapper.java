package com.rhaydae.mapper;

import com.rhaydae.dto.ProductRequest;
import com.rhaydae.dto.ProductResponse;
import com.rhaydae.dto.UserRequest;
import com.rhaydae.dto.UserResponse;
import com.rhaydae.entity.Product;
import com.rhaydae.entity.User;

public class ProductMapper {
	
	public static Product toEntity(ProductRequest dto) {
		return Product.builder()
				.name(dto.name())
				.description(dto.description())
				.price(dto.price())
				.image(dto.image())
				.build();
	}
	
	
	public static ProductResponse toResponse(Product product) {
		return new ProductResponse(
				product.getId(),product.getName(),
				product.getDescription(),
				product.getPrice(),
				product.getImage());
	}

}
