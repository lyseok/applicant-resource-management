package kr.or.ddit.mapper.community;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.community.AdminBoardVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class AdminBoardMapperTest {

	@Autowired
	AdminBoardMapper mapper;

	@Test
	void testSelectAdminBoard() {
		AdminBoardVO board = mapper.selectAdminBoard("BRD0000001");

		log.info("{}", board);
	}

	@Test
	void testSelectAdminBoardList() {
		assertDoesNotThrow(()->mapper.selectAdminBoardList("BRDD-001"));
		
		List<AdminBoardVO> list = mapper.selectAdminBoardList("BRDD-001");
		list.forEach(board->{
			log.info("{}", board);
		});
	}

	@Test
//	@DisplayName("문의있을때 테스트")
	void testInsertAdminBoard() {
		AdminBoardVO board = new AdminBoardVO();
		
		board.setUserId("admin");
		board.setBoardTypeCode("BRDD-002");
		board.setBoardTitle("자주 묻는 사항 테스트입니다");
		board.setBoardContent("테스트 내용입니다");
		board.setBoardPostHit(0);
		board.setBoardStatus("R");
		
		assertEquals(1, mapper.insertAdminBoard(board));
		
		log.info("{}", mapper.selectAdminBoard("BRD0000001"));
	}

	@Test
	void testUpdateAdminBoard() {
		AdminBoardVO board = new AdminBoardVO();
		
		board.setBoardNo("BRD0000001");
		board.setUserId("user01");
		board.setBoardTypeCode("BRDD-001");
		board.setBoardTitle("문의 수정합니다");
		board.setBoardWriteDate("2025-01-02");
		board.setBoardContent("문의 수정합니다~~!");
//		board.setBoardDeleteDate(null);
		board.setBoardPostHit(4);
		board.setBoardStatus("U");
		
		assertEquals(1, mapper.updateAdminBoard(board));
		
		log.info("{}", mapper.selectAdminBoard("BRD0000001"));
	}

	@Test
	void testDeleteAdminBoard() {
		mapper.deleteAdminBoard("BRD0000001");
		assertNotNull(mapper.selectAdminBoard("BRD0000001"));
	}

}
