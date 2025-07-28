package kr.or.ddit.admin.common.payment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.mapper.common.PaymentMapper;
import kr.or.ddit.vo.common.PaymentVO;
@Service
public class AdminSubscriptionService {
	 @Autowired
	    private PaymentMapper mapper;

	    @Transactional
	    public void resetSubscriptions() {
	        List<PaymentVO> scheduled = mapper.selectScheduledSubscriptions();
	        for (PaymentVO sub : scheduled) {
	            int allowed = sub.getUsageAllowed();
	            int remaining = (allowed == -1) ? -1 : allowed;  
	            mapper.activateSubscription(remaining, sub.getPaymentNo());
	        }
	    }
}
