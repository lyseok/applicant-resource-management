package kr.or.ddit.company.common.calendar.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.company.common.calendar.service.CompanyScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/ajax/company/schedule")
public class CompanyScheduleAjaxController {

	private final CompanyScheduleService service;
	
	@GetMapping("/recruit")
    public List<Map<String, Object>> getCompanySchedule() {
        return service.getCompanySchedule();
    }
}
