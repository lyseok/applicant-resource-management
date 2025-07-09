package kr.or.ddit.vo.recruitment;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"videoInterviewNo", "interviewNo"})
public class VideoInterviewVO implements Serializable{

	private String videoInterviewNo;
	private String interviewNo;
	private String roomTitle;
	private Integer maxJoinCount;
	private String startDate;
	private String endDate;
	private String companyInterviewUrl;
	private String videoInterviewDelDate;
	private String roomId;
}	
