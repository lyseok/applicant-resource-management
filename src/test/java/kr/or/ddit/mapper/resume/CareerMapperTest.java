package kr.or.ddit.mapper.resume;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.resume.CareerVO;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootTest
class CareerMapperTest {
	@Autowired
	CareerMapper mapper;
	
	
	@Test
	void testselectCareerList() {
		mapper.selectCareerList().forEach(cr -> {
			log.info("{}", cr);
		});
	}

	@Test
	void testselectCareerDetail() {
		log.info("{}",mapper.selectCareerDetail("CAR001"));
	}

	@Test
	void testInsertCareer() {
		CareerVO vo = new CareerVO();
		vo.setResumeNo("RSM002");
		vo.setJobCode("1");
		vo.setStartWorkDate("2020-12-28");
		vo.setTenure("Y");
		vo.setFreelancer("N");
		
		mapper.insertCareer(vo);
		log.info("{}",mapper.selectCareerDetail("CAR001"));
	}

	@Test
	void testUpdateCareer() {
		CareerVO vo = new CareerVO();
		vo.setCareerNo("CARE000004");
		vo.setResumeNo("RSM001");
		vo.setJobCode("80");
		vo.setStartWorkDate("2020-12-28");
		vo.setTenure("Y");
		vo.setFreelancer("N");
		
		mapper.updateCareer(vo);
		log.info("{}",mapper.selectCareerDetail(vo.getCareerNo()));
	}

	@Test
	void testDeleteCareer() {
		mapper.deleteCareer("CAR001");
	}

}
