package com.rezr.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice	//exception이 발생하면 이 어노테이션이 있는 class로 옴 
@RestController
public class GlobalExceptionHandler {

	//IllegalArgumentException이면 이 로직 수행 
	@ExceptionHandler(value = IllegalArgumentException.class)
	public String handleArgumentException(IllegalArgumentException e) {
		return "<h1>" + e.getMessage() + "</h1>";
	}
	
}
