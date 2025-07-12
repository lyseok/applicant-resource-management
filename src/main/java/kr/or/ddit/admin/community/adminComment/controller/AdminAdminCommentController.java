package kr.or.ddit.admin.community.adminComment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import kr.or.ddit.admin.community.adminComment.service.AdminAdminCommentAjaxService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.community.AdminCommentVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/board/admin_comment")
@RequiredArgsConstructor
public class AdminAdminCommentController {

	private final AdminAdminCommentAjaxService service;
	
	// 등록 폼으로 이동
	@GetMapping("/form")
	public String formUI(Model model) {
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "admin/community/adminComment/acommentForm";
	}
	
	// 관리자 답글 전체 조회
	@GetMapping("/list")
	public String acommentList(Model model) {
		List<AdminCommentVO> acommentList = service.searchAdminCommentList();
		model.addAttribute("acommentList", acommentList);
		
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);		
		return "admin/community/adminComment/acommentList";
	}
	
	// 문의사항별 관리자 답글 목록조회
	@GetMapping  //"/admin/board/admin_comment?no=ABNO000001"
	public String acommentType(
		String no
		, Model model
	) {
		List<AdminCommentVO> acommentList = service.searchAdminCommentCommentList(no);
		model.addAttribute("acommentList", acommentList);
		model.addAttribute("no", no);

		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "admin/community/adminComment/acommentList";
	}
	
	/*
	private ErrorsUtils errorsUtils;
	
	//에러 있을 시만 주입
	@Autowired(required = false)  
	public void setErrorsUtils(ErrorsUtils errorsUtils) {
		this.errorsUtils = errorsUtils;
	}
	
	static final String MODELNAME = "acomment";
	
	@ModelAttribute(MODELNAME)
	public AdminCommentVO acomment() {
		return new AdminCommentVO();
	}
	
	// 댓글 단건조회
	@GetMapping("/detail")  //"/admin/board/admin_comment/detail?no=ADCM000002"
	public String acommentDetail(
		@RequestParam(value = "no") String commentNo
		, Model model
	) {
		AdminCommentVO acomment = service.readAdminCommentbyPk(commentNo).get();  
		//service가 널일 일은 없음, commentNo는 없으면 false 처리됨
		//동기 컨트롤러니까 jsp에서 if jstl로 처리, 비동기는 (!result)로 처리
		model.addAttribute("acomment", acomment);

		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "admin/community/adminComment/acommentDetail";
	}
	
	// 수정 폼으로 이동
	@GetMapping("/form/edit")  //"/admin/board/admin_comment/form/edit?no=ADCM000003"
	public String editForm(
		@RequestParam(value = "no") String commentNo
		, Model model
	) {  
		if(!model.containsAttribute(MODELNAME)) {  //모델에 acomment가 없으면, db에서 가져옴
			AdminCommentVO acomment = service.readAdminCommentbyPk(commentNo).get();
			model.addAttribute(MODELNAME, acomment);
		}
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "admin/community/adminComment/acommentForm";
	}
	
	// 폼 입력 데이터 처리
	@PostMapping("/form/insert")
	public String acommentForm(
		@Validated(InsertGroup.class) @ModelAttribute(MODELNAME) AdminCommentVO acomment
		, BindingResult errors
		, RedirectAttributes redirectAttributes
		, Model model
	) {
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		String lvn;
		if(!errors.hasErrors()) {
			service.createAdminComment(acomment);
			lvn = "admin/community/adminBoard/aboardDetail";
		}else {
			String errorsName = BindingResult.MODEL_KEY_PREFIX + MODELNAME;
			redirectAttributes.addFlashAttribute(MODELNAME, acomment);
			redirectAttributes.addFlashAttribute(errorsName, errors);
			lvn = "admin/community/adminBoard/aboardForm";
		}
		return lvn;
	}
	
	// 폼 수정 데이터 처리, 삭제 상태 변경
	@PostMapping("/form/edit")
	public String acommentEdit(
		String commentNo
		, @Validated(UpdateGroup.class) @ModelAttribute(MODELNAME) AdminCommentVO acomment
		, BindingResult errors
		, RedirectAttributes redirectAttributes
		, Model model
	) {
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		String lvn;
		if(!errors.hasErrors()) {
			service.modifyAdminComment(acomment);
			lvn = "admin/community/adminComment/acommentDetail";
		}else {
			redirectAttributes.addFlashAttribute(MODELNAME, acomment);
			redirectAttributes.addFlashAttribute("errors", errorsUtils.errorsToMap(errors));
			lvn = "admin/community/adminComment/acommentForm";
		}
		return lvn;
	}
	*/
}
