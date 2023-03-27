package com.rezr.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogControllerTest {

	@GetMapping("/test/hello")
	public String BlogControllerTest() {
		// TODO Auto-generated constructor stub
		return "<h1>hello</h1>";
	}
}
