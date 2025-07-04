package kr.or.ddit.mapper.recruitment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.recruitment.PassIntroductionVO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class PassIntroductionMapperTest {

	@Autowired
	PassIntroductionMapper mapper;
	
	@Test
	void testSelectPassIntroductionList() {
		mapper.selectPassIntroductionList().forEach(code -> {
			log.info("{}", code);		
		});
	}

	@Test
	void testSelectPassIntroduction() {
		log.info("{}", mapper.selectPassIntroduction("PSIT000001"));
	}

	@Test
	void testInsertPassIntroduction() {
		PassIntroductionVO vo = new PassIntroductionVO();
		vo.setPasserCode("PASS000001");
		assertEquals(1, mapper.insertPassIntroduction(vo));
	}


	@Test
	void testDeletePassIntroduction() {
		assertEquals(1, mapper.deletePassIntroduction("PSIT000001"));
	}

}
