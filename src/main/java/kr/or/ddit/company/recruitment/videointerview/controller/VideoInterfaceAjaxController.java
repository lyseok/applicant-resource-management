package kr.or.ddit.company.recruitment.videointerview.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.company.recruitment.videointerview.service.VideoInterfaceService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ajax/company/videointerview")
public class VideoInterfaceAjaxController {
	private final VideoInterfaceService service;
	
	@GetMapping("/{no}")
	public String getCompanyVideoInterviewURL(@PathVariable String no) {
		return service.readCompanyURL(no);
	}
}
