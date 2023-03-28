package com.rezr.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//데이터를 반환하는 controller(html 파일 반환시엔 @Controller 사용 )
@RestController
public class HttpControllerTest {

	private static final String TAG = "HttpController";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = Member.builder().username(TAG).build();
		return m.getUsername();
	}
	
	//브라우저에선 get요청만 가능 (나머지 요청은 405)
	//@RequsetParam : queryString 값
	//Member 객체의 값을 알아서 바인딩 해주는 것 : Message Converter
	@GetMapping("/http/get")
	public String getTest(Member m) {
	// TODO Auto-generated constructor stub
		return m.getId() + " getTest " + m.getUsername();
	
	}

	//@RequestBody : body값(json포함)
	@PostMapping("/http/post")
	public String postTest0(@RequestBody Member m) {
	// TODO Auto-generated constructor stub
		return "postTest" + m.getId() + m.getUsername() + m.getEmail();
	
	}

	@PutMapping("/http/put")
	public String putTest() {
	// TODO Auto-generated constructor stub
		return "putTest";
	
	}
	
	@DeleteMapping("/http/delete")
	public String deleteTest() {
	// TODO Auto-generated constructor stub
		return "deleteTest";
	
	}

}
