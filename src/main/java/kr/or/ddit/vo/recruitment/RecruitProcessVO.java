package kr.or.ddit.vo.recruitment;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "recruitProcessNo")
public class RecruitProcessVO implements Serializable{

	@NotBlank(groups = UpdateGroup.class)
	private String recruitProcessNo;
	private String recruitmentNo;
	private String companyName;
	@NotBlank
	private String recruitProcessStep;
	@NotBlank
	private String recruitProcessFinal;
	@NotBlank
	private String recruitProcessType;
	private String recruitProcessTypeName;
	private String recruitProcessDelDate;
	
	// 부모
	private RecruitmentNoticeVO recruitmentNotice;
	
	// 자식
	@Valid
	private List<InterviewVO> interviewList;
	private List<RecruitmentExamVO> recruitmentExamList;
	private List<ApplicantRecordVO> applicantRecordList;
}
