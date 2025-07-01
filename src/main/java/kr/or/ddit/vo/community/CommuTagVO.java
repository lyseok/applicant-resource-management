package kr.or.ddit.vo.community;


import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of={"tagNo", "boardNo"})
public class CommuTagVO implements Serializable {
	private String tagNo;
	private String boardNo;
	private String commuTagDate;
}
