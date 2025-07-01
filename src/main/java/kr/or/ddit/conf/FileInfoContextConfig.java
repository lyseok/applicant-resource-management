package kr.or.ddit.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j // Lombok을 사용해 로거(logger)를 자동 생성
@Configuration // 이 클래스가 Spring 설정 클래스임을 나타냄
@PropertySource("classpath:kr/or/ddit/FileInfo.properties") // 외부 프로퍼티 파일을 로드
public class FileInfoContextConfig implements WebMvcConfigurer{
    @Value("${imagesFolder}")
    private Resource imagesLocation; // 프로퍼티(imagesFolder)로부터 이미지 폴더 리소스를 주입

    @Value("${imagesUrl}")
    private String imagesUrl; // 프로퍼티(imagesUrl)로부터 URL 패턴을 주입

    @Value("${user.dir}")
    private String useDir; // JVM 시스템 프로퍼티(user.dir)를 주입
    
    /**
     * 파일 시스템상의 자원을 웹상의 URL로 매핑
     * prodDetail.jsp 등에서 <img> 태그로 접근할 때 사용되는 URL 구조 설정
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(imagesUrl + "/**") // 요청 URL 패턴 지정
                .addResourceLocations(imagesLocation.toString()); // 실제 리소스 위치 지정
    }
    
    @PostConstruct // 빈 초기화 후 실행되는 메서드
    public void init() {
        log.info("====================> user.dir : {}", useDir); // 애플리케이션 실행 경로 출력
        log.info("====================> imagesLocation : {}", imagesLocation); // 주입된 이미지 폴더 위치 출력
    }
}