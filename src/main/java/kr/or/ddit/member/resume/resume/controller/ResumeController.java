package kr.or.ddit.member.resume.resume.controller;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.member.resume.resume.service.ResumeService;
import kr.or.ddit.vo.resume.ResumeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/mypage/resume")
@RequiredArgsConstructor
public class ResumeController {
	private final ResumeService service;
	
	static final String MODELNAME = "resumeList";

	@ModelAttribute(MODELNAME)
	public ResumeVO setupResumeVO() {
		ResumeVO vo = new ResumeVO();
		return vo;
	}

	// 개인 리스트 조회
	@GetMapping("list")
	public String getResumeList(
		Model model
	){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();
		
		List<ResumeVO> resumeList = service.readResumeList(userId);
		log.info("{}", resumeList);
		model.addAttribute(MODELNAME, resumeList);
		return "member/resume/mypage/resume/resumeList";
	}
	
	
	// 상세조회
	@GetMapping("{no}")
	public String getResumeDetail(
		Model model
		, @PathVariable String no
	){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();
		
		ResumeVO vo = new ResumeVO();
		vo.setUserId(userId);
		vo.setResumeNo(no);
		ResumeVO resume = service.readResumeDetail(vo);
		log.info("{}", resume);
		model.addAttribute(MODELNAME, resume);
		return "member/resume/mypage/resume/resumeDetail";
	}
	
	// 등록 폼 이동
	@GetMapping("create")
	public String getCreateResumeForm() {		
		return "member/resume/mypage/resume/resumeForm";
	}
	
	// 등록 로직 구현
	@PostMapping("create")
	public String createResume() {
		return "";
	}
	

	// 삭제 로직 구현
	@GetMapping("delete/{no}")
	public String createResume(
		@PathVariable String no
	) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName(); // 아이디
		ResumeVO vo = new ResumeVO();
		vo.setUserId(username);
		vo.setResumeNo(no);
		service.removeResume(no); // >> 논리적 삭제작업 해줘야함, 매퍼, 서비스 작업 아직 안해줬음
	    
		return "";
	}
	
	
	/*
	// 수정 로직 구현
	@PostMapping("create")
	public String createResume() {
		return "";
	}
	*/
	
	
	// 어센티케이션을 받아서 로그인한 사용자인지 체크, 익명 유저인 경우 로그인페이지로 이동 / 회원인경우 userId 리턴
	public String getUserId() {
		Authentication  authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();	// 현재 로그인된 사용자의 id값 가져오기
		return userId;
	}
	
	
}
