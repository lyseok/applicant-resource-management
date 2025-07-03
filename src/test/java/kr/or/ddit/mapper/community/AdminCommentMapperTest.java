package kr.or.ddit.mapper.community;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.community.AdminCommentVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class AdminCommentMapperTest {

	@Autowired
	AdminCommentMapper mapper;
	
	@Test
	void testSelectAdminComment() {
		log.info("{}", mapper.selectAdminComment("CMT0000001"));  //댓글 하나
	}

	@Test
	void testSelectAdminCommentList() {

		assertDoesNotThrow(()->mapper.selectAdminCommentList("BRD0000001"));  //여러 댓글
		
		List<AdminCommentVO> list = mapper.selectAdminCommentList("BRD0000001");
		list.forEach(c->log.info("{}", c));
	}

	@Test
	void testInsertAdminComment() {
		AdminCommentVO comment = new AdminCommentVO();
		comment.setUserId("admin");
		comment.setBoardNo("BRD0000001");
		comment.setBoardCommentContent("새로운 답글 : 관리자가 문의에 대한 답글 남깁니다.");
		comment.setBoardCommentStatus("R");
		
		assertEquals(1, mapper.insertAdminComment(comment));
		
		log.info("{}", mapper.selectAdminComment("CMT0000001"));
	}

	@Test
	void testUpdateAdminComment() {
		AdminCommentVO comment = new AdminCommentVO();
		comment.setBoardCommentNo("CMT0000001");
		comment.setUserId("admin");
		comment.setBoardNo("BRD0000001");
		comment.setBoardCommentContent("이건 제목 아니고 관리자의 문의 답글 내용입니다");
		comment.setBoardWriteDate("2025-07-03");  //등록 날짜보단 뒤로 나오게 제한? 아니면 관리자니까 타임슬립도 가능?
		comment.setBoardDeleteDate(null);
		comment.setBoardCommentStatus("U");
		mapper.updateAdminComment(comment);
		
		assertEquals(1, mapper.updateAdminComment(comment));
		
		log.info("{}", mapper.selectAdminComment("CMT0000001"));
	}

	@Test
	void testDeleteAdminComment() {
		mapper.deleteAdminComment("CMT0000001");
	}

}
