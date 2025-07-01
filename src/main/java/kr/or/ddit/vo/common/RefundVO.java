package kr.or.ddit.vo.common;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "refundNo")
public class RefundVO implements Serializable {
	private String refundNo;
	private String paymentNo;
	private String refundAccount;
	private String refundReason;
	private String refundRatio;
	private String productName;
	private String paymentPay;
}
