package kr.or.ddit.rest.chat.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.project.ChatMapper;
import kr.or.ddit.mapper.project.ChatMessageMapper;
import kr.or.ddit.mapper.project.ChatroomMapper;
import kr.or.ddit.vo.project.ChatMessageVO;
import kr.or.ddit.vo.project.ChatroomMemVO;
import kr.or.ddit.vo.project.ChatroomVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final ChatroomMapper chatroomMapper;
    private final ChatMessageMapper chatMessageMapper;

//    @Override
//    public ChatroomVO getProjectChatroom(String prjNo) {
//        return chatroomMapper.selectProjectChatroom(prjNo);
//    }

    @Override
    public List<ChatMessageVO> getChatMessages(String chatroomNo) {
        return chatMessageMapper.selectChatMessages(chatroomNo);
    }

    @Override
    public ChatMessageVO sendMessage(ChatMessageVO vo) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String username = authentication.getName();
    	vo.setUserId(username);
        chatMessageMapper.insertChatMessage(vo);
        return chatMessageMapper.selectChatMessage(vo.getMessageNo());
    }

    @Override
    public void updateReadStatus(String chatroomNo, String readMessageNo) {
    	ChatroomMemVO vo = new ChatroomMemVO();
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String username = authentication.getName();
    	vo.setUserId(username);
    	vo.setChatroomNo(chatroomNo);
    	vo.setReadMessageNo(readMessageNo);
    	
    	chatMessageMapper.updateReadStatus(vo);
    }
    
    
    private final ChatMapper chatMapper;

    @Override
    public ChatroomVO getProjectChatroom(String projectId) {
        log.info("프로젝트 채팅방 조회: projectId={}", projectId);
        
        ChatroomVO chatroom = chatMapper.selectChatroomByProjectId(projectId);
        
        if (chatroom == null) {
            // 채팅방이 없으면 자동 생성
            log.info("채팅방이 없어 자동 생성: projectId={}", projectId);
            chatroom = createChatroom(projectId, "프로젝트 채팅방");
        }
        
        return chatroom;
    }

    @Override
    public ChatroomVO createChatroom(String projectId, String chatroomName) {
        log.info("채팅방 생성: projectId={}, chatroomName={}", projectId, chatroomName);
        
        // 채팅방 번호 생성
        String chatroomNo = "CHAT" + System.currentTimeMillis();
        
        ChatroomVO chatroom = new ChatroomVO();
        chatroom.setChatroomNo(chatroomNo);
        chatroom.setPrjNo(projectId);
        chatroom.setChatroomName(chatroomName);
        
        // 채팅방 생성
        chatMapper.insertChatroom(chatroom);
        
        // 프로젝트 멤버들을 채팅방에 자동 추가
        List<String> projectMembers = chatMapper.selectProjectMembers(projectId);
        for (String userId : projectMembers) {
            addChatroomMember(chatroomNo, userId);
        }
        
        return chatMapper.selectChatroomByNo(chatroomNo);
    }

    @Override
    public List<ChatMessageVO> getChatMessages(String chatroomNo, int page, int size) {
        log.info("채팅 메시지 조회: chatroomNo={}, page={}, size={}", chatroomNo, page, size);
        
        int offset = page * size;
        return chatMapper.selectChatMessages(chatroomNo, offset, size);
    }

    @Override
    public ChatMessageVO saveMessage(ChatMessageVO message) {
        log.info("메시지 저장: chatroomNo={}, userId={}", message.getChatroomNo(), message.getUserId());
        
        // 메시지 저장
        chatMapper.insertChatMessage(message);
        
        return message;
    }

    @Override
    public void updateUnreadCount(String chatroomNo, String senderId) {
        log.info("읽지 않은 메시지 수 업데이트: chatroomNo={}, senderId={}", chatroomNo, senderId);
        
        // 발신자를 제외한 모든 멤버의 읽지 않은 메시지 수 증가
        chatMapper.incrementUnreadCount(chatroomNo, senderId);
    }

    @Override
    public void updateReadStatus(String chatroomNo, String userId, String readMessageNo) {
        log.info("읽음 상태 업데이트: chatroomNo={}, userId={}, readMessageNo={}", 
                chatroomNo, userId, readMessageNo);
        
        ChatroomMemVO member = new ChatroomMemVO();
        member.setChatroomNo(chatroomNo);
        member.setUserId(userId);
        member.setReadMessageNo(readMessageNo);
        
        chatMapper.updateReadMessageNo(member);
    }

    @Override
    public int getUnreadCount(String chatroomNo, String userId) {
        return chatMapper.selectUnreadCount(chatroomNo, userId);
    }

    @Override
    public void addChatroomMember(String chatroomNo, String userId) {
        log.info("채팅방 멤버 추가: chatroomNo={}, userId={}", chatroomNo, userId);
        
        ChatroomMemVO member = new ChatroomMemVO();
        member.setChatroomNo(chatroomNo);
        member.setUserId(userId);
        member.setReadMessageNo("0"); // 초기값
        
        chatMapper.insertChatroomMember(member);
    }

    @Override
    public void removeChatroomMember(String chatroomNo, String userId) {
        log.info("채팅방 멤버 제거: chatroomNo={}, userId={}", chatroomNo, userId);
        
        chatMapper.deleteChatroomMember(chatroomNo, userId);
    }
}
