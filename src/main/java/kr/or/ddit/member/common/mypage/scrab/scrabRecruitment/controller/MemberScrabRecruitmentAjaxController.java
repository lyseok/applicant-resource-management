package kr.or.ddit.member.common.mypage.scrab.scrabRecruitment.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.member.common.mypage.scrab.scrabRecruitment.service.MemberScrabRecruitmentService;
import kr.or.ddit.vo.common.ScrabRecruitmentVO;
import lombok.RequiredArgsConstructor;

// 일반회원의 마이페이지 스크랩 중 관심공고 조회
@RestController
@RequestMapping("/ajax/member/scrab_recruit")
@RequiredArgsConstructor
public class MemberScrabRecruitmentAjaxController {

	private final MemberScrabRecruitmentService service;

	@GetMapping("/my")
	public ResponseEntity<List<ScrabRecruitmentVO>> getMyList() {
		return ResponseEntity.ok(service.readMyScrabRecruitmentList());
	}
	
	@GetMapping("/{recruitmentNo}")
	public int findRecruitScrabYn(@PathVariable String recruitmentNo) {
		return service.findRecruitScrabYn(recruitmentNo);
	}

	// 관심 공고 마이페이지 내 등록
	@PostMapping("/{srecruit}")
	public ResponseEntity<?> createScrabRecruit(@PathVariable String srecruit){
		service.createScrabRecruitment(srecruit);
		return ResponseEntity.ok("ok");
	}

	// 관심 공고 마이페이지 내 삭제
	@DeleteMapping("/{srecruit}")
	public ResponseEntity<?> removeScrabRecruit(@PathVariable String srecruit){
		service.removeScrabRecruitment(srecruit);
		return ResponseEntity.ok("ok");
	}
	
}
