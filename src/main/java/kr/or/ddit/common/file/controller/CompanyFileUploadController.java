package kr.or.ddit.common.file.controller;

import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.file.S3Uploader;
import kr.or.ddit.common.file.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@RestController
@RequestMapping("/upload/company")
@RequiredArgsConstructor
@Slf4j
public class CompanyFileUploadController {
	private final S3Uploader s3Uploader;
	private final FileService fileService;
	
	 @PostMapping("/editor")
	    public ResponseEntity<Map<String, String>> uploadEditorImage(
	    		@RequestParam("file") MultipartFile file,
	    		@RequestParam(value = "source", required = false) Integer source) {
	        try {
	        	log.info("업로드 요청: {}", file.getOriginalFilename());
	        	
	            String s3Url = s3Uploader.upload(file);
	            log.info("uploadEditorImage - uploaded to S3: {}", s3Url);
	            fileService.saveUploadFile(file, s3Url, 827);
	            log.info("uploadEditorImage - metadata saved for fileSource=2");
	            
	            if (source != null && source == 827) {
	                fileService.saveUploadFile(file, s3Url, 827);
	                log.info("파일 메타데이터 저장됨 (fileSource=827)");
	            } else {
	                log.info("fileSource 저장 생략 (source={})", source);
	            }
	            return ResponseEntity.ok(Map.of("url", s3Url));
	        } catch (Exception e) {
	        	log.error("uploadEditorImage - error occurred", e);
	            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
	                                 .body(Map.of("error", e.getMessage()));
	        }
	    }

	    @DeleteMapping("/editor")
	    public ResponseEntity<String> deleteEditorImage(@RequestParam String url) {
			try {
				s3Uploader.delete(url);
				fileService.deleteUnusedImages(List.of(url), List.of());
				return ResponseEntity.ok("삭제 성공");
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("삭제 실패");
			}
		}
	    
	
}
