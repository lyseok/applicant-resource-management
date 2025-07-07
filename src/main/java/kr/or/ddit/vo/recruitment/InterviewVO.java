package kr.or.ddit.vo.recruitment;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "interviewNo")
public class InterviewVO implements Serializable{

	private String interviewNo;
	private String processNo;
	private String interviewDate;
	private String interviewLocation;
	private String interviewType;
	private Integer interviewPassScore;
	private String interviewDelDate;
}
