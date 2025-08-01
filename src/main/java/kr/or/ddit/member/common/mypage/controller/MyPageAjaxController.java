package kr.or.ddit.member.common.mypage.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.member.common.mypage.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/ajax/member/mypage")
public class MyPageAjaxController {
	
	private final MyPageService service;
	
	@GetMapping("/info")
	public Map<String, Object> readMyPageInfo(){
		return service.readMyPageInfo();
	}
	
	@GetMapping("/prj")
	public Map<String, Object> readMyPrjData(){
		return service.selectPrjectData();
	}

}
