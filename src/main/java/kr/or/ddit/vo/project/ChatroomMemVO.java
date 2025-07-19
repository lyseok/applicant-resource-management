package kr.or.ddit.vo.project;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of={"chatroomNo", "prjNo", "userId"})
public class ChatroomMemVO implements Serializable {
	private String chatroomNo;
	private String prjNo;
	private String userId;
	private String readMessageNo;
	
	private ChatroomVO chatroom;
	private List<ChatMessageVO> chatMessageList;
}
