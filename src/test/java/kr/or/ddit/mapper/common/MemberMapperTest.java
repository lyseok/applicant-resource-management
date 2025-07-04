package kr.or.ddit.mapper.common;



import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.common.MemberVO;
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
	
	
	@Test
	void testSelectMemberList() {
		mapper.selectMemberList().forEach(list ->{
			log.info("{]", list);
		});
	}
	
	@Test
	void testInsertMember() {
		MemberVO vo = new MemberVO();
		vo.setUserId("USR006");
		vo.setMemName("test");
		vo.setMemEmail("test@email");
		vo.setMemBir("20010827");
		vo.setMemTel("00000");
		vo.setMemAdd1("NewYork");
		vo.setMemAdd1("Brooklyn");
		vo.setMemImg("Brooklyn");
		
		mapper.insertMember(vo);
		MemberVO vo2 = mapper.selectMemberById("USR001");
		log.info("{}", vo2);
	}
	@Test
	void testUpdateMember() {
		MemberVO vo = new MemberVO();
		vo.setUserId("USR007");
		vo.setMemName("test");
		vo.setMemEmail("test@email");
		vo.setMemBir("20010827");
		vo.setMemTel("00000");
		vo.setMemAdd1("NewYork");
		vo.setMemAdd1("Queens");
		vo.setMemImg("Queens");
		
		mapper.updateMember(vo);
		MemberVO vo2 = mapper.selectMemberById("USR001");
		log.info("{}", vo2);
	}
	@Test
	void testDeleteMember() {
		mapper.deleteMember("USR007");
		assertNull(mapper.selectMemberById("USR007"));
	}
	

}
