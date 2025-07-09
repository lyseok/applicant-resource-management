package kr.or.ddit.company.recruitment.skill.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.or.ddit.company.recruitment.skill.service.RecruitSkillService;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.recruitment.RecruitmentSkillVO;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ajax/recruit")
public class RecruitSkillAjaxController {

	private final RecruitSkillService service;
	private final ErrorsUtils errorsUtils;
	
	@PostMapping("/{recruitmentNo}/Skills")
	public ResponseEntity<?> insertSkills(
		@PathVariable String recruitmentNo
		, @Valid @RequestBody List<RecruitmentSkillVO> skills
		, BindingResult bindingResult
	){
		if(bindingResult.hasErrors()) {
			MultiValueMap<String, String> errors = errorsUtils.errorsToMap(bindingResult);
			return ResponseEntity.badRequest().body(errors);
		}
		
		for(RecruitmentSkillVO skill : skills) {
			skill.setRecruitmentNo(recruitmentNo);
			service.createRecruitSkill(skill);
		}
		
		return ResponseEntity.ok("ok");
	}
}
