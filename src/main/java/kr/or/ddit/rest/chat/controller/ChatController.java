package kr.or.ddit.rest.chat.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import kr.or.ddit.rest.chat.service.ChatService;
import kr.or.ddit.vo.project.ChatMessageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    private final SimpMessagingTemplate messagingTemplate;

    /**
     * WebSocket으로 받은 메시지 처리
     */
    @MessageMapping("/chat.message.{chatroomNo}")
    public void handleMessage(
            @DestinationVariable String chatroomNo,
            @Payload ChatMessageVO message,
            SimpMessageHeaderAccessor headerAccessor) {
        
        try {
            log.info("채팅 메시지 수신: chatroomNo={}, userId={}, message={}", 
                    chatroomNo, message.getUserId(), message.getMessage());

            // 메시지 유효성 검증
            if (message.getMessage() == null || message.getMessage().trim().isEmpty()) {
                log.warn("빈 메시지 수신: {}", message);
                return;
            }

            // 데이터베이스에 메시지 저장
            ChatMessageVO savedMessage = chatService.saveMessage(message);

            // 채팅방 구독자들에게 브로드캐스트
            messagingTemplate.convertAndSend("/sub/chat.room." + chatroomNo, savedMessage);


        } catch (Exception e) {
            log.error("메시지 처리 중 오류 발생: ", e);
            
            // 에러 메시지를 발신자에게만 전송
            messagingTemplate.convertAndSendToUser(
                message.getUserId(),
                "/queue/errors",
                "메시지 전송에 실패했습니다: " + e.getMessage()
            );
        }
    }
}


