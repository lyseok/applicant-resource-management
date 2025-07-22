package kr.or.ddit.rest.chat.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.project.ChatMessageMapper;
import kr.or.ddit.mapper.project.ChatroomMapper;
import kr.or.ddit.vo.project.ChatMessageVO;
import kr.or.ddit.vo.project.ChatroomMemVO;
import kr.or.ddit.vo.project.ChatroomVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final ChatroomMapper chatMapper;
    private final ChatMessageMapper chatMessageMapper;

    @Override
    public ChatroomVO getProjectChatroom(String prjNo) {
        return chatMapper.selectProjectChatroom(prjNo);
    }

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
}
