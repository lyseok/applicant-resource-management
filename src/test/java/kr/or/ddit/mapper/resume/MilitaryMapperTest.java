package kr.or.ddit.mapper.resume;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.resume.MilitaryVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class MilitaryMapperTest {
	@Autowired
	MilitaryMapper mapper;
	
	@Test
	void testselectMilitaryList() {
		mapper.selectMilitaryList().forEach(mil -> {
			log.info("{}", mil);
		});
	}

	@Test
	void testselectMilitaryDetail() {
		MilitaryVO vo =  mapper.selectMilitaryDetail("MLT001");
		log.info("{}", vo);
	}

	@Test
	void testInsertMilitary() {
		MilitaryVO vo = new MilitaryVO();
		vo.setResumeNo("RSM001");
		vo.setServiceCategoryCode("A");
		vo.setMilitaryTypeCode("A1");
		vo.setMilitaryRankCode("R4"); // 계급
		vo.setDischargeCode("D1"); // 전역사유
		vo.setMilitaryStartDate("20231101");
		vo.setMilitaryEndDate("20250101");
		vo.setMilitaryReason("안알랴줌");	// 병역사유
		mapper.insertMilitary(vo);
		mapper.selectMilitaryDetail("MLT001");
		log.info("{}", vo);
		
	}

	@Test
	void testUpdateMilitary() {
		MilitaryVO vo = new MilitaryVO();
		vo.setMilitaryNo("MLT0000002");
		vo.setResumeNo("RSM002");
		vo.setServiceCategoryCode("A");
		vo.setMilitaryReason("병역사유 수정했지롱");	// 병역사유
		mapper.updateMilitary(vo);
		mapper.selectMilitaryDetail("MLT0000002");
		log.info("{}", vo);
	}

	@Test
	void testDeleteMilitary() {
		MilitaryVO vo = new MilitaryVO();
		mapper.deleteMilitary("MLT0000003");
		mapper.selectMilitaryDetail("MLT0000003");
	}

}
