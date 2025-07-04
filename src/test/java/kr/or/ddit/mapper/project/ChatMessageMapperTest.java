package kr.or.ddit.mapper.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.project.ChatMessageVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class ChatMessageMapperTest {

	@Autowired
	private ChatMessageMapper mapper;

	@Test
	void testSelectChatMessageList() {
		List<ChatMessageVO> list = mapper.selectChatMessageList();
		list.forEach(vo -> log.info("{}", vo));
		assertNotNull(list);
	}

	@Test
	void testSelectChatMessageByPk() {
		ChatMessageVO vo = mapper.selectChatMessageByPk("MASG000001");
		log.info("{}", vo);
		assertNotNull(vo);
	}

	@Test
	void testInsertChatMessage() {
		ChatMessageVO vo = new ChatMessageVO();
		vo.setMessage("테스트 메시지입니다.");
		vo.setChatroomNo("CATR000001");
		vo.setPrjNo("PRJT000001");
		vo.setUserId("user01");

		assertEquals(1, mapper.insertChatMessage(vo));
		log.info("Inserted MESSAGE_NO: {}", vo.getMessageNo());
	}

	@Test
	void testDeleteChatMessage() {
		String messageNo = "MASG000004";
		assertEquals(1, mapper.deleteChatMessage(messageNo));
		assertNull(mapper.selectChatMessageByPk(messageNo));
	}
}
