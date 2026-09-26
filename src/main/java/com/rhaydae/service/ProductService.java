package com.rhaydae.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rhaydae.dto.ProductRequest;
import com.rhaydae.dto.ProductResponse;
import com.rhaydae.entity.Product;
import com.rhaydae.mapper.ProductMapper;
import com.rhaydae.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	
	private final ProductRepository productRepository;
	
	public ProductResponse saveProduct(ProductRequest dto) {
		Product product = ProductMapper.toEntity(dto);
		Product savedProduct= productRepository.save(product);
		return ProductMapper.toResponse(savedProduct);
	}
	
	public Product getProduct(Long id) {
		return productRepository.findById(id).orElseThrow();
	}
	
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	public Product updateProduct(Long id, Product product) {
		Product updatedProduct= productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("product not found"));
		
		updatedProduct.setName(product.getName());
		updatedProduct.setDescription(product.getDescription());
		updatedProduct.setPrice(product.getPrice());
		updatedProduct.setImage(product.getImage());
		
		return productRepository.save(updatedProduct);
	}
	
	public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
	
	public Product uploadImage(Long id, MultipartFile file) throws IOException {
		Product productConcerned = productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("product id not found"));
		
		String folder ="/upload";
		File directory = new File(folder);
	    if (!directory.exists()) directory.mkdirs();
	    
	    String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
	    Path filepath = Paths.get(folder, filename);
	    Files.write(filepath, file.getBytes());
	    
	    productConcerned.setImage(filename);
	    
		return productRepository.save(productConcerned);
	}
	
	public List<Product> searchProducts(String name, BigDecimal minPrice, BigDecimal maxPrice){
		if(name != null && minPrice !=null && maxPrice !=null) {
			return productRepository.findByNameContainingAndPriceBetween(name,minPrice,maxPrice);
		}
		else if(minPrice!=null && maxPrice != null) {
			return productRepository.findByPriceBetween(minPrice, maxPrice);
		}
		else if(name !=null) {
			return productRepository.findByNameContaining(name);
		}
		
		return productRepository.findAll();
	}

	
	public Page<Product> getAllProductsPaged(int page, int size, String sortBy) {
	    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
	    return productRepository.findAll(pageable);
	}
	
	
}
