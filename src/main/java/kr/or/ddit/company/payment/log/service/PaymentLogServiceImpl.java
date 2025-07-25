package kr.or.ddit.company.payment.log.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

	@Override
	public List<PaymentLogVO> selectLoghistory(String paymentNo) {
		// TODO Auto-generated method stub
		return mapper.selectLoghistory(paymentNo);
	}

	@Override
	public List<PaymentLogVO> filterLogHistory(String key, String keyword) {
		if(!List.of("all","emailAddress","subject","messageBody").contains(key)) {
			throw new IllegalArgumentException("잘못된 검색조건 : " + key);
		}
		if(keyword == null || keyword.isBlank() || "all".equals(key)) {
			return mapper.selectLoghistory(getUserId());
		}
		
		return mapper.filterLogHistory(key, "%" + keyword + "%");
	}
	
	public String getUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();		// 기업 ID 
		}

}
