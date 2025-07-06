package kr.or.ddit.admin.community.controller;

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

import kr.or.ddit.admin.community.service.AdminAdminBoardAjaxService;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.community.AdminBoardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ajax/admin/adminBoard")
@RequiredArgsConstructor
public class AdminAdminBoardAjaxController {
	
	private AdminAdminBoardAjaxService service;
	private ErrorsUtils errorsUtils;  //검증 추가해야 함
	
	@GetMapping("/{boardTypeCode}/{boardNo}")
	@ResponseBody
	public ResponseEntity<AdminBoardVO> getOneBoard(@PathVariable String boardNo) {
	    return service.readAdminBoard(boardNo)
//	    		.map(ResponseEntity::ok)
	    		.map(ab->ResponseEntity.ok(ab))  //boardNo 있으면 ok 반환
	            .orElse(ResponseEntity.status(404).body(null));  //없을 시 js에서 처리(상태코드 404 객체 반환)
	}
	
	@GetMapping("/{boardTypeCode}")
	public List<AdminBoardVO> getBoards(@PathVariable String boardTypeCode){
		return service.readAdminBoardList(boardTypeCode);
	}
	
	@PostMapping("/{boardTypeCode}")
	public Map<String, Object> inBoard(
			@PathVariable String boardTypeCode
			, @RequestBody AdminBoardVO board
	) {
		service.createAdminBoard(board);
	    return Map.of("ok", true);
	}
	
	@PutMapping("/{boardTypeCode}/{boardNo}")
	public Map<String, Object> editBoard(
		@PathVariable String boardTypeCode
		, @PathVariable String boardNo
		,  @RequestBody AdminBoardVO board
	) {
		board.setBoardNo(boardNo);
	    service.modifyAdminBoard(board);
	    return Map.of("ok", true);	// 수정 후 Detail 이동
	}
	
	@DeleteMapping("/{boardTypeCode}/{boardNo}")
	public Map<String, Object> deleteBoard(
		@PathVariable String boardTypeCode
		, @PathVariable String boardNo	
	) {
		service.removeAdminBoard(boardNo);
		return Map.of("ok", true);
	}
}
