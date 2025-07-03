package kr.or.ddit.mapper.recruitment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class LiveRecruitmentMapperTest {
	
	@Autowired
	LiveRecruitmentMapper mapper;

	@Test
	void testReadRecruitmentNoticeList() {
		mapper.readRecruitmentNoticeList().forEach(code -> {
			log.info("{}", code);		
		});

	}

	@Test
	void testSelectliveRecruitmentDetail() {

		log.info("{}", mapper.selectliveRecruitmentDetail("recr999999"));
	}

	@Test
	void testInsertRecruitmentNotice() {
		RecruitmentNoticeVO input = new RecruitmentNoticeVO();
		input.setUserId("corp01");
		input.setRecruitmentTitle("test 제목입니다");
		input.setJobCode("CD001");
		input.setYearCode("Y1");
		input.setRecContent("테스트하는 업무입니다.");
		input.setCityCode("118000");
		input.setDistrictCode("118000");
		input.setRecruitmentChargerTel("010-5555-5555");
		input.setRecruitmentSalary("3000만원");
		input.setRecruitmentDesk("인터넷");
		input.setRecruitmentReceiptStart("2025/07/15");
		input.setRecruitmentFinishDate("2025/07/30");
		
		assertEquals(1, mapper.insertRecruitmentNotice(input)); 
	}

	@Test
	void testUpdateRecruitmentNotice() {
		RecruitmentNoticeVO input = new RecruitmentNoticeVO();
		
		input.setRecruitmentTitle("test 제목 업데이트!");
		input.setUserId("corp01");
		input.setJobCode("CD001");
		input.setYearCode("Y1");
		input.setRecContent("테스트하는 업무입니다.");
		input.setCityCode("118000");
		input.setDistrictCode("118000");
		input.setRecruitmentChargerTel("010-5555-5555");
		input.setRecruitmentSalary("3000만원");
		input.setRecruitmentDesk("인터넷");
		input.setRecruitmentStartdate("2025/07/03");
		input.setRecruitmentReceiptStart("2025/07/15");
		input.setRecruitmentFinishDate("2025/07/30");
		input.setRecruitmentNo("recr888888");
		assertEquals(1, mapper.updateRecruitmentNotice(input));
	}

}
