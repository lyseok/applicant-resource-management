package kr.or.ddit.vo.common;

import java.io.Serializable;

import lombok.Data;

@Data
public class CompanySalesVO implements Serializable{
	private String comSalesId;
	private String userId;
	private String comSalesYear;
	private long comSalesAmount;
	private String createDate;
}
