
package kr.or.ddit.mapper.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.common.AdminPaymentVO;
import kr.or.ddit.vo.common.PaymentListVO;
import kr.or.ddit.vo.common.PaymentVO;
@Mapper
public interface PaymentMapper {
	public List<PaymentVO> selectPaymentList();

	public PaymentVO selectPaymentByPk(String paymentNo);

	public int insertPayment(PaymentVO vo);

	public int updatePayment(PaymentVO vo);

	public int deletePayment(String paymentNo);
	
	public PaymentListVO selectPaymentCompany(String userId);
	
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
	
	public PaymentVO expireCancelledSubscriptions();

	public List<PaymentVO> selectScheduledSubscriptions();
	
	public int activateSubscription(int usageRemaining, String paymentNo);
	
	public PaymentVO selectScheduledByUserId(String userId);
	
	public List<AdminPaymentVO> selectPaymentStatistics();
	
	public PaymentVO cancleSub(String userId);
	
	public List<String> allcompanyName();				// 전체 기업회원수

	public List<String> selectNewSubscribers();			// 2달이내 신규 구독자
	
	public List<String> selectChurnedSubscribers();		// 이번달 해지한사람
	
	public List<String> selectLeftSubscribers();		// 해지한 사람
	
	List<Integer> selectMonthlySales(@Param("year") int year);
	
	public Integer selectMonthlySales(Map<String, String> of);
	
	
	
}
