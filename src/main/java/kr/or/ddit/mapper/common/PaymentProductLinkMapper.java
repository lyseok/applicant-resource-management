package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.PaymentProductLinkVO;

@Mapper
public interface PaymentProductLinkMapper {
	
	public List<PaymentProductLinkVO> selectLinkList();
	
	public PaymentProductLinkVO selectLinkById(String paymentProductLinkId);

	public int insertLink(PaymentProductLinkVO vo);

	public int updateLink(PaymentProductLinkVO vo);

	public int deleteLink(String paymentProductLinkId);
	
	public int minusRemaining(String paymentNo, String productNo);

	public PaymentProductLinkVO selectToPaymentNo(String paymentNo);
}
