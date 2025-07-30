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
	private String paymentBillingKey; // 정기구독 빌링키
	private String startDate;
	private String endDate;
	private String status;
	private int usageAllowed;		// 사용가능 횟수
	private int usageRemaining;		// 남은횟수
	private String paymentKey;
	private String paymentOrderId;	// 결제시 오더 아이디
	private int daysRemaining;		// 남은기한
	private String nextMonth;
	
	private List<PaymentProductVO> paymentProductList;
	private List<CompanyVO> CompanyList;
	private List<PaymentListVO> paymentList;

}
