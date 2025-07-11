package kr.or.ddit.vo.recruitment;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"interviewScoreNo", "interviewQuestionNo"})
public class InterviewQuestionScoreVO implements Serializable {
	@NotBlank
	private String interviewScoreNo;
	@NotBlank
	private String interviewQuestionNo;
	@NotNull
	private Integer interviewQuestionScore;
	
	private InterviewScoreVO interviewScore;
	private InterviewQuestionVO interviewQuestion;
}
