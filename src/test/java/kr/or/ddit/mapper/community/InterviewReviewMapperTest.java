package kr.or.ddit.mapper.community;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.community.InterviewInformationVO;
import kr.or.ddit.vo.community.InterviewReviewVO;
import kr.or.ddit.vo.community.PassInformationVO;
import kr.or.ddit.vo.recruitment.InterviewVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class InterviewReviewMapperTest {

	@Autowired
	private InterviewReviewMapper mapper;
	
	@Test
	void testSelectInterviewList() {
		String id = "EMP001";
		
		mapper.selectInterviewList(id).forEach(iReview ->{
			log.info("{}", iReview);
		});
		
		
	}
	
	@Test
	void testSelectInterviewReview() {
		InterviewReviewVO vo = mapper.selectInterviewReview("RV001");
		
		log.info("{}", vo);
	}
	
	
	@Test
	void testSelectInterviewReviewListUser() {
		mapper.selectInterviewReviewListUser("USR001").forEach(u ->{
			log.info("{}", u);
		});
		
	}
	
	@Test 
	void testSelectInterviewReviewListCom(){
		mapper.selectInterviewReviewListCom("EMP001").forEach(c ->{
			log.info("{}", c);
		});
		
	}
	
	
	@Test 
	void testInsertInterviewReview(){
		   InterviewVO interview = new InterviewVO();
		    interview.setInterviewNo("INTV000002");
		    interview.setProcessNo("PROC001");
		    interview.setUserId("TESTUSER");
		    interview.setInterviewDate("250702");
		    interview.setInterviewLocation("서울본사");
		    interview.setInterviewType("M"); 
		    interview.setInterviewPassScore("PASS");
		    //interviewMapper.insertInterview(interview);

		
		
		 InterviewReviewVO review = new InterviewReviewVO();
	        review.setInterviewNo("INTV000002");
	        review.setInterviewReviewNo("RV002");
	        review.setComId("EMP002");
	        review.setJobCode("JOB002");
	        review.setInterviewDate("250702");
	        review.setUserId("TESTUSER");
	
	      
	        // Act
	        int result = mapper.insertInterviewReview(review);
	        log.info("{}", result);
	        mapper.selectInterviewReviewListUser("TESTUSER").forEach(u ->{
				log.info("{}", u);
			});
	}
	
	
	
	
	
	@Test
	void testUpdateStatusDelete() {
		
	}
	
	@Test
	void testDeleteInfromation() {
		mapper.deleteInterviewReview("INTV000001");
		
		assertNull(mapper.selectInterviewReview("INTV000001"));
		
	}
	
	
	
	
	

}
