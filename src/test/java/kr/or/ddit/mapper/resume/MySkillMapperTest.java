package kr.or.ddit.mapper.resume;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.resume.MySkillVO;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootTest
class MySkillMapperTest {
	@Autowired
	MySkillMapper mapper;

	@Test
	void testselectMySkillList() {
		mapper.selectMySkillList("RESM000001").forEach(sk ->{
			log.info("{}", sk);
		});
	}

	@Test
	void testselectMySkillDetail() {
		MySkillVO vo = new MySkillVO();
		vo.setResumeNo("RESM000001");
		vo.setMySkillCode("MYSK000001");
		
		log.info("{}", mapper.selectMySkillDetail(vo));
	}

	@Test
	void testInsertMySkill() {
		MySkillVO vo = new MySkillVO();
		vo.setResumeNo("RSM001");
		vo.setMySkillName("8");
		mapper.insertMySkill(vo);
		
		mapper.selectMySkillDetail(vo);
		log.info("{}", vo);
	}

	@Test
	void testUpdateMySkill() {
		MySkillVO vo = new MySkillVO();
		vo.setMySkillCode("MYS0000005");
		vo.setResumeNo("RSM001");
		vo.setMySkillName("메롱");
		mapper.updateMySkill(vo);
		
		mapper.selectMySkillDetail(vo);
		log.info("{}", vo);
	}

	@Test
	void testDeleteMySkill() {
		mapper.deleteMySkill("MYS0000006");
		/* assertNull(mapper.selectMySkillDetail("MYS0000006")); */
	}

}
