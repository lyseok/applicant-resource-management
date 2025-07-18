package kr.or.ddit.mapper.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.or.ddit.vo.common.UsersVO;

@SpringBootTest
class UserMapperTest {

	@Autowired
	UserMapper userMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Test
	void testInsertUser() {
		UsersVO user = new UsersVO();
		String encoded = passwordEncoder.encode("java");
		user.setUserId("imgoogle001");
		user.setUserPassword(encoded);
		assertEquals(1, userMapper.insertUser(user));
	}

	@Test
	void testExistsById() {
		assertNotNull(userMapper.existsById("USR001"));
	}

}
