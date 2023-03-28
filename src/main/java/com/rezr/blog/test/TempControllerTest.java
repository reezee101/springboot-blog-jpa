package com.rezr.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
	
	@GetMapping("/temp")
	public String tempControllerTest() {
		System.out.println("tempControllerTest 실행");
		
		//@Controller 는 파일 리턴함
		//리턴 기본 경로 : src/main/resources/static
		//static 폴더엔 정적파일만 놓을 수 있음 (컴파일 필요한 jsp파일은 읽어올 수 없음)
		return "/home.html";
	}

}
