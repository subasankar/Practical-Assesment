package com.example.demo.controller;

import java.util.List;
import java.util.Optional;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.exception.NoDataFoundException;
import com.example.demo.model.Employees;
import com.example.demo.repository.EmployeesRepository;

@CrossOrigin(origins ="http://localhost:3000")
@RestController()
@RequestMapping("/employeess")
public class EmployeesController {
	
	@Autowired
	EmployeesRepository employeesRepository;
	
	//for getting welcome message
	@GetMapping
	public String getEmployee()
	{
		return "Welcome to Our Company";
	}
	
	//for getting all employee details
	@GetMapping("/all")
	public List<Employees> getAllEmployees() throws NoDataFoundException
	{
		return Optional.ofNullable(
				employeesRepository.findAll().isEmpty() ? null :employeesRepository.findAll()
				).orElseThrow(()-> new NoDataFoundException("No records found"));
	}
	
	
	//for getting employee details based on the given employeeId 
	@GetMapping("/{employeeId}")
	public ResponseEntity<?> getEmployeesById(@PathVariable("employeeId") Integer id) throws EmployeeNotFoundException
	{
		Optional<Employees> optional = employeesRepository.findById(id);
		if(optional.isPresent())
		{
			return ResponseEntity.ok(optional.get());
		}
		else
		{
			throw new EmployeeNotFoundException("NO employee");
		}
		
	}
	
	//for update the employee based on the given employeeId
	@PutMapping("/{employeeId}")
	public ResponseEntity<Employees> updateEmployees(@PathVariable("employeeId") 
	Integer id, @Valid @RequestBody Employees employees)
	throws EmployeeNotFoundException
	{
		Employees employees2 = employeesRepository.findById(id).
				orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found"));
		employees2.setFirstName(employees.getFirstName());
		employees2.setLastName(employees.getFirstName());
		employees2.setEmail(employees.getEmail());
		employees2.setAddress(employees.getAddress());
		employees2.setPhoneNumber(employees.getPhoneNumber());
		employees2.setQualification(employees.getQualification());
		
		final Employees newEmp = employeesRepository.save(employees2);
		return ResponseEntity.ok(newEmp);
		
	}
	
	//for entering the new emploee details
	@PostMapping
	public Employees addEmployees(@RequestBody @Valid Employees employees) throws NoDataFoundException
	{
		if(employees.getEmployeeId() == null)
				throw new NoDataFoundException("No emplooye found");
		return employeesRepository.save(employees);
	}
	
	//for deleting the employee based on the given employeeId
	@DeleteMapping("/{employeeId}")
	public void deleteEmployees(@PathVariable("employeeId") Integer id) throws EmployeeNotFoundException {
		
		Optional<Employees> optional = employeesRepository.findById(id);
		
		if(optional.isPresent())
		{
			employeesRepository.deleteById(id);
		}
		else
		{
			throw new EmployeeNotFoundException("No record found");
		}
	}
}
