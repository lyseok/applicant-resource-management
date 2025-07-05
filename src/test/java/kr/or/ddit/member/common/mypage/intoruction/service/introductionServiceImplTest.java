package kr.or.ddit.member.common.mypage.intoruction.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.mapper.resume.IntroductionMapper;
import kr.or.ddit.vo.resume.IntroductionVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class introductionServiceImplTest {
	@Autowired
	IntroductionMapper mapper;
	
	@Test
	void testReadIntroductionList() {
		mapper.selectIntroductionList("USR001").forEach(code -> {
			log.info("♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣ {}", code);	
		});
	}

	@Test
	void testReadIntroductionDetail() {
		String no = "INT0000004";
		
        IntroductionVO resultVo = mapper.selectIntroductionDetail(no);
		
		log.info("{}", resultVo);	
	}

	@Test
	void testCreateIntroduction() {
		IntroductionVO vo = new IntroductionVO();
		vo.setUserId("USR001");
		vo.setIntroductionName("제출용 자기소개서 인서트지롱");
		vo.setIntroductionContent("제출 자소서 test 할거다!");
        int result = mapper.insertIntroduction(vo);

		log.info("{}", result);		
	}

	@Test
	void testEditIntroduction() {
		IntroductionVO vo = new IntroductionVO();
		vo.setUserId("USR001");
		vo.setIntroductionNo("INT0000005");
		vo.setIntroductionName("업데이트 테스트다!");
		vo.setIntroductionContent("이렇게 오래 걸리는게 정상임?");
        mapper.updateIntroduction(vo);

        IntroductionVO result1 = mapper.selectIntroductionDetail(vo.getIntroductionNo());
		log.info("{}", result1);		
	}

	@Test
	void testRemoveIntroduction() {
		IntroductionVO vo = new IntroductionVO();
		vo.setUserId("USR001");
		vo.setIntroductionNo("INT0000006");
		
		mapper.deleteIntroduction(vo);
		
    	assertNull(mapper.selectIntroductionDetail("INT0000006"));
	}

}
