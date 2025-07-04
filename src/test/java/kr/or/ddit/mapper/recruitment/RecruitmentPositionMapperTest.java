package kr.or.ddit.mapper.recruitment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.recruitment.RecruitmentPositionVO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class RecruitmentPositionMapperTest {
	
	@Autowired
	RecruitmentPositionMapper mapper;

	@Test
	void testSelectRecruitmentPositonList() {
		mapper.selectRecruitmentPositonList().forEach(code -> {
			log.info("{}", code);		
		});
	}

	@Test
	void testSelectRecruitmentPosition() {
		log.info("{}", mapper.selectRecruitmentPosition(null));
	}

	@Test
	void testInsertRecruitmentPositon() {
		RecruitmentPositionVO vo = new RecruitmentPositionVO();
		vo.setRecruitmentNo("RECR000001");
		vo.setCodeDetailNo("RANK-001");
		assertEquals(1, mapper.insertRecruitmentPositon(vo));
	}

	@Test
	void testUpdateRecruitmentPositon() {
		RecruitmentPositionVO vo = new RecruitmentPositionVO();
		vo.setCodeDetailNo("RANK-000");
		vo.setRecruitmentPositionCode("REPO000001");
		assertEquals(1, mapper.updateRecruitmentPositon(vo));
	}

	@Test
	void testDeleteRecruitmentPositon() {
		assertEquals(1, mapper.deleteRecruitmentPositon("REPO000001"));
	}

}
