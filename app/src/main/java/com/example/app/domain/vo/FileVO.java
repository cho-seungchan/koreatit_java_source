package com.example.app.domain.vo;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FileVO {

	private long    fileNo;
	private String  fileName;
	private String  fileUploadPath;
	private String  fileUuid;
	private String  fileSize;
	private boolean fileIsImage;
	private long    boardNo;
	
	@Override
	public String toString() {
		return "FileVO [fileNo=" + fileNo + ", fileName=" + fileName + ", fileUploadPath=" + fileUploadPath
				+ ", fileUuid=" + fileUuid + ", fileSize=" + fileSize + ", fileIsImage=" + fileIsImage + ", boardNo="
				+ boardNo + "]";
	}
	
	
}
