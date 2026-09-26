package com.rhaydae.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rhaydae.dto.ProductRequest;
import com.rhaydae.dto.ProductResponse;
import com.rhaydae.entity.Product;
import com.rhaydae.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService productService;
	
	@PostMapping("/saveProduct")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ProductResponse saveProduct(@RequestBody ProductRequest dto) {
		return productService.saveProduct(dto);
	}
	
	@GetMapping("/getProduct/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Product getProduct(@PathVariable Long id) {
		return productService.getProduct(id);
	}
	
	@GetMapping("/getAllProducts")
	@ResponseStatus(HttpStatus.OK)
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}
	
	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.OK)
    public Product updateProduct(@RequestBody Product product, @PathVariable Long id) {
		return productService.updateProduct(id,product);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteProduct(@PathVariable Long id){
		productService.deleteProduct(id);
	}
	
	@PostMapping(value =  "/uploadImage/{id}",     consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Product uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
	    return productService.uploadImage(id, file);
	}

	@GetMapping("/search")
	@ResponseStatus(HttpStatus.OK)
	public List<Product> serachProduct(@RequestParam(required = false) String name, @RequestParam(required = false) BigDecimal minPrice, @RequestParam (required = false) BigDecimal maxPrice) {
		return productService.searchProducts(name, minPrice, maxPrice) ;
	}
	
	@GetMapping("pages/all")
	public Page<Product> getAllProductsPaged(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "5") int size,
	        @RequestParam(defaultValue = "name") String sortBy) {
	    return productService.getAllProductsPaged(page, size, sortBy);
	}
}
