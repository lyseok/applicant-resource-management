package kr.or.ddit.conf;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;

@Configuration // 이 클래스가 스프링 설정 클래스임을 표시
public class SwaggerConfig {

    @Bean // 스프링 컨테이너에 OpenAPI 빈으로 등록
    public OpenAPI customOpenAPI() {
        final String SCHEME_KEY = "bearerAuth"; // 보안 스킴 식별 키 상수

        // JWT Bearer 토큰을 위한 SecurityScheme 정의
        SecurityScheme securityScheme = new SecurityScheme()
                .type(Type.HTTP)               // HTTP 방식 스킴 지정
                .scheme("bearer")            // Bearer 스킴 지정
                .bearerFormat("JWT");        // 토큰 포맷 설명 (JWT)
        
        // OpenAPI 객체 생성 및 보안 설정 추가
        return new OpenAPI()
                .components(
                    new Components()
                        .addSecuritySchemes(SCHEME_KEY, securityScheme) // Components에 정의한 스킴 등록
                )
                .addSecurityItem(
                    new SecurityRequirement().addList(SCHEME_KEY)   // 전역 보안 요구사항에 BearerAuth 적용
                );
    }
}