package kr.or.ddit.vo.common;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "paymentNo")
public class PaymentVO implements Serializable{
	private String paymentNo;
	private String userId;
	private String productNo;
	private String paymentMethod;
	private String paymentDate;
	private String paymentPay;
	private String paymentContract;
	
	private List<PaymentProductVO> paymentProductList;
	private List<CompanyVO> CompanyList;

}
