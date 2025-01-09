package com.dac.producthive.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Author : rajgs
 * Date   : 31 Dec 2024
 * Time   : 5:03:47 pm
 * 
 * Hibernate with Spring Boot JPA
*  Hibernate is a ORM Tool -> Object - Relational Mapping
 * 
 * Spring Boot JPA is a Java specification for managing relational data in Java applications. 
 * It allows us to access and persist data between Java object/ class and relational database. 
 * JPA follows Object-Relation Mapping (ORM). It is a set of interfaces. 
 * It also provides a runtime EntityManager API for processing queries and transactions on the objects 
 * against the database. 
 * It uses a platform-independent object-oriented query language JPQL (Java Persistent Query Language).
 * JPA is implemented with Annotations
*/
/*
 * Model class for Managing products using Hibernate -JPA
 * The @Entity annotation specifies that the class is an entity and is 
 * mapped to a database table.
*/

//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
@Entity
public class Product {
	
	/*
	 * The @Id annotation specifies the primary key of an entity.
	 * @GeneratedValue provides for the specification of generation strategies for the values of primary keys.
	 * @SequenceGenerator, you can specify the sequence name, initial value, and allocation size for the 
	 * sequence. 
	 * This allows you to control the sequence generation process and ensure database consistency. 
	 */
		 @Id
		 @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "prod_seq")
		 @SequenceGenerator(name="prod_seq",initialValue = 1000,allocationSize = 1)
	   	 private Long pid;
		 
		 @Column(nullable = false)
	     private String name;
		 
		 @Column(nullable = false)
	     private String brand;
		 
		 @Column(nullable = false)
	     private String madein;
		 
		 @Column(nullable = false)
	     private float price;

		public Long getPid() {
			return pid;
		}

		public void setPid(Long pid) {
			this.pid = pid;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getBrand() {
			return brand;
		}

		public void setBrand(String brand) {
			this.brand = brand;
		}

		public String getMadein() {
			return madein;
		}

		public void setMadein(String madein) {
			this.madein = madein;
		}

		public float getPrice() {
			return price;
		}

		public void setPrice(float price) {
			this.price = price;
		}

		public Product(Long pid, String name, String brand, String madein, float price) {
			this.pid = pid;
			this.name = name;
			this.brand = brand;
			this.madein = madein;
			this.price = price;
		}

		public Product() {
		}
		 
		 

}
