package kr.or.ddit.vo.common;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "productNo")
public class PaymentProductVO implements Serializable{
	private String productNo;
	private String productName;
	private String productDetail;
	private String productPrice;
	private String productImg;
	private String productPeriod;
}
