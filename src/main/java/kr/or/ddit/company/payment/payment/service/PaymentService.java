package kr.or.ddit.company.payment.payment.service;

import java.util.List;

import kr.or.ddit.vo.common.PaymentVO;

public interface PaymentService {
	public List<PaymentVO> selectPaymentList();

	public PaymentVO selectPaymentByPk(String paymentNo);

	public int insertPayment(PaymentVO vo);

	public int updatePayment(PaymentVO vo);

	public int deletePayment(String paymentNo);
	
	public List<PaymentVO> selectMyPaymentList(String userId);
	
	public String checkbilling(String userId);
	
	public int cancelPayment(String oldPaymentNo);
	
	public int comePayment(PaymentVO vo);
	
	public int checkPayment(String userId);
	
	public int updateComPaymentStatus(String userId);
	
	public String getPaymentNo(String userId);
	
	public PaymentVO selectStauts(String userId);
	
	public int minuseaining(String paymentNo);
	
	public String newPaymentNo(String userId);
	
	public void monthlySubscriptionRefresh();
	
	
}
