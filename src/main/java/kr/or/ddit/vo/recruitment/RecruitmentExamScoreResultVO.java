package kr.or.ddit.vo.recruitment;

import lombok.Data;

@Data
public class RecruitmentExamScoreResultVO {
	private int examTotalScore;
	private int examcutlineScore; 
	private boolean examPass;
}
