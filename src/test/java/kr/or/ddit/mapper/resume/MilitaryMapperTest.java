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
	void testSeletMilitaryList() {
		mapper.seletMilitaryList().forEach(mil -> {
			log.info("{}", mil);
		});
	}

	@Test
	void testSeletMilitaryDetail() {
		MilitaryVO vo =  mapper.seletMilitaryDetail("MLT001");
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
		mapper.seletMilitaryDetail("MLT001");
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
		mapper.seletMilitaryDetail("MLT0000002");
		log.info("{}", vo);
	}

	@Test
	void testDeleteMilitary() {
		MilitaryVO vo = new MilitaryVO();
		mapper.deleteMilitary("MLT0000003");
		mapper.seletMilitaryDetail("MLT0000003");
	}

}
