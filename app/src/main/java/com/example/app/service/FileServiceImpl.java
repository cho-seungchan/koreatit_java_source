package com.example.app.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.app.domain.vo.FileVO;
import com.example.app.mapper.FileMapper;

import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService{

	private final FileMapper fileMapper;

	@Override
	public List<FileVO> postFilesUPload(List<MultipartFile> multipartFiles) 
			throws IllegalStateException, IOException {
		
		List<FileVO> files = new ArrayList<>();
		
		String rootPath = "C:/KoreaIT/images";
		String datePath = datePath();
		File uploadPath = new File(rootPath, datePath);
		if (!uploadPath.exists()) {
			uploadPath.mkdirs();
		}
		
		for (MultipartFile multipartFile : multipartFiles) {
			
			String fileName = multipartFile.getOriginalFilename();
			String fileSize = String.format("%.2f", multipartFile.getSize() / 1024.0);
			String uuid = UUID.randomUUID().toString();
			File save = new File(uploadPath, uuid+"_"+fileName);
			multipartFile.transferTo(save);
			
			//image 파일 일 때 썸네일용 파일 생성
			boolean isImage = multipartFile.getContentType().startsWith("image");
			if (isImage) {
				FileOutputStream out = new FileOutputStream(new File(uploadPath, "t_"+uuid+"_"+fileName));
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
		
		return files;
	}

	public String datePath() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MMdd"));
	}
	
	
}
