package kr.or.ddit.mapper.recruitment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.recruitment.InterviewItemVO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class InterviewItemMapperTest {

	@Autowired
	InterviewItemMapper mapper;
	
	@Test
	void testSelectInterviewItemList() {
		mapper.selectInterviewItemList().forEach(code -> {
			log.info("{}", code);		
		});
	}

	@Test
	void testSelectInterviewItem() {
		log.info("{}", mapper.selectInterviewItem("INTE000001"));
	}

	@Test
	void testInsertInterviewItem() {
		InterviewItemVO vo = new InterviewItemVO();
		vo.setInterviewQuestionNo("INQE000001");
		vo.setInterviewItemContent("전혀 그렇지 않다");
		vo.setInterviewItemScore("10");
		assertEquals(1, mapper.insertInterviewItem(vo));
	}

	@Test
	void testUpdateInterviewItem() {
		InterviewItemVO vo = new InterviewItemVO();
		vo.setInterviewItemContent("그렇지 않다");
		vo.setInterviewItemScore("20");
		vo.setInterviewItemNo("INTE000001");
		assertEquals(1, mapper.updateInterviewItem(vo));
	}

	@Test
	void testDeleteInterviewItem() {
		assertEquals(1, mapper.deleteInterviewItem("INTE000001"));
	}

}
