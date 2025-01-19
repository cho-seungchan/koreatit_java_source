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
import com.example.app.mapper.BoardMapper;
import com.example.app.mapper.FileMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileServiceImpl implements FileService{
	final private BoardMapper boardMapper;
	
    @Value("${file.root.path}")  // application.properties에서 경로 설정 "C:/KoreaIT/images/"
    private String rootPath;

	@Override
	public List<FileVO> postFilesUPload(List<MultipartFile> multipartFiles, 
			String existingFilesJson) throws IllegalStateException, IOException { // 2025.1.17 String existingFilesJson 추가  
		List<FileVO> files = new ArrayList<>();
		
		// 기존 파일들이 다시 올라왔을 때 
		if (existingFilesJson != null) {
			
            ObjectMapper objectMapper = new ObjectMapper();
            // JSON 문자열 배열을 List<FileVO>로 변환한 후 files에 추가
            List<FileVO> existingFiles = objectMapper.readValue(existingFilesJson, 
            	    objectMapper.getTypeFactory().constructCollectionType(List.class, FileVO.class));
            files.addAll(existingFiles); 
//            System.out.println("existingFilesJson  "+files); 

//          System.out.println("신규 처리 후 "+files);
//    		System.out.println("existingFilesJson files == "+existingFilesJson);
		} 
		
		// 신규 파일들이 올라왔을 때
		if (multipartFiles != null) { // 2025.1.17 추가 :: 신규 파일일 때 path와 썸네일 생성
			
			String datePath = datePath();
			File uploadPath = new File(rootPath, datePath);
			if (!uploadPath.exists()) {
				uploadPath.mkdirs();
			}

			for (MultipartFile multipartFile : multipartFiles) {

				//modyfy service에서는 조회시켜준 썸네일 파일이 올라오기 때문에 size때문에 Thumbnailator.createThumbnail에서 오류가 남
				// fileUploadPath와 fileUuid가 존재하면, 기존에 등록된 파일들이기 때문에 path와 썸네일을 만드는 작업을 하지 않는다.
				//이미 tbl_file에 존재하면 썸네일이 존재하는것으로 판단할 수 있으므로 썸네일용 파일 생성을 못하게 한다.
//				System.out.println(multipartFile.get+" "+uuid+" "+multipartFile.getOriginalFilename()+" "+"  "+multipartFile.getSize());
//				boolean findFile = boardMapper.findFile(rootPath+datePath,uuid,fileName) > 0;

				
				String fileName = multipartFile.getOriginalFilename();
				// 서버에 올라와 있는 파일(이미 /1024되 파일)을 다시 보여주고 올릴때 계속 나눠줘야 함
//				String fileSize = String.format("%.2f", multipartFile.getSize() / 1024.0); 
				String fileSize = String.format("%dB", multipartFile.getSize());
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

//	    		System.out.println("신규 처리 후 "+files);
				
			} 
			
		} 
			
		
//		System.out.println("file service 여기 여기 여기 sysout: " + files.stream()
//	    .map(file -> file.toString()) // FileVO 객체의 toString() 사용
//	    .collect(Collectors.joining(", ")));
		
		System.out.println("최종  "+files);
		return files;
	}

	public String datePath() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MMdd"));
	}

	@Override
	public List<FileVO> yesterFilesInDB() {
		return boardMapper.yesterFilesInDB();
	}
	
}
