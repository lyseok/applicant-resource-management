package kr.or.ddit.member.common.mypage.subIntoruction.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.mapper.resume.IntroductionMapper;
import kr.or.ddit.mapper.resume.SubIntroductionMapper;
import kr.or.ddit.vo.resume.SubIntroductionVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class SubIntroductionServiceImplTest {
	@Autowired
	SubIntroductionMapper mapper; 
	
	@Test
	void testReadSubIntroductionList() {
		mapper.selectSubIntroductionList().forEach(itrd ->{
			log.info("{}", itrd);
		});
	}

	@Test
	void testReadSubIntroductionDetail() {
		String subIntroductionNo = "SUBINT0004";
		SubIntroductionVO vo = mapper.selectSubIntroductionDetail(subIntroductionNo);

		log.info("{}", vo);	
	}

	@Test
	void testCreateSubIntroduction() {
		SubIntroductionVO vo = new SubIntroductionVO();
		vo.setResumeNo("RSM001");
		vo.setIntroductionNo("INT0000005");
		mapper.insertSubIntroduction(vo);
		log.info("{}", vo);		
	}

	@Test
	void testUpdateSubIntroduction() {
		SubIntroductionVO vo = new SubIntroductionVO();
		vo.setSubIntroductionNo("SUBINT0005");
		vo.setResumeNo("RSM001");
		vo.setIntroductionNo("INT0000001");
		mapper.updateSubIntroduction(vo);
		log.info("{}", vo);
	}

	@Test
	void testDeleteSubIntroduction() {
		String no = "SUBINT0005";
		mapper.deleteSubIntroduction(no);
		assertNull(mapper.selectSubIntroductionDetail(no));
	}

}
