package kr.or.ddit.recruitment.live.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.mapper.recruitment.LiveRecruitmentMapper;
import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;
import lombok.extern.slf4j.Slf4j;
@SpringBootTest
@Slf4j
class LiveRecruitmentServiceImplTest {

	@Autowired
	LiveRecruitmentMapper mapper;
	 
	@Test
	void testReadRecruitmentList() {
		log.info("결과1 : {}");
	}

	@Test
	void testReadRecruitmentNotice() {
		Optional<RecruitmentNoticeVO> Rdetail = mapper.selectLiveRecruitment("");
		log.info("결과2 : {}", Rdetail.toString());
	}

	@Test
	void testSalaryRecruitment() {
		List<RecruitmentNoticeVO> Rdetail = mapper.salaryRecruitment("");
		for(int i=0; i<=Rdetail.size(); i++) {
		log.info("결과3 : {}", Rdetail.get(i));
		}
	}

	@Test
	void testSelectliveRecruitmentDetail() {
		RecruitmentNoticeVO Rdetail = mapper.selectliveRecruitmentDetail("recr000001");
		log.info("결과4 : {}", Rdetail.getJobCode());
		log.info("결과4 : {}", Rdetail.getRecPositionNumber());
		log.info("결과4 : {}", Rdetail.getRecruitmentChargerTel());
		log.info("결과4 : {}", Rdetail.getRecruitmentNo());
		log.info("결과4 : {}", Rdetail.getUserId());
		log.info("결과4 : {}", Rdetail.getWelfare());
		log.info("결과4 : {}", Rdetail.getRecruitmentDesk());
	}

	@Test
	void testLiveRecruitmentServiceImpl() {
		log.info("결과 : {}");
	}

}
