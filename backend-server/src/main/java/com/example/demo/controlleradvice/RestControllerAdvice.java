package com.example.demo.controlleradvice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.errorresponse.ErrorResponse;
import com.example.demo.exception.EmployeeNotFoundException;

public class RestControllerAdvice {
	
	public final ResponseEntity<ErrorResponse> handleEmployeeNotFoundException (EmployeeNotFoundException e,WebRequest request){
		
		List<String> details = new ArrayList<String>();
		details.add(e.getLocalizedMessage());
		
		ErrorResponse errorResponse = new ErrorResponse("INNCORRECT DETIALS",details);
		return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	}

}
