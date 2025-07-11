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
		log.info("확인 : {}", mapper.selectScrabCompanyByPk("corp03"));
		assertDoesNotThrow(()->mapper.selectScrabCompanyByPk("corp03"));
		assertNotNull(mapper.selectScrabCompanyByPk("corp03"));
	}

	@Test
	void testInsertScrabCompany() {
		ScrabCompanyVO SCV = new ScrabCompanyVO();
		
		SCV.setCompanyId("corp03");
		SCV.setUserId("USR001");
				
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
	void testDeleteScrabCompany() {
		ScrabCompanyVO SCV = new ScrabCompanyVO();
			
		SCV.setCompanyId("QWER5");
		SCV.setUserId("QWE1");
		
		mapper.deleteScrabCompany(SCV);
		
	}

}
