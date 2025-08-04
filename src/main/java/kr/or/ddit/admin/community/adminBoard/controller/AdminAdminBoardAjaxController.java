package kr.or.ddit.admin.community.adminBoard.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.time.format.DateTimeFormatter;

import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.or.ddit.admin.common.users.service.AdminUsersService;
import kr.or.ddit.admin.community.adminBoard.service.AdminAdminBoardAjaxService;
import kr.or.ddit.conf.CodeMapProvider;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.common.CmnCodeGroupVO;
import kr.or.ddit.vo.common.CmnCodeVO;
import kr.or.ddit.vo.common.CompanyVO;
import kr.or.ddit.vo.common.MemberVO;
import kr.or.ddit.vo.common.UsersVO;
import kr.or.ddit.vo.community.AdminBoardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ajax/admin/board/admin_board")
@RequiredArgsConstructor
public class AdminAdminBoardAjaxController {
	
	private final AdminAdminBoardAjaxService service;
	private final AdminUsersService userService;
	private final CodeMapProvider codeMapProvider;
	
    // 해당 유형의 해당 글의 게시글 단건조회, 해당 게시글의 유형 코드에 맞는 이름 가져옴
	@GetMapping("/detail/{boardNo}")
	public ResponseEntity<AdminBoardVO> getOneBoard(@PathVariable String boardNo) {
	    return service.readAdminBoardByPk(boardNo)
	        .map(board -> {
	            String boardTypeCode = board.getBoardTypeCode();

	            // 1. 캐시에 있는지(즉, codeMapProvider를 쓸수있는지) 우선 조회
	            String codeName = null;
	            if (boardTypeCode != null) {
	                codeName = codeMapProvider.getCodeName(boardTypeCode);
	            }

	            // 2. 캐시에 없으면(즉, codeMapProvider를 쓸수없으면) DB 직접 조회
	            if (codeName == null && boardTypeCode != null && !boardTypeCode.isBlank()) {
	                String dbCodeName = service.readBoardTypeName(boardTypeCode);
	                board.setCodeName(dbCodeName); // 성공 시 설정
	            } else {
	                board.setCodeName(codeName); // 캐시 결과 설정
	            }

	            return ResponseEntity.ok(board);
	        })
	        .orElse(ResponseEntity.status(404).body(null));
	}
	
	// 유형별 게시글 목록조회(회원권한 포함)
	@GetMapping("/{boardTypeCode}")
	public List<AdminBoardVO> getBoards(
		@PathVariable String boardTypeCode
		, @RequestParam(required = false) String userRole
	) {
		return service.readAdminBoardListByType(boardTypeCode, userRole);
	}

	// 공지사항 게시글 목록조회
	@GetMapping("/notice/{boardTypeCode}")
	public List<AdminBoardVO> getNotice(
		@PathVariable String boardTypeCode
	) {
		return service.readNoticeList(boardTypeCode);
	}
	
	// 검색
	@GetMapping("/filter")
	public ResponseEntity<Map<String, Object>> getFilterAboardList(
		@RequestParam int page,
	    @RequestParam int pageSize,
		@RequestParam(required = false) List<String> adminCommentList,
		@RequestParam(required = false) String userId,
		@RequestParam(required = false) String boardTitle,
		@RequestParam(required = false) String boardContent
	){
		Map<String, Object> params = new HashMap<>();
		params.put("startRow", (page - 1) * pageSize);
		params.put("endRow", page * pageSize);
		params.put("adminCommentList", adminCommentList);
		params.put("userId", userId);
		params.put("boardTitle", boardTitle);
		params.put("boardContent", boardContent);
		
		Map<String, Object> resp = service.readAboardByFilter(params);
		return ResponseEntity.ok(resp);
	}
	
	// 공지사항 페이지 처리
	@GetMapping("/{boardTypeCode}/notice-page")
	public ResponseEntity<Map<String, Object>> getNoticePage(
			@PathVariable String boardTypeCode
			, @RequestParam int page
			, @RequestParam int pageSize
			) {
		Map<String, Object> params = new HashMap<>();
		params.put("boardTypeCode", boardTypeCode);  // 게시판 유형 추가
		params.put("startRow", (page - 1) * pageSize);
		params.put("endRow", page * pageSize);
		
		log.debug("boardTypeCode : {}", boardTypeCode);
		log.debug("startRow : {}", (page - 1) * pageSize);
		log.debug("endRow : {}", page * pageSize);
		
		// service에서 boardTypeCode도 고려하도록
		Map<String, Object> resp = service.readNotice(params);
		log.info("params : {}", params);
		
		log.info("resp : {}", resp);
		
		return ResponseEntity.ok(resp);
	}
	
	// 게시글 페이지 처리
	@GetMapping("/{boardTypeCode}/page")
	public ResponseEntity<Map<String, Object>> getAboardPage(
	      @PathVariable String boardTypeCode
	    , @RequestParam int page
	    , @RequestParam int pageSize
	) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("boardTypeCode", boardTypeCode);  // 게시판 유형 추가
	    params.put("startRow", (page - 1) * pageSize);
	    params.put("endRow", page * pageSize);
	    
	    // service에서 boardTypeCode도 고려하도록
	    Map<String, Object> resp = service.readAboardPage(params);
	    log.info("params : {}", params);
	    return ResponseEntity.ok(resp);
	}

	// 삭제된 게시글 목록조회
	@GetMapping("/hidden")  
	public List<AdminBoardVO> gethiddenBoards(){
		return service.readDelAboardList();
	}
	
	// 일반/기업/전체/이벤트 앞부분 글자대로 목록조회
	@GetMapping("/pre/{groupPrefix}")  //UFAQ
	public List<AdminBoardVO> getPre(@PathVariable String groupPrefix) {
	    return service.readAFaqListByCgn(groupPrefix);
	}

	// 해당 상위코드 전체 목록조회
	@GetMapping("/list/{upperCodeNo}")  //BRDD-002
	public List<AdminBoardVO> getUpper(@PathVariable String upperCodeNo) {
		return service.readAFaqListByUcn(upperCodeNo);
	}

	// 해당 상위코드 기준 세 테이블 조회
	@GetMapping("/group/{upperCodeNo}")
	public List<CmnCodeGroupVO> getCmnGroup(@PathVariable String upperCodeNo) {
		return service.readCmnGroupList(upperCodeNo);
	}

	// 공통코드(not그룹) 리스트 가져오는 건 없어서 만듦
	@GetMapping("/cmn/{codeGroupNo}")
	public List<CmnCodeVO> getCmn(@PathVariable String codeGroupNo) {
		return service.readCmnList(codeGroupNo);
	}

	//해당 게시글의 작성자 아이디에 맞는 이름 가져옴
	@GetMapping("/userinfo/{userId}")
	public ResponseEntity<Map<String, Object>> getUserInfo(@PathVariable String userId) {
	    Map<String, Object> result = new HashMap<>();
	    
	    // 1. Users 테이블 조회 (userRole 얻기)
	    Optional<UsersVO> optionalUser = userService.searchUserById(userId);
	    if (!optionalUser.isPresent()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 또는 에러 메시지 반환
	    }
	
	    UsersVO user = optionalUser.get();
	    String role = user.getUserRole(); // ex: ROLE_MEMBER
	    result.put("role", role);
	
	    // 2. 역할에 따라 이름 조회
	    switch (role) {
	        case "ROLE_USER":
	            MemberVO member = service.readMemName(userId);
	            result.put("name", member != null ? member.getMemName() : "(회원 없음)");
	            break;
	
	        case "ROLE_COMPANY":
	            CompanyVO company = service.readComName(userId);
	            result.put("name", company != null ? company.getComName() : "(기업 없음)");
	            break;
	
	        case "ROLE_ADMIN":
	            result.put("name", "관리자");
	            break;
	
	        default:
	            result.put("name", "(알 수 없음)");
	    }
	    return ResponseEntity.ok(result);
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
		
		service.createAdminBoard(board);
		
		log.info("board : {}", board);
		
		log.info("등록된 boardNo: {}", board.getBoardNo());
		
	    return Map.of(
            "ok", true,
            "boardNo", board.getBoardNo()
        );
	}
	
	// 해당 유형의 해당 글의 게시글 수정
	@PostMapping("/detail/{boardNo}")
	public Map<String, Object> editBoard(
		@PathVariable String boardNo
		, @RequestBody AdminBoardVO board
	) {
		board.setBoardNo(boardNo);
	    service.modifyAdminBoard(board);
	    
	    return Map.of(
            "ok", true,
            "boardNo", board.getBoardNo()
        );	// 수정 후 Detail 이동
	}
	
	// 해당 글의 조회수 증가
	@PostMapping("/hit/{boardNo}")
	public Map<String, Object> hitUpBoard(@PathVariable String boardNo) {
	    AdminBoardVO board = new AdminBoardVO();
	    board.setBoardNo(boardNo);
	    service.addABoardPostHit(board);
	    return Map.of("ok", true);
	}

	// 해당 유형의 해당 글의 삭제 상태 변경
	@PostMapping("/hidden/{boardNo}")
	public Map<String, Object> hiddenBoard(
	    @PathVariable String boardNo
	    , @RequestBody AdminBoardVO board
	) {
		board.setBoardNo(boardNo);
	    board.setBoardDeleteDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	    service.hiddenAdminBoard(board);

	    Map<String, Object> result = new HashMap<>();
	    result.put("ok", true);
	    result.put("boardTypeCode", board.getBoardTypeCode());  // null 허용
	    return result;	// 삭제 후 list 이동
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
