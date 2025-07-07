package kr.or.ddit.member.community.adminBoard.controller;

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

import kr.or.ddit.member.community.adminBoard.service.MemberAdminBoardAjaxService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.community.AdminBoardVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member/adminBoard")
@RequiredArgsConstructor
public class MemberAdminBoardController {

	private final MemberAdminBoardAjaxService service;
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
	
	// 게시글 단건조회
	@GetMapping("/detail")
	public String aboardDetail(String boardNo, Model model) {
		AdminBoardVO aboard = service.readAdminBoardByPk(boardNo).get();  
		//service가 널일 일은 없음, boardNo는 없으면 false 처리됨
		//동기 컨트롤러니까 jsp에서 if jstl로 처리, 비동기는 (!result)로 처리
		model.addAttribute("aboard", aboard);

		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "member/community/adminBoard/aboardDetail";
	}
	
	// 유형별 게시글 목록조회
	@GetMapping("/list/type")
	public String aboardType(String boardTypeCode, Model model) {
		List<AdminBoardVO> aboardList = service.readAdminBoardListByType(boardTypeCode);
		model.addAttribute("aboardList", aboardList);
		model.addAttribute("boardTypeCode", boardTypeCode);

		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "member/community/adminBoard/aboardList";
	}
	
	// 게시글 목록조회
	@GetMapping("/list")
	public String aboardList(Model model) {
		List<AdminBoardVO> aboardList = service.readAdminBoardList();
		model.addAttribute("aboardList", aboardList);
		
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);		
		return "member/community/adminBoard/aboardList";
	}
		
	// 등록 폼으로 이동
	@GetMapping("/form")
	public String formUI(Model model) {
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "member/community/adminBoard/aboardForm";
	}
	
	// 수정 폼으로 이동
	@GetMapping("/form/edit")
	public String editForm(String boardNo, Model model) {  
		if(!model.containsAttribute(MODELNAME)) {  //모델에 aboard가 없으면, db에서 가져옴
			AdminBoardVO aboard = service.readAdminBoardByPk(boardNo).get();
			model.addAttribute(MODELNAME, aboard);
		}
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "member/community/adminBoard/aboardForm";
	}
	
	// 폼 입력 데이터 처리
	@PostMapping("/form/insert")
	public String aboardForm(
		@Validated(InsertGroup.class) @ModelAttribute(MODELNAME) AdminBoardVO aboard
		, BindingResult errors
		, RedirectAttributes redirectAttributes
		, Model model
	) {
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		String lvn;
		if(!errors.hasErrors()) {
			service.createAdminBoard(aboard);
			lvn = "member/community/adminBoard/aboardDetail";
		}else {
			String errorsName = BindingResult.MODEL_KEY_PREFIX + MODELNAME;
			redirectAttributes.addFlashAttribute(MODELNAME, aboard);
			redirectAttributes.addFlashAttribute(errorsName, errors);
			lvn = "member/community/adminBoard/aboardForm";
		}
		return lvn;
	}
	
	// 폼 수정 데이터 처리
	@PutMapping("/form/update")
	public String aboardEdit(
		String boardNo
		, @Validated(UpdateGroup.class) @ModelAttribute(MODELNAME) AdminBoardVO aboard
		, BindingResult errors
		, RedirectAttributes redirectAttributes
		, Model model
	) {
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		String lvn;
		if(!errors.hasErrors()) {
			service.modifyAdminBoard(aboard);
			lvn = "member/community/adminBoard/aboardDetail";
		}else {
			redirectAttributes.addFlashAttribute(MODELNAME, aboard);
			redirectAttributes.addFlashAttribute("errors", errorsUtils.errorsToMap(errors));
			lvn = "member/community/adminBoard/aboardForm";
		}
		return lvn;
	}
	
	// 게시글 단건 삭제
	@DeleteMapping("/remove")
	public String aboardDelete(String boardNo, Model model) {
		service.removeAdminBoard(boardNo);
		
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "member/community/adminBoard/aboardList";
	}
}
