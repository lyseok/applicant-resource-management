package kr.or.ddit.mapper.community;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.community.PassInformationVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@SpringBootTest
class PassInformationMapperTest {
	@Autowired
	PassInformationMapper mapper;
	
	@Test
	void testSelectPassInfromationList() {
		mapper.selectPassInfromationList().forEach(list ->{
			log.info("{}", list);
		});
	}

	@Test
	void testSelectPassInformationByPk() {
		PassInformationVO vo = mapper.selectPassInformationByPk("");
		log.info("{}", vo);
	}

	@Test
	void testInsertPassInformation() {
		PassInformationVO vo = new PassInformationVO();
		vo.setInterviewReviewNo("");
		vo.setInterviewQuestion("test");
		vo.setTip("tip");
		vo.setInterviewPassYn("Y");
		
		PassInformationVO vo2 = mapper.selectPassInformationByPk("");
		log.info("{}", vo2);
		
	}

	@Test
	void testUpdatePassInformation() {
		PassInformationVO vo = new PassInformationVO();
		vo.setPassInformationNo("");
		vo.setInterviewReviewNo("");
		vo.setInterviewQuestion("test");
		vo.setTip("tip");
		vo.setInterviewPassYn("N");
		
		PassInformationVO vo2 = mapper.selectPassInformationByPk("");
		log.info("{}", vo2);
	}

	@Test
	void testDeletePassInformation() {
		mapper.deletePassInformation("");
		assertNull(mapper.selectPassInformationByPk(""));
	}

}
