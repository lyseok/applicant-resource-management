package kr.or.ddit.vo.recruitment;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "interviewScoreNo")
public class InterviewScoreVO implements Serializable{
	@NotBlank(groups = UpdateGroup.class)
	private String interviewScoreNo;
	@NotBlank
	private String interviewNo;
	@NotBlank
	private String applicantId;
	private Integer applicantRating;
	private String interviewScoreDelDate;
	
	private List<InterviewScoreVO> interviewScoreList;
}
