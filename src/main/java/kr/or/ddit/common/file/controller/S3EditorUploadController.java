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
@RequestMapping("/upload")
@RequiredArgsConstructor
@Slf4j
public class S3EditorUploadController {

	private final S3Uploader s3Uploader;
	private final FileService fileService;
	
	 @PostMapping("/editor")
	    public ResponseEntity<Map<String, String>> uploadEditorImage(@RequestParam("file") MultipartFile file) {
	        try {
	        	log.info("업로드 요청: {}", file.getOriginalFilename());
	        	
	            String s3Url = s3Uploader.upload(file);
	            fileService.saveUploadFile(file, s3Url, 1);
	            return ResponseEntity.ok(Map.of("url", s3Url));
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
	                .body(Map.of("error", "업로드 실패"));
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
