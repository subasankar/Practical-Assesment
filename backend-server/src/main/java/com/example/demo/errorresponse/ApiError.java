package com.example.demo.errorresponse;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.hibernate.validator.cfg.context.Constrainable;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class ApiError implements Serializable {
	
	private HttpStatus status;
	private LocalDateTime timeStamp;
	private String message;
	private String debugMessage;
	private List<ApiSubError> subErrors;
	
	
	public ApiError() {	
		this.timeStamp = LocalDateTime.now();
	}


	public ApiError(HttpStatus status) {
		this();
		this.status = status;
	}

	public ApiError(HttpStatus status,Throwable th) {
		this();
		this.status = status;
		this.message ="Unexpected error";	
		this.debugMessage = th.getLocalizedMessage();

	}


	public ApiError(HttpStatus status, String message,Throwable th) {
		this();
		this.status = status;
		this.message = message;
		this.debugMessage = th.getLocalizedMessage();
	}
	
	private void addSubError(ApiSubError subError)
	{
		if(subErrors == null)
		{
			subErrors = new ArrayList<>();
		}
		subErrors.add(subError);
	}
	
	private void addValidationError(String object, String field, Object rejectedValue, String message)
	{
		addSubError(new ApiValidationError(object, field,rejectedValue,message));
		
	}
	
	private void addValidationError(String object,String message)
	{
		addSubError(new ApiValidationError(object,message));
		
	}
	
	
	public void addValidationError(FieldError fieldError)
	{
		this.addValidationError(
				fieldError.getObjectName(),
				fieldError.getField(),
				fieldError.getRejectedValue(),
				fieldError.getDefaultMessage());
	}
	
	
	public void addValidationError(List<FieldError> fieldErrors)
	{
		fieldErrors.forEach(this::addValidationError);
	}
	
	
	public void addValidationError(ObjectError objectError)
	{
		this.addValidationError(objectError.getObjectName(),
				objectError.getDefaultMessage());
	}
	
	
	
	private void addValidationError(ConstraintViolation<?> cv)
	{
		this.addValidationError(cv.getRootBeanClass().getSimpleName(),
				((PathImpl) cv.getPropertyPath()).getLeafNode().asString(),
				cv.getInvalidValue(),
				cv.getMessage());
	}
	
	

	
	public void addViolationErrors(Set<ConstraintViolation<?>> constraintViolation) {
		
		constraintViolation.forEach(this::addValidationError);
	}
	
	public HttpStatus getStatus() {
		return status;
	}
	
}
