package kr.or.ddit.mapper.community;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.queryParam;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.community.CompanyReviewQuestionVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class CompanyReivewQuestionMapperTest {

	@Autowired
	CompanyReivewQuestionMapper mapper;
	
	@Test
	void testSelectCompanyReview() {
		CompanyReviewQuestionVO vo = mapper.selectCompanyReviewQuestionByNo("CQ0001");
		log.info("{}", vo );
	}
	
	@Test
	void testSelectCompanyReviewQuestionList() {
		mapper.selectCompanyReviewQuestionList().forEach(list ->{
			log.info("{}", list);
		});
	}
	
	@Test
	void testSelectCompanyReviewQuestionListByNo() {
		mapper.selectCompanyReviewQuestionListByNo("CR0001").forEach(list ->{
			log.info("{}", list);
		});
	}

	@Test
	void testInsertCompanyReviewQuestionWithAnswer() {
		CompanyReviewQuestionVO vo = new CompanyReviewQuestionVO();
		
		vo.setCompanyReviewQuestionNo("CQ9999");
		vo.setCompanyReviewNo("CR0001");
		vo.setReviewSubjectCode("R1");
		vo.setCompanyReviewScore(1);
		
		mapper.insertCompanyReviewQuestionWithAnswer(vo);
		
		CompanyReviewQuestionVO vo2 = mapper.selectCompanyReviewQuestionByNo("CQ9999");
		log.info("{}",vo2);
		
	}
	
	@Test
	void testUpdateCompanyReview() {
		CompanyReviewQuestionVO vo = new CompanyReviewQuestionVO();
		
		vo.setCompanyReviewQuestionNo("CQ006");
		vo.setCompanyReviewNo("CR0001");
		vo.setReviewSubjectCode("R1");
		vo.setCompanyReviewScore(2);
		
		mapper.updateCompanyReviewQuetiion(vo);
	}
	
	
	@Test
	void testDeleteCompanyReview() {
		mapper.deleteCompanyReviewQuestion("CQ0001");
		assertNull(mapper.selectCompanyReviewQuestionByNo("CQ0001"));
	}

}
