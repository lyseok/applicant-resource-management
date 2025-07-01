package kr.or.ddit.vo.project;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="chatroomNo")
public class ChatroomMemVO implements Serializable {
	private String chatroomNo;
	private String prjNo;
	private String userId;
	private String readMessageNo;
}
