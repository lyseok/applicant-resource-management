package kr.or.ddit.vo.recruitment;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "interviewQuestionNo")
public class InterviewQuestionVO implements Serializable{

	private String interviewQuestionNo;
	private String interviewNo;
	private String interviewQuestionContent;

}
