package com.example.app.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Criteria {
	private int      page;        
	private int      rowCount;
	private int      pageCount;
	private int      startPage;
	private int      endPage;
	private int      realEnd;
	private boolean  prev, next;
	private int      total;
	
	public Criteria() {  // 최초 시작 페이지. 조회하면서 controller를 통해 변경됨.
		this.page = 1;
	}
	
	public void create(int total) {
		this.total = total; // 전체 레코드 수
		rowCount   = 10;    // 한페이지에 보여줄 갯수
		pageCount  = 10;    // 밑에 보여줄 최대 페이지수  1 2 3 4 5 6 7 8 9 10 >> 
		endPage    = (int)Math.ceil(page/(double)pageCount) * pageCount; 
							// 올림이므로 1~10 => 10, 11~20 => 20....
		                    // 1 2 3 4 5 6 7 8 9 10 >> 의 마지막 수 즉, 10, 20, 30....
		startPage  = endPage - pageCount + 1;
                            // 1 2 3 4 5 6 7 8 9 10 >> 의 첫번째 수 즉, 1, 11, 21.....
		realEnd    = (int)Math.ceil(total / (double)pageCount);
		                    // 전체 레코드를 보여줄 총 페이지 수. 267레코드 면 27페이지.	
		
		if (endPage > realEnd) {
			endPage = realEnd == 0 ? 1 : realEnd; // 레코드가 하나도 없을 때 1 로 세팅
		}
		
		this.prev  = startPage > 1;   // startPage = 1, 11, 21.......
		this.next  = endPage < realEnd;
	}

}
