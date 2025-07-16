package kr.or.ddit.dto;

import java.util.List;

import kr.or.ddit.vo.recruitment.RecruitmentExamQuestionsVO;
import lombok.Data;
@Data
public class RecruitmentExamDetailDTO {
	  private String recruitExamNo;
	  private String recruitExamName;
	  private Integer recruitExamTime;
	  private List<RecruitmentExamQuestionsVO> questions;
}
