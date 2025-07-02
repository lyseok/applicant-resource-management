package kr.or.ddit.mapper.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.common.UsersVO;

@SpringBootTest
class UserMapperTest {

	@Autowired
	UserMapper userMapper;
	
	@Test
	void testInsertUser() {
		UsersVO user = new UsersVO();
		user.setUserId("zoo6213");
		user.setUserPassword("john5214");
		assertEquals(1, userMapper.insertUser(user));
	}

	@Test
	void testExistsById() {
		userMapper.existsById("USR001");
		assertNotNull(userMapper.existsById("USR001"));
	}

}
