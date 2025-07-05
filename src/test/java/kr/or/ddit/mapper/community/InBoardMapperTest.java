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
	void testselectInBoardByPk() {
		assertDoesNotThrow(()->mapper.selectInBoardByPk("CMBD000001", "AVTR000001"));
		
		log.info("{}", mapper.selectInBoardByPk("CMBD000001", "AVTR000001"));
	}

	@Test
	void testSearchInBoardCommuPostList() {
		assertDoesNotThrow(()->mapper.searchInBoardCommuPostList("AVTR000001"));
		
		List<InBoardVO> list = mapper.searchInBoardCommuPostList("AVTR000001");
		list.forEach(p->{
			log.info("{}", p);
		});

		assertEquals(1, list.size());
	}

	@Test
	void testSearchInBoardAvatarList() {
		assertDoesNotThrow(()->mapper.searchInBoardAvatarList("CMBD000001"));
		
		List<InBoardVO> list = mapper.searchInBoardAvatarList("CMBD000001");
		list.forEach(a->{
			log.info("{}", a);
		});

		assertEquals(1, list.size());
	}

	@Test
	void testSelectInBoardList() {
		assertDoesNotThrow(()->mapper.selectInBoardList());
		
		List<InBoardVO> list = mapper.selectInBoardList();
		list.forEach(b->{
			log.info("{}", b);
		});

		assertEquals(1, list.size());
	}
	
	@Test
	void testInsertInBoard() {
		InBoardVO board = new InBoardVO();
		
		board.setCommuPostNo("CMBD000001");
		board.setAvatarId("AVTR000001");
		

		assertEquals(1, mapper.insertInBoard(board));
		
		log.info("{}", mapper.selectInBoardByPk("CMBD000001", "AVTR000001"));
	}

	@Test
	void testDeleteInBoard() {
		log.info("{}", mapper.deleteInBoard("CMBD000001", "AVTR000001"));
		assertNull(mapper.selectInBoardByPk("CMBD000001", "AVTR000001"));
	}

}
