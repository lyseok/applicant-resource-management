package kr.or.ddit.mapper.recruitment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.recruitment.RecruitmentEducationVO;
import kr.or.ddit.vo.recruitment.RecruitmentPositionVO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class RecruitmentEducationMapperTest {
	
	@Autowired
	RecruitmentEducationMapper mapper;

	@Test
	void testSelectRecruitmentEduList() {
		fail("Not yet implemented");
	}

	@Test
	void testSelectRecruitmentEdu() {
		fail("Not yet implemented");
	}

	@Test
	void testInsertRecruitmentEducation() {
		RecruitmentEducationVO vo = new RecruitmentEducationVO();
		vo.setRecruitmentNo("RECR000001");
		vo.setCodeDetailNo("EDUC-004");
		assertEquals(1, mapper.insertRecruitmentEducation(vo));
	}

	@Test
	void testUpdateRecruitmentEducation() {
		RecruitmentEducationVO vo = new RecruitmentEducationVO();
		vo.setCodeDetailNo("EDUC-002");
		vo.setRecruitmentEducationCode("REED000001");
		assertEquals(1, mapper.updateRecruitmentEducation(vo));
	}

	@Test
	void testDeleteRecruitmentEducation() {
		assertEquals(1, mapper.deleteRecruitmentEducation("REED000001"));
	}

}
