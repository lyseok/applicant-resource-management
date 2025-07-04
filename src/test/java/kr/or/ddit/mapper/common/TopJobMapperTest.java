package kr.or.ddit.mapper.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.common.TopJobVO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class TopJobMapperTest {

	@Autowired
	TopJobMapper mapper;

	@Test
	void testSelectTopJobList() {
		mapper.selectTopJobList().forEach(code -> {
			log.info("{}", code);
		});
	}

	@Test
	void testSelectTopJobByPk() {
		TopJobVO TJV = new TopJobVO();

		TJV.setTopJobCode("TEST");
		TJV.setTopJobName("TEST");

		mapper.selectTopJobByPk(TJV);
	}

	@Test
	void testInsertTopJob() {
		TopJobVO TJV = new TopJobVO();

		TJV.setTopJobCode("TEST");
		TJV.setTopJobName("TEST");

		mapper.insertTopJob(TJV);
	}

	@Test
	void testUpdateTopJob() {
		TopJobVO TJV = new TopJobVO();

		TJV.setTopJobCode("TEST");
		TJV.setTopJobName("ㅋㅋ루삥뽕땅");
		
		mapper.updateTopJob(TJV);
	}

	@Test
	void testDeleteTopJob() {
		TopJobVO TJV = new TopJobVO();

		TJV.setTopJobCode("TEST");
		TJV.setTopJobName("ㅋㅋ루삥뽕땅");	
		
		mapper.deleteTopJob(TJV);
	}
	

}
