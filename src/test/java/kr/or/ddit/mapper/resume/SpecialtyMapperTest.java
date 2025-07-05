package kr.or.ddit.mapper.resume;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.resume.SpecialtyVO;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootTest
class SpecialtyMapperTest {
	@Autowired
	SpecialtyMapper mapper;
	
	@Test
	void testSelectSpecialtyList() {
		List<SpecialtyVO> list = mapper.selectSpecialtyList();
		list.forEach(vo -> {
			log.info("{}", vo);
		});
		assertNotNull(list);
	}

	@Test
	void testSelectSpecialtyDetail() {
		SpecialtyVO vo = new SpecialtyVO();
		vo.setEducationNo("SPCL000001");
		vo.setResumeNo("RESM000001");
		SpecialtyVO result = mapper.selectSpecialtyDetail(vo);
		log.info("{}", result);
		assertNotNull(result);
	}

	@Test
	void testInsertSpecialty() {
		SpecialtyVO vo = new SpecialtyVO();
		vo.setEducationNo("EDCT000002");
		vo.setResumeNo("RESM000001");
		vo.setMainMajor("컴퓨터공학");
		vo.setSubMajor("인공지능");
		vo.setSubMajorCode("3");
		int result = mapper.insertSpecialty(vo);
		log.info("{}", mapper.selectSpecialtyDetail(vo));
		
		assertEquals(1, result);
	}

	@Test
	void testUpdateSpecialty() {
		SpecialtyVO vo = new SpecialtyVO();
		vo.setEducationNo("EDCT000002");
		vo.setResumeNo("RESM000001");
		vo.setMainMajor("전자공학");
		vo.setSubMajor("기계공학");
		vo.setSubMajorCode("3");
		int result = mapper.updateSpecialty(vo);
		log.info("{}", mapper.selectSpecialtyDetail(vo));
		
		assertEquals(1, result);
	}

	@Test
	void testDeleteSpecialty() {
		SpecialtyVO vo = new SpecialtyVO();
		vo.setEducationNo("EDCT000002");
		vo.setResumeNo("RESM000001");
		int result = mapper.deleteSpecialty(vo);
		log.info("{}", mapper.selectSpecialtyDetail(vo));
		
		assertEquals(1, result);
	}

}
