package kr.or.ddit.mapper.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.project.PrjMemVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class PrjMemMapperTest {

	@Autowired
	private PrjMemMapper mapper;

	@Test
	void testSelectPrjRcrtPsncntList() {
		List<PrjMemVO> list = mapper.selectPrjRcrtPsncntList();
		list.forEach(vo -> log.info("{}", vo));
		assertNotNull(list);
	}

	@Test
	void testSelectPrjRcrtPsncntByPk() {
		PrjMemVO param = new PrjMemVO();
		param.setPrjNo("PRJT000001");
		param.setUserId("user01");

		PrjMemVO vo = mapper.selectPrjRcrtPsncntByPk(param);
		log.info("{}", vo);
		assertNotNull(vo);
	}

	@Test
	void testInsertPrjRcrtPsncnt() {
		PrjMemVO vo = new PrjMemVO();
		vo.setPrjNo("PRJT000001");
		vo.setUserId("user02");
		vo.setAuthorityCode("PL");

		assertEquals(1, mapper.insertPrjRcrtPsncnt(vo));
	}

	@Test
	void testUpdatePrjRcrtPsncnt() {
		PrjMemVO vo = new PrjMemVO();
		vo.setPrjNo("PRJT000001");
		vo.setUserId("user02");
		vo.setAuthorityCode("AA"); // 수정할 권한 코드

		assertEquals(1, mapper.updatePrjRcrtPsncnt(vo));
	}

	@Test
	void testDeletePrjRcrtPsncnt() {
		PrjMemVO vo = new PrjMemVO();
		vo.setPrjNo("PRJT000001");
		vo.setUserId("user02");

		assertEquals(1, mapper.deletePrjRcrtPsncnt(vo));
		assertNull(mapper.selectPrjRcrtPsncntByPk(vo));
	}
}
