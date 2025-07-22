package kr.or.ddit.mapper.project;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.project.ChatMessageVO;
import kr.or.ddit.vo.project.ChatroomMemVO;

@Mapper
public interface ChatMessageMapper {
	public List<ChatMessageVO> selectChatMessageList();
	public ChatMessageVO selectChatMessageByPk(String messageNo);
	public int deleteChatMessage(String messageNo);
	
	
	
    public List<ChatMessageVO> selectChatMessages(@Param("chatroomNo") String chatroomNo);

    public int insertChatMessage(ChatMessageVO chatMessage);
	
    public ChatMessageVO selectChatMessage(String messageNo);
	
    public int updateReadStatus(ChatroomMemVO vo);
}
