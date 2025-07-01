package kr.or.ddit.vo.community;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="commuCommentNo")
public class CommuCommentVO implements Serializable {
	private String commuCommentNo;
	private String commuPostNo;
	private String avatarId;
	private String commuCommentContent;
	private String commuCommentWriteDate;
	private String commuCommentStatus;
	private String commuCommentDeleteDate;
}
