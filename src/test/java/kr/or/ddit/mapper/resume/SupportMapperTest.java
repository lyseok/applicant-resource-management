package kr.or.ddit.mapper.resume;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.resume.SupportVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class SupportMapperTest {
	@Autowired
	SupportMapper mapper;
	
	@Test
	void testselectSupportList() {
		mapper.selectSupportList().forEach(supt -> {
			log.info("{}", supt);
		});
	}

	@Test
	void testselectSupportDetail() {
		SupportVO vo = mapper.selectSupportDetail("SUP002");
		log.info("{}", vo);
	}

	@Test
	void testInsertSupport() {
		SupportVO vo = new SupportVO();
		vo.setResumeNo("RSM002");
		vo.setDisabilityCode("5");
		vo.setDisabilityLevelCode("1");
		
		mapper.insertSupport(vo);
		SupportVO result = mapper.selectSupportDetail(vo.getSupportNo());
		log.info("{}", result);
		
	}

	@Test
	void testUpdateSupport() {
		SupportVO vo = new SupportVO();
		vo.setSupportNo("SUPT000005");
		vo.setResumeNo("RSM001");
		vo.setDisabilityCode("5");
		vo.setDisabilityLevelCode("9");
		
		mapper.insertSupport(vo);
		SupportVO result = mapper.selectSupportDetail(vo.getSupportNo());
		log.info("{}", result);
		
	}

	@Test
	void testDeleteSupport() {
		mapper.deleteSupport("SUPT000006");
		SupportVO result = mapper.selectSupportDetail("SUPT000006");
		log.info("{}", result);		
	}

}
