package kr.or.ddit.member.project.announcement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public List<PrjAnncBbsVO> getProjectBoardList() {
		return service.prjAnncBbsList();
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
