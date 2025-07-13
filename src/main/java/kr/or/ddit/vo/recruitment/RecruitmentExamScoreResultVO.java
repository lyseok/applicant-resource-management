package kr.or.ddit.vo.recruitment;

import lombok.Data;

@Data
public class RecruitmentExamScoreResultVO {
	private String applicantId;
	private int examTotalScore;
	private int examcutlineScore; 
	private boolean examPass;
	private String passYn;
}
