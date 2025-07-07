package kr.or.ddit.vo.recruitment;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "applicantAnswerNo")
public class ApplicantAnswerVO implements Serializable{

	private String applicantAnswerNo;
	private String recruitExamNo;
	private String recruitQuestionsNo;
	private String applicantId;
	private Integer applicantScore;
	private String applicantAnswerDelDate;
}
