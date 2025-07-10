package kr.or.ddit.vo.community;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import kr.or.ddit.vo.common.AvatarVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="commuPostNo")
public class CommuBoardVO implements Serializable {
	private String commuPostNo;
	private String avatarId;
	@NotBlank(message = "제목을 입력해주세요.")
	private String commuTitle;
	@NotBlank(message = "내용을 입력해주세요.")
	private String commuContents;
	@NotBlank(message = "카테고리를 선택해주세요.")
	private String categoryCode;
	private String commuWriteDate;
	private Integer commuPostHit;
	private String commuPostStatus;
	private String commuDeleteDate;
	
	private String codeName;
	
	private transient AvatarVO avatar;
	private List<CommuCommentVO> commuCommentList;
}
