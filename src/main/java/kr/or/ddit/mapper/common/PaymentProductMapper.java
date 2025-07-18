package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.PaymentProductVO;
@Mapper
public interface PaymentProductMapper {
	public List<PaymentProductVO> selectPaymentProductList();

	public PaymentProductVO selectPaymentProductByPk(String productNo);

	public int insertPaymentProduct(PaymentProductVO vo);

	public int updatePaymentProduct(PaymentProductVO vo);

	public int deletePaymentProduct(String productNo);
	
	public PaymentProductVO selectPaymentProductByName(String productName);
}
