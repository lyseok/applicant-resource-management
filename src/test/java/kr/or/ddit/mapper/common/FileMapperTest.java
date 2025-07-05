package kr.or.ddit.mapper.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.common.FilesVO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class FileMapperTest {

	@Autowired
	FileMapper mapper;

	@Test
	void testSelectFileList() {
		mapper.selectFileList().forEach(code -> {
			log.info("{}", code);
		});
	}

	@Test
	void testSelectFileByPk() {
		FilesVO FV = new FilesVO();

		FV.setFileName("TEST");
		FV.setFileNo("1234");
		FV.setFileSize(1234);
		FV.setFileType("1234");
		FV.setRealFile("TEST");
		
		mapper.selectFileByPk(FV);
	}

	@Test
	void testInsertFile() {
		FilesVO FV = new FilesVO();

		FV.setFileName("TEST");
		FV.setFileSize(1234);
		FV.setFileType("1234");
		FV.setRealFile("TEST");

		mapper.insertFile(FV);
	}

	@Test
	void testUpdateFile() {
		FilesVO FV = new FilesVO();

		FV.setFileName("TEST1");
		FV.setFileNo("1234");
		FV.setFileSize(1234);
		FV.setFileType("TEST1");
		FV.setRealFile("TEST1");
		
		mapper.updateFile(FV);
	}

	@Test
	void testDeleteFile() {
		FilesVO FV = new FilesVO();

		FV.setFileName("TEST1");
		FV.setFileNo("1234");
		FV.setFileSize(1234);
		FV.setFileType("TEST1");
		FV.setRealFile("TEST1");
		
		mapper.deleteFile(FV);
		
	}

}
