package kr.or.ddit.member.common.mypage.intoruction.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.member.common.mypage.intoruction.service.introductionService;
import kr.or.ddit.vo.resume.IntroductionVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/mypage/intoruction/")
@RequiredArgsConstructor
public class intoructionController {
	private final introductionService service; 
	
	// 자소서 리스트 가져오기
	@GetMapping("/list")
	public String getIntoruction(
		// @AuthenticationPrincipal String userId
		Model model
	) {
		String userId = "USR001";
		List<IntroductionVO> introductionList = service.readIntroductionList(userId);
		model.addAttribute("introductionList", introductionList);
		return "common/mypage/intoruction/intoructionList";
	}
}

