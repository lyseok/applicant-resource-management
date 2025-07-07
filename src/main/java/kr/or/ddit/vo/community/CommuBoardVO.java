package kr.or.ddit.vo.community;

import java.io.Serializable;
import java.util.List;

import kr.or.ddit.vo.common.AvatarVO;
import kr.or.ddit.vo.common.UsersVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="commuPostNo")
public class CommuBoardVO implements Serializable {
	private String commuPostNo;
	private String avatarId;
	private String commuTitle;
	private String commuContents;
	private String categoryCode;
	private String commuWriteDate;
	private Integer commuPostHit;
	private String commuPostStatus;
	private String commuDeleteDate;
	
	private transient AvatarVO avatars;
	private List<CommuCommentVO> commuCommentList;
}
