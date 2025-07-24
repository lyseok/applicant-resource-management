package kr.or.ddit.member.common.mypage.introduction.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nimbusds.jose.proc.SecurityContext;

import jakarta.validation.Valid;
import kr.or.ddit.member.common.mypage.introduction.service.introductionService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.resume.IntroductionListVO;
import kr.or.ddit.vo.resume.IntroductionVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/mypage/introduction")
@RequiredArgsConstructor
public class introductionController {
	private final introductionService service; 
	static final String MODELNAME = "introduction";
	
	
	@ModelAttribute(MODELNAME)
	public IntroductionVO setupIntroductionVO() { // 메서드 이름도 명확하게 변경
	    // List 필드를 초기화하여 NPE 방지
		IntroductionVO vo = new IntroductionVO();
		return vo;
	}
	
	// 자소서 리스트 가져오기
	@GetMapping("/list")
	public String getintroduction(
		@AuthenticationPrincipal UserDetails userDetails 
		, @RequestParam(defaultValue = "1") int page
		, Model model
	) {
		if(userDetails == null) {
			return "redirect:/login";
		}
		String userId = userDetails.getUsername();	// 현재 로그인된 사용자의 id값 가져오기	
		
		int pageSize = 4;
	    int offset = (page - 1) * pageSize;
	    int totalCount = service.getTotalCount(userId);

	    List<IntroductionVO> introductionList = service.getIntroductionPagingList(userId, offset, pageSize);

	    int totalPages = (int) Math.ceil((double) totalCount / pageSize);

		//List<IntroductionVO> introductionList = service.readIntroductionList(userId);
		model.addAttribute("introductionList", introductionList);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalCount", totalCount);
	    model.addAttribute("totalPages", totalPages);

		return "member/resume/mypage/introduction/introductionList";
	}
	
	// 자소서 상세페이지 이동
	@GetMapping("{no}")
	public String getintroductionDetailForm(
		@PathVariable String no 
		, Model model
	) {
		IntroductionVO introdDetail = service.readIntroductionDetail(no);
		log.info("introdDetaion 확인 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<{}", introdDetail);
		model.addAttribute("introdDetail", introdDetail);
		return "member/resume/mypage/introduction/introductionDetail";
	}
	
	// 자소서 등록페이지 이동
	@GetMapping("create")
	public String getintroductionCreateForm(
		Model model
	) {
		IntroductionVO vo = new IntroductionVO();
		model.addAttribute(MODELNAME, vo);
		model.addAttribute("introdCreate", true);
		return "member/resume/mypage/introduction/introductionForm";
	}
	
	// 자소서 수정 페이지 이동
	@GetMapping("edit/{no}")
	public String getintroductionEditForm (
		Model model
		, @PathVariable String no
	) {
		log.info("=====>{}", no);
		model.addAttribute(MODELNAME, service.readIntroductionDetail(no));
		model.addAttribute("introdEdit", true);
		return "member/resume/mypage/introduction/introductionForm";
	}	
	
	// 자소서 등록 로직 구현 controller
	@PostMapping("create")
	public String createIntrodcution(
		@Valid @ModelAttribute(MODELNAME) IntroductionVO itrdVO
		, BindingResult errors
		, RedirectAttributes redirectAttributes
	) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();
		
		String lvn = "";
		log.info("진짜 설마 아직도 안찍힘??? >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> {}", itrdVO);
		if(!errors.hasErrors()) {
      	// pk가 있으면 수정 로직 !
      	if(itrdVO.getIntroductionNo() != null) {
      		log.info("pk 있는 지 확인 >>>>>>>>>>>>>>>>>>>>>>> {}", itrdVO);
      		itrdVO.setUserId(userId);
        	service.editIntroduction(itrdVO);
      	} else {
      		itrdVO.setUserId(userId);
        	service.createIntroduction(itrdVO);
      	}
    	  lvn = "redirect:/mypage/introduction/list";
      } else {
				log.info("유효성 검사 실패!!!");
				String errorsName = BindingResult.MODEL_KEY_PREFIX + MODELNAME;
	      redirectAttributes.addFlashAttribute(errorsName, errors); 
	
	      // 입력 데이터 유지용 add FlashAttribute
	      redirectAttributes.addFlashAttribute(MODELNAME, itrdVO);
		
	      lvn = "redirect:/mypage/introduction/create"; // 입력데이터 가지고 다시 입력 폼으로 보내줌			
      }
		return lvn;
	}

	
	// 자소서 삭제 업데이트 후 목록 페이지 이동
	@GetMapping("delete/{no}")
	public String getintroductionCreate (
		Model model
		, @AuthenticationPrincipal UserDetails userDetails
		, @PathVariable String no 
		, RedirectAttributes redirectAttributes
	) {
		String userId = userDetails.getUsername();
		IntroductionVO vo = new IntroductionVO();
		vo.setUserId(userId);
		vo.setIntroductionNo(no);
		
		try {
	        service.removeIntroduction(vo);
	        redirectAttributes.addFlashAttribute("message", "자소서가 성공적으로 삭제되었습니다.");
	    } catch (IllegalArgumentException e) {
	        // 서비스 계층에서 발생한 예외를 캐치하여 사용자에게 메시지를 전달합니다.
	        log.error("자소서 삭제 실패: {}", e.getMessage());
	        redirectAttributes.addFlashAttribute("error", e.getMessage());
	    } catch (Exception e) {
	        // 그 외 예외 처리
	        log.error("자소서 삭제 중 예상치 못한 오류 발생", e);
	        redirectAttributes.addFlashAttribute("error", "자소서 삭제 중 오류가 발생했습니다.");
	    }

		return "redirect:/mypage/introduction/list";
	}
	
	
	
	
	// 검색 로직 수행
	@GetMapping("search")
	public String getintroductionSearch(
		Model model
		, @RequestParam String keyword
	) {
		model.addAttribute("introductionList", service.readIntroductionSearch(keyword));
		return "member/resume/mypage/introduction/introductionList";
	}
}

