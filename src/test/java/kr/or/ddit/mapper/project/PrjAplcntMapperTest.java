package kr.or.ddit.mapper.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.project.PrjAplcntVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class PrjAplcntMapperTest {

	@Autowired
	private PrjAplcntMapper mapper;

	@Test
	void testSelectPrjRcrtPsncntList() {
		List<PrjAplcntVO> list = mapper.selectPrjRcrtPsncntList();
		list.forEach(vo -> log.info("{}", vo));
		assertNotNull(list);
	}

	@Test
	void testSelectPrjRcrtPsncntByPk() {
		PrjAplcntVO vo = mapper.selectPrjRcrtPsncntByPk("PJAC000001");
		log.info("{}", vo);
		assertNotNull(vo);
	}

	@Test
	void testInsertPrjRcrtPsncnt() {
		PrjAplcntVO vo = new PrjAplcntVO();
		vo.setResumeNo("RESM000001"); // 이력서 번호
		vo.setUserId("user01"); // 사용자 ID
		vo.setAplcntStatusCode("PAPP003"); // 지원 상태 코드
		vo.setRcrtPsncntNo("PCNT000002"); // 모집 인원 번호
		vo.setPrjAnncNo("PJAB000002"); // 공고 번호

		assertEquals(1, mapper.insertPrjRcrtPsncnt(vo));
		log.info("Inserted PK: {}", vo.getPrjAplcntNo());
	}

}