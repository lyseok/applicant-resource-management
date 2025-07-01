package kr.or.ddit.vo.common;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "paymentReceipt")
public class PaymentListVO implements Serializable{
	private String paymentReceipt;
	private String paymentNo;
	private String paymentStartDay;
	private String paymentAmount;
	private String paymentStatus;
	private String paymentApprovalnum;
}
