package kr.or.ddit.company.payment.payment.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.common.AdminPaymentVO;
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
	
	public PaymentVO selectScheduledByUserId(String userId);
	
	public List<AdminPaymentVO> selectPaymentStatistics();
	
	public PaymentVO cancleSub(String userId);
	
	public List<String> allcompanyName();
	
	public List<String> selectNewSubscribers();			// 2달이내 신규 구독자
	
	public List<String> selectChurnedSubscribers();		// 이번달 해지한사람

	public List<String> selectLeftSubscribers();
	
	public List<Integer> selectMonthlySalesCompare(int year);

	List<Integer> selectMonthlySales(@Param("year") int year);
	
}
