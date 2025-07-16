package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.PaymentListVO;
import kr.or.ddit.vo.common.PaymentVO;
@Mapper
public interface PaymentMapper {
	public List<PaymentVO> selectPaymentList();

	public PaymentVO selectPaymentByPk(PaymentVO vo);

	public int insertPayment(PaymentVO vo);

	public int updatePayment(PaymentVO vo);

	public int deletePayment(String paymentNo);
	
	public PaymentListVO selectPaymentCompany(String userId);
}
