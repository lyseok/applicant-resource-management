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
	void testSelectAdminCommentbyPk() {
		log.info("{}", mapper.selectAdminCommentbyPk("ADCM000001"));  //댓글 하나
	}

	@Test
	void testSelectAdminCommentCommentList() {

		assertDoesNotThrow(()->mapper.searchAdminCommentCommentList("ABNO000003"));  //여러 댓글
		
		List<AdminCommentVO> list = mapper.searchAdminCommentCommentList("ABNO000003");
		list.forEach(c->log.info("{}", c));
	}
	
	@Test
	void testSelectAdminCommentList() {
		
		assertDoesNotThrow(()->mapper.searchAdminCommentList());  //댓글 전체
		
		List<AdminCommentVO> list = mapper.searchAdminCommentList();
		list.forEach(c->log.info("{}", c));
	}

	@Test
	void testInsertAdminComment() {
		AdminCommentVO comment = new AdminCommentVO();
		comment.setUserId("admin");
		comment.setBoardNo("ABNO000003");
		comment.setBoardCommentContent("새로운 답글 : 관리자가 문의에 대한 답글 남깁니다.");
		comment.setBoardCommentStatus("R");
		
		assertEquals(1, mapper.insertAdminComment(comment));
		
		log.info("{}", mapper.selectAdminCommentbyPk("ADCM000001"));
	}

	@Test
	void testUpdateAdminComment() {
		AdminCommentVO comment = new AdminCommentVO();
		comment.setBoardCommentNo("ADCM000001");
		comment.setUserId("admin");
		comment.setBoardNo("ABNO000003");
		comment.setBoardCommentContent("이건 제목 아니고 관리자의 문의 답글 내용입니다");
		comment.setBoardWriteDate("2025-07-03");  //등록 날짜보단 뒤로 나오게 제한? 아니면 관리자니까 등록일자도 변경가능?
		comment.setBoardDeleteDate(null);
		comment.setBoardCommentStatus("U");
		mapper.updateAdminComment(comment);
		
		assertEquals(1, mapper.updateAdminComment(comment));
		
		log.info("{}", mapper.selectAdminCommentbyPk("ADCM000001"));
	}

	@Test
	void testDeleteAdminComment() {
		mapper.deleteAdminComment("ADCM000001");
	}

}
