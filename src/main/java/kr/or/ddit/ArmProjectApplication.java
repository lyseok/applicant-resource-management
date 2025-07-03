package kr.or.ddit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@EnableEncryptableProperties
@EnableConfigurationProperties
@SpringBootApplication
public class ArmProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArmProjectApplication.class, args);
	}

}
