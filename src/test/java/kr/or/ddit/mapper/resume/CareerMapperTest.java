package kr.or.ddit.mapper.resume;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.resume.AwardVO;
import kr.or.ddit.vo.resume.CareerVO;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootTest
class CareerMapperTest {
	@Autowired
	CareerMapper mapper;
	
	
	@Test
	void testselectCareerList() {
		mapper.selectCareerList("RESM000001").forEach(cr -> {
			log.info("{}", cr);
		});
	}

	@Test
	void testselectCareerDetail() {
		CareerVO vo = new CareerVO();
		vo.setResumeNo("RESM000001");
		vo.setCareerNo("CAR0000008");
		log.info("{}", mapper.selectCareerDetail(vo));
	}

	@Test
	void testInsertCareer() {
		CareerVO vo = new CareerVO();
		vo.setResumeNo("CARE000011");
		vo.setJobCode("1");
		vo.setStartWorkDate("2020-12-28");
		vo.setTenure("Y");
//		vo.setFreelancer("N");
		
		mapper.insertCareer(vo);
		log.info("{}", mapper.selectCareerDetail(vo));
	}

	@Test
	void testUpdateCareer() {
		CareerVO vo = new CareerVO();
		vo.setCareerNo("CARE000004");
		vo.setResumeNo("RSM001");
		vo.setJobCode("80");
		vo.setStartWorkDate("2020-12-28");
		vo.setTenure("Y");
//		vo.setFreelancer("N");
		
		mapper.updateCareer(vo);
		log.info("{}", mapper.selectCareerDetail(vo));
	}

	@Test
	void testDeleteCareer() {
		mapper.deleteCareer("CAR001");
	}

}
