package kr.or.ddit.mapper.common;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class CmnCodeGroupMapperTest {
	@Autowired
	CmnCodeGroupMapper mapper;
	
	@Test
	void test() {
		
		mapper.selectCmnCodeGroupList().forEach(code -> {
			log.info("{}", code);		
		});
	}

}
