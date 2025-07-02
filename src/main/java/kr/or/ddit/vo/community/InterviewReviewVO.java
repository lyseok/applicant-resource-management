package kr.or.ddit.vo.community;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="interviewReviewNo")
public class InterviewReviewVO implements Serializable {
	private String interviewReviewNo;
	private String interviewNo; //NO 수정
	private String comId;
	private String jobCode;
	private String interviewDate;
	private String interviewReviewDate;
}
