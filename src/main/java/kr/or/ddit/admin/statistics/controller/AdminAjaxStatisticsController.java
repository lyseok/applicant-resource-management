package kr.or.ddit.admin.statistics.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ajax/admin/statistics")
public class AdminAjaxStatisticsController {
	@GetMapping
	public ResponseEntity<?> getInsertMemberCnt(){
		return null;
	}
}
