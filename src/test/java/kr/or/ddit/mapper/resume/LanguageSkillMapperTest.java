package kr.or.ddit.mapper.resume;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.resume.LanguageSkillVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class LanguageSkillMapperTest {
	
	@Autowired
	LanguageSkillMapper mapper;
	
	@Test
	void testSeletLanguageSkillList() {
		List<LanguageSkillVO> skillList = mapper.seletLanguageSkillList();
		skillList.forEach(skill -> {
			log.info("{}", skill);
		});
	}

	@Test
	void testSeletLanguageSkillDetail() {
		LanguageSkillVO vo = mapper.seletLanguageSkillDetail("LT001");
		log.info("{}", vo);
	}

	@Test
	void testInsertLanguageSkill() {
		LanguageSkillVO vo = new LanguageSkillVO();
		vo.setResumeNo("RSM003");
		vo.setLanguageExamCode("일본어");
		vo.setLanguageCode("2");
		vo.setLanguageExamName("JLPT");
		vo.setPassDate("202306");
		vo.setLanguageExamType("R");
		vo.setLanguageExamScore("170");
		vo.setLanguageExamLevelCode(null);
		mapper.insertLanguageSkill(vo);
		log.info("{}", vo);
	}

	@Test
	void testUpdateLanguageSkill() {
		LanguageSkillVO vo = new LanguageSkillVO();
		vo.setLanguageSkillNo("RSM003");
		vo.setResumeNo("RSM003");
		vo.setLanguageExamCode("영어");
		vo.setLanguageCode("1");
		vo.setLanguageExamName("JLPT");
		vo.setPassDate("202512");
		vo.setLanguageExamType("R");
		vo.setLanguageExamScore("170");
		vo.setLanguageExamLevelCode(null);
		mapper.updateLanguageSkill(vo);
		log.info("{}", vo);
	}

	@Test
	void testDeleteLanguageSkill() {
		mapper.deleteLanguageSkill("LT0000005");
		assertNull(mapper.seletLanguageSkillDetail("LT0000005"));
	}

}
