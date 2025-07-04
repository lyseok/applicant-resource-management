package kr.or.ddit.mapper.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.common.ScrabCompanyVO;
import lombok.extern.slf4j.Slf4j;
@SpringBootTest
@Slf4j
class ScrabCompanyMapperTest {

	@Autowired
	ScrabCompanyMapper mapper;
	
	@Test
	void testSelectScrabCompanyList() {
		
		mapper.selectScrabCompanyList().forEach(code ->{
			log.info("{}",code);
		});
	}

	@Test
	void testSelectScrabCompanyByPk() {
		ScrabCompanyVO SCV = new ScrabCompanyVO();
		
		SCV.setUserId("QWE1");
		
		
		mapper.selectScrabCompanyByPk(SCV);
		
		
	}

	@Test
	void testInsertScrabCompany() {
		ScrabCompanyVO SCV = new ScrabCompanyVO();
		
		SCV.setCompanyId("QWER5");
		SCV.setUserId("QWE1");
				
		mapper.insertScrabCompany(SCV);
		
	}

	@Test
	void testUpdateScrabCompany() {
		ScrabCompanyVO SCV = new ScrabCompanyVO();
		
		SCV.setCompanyId("ㅂㅈㄷㄱ쇼");
		SCV.setUserId("QWE1");
		
		mapper.updateScrabCompany(SCV);
		mapper.selectScrabCompanyList();
	}

	@Test
	void testDeleteCompany() {
		ScrabCompanyVO SCV = new ScrabCompanyVO();
			
		SCV.setCompanyId("QWER5");
		SCV.setUserId("QWE1");
		
		mapper.deleteCompany(SCV);
		
	}

}
