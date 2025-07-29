package kr.or.ddit.vo.common;

import java.io.Serializable;

import lombok.Data;

@Data
public class CompanyOpProfitVO implements Serializable{
	
	private String comProfitId;
	private String userId;
	private String comProfitYear;
	private String comOperatingProfit;
	private String createDate;
	
}
