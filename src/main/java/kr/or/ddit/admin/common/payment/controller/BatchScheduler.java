package kr.or.ddit.admin.common.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.or.ddit.admin.common.payment.service.AdminSubscriptionService;

@Component
public class BatchScheduler {
	@Autowired
    private AdminSubscriptionService service;

    // 매월 1일 0시 실행
    @Scheduled(cron = "0 0 0 1 * *")
    public void monthlyReset() {
        service.resetSubscriptions();
    }
}
