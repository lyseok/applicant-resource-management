package kr.or.ddit.mapper.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.project.PrjBbsVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class PrjBbsMapperTest {

	@Autowired
	private PrjBbsMapper mapper;

	@Test
	void testSelectPrjBbsList() {
		List<PrjBbsVO> list = mapper.selectPrjBbsList();
		list.forEach(vo -> log.info("{}", vo));
		assertNotNull(list);
	}

	@Test
	void testSelectPrjBbsByPk() {
		PrjBbsVO vo = mapper.selectPrjBbsByPk("POST000001");
		log.info("{}", vo);
		assertNotNull(vo);
	}

	@Test
	void testInsertPrjBbs() {
		PrjBbsVO vo = new PrjBbsVO();
		vo.setPrjNo("PRJT000001");
		vo.setUserId("user01");
		vo.setTitle("테스트 제목");
		vo.setContent("테스트 내용");

		assertEquals(1, mapper.insertPrjBbs(vo));
		log.info("Inserted PRJ_POST_NO: {}", vo.getPrjPostNo());
	}

	@Test
	void testUpdatePrjBbs() {
		PrjBbsVO vo = new PrjBbsVO();
		vo.setPrjPostNo("POST000004");
		vo.setPrjNo("PRJT000001");
		vo.setUserId("user01");
		vo.setTitle("수정된 제목");
		vo.setContent("수정된 내용");
		vo.setCreateDate("2025-07-03");
		vo.setDeleteDate(null);

		assertEquals(1, mapper.updatePrjBbs(vo));
	}

	@Test
	void testDeletePrjBbs() {
		String prjPostNo = "POST000004";
		assertEquals(1, mapper.deletePrjBbs(prjPostNo));
		assertNull(mapper.selectPrjBbsByPk(prjPostNo));
	}
}