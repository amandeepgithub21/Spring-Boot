package com.dac.producthive.model;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Address {
	
	

	@Id  //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Auto Numbering from 1
    private Long addressId;
	
	private @NonNull String street;
	private @NonNull String city;
	private  int pincode;
	
	/* Foreign key Relationship*/
	@OneToOne             //One-One Mapping
	@JoinColumn(name="dealer_id") //Foreign key field
	private Dealer dealer; //Reference Class Object

	
	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPincode() {
		return pincode;
	}

	

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}
	
	public Address(String street, String city, int pincode) {
		this.street = street;
		this.city = city;
		this.pincode = pincode;
	}

	public Address() {
	}

	public Address(Long addressId, String street, String city, int pincode, Dealer dealer) {
		this.addressId = addressId;
		this.street = street;
		this.city = city;
		this.pincode = pincode;
		this.dealer = dealer;
	}
}