package com.example.app.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import com.example.app.domain.vo.FileVO;
import com.example.app.service.FileService;

import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;

@RestController
@RequiredArgsConstructor
@RequestMapping("/files")
public class FileController {
	private final FileService fileService;
	
	@PostMapping("/upload")
	public List<FileVO> postFilesUpload(@RequestParam("multipartFiles") List<MultipartFile> multipartFiles) 
			throws IllegalStateException, IOException {
		
		return fileService.postFilesUPload(multipartFiles);
		
	}

	@GetMapping("/display")
	public byte[] getFilesDisplay(@RequestParam("filePathName") String filePathName) 
			throws IOException {
		// 일부 특수 문자(예: 공백, 대괄호 등)는 인코딩
		//String decodedPath = UriUtils.decode(filePathName, StandardCharsets.UTF_8);		
		File file = new File("C:/KoreaIT/images", filePathName);
		return FileCopyUtils.copyToByteArray(file);
	}
}
