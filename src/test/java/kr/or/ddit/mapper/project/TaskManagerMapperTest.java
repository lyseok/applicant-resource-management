package kr.or.ddit.mapper.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.project.TasksManagerVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class TaskManagerMapperTest {

	@Autowired
	private TaskManagerMapper mapper;

	@Test
	void testSelectTasksManagerList() {
		List<TasksManagerVO> list = mapper.selectTasksManagerList();
		list.forEach(vo -> log.info("{}", vo));
		assertNotNull(list);
	}

	@Test
	void testSelectTasksManagerByPk() {
		TasksManagerVO param = new TasksManagerVO();
		param.setTaskNo("TASK000001");
		param.setPrjNo("PRJT000001");
		param.setUserId("user01");

		TasksManagerVO vo = mapper.selectTasksManagerByPk(param);
		log.info("{}", vo);
		assertNotNull(vo);
	}

	@Test
	void testInsertTasksManager() {
		TasksManagerVO vo = new TasksManagerVO();
		vo.setTaskNo("TASK000003");
		vo.setPrjNo("PRJT000001");
		vo.setUserId("user01");

		assertEquals(1, mapper.insertTasksManager(vo));
	}

	@Test
	void testDeleteTasksManager() {
		TasksManagerVO vo = new TasksManagerVO();
		vo.setTaskNo("TASK000003");
		vo.setPrjNo("PRJT000001");
		vo.setUserId("user01");

		assertEquals(1, mapper.deleteTasksManager(vo));
		assertNull(mapper.selectTasksManagerByPk(vo));
	}
}
