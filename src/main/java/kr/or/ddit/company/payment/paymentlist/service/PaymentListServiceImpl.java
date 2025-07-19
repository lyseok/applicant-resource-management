package kr.or.ddit.company.payment.paymentlist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.common.PaymentListMapper;
import kr.or.ddit.vo.common.PaymentListVO;
import kr.or.ddit.vo.common.PaymentVO;

@Service
public class PaymentListServiceImpl implements PaymentListService {

	@Autowired
	PaymentListMapper mapper;
	
	@Override
	public List<PaymentVO> selectPaymentList() {
		// TODO Auto-generated method stub
		return mapper.selectPaymentList();
	}

	@Override
	public PaymentVO selectPaymentByPk(PaymentListVO vo) {
		// TODO Auto-generated method stub
		return mapper.selectPaymentByPk(vo);
	}

	@Override
	public int insertPayment(PaymentListVO vo) {
		// TODO Auto-generated method stub
		return mapper.insertPayment(vo);
	}

	@Override
	public int updatePayment(PaymentListVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deletePayment(PaymentListVO vo) {
		// TODO Auto-generated method stub
		return mapper.deletePayment(vo);
	}

	@Override
	public List<PaymentVO> selectPaymentCompany(String userId) {
		// TODO Auto-generated method stub
		return mapper.selectPaymentCompany(userId);
	}

	

}
