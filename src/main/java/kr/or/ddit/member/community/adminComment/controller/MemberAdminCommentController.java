package kr.or.ddit.member.community.adminComment.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.member.community.adminComment.service.MemberAdminCommentAjaxService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.community.AdminCommentVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member/board/admin_comment")
@RequiredArgsConstructor
public class MemberAdminCommentController {

	private final MemberAdminCommentAjaxService service;
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
	@GetMapping("/detail")  
	public String acommentDetail(String commentNo, Model model) {
		AdminCommentVO acomment = service.readAdminCommentbyPk(commentNo).get();  
		//service가 널일 일은 없음, commentNo는 없으면 false 처리됨
		//동기 컨트롤러니까 jsp에서 if jstl로 처리, 비동기는 (!result)로 처리
		model.addAttribute("acomment", acomment);

		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "member/community/adminComment/acommentDetail";
	}
	
	// 게시글별 댓글 목록조회
	@GetMapping("/list/board")  
	public String acommentType(String boardNo, Model model) {
		List<AdminCommentVO> acommentList = service.searchAdminCommentCommentList(boardNo);
		model.addAttribute("acommentList", acommentList);
		model.addAttribute("boardNo", boardNo);

		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "member/community/adminComment/acommentList";
	}
	
	// 댓글 목록조회
	@GetMapping("/list")
	public String acommentList(Model model) {
		List<AdminCommentVO> acommentList = service.searchAdminCommentList();
		model.addAttribute("acommentList", acommentList);
		
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);		
		return "member/community/adminComment/acommentList";
	}
		
	// 등록 폼으로 이동
	@GetMapping("/form")
	public String formUI(Model model) {
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "member/community/adminComment/acommentForm";
	}
	
	// 수정 폼으로 이동
	@GetMapping("/form/edit")  
	public String editForm(String commentNo, Model model) {  
		if(!model.containsAttribute(MODELNAME)) {  //모델에 acomment가 없으면, db에서 가져옴
			AdminCommentVO acomment = service.readAdminCommentbyPk(commentNo).get();
			model.addAttribute(MODELNAME, acomment);
		}
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "member/community/adminComment/acommentForm";
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
			lvn = "member/community/adminComment/acommentDetail";
		}else {
			String errorsName = BindingResult.MODEL_KEY_PREFIX + MODELNAME;
			redirectAttributes.addFlashAttribute(MODELNAME, acomment);
			redirectAttributes.addFlashAttribute(errorsName, errors);
			lvn = "member/community/adminComment/acommentForm";
		}
		return lvn;
	}
	
	// 폼 수정 데이터 처리
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
			lvn = "member/community/adminComment/acommentDetail";
		}else {
			redirectAttributes.addFlashAttribute(MODELNAME, acomment);
			redirectAttributes.addFlashAttribute("errors", errorsUtils.errorsToMap(errors));
			lvn = "member/community/adminComment/acommentForm";
		}
		return lvn;
	}
}
