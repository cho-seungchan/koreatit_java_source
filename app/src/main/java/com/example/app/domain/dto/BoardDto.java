package com.example.app.domain.dto;

import java.util.List;

import com.example.app.domain.vo.FileVO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardDto {
    
    private long   boardNo;
    private String boardTitle;
    private String boardWriter;
    private String boardContent;
    private String boardRegDate;
    private String boardUpdateDate;
    private List<FileVO> files;
	@Override
	public String toString() {
		return "BoardDto [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardWriter=" + boardWriter
				+ ", boardContent=" + boardContent + ", boardRegDate=" + boardRegDate + ", boardUpdateDate="
				+ boardUpdateDate + ", files=" + files + "]";
	}    
    
   
}
