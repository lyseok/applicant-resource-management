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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.member.common.mypage.scrab.scrabCompany.service.MemberScrabCompanyService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.common.ScrabCompanyVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/mypage/scrabCompany")
@RequiredArgsConstructor
@Slf4j
public class MemberScrabCompanyController {

	private final MemberScrabCompanyService service;
	
	// 등록 폼으로 이동
	@GetMapping("/form")
	public String formUI(Model model) {
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "member/common/mypage/scrab/scrabCompany/scompanyForm";
	}
	
	@GetMapping("/list")
	public String listUI(
		Model model
		, @RequestParam(defaultValue = "1") int page
		, @RequestParam(required = false, defaultValue = "") String keyword
			) {
		List<ScrabCompanyVO> SCompany = service.readMyScrabCompanyList();
		
		int totalItems = SCompany.size();
		int itemsPerPage = 10;
		int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);
		
		int fromIndex = (page - 1) * itemsPerPage;
		int toIndex = Math.min(fromIndex + itemsPerPage, totalItems);
		
		List<ScrabCompanyVO> pageList = SCompany.subList(fromIndex, toIndex);
		
		if(keyword != null && !keyword.trim().isEmpty()) {
			SCompany = SCompany.stream()
						.filter(s-> s.getCompany().getComName() != null &&
									s.getCompany().getComName().toLowerCase()
									 .contains(keyword.toLowerCase()))
									 .toList();
		}
		
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("SCompany",pageList);
		model.addAttribute("keyword", keyword);
		log.info("SCompany : {}",SCompany);
		return "member/common/mypage/scrab/scrabCompany/scompanyList";
	}
	
	/*
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
	@GetMapping("/list")
	public String scompanyList(Model model) {
		List<ScrabCompanyVO> scompanyList = service.readScrabCompanyList();
		model.addAttribute("scompanyList", scompanyList);
		
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);		
		return "member/common/mypage/scrab/scrabCompany/scompanyList";
	}
	
	// 관심 기업 단건조회
	@GetMapping("/detail")
	public String scompanyDetail(
		@RequestParam(value = "companyId", required = false) String companyId
		, Model model
	) {
		ScrabCompanyVO scompany = null;
	    if (companyId != null) {
	    	scompany = service.searchScrabCompanyByComId(companyId).orElse(null);
	    }
		//service가 널일 일은 없음, scompany는 없으면 false 처리됨
		//동기 컨트롤러니까 jsp에서 if jstl로 처리, 비동기는 (!vo)로 처리
		model.addAttribute("scompany", scompany);

		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "member/common/mypage/scrab/scrabCompany/scompanyDetail";
	}
	
	// 수정 폼으로 이동
	@GetMapping("/form/edit")
	public String editForm(String companyId, Model model) {
		if(!model.containsAttribute(MODELNAME)) {  //모델에 scompany가 없으면, db에서 가져옴
			ScrabCompanyVO scompany = service.searchScrabCompanyByComId(companyId).get();
			model.addAttribute(MODELNAME, scompany);
		}
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "member/common/mypage/scrab/scrabCompany/scompanyForm";
	}

	// 폼 입력 데이터 처리
	@PostMapping("/form/insert")
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
	@PutMapping("/form/edit")
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
	@DeleteMapping("detail/remove")
	public String scompanyDelete(ScrabCompanyVO scompany, Model model) {
		service.removeScrabCompany(scompany);
		
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "member/common/mypage/scrab/scrabCompany/scompanyList";
	}
	*/
}
