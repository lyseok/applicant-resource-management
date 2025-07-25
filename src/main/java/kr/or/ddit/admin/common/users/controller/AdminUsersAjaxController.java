package kr.or.ddit.admin.common.users.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.admin.common.users.service.AdminUsersService;
import kr.or.ddit.vo.common.UsersVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ajax/admin/common/users")
@RequiredArgsConstructor
public class AdminUsersAjaxController {

	private final AdminUsersService service;
	
	@GetMapping
	public List<UsersVO> getAll(
		@RequestParam(required = false) String userRole  //역할 조회시 /users?userRole=ROLE_COMPANY
		, @RequestParam(required = false) String userId
	){  
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName(); // 아이디
	    log.info("🔐 요청자: {}", username);
		return service.readUsersList(userRole, userId);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UsersVO> getOneUser(@PathVariable String userId) {
	    return service.searchUserById(userId)
	    		.map(ResponseEntity::ok)  //userId 있으면 ok 반환
	            .orElse(ResponseEntity.status(404).body(null));  //없을 시 js에서 처리(상태코드 404 객체 반환)
	}
	
	// 관리자가 회원을 이렇게 새로 만드는 건 안될걸?
	@PostMapping
	public Map<String, Object> newUser(@RequestBody UsersVO user) {
		service.createUser(user);
	    return Map.of("ok", true);
	}
	
	// 수정, 탈퇴 회원 상태 변경
	@PostMapping("/{userId}")
	public Map<String, Object> editUser(
		@PathVariable String userId
		,  @RequestBody UsersVO user
	) {
		user.setUserId(userId);
	    service.modifyUser(user);
	    return Map.of("ok", true);	// 수정 후 리스트로(!) 이동하는 게 나을듯
	}
	
	@GetMapping("/{userId}/check")
	public Map<String, Object> checkUser(
		@PathVariable String userId	
	) {
		service.existsById(userId);
		return Map.of("ok", true);
	}
	/*
	@GetMapping("/who/{email}")
	public ResponseEntity<UsersVO> getOneSMember(@PathVariable String email) {
	    return service.searchMemberByMail(email)
	    		.map(ResponseEntity::ok)  //email 있으면 ok 반환
	            .orElse(ResponseEntity.status(404).body(null));  //없을 시 js에서 처리(상태코드 404 객체 반환)
	}  */
}
