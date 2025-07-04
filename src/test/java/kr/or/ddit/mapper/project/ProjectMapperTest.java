package kr.or.ddit.mapper.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.project.ProjectVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class ProjectMapperTest {

	@Autowired
	private ProjectMapper mapper;

	@Test
	void testSelectPrjRcrtPsncntList() {
		List<ProjectVO> list = mapper.selectPrjRcrtPsncntList();
		list.forEach(vo -> log.info("{}", vo));
		assertNotNull(list);
	}

	@Test
	void testSelectPrjRcrtPsncntByPk() {
		ProjectVO vo = mapper.selectPrjRcrtPsncntByPk("PRJT000001");
		log.info("{}", vo);
		assertNotNull(vo);
	}

	@Test
	void testInsertPrjRcrtPsncnt() {
		ProjectVO vo = new ProjectVO();
		vo.setUserId("user01"); // 사용자 ID
		vo.setProjectBoardNo("PJAB000001"); // 게시판 번호
		vo.setProjectName("TEST"); // 프로젝트명
		vo.setProjectContents("TEST PROJECT"); // 프로젝트 내용
		vo.setProjectStatus("PROG001"); // 상태

		assertEquals(1, mapper.insertPrjRcrtPsncnt(vo));
	}

	@Test
	void testUpdatePrjRcrtPsncnt() {
		ProjectVO vo = new ProjectVO();
		vo.setPrjNo("PRJT000004"); // 수정 대상
		vo.setProjectName("수정된 프로젝트");
		vo.setProjectContents("내용 수정됨");
		vo.setProjectStatus("PROG002");

		assertEquals(1, mapper.updatePrjRcrtPsncnt(vo));
	}

	@Test
	void testDeletePrjRcrtPsncnt() {
		assertEquals(1, mapper.deletePrjRcrtPsncnt("PRJT000004"));
		assertNull(mapper.selectPrjRcrtPsncntByPk("PRJT000004"));
	}
}