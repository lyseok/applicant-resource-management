package kr.or.ddit.common.file;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class S3Uploader {

	private final AmazonS3 amazonS3;
	
	@Value("${aws.s3.bucketName}")
	private String bucketName;
	
	private String generateFileName(String originalName) {
		return LocalDate.now() + "/" + UUID.randomUUID() + "_" + originalName;
	}
	
	private String extractKeyFromUrl(String fileUrl) {
		String bucketUrl = amazonS3.getUrl(bucketName, "").toString();
		return fileUrl.replace(bucketUrl, "");
	}
	
	public String upload(MultipartFile file) throws AmazonServiceException, SdkClientException, IOException {
		String fileName = generateFileName(file.getOriginalFilename());
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(file.getSize());
		metadata.setContentType(file.getContentType());
		
		amazonS3.putObject(bucketName, fileName, file.getInputStream(), metadata);
		return amazonS3.getUrl(bucketName, fileName).toString();
	}
	
	public void delete(String fileUrl) {
        String key = extractKeyFromUrl(fileUrl);
        amazonS3.deleteObject(bucketName, key);
    }
	
}
