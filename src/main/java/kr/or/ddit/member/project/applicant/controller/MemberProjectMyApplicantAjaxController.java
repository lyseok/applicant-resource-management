package kr.or.ddit.member.project.applicant.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.member.project.announcement.service.MemberProjectAnnouncememtService;
import kr.or.ddit.vo.project.PrjAnncBbsVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ajax/mypage/project/my_applicant")
public class MemberProjectMyApplicantAjaxController {
	private final MemberProjectAnnouncememtService service;
	
	@GetMapping
	public List<PrjAnncBbsVO> getMyApplicantPrjAnncBbsList() {
		return service.myApplicantPrjAnncBbsList();
	}
}
