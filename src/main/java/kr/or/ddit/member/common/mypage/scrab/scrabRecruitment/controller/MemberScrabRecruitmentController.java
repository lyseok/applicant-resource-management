package kr.or.ddit.member.common.mypage.scrab.scrabRecruitment.controller;

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

import kr.or.ddit.member.common.mypage.scrab.scrabRecruitment.service.MemberScrabRecruitmentService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.common.ScrabRecruitmentVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/ajax/member/common/mypage/scrab/scrabRecruitment")
@RequiredArgsConstructor
public class MemberScrabRecruitmentController {

	private final MemberScrabRecruitmentService service;
	private ErrorsUtils errorsUtils;
	
	//에러 있을 시만 주입
	@Autowired(required = false)  
	public void setErrorsUtils(ErrorsUtils errorsUtils) {
		this.errorsUtils = errorsUtils;
	}
	
	static final String MODELNAME = "srecruit";
	
	@ModelAttribute(MODELNAME)
	public ScrabRecruitmentVO srecruit() {
		return new ScrabRecruitmentVO();
	}
	
	// 관심 공고 목록조회
	@GetMapping("/srecruitList")
	public String srecruitList(Model model) {
		List<ScrabRecruitmentVO> srecruitList = service.readScrabRecruitmentList();
		model.addAttribute("srecruitList", srecruitList);
		
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);		
		return "member/common/mypage/scrab/scrabRecruitment/srecruitList";
	}
	
	// 관심 공고 단건조회
	@GetMapping("/srecruitDetail")
	public String srecruitDetail(
		@ModelAttribute ScrabRecruitmentVO srecruit
		, Model model
	) {
		ScrabRecruitmentVO vo = null;
	    if (srecruit.getUserId() != null && srecruit.getRecruitmentNo() != null) {
	    	vo = service.searchScrabRecruitmentByPk(srecruit).orElse(null);
	    }
		//service가 널일 일은 없음, srecruit는 없으면 false 처리됨
		//동기 컨트롤러니까 jsp에서 if jstl로 처리, 비동기는 (!vo)로 처리
		model.addAttribute("srecruit", vo);

		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "member/common/mypage/scrab/scrabRecruitment/srecruitDetail";
	}
	
	// 등록 폼으로 이동
	@GetMapping("/srecruitForm")
	public String formUI(Model model) {
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "member/common/mypage/scrab/scrabRecruitment/srecruitForm";
	}
	
	// 수정 폼으로 이동
	@GetMapping("/srecruitForm/edit")
	public String editForm(
		@ModelAttribute ScrabRecruitmentVO srecruit
		, Model model
	) {
		ScrabRecruitmentVO vo = null;
		if(!model.containsAttribute(MODELNAME)) {  //모델에 srecruit가 없으면, db에서 가져옴
			vo = service.searchScrabRecruitmentByPk(srecruit).get();
			model.addAttribute(MODELNAME, vo);
		}
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "member/common/mypage/scrab/scrabRecruitment/srecruitForm";
	}

	// 폼 입력 데이터 처리
	@PostMapping("/srecruitForm/insert")
	public String srecruitForm(
		@Validated(InsertGroup.class) @ModelAttribute(MODELNAME) ScrabRecruitmentVO srecruit
		, BindingResult errors
		, RedirectAttributes redirectAttributes
		, Model model
	) {
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		String lvn;
		if(!errors.hasErrors()) {
			service.createScrabRecruitment(srecruit);
			lvn = "member/common/mypage/scrab/scrabRecruitment/srecruitDetail";
		}else {
			String errorsName = BindingResult.MODEL_KEY_PREFIX + MODELNAME;
			redirectAttributes.addFlashAttribute(MODELNAME, srecruit);
			redirectAttributes.addFlashAttribute(errorsName, errors);
			lvn = "member/common/mypage/scrab/scrabRecruitment/srecruitForm";
		}
		return lvn;
	}
	
	// 폼 수정 데이터 처리
	@PutMapping("/srecruitForm/update")
	public String srecruitEdit(
		ScrabRecruitmentVO srecruit
		, @Validated(UpdateGroup.class) @ModelAttribute(MODELNAME) ScrabRecruitmentVO vo
		, BindingResult errors
		, RedirectAttributes redirectAttributes
		, Model model
	) {
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		String lvn;
		if(!errors.hasErrors()) {
			service.modifyScrabRecruitment(srecruit);
			lvn = "member/common/mypage/scrab/scrabRecruitment/srecruitDetail";
		}else {
			redirectAttributes.addFlashAttribute(MODELNAME, vo);
			redirectAttributes.addFlashAttribute("errors", errorsUtils.errorsToMap(errors));
			lvn = "member/common/mypage/scrab/scrabRecruitment/srecruitForm";
		}
		return lvn;
	}
	
	// 관심 공고 단건 삭제
	@DeleteMapping("srecruitDetail/remove")
	public String srecruitDelete(ScrabRecruitmentVO srecruit, Model model) {
		service.removeScrabRecruitment(srecruit);
		
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "member/common/mypage/scrab/scrabRecruitment/srecruitList";
	}
	
}
