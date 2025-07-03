package kr.or.ddit.mapper.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.common.UsersVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class MemberMapperTest {
	
	@Autowired
	MemberMapper mapper;
	
	@Test
	void testSelectMemberById() {
		UsersVO user = mapper.selectMemberById("testUser");
		
		log.info("{}", user.getUserPassword());
		log.info("{}", user.getUserRole());
		log.info("{}", user);
	}

}
