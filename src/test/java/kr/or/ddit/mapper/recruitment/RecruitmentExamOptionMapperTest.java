package kr.or.ddit.mapper.recruitment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.recruitment.RecruitmentExamOptionVO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class RecruitmentExamOptionMapperTest {

	@Autowired
	RecruitmentExamOptionMapper mapper;
	
	@Test
	void testSelectRecrExamOptionList() {
		mapper.selectRecrExamOptionList().forEach(code -> {
			log.info("{}", code);		
		});
	}

	@Test
	void testSelectRecrExamOption() {
		log.info("{}", mapper.selectRecrExamOption("REOP000001"));
	}

	@Test
	void testInsertRecrExamOption() {
		RecruitmentExamOptionVO vo = new RecruitmentExamOptionVO();
		vo.setRecruitExamQuestNo("REQE000001");
		vo.setRecruitExamOptionContent("테스트 항목 1");
		vo.setRecruitExamOptionCorrectYn("Y");
		assertEquals(1, mapper.insertRecrExamOption(vo));
	}

	@Test
	void testUpdateRecrExamOption() {
		RecruitmentExamOptionVO vo = new RecruitmentExamOptionVO();
		vo.setRecruitExamOptionContent("테스트 항목 업데이트 1");
		vo.setRecruitExamOptionCorrectYn("Y");
		vo.setRecruitOptionNo("REOP000001");
		assertEquals(1, mapper.updateRecrExamOption(vo));
	}

	@Test
	void testDeleteRecrExamOption() {
		assertEquals(1, mapper.deleteRecrExamOption("REOP000001"));
	}

}
