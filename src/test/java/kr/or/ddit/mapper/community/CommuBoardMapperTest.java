package kr.or.ddit.mapper.community;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.community.CommuBoardVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class CommuBoardMapperTest {

	@Autowired
	CommuBoardMapper mapper;
	
	@Test
	void testSelectCommuBoard() {
		CommuBoardVO board = mapper.selectCommuBoard("CB0000002");

		log.info("{}", board);
	}

	@Test
	void testSelectCommuBoardList() {
		assertDoesNotThrow(()->mapper.selectCommuBoardList("CATE-001"));
		
		(mapper.selectCommuBoardList("CATE-001")).forEach(board->{
			log.info("{}", board);
		});
	}

	@Test
	void testInsertCommuBoard() {
		CommuBoardVO board = new CommuBoardVO();
		
		board.setAvatarId("AVT003");
		board.setCommuTitle("인턴 후기");
		board.setCommuContents("최근 참여한 마케팅 인턴십 후기를 공유합니다.");
		board.setCategoryCode("CATE-003");
		board.setCommuPostStatus("R");
		
		assertEquals(1, mapper.insertCommuBoard(board));
		
		log.info("{}", mapper.selectCommuBoard("CB0000004"));
	}

	@Test
	void testUpdateCommuBoard() {
		CommuBoardVO board = new CommuBoardVO();
		
		board.setCommuPostNo("CB0000001");
		board.setAvatarId("AVT001");
		board.setCommuTitle("취업준비 게시판의 제목입니다");
		board.setCommuContents("취준게시판 테스트 내용입니다");
		board.setCategoryCode("CATE-001");
		board.setCommuWriteDate("2025-07-03");
		board.setCommuPostHit(2);
		board.setCommuPostStatus("U");
		board.setCommuDeleteDate(null);
		
		assertEquals(1, mapper.updateCommuBoard(board));
		
		log.info("{}", mapper.selectCommuBoard("CB0000001"));
	}

	@Test
	void testDeleteCommuBoard() {
		mapper.deleteCommuBoard("CB0000002");
		assertNull(mapper.selectCommuBoard("CB0000002"));
	}

}
