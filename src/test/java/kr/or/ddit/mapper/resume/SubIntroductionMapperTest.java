package kr.or.ddit.mapper.resume;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.resume.SubIntroductionVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class SubIntroductionMapperTest {
	
	@Autowired
	private SubIntroductionMapper mapper;
	
	@Test
	void testSelectSubIntroductionList() {
		List<SubIntroductionVO> subList = mapper.selectSubIntroductionList("RESM000001");
		subList.forEach(sub -> {
			log.info("{} ", sub);
		});
	}

	@Test
	void testSelectSubIntroductionDetail() {
		SubIntroductionVO vo = new SubIntroductionVO();
		vo.setIntroductionNo("SIND000001");
		vo.setResumeNo("RESM000001");
		
		log.info("{}", mapper.selectSubIntroductionDetail(vo));	
	}

	@Test
	void testInsertSubIntroduction() {
		SubIntroductionVO vo = new SubIntroductionVO();
		vo.setResumeNo("RSM001");
		vo.setIntroductionNo("INT0000005");
		mapper.insertSubIntroduction(vo);
		log.info("{}", vo);		
	}

	@Test
	void testUpdateSubIntroduction() {
		SubIntroductionVO vo = new SubIntroductionVO();
		vo.setResumeNo("RSM003");
		vo.setIntroductionNo("INT0000001");
		mapper.updateSubIntroduction(vo);
		log.info("{}", vo);
	}

	@Test
	void testDeleteSubIntroduction() {
		String no = "SUBINT0005";
		mapper.deleteSubIntroduction(no);
		/* assertNull(mapper.selectSubIntroductionDetail(no)); */
	}

}
