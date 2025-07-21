package kr.or.ddit.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class InterviewReviewDTO {
	
	private String comId;
	
	private String interviewNo;
	
	@NotBlank(message = "직무를 선택해주세요. ")
	private String jobCode;
	
	@NotBlank(message = "날짜를 선택해주세요.  ")
	private String interviewDate;
	
	@NotBlank(message = "면접 유형을 선택해주세요.  ")
	private String interviewType;
	
	@NotBlank(message = "직무를 선택해주세요. ")
	private String evaluation;
	
	@NotBlank(message = "전반적 평가를 선택해주세요. ")
	private String interviewContent;
	
	@NotBlank(message = "면접 난이도를 선택해주세요. ")
	private String interviewLevel;
	
	@NotBlank(message = "합격여부를 선택해주세요. ")
	private String interviewPassYn;
	
	@NotEmpty(message = "질문을 입력해주세요. ")
	private List<@NotBlank String> interviewQuestionContent;
	
	private String tip;
}
