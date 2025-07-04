package kr.or.ddit.mapper.recruitment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.recruitment.ComExamQuestionsVO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class ComExamQuestionsMapperTest {

	@Autowired
	ComExamQuestionsMapper mapper;
	
	@Test
	void testSelectComExamQuestList() {
		mapper.selectComExamQuestList().forEach(code -> {
			log.info("{}", code);		
		});
	}

	@Test
	void testSelectComExamQuest() {
		log.info("{}", mapper.selectComExamQuest("COQE000001"));
	}

	@Test
	void testInsertComExamQuest() {
		ComExamQuestionsVO vo = new ComExamQuestionsVO();
		vo.setComExamNo("COEX000001");
		vo.setComExamContents("최종 프로젝트의 뜻은?");
		assertEquals(1, mapper.insertComExamQuest(vo));
	}

	@Test
	void testUpdateComExamQuest() {
		ComExamQuestionsVO vo = new ComExamQuestionsVO();
		vo.setComExamContents("중간 프로젝트와 최종 프로젝트의 차이점이 아닌 것은?");
		vo.setComQuestionsNo("COQE000001");
		assertEquals(1, mapper.updateComExamQuest(vo));
	}

	@Test
	void testDeleteComExamQuest() {
		assertEquals(1, mapper.deleteComExamQuest("COQE000001"));
	}

}
