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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.member.common.mypage.scrab.scrabRecruitment.service.MemberScrabRecruitmentService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.common.PaymentLogVO;
import kr.or.ddit.vo.common.ScrabRecruitmentVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/mypage/scrabRecruit")
@RequiredArgsConstructor
@Slf4j
public class MemberScrabRecruitmentController {

	private final MemberScrabRecruitmentService service;

	// 등록 폼으로 이동
	@GetMapping("/form")
	public String formUI(Model model) {
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "member/common/mypage/scrab/scrabRecruitment/srecruitForm";
	}

	@GetMapping("/list")
	public String listUI(Model model, @RequestParam(defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "") String keyword
				) {
		List<ScrabRecruitmentVO> srecruitList = service.readMyScrabRecruitmentList();
		
		if(keyword != null && !keyword.trim().isEmpty()) {
			srecruitList = srecruitList.stream()
					.filter(s-> s.getRecruitment().getRecruitmentTitle() != null && 
								s.getRecruitment().getRecruitmentTitle().toLowerCase()
								 .contains(keyword.toLowerCase()))
								.toList();
		}
		
		int totalItems = srecruitList.size();
		int itemsPerPage = 10;
		int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

		int fromIndex = (page - 1) * itemsPerPage;
		int toIndex = Math.min(fromIndex + itemsPerPage, totalItems);

		List<ScrabRecruitmentVO> pageList = srecruitList.subList(fromIndex, toIndex);

		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("srecruitList", pageList);
		model.addAttribute("keyword", keyword);
		log.info("srecruitList : {}", srecruitList);
		return "member/common/mypage/scrab/scrabRecruitment/srecruitList";
	}

	/*
	 * private ErrorsUtils errorsUtils;
	 * 
	 * //에러 있을 시만 주입
	 * 
	 * @Autowired(required = false) public void setErrorsUtils(ErrorsUtils
	 * errorsUtils) { this.errorsUtils = errorsUtils; }
	 * 
	 * static final String MODELNAME = "srecruit";
	 * 
	 * @ModelAttribute(MODELNAME) public ScrabRecruitmentVO srecruit() { return new
	 * ScrabRecruitmentVO(); }
	 * 
	 * // 관심 공고 목록조회
	 * 
	 * @GetMapping("/list") public String srecruitList(Model model) {
	 * List<ScrabRecruitmentVO> srecruitList = service.readScrabRecruitmentList();
	 * model.addAttribute("srecruitList", srecruitList);
	 * 
	 * model.addAttribute("boardCss", true); model.addAttribute("searchBar", true);
	 * return "member/common/mypage/scrab/scrabRecruitment/srecruitList"; }
	 * 
	 * // 관심 공고 단건조회
	 * 
	 * @GetMapping("/detail") public String srecruitDetail(
	 * 
	 * @ModelAttribute ScrabRecruitmentVO srecruit , Model model ) {
	 * ScrabRecruitmentVO vo = null; if (srecruit.getUserId() != null &&
	 * srecruit.getRecruitmentNo() != null) { vo =
	 * service.searchScrabRecruitmentByPk(srecruit).orElse(null); } //service가 널일 일은
	 * 없음, srecruit는 없으면 false 처리됨 //동기 컨트롤러니까 jsp에서 if jstl로 처리, 비동기는 (!vo)로 처리
	 * model.addAttribute("srecruit", vo);
	 * 
	 * model.addAttribute("boardCss", true); model.addAttribute("searchBar", true);
	 * return "member/common/mypage/scrab/scrabRecruitment/srecruitDetail"; }
	 * 
	 * // 수정 폼으로 이동
	 * 
	 * @GetMapping("/form/edit") public String editForm(
	 * 
	 * @ModelAttribute ScrabRecruitmentVO srecruit , Model model ) {
	 * ScrabRecruitmentVO vo = null; if(!model.containsAttribute(MODELNAME)) { //모델에
	 * srecruit가 없으면, db에서 가져옴 vo =
	 * service.searchScrabRecruitmentByPk(srecruit).get();
	 * model.addAttribute(MODELNAME, vo); } model.addAttribute("boardCss", true);
	 * model.addAttribute("searchBar", true); return
	 * "member/common/mypage/scrab/scrabRecruitment/srecruitForm"; }
	 * 
	 * // 폼 입력 데이터 처리
	 * 
	 * @PostMapping("/form/insert") public String srecruitForm(
	 * 
	 * @Validated(InsertGroup.class) @ModelAttribute(MODELNAME) ScrabRecruitmentVO
	 * srecruit , BindingResult errors , RedirectAttributes redirectAttributes ,
	 * Model model ) { model.addAttribute("boardCss", true);
	 * model.addAttribute("searchBar", true); String lvn; if(!errors.hasErrors()) {
	 * service.createScrabRecruitment(srecruit); lvn =
	 * "member/common/mypage/scrab/scrabRecruitment/srecruitDetail"; }else { String
	 * errorsName = BindingResult.MODEL_KEY_PREFIX + MODELNAME;
	 * redirectAttributes.addFlashAttribute(MODELNAME, srecruit);
	 * redirectAttributes.addFlashAttribute(errorsName, errors); lvn =
	 * "member/common/mypage/scrab/scrabRecruitment/srecruitForm"; } return lvn; }
	 * 
	 * // 폼 수정 데이터 처리
	 * 
	 * @PutMapping("/form/edit") public String srecruitEdit( ScrabRecruitmentVO
	 * srecruit , @Validated(UpdateGroup.class) @ModelAttribute(MODELNAME)
	 * ScrabRecruitmentVO vo , BindingResult errors , RedirectAttributes
	 * redirectAttributes , Model model ) { model.addAttribute("boardCss", true);
	 * model.addAttribute("searchBar", true); String lvn; if(!errors.hasErrors()) {
	 * service.modifyScrabRecruitment(srecruit); lvn =
	 * "member/common/mypage/scrab/scrabRecruitment/srecruitDetail"; }else {
	 * redirectAttributes.addFlashAttribute(MODELNAME, vo);
	 * redirectAttributes.addFlashAttribute("errors",
	 * errorsUtils.errorsToMap(errors)); lvn =
	 * "member/common/mypage/scrab/scrabRecruitment/srecruitForm"; } return lvn; }
	 * 
	 * // 관심 공고 단건 삭제
	 * 
	 * @DeleteMapping("detail/remove") public String
	 * srecruitDelete(ScrabRecruitmentVO srecruit, Model model) {
	 * service.removeScrabRecruitment(srecruit);
	 * 
	 * model.addAttribute("boardCss", true); model.addAttribute("searchBar", true);
	 * return "member/common/mypage/scrab/scrabRecruitment/srecruitList"; }
	 */
}
