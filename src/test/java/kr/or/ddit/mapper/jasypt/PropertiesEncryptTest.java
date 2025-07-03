package kr.or.ddit.mapper.jasypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@ConfigurationProperties("spring.datasource")
@SpringBootTest
class PropertiesEncryptTest {
	String driverClassName;
	String url;
	String username;
	String password;

	@Value("classpath:/application.properties")
	File propertiesFile;

	@Test
	void test() throws FileNotFoundException, IOException {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword("");
		encryptor.setAlgorithm("PBEWithHMACSHA512AndAES_256");
		encryptor.setIvGenerator(new RandomIvGenerator());
		String encrypted = encryptor.encrypt(driverClassName);
		
		log.info("평문 : {}", driverClassName);
		log.info("암호화 결과 : {}", encrypted);
//		
	}

}