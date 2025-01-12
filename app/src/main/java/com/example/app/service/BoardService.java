package com.example.app.service;

import java.util.List;

import com.example.app.domain.dto.BoardDto;
import com.example.app.domain.dto.Criteria;
import com.example.app.domain.dto.Search;
import com.example.app.domain.vo.BoardVO;
import com.example.app.exception.InvalidInputException;

public interface BoardService {

	List<BoardVO> getBoardList(Search search, Criteria criteria);

	void postBoardWrite(BoardDto boardDto) throws InvalidInputException;

	BoardDto getBoardReadBoardNo(long boardNo);

	void postBoardModify(BoardDto boardDto);

	void postBoardRemove(BoardVO boardVO);

}
