package kr.or.ddit.vo.community;

import java.io.Serializable;

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
}
