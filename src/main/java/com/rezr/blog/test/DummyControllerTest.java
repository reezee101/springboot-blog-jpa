package com.rezr.blog.test;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rezr.blog.model.RoleType;
import com.rezr.blog.model.User;
import com.rezr.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	@Autowired	//di
	private UserRepository userRepository ;
	
	@GetMapping("/dummy/detail/{id}")
	public User detail(@PathVariable int id) {
		
		//User user = userRepository.findById(id);	findById : return이 Optional로 감싸서 가져와지므로(결과 null 방지) null 체크해야함 
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저가 존재하지 않습니다.");
			}
		
		});
		return user;
	}

	@PostMapping("/dummy/join")
	public String join(User u) {	//@RequestParam없이 그냥 매개변수를 쓰면 body 값을 토대로 읽어옴(key=value)

		//u.setRole("user")
		u.setRole(RoleType.USER);	//범위가 정해져있는 데이터 사용할 때 사용 
		userRepository.save(u);

		return "회원가입이 완료되었습니다!!!";
	}
	
}
