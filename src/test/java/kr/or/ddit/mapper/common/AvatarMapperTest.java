package kr.or.ddit.mapper.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.common.AvatarVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@SpringBootTest
class AvatarMapperTest {
	@Autowired
	AvatarMapper mapper;
	@Test
	void testSelectAvatarById() {
		AvatarVO avatar = mapper.selectAvatarById("");
	}

	@Test
	void testSelectAvatarList() {
		mapper.selectAvatarList().forEach(list ->{
			log.info("{}", list);
		});
	}

	@Test
	void testInsertAvatar() {
		AvatarVO vo = new AvatarVO();
		
	}

	@Test
	void testUpdateAvatar() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteAvatar() {
		fail("Not yet implemented");
	}

}
