package kr.or.ddit.conf;

import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration // Spring 설정 클래스임을 나타내는 어노테이션
public class SiteMeshFilterConfig {

    @Bean // SiteMesh 필터 등록을 위한 Bean 정의
    FilterRegistrationBean<ConfigurableSiteMeshFilter> siteMeshFilter() {
        // FilterRegistrationBean 객체 생성 (제네릭으로 필터 타입 지정)
        FilterRegistrationBean<ConfigurableSiteMeshFilter> filter = new FilterRegistrationBean<>();

        // SiteMesh 필터 생성 및 데코레이터 설정
        filter.setFilter(
            ConfigurableSiteMeshFilter.create(builder -> 
                builder
                    .setDecoratorPrefix("/WEB-INF/decorators/")   // 데코레이터 JSP 파일들이 위치한 경로 지정
                    .addExcludedPath("/ajax/**")                 // ajax 요청 경로 필터 제외
                    .addExcludedPath("/rest/**")                 // REST API 요청 경로 필터 제외
                    .addExcludedPath("*.html")                   // 정적 HTML 파일 필터 제외
                    .addDecoratorPath("/company", "companyDashDecorators.jsp") // 관리 페이지에 적용할 데코레이터 지정
                    .addDecoratorPath("/company/**", "companyDashDecorators.jsp") // 관리 페이지에 적용할 데코레이터 지정
                    .addDecoratorPath("/admin", "adminDashDecorators.jsp") // 관리 페이지에 적용할 데코레이터 지정
                    .addDecoratorPath("/admin/**", "adminDashDecorators.jsp") // 관리 페이지에 적용할 데코레이터 지정
                    .addDecoratorPath("/**", "mainDecorators.jsp")      // 나머지 모든 요청에 적용할 메인 데코레이터 지정
            )
        );

        // 필터 실행 우선순위 설정: 가장 낮은 우선순위에서 -100만큼 높음
        filter.setOrder(Ordered.LOWEST_PRECEDENCE - 100);

        // 모든 요청(\"/*\")에 대해 필터가 적용되도록 URL 패턴 매핑
        filter.addUrlPatterns("/*");

        return filter; // 구성된 FilterRegistrationBean 반환
    }
}