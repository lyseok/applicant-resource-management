package kr.or.ddit.vo.community;

import java.io.Serializable;

import kr.or.ddit.vo.common.UsersVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="boardCommentNo")
public class AdminCommentVO implements Serializable {
	private String boardCommentNo;
	private String userId;
	private String boardNo;
	private String boardCommentContent;
	private String boardWriteDate;
	private String boardDeleteDate;
	private String boardCommentStatus;
	
	private transient UsersVO users;
}
