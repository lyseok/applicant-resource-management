package kr.or.ddit.mapper.recruitment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.recruitment.ApplicantRecordVO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class ApplicantRecordMapperTest {

	@Autowired
	ApplicantRecordMapper mapper;
	
	@Test
	void testSelectApplicantRecordList() {
		mapper.selectApplicantRecordList().forEach(code -> {
			log.info("{}", code);		
		});

	}

	@Test
	void testSelectApplicantRecord() {
		log.info("{}", mapper.selectApplicantRecord("APRC000001"));
	}

	@Test
	void testInsertApplicantRecord() {
		ApplicantRecordVO vo = new ApplicantRecordVO();
		vo.setRecruitmentNo("PROC000001");
		vo.setApplicantId("APPL000001");
		vo.setRecruitProcessStep("1");
		vo.setStepPassYn("N");
		vo.setStepApplicationYn("Y");
		assertEquals(1, mapper.insertApplicantRecord(vo));
	}

	@Test
	void testUpdateApplicantRecord() {
		ApplicantRecordVO vo = new ApplicantRecordVO();
		vo.setRecruitProcessStep("1");
		vo.setStepPassYn("N");
		vo.setStepApplicationYn("Y");
		vo.setApplicantRecordNo("APRC000001");
		assertEquals(1, mapper.updateApplicantRecord(vo));
	}

	@Test
	void testDeleteApplicantRecord() {
		assertEquals(1, mapper.deleteApplicantRecord("APRC000001"));
	}

}
