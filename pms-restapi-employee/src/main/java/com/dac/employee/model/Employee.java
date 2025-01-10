package com.dac.employee.model;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.Base64;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;




	
	@Entity
	@Table(name="employee")
	public class Employee {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "employee_seq")
		@SequenceGenerator(name="employee_seq",initialValue = 101,allocationSize = 1)
		@Column(name="eid")
		private Long eid;

		@Column(name="first_name",nullable = false) //not null constraint
		private String fname;

		@Column(name="last_name",nullable = false)
		private String lname;

		@NotBlank(message = "Password cannot be blank")
		@Size(min = 8, max = 255, message = "Password must be between 8 and 255 characters")
		@Column(name = "password", nullable = false)
		private String password;

		@Column(nullable = false)    
		private String dept;
		
		@JsonFormat(pattern="yyyy-MM-dd")
		@Column(nullable = false)
		private Date dob; 	
		
		@Column(nullable = false)    
		private Long sallery;

		public Long getEid() {
			return eid;
		}

		public void setEid(Long eid) {
			this.eid = eid;
		}

		public String getFname() {
			return fname;
		}

		public void setFname(String fname) {
			this.fname = fname;
		}

		public String getLname() {
			return lname;
		}

		public void setLname(String lname) {
			this.lname = lname;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			Base64.Encoder encoder = Base64.getEncoder();  //Use Base64 class for password Encryption
	        String normalString = password;
	        String encodedString = encoder.encodeToString(   // encrypt password in database field
	        normalString.getBytes(StandardCharsets.UTF_8) );
	        this.password = encodedString;
		}

		public String getDept() {
			return dept;
		}

		public void setDept(String dept) {
			this.dept = dept;
		}

		public Date getDob() {
			return dob;
		}

		public void setDob(Date dob) {
			this.dob = dob;
		}

		public Long getSallery() {
			return sallery;
		}

		public void setSallery(Long sallery) {
			this.sallery = sallery;
		}

		public Employee(Long eid, String fname, String lname,
				@NotBlank(message = "Password cannot be blank") @Size(min = 8, max = 255, message = "Password must be between 8 and 255 characters") String password,
				String dept, Date dob, Long sallery) {
			this.eid = eid;
			this.fname = fname;
			this.lname = lname;
			this.password = password;
			this.dept = dept;
			this.dob = dob;
			this.sallery = sallery;
		}

		public Employee() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
		
}
