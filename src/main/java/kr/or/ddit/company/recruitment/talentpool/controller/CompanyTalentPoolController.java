package kr.or.ddit.company.recruitment.talentpool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.company.recruitment.talentpool.service.CompanyTalentService;
import kr.or.ddit.vo.resume.CareerVO;

@RequestMapping("/talentpool")
@Controller
public class CompanyTalentPoolController {
	
	@Autowired
	private CompanyTalentService CTservice; 

	@GetMapping("/list")
	public String talentpoolList(Model model) {
		List<CareerVO> talentpoolList = CTservice.selectTalentPoolList();
//		List<MySkillVO> SkillList = CTservice.selectMySkill();
		model.addAttribute("boardCss",true);
		model.addAttribute("talentpoolList", talentpoolList);
//		model.addAttribute("skillList",SkillList);
		return "company/recruitment/TalentPool";
	}
	
}
