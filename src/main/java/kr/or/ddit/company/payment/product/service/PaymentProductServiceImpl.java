package kr.or.ddit.company.payment.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.common.PaymentProductMapper;
import kr.or.ddit.vo.common.PaymentProductVO;

@Service
public class PaymentProductServiceImpl implements PaymentProductService {

	@Autowired
	PaymentProductMapper mapper;
	
	@Override
	public List<PaymentProductVO> selectPaymentProductList() {
		// TODO Auto-generated method stub
		return mapper.selectPaymentProductList();
	}

	@Override
	public PaymentProductVO selectPaymentProductByPk(String productNo) {
		// TODO Auto-generated method stub
		return mapper.selectPaymentProductByPk(productNo);
	}

	@Override
	public int insertPaymentProduct(PaymentProductVO vo) {
		// TODO Auto-generated method stub
		return mapper.insertPaymentProduct(vo);
	}

	@Override
	public int updatePaymentProduct(PaymentProductVO vo) {
		// TODO Auto-generated method stub
		return mapper.updatePaymentProduct(vo);
	}

	@Override
	public int deletePaymentProduct(String productNo) {
		// TODO Auto-generated method stub
		return mapper.deletePaymentProduct(productNo);
	}





}
