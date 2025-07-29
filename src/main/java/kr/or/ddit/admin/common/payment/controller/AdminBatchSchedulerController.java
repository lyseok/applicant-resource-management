package kr.or.ddit.admin.common.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.or.ddit.admin.common.payment.service.AdminSubscriptionService;

@Component
public class AdminBatchSchedulerController {
	@Autowired
    private AdminSubscriptionService service;

    // 매월 1일 0시 실행
    @Scheduled(cron = "0 0 0 1 * *")
    public void monthlyReset() {
    	// 초기화 범위설정 (* 해당 WAS에서만 돌 수 있게)
    	// application.properties :
    	// batch.service.was = ndel
        service.resetSubscriptions();
    }
    
    
}
