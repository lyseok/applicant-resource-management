package kr.or.ddit.mapper.recruitment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.recruitment.RecruitProcessVO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class RecruitProcessMapperTest {
	
	@Autowired
	RecruitProcessMapper mapper;
	
	@Test
	void testSelectRecruitprocessList() {
		mapper.selectRecruitprocessList().forEach(code -> {
			log.info("{}", code);		
		});

	}

	@Test
	void testSelectRecruitprocess() {
		assertNotNull(mapper.selectRecruitprocess("PROC999999"));
	}

	@Test
	void testInsertRecruitProcess() {
		RecruitProcessVO vo = new RecruitProcessVO();
		vo.setRecruitmentNo("RECR000001");
		vo.setCompanyName("corp01");
		vo.setRecruitProcessStep("1");
		vo.setRecruitProcessFinal("3");
		vo.setRecruitProcessType("시험");
		assertEquals(1, mapper.insertRecruitProcess(vo));
	}

	@Test
	void testUpdateRecruitProcess() {
		RecruitProcessVO vo = new RecruitProcessVO();
		vo.setRecruitProcessNo("PROC000001");
		vo.setRecruitmentNo("RECR000001");
		vo.setCompanyName("corp03");
		vo.setRecruitProcessStep("1");
		vo.setRecruitProcessFinal("3");
		vo.setRecruitProcessType("면접");
		assertEquals(1, mapper.updateRecruitProcess(vo));
	}

}
