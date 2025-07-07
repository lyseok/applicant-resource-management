package kr.or.ddit.member.common.mypage.introduction.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.member.common.mypage.introduction.service.introductionService;
import kr.or.ddit.vo.resume.IntroductionVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/mypage/intoruction/test")
@RequiredArgsConstructor
public class introductionController {
	private final introductionService service; 
	
	// 자소서 리스트 가져오기
	@GetMapping("list")
	public String getIntoruction(
		@AuthenticationPrincipal UserDetails userDetails 
		, Model model
	) {
		String userId = userDetails.getUsername();	// 현재 로그인된 사용자의 id값 가져오기
		List<IntroductionVO> introductionList = service.readIntroductionList(userId);
		model.addAttribute("introductionList", introductionList);
		return "member/resume/mypage/intoruction/intoructionList";
	}
	
	// 자소서 상세페이지 이동
	@GetMapping("{no}")
	public String getIntoructionDetailForm(
		@PathVariable String no 
		, Model model
	) {
		IntroductionVO introdDetail = service.readIntroductionDetail(no);
		model.addAttribute("introdDetail", introdDetail);
		return "member/resume/mypage/intoruction/intoructionDetaile";
	}
	

	// 자소서 등록페이지 이동
	@GetMapping("/create")
	public String getIntoructionCreateForm(
		Model model
	) {
		model.addAttribute("introdCreate", true);
		return "member/common/mypage/intoruction/intoructionForm";
	}
	
	// 자소서 등록 로직 구현 controller
	@ResponseBody
	@PostMapping("")
	public String createIntrodcution(
		@RequestBody Map<String, String> formDataMap 
	) {
        List<IntroductionVO> itrdList = new ArrayList();
        
		return "";
	}
}

