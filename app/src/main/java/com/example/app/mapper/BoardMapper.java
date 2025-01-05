package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.app.domain.dto.Criteria;
import com.example.app.domain.dto.Search;
import com.example.app.domain.vo.BoardVO;

@Mapper
public interface BoardMapper {

	List<BoardVO> getBoardList(@Param("search") Search search, @Param("criteria") Criteria criteria);

	void postBoardWrite(BoardVO boardVO);

	BoardVO getBoardReadBoardNo(long boardNo);

	void postBoardModify(BoardVO boardVO);

	void postBoardRemove(BoardVO boardVO);

	int getTotal(@Param("search") Search search);     // 전체 레코드 수 

}
