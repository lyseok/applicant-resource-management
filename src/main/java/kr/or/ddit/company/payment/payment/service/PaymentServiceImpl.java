package kr.or.ddit.company.payment.payment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.common.PaymentMapper;
import kr.or.ddit.vo.common.PaymentVO;
@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PaymentMapper mapper;
	
	@Override
	public List<PaymentVO> selectPaymentList() {
		// TODO Auto-generated method stub
		return mapper.selectPaymentList();
	}

	@Override
	public PaymentVO selectPaymentByPk(String userId) {
		// TODO Auto-generated method stub
		return mapper.selectPaymentByPk(userId);
	}

	@Override
	public int insertPayment(PaymentVO vo) {
		vo.setUserId(getUserId());
		return mapper.insertPayment(vo);
	}

	@Override
	public int updatePayment(PaymentVO vo) {
		// TODO Auto-generated method stub
		return mapper.updatePayment(vo);
	}

	@Override
	public int deletePayment(String paymentNo) {
		// TODO Auto-generated method stub
		return mapper.deletePayment(paymentNo);
	}
	
	@Override
	public List<PaymentVO> selectMyPaymentList(String userId) {
		
		return mapper.selectMyPaymentList(userId);
	}

	@Override
	public String checkbilling(String userId) {
		
		return mapper.checkbilling(userId);
	}
	
	@Override
	public int cancelPayment(String oldPaymentNo) {
		// TODO Auto-generated method stub
		return mapper.cancelPayment(oldPaymentNo);
	}

	public String getUserId() {
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	return authentication.getName();		// 기업 ID 
	}

	@Override
	public int comePayment(PaymentVO vo) {
		// TODO Auto-generated method stub
		return mapper.comePayment(vo);
	}



	
}
