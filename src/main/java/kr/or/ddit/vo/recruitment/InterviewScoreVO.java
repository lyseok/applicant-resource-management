package kr.or.ddit.vo.recruitment;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "interviewScoreNo")
public class InterviewScoreVO implements Serializable{

	private String interviewScoreNo;
	private String interviewQuestionNo;
	private String interviewNo;
	private String applicantId;
	private String applicantRating;

}
