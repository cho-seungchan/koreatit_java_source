package com.example.app.service;

import java.util.List;

import com.example.app.domain.dto.Criteria;
import com.example.app.domain.dto.Search;
import com.example.app.domain.vo.BoardVO;

public interface BoardService {

	List<BoardVO> getBoardList(Search search, Criteria criteria);

	void postBoardWrite(BoardVO boardVO);

	BoardVO getBoardReadBoardNo(long boardNo);

	void postBoardModify(BoardVO boardVO);

	void postBoardRemove(BoardVO boardVO);

}
