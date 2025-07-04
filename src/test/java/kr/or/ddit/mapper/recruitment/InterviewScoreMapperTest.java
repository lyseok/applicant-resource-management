package kr.or.ddit.mapper.recruitment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.recruitment.InterviewScoreVO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class InterviewScoreMapperTest {

	@Autowired
	InterviewScoreMapper mapper;
	
	@Test
	void testSelectInterviewScoreList() {
		mapper.selectInterviewScoreList().forEach(code -> {
			log.info("{}", code);		
		});

	}

	@Test
	void testSelectInterviewScore() {
		log.info("{}", mapper.selectInterviewScore("INSC000001"));
	}

	@Test
	void testInsertInterviewScore() {
		InterviewScoreVO vo = new InterviewScoreVO();
		vo.setInterviewNo("INTV000001");
		vo.setInterviewQuestionNo("INQE000001");
		vo.setApplicantId("APPL000001");
		vo.setApplicantRating("30");
		assertEquals(1, mapper.insertInterviewScore(vo));
	}

	@Test
	void testUpdateInterviewScore() {
		InterviewScoreVO vo = new InterviewScoreVO();
		vo.setApplicantRating("50");
		vo.setInterviewScoreNo("INSC000001");
		assertEquals(1, mapper.updateInterviewScore(vo));
	}

	@Test
	void testDeleteInterviewScore() {
		assertEquals(1, mapper.deleteInterviewScore("INSC000001"));
	}

}
