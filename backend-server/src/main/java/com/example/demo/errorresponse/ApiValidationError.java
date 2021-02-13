package com.example.demo.errorresponse;

import java.io.Serializable;

import lombok.Data;

@Data
public class ApiValidationError extends ApiSubError implements Serializable {
	
	private String object;
	private String field;
	private Object rejectedValue;
	private String message;
	
	public ApiValidationError(String object, String field, Object rejectedValue, String message) {
		super();
		this.object = object;
		this.field = field;
		this.rejectedValue = rejectedValue;
		this.message = message;
	}

	public ApiValidationError(String object, String message) {
		super();
		this.object = object;
		this.message = message;
	} 
	
	
	
	
}
