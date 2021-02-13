package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name ="Employees")
@Table(name = "employeess")

public class Employees {
	@Id
	private Integer employeeId;
	
	@NotBlank(message="firstName should not blank")
	private String firstName;
	
	@NotBlank(message="lastName should not blank")
	private String lastName;
	
	@NotBlank(message="email should not blank")
	private String email;
	
	@NotBlank(message="address should not blank")
	private String address;
	
	private int phoneNumber;
	
	@NotBlank(message="qualification should not blank")
	private String qualification;
	

}
