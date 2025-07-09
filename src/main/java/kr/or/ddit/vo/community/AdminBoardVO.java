package kr.or.ddit.vo.community;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import kr.or.ddit.vo.common.CmnCodeVO;
import kr.or.ddit.vo.common.UsersVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="boardNo")
public class AdminBoardVO implements Serializable {
	private String boardNo;
	private String userId;
	@NotBlank(message = "게시판 유형을 선택해주세요.")
	private String boardTypeCode;
	@NotBlank(message = "제목을 입력해주세요.")
	private String boardTitle;
	private String boardWriteDate;
	@NotBlank(message = "내용을 입력해주세요.")
	private String boardContent;
	private String boardDeleteDate;
	private Integer boardPostHit;
	private String boardStatus;
	
	private String codeName;  //공통코드그룹명에 따른 공통코드 리스트 가져옴
	private transient CmnCodeVO code;  //공통코드에 따른 공통코드그룹 리스트 가져옴

	private transient UsersVO users;
	private transient List<AdminCommentVO> adminCommentList;  // 게시판 댓글 리스트
}
