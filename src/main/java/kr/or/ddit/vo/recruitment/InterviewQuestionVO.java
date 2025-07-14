package kr.or.ddit.vo.recruitment;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "interviewQuestionNo")
public class InterviewQuestionVO implements Serializable{

	private String interviewQuestionNo;
	@NotBlank
	private String interviewNo;
	@NotBlank
	private String interviewQuestionContent;
	private Integer interviewQuestionScore;
	private String interviewQuestionDelDate;
}
