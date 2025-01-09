package com.dac.producthive.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dac.producthive.model.Dealer;
import com.dac.producthive.model.DealerAndAddressProjection;
import com.dac.producthive.repository.DealerRepository;






@Service
public class DealerService {

	
	@Autowired
	private DealerRepository drepo;

	
	public Dealer registerDealer(Dealer d) {
		return drepo.save(d); // invokes pre-defined methods of JPA repo
	}
	
	public Optional<Dealer> loginDealer(String email) {
		return drepo.findByEmail(email); //Invokes custom method of JPA repo
	}
	
	public List<DealerAndAddressProjection> getDealerInfo() {
		return drepo.findSelectedFieldsFromDealerAndAddress(); //Invokes Custom Query method
	}
	
}
