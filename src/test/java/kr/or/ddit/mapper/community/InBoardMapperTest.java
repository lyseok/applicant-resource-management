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
	void testSelectInBoard() {
		log.info("{}", mapper.selectInBoard("CMBD000005", "AVT001"));
	}

	@Test
	void testSelectInBoardPostList() {
		assertDoesNotThrow(()->mapper.selectInBoardPostList("AVT001"));
		
		List<InBoardVO> list = mapper.selectInBoardPostList("AVT001");
		list.forEach(p->{
			log.info("{}", p);
		});
	}

	@Test
	void testSelectInBoardAvatarList() {
		assertDoesNotThrow(()->mapper.selectInBoardAvatarList("CMBD000005"));
		
		List<InBoardVO> list = mapper.selectInBoardAvatarList("CMBD000005");
		list.forEach(a->{
			log.info("{}", a);
		});
	}

	@Test
	void testInsertInBoard() {
		InBoardVO board = new InBoardVO();
		
		board.setCommuPostNo("CMBD000003");
		board.setAvatarId("AVT002");
		

		assertEquals(1, mapper.insertInBoard(board));
		
		log.info("{}", mapper.selectInBoard("CMBD000003", "AVT002"));
	}

	@Test
	void testDeleteInBoard() {
		mapper.deleteInBoard("CMBD000003", "AVT002");
		assertNull(mapper.selectInBoard("CMBD000003", "AVT002"));
	}

}
