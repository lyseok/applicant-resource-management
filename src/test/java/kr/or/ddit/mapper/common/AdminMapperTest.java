package kr.or.ddit.mapper.common;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.common.UsersVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class AdminMapperTest {
		
	@Autowired
	AdminMapper mapper;
	
	@Test
	void testSelectAdminById() {
		UsersVO user = mapper.selectAdminById("testAdmin");
		
		log.info("{}", user.getUserPassword());
		log.info("{}", user.getUserRole());
		log.info("{}", user);
	}

}
