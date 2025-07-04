package kr.or.ddit.mapper.common;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.common.SalaryVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class SalaryMapperTest {
	@Autowired
	SalaryMapper mapper;
	
	@Test
	void testSelectSalaryList() {
		mapper.selectSalaryList().forEach(salary -> {
			log.info("{}", salary);
		});
	}

	@Test
	void testSelectSalaryByCompany() {
		SalaryVO salaryVO = mapper.selectSalaryByCompany("corp01");
		log.info("{}", salaryVO);
	}

	@Test
	void testSelectSalaryRangeList() {
		Map<String, Object> params = new HashMap<>();
		params.put("salaryMin", 3000);
		params.put("salaryMax", 6000);
		
		mapper.searchSalaryRangeList(params).forEach(salary ->{
			log.info("{}", salary);
		});
		
		
	}

	@Test
	void testSelectSalaryComNameList() {
		mapper.searchSalaryComNameList("네이버").forEach(salary ->{
			log.info("{}", salary);
		});
	}

	@Test
	void testUpdateSalary() {
		   SalaryVO vo = new SalaryVO();
	        vo.setSalaryId("TEST_UPD");
	        vo.setUserId("corp02");
	        vo.setJobCode("100");
	        vo.setSalaryMin("3501");
	        vo.setSalaryMax("6509");
	        
	        int result = mapper.updateSalary(vo);
	        log.info("{}", result);
	      
	}

	@Test
	void testInsertSalary() {
		  SalaryVO vo = new SalaryVO();
	        vo.setSalaryId("TEST_UPD");
	        vo.setUserId("corp01");
	        vo.setJobCode("20");
	        vo.setSalaryMin("3500");
	        vo.setSalaryMax("6500");
	        int result= mapper.insertSalary(vo);
	        
	        log.info("{}", result);
	}

	@Test
	void testDeleteSalary() {
		int result = mapper.deleteSalary("corp01");
	}

}
