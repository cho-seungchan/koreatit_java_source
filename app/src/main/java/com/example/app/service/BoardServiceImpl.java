package com.example.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.dto.Criteria;
import com.example.app.domain.dto.Search;
import com.example.app.domain.vo.BoardVO;
import com.example.app.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final BoardMapper boardMapper;

	@Override
	@Transactional
	public List<BoardVO> getBoardList(Search search, Criteria criteria) {
		
		criteria.create(boardMapper.getTotal(search));  // 페이지 분할 값 세팅
		
		List<BoardVO> list = boardMapper.getBoardList(search, criteria);
		
		return list;
	}

	@Override
	@Transactional
	public void postBoardWrite(BoardVO boardVO) {
		boardMapper.postBoardWrite(boardVO);
	}

	@Override
	@Transactional
	public BoardVO getBoardReadBoardNo(long boardNo) {
		return boardMapper.getBoardReadBoardNo(boardNo);
	}

	@Override
	@Transactional
	public void postBoardModify(BoardVO boardVO) {
		boardMapper.postBoardModify(boardVO);
		
	}

	@Override
	public void postBoardRemove(BoardVO boardVO) {
		boardMapper.postBoardRemove(boardVO);
	}	
	
	
}
