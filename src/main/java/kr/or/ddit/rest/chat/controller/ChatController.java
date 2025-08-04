package kr.or.ddit.rest.chat.controller;

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

            // 메시지에 chatroomNo 설정 (경로에서 가져온 값)
            message.setChatroomNo(chatroomNo);

            // 데이터베이스에 메시지 저장
            ChatMessageVO savedMessage = chatService.saveMessage(message);

            // 채팅방 구독자들에게 브로드캐스트
            messagingTemplate.convertAndSend("/sub/chat.room." + chatroomNo, savedMessage);

            log.info("메시지 브로드캐스트 완료: chatroomNo={}", chatroomNo);

        } catch (Exception e) {
            log.error("메시지 처리 중 오류 발생: ", e);
        }
    }
}


