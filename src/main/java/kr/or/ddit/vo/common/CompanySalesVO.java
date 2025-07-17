package kr.or.ddit.vo.common;

import java.io.Serializable;

import lombok.Data;

@Data
public class CompanySalesVO implements Serializable{
	private Integer comSalesId;
	private String userId;
	private String comSalesYear;
	private Integer comSalesAmount;
	private String createDate;
}
