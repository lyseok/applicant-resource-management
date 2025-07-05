package kr.or.ddit.mapper.project;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.project.ChatroomMemVO;

@Mapper
public interface ChatroomMemMapper {
	public List<ChatroomMemVO> selectChatroomMemList();
	public ChatroomMemVO selectChatroomMemByPk(ChatroomMemVO chatroomMem);
	public int insertChatroomMem(ChatroomMemVO chatroomMem);
	public int updateChatroomMem(ChatroomMemVO chatroomMem);
	public int deleteChatroomMem(ChatroomMemVO chatroomMem);
}
