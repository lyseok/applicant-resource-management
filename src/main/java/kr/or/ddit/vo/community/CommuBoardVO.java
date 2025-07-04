package kr.or.ddit.vo.community;

import java.io.Serializable;

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
}
