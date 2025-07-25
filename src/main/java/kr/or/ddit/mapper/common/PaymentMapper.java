
package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

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
	
}
