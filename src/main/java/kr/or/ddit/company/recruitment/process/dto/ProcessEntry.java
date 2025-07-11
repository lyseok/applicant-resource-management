package kr.or.ddit.company.recruitment.process.dto;

import jakarta.validation.Valid;
import kr.or.ddit.vo.recruitment.InterviewVO;
import kr.or.ddit.vo.recruitment.RecruitProcessVO;
import kr.or.ddit.vo.recruitment.RecruitmentExamVO;
import lombok.Data;

@Data
public class ProcessEntry {

	@Valid
	private RecruitProcessVO process;
	
	@Valid
	private InterviewVO interview;
	
	@Valid
	private RecruitmentExamVO exam;
	
	private String companyExamNo;
}
