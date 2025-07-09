package kr.or.ddit.admin.community.adminBoard.controller;

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

import kr.or.ddit.admin.community.adminBoard.service.AdminAdminBoardAjaxService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.community.AdminBoardVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/community/adminBoard")
@RequiredArgsConstructor
public class AdminAdminBoardController {

	private final AdminAdminBoardAjaxService service;
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
	@GetMapping("/aboardDetail")  //http://localhost/admin/community/adminBoard/aboardDetail?boardNo=ABNO000002
	public String aboardDetail(
		@RequestParam(value = "boardNo", required = false) String boardNo
		, Model model
	) {
		AdminBoardVO aboard = null;
	    if (boardNo != null) {  //boardNo가 있을때만 메서드 실행
	        aboard = service.readAdminBoardByPk(boardNo).orElse(null);
	    }
		//service가 널일 일은 없음, boardNo는 없으면 false 처리됨
		//동기 컨트롤러니까 jsp에서 if jstl로 처리, 비동기는 (!result)로 처리
		model.addAttribute("aboard", aboard);

		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "admin/community/adminBoard/aboardDetail";
//		return "redirect:/admin/adminBoard/detail?boardNo=" + boardNo;  //redirect는 다른 컨트롤러로 가서, 무한 리디렉션 에러!
	}
	
	// 유형별 게시글 목록조회
	@GetMapping("/aboardList/type")  //http://localhost/admin/community/adminBoard/aboardList/type?boardTypeCode=UFAQ-U5
	public String aboardType(String boardTypeCode, Model model) {
		List<AdminBoardVO> aboardList = service.readAdminBoardListByType(boardTypeCode);
		model.addAttribute("aboardList", aboardList);
		model.addAttribute("boardTypeCode", boardTypeCode);

		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "admin/community/adminBoard/aboardList";
	}
	
	// 게시글 목록조회
	@GetMapping("/aboardList")  //http://localhost/admin/community/adminBoard/aboardList
	public String aboardList(Model model) {
		List<AdminBoardVO> aboardList = service.readAdminBoardList();
		model.addAttribute("aboardList", aboardList);
		
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);		
		return "admin/community/adminBoard/aboardList";  //위랑 똑같네...? 이렇게 써도 되나
	}
	
	// 등록 폼으로 이동
	@GetMapping("/aboardForm")
	public String formUI(Model model) {
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "admin/community/adminBoard/aboardForm";
	}
	
	// 수정 폼으로 이동
	@GetMapping("/aboardForm/edit")
	public String editForm(String boardNo, Model model) {  
		if(!model.containsAttribute(MODELNAME)) {  //모델에 aboard가 없으면, db에서 가져옴
			AdminBoardVO aboard = service.readAdminBoardByPk(boardNo).get();
			model.addAttribute(MODELNAME, aboard);
		}
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "admin/community/adminBoard/aboardForm";
	}
	
	// 폼 입력 데이터 처리
	@PostMapping("/aboardForm/insert")
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
			lvn = "admin/community/adminBoard/aboardDetail";
		}else {
			String errorsName = BindingResult.MODEL_KEY_PREFIX + MODELNAME;
			redirectAttributes.addFlashAttribute(MODELNAME, aboard);
			redirectAttributes.addFlashAttribute(errorsName, errors);
			lvn = "admin/community/adminBoard/aboardForm";
		}
		return lvn;
	}
	
	// 폼 수정 데이터 처리
	@PutMapping("/aboardForm/update")
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
			lvn = "admin/community/adminBoard/aboardDetail";
		}else {
			redirectAttributes.addFlashAttribute(MODELNAME, aboard);
			redirectAttributes.addFlashAttribute("errors", errorsUtils.errorsToMap(errors));
			lvn = "admin/community/adminBoard/aboardForm";
		}
		return lvn;
	}
	
	// 게시글 단건 삭제
	@DeleteMapping("aboardDetail/remove")  //aboardDetail 붙는거 맞나
	public String aboardDelete(String boardNo, Model model) {
		service.removeAdminBoard(boardNo);
		
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "admin/community/adminBoard/aboardList";
	}
}
