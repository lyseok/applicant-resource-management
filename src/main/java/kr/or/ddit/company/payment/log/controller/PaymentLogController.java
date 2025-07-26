
package kr.or.ddit.company.payment.log.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@GetMapping("/selectlog")
	@ResponseBody
	public List<PaymentLogVO> selectFormUI(
		@RequestParam String key
		, @RequestParam String keyword
			) {
		log.info("keyword: {}",keyword);
		log.info("key: {}",key);
		log.info("리턴값 : {}", service.filterLogHistory(key, keyword)); 
		return service.filterLogHistory(key, keyword);
	}
	

	@GetMapping("/list")
	public String listFormUI(
		Model model
		 ,@RequestParam(defaultValue ="1") int page
			) {
		
		List<PaymentLogVO> history = service.selectLoghistoryByUserId(pservice.getUserId());
		
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
