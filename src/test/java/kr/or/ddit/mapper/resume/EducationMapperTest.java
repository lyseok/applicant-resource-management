package kr.or.ddit.mapper.resume;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.resume.EducationVO;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootTest
class EducationMapperTest {

	@Autowired
	EducationMapper mapper;
	
	@Test
	void testselectEducationList() {
		List<EducationVO> list = mapper.selectEducationList();
		list.forEach(ed->{
			log.info("{}", ed);
		});
		assertNotNull(list);
	}

	@Test
	void testselectEducationDetail() {
		EducationVO vo = new EducationVO();
		vo.setEducationNo("EDCT000001");
		vo.setResumeNo("RESM000001");
		
		EducationVO result = mapper.selectEducationDetail(vo);
		log.info("{}", result);
		assertNotNull(result);		
	}

	@Test
	void testInsertEducation() {
		EducationVO vo = new EducationVO();
		vo.setResumeNo("RESM000002");
		vo.setHighestEducationCode("4");
		vo.setSchoolName("한남대학교");
		vo.setGraduateYn("1");
		vo.setTransferYn("N");
		vo.setEntranceDate("2020-03-25");
		vo.setGraduateDate("2024-04-23");
		vo.setLocation("대전광역시");
		vo.setDepartmentCode("1");
		mapper.insertEducation(vo);
		
		EducationVO result = mapper.selectEducationDetail(vo);
		log.info("{}", result);
		assertNotNull(result);
	}

	@Test
	void testUpdateEducation() {
		EducationVO vo = new EducationVO();
		vo.setEducationNo("EDCT000003");
		vo.setResumeNo("RESM000002");
		vo.setHighestEducationCode("4");
		vo.setSchoolName("수정후 삭제할거임!!!");
		vo.setGraduateYn("1");
		vo.setTransferYn("N");
		vo.setEntranceDate("2020-03-25");
		vo.setGraduateDate("2024-04-23");
		vo.setLocation("뿌우ㅐㅜ라엥");
		vo.setDepartmentCode("1");
		int result = mapper.updateEducation(vo);
		
		assertEquals(1, result);
	}

	@Test
	void testDeleteEducation() {
		EducationVO vo = new EducationVO();
		vo.setEducationNo("EDCT000002");
		vo.setResumeNo("RESM000002");
		int result = mapper.deleteEducation(vo);
		assertEquals(1, result);
	}

}
