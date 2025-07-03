package kr.or.ddit.mapper.common;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.common.ScrabRecruitmentVO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class ScrabRecruitmentMapperTest {

	@Autowired
	ScrabRecruitmentMapper mapper;

	@Test
	void testSelectScrabRecruitmentList() {		
		
		mapper.selectScrabRecruitmentList().forEach(code -> {
			log.info("{}", code);		
		});
		
	}

	@Test
	void testSelectScrabRecruitmentByPk() {
		ScrabRecruitmentVO SRV = new ScrabRecruitmentVO();
		SRV.setUserId("USR001");
		SRV.setRecruitmentNo("recr000005");
		
		mapper.selectScrabRecruitmentByPk(SRV);
	
	}

	@Test
	void testInsertScrabRecruitment() {
		ScrabRecruitmentVO SRV = new ScrabRecruitmentVO();

		SRV.setUserId("qwer1234");
		SRV.setRecruitmentNo("asdf1234");

		mapper.insertScrabRecruitment(SRV);

		log.info("결과 : {}", SRV);
	}

	@Test
	void testUpdateScrabRecruitment() {
		ScrabRecruitmentVO SRV = new ScrabRecruitmentVO();
		SRV.setRecruitmentNo("asdf1234");
		SRV.setUserId("qwer1234");
		mapper.updateScrabRecruitment(SRV);
		mapper.selectScrabRecruitmentList();
		log.info("결과 : {}");
	}
	
	@Test
	void testDeleteScrabRecruitment() {
		ScrabRecruitmentVO SRV = new ScrabRecruitmentVO();
		SRV.setRecruitmentNo("asdf1234");
		SRV.setUserId("qwer1234");
		mapper.deleteScrabRecruitment(SRV);
	}

}
