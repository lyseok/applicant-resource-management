package kr.or.ddit.mapper.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.common.AvatarVO;
import kr.or.ddit.vo.common.MemberVO;
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
		vo.setUserId("USR001");
		vo.setTopJobCode("20");
		vo.setAvatarNn("testAvatar");
		vo.setYearCode("test");
		mapper.insertAvatar(vo);
		
		AvatarVO vo2 = mapper.selectAvatarById("USR001");
		log.info("{}", vo2);
	}

	@Test
	void testUpdateAvatar() {
		AvatarVO vo = new AvatarVO();
		vo.setUserId("USR001");
		vo.setTopJobCode("17");
		vo.setAvatarNn("testAvatar2");
		vo.setYearCode("test2");
		mapper.updateAvatar(vo);
		
		AvatarVO vo2 = mapper.selectAvatarById("USR001");
		log.info("{}", vo2);
	}

	@Test
	void testDeleteAvatar() {
		mapper.deleteAvatar("USR001");
		assertNull(mapper.selectAvatarById("USR001"));
	}

}
