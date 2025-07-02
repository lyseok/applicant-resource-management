package kr.or.ddit.mapper.common;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

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
		vo.setIntroductionName("insert 테스트");
		vo.setIntroductionContent("insert 매퍼 테스트중입니다.");
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
