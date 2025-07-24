
package kr.or.ddit.company.payment.log.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.company.payment.log.service.PaymentLogService;
import kr.or.ddit.company.payment.payment.service.PaymentServiceImpl;
import kr.or.ddit.vo.common.PaymentLogVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/company/log")
public class PaymentLogController {
	
	@Autowired
	private PaymentServiceImpl pservice;
	
	@Autowired
	PaymentLogService service;

	@GetMapping("/list")
	public String listFormUI(
		Model model
		 ,@RequestParam(defaultValue ="1") int page
			) {
		String PaymentNo = pservice.getPaymentNo(pservice.getUserId());
		
		List<PaymentLogVO> history = service.selectLoghistory(PaymentNo);
		
		int totalItems = history.size();
		int itemsPerPage = 10;
		int totalPages = (int)Math.ceil((double) totalItems/itemsPerPage);
		
		int fromIndex = (page -1) * itemsPerPage;
		int toIndex = Math.min(fromIndex + itemsPerPage, totalItems);
		
		List<PaymentLogVO> pageList = history.subList(fromIndex, toIndex);
		
		log.info("history 값 : {}",history);
		model.addAttribute("history",history);
		model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", totalPages);
		return "company/payment/log/PaymentLog";
	}
}
