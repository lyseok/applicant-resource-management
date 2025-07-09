package kr.or.ddit.vo.community;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import kr.or.ddit.vo.common.UsersVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="boardCommentNo")
public class AdminCommentVO implements Serializable {
	private String boardCommentNo;
	private String userId;
	private String boardNo;
	@NotBlank(message = "댓글 내용을 입력해주세요.")
	private String boardCommentContent;
	private String boardWriteDate;
	private String boardDeleteDate;
	private String boardCommentStatus;
	
	private transient UsersVO users;
}
