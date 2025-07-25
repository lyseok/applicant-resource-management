package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.FilesVO;
import kr.or.ddit.vo.common.PaymentLogVO;

@Mapper
public interface PaymentLogMapper {
	public List<PaymentLogVO> selectLog();

	public PaymentLogVO selectLogByPk(String logId);
	
	public int insertLog(PaymentLogVO vo);
	
	public int updateLog(PaymentLogVO vo);
	
	public int deleteLog(String logId);
	
	public List<PaymentLogVO> selectLoghistory(String paymentNo);
	
	public List<PaymentLogVO> filterLogHistory(String key, String keyword);
}
