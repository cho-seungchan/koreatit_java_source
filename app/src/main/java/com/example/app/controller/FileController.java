package com.example.app.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/files")
public class FileController {
	private final FileService fileService;
	
    @Value("${file.root.path}")  // application.properties에서 경로 설정 "C:/KoreaIT/images/"
    private String rootPath;

	
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
		File file = new File(rootPath, filePathName);
		return FileCopyUtils.copyToByteArray(file);
	}
	

    @GetMapping("/download")
    public ResponseEntity<Resource> download(@RequestParam("filePath") String filePath) throws UnsupportedEncodingException {
/*    	System.out.println("filePath received: " + filePath); 
    	File file = new File("C:/KoreaIT/images/"+filePath);
    	System.out.println("File exists: " + file.exists());
    	System.out.println("Is File: " + file.isFile());
    	System.out.println("Can Read: " + file.canRead()); */
    	
    	
        Resource resource = new PathResource(rootPath + filePath);
        HttpHeaders header = new HttpHeaders();
        String name = resource.getFilename();
        header.add("Content-Disposition", "attachment;filename=" + new String(name.substring(name.indexOf("_") + 1).getBytes("UTF-8"), "ISO-8859-1"));
        return new ResponseEntity<>(resource, header, HttpStatus.OK);
    }

}
