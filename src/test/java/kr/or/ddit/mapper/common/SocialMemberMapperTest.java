package kr.or.ddit.mapper.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.common.Oauth2TokenVO;
import kr.or.ddit.vo.common.SocialMemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class SocialMemberMapperTest {
	@Autowired
	SocialMemberMapper mapper;

	
	
	@Test
	void testSelectSocialMemberById() {

	}

	@Test
	void testSelectSocialMemberList() {
		mapper.selectSocialMemberList().forEach(list ->{
			log.info("{}", list);
		});
	}
	
	
	

	@Test
	void testInsertSocialMember() {
		SocialMemberVO vo = new SocialMemberVO();
		vo.setSocialMemId("kakao_9988776655");
		vo.setUserId("USR002");
		vo.setSocialId("이게구글아이디인가");
		
		mapper.insertSocialMember(vo);
		
		SocialMemberVO vo2 = mapper.selectSocialMemberById(vo);
		log.info("{}", vo2);
	}

	@Test
	void testUpdateSocialMember() {
		SocialMemberVO vo = new SocialMemberVO();
		vo.setSocialMemId("naver_abcdefg987");
		vo.setUserId("USR002");
		vo.setSocialId("이게구글");
	}

	@Test
	void testDeleteSocialMember() {
		SocialMemberVO vo = new SocialMemberVO();
		vo.setSocialMemId("naver_abcdefg987");
		vo.setUserId("USR002");
		mapper.deleteSocialMember(vo);
		
		assertNull(mapper.selectSocialMemberById(vo));
	}

}
