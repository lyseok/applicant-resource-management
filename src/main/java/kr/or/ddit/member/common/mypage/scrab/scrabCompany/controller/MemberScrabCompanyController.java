package kr.or.ddit.member.common.mypage.scrab.scrabCompany.controller;

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

import kr.or.ddit.member.common.mypage.scrab.scrabCompany.service.MemberScrabCompanyService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.common.ScrabCompanyVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member/common/mypage/scrab/scrabCompany")
@RequiredArgsConstructor
public class MemberScrabCompanyController {

	private final MemberScrabCompanyService service;
	private ErrorsUtils errorsUtils;
	
	//에러 있을 시만 주입
	@Autowired(required = false)  
	public void setErrorsUtils(ErrorsUtils errorsUtils) {
		this.errorsUtils = errorsUtils;
	}
	
	static final String MODELNAME = "scompany";
	
	@ModelAttribute(MODELNAME)
	public ScrabCompanyVO scompany() {
		return new ScrabCompanyVO();
	}
	
	// 관심 기업 목록조회
	@GetMapping("/scompanyList")
	public String scompanyList(Model model) {
		List<ScrabCompanyVO> scompanyList = service.readScrabCompanyList();
		model.addAttribute("scompanyList", scompanyList);
		
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);		
		return "member/common/mypage/scrab/scrabCompany/scompanyList";
	}
	
	// 관심 기업 단건조회
	@GetMapping("/scompanyDetail")
	public String scompanyDetail(
		@ModelAttribute ScrabCompanyVO scompany
		, Model model
	) {
		ScrabCompanyVO vo = null;
	    if (scompany.getUserId() != null && scompany.getCompanyId() != null) {
	    	vo = service.searchScrabCompanyByPk(scompany).orElse(null);
	    }
		//service가 널일 일은 없음, scompany는 없으면 false 처리됨
		//동기 컨트롤러니까 jsp에서 if jstl로 처리, 비동기는 (!vo)로 처리
		model.addAttribute("scompany", vo);

		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "member/common/mypage/scrab/scrabCompany/scompanyDetail";
	}
	
	// 등록 폼으로 이동
	@GetMapping("/scompanyForm")
	public String formUI(Model model) {
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "member/common/mypage/scrab/scrabCompany/scompanyForm";
	}
	
	// 수정 폼으로 이동
	@GetMapping("/scompanyForm/edit")
	public String editForm(
		@ModelAttribute ScrabCompanyVO scompany
		, Model model
	) {
		ScrabCompanyVO vo = null;
		if(!model.containsAttribute(MODELNAME)) {  //모델에 scompany가 없으면, db에서 가져옴
			vo = service.searchScrabCompanyByPk(scompany).get();
			model.addAttribute(MODELNAME, vo);
		}
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "member/common/mypage/scrab/scrabCompany/scompanyForm";
	}

	// 폼 입력 데이터 처리
	@PostMapping("/scompanyForm/insert")
	public String scompanyForm(
		@Validated(InsertGroup.class) @ModelAttribute(MODELNAME) ScrabCompanyVO scompany
		, BindingResult errors
		, RedirectAttributes redirectAttributes
		, Model model
	) {
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		String lvn;
		if(!errors.hasErrors()) {
			service.createScrabCompany(scompany);
			lvn = "member/common/mypage/scrab/scrabCompany/scompanyDetail";
		}else {
			String errorsName = BindingResult.MODEL_KEY_PREFIX + MODELNAME;
			redirectAttributes.addFlashAttribute(MODELNAME, scompany);
			redirectAttributes.addFlashAttribute(errorsName, errors);
			lvn = "member/common/mypage/scrab/scrabCompany/scompanyForm";
		}
		return lvn;
	}
	
	// 폼 수정 데이터 처리
	@PutMapping("/scompanyForm/update")
	public String scompanyEdit(
		ScrabCompanyVO scompany
		, @Validated(UpdateGroup.class) @ModelAttribute(MODELNAME) ScrabCompanyVO vo
		, BindingResult errors
		, RedirectAttributes redirectAttributes
		, Model model
	) {
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		String lvn;
		if(!errors.hasErrors()) {
			service.modifyScrabCompany(vo);
			lvn = "member/common/mypage/scrab/scrabCompany/scompanyDetail";
		}else {
			redirectAttributes.addFlashAttribute(MODELNAME, vo);
			redirectAttributes.addFlashAttribute("errors", errorsUtils.errorsToMap(errors));
			lvn = "member/common/mypage/scrab/scrabCompany/scompanyForm";
		}
		return lvn;
	}
	
	// 관심 기업 단건 삭제
	@DeleteMapping("scompanyDetail/remove")
	public String scompanyDelete(ScrabCompanyVO scompany, Model model) {
		service.removeCompany(scompany);
		
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "member/common/mypage/scrab/scrabCompany/scompanyList";
	}
	
}
