package com.rezr.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rezr.blog.model.User;
//dao																			//User테이블, 그 테이블의 primarykey
//자동으로 bean 등록(@Repository 생략 가능)
public interface UserRepository extends JpaRepository<User, Integer>{

}
