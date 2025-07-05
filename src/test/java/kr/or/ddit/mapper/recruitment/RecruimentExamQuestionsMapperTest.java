package kr.or.ddit.mapper.recruitment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.recruitment.RecruitmentExamQuestionsVO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class RecruimentExamQuestionsMapperTest {
	
	@Autowired
	RecruimentExamQuestionsMapper mapper;

	@Test
	void testSelectRecrExamQuestList() {
		mapper.selectRecrExamQuestList().forEach(code -> {
			log.info("{}", code);		
		});
	}

	@Test
	void testSelectRecrExamQuest() {
		log.info("{}", mapper.selectRecrExamQuest("REQE000001"));
	}

	@Test
	void testInsertRecrExamQuest() {
		RecruitmentExamQuestionsVO vo = new RecruitmentExamQuestionsVO();
		vo.setRecruitExamNo("REEX000001");
		vo.setRecruitExamQuestContent("테스트 문제 1");
		assertEquals(1, mapper.insertRecrExamQuest(vo));
	}

	@Test
	void testUpdateRecrExamQuest() {
		RecruitmentExamQuestionsVO vo = new RecruitmentExamQuestionsVO();
		vo.setRecruitExamNo("REEX000001");
		vo.setRecruitExamQuestContent("테스트 문제 업데이트 1");
		vo.setRecruitExamQuestNo("REQE000001");
		assertEquals(1, mapper.updateRecrExamQuest(vo));
	}

	@Test
	void testDeleteRecrExamQuest() {
		assertEquals(1, mapper.deleteRecrExamQuest("REQE000001"));
	}

}
