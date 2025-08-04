package kr.or.ddit.admin.community.adminBoard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.admin.community.adminBoard.service.AdminAdminBoardAjaxService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.community.AdminBoardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin/board/admin_board")
@RequiredArgsConstructor
public class AdminAdminBoardController { // 동기 컨트롤러는 페이지 이동용

	private final AdminAdminBoardAjaxService service;
	
	// 등록 폼으로 이동
	@GetMapping("/form")
	public String formUI(
		String type
		, Model model			
	) {
		model.addAttribute("type", type);
		model.addAttribute("boardCss", true);    // 게시판 전용 css 이 한줄만 추가해주면 됩니다.
		model.addAttribute("searchBar", true);   // 서치바 전용 css
		return "admin/community/adminBoard/aboardForm";
	}
	
	// 게시글 목록조회 - 관리자가 게시판 타입 상관없이 조회할 경우 사용할 듯
	@GetMapping("/list")  //http://localhost/admin/admin_board/list
	public String aboardList(Model model) {
		List<AdminBoardVO> aboardList = service.readAdminBoardList();
		model.addAttribute("aboardList", aboardList);
		
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);		
		return "admin/community/adminBoard/aboardList";
	}
	
	// 유형별 게시글 목록조회
	@GetMapping  //http://localhost/admin/admin_board?type=UFAQ-U5
	public String aboardType(
		@RequestParam String type
		, Model model			
	) {
		List<AdminBoardVO> aboardList = service.readAdminBoardListByType(type);
		model.addAttribute("aboardList", aboardList);
		model.addAttribute("type", type);

		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "admin/community/adminBoard/aboardList";
	}
	
	// 게시글 단건조회
	@GetMapping("/detail")  //http://localhost/admin/board/admin_board/detail?no=ABNO000002
	public String aboardDetail(
		String no
		, Model model
	) {
		AdminBoardVO aboard = null;
	    if (no != null) {
	        aboard = service.readAdminBoardByPk(no).orElse(null);
	    }
		model.addAttribute("aboard", aboard);

		model.addAttribute("boardCss", true);    // 게시판 전용 css 이 한줄만 추가해주면 됩니다.
		model.addAttribute("searchBar", true);   // 서치바 전용 css
		return "admin/community/adminBoard/aboardDetail";
	}
	
	/*
	
	// 수정 폼으로 이동
	@GetMapping("/form")  ///admin/board/admin_board/form?no=ABNO000002
	public String editForm(
		String no
		, Model model
	) {
		AdminBoardVO aboard = null;
	    if (no != null) {
	        aboard = service.readAdminBoardByPk(no).orElse(null);
	    }
		model.addAttribute("aboard", aboard);
		
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "admin/community/adminBoard/aboardForm";
	}
	
	private ErrorsUtils errorsUtils;
	
	//에러 있을 시만 주입
	@Autowired(required = false)  
	public void setErrorsUtils(ErrorsUtils errorsUtils) {
		this.errorsUtils = errorsUtils;
	}
	
	static final String MODELNAME = "aboard";
	
	@ModelAttribute(MODELNAME)
	public AdminBoardVO aboard() {
		return new AdminBoardVO();
	}
	
	// 폼 입력 데이터 처리
	@PostMapping("/form/insert")
	public String aboardForm(
	    @Validated(InsertGroup.class) @ModelAttribute(MODELNAME) AdminBoardVO aboard,
	    BindingResult errors,
	    RedirectAttributes redirectAttributes,
	    Model model
	) {
	    model.addAttribute("boardCss", true);
	    model.addAttribute("searchBar", true);
	    if (!errors.hasErrors()) {
	    	aboard.setUserId(getLoginId());
	        service.createAdminBoard(aboard); // 실제 저장
	        return "redirect:/admin/admin_board/detail?no=" + aboard.getBoardNo();
	    }
	    String errorsName = BindingResult.MODEL_KEY_PREFIX + MODELNAME;
	    redirectAttributes.addFlashAttribute(MODELNAME, aboard);
	    redirectAttributes.addFlashAttribute(errorsName, errors);
	    return "redirect:/admin/admin_board/form/insert";
	}

	
	// 폼 수정 데이터 처리, 삭제 상태 변경
	@PostMapping("/form/edit")
	public String aboardEdit(
		@Validated(UpdateGroup.class) @ModelAttribute(MODELNAME) AdminBoardVO aboard
		, BindingResult errors
		, RedirectAttributes redirectAttributes
		, Model model
	) {
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		if(!errors.hasErrors()) {
			service.modifyAdminBoard(aboard);
			return "redirect:/admin/admin_board/detail?no=" + aboard.getBoardNo();
		}else {
			redirectAttributes.addFlashAttribute(MODELNAME, aboard);
			redirectAttributes.addFlashAttribute("errors", errorsUtils.errorsToMap(errors));
			return "redirect:/admin/admin_board/form/edit?no=" + aboard.getBoardNo();
		}
	}
	
	private String getLoginId() {
 	   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = auth.getName();
        return userId;
	}
	*/
}
