package com.dac.usersoft.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Author : rajgs
 * Date   : 31 Dec 2024
 * Time   : 12:03:31 pm
 * 
 * Hibernate with JPA - Spring Boot JPA
*/
@Entity
@Table(name="users")
public class User {
	
	@Id  //Primary key field - by default notnull & unique
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Auto Numbering
	private Long userId;

    @Column(nullable = false, unique = true)//Not NUll & Unique Constraint
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    //Generate Getters & Setters
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    

}
