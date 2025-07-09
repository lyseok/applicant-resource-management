package kr.or.ddit.admin.community.adminBoard.controller;

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

import kr.or.ddit.admin.common.codegroup.service.AdminCmnCodeGroupAjaxService;
import kr.or.ddit.admin.community.adminBoard.service.AdminAdminBoardAjaxService;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.common.CmnCodeGroupVO;
import kr.or.ddit.vo.community.AdminBoardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ajax/admin/adminBoard")
@RequiredArgsConstructor
public class AdminAdminBoardAjaxController {
	
	private final AdminAdminBoardAjaxService service;
	private final AdminCmnCodeGroupAjaxService cservice;
	
    // codeGroupNo 파라미터를 받아 단건 조회
    @GetMapping("/cmncodegroup/{no}")  //http://localhost/ajax/admin/adminBoard/cmncodegroup/BRDD 로 cmnCodeList가 나옴(공지/faq/문의)
    public CmnCodeGroupVO cmnCodeGroup(@PathVariable("no") String no) {
        return cservice.readCmnCodeGroupByPk(no);
    }

	@GetMapping("/{boardTypeCode}/{boardNo}")
	public ResponseEntity<AdminBoardVO> getOneBoard(@PathVariable String boardNo) {
	    return service.readAdminBoardByPk(boardNo)
	    		.map(ResponseEntity::ok)  //boardNo 있으면 ok 반환
	            .orElse(ResponseEntity.status(404).body(null));  //없을 시 js에서 처리(상태코드 404 객체 반환)
	}
	
	@GetMapping("/{boardTypeCode}")
	public List<AdminBoardVO> getBoards(@PathVariable String boardTypeCode){
		return service.readAdminBoardListByType(service.matchBoardTypeCode(boardTypeCode));
	}
	
	@GetMapping
	public List<AdminBoardVO> getAll(){
		return service.readAdminBoardList();
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
