package kr.or.ddit.mapper.resume;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import kr.or.ddit.vo.resume.MyLicenseVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class MyLicenseMapperTest {
	@Autowired
	MyLicenseMapper mapper;

	@Test
	void testSeletMyLicenseList() {
		mapper.seletMyLicenseList().forEach(lic -> {
			log.info("{}", lic);
		});
	}

	@Test
	void testSeletMyLicenseDetail() {
		MyLicenseVO vo = mapper.seletMyLicenseDetail("ML00000006");
		log.info("{}", vo);
	}

	@Test
	void testInsertMyLicense() {
		MyLicenseVO vo = new MyLicenseVO();
		vo.setResumeNo("RSM001");
		vo.setLicenseCode("??");
		vo.setLicensePassDate("20220406");
		mapper.insertMyLicense(vo);
		mapper.seletMyLicenseDetail("ML001");
		log.info("{}", vo);
	}

	@Test
	void testUpdateMyLicense() {
		MyLicenseVO vo = new MyLicenseVO();
		vo.setMyLicense("ML00000006");
		vo.setResumeNo("RSM001");
		vo.setLicenseCode("33");
		vo.setLicensePassDate("2024-04-01");
		mapper.updateMyLicense(vo);
		
		MyLicenseVO result = mapper.seletMyLicenseDetail(vo.getMyLicense());
		log.info("{}", result);
	}

	@Test
	void testDeleteMyLicense() {
		mapper.deleteMyLicense("ML00000006");
	}

}
