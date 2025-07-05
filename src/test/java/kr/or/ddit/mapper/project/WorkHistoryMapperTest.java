package kr.or.ddit.mapper.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.project.WorkHistoryVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class WorkHistoryMapperTest {

	@Autowired
	private WorkHistoryMapper mapper;

	@Test
	void testSelectWorkHistoryList() {
		List<WorkHistoryVO> list = mapper.selectWorkHistoryList();
		list.forEach(vo -> log.info("{}", vo));
		assertNotNull(list);
	}
	@Test
	void testSearchWorkHistoryList() {
		List<WorkHistoryVO> list = mapper.searchWorkHistoryList("PRJT000001");
		list.forEach(vo -> log.info("{}", vo));
		assertNotNull(list);
	}

	@Test
	void testSelectWorkHistoryByPk() {
		WorkHistoryVO vo = mapper.selectWorkHistoryByPk("WKHR000001");
		log.info("{}", vo);
		assertNotNull(vo);
	}

	@Test
	void testInsertWorkHistory() {
		WorkHistoryVO vo = new WorkHistoryVO();
		vo.setPrjNo("PRJT000001");
		vo.setUserId("user01");
		vo.setWorkTable("PRJ_MEM"); // 작업 테이블
		vo.setWorkType("C"); // 작업 종류

		assertEquals(1, mapper.insertWorkHistory(vo));
	}
}
