package kr.or.ddit.admin.common.email.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;
import kr.or.ddit.admin.common.email.service.EmailAuthService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailAuthController {

	private final EmailAuthService service;
	
	@PostMapping("/send")
	public ResponseEntity<?> sendCode(@RequestBody Map<String, String> req) throws MessagingException{
		String email = req.get("email");
		service.sendAuthCode(email);
		return ResponseEntity.ok(Map.of("success",true,"message", "인증번호 발송 완료"));
	}
	
	@PostMapping("/verify")
	public ResponseEntity<?> verify(@RequestBody Map<String, String> req){
		String email = req.get("email");
		String code = req.get("code");
		
		boolean isValid = service.verifyCode(email, code);
		if(isValid) {
			return ResponseEntity.ok(Map.of("success",true,"message", "인증 성공!"));
		} else {
			return ResponseEntity.badRequest().body(Map.of("message", "인증 실패"));
		}
	}
}
