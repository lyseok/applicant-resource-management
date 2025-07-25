package kr.or.ddit.member.common.mypage.scrab.scrabCompany.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.member.common.mypage.scrab.scrabCompany.service.MemberScrabCompanyService;
import kr.or.ddit.vo.common.ScrabCompanyVO;
import lombok.RequiredArgsConstructor;

// 일반회원의 마이페이지 스크랩 중 관심기업 조회
@RestController
@RequestMapping("/ajax/member/scrab_company")
@RequiredArgsConstructor
public class MemberScrabCompanyAjaxController {

	private final MemberScrabCompanyService service;

	@GetMapping("/my")
	public ResponseEntity<List<ScrabCompanyVO>> getAll() {
		return ResponseEntity.ok(service.readMyScrabCompanyList());
	}

	@PostMapping("/{scompany}")
	public ResponseEntity<?> inSCompany(@PathVariable String scompany) {
		service.createScrabCompany(scompany);
		return ResponseEntity.ok("ok");
	}

	@DeleteMapping("/{scompany}")
	public ResponseEntity<?> deleteSCompany(@PathVariable String scompany) {
		service.removeScrabCompany(scompany);
		return ResponseEntity.ok("ok");
	}
}
