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
	void testSelectInComment() {
		log.info("{}", mapper.selectInComment("CMCM000001", "AVT001"));
	}

	@Test
	void testSelectInCommentList() {
		assertDoesNotThrow(()->mapper.selectInCommentList("AVT001"));
		
		List<InCommentVO> list = mapper.selectInCommentList("AVT001");
		list.forEach(p->{
			log.info("{}", p);
		});
	}

	@Test
	void testSelectInCommentAvatarList() {
		assertDoesNotThrow(()->mapper.selectInCommentAvatarList("CMCM000001"));
		
		List<InCommentVO> list = mapper.selectInCommentAvatarList("CMCM000001");
		list.forEach(a->{
			log.info("{}", a);
		});
	}

	@Test
	void testInsertInComment() {
		InCommentVO Comment = new InCommentVO();
		
		Comment.setCommentNo("CMCM000001");
		Comment.setAvatarId("AVT002");

		assertEquals(1, mapper.insertInComment(Comment));
		
		log.info("{}", mapper.selectInComment("CMCM000001", "AVT002"));
	}

	@Test
	void testDeleteInComment() {
		log.info("{}", mapper.deleteInComment("CMCM000001", "AVT002"));  //1이면 삭제
		assertNull(mapper.selectInComment("CMCM000001", "AVT002"));
	}

}
