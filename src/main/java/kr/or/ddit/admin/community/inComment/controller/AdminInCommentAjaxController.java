package kr.or.ddit.admin.community.inComment.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.admin.community.inComment.service.AdminInCommentService;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.community.InCommentVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ajax/admin/board/in_comment")
@RequiredArgsConstructor
public class AdminInCommentAjaxController {

	private final AdminInCommentService service;
	private final ErrorsUtils errorsUtils;  //검증 추가해야 함
	
	@GetMapping("/{commentNo}/{avatarId}")
	public ResponseEntity<InCommentVO> getOneInComment(
		@PathVariable String commentNo
		, @PathVariable String avatarId
	) {
	    return service.readInCommentByPk(commentNo, avatarId)
	    		.map(ResponseEntity::ok)
	            .orElse(ResponseEntity.status(404).body(null));  //없으면 404 반환
	}
	
	@GetMapping("/{avatarId}")
	public List<InCommentVO> getComments(@PathVariable String avatarId){
		return service.searchInCommentCommentList(avatarId);
	}
	
	@GetMapping("/{commentNo}")
	public List<InCommentVO> getAvatars(@PathVariable String commentNo){
		return service.searchInCommentAvatarList(commentNo);
	}
	
	@GetMapping
	public List<InCommentVO> getAll(){
		return service.readInCommentList();
	}
	
	@PostMapping("/{commentNo}")
	public Map<String, Object> intag(@RequestBody InCommentVO comment) {
		service.createInComment(comment);
	    return Map.of("ok", true);
	}

	@DeleteMapping("/{commentNo}/{avatarId}")
	public Map<String, Object> deletetag(
		@PathVariable String commentNo
		, @PathVariable String avatarId	
	) {
		service.removeInComment(commentNo, avatarId);
		return Map.of("ok", true);
	}
}
