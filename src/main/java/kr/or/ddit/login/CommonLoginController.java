package kr.or.ddit.login;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.boot.web.server.Cookie.SameSite;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.security.jwt.CookieBearerTokenResolver;
import kr.or.ddit.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommonLoginController {
	private final AuthenticationManager authenticationManager;
	private final JwtProvider jwtProvider;
	private final SecurityContextRepository securityContextRepository;
	private final LogoutHandler logoutHandler;
	
	@PostMapping("/common/auth/revoke")
	public ResponseEntity<?> revoke(
		HttpServletRequest req
		, HttpServletResponse resp
		, Authentication authentication
	) {
		
		// 토큰 기반 인증 상태를 로그아웃으로 처리
		String tokenCookie = ResponseCookie.from(CookieBearerTokenResolver.ACCESSTOKENCOOKIE)
				.value("")
				.path("/")
//				.domain(".naver.com")
				.httpOnly(true)
//				.secure(true)
				.sameSite(SameSite.STRICT.attributeValue())
				.maxAge(0)
				.build().toString();
		
		// 세션 기반 인증 상태를 로그아웃으로 처리
		logoutHandler.logout(req, resp, authentication);
		
		return ResponseEntity.ok()
				.header(HttpHeaders.SET_COOKIE, tokenCookie)
				.build();
	}
	

	@PostMapping("/common/auth")
	public ResponseEntity<?> authenticate(
		@RequestBody RestAuthVO auth
		, HttpServletRequest req
		, HttpServletResponse resp
	) {
		UsernamePasswordAuthenticationToken inputData =
				UsernamePasswordAuthenticationToken
					.unauthenticated(auth.getUsername(), auth.getPassword());
		
		try {
			log.info("{}", auth);
			Authentication authentication = authenticationManager.authenticate(inputData);
			
			// 토큰 기반 인증 처리
			String encodedToken = jwtProvider.authenticationToToken(authentication);
			
			String tokenCookie = ResponseCookie.from(CookieBearerTokenResolver.ACCESSTOKENCOOKIE)
						.value(encodedToken)
						.path("/")
//						.domain(".naver.com")
						.httpOnly(true)
//						.secure(true)
						.sameSite(SameSite.STRICT.attributeValue())
						.maxAge(JwtProvider.VALID_TERM / 1000)
						.build().toString();
						
			
			// 세션 기반 인증 처리
			SecurityContext newContext = SecurityContextHolder.createEmptyContext();
			newContext.setAuthentication(authentication);
			SecurityContextHolder.setContext(newContext);
			securityContextRepository.saveContext(newContext, req, resp);
			
			List<String> roles = authentication.getAuthorities().stream()
		            .map(GrantedAuthority::getAuthority)
		            .collect(Collectors.toList());
			
			Map<String, Object> body = Map.of(
		            "username", authentication.getName(),
		            "roles", roles
		        );
			return ResponseEntity.ok()
							.header(HttpHeaders.SET_COOKIE, tokenCookie)
							.body(body);
							
			
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
//	private String generateToken(Authentication authentication) {
//		Map<String,String> claimSet = new HashMap<>();
//		claimSet.put("subject", authentication.getName()); // c001
//		claimSet.put("scope", authentication.getAuthorities()
//									.stream().findFirst().get().getAuthority()); // ROLE_ADMIN
//		String tokenValue = new Gson().toJson(claimSet);
//		return Base64.getEncoder().encodeToString(tokenValue.getBytes());
//	}
}
