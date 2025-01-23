package com.example.app.service;

import org.springframework.stereotype.Service;

import com.example.app.domain.dto.ReplyDto;
import com.example.app.domain.vo.ReplyVO;
import com.example.app.mapper.ReplyMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyService {

	private final ReplyMapper replyMapper;
	
	public void postReplyWrite(ReplyVO replyVO) {
		replyMapper.postReplyWrite(replyVO);
	}

	public ReplyDto getReplyList(long boardNo, int replyPage, int rowCount, String replyType, String replyKeyword) {
		ReplyDto replyDto = new ReplyDto();
	    String[] replyTypeArray = replyType != null ? replyType.split("") : new String[0];
		replyMapper.getReplyList(boardNo, replyPage, rowCount, replyTypeArray, replyKeyword);		
		System.out.println("replyMapper.getReplyList");

		replyDto.setCountOfNextPage(replyMapper.getCountOfNextPage(boardNo, replyPage, rowCount, replyTypeArray, replyKeyword));
		
		return replyDto;
	}

}
