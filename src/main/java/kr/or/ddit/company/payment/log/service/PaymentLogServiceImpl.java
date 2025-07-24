package kr.or.ddit.company.payment.log.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.common.PaymentLogMapper;
import kr.or.ddit.vo.common.PaymentLogVO;
@Service
public class PaymentLogServiceImpl implements PaymentLogService {

	@Autowired
	PaymentLogMapper mapper;
	
	@Override
	public List<PaymentLogVO> selectLog() {
		// TODO Auto-generated method stub
		return mapper.selectLog();
	}

	@Override
	public PaymentLogVO selectLogByPk(String logId) {
		// TODO Auto-generated method stub
		return mapper.selectLogByPk(logId);
	}

	@Override
	public int insertLog(PaymentLogVO vo) {
		// TODO Auto-generated method stub
		return mapper.insertLog(vo);
	}

	@Override
	public int updateLog(PaymentLogVO vo) {
		// TODO Auto-generated method stub
		return mapper.updateLog(vo);
	}

	@Override
	public int deleteLog(String logId) {
		// TODO Auto-generated method stub
		return mapper.deleteLog(logId);
	}

}
