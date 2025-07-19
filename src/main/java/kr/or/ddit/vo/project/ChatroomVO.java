package kr.or.ddit.vo.project;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="chatroomNo")
public class ChatroomVO implements Serializable {
	private String chatroomNo;
	private String prjNo;
	private String chatroomName;
	
	private ProjectVO project;
	private List<ChatroomMemVO> chatroomMemList;
}
