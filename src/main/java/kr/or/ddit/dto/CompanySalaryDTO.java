package kr.or.ddit.dto;

import lombok.Data;

@Data
public class CompanySalaryDTO {
	private String userId;
	private String comName;
	private String codeDetailNo;
	private String codeName;
	private String industryType;
	private String induName;
	private String salaryMin;
	private String salaryMax;
	private String createDate;
	private String salaryAvgAll;
	private String salaryAvgExclExec; // 전체 평균(임원 제외)
    private String salaryAvgExec;     // 임원 전체 평균
	private String avgByRank;          // 직급별 평균
}
