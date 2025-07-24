package kr.or.ddit.company.payment.log.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.vo.common.PaymentLogVO;

public interface PaymentLogService {
	
	public List<PaymentLogVO> selectLog();

	public PaymentLogVO selectLogByPk(String logId);
	
	public int insertLog(PaymentLogVO vo);
	
	public int updateLog(PaymentLogVO vo);
	
	public int deleteLog(String logId);
}
