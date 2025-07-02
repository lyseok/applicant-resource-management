package kr.or.ddit.mapper.resume;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.resume.IntroductionVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class IntroductionMapperTest {
	@Autowired
	IntroductionMapper mapper;

	@Test
	void testSelectIntroductionList() {
		mapper.selectIntroductionList("USR001").forEach(code -> {
			log.info("♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣♣ {}", code);	
		});
	}

	@Test
	void testSelectIntroductionDetail() {
		IntroductionVO vo = new IntroductionVO();
		vo.setUserId("USR001");
		vo.setIntroductionNo("INT0000004");
		
        IntroductionVO resultVo = mapper.selectIntroductionDetail(vo);
		
		log.info("{}", resultVo);		
	}

	@Test
	void testInsertIntroduction() {
		IntroductionVO vo = new IntroductionVO();
		vo.setUserId("USR001");
		vo.setIntroductionName("제출용 자기소개서 인서트지롱");
		vo.setIntroductionContent("제출 자소서 test 할거다!");
        int result = mapper.insertIntroduction(vo);

		log.info("{}", result);		
	}

	@Test
	void testUpdateIntroduction() {
		IntroductionVO vo = new IntroductionVO();
		vo.setUserId("USR001");
		vo.setIntroductionNo("INT0000004");
		vo.setIntroductionName("업데이트 테스트다!");
		vo.setIntroductionContent("이렇게 오래 걸리는게 정상임?");
        mapper.updateIntroduction(vo);

        IntroductionVO result1 = mapper.selectIntroductionDetail(vo);
		log.info("{}", result1);		
	}

	@Test
	void testDeleteIntroduction() {
		IntroductionVO vo = new IntroductionVO();
		vo.setUserId("USR001");
		vo.setIntroductionNo("INT0000005");
		
		mapper.deleteIntroduction(vo);
		
    	assertNull(mapper.selectIntroductionDetail(vo));
	}

}
