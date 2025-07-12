package kr.or.ddit.company.recruitment.talentpool.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.company.recruitment.talentpool.service.CompanyTalentService;
import kr.or.ddit.vo.common.CityCodeVO;
import kr.or.ddit.vo.common.JobVO;
import kr.or.ddit.vo.common.TopJobVO;
import kr.or.ddit.vo.resume.CareerVO;
import kr.or.ddit.vo.resume.EducationVO;
import kr.or.ddit.vo.resume.MySkillVO;
import kr.or.ddit.vo.resume.ResumeVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/company/talentpool")
@Controller
public class CompanyTalentPoolController {

	@Autowired
	private CompanyTalentService CTservice;

	@GetMapping("/list")
	public String talentpoolList(Model model) {
		List<ResumeVO> talentpoolList = CTservice.selectTalentPoolList();
		List<CareerVO> careerList = CTservice.selectCareer();
		List<TopJobVO> tjobList = CTservice.selectTopJob();
		List<JobVO> jobList = CTservice.selectJob();
		List<CityCodeVO> cityList = CTservice.selectlocation();
		List<EducationVO> eduList = CTservice.selecteducation();
		
		model.addAttribute("talentpoolList", talentpoolList);
		model.addAttribute("tjobList", tjobList);
		model.addAttribute("jobList", jobList);
		model.addAttribute("cityList", cityList);
		model.addAttribute("eduList", eduList);
		model.addAttribute("boardCss", true);
		log.info("list 를 했을때 로그 : {}", model);

		return "company/recruitment/talentpool/TalentPool";
	}

	 
	@GetMapping("/detail/{no}")
	public String detail(@PathVariable String no, Model model) {
		ResumeVO detail = CTservice.selectResumeDetail(no);	
		List<TopJobVO> tjobList = CTservice.selectTopJob();
		List<JobVO> jobList = CTservice.selectJob();
		model.addAttribute("boardCss", true);
		model.addAttribute("tjobList", tjobList);
		model.addAttribute("jobList", jobList);
		model.addAttribute("detail", detail); // 이 이름에 주의
		System.out.println("디테일 값 : " + detail);
		System.out.println("tjob" + tjobList);
		log.info("tjob" , tjobList);
		return "company/recruitment/talentpool/TalentPoolDetail";
	}


	@PostMapping("/filter")
	public String filterTalentPool(@RequestParam Map<String, Object> param, Model model) {
		List<ResumeVO> talentpoolList = CTservice.selectTalentPoolListByFilter(param);
	    model.addAttribute("talentpoolList", talentpoolList);
	    System.out.println("Filter로 받아온 값 :" + talentpoolList);
	    return "company/recruitment/talentpool/TalentpoolListFragment"; 
	}
	
	@PostMapping("/higtsearch")
	public String higtSearch(@RequestParam(required = false)
								String license,
							@RequestParam(required = false)
								String skillName,
								Model model) {
		 Map<String, String> paramMap = new HashMap<>();
		    paramMap.put("skillName", skillName);
		    paramMap.put("keywordlicense", license);

		    List<ResumeVO> talentpoolList = CTservice.selectSearchSkillAndLicense(paramMap);

		    model.addAttribute("talentpoolList", talentpoolList);
		    return "company/recruitment/talentpool/TalentpoolListFragment";
		}
	 
	
	
}