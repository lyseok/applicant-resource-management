package kr.or.ddit.company.payment.link.service;

import java.util.List;

import kr.or.ddit.vo.common.PaymentProductLinkVO;

public interface PaymentProductLinkService {

public List<PaymentProductLinkVO> selectLinkList();
	
	public PaymentProductLinkVO selectLinkById(String paymentProductLinkId);

	public int insertLink(PaymentProductLinkVO vo);

	public int updateLink(PaymentProductLinkVO vo);

	public int deleteLink(String paymentProductLinkId);
	
	public int minusRemaining(String paymentNo, String productNo);

	public PaymentProductLinkVO selectToPaymentNo(String paymentNo);
}
