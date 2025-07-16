package kr.or.ddit.admin.community.adminBoard.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.or.ddit.admin.community.adminBoard.service.AdminAdminBoardAjaxService;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.community.AdminBoardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ajax/admin/board/admin_board")
@RequiredArgsConstructor
public class AdminAdminBoardAjaxController {
	
	private final AdminAdminBoardAjaxService service;

    // 해당 유형의 해당 글의 게시글 단건조회
	@GetMapping("/detail/{boardNo}")
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
	
	// 해당 유형의 등록
	//boardForm.jsp에서 [등록]버튼 클릭 시 수행
	/*
	1.할아버지 [AdminBoardVO] : userId, boardTypeCode, boardTitle, boardContent
	2.첫째 아빠 [AdminBoardVO.cmnCodeGroupVOList[0]] : codeGroupNo
	3.첫째 딸 [AdminBoardVO.cmnCodeGroupVOList[0].cmnCodeList[0]] : codeDetailNo(=memType)
	*/
	@PostMapping("/{boardTypeCode}")
	public Map<String, Object> inBoard(
		@PathVariable String boardTypeCode
		, @RequestBody AdminBoardVO board
	) {
		/*
		할아버지AdminBoardVO(boardNo=null, userId=testAdmin, boardTypeCode=BRDD-002, boardTitle=제목 연습, boardWriteDate=null, boardContent=내용 연습, boardDeleteDate=null, boardPostHit=null, boardStatus=null, codeName=null, users=null, adminCommentList=null, 
		cmnCodeGroupVOList=[
			첫째아빠CmnCodeGroupVO(codeGroupNo=UFAQ, codeGroupName=null, description=null, useYn=null, crateDate=null, updateDate=null, 
				cmnCodeList=[
					첫째딸CmnCodeVO(codeDetailNo=UFAQ-U1, codeGroupNo=null, upperCodeNo=null, codeName=null, sortOrder=null, useYn=null, crateDate=null, updateDate=null)
				])
		])
		 */
		log.info("찍힘 확인 : {}", board);
		
		service.createAdminBoard(board);
		//boardNo를 확인해보자 : 
		log.info("board : {}", board);
		
	    return Map.of("ok", true);
	}
	
	// 해당 유형의 해당 글의 게시글 수정, 삭제 상태 변경
	@PostMapping("/detail/{boardNo}")
	public Map<String, Object> editBoard(
		@PathVariable String boardTypeCode
		, @PathVariable String boardNo
		,  @RequestBody AdminBoardVO board
	) {
		board.setBoardNo(boardNo);
	    service.modifyAdminBoard(board);
	    return Map.of("ok", true);	// 수정 후 Detail 이동
	}
	
	//에러 검증
	private final ErrorsUtils errorsUtils; // <- 주입 받고

	@PostMapping("/check")
	public ResponseEntity<?> saveAboard(
		@Valid @RequestBody AdminBoardVO vo
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
