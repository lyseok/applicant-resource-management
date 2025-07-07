package kr.or.ddit.vo.community;

import java.io.Serializable;
import java.util.List;

import kr.or.ddit.vo.common.UsersVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="boardNo")
public class AdminBoardVO implements Serializable {
	private String boardNo;
	private String userId;
	private String boardTypeCode;
	private String boardTitle;
	private String boardWriteDate;
	private String boardContent;
	private String boardDeleteDate;
	private Integer boardPostHit;
	private String boardStatus;

	private transient UsersVO users;
	private transient List<AdminCommentVO> adminCommentList;
}
