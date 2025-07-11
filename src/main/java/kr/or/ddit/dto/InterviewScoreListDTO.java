package kr.or.ddit.dto;

import java.util.List;

import jakarta.validation.Valid;
import kr.or.ddit.vo.recruitment.InterviewScoreVO;
import lombok.Data;

@Data
public class InterviewScoreListDTO {
	@Valid
	private List<InterviewScoreVO> interviewScoreList;
}
