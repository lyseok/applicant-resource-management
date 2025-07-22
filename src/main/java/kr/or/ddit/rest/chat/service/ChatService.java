package kr.or.ddit.rest.chat.service;

import java.util.List;

import kr.or.ddit.vo.project.ChatMessageVO;
import kr.or.ddit.vo.project.ChatroomVO;

public interface ChatService {
	public ChatroomVO getProjectChatroom(String prjNo);
    public List<ChatMessageVO> getChatMessages(String chatroomNo);
    public ChatMessageVO sendMessage(ChatMessageVO vo);
    public void updateReadStatus(String chatroomNo, String readMessageNo);
}
