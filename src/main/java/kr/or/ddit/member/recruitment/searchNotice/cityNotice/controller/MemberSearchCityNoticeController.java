package kr.or.ddit.member.recruitment.searchNotice.cityNotice.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.member.recruitment.searchNotice.cityNotice.service.MemberSearchCityNoticeAjaxService;
import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member/recruit/city")
@RequiredArgsConstructor
public class MemberSearchCityNoticeController {
	
	private final MemberSearchCityNoticeAjaxService service;
	
	// 서치바에 있지만, 일단 검색 카테고리만 jsp 눈으로 확인용
	@GetMapping("/list")
	public String mrecruitList(Model model) {
//		log.info("현정이 미워");
		List<RecruitmentNoticeVO> recruitList = service.readRecruitList();
		model.addAttribute("recruitList", recruitList);
		
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);		
		return "member/recruitment/searchnotice/citynotice/cnoticeList";
	}

}
