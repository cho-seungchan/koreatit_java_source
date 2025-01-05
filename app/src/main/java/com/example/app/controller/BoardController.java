package com.example.app.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.domain.dto.Criteria;
import com.example.app.domain.dto.Search;
import com.example.app.domain.vo.BoardVO;
import com.example.app.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

	private final BoardService boardService;
	
	@GetMapping("/list")
	public String getBoardList(Search search, Criteria criteria, Model model) {  //Criteria :: 페이지 분할을 위한 객체
		List<BoardVO> boards = boardService.getBoardList(search, criteria);
		model.addAttribute("boards", boards);
		
		return "/board/list";
	}
	
	@GetMapping("/write")
	public String getBoardWrite(Model model) {
		model.addAttribute("boardVO", new BoardVO());
		return "/board/write";
	}
	
	@PostMapping("/write")
	public String postBoardWrite(@ModelAttribute("boardVO") BoardVO boardVO) {
		boardService.postBoardWrite(boardVO);
		return "redirect:/board/list";
	}
	
	@GetMapping(value = {"read", "modify"})  // 두 url을 동시에 처리. return이 없으면 url 맞춰서 read.html, modify.html로 맞춰서 호출
	                                         // modify는 post 처리만 하도록 수정했음
	public void getBoardReadBoardNO(@RequestParam("boardNo") long boardNo, Model model) {
		BoardVO boardVO = boardService.getBoardReadBoardNo(boardNo);
		model.addAttribute("boardVO", boardVO);
	}
	
	@PostMapping("/modify")
	public String postBoardModify(@ModelAttribute("boardVO") BoardVO boardVO, Model model ) {
		boardService.postBoardModify(boardVO);
//		return "forward:/board/list";  그대로 전달하는 방식이라서 /board/list 에 postmapping이 있어야 처리가 됨.
		return "redirect:/board/list";
	}
	
	@PostMapping("/remove")
	public String postBoardRemove(@ModelAttribute("boardVO") BoardVO boardVO, Model model ) {
		boardService.postBoardRemove(boardVO);
		return "redirect:/board/list";
	}
	
}
