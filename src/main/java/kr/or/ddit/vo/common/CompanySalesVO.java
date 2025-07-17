package kr.or.ddit.vo.common;

import lombok.Data;

@Data
public class CompanySalesVO {
	private Integer comSalesId;
	private String userId;
	private String comSalesYear;
	private Integer comSalesAmount;
	private String createDate;
}
