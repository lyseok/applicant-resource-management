package kr.or.ddit.common.login;


import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.shaded.gson.Gson;

import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RestLoginController {

	@PostMapping("/rest/auth")
	public ResponseEntity<?> authenticate(@RequestBody RestAuthVO auth) {
		UsernamePasswordAuthenticationToken inputData =
				UsernamePasswordAuthenticationToken
					.unauthenticated(auth.getUsername(), auth.getPassword());
		
		try {
			
			String encodedToken = generateToken(inputData);
			
			return ResponseEntity.ok()
							.body(Map.of("token", encodedToken));
			
		} catch (AuthenticationException e) {
			
			return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED)
							.body(Map.of("error", 401, "message", e.getMessage()));
		}
		
	}

	/**
	 * 인증된 객체임을 표현할 수 있는 여러가지 데이터(claim)를 자체적으로 포함하고 있는 JSON 으로 표현된 토큰 생성
	 * @param authentication
	 * @return
	 */
	private String generateToken(Authentication authentication) {
		Map<String,String> claimSet = new HashMap<>();
		claimSet.put("subject", authentication.getName()); // c001
		claimSet.put("scope", authentication.getAuthorities()
									.stream().findFirst().get().getAuthority()); // ROLE_ADMIN
		String tokenValue = new Gson().toJson(claimSet);
		return Base64.getEncoder().encodeToString(tokenValue.getBytes());
	}
}