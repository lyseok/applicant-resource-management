package kr.or.ddit.admin.community.adminBoard.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.or.ddit.admin.common.codegroup.service.AdminCmnCodeGroupAjaxService;
import kr.or.ddit.admin.community.adminBoard.service.AdminAdminBoardAjaxService;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.common.CmnCodeGroupVO;
import kr.or.ddit.vo.community.AdminBoardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ajax/admin/community/adminBoard")
@RequiredArgsConstructor
public class AdminAdminBoardAjaxController {
	
	private final AdminAdminBoardAjaxService service;
	private final AdminCmnCodeGroupAjaxService cservice;
	
    // codeGroupNo 파라미터(BRDD, UFAQ, CFAQ)를 받아 
	// codeDetailNo 목록(BRDD-001, BRDD-002, BRDD-003...)과 codeName("문의사항", "FAQ", "공지사항"...) 조회
    @GetMapping("/cmncodegroup/{no}")  //http://localhost/ajax/admin/adminBoard/cmncodegroup/BRDD
    public CmnCodeGroupVO cmnCodeGroup(@PathVariable("no") String no) {
        return cservice.readCmnCodeGroupByPk(no);
    }

    // 해당 유형의 해당 글의 게시글 단건조회
	@GetMapping("/{boardTypeCode}/{boardNo}")
	public ResponseEntity<AdminBoardVO> getOneBoard(@PathVariable String boardNo) {
	    return service.readAdminBoardByPk(boardNo)
	    		.map(ResponseEntity::ok)  //boardNo 있으면 ok 반환
	            .orElse(ResponseEntity.status(404).body(null));  //없을 시 js에서 처리(상태코드 404 객체 반환)
	}
	
	// 유형별 게시글 목록조회
	@GetMapping("/{boardTypeCode}")
	public List<AdminBoardVO> getBoards(@PathVariable String boardTypeCode){
		return service.readAdminBoardListByType(boardTypeCode);
	}

	// 전체 게시글 목록조회
	@GetMapping
	public List<AdminBoardVO> getAll(){
		return service.readAdminBoardList();
	}
	
	// 해당 유형의 등록
	@PostMapping("/{boardTypeCode}")
	public Map<String, Object> inBoard(
		@PathVariable String boardTypeCode
		, @RequestBody AdminBoardVO board
	) {
		String boardNo = service.createAdminBoard(board);  //boardNo 받아서 뷰에서 사용
	    return Map.of("ok", true, "boardNo", boardNo);
	}
	
	// 해당 유형의 해당 글의 게시글 수정
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
	
	// 해당 유형의 해당 글의 게시글 삭제
	@DeleteMapping("/{boardTypeCode}/{boardNo}")
	public Map<String, Object> deleteBoard(
		@PathVariable String boardTypeCode
		, @PathVariable String boardNo	
	) {
		service.removeAdminBoard(boardNo);
		return Map.of("ok", true);
	}

}
