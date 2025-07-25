package kr.or.ddit.rest.chat.service;

import java.util.List;

import kr.or.ddit.vo.project.ChatMessageVO;
import kr.or.ddit.vo.project.ChatroomVO;

public interface ChatService {
	public ChatroomVO getProjectChatroom(String prjNo);
    public List<ChatMessageVO> getChatMessages(String chatroomNo);
    public ChatMessageVO sendMessage(ChatMessageVO vo);
    public void updateReadStatus(String chatroomNo, String readMessageNo);
    
    /**
     * 채팅방 생성 (프로젝트 생성 시 자동 호출)
     */
    ChatroomVO createChatroom(String projectId, String chatroomName);
    
    /**
     * 채팅 메시지 목록 조회
     */
    List<ChatMessageVO> getChatMessages(String chatroomNo, int page, int size);
    
    /**
     * 메시지 저장
     */
    ChatMessageVO saveMessage(ChatMessageVO message);
    
    /**
     * 읽지 않은 메시지 수 업데이트
     */
    void updateUnreadCount(String chatroomNo, String senderId);
    
    /**
     * 읽음 상태 업데이트
     */
    void updateReadStatus(String chatroomNo, String userId, String readMessageNo);
    
    /**
     * 읽지 않은 메시지 수 조회
     */
    int getUnreadCount(String chatroomNo, String userId);
    
    /**
     * 채팅방 멤버 추가
     */
    void addChatroomMember(String chatroomNo, String userId);
    
    /**
     * 채팅방 멤버 제거
     */
    void removeChatroomMember(String chatroomNo, String userId);
}
