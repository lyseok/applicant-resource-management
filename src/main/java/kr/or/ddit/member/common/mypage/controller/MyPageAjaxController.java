package kr.or.ddit.member.common.mypage.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.member.common.mypage.service.MyPageService;
import kr.or.ddit.vo.common.MemberVO;
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
	
	@PostMapping("/{resumeNo}")
	public ResponseEntity<?> updateMainResume(@PathVariable String resumeNo){
		service.updateMainResume(resumeNo);
		
		
		return ResponseEntity.ok("ok");
	}
	
	@PostMapping
	public ResponseEntity<?> updateProfile(
	        @ModelAttribute MemberVO memberVO, // 나머지 필드 자동 바인딩
	        @RequestPart(value = "memberImage", required = false) MultipartFile memberImage
	) {
		service.updateMember(memberVO, memberImage);
	    return ResponseEntity.ok(Map.of("result", "success"));
	}

}
