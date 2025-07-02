package kr.or.ddit.mapper.community;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
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
	@DisplayName("게시글 단건조회 테스트1")
	@Disabled
	void testSelectAdminBoard() {
		AdminBoardVO board = mapper.selectAdminBoard("b001");

		log.info("{}", board);
	}

	@Test
	@DisplayName("게시글 목록조회 테스트1")
	@Disabled
	void testSelectAdminBoardList() {
		mapper.selectAdminBoardList("BRDD-003").forEach(board->{
			log.info("{}", board);
		});
		assertDoesNotThrow(()->mapper.selectAdminBoardList("BRDD-003"));
	}

	@Test
	@DisplayName("게시글 등록 테스트1")
	@Disabled
	void testInsertAdminBoard() {
		AdminBoardVO board = new AdminBoardVO();
		
		board.setBoardNo("b001");
		board.setUserId("admin");
		board.setBoardTypeCode("BRDD-003");
		board.setBoardTitle("테스트입니다");
		board.setBoardWriteDate("2025-01-01");
		board.setBoardContent("테스트 내용입니다");
		board.setBoardDeleteDate(null);
		board.setBoardPostHit(3);
		board.setBoardStatus("R");
		
		assertEquals(1, mapper.insertAdminBoard(board));
	}

	@Test
	@DisplayName("게시글 수정 테스트1")
	@Disabled
	void testUpdateAdminBoard() {
		AdminBoardVO board = new AdminBoardVO();
		
		board.setBoardNo("b001");
		board.setUserId("admin");
		board.setBoardTypeCode("BRDD-003");
		board.setBoardTitle("테스트 수정합니다");
		board.setBoardWriteDate("2025-01-02");
		board.setBoardContent("수정 내용입니다");
		board.setBoardDeleteDate(null);
		board.setBoardPostHit(4);
		board.setBoardStatus("U");
		
		assertEquals(1, mapper.updateAdminBoard(board));
	}

	@Test
	@DisplayName("게시글 삭제 테스트1")
	@Disabled
	void testDeleteAdminBoard() {
		mapper.deleteAdminBoard("b001");
		assertNotNull(mapper.selectAdminBoard("b001"));
	}

}
