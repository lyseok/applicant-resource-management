package kr.or.ddit.mapper.community;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.community.InCommentVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class InCommentMapperTest {

	@Autowired
	InCommentMapper mapper;
	
	@Test
	void testSelectInCommentbyPk() {
		log.info("{}", mapper.selectInCommentbyPk("CMCM000001", "AVT001"));
	}

	@Test
	void testSearchInCommentCommentList() {
		assertDoesNotThrow(()->mapper.searchInCommentCommentList("AVT001"));
		
		List<InCommentVO> list = mapper.searchInCommentCommentList("AVT001");
		list.forEach(c->{
			log.info("{}", c);
		});
	}

	@Test
	void testSearchInCommentAvatarList() {
		assertDoesNotThrow(()->mapper.searchInCommentAvatarList("CMCM000001"));
		
		List<InCommentVO> list = mapper.searchInCommentAvatarList("CMCM000001");
		list.forEach(a->{
			log.info("{}", a);
		});
	}
	
	@Test
	void testSelectInCommentList() {
		assertDoesNotThrow(()->mapper.selectInCommentList());
		
		List<InCommentVO> list = mapper.selectInCommentList();
		list.forEach(c->{
			log.info("{}", c);
		});
	}

	@Test
	void testInsertInComment() {
		InCommentVO Comment = new InCommentVO();
		
		Comment.setCommentNo("CMCM000001");
		Comment.setAvatarId("AVT002");

		assertEquals(1, mapper.insertInComment(Comment));
		
		log.info("{}", mapper.selectInCommentbyPk("CMCM000001", "AVT002"));
	}

	@Test
	void testDeleteInComment() {
		log.info("{}", mapper.deleteInComment("CMCM000001", "AVT002"));  //1이면 삭제 성공
		assertNull(mapper.selectInCommentbyPk("CMCM000001", "AVT002"));
	}

}
