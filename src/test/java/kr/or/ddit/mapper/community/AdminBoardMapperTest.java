package kr.or.ddit.mapper.community;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
		AdminBoardVO board = mapper.selectAdminBoard("ABNO000001");

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
	void testInsertAdminBoard() {
		AdminBoardVO board = new AdminBoardVO();
		
		board.setUserId("USR007");
		board.setBoardTypeCode("BRDD-002");
		board.setBoardTitle("자주 묻는 사항 테스트입니다");
		board.setBoardContent("테스트 내용입니다");
		board.setBoardPostHit(0);
		board.setBoardStatus("R");
		
		assertEquals(1, mapper.insertAdminBoard(board));
		
		log.info("{}", mapper.selectAdminBoard("ABNO000002"));
	}

	@Test
	void testUpdateAdminBoard() {
		AdminBoardVO board = new AdminBoardVO();
		
		board.setBoardNo("ABNO000001");
		board.setUserId("USR001");
		board.setBoardTypeCode("BRDD-001");
		board.setBoardTitle("문의 수정합니다");
		board.setBoardWriteDate("20250102");
		board.setBoardContent("문의 수정합니다~~!");
		board.setBoardPostHit(4);
		board.setBoardStatus("U");
		
		assertEquals(1, mapper.updateAdminBoard(board));
		
		log.info("{}", mapper.selectAdminBoard("ABNO000001"));
	}

	@Test
	void testDeleteAdminBoard() {
		mapper.deleteAdminBoard("ABNO000002");
		log.info("{}", mapper.deleteAdminBoard("ABNO000002"));  //0이면 삭제실패(이미 삭제된 것도 0)
		assertNull(mapper.selectAdminBoard("ABNO000002"));
	}

}
