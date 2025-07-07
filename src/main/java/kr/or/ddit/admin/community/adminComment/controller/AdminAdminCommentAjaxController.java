package kr.or.ddit.admin.community.adminComment.controller;

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

import kr.or.ddit.admin.community.adminComment.service.AdminAdminCommentAjaxService;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.community.AdminCommentVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ajax/admin/adminComment")
@RequiredArgsConstructor
public class AdminAdminCommentAjaxController {
	
	private AdminAdminCommentAjaxService service;
	private ErrorsUtils errorsUtils;  //검증 추가해야 함
	
	@GetMapping("/{boardNo}/{boardCommentNo}")
	public ResponseEntity<AdminCommentVO> getOneComment(@PathVariable String commentNo) {
	    return service.readAdminCommentbyPk(commentNo)
//	    		.map(ResponseEntity::ok)
	    		.map(ac->ResponseEntity.ok(ac))  //commentNo 있으면 ok 반환
	            .orElse(ResponseEntity.status(404).body(null));  //없을 시 상태코드 404 객체 반환
	}
	
	@GetMapping("/{boardNo}")
	public List<AdminCommentVO> getComments(@PathVariable String boardNo){
		return service.searchAdminCommentCommentList(boardNo);
	}
	
	@GetMapping
	public List<AdminCommentVO> getAllComments(){
		return service.searchAdminCommentList();
	}
	
	@PostMapping("/{boardNo}")
	public Map<String, Object> inComment(
			@PathVariable String boardNo
			, @RequestBody AdminCommentVO comment
	) {
		service.createAdminComment(comment);
	    return Map.of("ok", true);
	}
	
	@PutMapping("/{boardNo}/{boardCommentNo}")
	public Map<String, Object> editComment(
		@PathVariable String boardNo
		, @PathVariable String commentNo
		,  @RequestBody AdminCommentVO comment
	) {
		comment.setBoardCommentNo(commentNo);
	    service.modifyAdminComment(comment);
	    return Map.of("ok", true);	// 수정 후 Detail 이동
	}
	
	@DeleteMapping("/{boardNo}/{boardCommentNo}")
	public Map<String, Object> deleteComment(
		@PathVariable String boardNo
		, @PathVariable String commentNo	
	) {
		service.removeAdminComment(commentNo);
		return Map.of("ok", true);
	}
}
