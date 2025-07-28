package kr.or.ddit.dto;

import lombok.Data;

@Data
public class CompanySalesDTO {
	
	private String comSalesYear;           // 기준 연도
	private String comSalesAmount;           // 매출액
	private Double growthRatePercent;      // 작년 대비 증가율 (%)
	private Double avgSalesAmount;         // 평균 매출액
	
}
