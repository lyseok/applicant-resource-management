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
	@NotBlank(message = "답변 내용을 입력해주세요.")
	private String boardCommentContent;
	private String boardWriteDate;
	private String boardDeleteDate;
	private String boardCommentStatus;
	
	private String codeName;  //문의사항에는 공통 코드가 없지만, AdminBoardVO에 AdminComment가 들어있어서 같이 넣어야 함
	
	private transient UsersVO users;
}
