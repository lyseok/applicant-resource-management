package kr.or.ddit.admin.community.commuBoard.controller;

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

import kr.or.ddit.admin.community.commuBoard.service.AdminCommuBoardService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.community.CommuBoardVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/board/commu_board")
@RequiredArgsConstructor
public class AdminCommuBoardController {

	private final AdminCommuBoardService service;
	private ErrorsUtils errorsUtils;
	
	//에러 있을 시만 주입
	@Autowired(required = false)  
	public void setErrorsUtils(ErrorsUtils errorsUtils) {
		this.errorsUtils = errorsUtils;
	}
	
	static final String MODELNAME = "cboard";
	
	@ModelAttribute(MODELNAME)
	public CommuBoardVO cboard() {
		return new CommuBoardVO();
	}

	// 게시글 단건조회
	@GetMapping("/detail")
	public String cboardDetail(String commuPostNo, Model model) {
		CommuBoardVO cboard = service.readCommuBoard(commuPostNo).get();  
		model.addAttribute("cboard", cboard);

		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "admin/community/commuBoard/cboardDetail";
	}

	// 카테고리별 게시글 목록조회
	@GetMapping("/list/type")
	public String cboardType(String categoryCode, Model model) {
		List<CommuBoardVO> cboardList = service.readCommuBoardList(categoryCode);
		model.addAttribute("cboardList", cboardList);
		model.addAttribute("categoryCode", categoryCode);

		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "admin/community/commuBoard/cboardList";
	}
		
	// 등록 폼으로 이동
	@GetMapping("/form")
	public String formUI(Model model) {
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "admin/community/commuBoard/cboardForm";
	}
	
	// 수정 폼으로 이동
	@GetMapping("/form/edit")
	public String editForm(String commuPostNo, Model model) {  
		if(!model.containsAttribute(MODELNAME)) {
			CommuBoardVO cboard = service.readCommuBoard(commuPostNo).get();
			model.addAttribute(MODELNAME, cboard);
		}
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "admin/community/commuBoard/cboardForm";
	}

	// 폼 입력 데이터 처리
	@PostMapping("/form/insert")
	public String cboardForm(
		@Validated(InsertGroup.class) @ModelAttribute(MODELNAME) CommuBoardVO cboard
		, BindingResult errors
		, RedirectAttributes redirectAttributes
		, Model model
	) {
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		String lvn;
		if(!errors.hasErrors()) {
			service.createCommuBoard(cboard);
			lvn = "admin/community/commuBoard/cboardDetail";
		}else {
			String errorsName = BindingResult.MODEL_KEY_PREFIX + MODELNAME;
			redirectAttributes.addFlashAttribute(MODELNAME, cboard);
			redirectAttributes.addFlashAttribute(errorsName, errors);
			lvn = "admin/community/commuBoard/cboardForm";
		}
		return lvn;
	}
	
	// 폼 수정 데이터 처리
	@PostMapping("/form/edit")
	public String cboardEdit(
		String commuPostNo
		, @Validated(UpdateGroup.class) @ModelAttribute(MODELNAME) CommuBoardVO cboard
		, BindingResult errors
		, RedirectAttributes redirectAttributes
		, Model model
	) {
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		String lvn;
		if(!errors.hasErrors()) {
			service.modifyCommuBoard(cboard);
			lvn = "admin/community/commuBoard/cboardDetail";
		}else {
			redirectAttributes.addFlashAttribute(MODELNAME, cboard);
			redirectAttributes.addFlashAttribute("errors", errorsUtils.errorsToMap(errors));
			lvn = "admin/community/commuBoard/cboardForm";
		}
		return lvn;
	}
}
