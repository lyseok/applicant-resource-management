package kr.or.ddit.company.recruitment.talentpool.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.company.recruitment.talentpool.service.TalentPoolService;
import kr.or.ddit.vo.resume.ResumeVO;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ajax/company/talentpool")
@RequiredArgsConstructor
public class AjaxTalentPoolController {
	private final TalentPoolService poolService;
	
	@GetMapping("/filter")
	public ResponseEntity<List<ResumeVO>> getFilterResumeList(
		@RequestParam(required = false) List<String> careerJobCodeList,
		@RequestParam(required = false) List<String> careerYearList,
		@RequestParam(required = false) List<String> careerGradeList,
		@RequestParam(required = false) List<String> licenseCodeList,
		@RequestParam(required = false) List<String> skillCodeList,
		@RequestParam(required = false) String languageName,
		@RequestParam(required = false) Integer languageScoreMin,
		@RequestParam(required = false) String educationCode,
		@RequestParam(required = false) String majorName	
	){
		Map<String, Object> params = new HashMap<>();
		params.put("careerJobCodeList", careerJobCodeList);
		params.put("careerYearList", careerYearList);
		params.put("careerGradeList", careerGradeList);
		params.put("licenseCodeList", licenseCodeList);
		params.put("languageName", languageName);
		params.put("languageScoreMin", languageScoreMin);
		params.put("educationCode", educationCode);
		params.put("majorName", majorName);
		params.put("skillCodeList", skillCodeList);
		
		List<ResumeVO> resp = poolService.readResumeByFilter(params);
		return ResponseEntity.ok(resp);
	}
}
