package kr.or.ddit.vo.community;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of={"tagNo", "comReviewNo"})
public class ComReviewTagVO {
	private String tagNo;
	private String comReviewNo;
	private String comReviewTagDate;
}
