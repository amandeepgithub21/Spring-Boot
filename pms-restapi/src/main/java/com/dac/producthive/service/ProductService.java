package com.dac.producthive.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dac.producthive.model.Product;
import com.dac.producthive.repository.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		
		this.productRepository = productRepository;
	} 
	
	
	public Product saveProduct(Product p) {
		return productRepository.save(p); // invoked pre defined mehtod  save()of jpA repository 
	}
	
	
	public List<Product> listAll(){
		return productRepository.findAll();
		}
	
	// Optional return type is to handle Null Pointer Exception
	   public Optional<Product> getSingleProduct(long pid) {
		   return productRepository.findById(pid);       //Invokes pre-defined method findById() of JPA repository
	   }
	   
	   public void deleteProduct(long pid) {
		   productRepository.deleteById(pid); //Invokes pre-defined method deleteById() of JPA repository
	   }
	   
	   public List<Product> searchProductsByName(String name){
		   return productRepository.findProductsByNameContainingIgnoreCase(name); //Invokes method with custom query
	   }

	   


	   
	   
}
