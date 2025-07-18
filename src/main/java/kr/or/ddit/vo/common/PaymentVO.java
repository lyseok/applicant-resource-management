package kr.or.ddit.vo.common;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "paymentNo")
public class PaymentVO implements Serializable{
	private String paymentNo;		// 결제정보 번호	기본키 박고
	private String userId;			// 기업 ID	oK = getUserName
	private String productNo;		// 상품번호	order
	private String paymentMethod;	// 결제수단 (카드통일 , 계좌이체 => 세금계산서 발행)
	private String paymentDate;		// 결제일시	sysdate
	private String paymentPay;		// 결제 금액 amount
	private String tossPaymentKey;	// 결제 키값 (조회 및 취소) 
	private String paymentBillingKey; // 정기구독 빌링키
	
	private List<PaymentProductVO> paymentProductList;
	private List<CompanyVO> CompanyList;
	private List<PaymentListVO> paymentList;

}
