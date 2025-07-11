package kr.or.ddit.member.community.commuBoard.controller;

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

import kr.or.ddit.member.community.commuBoard.service.MemberCommuBoardService;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.community.CommuBoardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ajax/member/board/commu_board")
@RequiredArgsConstructor
public class MemberCommuBoardAjaxController {

	private MemberCommuBoardService service;
	private ErrorsUtils errorsUtils;  //검증 추가해야 함
	
	@GetMapping("/{categoryCode}/{commuPostNo}")
	public ResponseEntity<CommuBoardVO> getOneBoard(@PathVariable String commuPostNo) {
	    return service.readCommuBoard(commuPostNo)
//	    		.map(ResponseEntity::ok)
	    		.map(cp->ResponseEntity.ok(cp))  //commuPostNo 있으면 ok 반환
	            .orElse(ResponseEntity.status(404).body(null));  //없을 시 js에서 처리(상태코드 404 객체 반환)
	}
	
	@GetMapping("/{categoryCode}")
	public List<CommuBoardVO> getBoards(@PathVariable String categoryCode){
		return service.readCommuBoardList(categoryCode);
	}
		
	@PostMapping("/{categoryCode}")
	public Map<String, Object> inBoard(
			@PathVariable String categoryCode
			, @RequestBody CommuBoardVO board
	) {
		service.createCommuBoard(board);
	    return Map.of("ok", true);
	}
	
	@PutMapping("/{categoryCode}/{commuPostNo}")
	public Map<String, Object> editBoard(
		@PathVariable String categoryCode
		, @PathVariable String commuPostNo
		,  @RequestBody CommuBoardVO board
	) {
		board.setCommuPostNo(commuPostNo);
	    service.modifyCommuBoard(board);
	    return Map.of("ok", true);	// 수정 후 Detail 이동
	}
	
	@DeleteMapping("/{categoryCode}/{commuPostNo}")
	public Map<String, Object> deleteBoard(
		@PathVariable String categoryCode
		, @PathVariable String commuPostNo	
	) {
		service.removeCommuBoard(commuPostNo);
		return Map.of("ok", true);
	}
}
