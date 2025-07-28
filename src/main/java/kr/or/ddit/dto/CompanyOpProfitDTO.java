package kr.or.ddit.dto;

import lombok.Data;

@Data
public class CompanyOpProfitDTO {
	
	private String comProfitYear;             // 기준 연도
	private String comOperatingProfit;          // 영업이익
	private Double growthRatePercent;         // 작년 대비 증가율 (%)
	private Double avgOperatingProfit;        // 평균 영업이익
	
}
