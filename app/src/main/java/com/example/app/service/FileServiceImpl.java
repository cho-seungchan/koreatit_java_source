package com.example.app.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.app.domain.vo.FileVO;
import com.example.app.mapper.FileMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileServiceImpl implements FileService{
	
    @Value("${file.root.path}")  // application.properties에서 경로 설정 "C:/KoreaIT/images/"
    private String rootPath;

	@Override
	public List<FileVO> postFilesUPload(List<MultipartFile> multipartFiles) 
			throws IllegalStateException, IOException {
//		System.out.println("file service 여기 여기 여기 sysout");
		
		List<FileVO> files = new ArrayList<>();
		
		String datePath = datePath();
		File uploadPath = new File(rootPath, datePath);
		if (!uploadPath.exists()) {
			uploadPath.mkdirs();
		}

		for (MultipartFile multipartFile : multipartFiles) {
			
			String fileName = multipartFile.getOriginalFilename();
			// 서버에 올라와 있는 파일(이미 /1024되 파일)을 다시 보여주고 올릴때 계속 나눠줘야 함
//			String fileSize = String.format("%.2f", multipartFile.getSize() / 1024.0); 
			String fileSize = String.format("%d B", multipartFile.getSize());
			String uuid = UUID.randomUUID().toString();
			File save = new File(uploadPath, uuid+"_"+fileName);
			multipartFile.transferTo(save);
			
			//image 파일 일 때 썸네일용 파일 생성
			boolean isImage = multipartFile.getContentType().startsWith("image");
			if (isImage) {
				FileOutputStream out = new FileOutputStream(new File(uploadPath, "t_"+uuid+"_"+fileName));
				System.out.println("파일 :: "+fileName+" "+fileSize+" "+isImage);
				Thumbnailator.createThumbnail(multipartFile.getInputStream(), out, 100, 100);
				out.close();				
			}
			
			FileVO fileVO = new FileVO();
			fileVO.setFileName(fileName);       
			fileVO.setFileUploadPath(datePath); 
			fileVO.setFileUuid(uuid);       
			fileVO.setFileSize(fileSize);       
			fileVO.setFileIsImage(isImage);  
			
			files.add(fileVO);
			
		}
		
//		System.out.println("file service 여기 여기 여기 sysout: " + files.stream()
//	    .map(file -> file.toString()) // FileVO 객체의 toString() 사용
//	    .collect(Collectors.joining(", ")));
		
		return files;
	}

	public String datePath() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MMdd"));
	}
	
	
}
