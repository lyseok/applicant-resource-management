package kr.or.ddit.mapper.project;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.project.ChatMessageVO;
import kr.or.ddit.vo.project.ChatroomMemVO;
import kr.or.ddit.vo.project.ChatroomVO;

@Mapper
public interface ChatMapper {
    
    /**
     * 프로젝트 ID로 채팅방 조회
     */
    ChatroomVO selectChatroomByProjectId(@Param("projectId") String projectId);
    
    /**
     * 채팅방 번호로 채팅방 조회
     */
    ChatroomVO selectChatroomByNo(@Param("chatroomNo") String chatroomNo);
    
    /**
     * 채팅방 생성
     */
    int insertChatroom(ChatroomVO chatroom);
    
    /**
     * 채팅 메시지 목록 조회
     */
    List<ChatMessageVO> selectChatMessages(
        @Param("chatroomNo") String chatroomNo,
        @Param("offset") int offset,
        @Param("size") int size
    );
    
    /**
     * 채팅 메시지 저장
     */
    int insertChatMessage(ChatMessageVO message);
    
    /**
     * 채팅방 멤버 추가
     */
    int insertChatroomMember(ChatroomMemVO member);
    
    /**
     * 채팅방 멤버 제거
     */
    int deleteChatroomMember(
        @Param("chatroomNo") String chatroomNo,
        @Param("userId") String userId
    );
    
    /**
     * 읽음 메시지 번호 업데이트
     */
    int updateReadMessageNo(ChatroomMemVO member);
    
    /**
     * 읽지 않은 메시지 수 증가
     */
    int incrementUnreadCount(
        @Param("chatroomNo") String chatroomNo,
        @Param("senderId") String senderId
    );
    
    /**
     * 읽지 않은 메시지 수 조회
     */
    int selectUnreadCount(
        @Param("chatroomNo") String chatroomNo,
        @Param("userId") String userId
    );
    
    /**
     * 프로젝트 멤버 목록 조회
     */
    List<String> selectProjectMembers(@Param("projectId") String projectId);
}
