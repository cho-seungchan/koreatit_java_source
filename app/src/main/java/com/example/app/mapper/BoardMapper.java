package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.app.domain.dto.BoardDto;
import com.example.app.domain.dto.Criteria;
import com.example.app.domain.dto.Search;
import com.example.app.domain.vo.BoardVO;
import com.example.app.domain.vo.FileVO;

@Mapper
public interface BoardMapper {

	List<BoardVO> getBoardList(@Param("search") Search search, @Param("criteria") Criteria criteria);

	void postBoardWrite(BoardDto boardDto);
	void postFileWrite(FileVO fileVO);               // 신규 파일 등록

	BoardDto getBoardReadBoardNo(@Param("boardNo")    long boardNo);
	List<FileVO> getFileReadBoardNo(@Param("boardNo") long boardNo);

	void postBoardModify(BoardDto boardDto);
	void postFileDelete(BoardDto boardDto);           // 기존 파일 내역 삭제 후 postFileWrite(FileVO fileVO);

	void postBoardRemove(BoardVO boardVO);

	int getTotal(@Param("search") Search search);     // 전체 레코드 수 




}
