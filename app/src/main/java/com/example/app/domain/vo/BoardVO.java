package com.example.app.domain.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardVO {
    
    private long   boardNo;
    private String boardTitle;
    private String boardWriter;
    private String boardContent;
    private String boardRegDate;
    private String boardUpdateDate;
   
}
