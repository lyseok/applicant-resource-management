package kr.or.ddit.mapper.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.common.Oauth2TokenVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class Oauth2TokenMapperTest {
	@Autowired
	Oauth2TokenMapper mapper;

	@Test
	void testSelectOauth2TokenById() {
		mapper.selectOauth2TokenById("");
	}

	@Test
	void testSelectOauth2TokenList() {
		mapper.selectOauth2TokenList().forEach(list ->{
			log.info("{}", list);
		});
	}

	@Test
	void testInsertOauth2Token() {
		Oauth2TokenVO vo = new Oauth2TokenVO();
		vo.setSocialMemUser("meta");
		vo.setSocialMemTokenType("Bearer");
		vo.setSocialMemId("546F6B656E44617461313233");
		vo.setTokenRefresh("52656672657368446174613435");
	}

	@Test
	void testUpdateOauth2Token() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteOauth2Token() {
		fail("Not yet implemented");
	}

}
