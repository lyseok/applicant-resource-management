package kr.or.ddit.mapper.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.project.PrjBbsCommentVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class PrjBbsCommentMapperTest {

	@Autowired
	private PrjBbsCommentMapper mapper;

	@Test
	void testSelectList() {
		List<PrjBbsCommentVO> list = mapper.selectPrjRcrtPsncntList();
		list.forEach(vo -> log.info("{}", vo));
		assertNotNull(list);
	}

	@Test
	void testSelectByPk() {
		PrjBbsCommentVO vo = mapper.selectPrjRcrtPsncntByPk("PCOM000001");
		log.info("{}", vo);
		assertNotNull(vo);
	}

	@Test
	void testInsert() {
		PrjBbsCommentVO vo = new PrjBbsCommentVO();
		vo.setPrjPostNo("POST000001");
		vo.setPrjNo("PRJT000001");
		vo.setUserId("user01");
		vo.setCommentContent("댓글 테스트");

		int res = mapper.insertPrjRcrtPsncnt(vo);
		assertEquals(1, res);
		log.info("Inserted COMMENT_NO: {}", vo.getCommentNo());
	}

	@Test
	void testUpdate() {
		PrjBbsCommentVO vo = new PrjBbsCommentVO();
		vo.setCommentNo("PCOM000001");
		vo.setPrjPostNo("POST000001");
		vo.setPrjNo("PRJT000001");
		vo.setUserId("user01");
		vo.setCommentContent("수정된 댓글 내용");
		vo.setCreateDate("2025-07-03");
		vo.setDeleteDate(null);

		int res = mapper.updatePrjRcrtPsncnt(vo);
		assertEquals(1, res);
	}

	@Test
	void testDelete() {
		String commentNo = "PCOM000004";
		int res = mapper.deletePrjRcrtPsncnt(commentNo);
		assertEquals(1, res);
		assertNull(mapper.selectPrjRcrtPsncntByPk(commentNo));
	}
}