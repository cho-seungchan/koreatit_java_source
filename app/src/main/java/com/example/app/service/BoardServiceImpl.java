package com.example.app.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.dto.BoardDto;
import com.example.app.domain.dto.Criteria;
import com.example.app.domain.dto.Search;
import com.example.app.domain.vo.BoardVO;
import com.example.app.domain.vo.FileVO;
import com.example.app.exception.InvalidInputException;
import com.example.app.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
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
	public void postBoardWrite(BoardDto boardDto) throws InvalidInputException {
		if (boardDto.getBoardTitle()   == "" ||
			boardDto.getBoardWriter()  == "" ||
			boardDto.getBoardContent() == "" ) {
			throw new InvalidInputException("입력값이 없습니다.");			
		}
		
		boardMapper.postBoardWrite(boardDto);                 // 게시글 등록

//		if (boardDto.getFiles() != null) {
//			System.out.println("service postBoardWrite"+boardDto.getFiles().stream()
//					.map(file -> file.toString()).collect(Collectors.joining(", ")));
//		} else {
//			System.out.println("service postBoardWrite boardDto.getFiles()는 null");
//		}
		
		if (boardDto.getFiles() != null && !boardDto.getFiles().isEmpty()) {  // 파일 등록
			for (FileVO fileVO : boardDto.getFiles()) {
				System.out.println("file data insert 구간");
				fileVO.setBoardNo(boardDto.getBoardNo());    // postBoardWrite.xml에서 return
				boardMapper.postFileWrite(fileVO);
			}
		}
	}

	@Override
	@Transactional
	public BoardDto getBoardReadBoardNo(long boardNo) {
		
		BoardDto boardDto = boardMapper.getBoardReadBoardNo(boardNo);
		System.out.println(" read boardDto "+boardDto.toString());
		
		List<FileVO> fileVO = boardMapper.getFileReadBoardNo(boardNo);	
		boardDto.setFiles(fileVO);
		
		if (boardDto.getFiles() != null) {
			System.out.println("service getBoardReadBoardNo"+boardDto.getFiles().stream()
					.map(file -> file.toString()).collect(Collectors.joining(", ")));
		} else {
			System.out.println("service getBoardReadBoardNo boardDto.getFiles()는 null");
		}

		return boardDto;
	}

	@Override
	@Transactional
	public void postBoardModify(BoardDto boardDto) {

		boardMapper.postBoardModify(boardDto);
		boardMapper.postFileDelete(boardDto);
		
//		if (boardDto.getFiles() != null) {
//			System.out.println("service postBoardModify  "+boardDto.getFiles().stream()
//					.map(file -> file.toString()).collect(Collectors.joining(", ")));
//		} else {
//			System.out.println("service postBoardModify boardDto.getFiles()는 null");
//		}
		
		if (boardDto.getFiles() != null && !boardDto.getFiles().isEmpty()) {
			for (FileVO fileVO : boardDto.getFiles()) {
				fileVO.setBoardNo(boardDto.getBoardNo());
				boardMapper.postFileWrite(fileVO);
			}
		}
	}

	@Override
	public void postBoardRemove(BoardVO boardVO) {
		boardMapper.postBoardRemove(boardVO);
	}	
	
	
}
