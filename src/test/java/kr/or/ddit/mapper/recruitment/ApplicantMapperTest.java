package kr.or.ddit.mapper.recruitment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.recruitment.ApplicantVO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class ApplicantMapperTest {

	@Autowired
	ApplicantMapper mapper;
	
	@Test
	void testSelectApplicantList() {
		mapper.selectApplicantList().forEach(code -> {
			log.info("{}", code);		
		});

	}

	@Test
	void testSelectApplicant() {
		log.info("{}", mapper.selectApplicant("APPL000001"));
	}

	@Test
	void testInsertApplicant() {
		ApplicantVO vo = new ApplicantVO();
		vo.setRecruitmentNo("RECR000001");
		vo.setResumeNo("RESM000001");
		vo.setUserId("USR001");
		assertEquals(1, mapper.insertApplicant(vo));
	}

	@Test
	void testUpdateApplicant() {
		ApplicantVO vo = new ApplicantVO();
		vo.setResumeViewYn("Y");
		vo.setStatus("N");
		vo.setApplicantId("APPL000001");
		assertEquals(1, mapper.updateApplicant(vo));
	}

	@Test
	void testDeleteApplicant() {
		assertEquals(1, mapper.deleteApplicant("APPL000001"));
	}

}
