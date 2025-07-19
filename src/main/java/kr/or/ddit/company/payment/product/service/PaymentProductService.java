package kr.or.ddit.company.payment.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.vo.common.PaymentProductVO;

public interface PaymentProductService {
	public List<PaymentProductVO> selectPaymentProductList();

	public PaymentProductVO selectPaymentProductByPk(String productNo);

	public int insertPaymentProduct(PaymentProductVO vo);

	public int updatePaymentProduct(PaymentProductVO vo);

	public int deletePaymentProduct(String productNo);
	
	public PaymentProductVO selectPaymentProductByName(String productName);

}
