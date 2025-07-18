package kr.or.ddit.mapper.common;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.or.ddit.vo.common.UsersVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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

	@Test
	void testSelectUsersList() {
		assertDoesNotThrow(()->userMapper.selectUsersList("ROLE_COMPANY", "corp03"));
		List<UsersVO> users = userMapper.selectUsersList("ROLE_COMPANY", "corp03");
		users.forEach(user->{
			log.info("기업 회원 하나 : {}", user);
		});
	}
}
