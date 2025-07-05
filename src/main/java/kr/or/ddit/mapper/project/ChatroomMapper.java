package kr.or.ddit.mapper.project;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.project.ChatroomVO;

@Mapper
public interface ChatroomMapper {
	public List<ChatroomVO> selectChatroomList();
	public ChatroomVO selectChatroomByPk(String ChatroomNo);
	public int insertChatroom(ChatroomVO Chatroom);
	public int updateChatroom(ChatroomVO Chatroom);
	public int deleteChatroom(String ChatroomNo);
}
