package com.rezr.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity	//mysql 테이블 생성 
public class Reply {

	@Id	//primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//전략 : 프로젝트에 연결된 디비의 넘버링 전략을 사용 (오라클 : 시퀀스, mysql : 오토인크리)	
																						// use-new-id-generator-mappings: false : jpa 기본 넘버링전략
	private int id;	//시퀀스. 오토인크리
	
	@Column(nullable = false, length = 200)
	private String content;

	
	@ManyToOne
	@JoinColumn(name = "boardId")
	private Board board;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	@CreationTimestamp
	private Timestamp createTime;
	
}
