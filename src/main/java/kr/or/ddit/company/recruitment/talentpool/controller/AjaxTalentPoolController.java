package kr.or.ddit.company.recruitment.talentpool.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.company.recruitment.talentpool.service.TalentPoolService;
import kr.or.ddit.dto.MailDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ajax/company/talentpool")
@RequiredArgsConstructor
public class AjaxTalentPoolController {
	private final TalentPoolService poolService;
	
	@GetMapping("/filter")
	public ResponseEntity<Map<String, Object>> getFilterResumeList(
		@RequestParam int page,
	    @RequestParam int pageSize,
		@RequestParam(required = false) List<String> careerJobCodeList,
		@RequestParam(required = false) List<String> careerYearList,
		@RequestParam(required = false) List<String> careerGradeList,
		@RequestParam(required = false) List<String> licenseCodeList,
		@RequestParam(required = false) List<String> skillCodeList,
		@RequestParam(required = false) String languageName,
		@RequestParam(required = false) Integer languageScoreMin,
		@RequestParam(required = false) String educationCode,
		@RequestParam(required = false) String majorName,
		@RequestParam(required = false) String keyWord
	){
		Map<String, Object> params = new HashMap<>();
		params.put("startRow", (page - 1) * pageSize);
		params.put("endRow", page * pageSize);
		params.put("careerJobCodeList", careerJobCodeList);
		params.put("careerYearList", careerYearList);
		params.put("careerGradeList", careerGradeList);
		params.put("licenseCodeList", licenseCodeList);
		params.put("languageName", languageName);
		params.put("languageScoreMin", languageScoreMin);
		params.put("educationCode", educationCode);
		params.put("majorName", majorName);
		params.put("skillCodeList", skillCodeList);
		
		Map<String, Object> resp = poolService.readResumeByFilter(params);
		return ResponseEntity.ok(resp);
	}
	
	@GetMapping("company-scrab")
	public ResponseEntity<Map<String, Object>> getScrabResumeList(
			@RequestParam int page,
		    @RequestParam int pageSize
		){
			Map<String, Object> params = new HashMap<>();
			params.put("startRow", (page - 1) * pageSize);
			params.put("endRow", page * pageSize);
			
			Map<String, Object> resp = poolService.readResumeByMyScrab(params);
			return ResponseEntity.ok(resp);
		}
	
	@GetMapping("savelist")
	public List<String> getSavedTalentList() {
        return poolService.getSavedTalentList();
    }
	
	@PostMapping("savelist")
	public ResponseEntity<Void> postTalentpoolSaveList(@RequestBody Map<String, Object> reqData) {

	    // 요청 데이터에서 리스트 추출
	    List<String> addList = (List<String>) reqData.getOrDefault("addList", List.of());
	    List<String> removeList = (List<String>) reqData.getOrDefault("removeList", List.of());

	    poolService.updateTalentList(addList, removeList);

	    return ResponseEntity.ok().build();
	}
	
	@GetMapping("setupdata")
	public ResponseEntity<Map<String, Object>> getSetupDataList() {
		Map<String, Object> resp = poolService.readSetupList();
		return ResponseEntity.ok(resp);
	}
	
	@PostMapping("/joboffer")
	public ResponseEntity<?> postMailByTalentList(@RequestBody List<MailDTO> reqData){
		log.info("========> {}", reqData);
		
		poolService.postMailLogic(reqData);

		return ResponseEntity.ok("ok");
	}
	
	@PostMapping("/{userId}")
	public ResponseEntity<?> updateResumeConfirm(@PathVariable String userId){
		poolService.updateResumeConfirm(userId);
		
		return ResponseEntity.ok("ok");
	}
}
