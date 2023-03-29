package com.rezr.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
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
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob
	private String content;
	
	@ColumnDefault("0")
	private int count;
						
								//EAGER : board select해올때 무조건 user테이블 값도 들고옴
	@ManyToOne(fetch = FetchType.EAGER)	//many : board, one : user
	@JoinColumn(name = "userId")
	private User user;
	
							//LAZY : board select 해올 떄 relpy 테이블 조인해서 값 있어야지 들고옴(없으면 안들고옴)
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER)	//mappedBy : 연관관계의 주인이 아님(fk가 아님 = db에 컬럼 만들지 않고 join으로 값얻기용임)
	//@JoinColumn(name = "replyId") : 필요없음
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
	
	
}
