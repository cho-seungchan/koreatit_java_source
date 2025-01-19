package com.example.app.task;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.app.domain.vo.FileVO;
import com.example.app.service.FileService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FileTask {    // DB에 저장되지 않은 PC 파일 삭제
	
	private final FileService fileService;
	
    @Value("${file.root.path}")  // application.properties에서 경로 설정 "C:/KoreaIT/images/"
    private String rootPath;
    
//	 ─ ─ ─ ─ ─ ─ ─
//	  |  |  |  |  |  |
//	  |  |  |  |  |  └─ Day of the Week (0 - 6) (Sunday = 0 or 7)
//	  |  |  |  |  └───── Month (1 - 12)
//	  |  |  |  └─────── Day of the Month (1 - 31)
//	  |  |  └────────── Hour (0 - 23)
//	  |  └───────────── Minute (0 - 59)
//	  └─────────────── Second (0 - 59)
//	"0/10 * * * * *"는 매 10초마다 실행
//	"0 0/5 * * * *": 매 5분마다 실행
//	"0 15 10 * * *": 매일 10시 15분에 실행
//	"0 0 12 1 * ?": 매달 1일 12시에 실행
//	"0 0 12 ? * 2": 매주 월요일 12시에 실행
	
    @Scheduled(cron = "0 0 0/2 * * * ") // 매 2시간 0분0초, 즉 정각에 실행. cron = "0 0 * * * * " 매 시간 정각 실행
	public void checkFiles() throws IOException{
        System.out.println("======================================");
        System.out.println("File Checking Task Run................");
        System.out.println("File Checking Task Run................");
        System.out.println("======================================");

        // DB 에서 어제 날짜에 등록된 파일들 조회
        List<FileVO> filesOfYesterdayInDb = fileService.yesterFilesInDB();
        if (filesOfYesterdayInDb == null || filesOfYesterdayInDb.isEmpty()) return;
        
        // filesOfYesterdayInDb를 읽어서 Path 추가한 리스트 생성. 
        // Path는 자바 표준 라이브러리에서 제공되는 객체이며, Paths.get()을 통해 쉽게 생성
        List<Path> pathYesterFilesInDB = filesOfYesterdayInDb.stream()
        		.map(file -> Paths.get(rootPath, file.getFileUploadPath(), file.getFileUuid()+"_"+file.getFileName()))
        		.collect(Collectors.toList());
        
        // filesOfYesterdayInDb를 읽어서 "t_" 들어간 이미지 파일을 Path에 추가
        filesOfYesterdayInDb.stream().filter(file -> file.isFileIsImage())
        	.map(file -> Paths.get(rootPath, file.getFileUploadPath(), "t_"+file.getFileUuid()+"_"+file.getFileName()))
        	.forEach(path -> pathYesterFilesInDB.add(path));
        
        // 만들어진 경로 출력
        System.out.println("======= 어제 테이블에 등록된 파일 리스트 =======");
        pathYesterFilesInDB.stream().map(path -> path.toAbsolutePath()) // 절대 경로로 변환. 이미 절대경로이면 그대로 반환
        	.map(path -> path.toString()).forEach(path -> System.out.println(path) );
        
        // PC에 있는 실제 경로를 FILE 객체에 담기
        System.out.println("============== 서버 실제 경로 ===============");
        System.out.println(getUploadPathOfYesterday());
        File filesInDirectory = Paths.get(rootPath, getUploadPathOfYesterday()).toFile();

        // DB에 없고 PC에 있는 파일들 삭제
        Arrays.asList(filesInDirectory.listFiles(file -> !pathYesterFilesInDB.contains(file.toPath())))
        	.forEach(file -> file.delete());
        
	}
	
	public String getUploadPathOfYesterday() {
		SimpleDateFormat dateForm = new SimpleDateFormat("yyyy/MMdd"); // date format 정하기
		Calendar yesterday = Calendar.getInstance();  // 오늘 날짜를 Calender 객체로 가져오기
		yesterday.add(Calendar.DATE, -1);  // -1 은 어제 날짜 => XML 파일에도 동시 적용 필요
		
		return dateForm.format(yesterday.getTime());  // 수정한 어제 날짜를 dateForm의 형태변환
	}
	
	
}
