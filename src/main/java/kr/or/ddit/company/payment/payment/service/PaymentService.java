package kr.or.ddit.company.payment.payment.service;

import java.util.List;

import kr.or.ddit.vo.common.PaymentVO;

public interface PaymentService {
	public List<PaymentVO> selectPaymentList();

	public PaymentVO selectPaymentByPk(String userId);

	public int insertPayment(PaymentVO vo);

	public int updatePayment(PaymentVO vo);

	public int deletePayment(String paymentNo);
	
	public List<PaymentVO> selectMyPaymentList(String userId);
	
	public String checkbilling(String userId);
}
