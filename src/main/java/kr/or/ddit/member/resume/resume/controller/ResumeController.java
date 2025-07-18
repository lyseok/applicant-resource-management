package kr.or.ddit.member.resume.resume.controller;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;
import kr.or.ddit.dto.ResumeSaveValidError;
import kr.or.ddit.dto.ResumeSaveValidationErrorResponse;
import kr.or.ddit.mapper.resume.ResumeMapper;
import kr.or.ddit.member.common.mypage.introduction.service.introductionService;
import kr.or.ddit.member.resume.resume.service.ResumeService;
import kr.or.ddit.vo.resume.IntroductionVO;
import kr.or.ddit.vo.resume.ResumeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/mypage/resume")
@RequiredArgsConstructor
public class ResumeController {
	private final ResumeService service;
	private final introductionService introductionService;

	static final String MODELNAME = "resumeList";

	@ModelAttribute(MODELNAME)
	public ResumeVO setupResumeVO() {
		ResumeVO vo = new ResumeVO();
		return vo;
	}

	// 개인 리스트 조회
	@GetMapping("list")
	public String getResumeList(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();

		List<ResumeVO> resumeList = service.readResumeList(userId);
		log.info("{}", resumeList);
		model.addAttribute(MODELNAME, resumeList);
		return "member/resume/mypage/resume/resumeList";
	}

	// 상세조회
	@GetMapping("{no}")
	public String getResumeDetail(Model model, @PathVariable String no) {
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
	public String getCreateResumeForm(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();
		// user가 등록한 이력서 갯수 구해오기
		int resumeCnt =service.readUserResumeNoCount(userId);
		// 자소서 썼는지 확인
		List<IntroductionVO> introdList = introductionService.readIntroductionList(userId);
		if(introdList.isEmpty()) {
			model.addAttribute("hasIntrod", "자소서 작성 이후 등록할 수 있습니다.");
		}
		
		model.addAttribute("resumeCnt", resumeCnt);
	    model.addAttribute("mode", "create"); // ✅ JS에서 mode로 사용 가능
		log.info("{}", userId);
		return "member/resume/mypage/resume/resumeForm";
	}

	// 등록 로직 구현
	@ResponseBody
	@PostMapping("create")
	public ResponseEntity<?> createResume(@Valid @RequestPart("resume") ResumeVO vo, BindingResult bindingResult,
			@RequestPart(value = "photo", required = false) MultipartFile photo,
			@RequestPart(value = "comImage", required = false) MultipartFile comImage) {
		log.info("✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅ introductionNo = {}", vo.getIntroductionNo());

		if (!bindingResult.hasErrors()) {
			service.createResume(vo);
			return ResponseEntity.ok("ok");
		} else {
			List<ResumeSaveValidError> errors = bindingResult.getFieldErrors().stream()
					.map(error -> new ResumeSaveValidError(error.getField(), error.getDefaultMessage()))
					.collect(Collectors.toList());

			ResumeSaveValidationErrorResponse<ResumeVO> response = new ResumeSaveValidationErrorResponse<>(errors, vo);

			return ResponseEntity.badRequest().body(response);

		}
	}

	// 삭제 로직 구현
	@GetMapping("delete/{no}")
	public String createResume(
		@PathVariable String no
		, RedirectAttributes redirectAttributes
	) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();
		ResumeVO vo = new ResumeVO();
		vo.setUserId(userId);
		vo.setResumeNo(no);

		try {
			service.editResumeRemove(vo);
			redirectAttributes.addFlashAttribute("message", "이력서가 성공적으로 삭제되었습니다.");
			return "redirect:/mypage/resume/list";

		} catch (IllegalArgumentException e) {
			redirectAttributes.addFlashAttribute("errors", e.getMessage());
			return "redirect:/mypage/resume/list"; // 🔥 여기 꼭 필요
		} catch (Exception e) {
			log.error("자소서 삭제 중 예상치 못한 오류 발생", e);
			redirectAttributes.addFlashAttribute("error", "이력서 삭제 중 오류가 발생했습니다.");
			return "redirect:/mypage/resume/list"; // 🔥 이것도 꼭 필요
		}
		
	}

	  // 수정 로직 구현	  
	  @GetMapping("edit/{no}")
	  public String createResume(
		@PathVariable String no
		, Model model
	  ) throws JsonProcessingException{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();
		ResumeVO vo = new ResumeVO();
		vo.setResumeNo(no);
		vo.setUserId(userId);
		ResumeVO resumeVO = service.readResumeDetail(vo);
	    String resumeJson = new ObjectMapper().writeValueAsString(resumeVO);
	    log.info("♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣ {}", resumeJson);
	    
	    model.addAttribute("mode", "update"); // ✅ JS에서 mode로 사용 가능
	    model.addAttribute("resumeJson", resumeJson); // ✅ resumeFromServer로 바인딩됨
		return "member/resume/mypage/resume/resumeForm";
	  }
	 

}
