package kr.or.ddit.mapper.recruitment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.recruitment.CompanyExamVO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class CompanyExamMapperTest {

	@Autowired
	CompanyExamMapper mapper;
	
	@Test
	void testSelectCompanyExamList() {
		mapper.selectCompanyExamList().forEach(code -> {
			log.info("{}", code);		
		});
	}

	@Test
	void testSelectCompanyExam() {
		log.info("{}", mapper.selectCompanyExam("COEX000001"));
	}

	@Test
	void testInsertCompanyExam() {
		CompanyExamVO vo = new CompanyExamVO();
		vo.setUserId("corp03");
		vo.setComExamName("네오플 입사 시험");
		assertEquals(1, mapper.insertCompanyExam(vo));
	}

	@Test
	void testUpdateCompanyExam() {
		CompanyExamVO vo = new CompanyExamVO();
		vo.setComExamName("네오플 퇴사 시험");
		vo.setComExamNo("COEX000001");
		assertEquals(1, mapper.updateCompanyExam(vo));
	}

	@Test
	void testDeleteCompanyExam() {
		assertEquals(1, mapper.deleteCompanyExam("COEX000001"));
	}

}
