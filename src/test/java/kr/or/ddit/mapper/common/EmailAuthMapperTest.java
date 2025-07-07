package kr.or.ddit.mapper.common;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.common.EmailAuthVO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class EmailAuthMapperTest {

	@Autowired
	EmailAuthMapper mapper;
	
	@Test
	void testUpsertAuthCode() {
		EmailAuthVO vo = new EmailAuthVO();
		vo.setEmail("robin0329@naver.com");
		vo.setAuthCode("0000");
		vo.setExpireTime(LocalDateTime.of(2025, 7, 7, 0, 0));
		assertEquals(1, mapper.upsertAuthCode(vo));
	}

	@Test
	void testVerifyAuthCode() {
		EmailAuthVO vo = new EmailAuthVO();
		vo.setEmail("robin0329@naver.com");
		vo.setAuthCode("0000");
		log.info("{}", mapper.verifyAuthCode(vo));
	}

}
