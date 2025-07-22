package kr.or.ddit.common.file;

import java.io.IOException;
import java.io.InputStream;
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
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class S3Uploader {

	private final AmazonS3 amazonS3;
	
	@Value("${aws.s3.bucketName}")
	private String bucketName;
	
	@Value("${aws.s3.region}")
	private String region;
	
	private String generateFileName(String originalName) {
		return LocalDate.now() + "/" + UUID.randomUUID() + "_" + originalName;
	}
	
	private String extractKeyFromUrl(String fileUrl) {
		 String host = "https://" + bucketName + ".s3." + region + ".amazonaws.com/";
		    return fileUrl.replace(host, "");
	}
	
	public String upload(MultipartFile file) throws IOException {
		if (file.isEmpty()) {
			throw new IllegalArgumentException("업로드할 파일이 비어있습니다.");
		}

		String fileName = generateFileName(file.getOriginalFilename());

		try (InputStream inputStream = file.getInputStream()) {
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(file.getSize());
			metadata.setContentType(file.getContentType());

			amazonS3.putObject(bucketName, fileName, inputStream, metadata);
		} catch (SdkClientException e) {
			log.error("S3 업로드 실패: {}", e.getMessage(), e);
			throw e;
		}

		return amazonS3.getUrl(bucketName, fileName).toString();
	}
	
	public void delete(String fileUrl) {
        String key = extractKeyFromUrl(fileUrl);
        amazonS3.deleteObject(bucketName, key);
    }
	
}
