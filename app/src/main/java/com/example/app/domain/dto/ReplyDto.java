package com.example.app.domain.dto;

import java.util.List;

import com.example.app.domain.vo.ReplyVO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReplyDto {
	
	private List<ReplyVO> replies; 
	private int countOfNextPage;
	
	public ReplyDto(List<ReplyVO> replies, int countOfNextPage) {
		super();
		this.replies = replies;
		this.countOfNextPage = countOfNextPage;
	}
	
}
