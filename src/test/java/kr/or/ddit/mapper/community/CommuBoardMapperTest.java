package kr.or.ddit.mapper.community;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
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
	void testSelectCommuBoardByPk() {
		CommuBoardVO board = mapper.selectCommuBoardByPk("CMBD000001");

		log.info("{}", board);
	}

	@Test
	@DisplayName("셀렉트")
	void testSelectCommuBoardListByCate() {
		assertDoesNotThrow(()->mapper.selectCommuBoardListByCate("CATE-003"));
		
		List<CommuBoardVO> list = mapper.selectCommuBoardListByCate("CATE-003");
		log.info("리스트 확인 : {}", list);
		list.forEach(c->log.info("상세 내용 : {}", c));
		
		assertEquals(1, list.size());
	}

	@Test
	void testInsertCommuBoard() {
		CommuBoardVO board = new CommuBoardVO();
		
		board.setAvatarId("AVTR000001");
		board.setCommuTitle("두번째 인턴 후기");
		board.setCommuContents("두번째로 최근 참여한 마케팅 인턴십 후기를 공유합니다.");
		board.setCategoryCode("CATE-003");
		board.setCommuPostStatus("R");
		
		assertEquals(1, mapper.insertCommuBoard(board));
		
		log.info("{}", mapper.selectCommuBoardByPk("CMBD000001"));
	}

	@Test
	void testUpdateCommuBoard() {
		CommuBoardVO board = new CommuBoardVO();
		
		board.setCommuPostNo("CMBD000001");
		board.setAvatarId("AVTR000001");
		board.setCommuTitle("취업준비 게시판의 제목입니다");
		board.setCommuContents("취준게시판 테스트 내용입니다");
		board.setCategoryCode("CATE-001");
		board.setCommuWriteDate("20250703");
		board.setCommuPostHit(2);
		board.setCommuPostStatus("U");
		board.setCommuDeleteDate(null);
		
		assertEquals(1, mapper.updateCommuBoard(board));
		
		log.info("{}", mapper.selectCommuBoardByPk("CMBD000001"));
	}

	@Test
	void testDeleteCommuBoard() {
		log.info("{}", mapper.deleteCommuBoard("CMBD000001"));
		assertNull(mapper.selectCommuBoardByPk("CMBD000001"));
	}

}
