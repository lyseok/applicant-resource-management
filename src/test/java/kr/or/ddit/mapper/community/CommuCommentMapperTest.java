package kr.or.ddit.mapper.community;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.community.CommuCommentVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class CommuCommentMapperTest {
	
	@Autowired
	CommuCommentMapper mapper;

	@Test
	void testSelectCommuComment() {
		log.info("{}", mapper.selectCommuComment("CMCM000001"));
	}

	@Test
	void testSelectCommuCommentList() {
		assertDoesNotThrow(()->mapper.selectCommuCommentList("CMBD000004"));
		
		List<CommuCommentVO> list = mapper.selectCommuCommentList("CMBD000004");
		list.forEach(c->log.info("{}", c));
	}

	@Test
	void testInsertCommuComment() {
		CommuCommentVO comment = new CommuCommentVO();
		comment.setCommuPostNo("CMBD000002");
		comment.setAvatarId("AVT002");
		comment.setCommuCommentContent("힘내세요 ㅠㅠ");
		comment.setCommuCommentStatus("R");
		
		assertEquals(1, mapper.insertCommuComment(comment));
		
		log.info("{}", mapper.selectCommuComment("CMCM000003"));
	}

	@Test
	void testUpdateCommuComment() {
		CommuCommentVO comment = new CommuCommentVO();
		comment.setCommuCommentNo("CMCM000003");
		comment.setCommuPostNo("CMBD000002");
		comment.setAvatarId("AVT002");
		comment.setCommuCommentContent("아직은 이르다 생각합니다.");
		comment.setCommuCommentWriteDate("2025-07-03");
		comment.setCommuCommentStatus("U");
		comment.setCommuCommentDeleteDate(null);	
		
		mapper.updateCommuComment(comment);
		
		assertEquals(1, mapper.updateCommuComment(comment));
		
		log.info("{}", mapper.selectCommuComment("CMCM000003"));
	}

	@Test
	void testDeleteCommuComment() {
		mapper.deleteCommuComment("CMCM000001");
	}

}
