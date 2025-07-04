package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.RefundVO;
@Mapper
public interface RefundMapper {
	public List<RefundVO> selectRefundList();

	public RefundVO selectRefundByPk(RefundVO vo);

	public int insertRefund(RefundVO vo);

	public int updateRefund(RefundVO vo);

	public int deleteRefund(RefundVO vo);
}
