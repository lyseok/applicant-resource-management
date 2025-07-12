package kr.or.ddit.company.payment.refund.service;

import java.util.List;

import kr.or.ddit.vo.common.RefundVO;

public interface RefundService {

	public List<RefundVO> selectRefundList();

	public RefundVO selectRefundByPk(RefundVO vo);

	public int insertRefund(RefundVO vo);

	public int updateRefund(RefundVO vo);

	public int deleteRefund(RefundVO vo);
}
