package kr.or.ddit.mapper.recruitment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.recruitment.RecruitmentSkillVO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class RecruitmentSkillmapperTest {

	@Autowired
	RecruitmentSkillmapper mapper;
	
	@Test
	void testSelectRecruitmentSkillList() {
		mapper.selectRecruitmentSkillList().forEach(code -> {
			log.info("{}", code);		
		});

	}

	@Test
	void testSelectRecruitmentSkill() {
		log.info("{}", mapper.selectRecruitmentSkill("RESK000001"));
	}

	@Test
	void testInsertRecruitmetnSkill() {
		RecruitmentSkillVO vo = new RecruitmentSkillVO();
		vo.setRecruitmentNo("RECR000001");
		vo.setRecruitSkillName("자바");
		assertEquals(1, mapper.insertRecruitmetnSkill(vo));
	}

	@Test
	void testUpdateRecruitmetnSkill() {
		RecruitmentSkillVO vo = new RecruitmentSkillVO();
		vo.setRecruitSkillName("파이썬");
		vo.setRecruitSkillCode("RESK000001");
		assertEquals(1, mapper.updateRecruitmetnSkill(vo));
	}

	@Test
	void testDeleteRecruitmetnSkill() {
		assertEquals(1, mapper.deleteRecruitmetnSkill("RESK000001"));
	}

}
