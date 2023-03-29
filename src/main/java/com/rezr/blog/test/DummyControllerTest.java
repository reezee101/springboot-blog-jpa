package com.rezr.blog.test;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rezr.blog.model.RoleType;
import com.rezr.blog.model.User;
import com.rezr.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	@Autowired	//di
	private UserRepository userRepository ;
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	//한 페이지당 2건의 user data
	@GetMapping("/dummy/user")
	public List<User> list(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> pageUser = userRepository.findAll(pageable);
		List<User> users = pageUser.getContent();
		return users;
	}
	
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		
		//User user = userRepository.findById(id);	findById : return이 Optional로 감싸서 가져와지므로(결과 null 방지) null 체크해야함 

//		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
//
//			@Override
//			public IllegalArgumentException get() {
//				// TODO Auto-generated method stub
//				return new IllegalArgumentException("해당 유저가 존재하지 않습니다.");
//			}
//		
//		});
		
		//람다
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("해당 유저가 존재하지 않습니다.");
		});
		
		//user라는 객체는 jave object이지만,springboot에서는 gson 라이브러리 없이도 (json으로 변환)
		//message converter 가 jackson 라이브러리를 호출해서 알아서 json으로 변환해준다 
		return user;
	}

	@PostMapping("/dummy/join")
	public String join(User u) {	//@RequestParam없이 그냥 매개변수를 쓰면 body 값을 토대로 읽어옴(key=value)

		//u.setRole("user")
		u.setRole(RoleType.USER);	//범위가 정해져있는 데이터 사용할 때 사용 
		userRepository.save(u);

		return "회원가입이 완료되었습니다!!!";
	}
	
	//@Transactional 을 붙이면 save()함수 호출 없이도 더티체킹을 통하여 update 쿼리를 실행함 
	@Transactional
	@PutMapping("/dummy/user/{id}")			//json data 받기용
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		
		//영속화
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		
		
		//영속화가 된 데이터가 변경되면 @Transactional 어노테이션을 붙은 메서드가 종료될 때 commit 이 일어남
		//commit 시 1차캐시에 있는 영속화 된 객체와 값 비교(더티체킹)을 하고 변화가 있으면 flush시킴 
		user.setEmail(requestUser.getEmail());
		user.setPassword(requestUser.getPassword());
		
		//userRepository.save(user); 	//save : id 있으면 update, 없으면 insert
		
		return user;
	}
	
	@DeleteMapping("/dummy/user/{id}")
	public String deleteUser(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다.";
		}
		return "삭제되었습니다.";
	}
	
}
