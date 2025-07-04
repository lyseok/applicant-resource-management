package kr.or.ddit.mapper.recruitment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.recruitment.ApplicantAnswerVO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class ApplicantAnswerMapperTest {
	
	@Autowired
	ApplicantAnswerMapper mapper;

	@Test
	void testSelectApplicantAnswerList() {
		mapper.selectApplicantAnswerList().forEach(code -> {
			log.info("{}", code);		
		});
	}

	@Test
	void testSelectApplicantAnswer() {
		log.info("{}", mapper.selectApplicantAnswer("APAS000001"));
	}

	@Test
	void testInsertApplicantAnswer() {
		ApplicantAnswerVO vo = new ApplicantAnswerVO();
		vo.setRecruitExamNo("REEX000001");
		vo.setRecruitQuestionsNo("REQE000001");
		vo.setApplicantId("APPL000001");
		vo.setApplicantScore("90");
		assertEquals(1, mapper.insertApplicantAnswer(vo));
	}

	@Test
	void testUpdateApplicantAnswer() {
		ApplicantAnswerVO vo = new ApplicantAnswerVO();
		vo.setApplicantScore("85");
		vo.setApplicantAnswerNo("APAS000001");
		assertEquals(1, mapper.updateApplicantAnswer(vo));
	}

	@Test
	void testDeleteApplicantAnswer() {
		assertEquals(1, mapper.deleteApplicantAnswer("APAS000001"));
	}

}
