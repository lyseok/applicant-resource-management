package kr.or.ddit.vo.common;

import lombok.Data;

@Data
public class CompanyOpProfitVO {
	private Integer comProfitId;
	private String userId;
	private String comProfitYear;
	private Integer comOperatingProfit;
	private String createDate;
}
