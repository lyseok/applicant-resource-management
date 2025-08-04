package kr.or.ddit.member.project.announcement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.or.ddit.member.project.announcement.service.MemberProjectAnnouncememtService;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.project.PrjAnncBbsVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/ajax/board/project")
public class MemberProjectAnnouncementAjaxController {
	private final MemberProjectAnnouncememtService service;
	private final ErrorsUtils errorsUtils;
	
	@GetMapping
	public Map<String, Object> getProjectBoardList(@RequestParam Map<String, Object> params, @RequestParam(required = false) List<String> tagList) {
		params.put("tagList", tagList);
		log.info("============> {}", params);
		return service.prjAnncBbsList(params);
	}
	
	@GetMapping("/my")
	public List<PrjAnncBbsVO> getMyProjectBoardList() {
		return service.myPrjAnncBbsList();
	}
	
	@GetMapping("/{no}")
	public PrjAnncBbsVO getProjectBoard(@PathVariable String no) {
		return service.readPrjAnncBbs(no);
	}
	
	@GetMapping("/{no}/applicant")
	public PrjAnncBbsVO getProjectApplicant(@PathVariable String no) {
		return service.readPrjAnncBbsApplicant(no);
	}
	
	@GetMapping("/applicantList")
	public List<PrjAnncBbsVO> getMyApplicantList() {
		return service.myApplicantPrjAnncBbsList();
	}
	
	@PostMapping
	public ResponseEntity<?> createProjectBoard(@Valid @RequestBody PrjAnncBbsVO prjAnncBbs, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			MultiValueMap<String, String> errors = errorsUtils.errorsToMap(bindingResult);
			return ResponseEntity.badRequest().body(errors);
		}
		service.createPrjAnncBbs(prjAnncBbs);
		return ResponseEntity.ok("ok");
	}
}
