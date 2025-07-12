package kr.or.ddit.dto;

import java.util.List;

import jakarta.validation.Valid;
import kr.or.ddit.vo.recruitment.InterviewQuestionScoreVO;
import lombok.Data;

@Data
public class InterviewQuestionScoreListDTO {
	private String applicantId;
    @Valid
    private List<InterviewQuestionScoreVO> interviewQuestionScoreList;
}