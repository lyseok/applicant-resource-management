package kr.or.ddit.mapper.recruitment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.recruitment.InterviewQuestionVO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class InterviewQuestionMapperTest {

	@Autowired
	InterviewQuestionMapper mapper;
	
	@Test
	void testSelectInterviewQuestionList() {
		mapper.selectInterviewQuestionList().forEach(code -> {
			log.info("{}", code);		
		});
	}

	@Test
	void testSelectInterviewQuestion() {
		log.info("{}", mapper.selectInterviewQuestion("INQE000001"));
	}

	@Test
	void testInsertInterviewQuestion() {
		InterviewQuestionVO vo = new InterviewQuestionVO();
		vo.setInterviewNo("INTV000001");
		vo.setInterviewQuestionContent("분란을 일으키지 않는 사람입니까?");
		assertEquals(1, mapper.insertInterviewQuestion(vo));
	}

	@Test
	void testUpdateInterviewQuestion() {
		InterviewQuestionVO vo = new InterviewQuestionVO();
		vo.setInterviewQuestionContent("인내심 깊은 사람인가?");
		vo.setInterviewQuestionNo("INQE000001");
		assertEquals(1, mapper.updateInterviewQuestion(vo));
	}

	@Test
	void testDeleteInterviewQuestion() {
		assertEquals(1, mapper.deleteInterviewQuestion("INQE000001"));
	}

}
