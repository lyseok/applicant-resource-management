package kr.or.ddit.mapper.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.project.PrjSectionVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class PrjSectionMapperTest {

	@Autowired
	private PrjSectionMapper mapper;

	@Test
	void testSelectPrjSectionList() {
		List<PrjSectionVO> list = mapper.selectPrjSectionList();
		list.forEach(vo -> log.info("{}", vo));
		assertNotNull(list);
	}

	@Test
	void testSelectPrjSectionByPk() {
		PrjSectionVO vo = mapper.selectPrjSectionByPk("SECT000001");
		log.info("{}", vo);
		assertNotNull(vo);
	}

	@Test
	void testInsertPrjSection() {
		PrjSectionVO vo = new PrjSectionVO();
		vo.setPrjNo("PRJT000001");
		vo.setUserId("user01");
		vo.setSectName("분류 섹션");
		vo.setSectOrder(1);

		assertEquals(1, mapper.insertPrjSection(vo));
		log.info("Inserted SECT_NO: {}", vo.getSectNo());
	}

	@Test
	void testUpdatePrjSection() {
		PrjSectionVO vo = new PrjSectionVO();
		vo.setSectNo("SECT000004");
		vo.setPrjNo("PRJT000001");
		vo.setUserId("user01");
		vo.setSectName("섹션 수정");
		vo.setSectOrder(2);
		vo.setDeleteDate("2025-08-01");
		vo.setDelUserId("admin");

		assertEquals(1, mapper.updatePrjSection(vo));
	}

	@Test
	void testDeletePrjSection() {
		assertEquals(1, mapper.deletePrjSection("SECT000004"));
		assertNull(mapper.selectPrjSectionByPk("SECT000004"));
	}
}
