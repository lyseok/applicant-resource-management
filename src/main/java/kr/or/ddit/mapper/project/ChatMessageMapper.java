package kr.or.ddit.mapper.project;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.project.ChatMessageVO;

@Mapper
public interface ChatMessageMapper {
	public List<ChatMessageVO> selectChatMessageList();
	public ChatMessageVO selectChatMessageByPk(String messageNo);
	public int insertChatMessage(ChatMessageVO chatMessage);
	public int deleteChatMessage(String messageNo);
}
