package kr.or.ddit.vo.recruitment;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "recruitProcessNo")
public class RecruitProcessVO implements Serializable{

	private String recruitProcessNo;
	private String recruitmentNo;
	private String companyName;
	private String recruitProcessStep;
	private String recruitProcessFinal;
	private String recruitProcessType;
	private String recruitProcessTypeName;
	private String recruitProcessDelDate;
	
	// 부모
	private RecruitmentNoticeVO recruitmentNotice;
	
	// 자식
	private List<InterviewVO> interviewList;
	private List<RecruitmentExamVO> recruitmentExamList;
	private List<ApplicantRecordVO> applicantRecordList;
}
