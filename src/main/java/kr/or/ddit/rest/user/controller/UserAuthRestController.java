package kr.or.ddit.rest.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.rest.user.service.UserRestService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserAuthRestController {
	private final UserRestService userRestService;
	
	@GetMapping("/api/users/me")
	public ResponseEntity<?> getCurrentUser() {
	    return ResponseEntity.ok(userRestService.readUserWithProjects());
	}
}
