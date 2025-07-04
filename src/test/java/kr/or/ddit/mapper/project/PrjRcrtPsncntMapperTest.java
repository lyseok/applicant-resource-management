package kr.or.ddit.mapper.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.project.PrjRcrtPsncntVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class PrjRcrtPsncntMapperTest {

	@Autowired
	private PrjRcrtPsncntMapper mapper;

	@Test
	void testSelectPrjRcrtPsncntList() {
		List<PrjRcrtPsncntVO> list = mapper.selectPrjRcrtPsncntList();
		list.forEach(vo -> log.info("{}", vo));
		assertNotNull(list);
	}

	@Test
	void testSelectPrjRcrtPsncntByPk() {
		PrjRcrtPsncntVO se = new PrjRcrtPsncntVO();
		se.setPrjAnncNo("PJAB000001");
		se.setRcrtPsncntNo("PCNT000001");
		
		PrjRcrtPsncntVO vo = mapper.selectPrjRcrtPsncntByPk(se);
		log.info("{}", vo);
		assertNotNull(vo);
	}

	@Test
	void testInsertPrjRcrtPsncnt() {
		PrjRcrtPsncntVO vo = new PrjRcrtPsncntVO();
		vo.setPrjAnncNo("PJAB000001"); // 관련 공고번호
		vo.setJobCode("TEST"); // 직무 코드
		vo.setRcrtPsncnt(3); // 모집 인원

		assertEquals(1, mapper.insertPrjRcrtPsncnt(vo));
	}

	@Test
	void testUpdatePrjRcrtPsncnt() {
		PrjRcrtPsncntVO vo = new PrjRcrtPsncntVO();
		vo.setRcrtPsncntNo("PCNT000003"); // 기존 키
		vo.setPrjAnncNo("PJAB000001"); // 기존 키
		vo.setJobCode("UPDT"); // 수정할 직무 코드
		vo.setRcrtPsncnt(5); // 수정할 인원

		assertEquals(1, mapper.updatePrjRcrtPsncnt(vo));
	}

	@Test
	void testDeletePrjRcrtPsncnt() {
		// 복합키 기준 삭제
		PrjRcrtPsncntVO se = new PrjRcrtPsncntVO();
		se.setPrjAnncNo("PJAB000001");
		se.setRcrtPsncntNo("PCNT000003");
		
		assertEquals(1, mapper.deletePrjRcrtPsncnt(se));
	}

}
