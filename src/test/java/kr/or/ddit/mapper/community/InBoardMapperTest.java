package kr.or.ddit.mapper.community;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.community.InBoardVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class InBoardMapperTest {

	@Autowired
	InBoardMapper mapper;
	
	@Test
	void testselectInBoardByCommuPostNoAvatarId() {
		log.info("{}", mapper.selectInBoardByCommuPostNoAvatarId("CMBD000005", "AVT001"));
	}

	@Test
	void testSearchInBoardCommuPostList() {
		assertDoesNotThrow(()->mapper.searchInBoardCommuPostList("AVT001"));
		
		List<InBoardVO> list = mapper.searchInBoardCommuPostList("AVT001");
		list.forEach(p->{
			log.info("{}", p);
		});
	}

	@Test
	void testSearchInBoardAvatarList() {
		assertDoesNotThrow(()->mapper.searchInBoardAvatarList("CMBD000005"));
		
		List<InBoardVO> list = mapper.searchInBoardAvatarList("CMBD000005");
		list.forEach(a->{
			log.info("{}", a);
		});
	}

	@Test
	void testSelectInBoardList() {
		assertDoesNotThrow(()->mapper.selectInBoardList());
		
		List<InBoardVO> list = mapper.selectInBoardList();
		list.forEach(b->{
			log.info("{}", b);
		});
	}
	
	@Test
	void testInsertInBoard() {
		InBoardVO board = new InBoardVO();
		
		board.setCommuPostNo("CMBD000003");
		board.setAvatarId("AVT002");
		

		assertEquals(1, mapper.insertInBoard(board));
		
		log.info("{}", mapper.selectInBoardByCommuPostNoAvatarId("CMBD000003", "AVT002"));
	}

	@Test
	void testDeleteInBoard() {
		mapper.deleteInBoard("CMBD000003", "AVT002");
		assertNull(mapper.selectInBoardByCommuPostNoAvatarId("CMBD000003", "AVT002"));
	}

}
