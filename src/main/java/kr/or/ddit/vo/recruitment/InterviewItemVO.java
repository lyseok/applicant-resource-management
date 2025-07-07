package kr.or.ddit.vo.recruitment;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "interviewItemNo")
public class InterviewItemVO implements Serializable{

	private String interviewItemNo;
	private String interviewQuestionNo;
	private String interviewItemContent;
	private String interviewItemScore;
	private String interviewItemDelDate;
}
