package kr.or.ddit.vo.community;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="interviewInformationNo")
public class InterviewInformationVO implements Serializable {
	private String interviewInformationNo;
	private String interviewReviewNo;
	private String evaluation;
	private String interviewLevel;
	private String interviewType;
	private String interviewContent;
}
