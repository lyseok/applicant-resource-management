package kr.or.ddit.company.recruitment.notice.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class EditorUploadController {
	@Value("${file.upload-dir}")
	private String uploadBaseDir;
	
	@PostMapping("/upload/editor")
	public Map<String, Object> upload(@RequestParam("file") MultipartFile file) throws IOException{
		Map<String, Object> result = new HashMap<>();
		if(file.isEmpty()) {
			result.put("success", false);
			return result;
		}
		String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));
		Path saveDir = Paths.get(uploadBaseDir, today);
		Files.createDirectories(saveDir);
		
		String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
	    Path savePath = saveDir.resolve(fileName);
	    file.transferTo(savePath);
		
		String url = "/upload/editor" + today + "/" + fileName;
		
		result.put("success", true);
		result.put("url", url);
		return result;
	}
	
	// 파일 삭제
    @DeleteMapping("/upload/editor")
    public Map<String, Object> delete(@RequestParam("url") String url) {
        Map<String, Object> result = new HashMap<>();

        try {
            // 파일 경로에서 파일명 추출
            String filePath = url.replace("/upload/editor", "");
            Path fileToDelete = Paths.get(uploadBaseDir, filePath);

            // 파일이 존재하면 삭제
            if (Files.exists(fileToDelete)) {
                Files.delete(fileToDelete);
                result.put("success", true);
                result.put("message", "파일이 성공적으로 삭제되었습니다.");
            } else {
                result.put("success", false);
                result.put("message", "파일을 찾을 수 없습니다.");
            }
        } catch (IOException e) {
            result.put("success", false);
            result.put("message", "파일 삭제 실패");
            e.printStackTrace();
        }

        return result;
    }
}
