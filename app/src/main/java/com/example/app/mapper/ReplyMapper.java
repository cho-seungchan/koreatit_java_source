package com.example.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.app.domain.vo.ReplyVO;

@Mapper
public interface ReplyMapper {

	void postReplyWrite(ReplyVO replyVO);

	void getReplyList(@Param("boardNO") long boardNo, @Param("replyPage") int replyPage, @Param("rowCount") int rowCount, @Param("replyTypes") String[] replyTypes, @Param("replyKeyword") String replyKeyword);
	int  getCountOfNextPage(@Param("boardNO") long boardNo, @Param("replyPage") int replyPage, @Param("rowCount") int rowCount, @Param("replyTypes") String[] replyTypes, @Param("replyKeyword") String replyKeyword);
}
