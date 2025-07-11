package kr.or.ddit.vo.recruitment;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "interviewNo")
public class InterviewVO implements Serializable{

	@NotBlank(groups = UpdateGroup.class)
	private String interviewNo;
	@NotBlank
	private String processNo;
	@NotBlank
	private String interviewDate;
	@NotBlank
	private String interviewLocation;
	@NotBlank
	private String interviewType;
	@NotBlank
	private Integer interviewPassScore;
	private String interviewDelDate;
	
	// 부모
	private RecruitProcessVO recruitProcess;
	
	// 자식
	private VideoInterviewVO videoInterview;
	private List<InterviewQuestionVO> interviewQuestionList;
	private List<InterviewScoreVO> interviewScoreList;
}
