package com.rhaydae.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rhaydae.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByNameContainingAndPriceBetween(String name, BigDecimal minPrice, BigDecimal maxPrice);
	List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
	List<Product> findByNameContaining(String name);

}
