package com.example.app.domain.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReplyVO {

	private long     replyNo;
	private String   replyContent;
	private String   replyWriter;
	private String   replyRegisterDate;
	private String   replyUpdateDate;
	private long     boardNo;
	
}
