package com.rezr.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity	//mysql 테이블 생성 
//@DynamicInsert	//null 인 값 제외시키고 insert
public class User {

	@Id	//primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//전략 : 프로젝트에 연결된 디비의 넘버링 전략을 사용 (오라클 : 시퀀스, mysql : 오토인크리)	
																						// use-new-id-generator-mappings: false : jpa 기본 넘버링전략
	private int id;	//시퀀스. 오토인크리
	
	@Column(nullable = false, length = 30)
	private String username;	
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, length = 30)
	private String email;
	
//	@ColumnDefault("'user'")
	@Enumerated(EnumType.STRING)	//해당 enum 타입이 String
	private RoleType role;	//Enum 권장 
	
	@CreationTimestamp	//시간 자동입력
	private Timestamp createDate;
	
//	private Timestamp updateDate;
	
}
