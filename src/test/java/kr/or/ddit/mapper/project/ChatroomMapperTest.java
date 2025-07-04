package kr.or.ddit.mapper.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.project.ChatroomVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class ChatroomMapperTest {

	@Autowired
	private ChatroomMapper mapper;

	@Test
	void testSelectChatroomList() {
		List<ChatroomVO> list = mapper.selectChatroomList();
		list.forEach(vo -> log.info("{}", vo));
		assertNotNull(list);
	}

	@Test
	void testSelectChatroomByPk() {
		ChatroomVO vo = mapper.selectChatroomByPk("CATR000001");
		log.info("{}", vo);
		assertNotNull(vo);
	}

	@Test
	void testInsertChatroom() {
		ChatroomVO vo = new ChatroomVO();
		vo.setPrjNo("PRJT000001");
		vo.setChatroomName("프로젝트 채팅방");

		assertEquals(1, mapper.insertChatroom(vo));
	}

	@Test
	void testUpdateChatroom() {
		ChatroomVO vo = new ChatroomVO();
		vo.setChatroomNo("CHAT000004");
		vo.setPrjNo("PRJT000001");
		vo.setChatroomName("채팅방 이름 수정");

		assertEquals(1, mapper.updateChatroom(vo));
	}

	@Test
	void testDeleteChatroom() {
		assertEquals(1, mapper.deleteChatroom("CHAT000001"));
		assertNull(mapper.selectChatroomByPk("CHAT000001"));
	}
}
