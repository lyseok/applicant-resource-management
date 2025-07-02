package kr.or.ddit.recruitment.live.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.mapper.recruitment.LiveRecruitmentMapper;
import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;

@Controller
@RequestMapping
public class LiveRecruitmentController {
	
	@Autowired
	private LiveRecruitmentMapper liveRecruitment;

	@GetMapping("/liverecruitment.do")
	String liveRecruitment(Model model) {
		model.addAttribute("boardCss",true);
		return "recruitment/live/recruitmentupload";
	}
}
