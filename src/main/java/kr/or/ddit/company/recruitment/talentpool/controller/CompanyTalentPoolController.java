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
import kr.or.ddit.vo.resume.ResumeVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/talentpool")
@Controller
public class CompanyTalentPoolController {

	@Autowired
	private CompanyTalentService CTservice;

	@GetMapping("/list")
	public String talentpoolList(Model model) {
		List<ResumeVO> talentpoolList = CTservice.selectTalentPoolList();
		List<TopJobVO> tjobList = CTservice.selectTopJob();
		List<JobVO> jobList = CTservice.selectJob();
		List<CityCodeVO> cityList = CTservice.selectlocation();
		List<EducationVO> eduList = CTservice.selecteducation();
		log.info("{}", talentpoolList);
		model.addAttribute("talentpoolList", talentpoolList);
		model.addAttribute("tjobList", tjobList);
		model.addAttribute("jobList", jobList);
		model.addAttribute("cityList", cityList);
		model.addAttribute("eduList", eduList);
		model.addAttribute("boardCss", true);

		return "company/recruitment/talentpool/TalentPool";
	}

	@GetMapping("/detail/{no}")
	public String detail(@PathVariable String no, Model model) {
		ResumeVO detail = CTservice.selectResumeDetail(no);

		model.addAttribute("boardCss", true);
		model.addAttribute("detail", detail); // 이 이름에 주의
		System.out.println("디테일 값 : " + detail);
		return "company/recruitment/talentpool/TalentPoolDetail";
	}


	@PostMapping("/filter")
	public String filterTalentPool(@RequestParam(required = false) String careerStart,
			@RequestParam(required = false) String region, @RequestParam(required = false) String education,
			@RequestParam(required = false) String graduate_edu, @RequestParam(required = false) String topJobPosition,
			@RequestParam(required = false) String jobPosition, Model model) {
		Map<String, Object> filter = new HashMap();
		filter.put("careerStart", careerStart);
		filter.put("region", region);
		filter.put("education", education);
		filter.put("graduateYn", graduate_edu);
		filter.put("topJobPosition", topJobPosition);
		filter.put("jobPosition", jobPosition);

		List<ResumeVO> filteredList = CTservice.selectTalentPoolListByFilter(filter);

		model.addAttribute("talentpoolList", filteredList);
		return "company/recruitment/TalentPool"; // 목록 JSP 그대로 재사용
	}

}
