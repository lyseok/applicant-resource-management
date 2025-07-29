package kr.or.ddit.vo.common;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangeProductResponseVO {
	   private PaymentVO payment;  // 현재 결제 정보 + 상품 리스트
	    private List<PaymentProductVO> changeableProducts;  // 변경 가능 상품
}
