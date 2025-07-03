package kr.or.ddit.mapper.recruitment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.recruitment.InterviewVO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class InterviewMapperTest {
	
	@Autowired
	InterviewMapper mapper;
		
	@Test
	void testSelectInterviewList() {
		mapper.selectInterviewList().forEach(code -> {
			log.info("{}", code);		
		});

	}

	@Test
	void testSelectInterview() {
		log.info("{}", mapper.selectInterview("INTV000001"));
	}

	@Test
	void testInsertInterview() {
		InterviewVO vo = new InterviewVO();
		vo.setProcessNo("PROC000001");
		vo.setUserId("corp01");
		vo.setInterviewLocation("115010");
		vo.setInterviewType("V");
		vo.setInterviewPassScore("60");
		assertEquals(1, mapper.insertInterview(vo));
	}

	@Test
	void testUpdateInterview() {
		InterviewVO vo = new InterviewVO();
		vo.setProcessNo("PROC000001");
		vo.setUserId("corp01");
		vo.setInterviewLocation("115010");
		vo.setInterviewType("V");
		vo.setInterviewPassScore("80");
		vo.setInterviewDate("2025/07/03");
		vo.setInterviewNo("INTV000001");
		assertEquals(1, mapper.updateInterview(vo));
	}

	@Test
	void testDeleteInterview() {
		fail("Not yet implemented");
	}

}
