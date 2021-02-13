package com.example.demo.exception;

public class EmployeeNotFoundException extends Exception {
	
	public EmployeeNotFoundException(String message)
	{
		super(message);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
