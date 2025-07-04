package kr.or.ddit.mapper.community;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.community.InterviewInformationVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class InterviewInformationMapperTest {
	@Autowired
	InterviewInformationMapper mapper;

	@Test
	void testSelectInterviewInfromationList() {
		mapper.selectInterviewInfromationList().forEach(list ->{
			log.info("{}", list);
		});
	}

	@Test
	void testSelectInterviewInformation() {
		InterviewInformationVO vo = mapper.selectInterviewInformationByPk("");
		log.info("{}", vo);
	}

	@Test
	void testInsertInterviewInformation() {
		InterviewInformationVO vo  = new InterviewInformationVO();
		vo.setInterviewInformationNo("");
		vo.setEvaluation(null);
		vo.setInterviewLevel(null);
		vo.setInterviewLevel(null);
		vo.setInterviewType(null);
		vo.setInterviewContent(null);
		
		mapper.insertInterviewInformation(vo);
		InterviewInformationVO vo2 = mapper.selectInterviewInformationByPk("");
		
	}

	@Test
	void testUpdateInterviewInformation() {
		InterviewInformationVO vo  = new InterviewInformationVO();
		vo.setInterviewInformationNo("");
		vo.setEvaluation(null);
		vo.setInterviewLevel(null);
		vo.setInterviewLevel(null);
		vo.setInterviewType(null);
		vo.setInterviewContent(null);
		
		mapper.insertInterviewInformation(vo);
		InterviewInformationVO vo2 = mapper.selectInterviewInformationByPk("");
	}

	@Test
	void testDeleteInterviewInformation() {
		mapper.deleteInterviewInformation("");
		assertNull(mapper.selectInterviewInformationByPk(""));
	}

}
