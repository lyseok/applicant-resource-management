package kr.or.ddit.mapper.recruitment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.recruitment.RecruitmentExamVO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class RecruitmentExamMapperTest {

	@Autowired
	RecruitmentExamMapper mapper;
	
	@Test
	void testSelectRecruitExamList() {
		mapper.selectRecruitExamList().forEach(code -> {
			log.info("{}", code);		
		});
	}

	@Test
	void testSelectRecruitExam() {
		log.info("{}", mapper.selectRecruitExam("REEX000001"));
	}

	@Test
	void testInsertRecruitExam() {
		RecruitmentExamVO vo = new RecruitmentExamVO();
		vo.setProcessNo("PROC000001");
		vo.setRecruitExamName("네오플 시험");
		vo.setRecruitExamCutline(70);
		vo.setRecruitExamStartDate("2025-07-13 11:00");
		vo.setRecruitExamTime(60);
		assertEquals(1, mapper.insertRecruitExam(vo));
	}

	@Test
	void testUpdateRecruitExam() {
		RecruitmentExamVO vo = new RecruitmentExamVO();
		vo.setRecruitExamName("네오플 시험");
		vo.setRecruitExamCutline(80);
		vo.setRecruitExamStartDate("2025-07-13 12:00");
		vo.setRecruitExamTime(60);
		vo.setRecruitExamNo("REEX000001");
		assertEquals(1, mapper.updateRecruitExam(vo));
	}

	@Test
	void testDeleteRecruitExam() {
		assertEquals(1, mapper.deleteRecruitExam("REEX000001"));
	}

}
