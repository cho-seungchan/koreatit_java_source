package com.example.app.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.app.domain.dto.Criteria;
import com.example.app.domain.dto.Search;
import com.example.app.domain.vo.BoardVO;
import com.example.app.exception.InvalidInputException;
import com.example.app.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {

	private final BoardService boardService;
	
	@GetMapping("/list")
	public String getBoardList(Search search, Criteria criteria, Model model) {  //Criteria :: 페이지 분할을 위한 객체
		List<BoardVO> boards = boardService.getBoardList(search, criteria);
		model.addAttribute("boards", boards);
		
		return "/board/list";
	}
	
	@GetMapping("/write")
	public String getBoardWrite(Search search, Criteria criteria, Model model) {
		model.addAttribute("boardVO", new BoardVO());
		return "/board/write";
	}
	
	@PostMapping("/write")
	public String postBoardWrite(@ModelAttribute("boardVO") BoardVO boardVO, 
								 RedirectAttributes redirectAttributes) {
		try {
			boardService.postBoardWrite(boardVO);
			return "redirect:/board/list";
		} catch (InvalidInputException e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
			return "redirect:/board/write"; 
		}
	}
	
	@GetMapping(value = {"read", "modify"})  // 두 url을 동시에 처리. return이 없으면 url 맞춰서 read.html, modify.html로 맞춰서 호출
	                                         // modify는 post 처리만 하도록 수정했음
	public void getBoardReadBoardNO(@RequestParam("boardNo") long boardNo, 
									Search search, Criteria criteria, Model model) {
		BoardVO boardVO = boardService.getBoardReadBoardNo(boardNo);
		model.addAttribute("boardVO", boardVO);
	}
	
	@PostMapping("/modify")
	public String postBoardModify(@ModelAttribute("boardVO") BoardVO boardVO, 
							Search search, Criteria criteria, 
							RedirectAttributes redirectAttributes ) {
		
		boardService.postBoardModify(boardVO);
		
		log.info("boardNo "+boardVO.getBoardNo()+"page "+criteria.getPage()+"type "+search.getType()+"keyWord"+search.getKeyWord());
		
		 // 쿼리 파라미터로 데이터 전달 /board/read?boardNo=123&page=1&type=w&keyWord=example
	    redirectAttributes.addAttribute("boardNo", boardVO.getBoardNo());
	    redirectAttributes.addAttribute("page", criteria.getPage());
	    redirectAttributes.addAttribute("type", search.getType());
	    redirectAttributes.addAttribute("keyWord", search.getKeyWord());
	    
		return "redirect:/board/read";
	}
	
	@PostMapping("/remove")
	public String postBoardRemove(@ModelAttribute("boardVO") BoardVO boardVO, Model model ) {
		boardService.postBoardRemove(boardVO);
		return "redirect:/board/list";
	}
	
}
