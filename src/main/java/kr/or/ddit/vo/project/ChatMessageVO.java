package kr.or.ddit.vo.project;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="messageNo")
public class ChatMessageVO implements Serializable {
	private String messageNo;
	private String message;
	private String createDate;
	private String chatroomNo;
	private String prjNo;
	private String userId;
}
