package kr.or.ddit.mapper.community;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
		log.info("{}", mapper.selectCommuCommentbyPk("CMCM000001"));
	}

	@Test
	void testSearchCommuCommentPostList() {
		assertDoesNotThrow(()->mapper.searchCommuCommentPostList("CMBD000001"));
		
		List<CommuCommentVO> list = mapper.searchCommuCommentPostList("CMBD000001");
		list.forEach(c->log.info("{}", c));
		
		assertEquals(1, list.size());
	}
	
	@Test
	void testSearchCommuCommentList() {
		assertDoesNotThrow(()->mapper.searchCommuCommentList());
		
		List<CommuCommentVO> list = mapper.searchCommuCommentList();
		list.forEach(c->log.info("{}", c));
		
		assertEquals(1, list.size());
	}

	@Test
	void testInsertCommuComment() {
		CommuCommentVO comment = new CommuCommentVO();
		comment.setCommuPostNo("CMBD000001");
		comment.setAvatarId("AVTR000001");
		comment.setCommuCommentContent("힘내세요 ㅠㅠ");
		comment.setCommuCommentStatus("R");
		
		assertEquals(1, mapper.insertCommuComment(comment));
		
		log.info("{}", mapper.selectCommuCommentbyPk("CMCM000001"));
	}

	@Test
	void testUpdateCommuComment() {
		CommuCommentVO comment = new CommuCommentVO();
		comment.setCommuCommentNo("CMCM000001");
		comment.setCommuPostNo("CMBD000001");
		comment.setAvatarId("AVTR000001");
		comment.setCommuCommentContent("아직은 이르다 생각합니다.");
		comment.setCommuCommentWriteDate("20250703");
		comment.setCommuCommentStatus("U");
		comment.setCommuCommentDeleteDate(null);	
		
		mapper.updateCommuComment(comment);
		
		assertEquals(1, mapper.updateCommuComment(comment));
		
		log.info("{}", mapper.selectCommuCommentbyPk("CMCM000001"));
	}

	@Test
	void testDeleteCommuComment() {
		log.info("{}", mapper.deleteCommuComment("CMCM000001"));
		assertNull(mapper.selectCommuCommentbyPk("CMCM000001"));
	}

}
