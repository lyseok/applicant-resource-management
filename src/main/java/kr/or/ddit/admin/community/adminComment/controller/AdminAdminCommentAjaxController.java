package kr.or.ddit.admin.community.adminComment.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.or.ddit.admin.community.adminComment.service.AdminAdminCommentAjaxService;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.community.AdminBoardVO;
import kr.or.ddit.vo.community.AdminCommentVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ajax/admin/board/admin_comment")
@RequiredArgsConstructor
public class AdminAdminCommentAjaxController {
	
	private final AdminAdminCommentAjaxService service;
	private final ErrorsUtils errorsUtils;  //검증 추가해야 함
	
	@GetMapping("/detail/{boardCommentNo}")
	public ResponseEntity<AdminCommentVO> getOneComment(@PathVariable String commentNo) {
	    return service.readAdminCommentbyPk(commentNo)
	    		.map(ResponseEntity::ok)
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
	
	// 수정, 삭제 상태 변경
	@PostMapping("/detail/{boardCommentNo}")
	public Map<String, Object> editComment(
		@PathVariable String boardNo
		, @PathVariable String commentNo
		,  @RequestBody AdminCommentVO comment
	) {
		comment.setBoardCommentNo(commentNo);
	    service.modifyAdminComment(comment);
	    return Map.of("ok", true);	// 수정 후 Detail 이동
	}
	

	//에러 검증
	@PostMapping("/check")
	public ResponseEntity<?> saveAcomment(
		@Valid @RequestBody AdminCommentVO vo
		, BindingResult bindingResult
	) {
		log.info("{}", vo);
		
		if(bindingResult.hasErrors()) {
			MultiValueMap<String, String> errors = errorsUtils.errorsToMap(bindingResult);
			return ResponseEntity.badRequest().body(errors);
		}
		
	    return ResponseEntity.ok("ok");
	}
}
