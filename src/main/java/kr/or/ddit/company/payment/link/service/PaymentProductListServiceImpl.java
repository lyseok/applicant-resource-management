package kr.or.ddit.company.payment.link.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.common.PaymentProductLinkMapper;
import kr.or.ddit.vo.common.PaymentProductLinkVO;
@Service
public class PaymentProductListServiceImpl implements PaymentProductLinkService {
	
	@Autowired
	PaymentProductLinkMapper mapper;
	
	@Override
	public List<PaymentProductLinkVO> selectLinkList() {
		// TODO Auto-generated method stub
		return mapper.selectLinkList();
	}

	@Override
	public PaymentProductLinkVO selectLinkById(String paymentProductLinkId) {
		// TODO Auto-generated method stub
		return mapper.selectLinkById(paymentProductLinkId);
	}

	@Override
	public int insertLink(PaymentProductLinkVO vo) {
		// TODO Auto-generated method stub
		return mapper.insertLink(vo);
	}

	@Override
	public int updateLink(PaymentProductLinkVO vo) {
		// TODO Auto-generated method stub
		return mapper.updateLink(vo);
	}

	@Override
	public int deleteLink(String paymentProductLinkId) {
		// TODO Auto-generated method stub
		return mapper.deleteLink(paymentProductLinkId);
	}

	@Override
	public int minusRemaining(String paymentNo, String productNo) {
		// TODO Auto-generated method stub
		return mapper.minusRemaining(paymentNo, productNo);
	}

	@Override
	public PaymentProductLinkVO selectToPaymentNo(String paymentNo) {
		// TODO Auto-generated method stub
		return mapper.selectToPaymentNo(paymentNo);
	}

}
