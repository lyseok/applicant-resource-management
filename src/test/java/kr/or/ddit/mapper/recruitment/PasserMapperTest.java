package kr.or.ddit.mapper.recruitment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.recruitment.PasserVO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class PasserMapperTest {

	@Autowired
	PasserMapper mapper;
	
	@Test
	void testSelectPasserList() {
		mapper.selectPasserList().forEach(code -> {
			log.info("{}", code);		
		});
	}

	@Test
	void testSelectPasser() {
		log.info("{}", mapper.selectPasser("PASS000001"));
	}

	@Test
	void testInsertPasser() {
		PasserVO vo = new PasserVO();
		vo.setApplicantId("APPL000001");
		vo.setHireDate("20250720");
		assertEquals(1, mapper.insertPasser(vo));
	}

	@Test
	void testUpdatePasser() {
		PasserVO vo = new PasserVO();
		vo.setHireDate("20250722");
		vo.setPassAlarmYn("Y");
		vo.setRecruitAcceptYn("Y");
		vo.setPasserNo("PASS000001");
		assertEquals(1, mapper.updatePasser(vo));
	}

	@Test
	void testDeletePasser() {
		assertEquals(1, mapper.deletePasser("PASS000001"));
	}

}
