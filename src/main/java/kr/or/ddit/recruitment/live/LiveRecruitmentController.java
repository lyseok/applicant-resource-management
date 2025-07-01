package kr.or.ddit.recruitment.live;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;

@Controller
@RequestMapping
public class LiveRecruitmentController {
	

	@GetMapping("/liverecruitment.do")
	String liveRecruitment(Model model) {
//		List<RecruitmentNoticeVO> RecruitmentList = 
		model.addAttribute("boardCss",true);
		return "recruitment/live/recruitmentupload";
	}
}
