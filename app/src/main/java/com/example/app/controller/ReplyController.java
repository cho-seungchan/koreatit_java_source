package com.example.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.domain.dto.ReplyDto;
import com.example.app.domain.vo.ReplyVO;
import com.example.app.service.ReplyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/replies")
@RequiredArgsConstructor
public class ReplyController {

	private final ReplyService replyService;
	
	@PostMapping("/write")
	public void postReplyWrite(@RequestBody ReplyVO replyVO) {
		replyService.postReplyWrite(replyVO);
	}
	
	@GetMapping("/list/{boardNo}/{replyPage}/{rowCount}")
	public ReplyDto getReplyList(@PathVariable("boardNo") long boardNo, @PathVariable("replyPage") int replyPage, @PathVariable("rowCount") int rowCount,
					@RequestParam(value="replyType", required = false) String replyType, @RequestParam(value="replyKeyword", required = false) String replyKeyword) {
		return replyService.getReplyList(boardNo, replyPage, rowCount, replyType, replyKeyword);
	};
}
