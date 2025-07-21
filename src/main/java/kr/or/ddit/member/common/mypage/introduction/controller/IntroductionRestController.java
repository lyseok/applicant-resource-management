package kr.or.ddit.member.common.mypage.introduction.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.member.common.mypage.introduction.service.introductionService;
import kr.or.ddit.vo.resume.IntroductionVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ajax/introduction")
public class IntroductionRestController {
	private final introductionService service;

	// 자소서 리스트 가져오기
	@GetMapping("/list")
	public List<IntroductionVO> getintroduction() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();	// 현재 로그인된 사용자의 id값 가져오기		
		List<IntroductionVO> introductionList = service.readIntroductionList(userId);
		log.info("{}", introductionList);
		return introductionList;
	}
	
	// 자소서 상세페이지 이동
	@GetMapping("{no}")
	public String getintroductionDetailForm(
		@PathVariable String no 
		, Model model
	) {
		IntroductionVO introdDetail = service.readIntroductionDetail(no);
		model.addAttribute("introdDetail", introdDetail);
		return "member/resume/mypage/introduction/introductionDetail";
	}
}
