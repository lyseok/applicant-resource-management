package kr.or.ddit.rest.chat.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.vo.project.ChatMessageVO;
import kr.or.ddit.vo.project.ChatroomVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class ChatRestController {
	@GetMapping("/projects/{prjNo}/chatroom")
	public ChatroomVO getChatroomApi() {
		return null;
	}
	
	@GetMapping("/chatrooms/{chatroomNo}/messages")
	public List<ChatMessageVO> getChatMessageListApi(@RequestParam String page, @RequestParam String size){
		return null;
	}
	
	@PostMapping("message")
	public ChatMessageVO postChatMessageApi(@RequestBody ChatMessageVO chatMessage) {
		return null;
	}
	
	@PatchMapping("/chatrooms/{chatroomNo}/read")
	public ResponseEntity<?> patchChatmessageApi(@RequestBody Map<String, String> readMessageData) {
		return null;
	}
}
