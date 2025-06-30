package kr.or.ddit.conf;

import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;



@Configuration
public class SiteMeshFilterConfig {
	
	
	@Bean
	FilterRegistrationBean<ConfigurableSiteMeshFilter> siteMeshFilter(){
		FilterRegistrationBean<ConfigurableSiteMeshFilter> filter =
											new FilterRegistrationBean<>();
		// 실제 필터 생성
		filter.setFilter(
			ConfigurableSiteMeshFilter.create(builder->
				builder.setDecoratorPrefix("/WEB-INF/decorators/")
					.addExcludedPath("/ajax/**")
					.addExcludedPath("/rest/**")
					.addExcludedPath("*.html")
					.addDecoratorPath("/admin/**", "dashDecorators.jsp")
					.addDecoratorPath("/**", "mainDecorators.jsp")
			)	
		);
		// 우선순위 결정
		filter.setOrder(Ordered.LOWEST_PRECEDENCE-100);
		// 필터 매핑 설정
		filter.addUrlPatterns("/*");
		return filter;
	}
}




