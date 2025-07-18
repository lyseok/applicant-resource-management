package kr.or.ddit.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RecruitmentExamAnswerDTO {
	  private String recruitExamNo;
	  private String recruitExamQuestNo;
	  @NotBlank(message = "보기 중 하나를 선택해주세요.")
	  private String selectedOptionNo;
	  private String applicantId;
}
