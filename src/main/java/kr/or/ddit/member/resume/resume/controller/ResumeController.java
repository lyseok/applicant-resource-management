package kr.or.ddit.member.resume.resume.controller;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RequestParam;
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
	public String getResumeList(
		Model model
		, @RequestParam(defaultValue = "1") int page
	) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();
		int pageSize = 5;
	    int offset = (page - 1) * pageSize;

	    int totalCount = service.getResumeTotalCount(userId);
	    int totalPages = (int) Math.ceil((double) totalCount / pageSize);

	    List<Map<String, Object>> resumeList = service.getResumePagingList(userId, offset, pageSize);

		// List<Map<String, Object>> resumeList = service.readResumeList(userId); 기존 이력서 목록
		log.info("{}", resumeList);
		model.addAttribute(MODELNAME, resumeList);
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", totalPages);
	    model.addAttribute("totalCount", totalCount);

		return "member/resume/mypage/resume/resumeList";
	}

	// 상세조회
	@GetMapping("{no}")
	public String getResumeDetail(Model model, @PathVariable String no) throws JsonProcessingException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();

		ResumeVO vo = new ResumeVO();
		vo.setUserId(userId);
		vo.setResumeNo(no);
		ResumeVO resume = service.readResumeDetail(vo);
		log.info("{}", resume);
		
		// 이력서 안에 자소서 없을경우 ( 구조 변경 전 자소서 선택되어있을 경우)
		IntroductionVO intro = resume.getIntroduction();
		if (intro != null) {
			String questionList = new ObjectMapper().writeValueAsString(intro.getIntroductionQuestionList());
			model.addAttribute("questionList", questionList);
		} else {
		    model.addAttribute("questionJson", "[]"); // 빈 리스트 넘김
		}

		model.addAttribute(MODELNAME, resume);

		return "member/resume/mypage/resume/resumeDetail";
	}
	
	// 상세조회
	@GetMapping("{no}/{userId}")
	public String getResumeDetailByApplicant(Model model, @PathVariable String no, @PathVariable String userId) {
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
		log.info("✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅ 이력서 번호!!!!!!!!!!!!!!!! = {}", vo.getResumeNo());
		if (!bindingResult.hasErrors()) {
			// pk가 있을경우 업데이트 처리
			if(vo.getResumeNo() != null) {
				int result = service.editResume(vo, photo);
				if(result > 0) { 
					return ResponseEntity.ok("ok");
				} else {
					return ResponseEntity
						.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("이력서 수정 중 오류가 발생했습니다.");
				}
			} else {			
				int result = service.createResumeWithPhoto(vo, photo);
	            return ResponseEntity.ok("ok");

			}
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
	public String deleteResume(
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

	  // 수정 폼 이둉	  
	  @GetMapping("edit/{no}")
	  public String editResume(
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
	    model.addAttribute("resumeVO", resumeVO);
		return "member/resume/mypage/resume/resumeForm";
	  }
	  

		
		// 검색 로직 수행
		@GetMapping("search")
		public String getintroductionSearch(
			Model model
			, @RequestParam String keyword
		) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String userId = authentication.getName();
			
			ResumeVO vo = new ResumeVO();
			vo.setUserId(userId);
			vo.setResumeName(keyword);
			
			model.addAttribute(MODELNAME, service.readResumeSearch(vo));
			return "member/resume/mypage/resume/resumeList";
		}
	 

}
