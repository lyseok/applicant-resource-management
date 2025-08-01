package kr.or.ddit.common.popup.resume.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.member.resume.resume.service.ResumeService;
import kr.or.ddit.vo.resume.IntroductionVO;
import kr.or.ddit.vo.resume.ResumeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/popup")
@RequiredArgsConstructor
public class ResuemPopupController {
	private final ResumeService service;

	static final String MODELNAME = "resumeList";

	@ModelAttribute(MODELNAME)
	public ResumeVO setupResumeVO() {
		ResumeVO vo = new ResumeVO();
		return vo;
	}
	// 상세조회
	@GetMapping("/resume/{no}")
	public String getResumeDetail(Model model, @PathVariable String no) throws JsonProcessingException {
		log.info("리슘 팝업 열렸음");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();

		ResumeVO vo = new ResumeVO();
		vo.setUserId(userId);
		vo.setResumeNo(no);
		ResumeVO resume = service.readResumeDetail(vo);
		
		// 이력서 안에 자소서 없을경우 ( 구조 변경 전 자소서 선택되어있을 경우)
		IntroductionVO intro = resume.getIntroduction();
		if (intro != null) {
			String questionList = new ObjectMapper().writeValueAsString(intro.getIntroductionQuestionList());
			model.addAttribute("questionList", questionList);
		} else {
		    model.addAttribute("questionJson", "[]"); // 빈 리스트 넘김
		}

		model.addAttribute(MODELNAME, resume);

		return "member/common/resumePopup";
	}
}
