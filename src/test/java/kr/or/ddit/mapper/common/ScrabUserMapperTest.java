package kr.or.ddit.mapper.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.common.ScrabUserVO;
import lombok.extern.slf4j.Slf4j;
@SpringBootTest
@Slf4j
class ScrabUserMapperTest {

	@Autowired
	ScrabUserMapper mapper;
	
	@Test
	void testSelectScrabUserList() {
		mapper.selectScrabUserList().forEach(code->{
			log.info("{}",code);
		});
	}

	@Test
	void testSelectScrabUserByPk() {
ScrabUserVO SUV = new ScrabUserVO();
		
		SUV.setCompanyId("COMID");
		SUV.setUserId("USERID");
		
		mapper.selectScrabUserByPk(SUV);
	}

	@Test
	void testInsertScrabUser() {
		ScrabUserVO SUV = new ScrabUserVO();
		
		SUV.setCompanyId("COMID");
		SUV.setUserId("USERID");
		
		mapper.insertScrabUser(SUV);
	}

	@Test
	void testUpdateScrabUser() {
ScrabUserVO SUV = new ScrabUserVO();
		
		SUV.setCompanyId("COMID");
		SUV.setUserId("USERID");
		
		mapper.updateScrabUser(SUV);
	}

	@Test
	void testDeleteScrabUser() {
ScrabUserVO SUV = new ScrabUserVO();
		
		SUV.setCompanyId("COMID");
		SUV.setUserId("USERID");
		
		mapper.deleteScrabUser(SUV);
	}

}
