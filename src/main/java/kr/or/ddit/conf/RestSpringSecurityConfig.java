package kr.or.ddit.conf;

import java.util.List; 

import javax.crypto.SecretKey; 
import javax.crypto.spec.SecretKeySpec; 

import org.springframework.beans.factory.annotation.Value; 
import org.springframework.boot.context.properties.ConfigurationProperties; 
import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration; 
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod; 
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm; 
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration; 
import org.springframework.web.cors.CorsConfigurationSource; 
import org.springframework.web.cors.UrlBasedCorsConfigurationSource; 

import com.nimbusds.jose.JWSAlgorithm; 

import lombok.Data; 

@Data 
@Configuration 
@ConfigurationProperties(prefix = "cors") 
public class RestSpringSecurityConfig {
    
    private List<String> allowedOrigins; // CORS에서 허용할 출처(Origins)
    private List<String> allowedMethods; // CORS에서 허용할 HTTP 메서드 목록
    private List<String> allowedHeaders; // CORS에서 허용할 HTTP 헤더 목록
    private boolean allowCredentials; // 자격 증명(Cookie 등) 허용 여부

    @Bean // CORS 정책을 제공하는 Bean 등록
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration(); // CORS 설정 객체 생성

        corsConfig.setAllowedOrigins(allowedOrigins); // 허용 출처 설정
        corsConfig.setAllowedMethods(allowedMethods); // 허용 HTTP 메서드 설정
        corsConfig.setAllowedHeaders(allowedHeaders); // 허용 HTTP 헤더 설정
        corsConfig.setAllowCredentials(allowCredentials); // 자격 증명 허용 설정

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(); // URL 기반 CORS 설정 소스 생성
        source.registerCorsConfiguration("/rest/**", corsConfig); // '/rest/**' 경로에 CORS 정책 등록
        return source; // 설정된 CORS 소스 반환
    }

    @Value("${jwt.secrete-key}") // 프로퍼티의 JWT 시크릿 키 바이트 배열 주입
    private byte[] secreteKey;

    @Bean // JWT 디코더 Bean 등록
    public JwtDecoder jwtDecoder() {
        // SecretKey 객체 생성 (바이트 배열과 JWS 알고리즘 이름 사용)
        SecretKey key = new SecretKeySpec(secreteKey, JWSAlgorithm.HS256.getName());
        
        // Nimbus 기반 JWT 디코더 생성, HMAC-SHA256 알고리즘 지정
        NimbusJwtDecoder decoder = NimbusJwtDecoder
                                    .withSecretKey(key)
                                    .macAlgorithm(MacAlgorithm.HS256)
                                    .build();
        return decoder; // 디코더 반환
    }

    @Bean // 시큐리티 필터 체인 정의 Bean
    @Order(1) // 우선순위 지정 (숫자가 낮을수록 먼저 적용)
    public SecurityFilterChain restSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/rest/**") // '/rest/**' 패턴 요청에 보안 적용
            .csrf(csrf -> csrf.disable()) // CSRF 보호 비활성화 (REST API 특성)
            .cors(cors -> cors.configurationSource(corsConfigurationSource())) // CORS 정책 적용
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.GET, "/rest/employment").permitAll() // 해당 GET 요청 허용
                .requestMatchers("/rest/auth").permitAll() // 인증 경로 허용
                .anyRequest().authenticated() // 나머지 요청은 인증 필요
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 미사용 (무상태 API)
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt.decoder(jwtDecoder())) // JWT 디코더 설정
            );

        return http.build(); // 필터 체인 빌드 및 반환
    }
}