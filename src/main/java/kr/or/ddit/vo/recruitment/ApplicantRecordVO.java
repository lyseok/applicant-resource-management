package kr.or.ddit.vo.recruitment;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "applicantRecordNo")
public class ApplicantRecordVO implements Serializable{
	private String applicantRecordNo;
	private String recruitmentNo;
	private String applicantId;
	private String interviewUrl;
	private String stepPassYn;
	private String stepApplicationYn;
	private String evaluationStartTime;
	private String applicantName;
	
	private ApplicantVO applicant;
}
