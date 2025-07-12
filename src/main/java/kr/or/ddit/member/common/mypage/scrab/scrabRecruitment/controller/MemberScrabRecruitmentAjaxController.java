package kr.or.ddit.member.common.mypage.scrab.scrabRecruitment.controller;

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

import kr.or.ddit.member.common.mypage.scrab.scrabRecruitment.service.MemberScrabRecruitmentService;
import kr.or.ddit.vo.common.ScrabRecruitmentVO;
import lombok.RequiredArgsConstructor;

// 일반회원의 마이페이지 스크랩 중 관심공고 조회
@RestController
@RequestMapping("/ajax/member/mypage/scrab_recruit")
@RequiredArgsConstructor
public class MemberScrabRecruitmentAjaxController {

	private final MemberScrabRecruitmentService service;
	
	// 관심 공고 목록조회
	@GetMapping
	public List<ScrabRecruitmentVO> getAll(){
		return service.readScrabRecruitmentList();
	}
	
	// 관심 공고 단건조회
	@GetMapping("/{srecruit}")  //String이 아니라 vo를 넣었으니 확인필요
	public ResponseEntity<ScrabRecruitmentVO> getOneSRecruit(@RequestBody ScrabRecruitmentVO srecruit) {
	    return service.searchScrabRecruitmentByPk(srecruit)
	    		.map(ResponseEntity::ok)  //srecruit 있으면 ok 반환
	            .orElse(ResponseEntity.status(404).body(null));  //없을 시 js에서 처리(상태코드 404 객체 반환)
	}
	
	// 관심 공고 마이페이지 내 등록
	@PostMapping("/{srecruit}")
	public Map<String, Object> inSRecruit(
		@RequestBody ScrabRecruitmentVO srecruit
	) {
		service.createScrabRecruitment(srecruit);
	    return Map.of("ok", true);
	}
	
	// 관심 공고 마이페이지 내 수정 - 삭제, 등록만 있으면 안 쓸 듯
	@PutMapping("/{srecruit}")
	public Map<String, Object> editSRecruit(
		@RequestBody ScrabRecruitmentVO srecruit
	) {
	    service.modifyScrabRecruitment(srecruit);
	    return Map.of("ok", true);
	}
	
	// 관심 공고 마이페이지 내 삭제
	@DeleteMapping("/{srecruit}")
	public Map<String, Object> deleteSRecruit(
		@PathVariable ScrabRecruitmentVO srecruit	
	) {
		service.removeScrabRecruitment(srecruit);
		return Map.of("ok", true);
	}
}




