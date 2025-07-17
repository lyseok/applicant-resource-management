package kr.or.ddit.vo.common;

import java.io.Serializable;

import lombok.Data;

@Data
public class CompanyOpProfitVO implements Serializable{
	private Integer comProfitId;
	private String userId;
	private String comProfitYear;
	private Integer comOperatingProfit;
	private String createDate;
}
