package kr.or.ddit.member.community.commuComment.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.member.community.commuComment.service.MemberCommuCommentService;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.community.CommuCommentVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ajax/member/board/commu_comment")
@RequiredArgsConstructor
public class MemberCommuCommentAjaxController {

	private final MemberCommuCommentService service;
	private final ErrorsUtils errorsUtils;  //검증 추가해야 함
	
	@GetMapping("/{commuPostNo}/{commuCommentNo}")
	public ResponseEntity<CommuCommentVO> getOneComment(@PathVariable String commuCommentNo) {
	    return service.readCommuCommentbyPk(commuCommentNo)
	    		.map(ResponseEntity::ok)
	            .orElse(ResponseEntity.status(404).body(null));  //없을 시 js에서 처리(상태코드 404 객체 반환)
	}
	
	@GetMapping("/{commuPostNo}")
	public List<CommuCommentVO> getComments(@PathVariable String commuPostNo){
		return service.searchCommuCommentPostList(commuPostNo);
	}
	
	@GetMapping
	public List<CommuCommentVO> getAllComments(){
		return service.searchCommuCommentList();
	}
	
	@PostMapping("/{commuPostNo}")
	public Map<String, Object> inComment(
			@PathVariable String commuPostNo
			, @RequestBody CommuCommentVO comment
	) {
		service.createCommuComment(comment);
	    return Map.of("ok", true);
	}
	
	@PutMapping("/{commuPostNo}/{commuCommentNo}")
	public Map<String, Object> editComment(
		@PathVariable String commuPostNo
		, @PathVariable String commuCommentNo
		,  @RequestBody CommuCommentVO comment
	) {
		comment.setCommuCommentNo(commuCommentNo);
	    service.modifyCommuComment(comment);
	    return Map.of("ok", true);	// 수정 후 Detail 이동
	}
	
	@DeleteMapping("/{commuPostNo}/{commuCommentNo}")
	public Map<String, Object> deleteComment(
		@PathVariable String commuPostNo
		, @PathVariable String commuCommentNo	
	) {
		service.removeCommuComment(commuCommentNo);
		return Map.of("ok", true);
	}
}
