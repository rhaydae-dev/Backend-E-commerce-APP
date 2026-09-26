package com.rhaydae.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import com.rhaydae.dto.OrderRequest;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter @Getter
@NoArgsConstructor
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate orderDate;
	private BigDecimal totalAmount;
	@ManyToOne
	private User user;
	@ManyToMany
	private List<Product> product;
	
	  public Order(OrderRequest request, User user, List<Product> products) {
	        this.orderDate = LocalDate.now();
	        this.user = user;
	        this.product = products;
	        this.totalAmount = products.stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
	    }

}
