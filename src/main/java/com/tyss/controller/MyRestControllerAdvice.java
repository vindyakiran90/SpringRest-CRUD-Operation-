package com.tyss.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tyss.dto.ProductResponse;
import com.tyss.exception.ProductException;

@RestControllerAdvice
public class MyRestControllerAdvice {

	@ExceptionHandler
	public ProductResponse myExceptionHandler(ProductException productException) {
		ProductResponse response = new ProductResponse();
		response.setError(true);
		response.setMessage(productException.getMessage());
		return response;
	}
}
