package kr.or.ddit.rest.chat.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.rest.chat.service.ChatService;
import kr.or.ddit.vo.project.ChatMessageVO;
import kr.or.ddit.vo.project.ChatroomVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class ChatRestController {
    private final ChatService chatService;

    // 1. 프로젝트 채팅방 정보 조회
    @GetMapping("/projects/{prjNo}/chatroom")
    public ResponseEntity<ChatroomVO> getProjectChatroom(@PathVariable String prjNo) {
        return ResponseEntity.ok(chatService.getProjectChatroom(prjNo));
    }

    // 2. 채팅 메시지 목록 조회
    @GetMapping("/chatrooms/{chatroomNo}/messages")
    public ResponseEntity<List<ChatMessageVO>> getChatMessages(@PathVariable String chatroomNo) {
        return ResponseEntity.ok(chatService.getChatMessages(chatroomNo));
    }

    // 3. 메시지 전송
    @PostMapping("/messages")
    public ResponseEntity<ChatMessageVO> postMessage(@RequestBody ChatMessageVO messageVO) {
        ChatMessageVO saved = chatService.sendMessage(messageVO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // 4. 읽음 상태 업데이트
    @PatchMapping("/chatrooms/{chatroomNo}/read")
    public ResponseEntity<?> patchChatroomRead(
            @PathVariable String chatroomNo,
            @RequestBody Map<String, String> req) {
    	
        chatService.updateReadStatus(chatroomNo, req.get("readMessageNo"));
        return ResponseEntity.ok(Map.of("message", "읽음 상태가 업데이트되었습니다."));
    }
}
