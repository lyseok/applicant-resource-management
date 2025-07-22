package kr.or.ddit.member.recruitment.recruitView.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import kr.or.ddit.company.recruitment.notice.service.RecruitService;
import kr.or.ddit.dto.RecruitViewDTO;
import kr.or.ddit.member.recruitment.recruitView.service.RecruitViewService;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.recruitment.RecruitViewVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ajax/recruit_view")
public class RecruitViewAjaxController {

	private final RecruitViewService service;

	@GetMapping("/list")
	public List<RecruitViewDTO> getRecruitViewList(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();
		List<RecruitViewDTO> viewList = service.readRecruitViewList(userId);
		log.info("list 잘 담겼나 확인 >>>>>>>>>>>>>>>>> {}", viewList);
		return viewList;
	}
	
	@PostMapping("/{no}")
	public ResponseEntity<?> insertRecruitView(
		@PathVariable String no
	){
		log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> {}", no);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();
		RecruitViewVO view = new RecruitViewVO();
		view.setRecruitmentNo(no);
		view.setUserId(userId);		
		service.createtRecruitView(view);
		return ResponseEntity.ok(view);
	}
	
}