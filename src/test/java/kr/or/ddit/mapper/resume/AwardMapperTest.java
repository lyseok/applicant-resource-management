package kr.or.ddit.mapper.resume;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.resume.AwardVO;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootTest
class AwardMapperTest {
	@Autowired
	AwardMapper mapper;
	
	@Test
	void testSeletAwardList() {
		mapper.seletAwardList().forEach(award->{
			log.info("{}", award);
		});
	}

	@Test
	void testSeletAwardDetail() {
		AwardVO vo = mapper.seletAwardDetail("AWD001");
		log.info("{}", vo);
	}

	@Test
	void testInsertAward() {
		AwardVO vo = new AwardVO();
		vo.setResumeNo("RSM001");
		vo.setAwardName("최우수 개발자상");
		vo.setAwardDate("2022-01-12");
		vo.setHosting("고용노동부");
		
		mapper.insertAward(vo);
		AwardVO result = mapper.seletAwardDetail("AWD001");
		log.info("{}", result);
		
	}

	@Test
	void testUpdateAward() {
		AwardVO vo = new AwardVO();
		vo.setAwardCode("AWAD000006");
		vo.setResumeNo("RSM001");
		vo.setAwardName("프로젝트 대상");
		vo.setAwardDate("2014-08-12");
		vo.setHosting("고용노동부");
		
		mapper.updateAward(vo);
		AwardVO result = mapper.seletAwardDetail("AWAD000006");
		log.info("{}", result);
	}

	@Test
	void testDeleteAward() {
		mapper.deleteAward("AWAD000006");
		AwardVO vo = mapper.seletAwardDetail("AWAD000006");
		log.info("{}", vo);
	}

}
