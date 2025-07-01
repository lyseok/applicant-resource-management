package kr.or.ddit.vo.community;

import java.io.Serializable;

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
}
