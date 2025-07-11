package kr.or.ddit.admin.community.inBoard.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.admin.community.inBoard.service.AdminInBoardService;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.community.InBoardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ajax/admin/board/in_board")
@RequiredArgsConstructor
public class AdminInBoardAjaxController {

	private AdminInBoardService service;
	private ErrorsUtils errorsUtils;  //검증 추가해야 함
	
	@GetMapping("/{commuPostNo}/{avatarId}")
	public ResponseEntity<InBoardVO> getOneInboard(
		@PathVariable String commuPostNo
		, @PathVariable String avatarId
	) {
	    return service.readInBoardByPk(commuPostNo, avatarId)
//	    		.map(ResponseEntity::ok)
	    		.map(ib->ResponseEntity.ok(ib))  //있으면 ok 반환
	            .orElse(ResponseEntity.status(404).body(null));  //없으면 404 반환
	}
	
	@GetMapping("/{avatarId}")
	public List<InBoardVO> getPosts(@PathVariable String avatarId){
		return service.searchInBoardCommuPostList(avatarId);
	}
	
	@GetMapping("/{commuPostNo}")
	public List<InBoardVO> getAvatars(@PathVariable String commuPostNo){
		return service.searchInBoardAvatarList(commuPostNo);
	}
	
	@GetMapping
	public List<InBoardVO> getAll(){
		return service.readInBoardList();
	}
	
	@PostMapping("/{commuPostNo}")
	public Map<String, Object> intag(@RequestBody InBoardVO tag) {
		service.createInBoard(tag);
	    return Map.of("ok", true);
	}

	@DeleteMapping("/{commuPostNo}/{avatarId}")
	public Map<String, Object> deletetag(
		@PathVariable String commuPostNo
		, @PathVariable String avatarId	
	) {
		service.removeInBoard(commuPostNo, avatarId);
		return Map.of("ok", true);
	}
}
