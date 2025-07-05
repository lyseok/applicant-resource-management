package kr.or.ddit.mapper.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.project.PrjTaskVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class PrjTaskMapperTest {

	@Autowired
	private PrjTaskMapper mapper;

	@Test
	void testSelectPrjTaskList() {
		List<PrjTaskVO> list = mapper.selectPrjTaskList();
		list.forEach(vo -> log.info("{}", vo));
		assertNotNull(list);
	}

	@Test
	void testSelectPrjTaskByPk() {
		PrjTaskVO vo = mapper.selectPrjTaskByPk("TASK000001");
		log.info("{}", vo);
		assertNotNull(vo);
	}

	@Test
	void testInsertPrjTask() {
		PrjTaskVO vo = new PrjTaskVO();
		vo.setPrjNo("PRJT000001");
		vo.setUserId("user01");
		vo.setSectNo("SECT000001");
		vo.setCreatorId("user01");
		vo.setTaskName("기능 설계");
		vo.setTaskStatus("진행중");
		vo.setDetailContent("상세 내용 작성");
		vo.setStartDate("20250703");
		vo.setDueDate("20250710");
		vo.setPriorityCode("PCOD001");
		vo.setUpperTaskNo(null); // 상위 작업 없음
		vo.setProgressRate("0");

		assertEquals(1, mapper.insertPrjTask(vo));
		log.info("Inserted TASK_NO: {}", vo.getTaskNo());
	}

	@Test
	void testUpdatePrjTask() {
		PrjTaskVO vo = new PrjTaskVO();
		vo.setTaskNo("TASK000004");
		vo.setUserId("user01");
		vo.setSectNo("SECT000001");
		vo.setTaskName("기능 1설계 수정");
		vo.setTaskStatus("완료");
		vo.setDetailContent("작업 완료됨");
		vo.setStartDate("20250703");
		vo.setDueDate("20250712");
		vo.setPriorityCode("MEDIUM");
		vo.setUpperTaskNo(null);
		vo.setProgressRate("100");
		vo.setDeleteDate("20250801");
		vo.setDeleteUserId("admin");

		assertEquals(1, mapper.updatePrjTask(vo));
	}

	@Test
	void testDeletePrjTask() {
		assertEquals(1, mapper.deletePrjTask("TASK000004"));
		assertNull(mapper.selectPrjTaskByPk("TASK000004"));
	}
}