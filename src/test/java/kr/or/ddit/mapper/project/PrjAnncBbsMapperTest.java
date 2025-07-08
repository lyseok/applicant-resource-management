package kr.or.ddit.mapper.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.project.PrjAnncBbsVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class PrjAnncBbsMapperTest {
	@Autowired
	PrjAnncBbsMapper mapper;
	
	@Test
	void testSelectPrjAnncBbsList() {
		List<PrjAnncBbsVO> list = mapper.selectPrjAnncBbsList();
		list.forEach(vo -> log.info("{}", vo));
		
		assertNotNull(list);
	}
	
	@Test
	void testSelectPrjAnncBbsByPk() {
		PrjAnncBbsVO vo = mapper.selectPrjAnncBbsByPk("PANNC0002");
		log.info("{}", vo);
		
		assertNotNull(vo);
	}
	
	@Test
	void testInsertPrjAnncBbs() {
		PrjAnncBbsVO vo = new PrjAnncBbsVO();
		vo.setUserId("user01"); // 사용자 ID 입력
		vo.setPrjEmpTitle("테스트 게시글3"); // 프로젝트 담당 제목
		vo.setPrjTopic("테스트입니다"); // 프로젝트 주제
		vo.setPrjStartPlanDate("20250704"); // 시작 예정일
		vo.setPrjEndPlanDate("20250804"); // 종료 예정일
		vo.setPrjAnncContent("테스트 공고 내용"); // 공고 내용
		vo.setAnncEndPlanDate("20250704"); // 공고 마감일

		assertEquals(1, mapper.insertPrjAnncBbs(vo));
	}

	@Test
	void testUpdatePrjAnncBbs() {
		PrjAnncBbsVO vo = new PrjAnncBbsVO();
		vo.setPrjAnncNo("PJAB000004");
		vo.setUserId("user01"); // 사용자 ID 입력
		vo.setPrjEmpTitle("테스트 수정게시글"); // 프로젝트 담당 제목
		vo.setPrjTopic("수정테스트입니다"); // 프로젝트 주제
		vo.setPrjTopic("테스트입니다"); // 프로젝트 주제
		vo.setPrjStartPlanDate("20250704"); // 시작 예정일
		vo.setPrjEndPlanDate("20250804"); // 종료 예정일
		vo.setPrjAnncContent("테스트 공고 내용"); // 공고 내용
		vo.setAnncEndPlanDate("20250704"); // 공고 마감일
		vo.setAnncEndYn("Y"); // 수정할 마감 여부

		assertEquals(1, mapper.updatePrjAnncBbs(vo));
	}

	@Test
	void testDeletePrjAnncBbs() {
		assertEquals(1, mapper.deletePrjAnncBbs("PJAB000004"));
		assertNull(mapper.selectPrjAnncBbsByPk("PJAB000004"));
	}

}
