package com.rezr.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Member {

	private int id;
	private String username;
	private String password;
	private String email;

	//builder pattern 장점 : 매개변수가 다른 생성자를 재정의 할 필요 없이 빌드할 때 자유자재로 빼고 객체 생성 가능함, 순서 상관없음 
	@Builder
	 public Member(int id, String username, String password, String email){
		 this.id = id;
		 this.username = username;
		 this.password = password;
		 this.email = email;
	 }
	
}
