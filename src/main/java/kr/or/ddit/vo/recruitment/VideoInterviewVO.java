package kr.or.ddit.vo.recruitment;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"videoInterviewNo", "interviewNo"})
public class VideoInterviewVO implements Serializable{

	private String videoInterviewNo;
	private String interviewNo;
	private String videoInterviewStartTime;
	private String videoInterviewEndTime;
	private String videoInterviewStatus;
	private String videoInterviewUrl;
	private String videoInterviewDelDate;
}	
