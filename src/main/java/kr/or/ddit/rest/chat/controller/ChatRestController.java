package kr.or.ddit.rest.chat.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class ChatRestController {
    private final ChatService chatService;

    private final SimpMessagingTemplate messagingTemplate;

    /**
     * 프로젝트 채팅방 조회
     */
    @GetMapping("/projects/{projectId}/chatroom")
    public ChatroomVO getProjectChatroom(@PathVariable String projectId) {
        log.info("프로젝트 채팅방 조회: projectId={}", projectId);
        return chatService.getProjectChatroom(projectId);
    }

    /**
     * 채팅 메시지 목록 조회
     */
    @GetMapping("/chatrooms/{chatroomNo}/messages")
    public List<ChatMessageVO> getChatMessages(
            @PathVariable String chatroomNo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size) {
        
        log.info("채팅 메시지 조회: chatroomNo={}, page={}, size={}", chatroomNo, page, size);
        return chatService.getChatMessages(chatroomNo, page, size);
    }

    /**
     * HTTP API로 메시지 전송 (WebSocket fallback)
     */
    @PostMapping("/chat/messages")
    public ChatMessageVO sendMessage(@RequestBody ChatMessageVO message) {
        try {
            log.info("HTTP API 메시지 전송: chatroomNo={}, userId={}", 
                    message.getChatroomNo(), message.getUserId());

            // 메시지 저장
            ChatMessageVO savedMessage = chatService.saveMessage(message);

            // WebSocket으로 실시간 전송 (연결된 사용자들에게)
            messagingTemplate.convertAndSend(
                "/sub/chat.room." + message.getChatroomNo(), 
                savedMessage
            );

            return savedMessage;

        } catch (Exception e) {
            log.error("HTTP API 메시지 전송 실패: ", e);
            throw new RuntimeException("메시지 전송에 실패했습니다: " + e.getMessage());
        }
    }

    /**
     * 읽음 상태 업데이트
     */
    @PostMapping("/chatrooms/{chatroomNo}/read")
    public void updateReadStatus(
            @PathVariable String chatroomNo,
            @RequestBody ReadStatusRequest request) {
        
        log.info("읽음 상태 업데이트: chatroomNo={}, userId={}, readMessageNo={}", 
                chatroomNo, request.getUserId(), request.getReadMessageNo());
        
        chatService.updateReadStatus(chatroomNo, request.getUserId(), request.getReadMessageNo());
    }

    /**
     * 읽지 않은 메시지 수 조회
     */
    @GetMapping("/chatrooms/{chatroomNo}/unread-count")
    public int getUnreadCount(
            @PathVariable String chatroomNo,
            @RequestParam String userId) {
        
        return chatService.getUnreadCount(chatroomNo, userId);
    }

    // 읽음 상태 업데이트 요청 DTO
    public static class ReadStatusRequest {
        private String userId;
        private String readMessageNo;

        // getters and setters
        public String getUserId() { return userId; }
        public void setUserId(String userId) { this.userId = userId; }
        public String getReadMessageNo() { return readMessageNo; }
        public void setReadMessageNo(String readMessageNo) { this.readMessageNo = readMessageNo; }
    }
}
//@RestController
//@RequestMapping("/api")
//@Slf4j
//@RequiredArgsConstructor
//public class ChatRestController {
//    private final ChatService chatService;
//
//    // 1. 프로젝트 채팅방 정보 조회
//    @GetMapping("/projects/{prjNo}/chatroom")
//    public ResponseEntity<ChatroomVO> getProjectChatroom(@PathVariable String prjNo) {
//        return ResponseEntity.ok(chatService.getProjectChatroom(prjNo));
//    }
//
//    // 2. 채팅 메시지 목록 조회
//    @GetMapping("/chatrooms/{chatroomNo}/messages")
//    public ResponseEntity<List<ChatMessageVO>> getChatMessages(@PathVariable String chatroomNo) {
//        return ResponseEntity.ok(chatService.getChatMessages(chatroomNo));
//    }
//
//    // 3. 메시지 전송
//    @PostMapping("/messages")
//    public ResponseEntity<ChatMessageVO> postMessage(@RequestBody ChatMessageVO messageVO) {
//        ChatMessageVO saved = chatService.sendMessage(messageVO);
//        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
//    }
//
//    // 4. 읽음 상태 업데이트
//    @PatchMapping("/chatrooms/{chatroomNo}/read")
//    public ResponseEntity<?> patchChatroomRead(
//            @PathVariable String chatroomNo,
//            @RequestBody Map<String, String> req) {
//    	
//        chatService.updateReadStatus(chatroomNo, req.get("readMessageNo"));
//        return ResponseEntity.ok(Map.of("message", "읽음 상태가 업데이트되었습니다."));
//    }
//}
