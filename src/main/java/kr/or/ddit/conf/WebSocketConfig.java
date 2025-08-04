package kr.or.ddit.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 메시지 브로커 설정 - 클라이언트가 구독할 수 있는 경로
        config.enableSimpleBroker("/sub");
        
        // 클라이언트가 메시지를 보낼 때 사용할 경로 prefix
        config.setApplicationDestinationPrefixes("/pub");
        
        // 사용자별 개인 메시지를 위한 prefix
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // WebSocket 엔드포인트 등록
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("http://192.168.34.70:3000")  // CORS 설정
                .withSockJS();  // SockJS 지원
    }
}
