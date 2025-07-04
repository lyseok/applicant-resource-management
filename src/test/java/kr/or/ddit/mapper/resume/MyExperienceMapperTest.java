package kr.or.ddit.mapper.resume;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.resume.MyExperienceVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class MyExperienceMapperTest {
	@Autowired
	MyExperienceMapper mapper;
	
	@Test
	void testselectMyExperienceList() {
		mapper.selectMyExperienceList().forEach(ex ->{
			log.info("{}", ex);
		});
	}

	@Test
	void testselectMyExperienceDetail() {
		MyExperienceVO vo = mapper.selectMyExperienceDetail("EXP001");
		log.info("{}", vo);		
	}

	@Test
	void testInsertMyExperience() {
		MyExperienceVO vo = new MyExperienceVO();
		vo.setResumeNo("RSM001");
		vo.setExpCode("9");
		vo.setExpName("교육이수내역");
		vo.setOrganizationName("에벨레레레");
		vo.setExpStartDate("2020-08-15");
		vo.setExpEndDate("2022-08-12");
		mapper.insertMyExperience(vo);
		log.info("{}", vo);
	}

	@Test
	void testUpdateMyExperience() {
		MyExperienceVO vo = new MyExperienceVO();
		vo.setMyExpCode("EXPR000004");
		vo.setResumeNo("RSM003");
		vo.setExpCode("9");
		vo.setExpName("교육이수내역");
		vo.setOrganizationName("호로로로로롤");
		vo.setExpStartDate("2020-02-15");
		vo.setExpEndDate("2022-03-12");
		mapper.updateMyExperience(vo);
		log.info("{}", vo);
	}

	@Test
	void testDeleteMyExperience() {
		mapper.deleteMyExperience("EXPR000004");
		assertNull(mapper.selectMyExperienceDetail("EXPR000004"));
		
	}

}
