package kr.or.ddit.vo.common;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="orderId")
public class TossPaymentVO implements Serializable{

	private String paymentKey;			// 결제의 키값, 승인, 조회, 취소에 이용
	private String type;				// 단건/ 자동결제
	private String orderId;				// 주문번호
	private String orderName;			// 상품명
	private String currency;			// 결제시 사용된 통화
	private String method;				// 결제수단
	private String totalAmount;			// 총 결제금액
	private Integer amount;				// 결제금액
	private String status;				// 상태
	private String requestedAt;			// 결제가 일어난 시간정보
	private String customerName;
	private String successUrl;
	private String failUrl;
	
	//================================== 자동결제
	private String mId; 				// 상점 Id
	private String customerKey;			// 구매자 ID
	private String authenticatedAt;		// 결제수단이 인증된 시간정보
	private String billingKey;			// 자동결제에서 카드정보 대신 사용되는값, customerKey 와 연결		
	private String card;				// 카드정보
	private String cardissuerCode;		// 카드 발급사 코드
	private String cardCompany;			// 카드 발급사
	private String cardNumber ;			// 카드번호
	
	
}
