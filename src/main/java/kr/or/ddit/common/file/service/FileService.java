package kr.or.ddit.common.file.service;

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
	
	public void saveUploadFile(MultipartFile file, String s3Url) {
		FilesVO vo = new FilesVO();
		vo.setRealFile(file.getOriginalFilename());
		vo.setFileName(UUID.randomUUID() + "_" + file.getOriginalFilename());
		vo.setFileSize(file.getSize());
		vo.setFileType(file.getContentType());
		vo.setFilePath(s3Url);
		fileMapper.insertFile(vo);
	}
	
	public void deleteUnusedImages(String fileNo) {
        fileMapper.deleteFile(fileNo);
    }

    public void updateUsedImages(FilesVO vo) {
    	
        fileMapper.updateSource(vo);
    }
}
