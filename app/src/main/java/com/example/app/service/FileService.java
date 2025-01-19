package com.example.app.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.app.domain.vo.FileVO;

public interface FileService {

	List<FileVO> postFilesUPload(List<MultipartFile> multipartFiles, String existingFilesJson) 
			throws IllegalStateException, IOException;

	List<FileVO> yesterFilesInDB();

}
