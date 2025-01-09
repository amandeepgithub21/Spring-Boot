package com.dac.producthive.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dac.producthive.exception.ResourceNotFoundException;
import com.dac.producthive.model.Address;
import com.dac.producthive.model.Dealer;
import com.dac.producthive.model.DealerAndAddressProjection;
import com.dac.producthive.service.DealerService;

@RestController
@RequestMapping("/api")
public class DealerController {

	
	private final DealerService dservice;

	public DealerController(DealerService dservice) {
	
		this.dservice = dservice;
	}
	
	
	//Open PostMan, make a POST Request - http://localhost:8086/producthive/api/register
			//Select body -> raw -> JSON 
			//Insert JSON Dealer object.
			@PostMapping("/register")
			public ResponseEntity<String> createDealer(@Validated @RequestBody Dealer dealer){
				try {
					Address address=dealer.getAddress(); //get data from secondary Table

					//Establish bi-directional Mapping
					address.setDealer(dealer);
					dealer.setAddress(address);

					Dealer registeredDealer=dservice.registerDealer(dealer); //save dealer details
					if(registeredDealer !=null) {
						return ResponseEntity.ok("Registration Successfull");
					}else {
						return ResponseEntity.badRequest().body("Registration Failed");
					}
				}catch(Exception e) {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
							body("An Error Occurred :"+e.getMessage());
				}
				
			}
			
			
			//Open PostMan, make a POST Request - http://localhost:8086/producthive/api/login
			//Select body -> raw -> JSON 
			//Insert JSON Dealer object with email & password.
		@PostMapping("/login")
		public ResponseEntity<Boolean> loginDealer(@Validated @RequestBody Dealer dealer) 
				throws ResourceNotFoundException
		{
			Boolean isLogin=false;
			String email = dealer.getEmail();
			String password =  dealer.getPassword();
			
			Dealer d= dservice.loginDealer(email).orElseThrow(() -> //Invokes loginDealer() method with email parameter
			new ResourceNotFoundException("Dealer doen't Exists :: " +email));
			
			if(email.equals(d.getEmail()) && password.equals(d.getPassword())) {
				isLogin=true;
			}
			return ResponseEntity.ok(isLogin);
		}
			
		//Open Postman/Browser - http://localhost:8086/producthive/api/dealers
		@GetMapping("/dealers")
		public ResponseEntity<List<DealerAndAddressProjection>> getDealerInfo(){
			try {
				List<DealerAndAddressProjection> selectedFields=dservice.getDealerInfo();
				
				return ResponseEntity.ok(selectedFields);
			}catch(Exception e) {
				e.printStackTrace();
				
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			}
		}
}
