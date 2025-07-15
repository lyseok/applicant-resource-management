package kr.or.ddit.company.payment.paymentlist.service;

import java.util.List;

import kr.or.ddit.vo.common.PaymentListVO;
import kr.or.ddit.vo.common.PaymentVO;

public interface PaymentListService {

	public List<PaymentVO> selectPaymentList();

	public PaymentVO selectPaymentByPk(PaymentListVO vo);

	public int insertPayment(PaymentListVO vo);

	public int updatePayment(PaymentListVO vo);

	public int deletePayment(PaymentListVO vo);
	
	public List<PaymentVO> selectPaymentCompany(String userId);
	
}
