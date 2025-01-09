package com.dac.producthive.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dac.producthive.exception.ResourceNotFoundException;
import com.dac.producthive.model.Product;
import com.dac.producthive.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {
   
	
	
	//Field DI
	@Autowired
	private ProductService productService;
	
	@PostMapping("/products")
	public ResponseEntity<Product> saveProduct(@Validated @RequestBody Product  p) {
		//TODO: process POST request
		try {
			
		Product prod1=productService.saveProduct(p);
		return ResponseEntity.status(HttpStatus.CREATED).body(prod1);
		
		} catch(Exception ex)
		{
			 ex.printStackTrace();
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
					 
		}
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> displayAllProducts(){
		try {
			List<Product> products=productService.listAll();
			return ResponseEntity.ok(products);
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	/*
	 * The @PathVariable annotation is used to extract the value from the URI. 
	 * It is most suitable for the RESTful web service where the URL contains some value.
	 */
	
	//Open PostMan/Browser, make a GET Request - http://localhost:8086/producthive/api/products/1004
	//Exception handling is done with Custom Exceptions
	@GetMapping("/products/{pid}")
	public ResponseEntity<Product> getProductById(@PathVariable(value="pid") Long pId) 
			throws ResourceNotFoundException{

		Product p=productService.getSingleProduct(pId).orElseThrow(() ->
		new  ResourceNotFoundException("Product Not Found for this Id : "+pId)); //invokes constructor of ResourceNotFoundException class

		return ResponseEntity.ok(p);
	}
	
	
	//Open PostMan, make a PUT Request - http://localhost:8086/producthive/api/products/1004
	//Select body -> raw -> JSON 
	//Update JSON product object with new Values.
	@PutMapping("/products/{pid}")
	public ResponseEntity<Product> updateProduct(@PathVariable(value="pid") Long pId,
			@Validated @RequestBody Product p) throws ResourceNotFoundException {

		Product product=productService.getSingleProduct(pId).
				orElseThrow(() -> new ResourceNotFoundException("Product Not Found for this Id :"+pId));
	
		//Update product with new values
		product.setBrand(p.getBrand());
		product.setMadein(p.getMadein());
		product.setName(p.getName());
		product.setPrice(p.getPrice());

		final Product updatedProduct=productService.saveProduct(product); // invokes service layer method
		return ResponseEntity.ok().body(updatedProduct);
	}
	
	
	//Open PostMan, make a DELETE Request - http://localhost:8086/producthive/api/products/1004
			@DeleteMapping("/products/{pid}")
			public ResponseEntity<Map<String,Boolean>> deleteProduct(@PathVariable(value="pid") Long pId) 
					throws ResourceNotFoundException {

				productService.getSingleProduct(pId).  // invokes service layer method
				orElseThrow(() -> new ResourceNotFoundException("Product Not Found for this Id :"+pId));

				productService.deleteProduct(pId); // invokes service layer method

				Map<String,Boolean> response=new HashMap<>(); //Map Stores Data in key-value pairs
				response.put("Deleted", Boolean.TRUE);
				
				return ResponseEntity.ok(response);
			}
				
				/*
				 * In Spring Boot, RequestParam is a standard annotation used to inject request parameters 
				 * from a web request into a controller method. 
				 * It is often used to extract data from HTTP requests, such as form parameters or query parameters. 
				 * When a controller method is annotated with @RequestParam, the method parameter is 
				 * bound to the value of the corresponding request parameter. 
				 * For example, @RequestParam("name") String name would bind the name method parameter to 
				 * the value of the "name" request parameter.
				 * 	
				 * ResponseEntity<?> indicates that the body of the response can be any type, 
				 * making it a generic reusable class.
				 */
				// GET Request - http://localhost:8086/producthive/api/search?name=Lap top
				@GetMapping("/search")
			    public ResponseEntity<?> searchProductsByName(@RequestParam("name") String name) {
			        try {
			            List<Product> products = productService.searchProductsByName(name);
			            
			            if (products.isEmpty()) {
			                return new ResponseEntity<>("No products found with the given name.", HttpStatus.NOT_FOUND);
			            }
			            
			            return new ResponseEntity<>(products, HttpStatus.OK);
			        } catch (Exception ex) {
			        	//database error
			            return new ResponseEntity<>("An error occurred while searching for products.", HttpStatus.INTERNAL_SERVER_ERROR);
			        }
			    }
				
				// Client (POSTMAN/Browser) --> request --->FC --> Controller ---> Service --> Repository --> JPA --> DB(MySQL)
				
				// DB - Response --> JPA --> Repository --> Service ---> Controller ---> FC ---> PostMan/Browser
				
			}
			
	

