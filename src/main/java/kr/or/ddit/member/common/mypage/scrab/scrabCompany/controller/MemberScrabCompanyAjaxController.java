package kr.or.ddit.member.common.mypage.scrab.scrabCompany.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.member.common.mypage.scrab.scrabCompany.service.MemberScrabCompanyService;
import kr.or.ddit.vo.common.ScrabCompanyVO;
import lombok.RequiredArgsConstructor;

// 일반회원의 마이페이지 스크랩 중 관심기업 조회
@RestController
@RequestMapping("/ajax/member/common/mypage/scrab/scrabCompany")
@RequiredArgsConstructor
public class MemberScrabCompanyAjaxController {

	private final MemberScrabCompanyService service;
	
	// 관심 기업 목록조회
	@GetMapping
	public List<ScrabCompanyVO> getAll(){
		return service.readScrabCompanyList();
	}
	
	// 관심 기업 단건조회
	@GetMapping("/{companyId}")
	public ResponseEntity<ScrabCompanyVO> getOneSCompany(@RequestBody String companyId) {
	    return service.searchScrabCompanyByComId(companyId)
	    		.map(ResponseEntity::ok)  //scompany 있으면 ok 반환
	            .orElse(ResponseEntity.status(404).body(null));  //없을 시 js에서 처리(상태코드 404 객체 반환)
	}
	
	// 관심 기업 마이페이지 내 등록
	@PostMapping("/{scompany}")
	public Map<String, Object> inSCompany(
		@RequestBody ScrabCompanyVO scompany
	) {
		service.createScrabCompany(scompany);
	    return Map.of("ok", true);
	}
	
	// 관심 기업 마이페이지 내 수정 - 삭제, 등록만 있으면 안 쓸 듯
	@PutMapping("/{scompany}")
	public Map<String, Object> editSCompany(
		@RequestBody ScrabCompanyVO scompany
	) {
	    service.modifyScrabCompany(scompany);
	    return Map.of("ok", true);
	}
	
	// 관심 기업 마이페이지 내 삭제
	@DeleteMapping("/{scompany}")
	public Map<String, Object> deleteSCompany(
		@PathVariable ScrabCompanyVO scompany	
	) {
		service.removeScrabCompany(scompany);
		return Map.of("ok", true);
	}
}




