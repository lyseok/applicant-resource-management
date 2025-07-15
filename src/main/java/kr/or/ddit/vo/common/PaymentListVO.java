package kr.or.ddit.vo.common;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "paymentReceipt")
public class PaymentListVO implements Serializable{
	private String paymentReceipt;			// 구독내용 번호
	private String paymentNo;				// 결제 정보 코드
	private String paymentStartDay;			// 결제 시작일
	private String paymentAmount;			// 누적 사용금액
	private String paymentStatus;			// 유지상태
	private String paymentApprovalnum;		// 승인번호 
	
	private List<PaymentListVO> paymentList;
	private List<PaymentVO> payment;
}
