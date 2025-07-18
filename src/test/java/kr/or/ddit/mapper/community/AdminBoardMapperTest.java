package kr.or.ddit.mapper.community;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	void testSelectAdminBoardByPk() {
		AdminBoardVO board = mapper.selectAdminBoardByPk("ABNO000001");

		log.info("{}", board);
	}

	@Test
	void testSelectAFaqListByCgn() {
		Map<String, Object> paramMap = new HashMap<>();
	    paramMap.put("boardTypeCode", "UFAQ-U5");

	    assertDoesNotThrow(() -> {
	        List<AdminBoardVO> list = mapper.selectAFaqListByCgn(paramMap);
	        list.forEach(board -> log.info("기업/일반 FAQ 전용 리스트 : {}", board));
	    });
	}

	@Test
	void testSelectAFaqListByUcn() {
		assertDoesNotThrow(()->mapper.selectAFaqListByUcn("BRDD-002"));  //얜 이것만을 위한 거임
		
		List<AdminBoardVO> list = mapper.selectAFaqListByUcn("BRDD-002");
		list.forEach(board->{
			log.info("자주묻는질문 전용 리스트 : {}", board);
		});
	}

	@Test
	void testSelectAdminBoardListByType() {
		assertDoesNotThrow(()->mapper.selectAdminBoardListByType("UFAQ-U5"));
		
		List<AdminBoardVO> list = mapper.selectAdminBoardListByType("UFAQ-U5");
		list.forEach(board->{
			log.info(" BRDD-002 제외한 모든 타입 리스트 : {}", board);
		});
	}
	@Test
	void testSelectDelAboardList() {
		assertDoesNotThrow(()->mapper.selectDelAboardList());
		
		List<AdminBoardVO> list = mapper.selectDelAboardList();
		list.forEach(board->{
			log.info(" 삭제된 게시글만 : {}", board);
		});
	}

	@Test
	void testSelectAdminBoardList() {
		assertDoesNotThrow(()->mapper.selectAdminBoardList());
		
		List<AdminBoardVO> list = mapper.selectAdminBoardList();
		list.forEach(board->{
			log.info("{}", board);
		});
	}
	
	@Test
	void testInsertAdminBoard() {
		AdminBoardVO board = new AdminBoardVO();
		
		board.setUserId("USR007");
		board.setBoardTypeCode("UFAQ-U5");
		board.setBoardTitle("개인회원 탈퇴 방법");
		board.setBoardContent("탈퇴시 방법입니다.");
		board.setBoardPostHit(0);
		board.setBoardStatus("R");
		
		assertEquals(1, mapper.insertAdminBoard(board));
		
		log.info("{}", mapper.selectAdminBoardByPk("ABNO000002"));
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
		
		log.info("{}", mapper.selectAdminBoardByPk("ABNO000001"));
	}

	@Test
	void testDeleteAdminBoard() {
		mapper.deleteAdminBoard("ABNO000002");
		log.info("{}", mapper.deleteAdminBoard("ABNO000002"));  //0이면 삭제실패(이미 삭제된 것도 0)
		assertNull(mapper.selectAdminBoardByPk("ABNO000002"));
	}

}
