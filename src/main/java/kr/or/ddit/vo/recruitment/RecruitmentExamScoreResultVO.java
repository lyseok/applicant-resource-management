package kr.or.ddit.vo.recruitment;

import lombok.Data;

@Data
public class RecruitmentExamScoreResultVO {
	private String applicantId;
	private String applicantName;
	private String recruitExamName;
	private int examTotalScore;

}
