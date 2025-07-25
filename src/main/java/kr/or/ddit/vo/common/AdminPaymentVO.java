package kr.or.ddit.vo.common;

import lombok.Data;

@Data
public class AdminPaymentVO {
	private String productName;
	private String productType;
	private long monthlySalesAmount; // 당월 매출액
	private long totalSalesAmount; // 총 매출액
	private long monthlySalesCount; // 당월 판매 수량
	private long totalSalesCount; // 누적 판매 수량
}
