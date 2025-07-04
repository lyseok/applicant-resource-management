package kr.or.ddit.mapper.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.project.ChatroomMemVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class ChatroomMemMapperTest {

	@Autowired
	private ChatroomMemMapper mapper;

	@Test
	void testSelectChatroomMemList() {
		List<ChatroomMemVO> list = mapper.selectChatroomMemList();
		list.forEach(vo -> log.info("{}", vo));
		assertNotNull(list);
	}

	@Test
	void testSelectChatroomMemByPk() {
		ChatroomMemVO param = new ChatroomMemVO();
		param.setChatroomNo("CATR000002");
		param.setPrjNo("PRJT000002");
		param.setUserId("user02");

		ChatroomMemVO vo = mapper.selectChatroomMemByPk(param);
		log.info("{}", vo);
		assertNotNull(vo);
	}

	@Test
	void testInsertChatroomMem() {
		ChatroomMemVO vo = new ChatroomMemVO();
		vo.setChatroomNo("CATR000002");
		vo.setPrjNo("PRJT000002");
		vo.setUserId("user02");
		vo.setReadMessageNo("MSG000001");

		assertEquals(1, mapper.insertChatroomMem(vo));
	}

	@Test
	void testUpdateChatroomMem() {
		ChatroomMemVO vo = new ChatroomMemVO();
		vo.setChatroomNo("CATR000002");
		vo.setPrjNo("PRJT000002");
		vo.setUserId("user02");
		vo.setReadMessageNo("MSG000005");

		assertEquals(1, mapper.updateChatroomMem(vo));
	}

	@Test
	void testDeleteChatroomMem() {
		ChatroomMemVO vo = new ChatroomMemVO();
		vo.setChatroomNo("CATR000002");
		vo.setPrjNo("PRJT000002");
		vo.setUserId("user02");

		assertEquals(1, mapper.deleteChatroomMem(vo));
		assertNull(mapper.selectChatroomMemByPk(vo));
	}
}
