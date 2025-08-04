package kr.or.ddit.common.file.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.file.S3Uploader;
import kr.or.ddit.mapper.common.FileMapper;
import kr.or.ddit.vo.common.FilesVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {

	private final FileMapper fileMapper;
	private final S3Uploader s3Uploader;

	public void saveUploadFile(MultipartFile file, String s3Url, int fileSource) {
		FilesVO vo = new FilesVO();
		vo.setRealFile(file.getOriginalFilename());
		vo.setFileName(UUID.randomUUID() + "_" + file.getOriginalFilename());
		vo.setFileSize(file.getSize());
		vo.setFileType(file.getContentType());
		vo.setFileSource(fileSource);
		vo.setFilePath(s3Url);
		fileMapper.insertFile(vo);
	}

	public void deleteUnusedImages(List<String> uploaded, List<String> used) {
		List<String> unused = uploaded.stream().filter(url -> !used.contains(url)).toList();

		for (String url : unused) {
			s3Uploader.delete(url);
			fileMapper.deleteByFilePath(url);
		}
	}

	public void updateFilesWithOrder(String sourceNo, List<String> urls) {
		for (int i = 0; i < urls.size(); i++) {
			fileMapper.updateSource(sourceNo, urls.get(i), i + 1); // 순서대로 1, 2, 3...
		}
	}

	public String TsaveUploadFile(MultipartFile file, String s3Url, int fileSource) {
		 FilesVO vo = new FilesVO();
	        vo.setRealFile(file.getOriginalFilename());
	        vo.setFileName(UUID.randomUUID() + "_" + file.getOriginalFilename());
	        vo.setFileSize(file.getSize());
	        vo.setFileType(file.getContentType());
	        vo.setFilePath(s3Url);
	        vo.setFileSource(fileSource);
	        fileMapper.insertFile(vo);
	        return vo.getFileNo(); // 파일 번호 리턴
	    }

}
