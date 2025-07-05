package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.PaymentListVO;
import kr.or.ddit.vo.common.PaymentVO;
@Mapper
public interface PaymentListMapper {
	public List<PaymentVO> selectPaymentList();

	public PaymentVO selectPaymentByPk(PaymentListVO vo);

	public int insertPayment(PaymentListVO plv);

	public int updatePayment(PaymentListVO vo);

	public int deletePayment(PaymentListVO vo);
}
