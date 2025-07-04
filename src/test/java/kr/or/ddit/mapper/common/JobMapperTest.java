package kr.or.ddit.mapper.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.common.JobVO;
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
	JobVO JV = new JobVO();
		
		JV.setJobCode("99999");
		JV.setTopJobCode("2");
		JV.setJobName("TEST999");
		
		mapper.selectJobByPk(JV);
	}

	@Test
	void testInsertJob() {
		JobVO JV = new JobVO();
		
		JV.setJobCode("99999");
		JV.setTopJobCode("2");
		JV.setJobName("TEST999");
		
		mapper.insertJob(JV);
	}

	@Test
	void testUpdateJob() {
	JobVO JV = new JobVO();
		
		JV.setJobCode("99999");
		JV.setTopJobCode("2");
		JV.setJobName("TTEESSTT");
		
		mapper.updateJob(JV);
	}

	@Test
	void testDeleteJob() {
JobVO JV = new JobVO();
		
		JV.setJobCode("99999");
		JV.setTopJobCode("2");
		JV.setJobName("TTEESSTT");
		
		mapper.deleteJob(JV);
	}

}
