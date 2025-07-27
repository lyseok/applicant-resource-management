package kr.or.ddit.admin.common.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.admin.common.payment.service.AdminSubscriptionService;

@Controller
@RequestMapping("/admin/subscription")
public class AdminSubscriptionController {
	 @Autowired
	    private AdminSubscriptionService service;

	    @PostMapping("/reset")
	    @ResponseBody
	    public ResponseEntity<?> resetSubscriptions() {
	        service.resetSubscriptions();
	        return ResponseEntity.ok("초기화 완료");
	    }
}
