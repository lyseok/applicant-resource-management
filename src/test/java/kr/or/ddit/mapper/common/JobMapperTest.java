package kr.or.ddit.mapper.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
@SpringBootTest
@Slf4j
class JobMapperTest {

		@Autowired
		JobMapper mapper;
		
	@Test
	void testSelectJobList() {
		mapper.selectJobList().forEach(code->{
			log.info("{}",code);
		});
	}

	@Test
	void testSelectJobByPk() {
		fail("Not yet implemented");
	}

	@Test
	void testInsertJob() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateJob() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteJob() {
		fail("Not yet implemented");
	}

}
