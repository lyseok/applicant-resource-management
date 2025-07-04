package kr.or.ddit.mapper.common;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.common.ScrabProjectVO;
import lombok.extern.slf4j.Slf4j;
@SpringBootTest
@Slf4j
class ScrabProjectMapperTest {

	@Autowired
	ScrabProjectMapper mapper ; 
	
	@Test
	void testSelectScrabProjettList() {
		mapper.selectScrabProjectList().forEach(code->{
			log.info("{}",code);
		});
	}
	
	@Test
	void testSelectScrabProjectByPk() {
		ScrabProjectVO SPV = new ScrabProjectVO();
		
		SPV.setCompanyId("QWE");
		SPV.setProjectNo("REWQ");
		
		mapper.selectScrabProjectByPk(SPV);
		
	}

	@Test
	void testInsertScrabProject() {
		ScrabProjectVO SPV = new ScrabProjectVO();

		SPV.setCompanyId("QWE");
		SPV.setProjectNo("REWQ");
		
		mapper.insertScrabProject(SPV);
	}

	@Test
	void testUpdateScrabProject() {
		ScrabProjectVO SPV = new ScrabProjectVO();
		
		SPV.setCompanyId("QWE");
		SPV.setProjectNo("REWQ");
		
		mapper.updateScrabProject(SPV);

	}

	@Test
	void testDeleteScrabProject() {
		ScrabProjectVO SPV = new ScrabProjectVO();
		
		SPV.setCompanyId("QWE");
		SPV.setProjectNo("REWQ");
		
		mapper.deleteScrabProject(SPV);
		
	}

}
