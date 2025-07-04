package kr.or.ddit.mapper.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.project.PrjAnncBoardTagVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class PrjAnncBoardTagMapperTest {

	@Autowired
	private PrjAnncBoardTagMapper mapper;

	@Test
	void testSelectPrjAnncBoardTagList() {
		List<PrjAnncBoardTagVO> list = mapper.selectPrjAnncBoardTagList();
		list.forEach(vo -> log.info("{}", vo));
		assertNotNull(list);
	}
	
	@Test
	void testSearchPrjAnncBoardTagList() {
		List<PrjAnncBoardTagVO> list = mapper.searchPrjAnncBoardTagList("PJAB000001");
		list.forEach(vo -> log.info("{}", vo));
		assertNotNull(list);
	}

	@Test
	void testSelectPrjAnncBoardTagByPk() {
		PrjAnncBoardTagVO param = new PrjAnncBoardTagVO();
		param.setTagNo("TAG001");
		param.setPrjAnncNo("TEST001");

		PrjAnncBoardTagVO vo = mapper.selectprjAnncBoardTagByPk(param);
		log.info("{}", vo);
		assertNotNull(vo);
	}

	@Test
	void testInsertPrjAnncBoardTag() {
		PrjAnncBoardTagVO vo = new PrjAnncBoardTagVO();
		vo.setTagNo("TAG001");
		vo.setPrjAnncNo("TEST001");
		vo.setTagDate("2025-07-03"); // 형식에 맞게 날짜 지정

		assertEquals(1, mapper.insertPrjAnncBoardTag(vo));
	}

	@Test
	void testDeletePrjAnncBoardTag() {
		PrjAnncBoardTagVO vo = new PrjAnncBoardTagVO();
		vo.setTagNo("TAG001");
		vo.setPrjAnncNo("TEST001");

		assertEquals(1, mapper.deletePrjAnncBoardTag(vo));
		assertNull(mapper.selectprjAnncBoardTagByPk(vo));
	}
}
