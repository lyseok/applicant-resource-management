package kr.or.ddit.conf;

import java.util.List;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
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

import kr.or.ddit.security.jwt.CookieBearerTokenResolver;
import lombok.Data;

@Configuration
public class RestSpringSecurityConfig {
	
	@Autowired
	private CorsConfigurationSource restCorsConfigurationSource;
	
	@Value("${jwt.secrete-key}")
	private byte[] secreteKey;
	
	@Bean
	public JwtDecoder jwtDecoder() {
		SecretKey key = new SecretKeySpec(secreteKey, JWSAlgorithm.HS256.getName());
		NimbusJwtDecoder decoder = NimbusJwtDecoder
									.withSecretKey(key)
									.macAlgorithm(MacAlgorithm.HS256)
									.build();
		return decoder;
	}
	
	@Autowired
	private CookieBearerTokenResolver cookieBearerTokenResolver;
	
	@Bean
	@Order(1)
	public SecurityFilterChain restSecurityFilterChain(HttpSecurity http) throws Exception {
		http.securityMatcher("/rest/**")
			.csrf(csrf->csrf.disable())
			.cors(cors->cors.configurationSource(restCorsConfigurationSource))
			.authorizeHttpRequests(authorize->
				authorize
					.requestMatchers("/rest/lprod/**").hasRole("ADMIN")
					.anyRequest().authenticated()
					
			)
			.sessionManagement(session->
				session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			)
			.oauth2ResourceServer(oauth2RS->
				oauth2RS.jwt(jwt->jwt.decoder(jwtDecoder()))
						.bearerTokenResolver(cookieBearerTokenResolver)
			)
			;
		return http.build();
	}
}










