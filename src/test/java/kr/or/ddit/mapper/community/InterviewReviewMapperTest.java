package kr.or.ddit.mapper.community;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.community.InterviewInformationVO;
import kr.or.ddit.vo.community.InterviewReviewVO;
import kr.or.ddit.vo.community.PassInformationVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class InterviewReviewMapperTest {

	@Autowired
	private InterviewReviewMapper mapper;
	
	@Test
	void testSelectInterviewList() {
		String id = "EMP001";
		
		assertDoesNotThrow(()->mapper.selectInterviewList(id));
	}
	
	
	@Test
	void testSelectInterviewReviewList() {
		
	}
	
	
	@Test 
	void testInsertInterviewReview(){
		 InterviewReviewVO review = new InterviewReviewVO();
	        review.setInterviewNo("INTV000001");
	        review.setInterviewReviewNo("RV001");
	        review.setComId("EMP001");
	        review.setJobCode("JOB001");
	        review.setInterviewDate("250702");
	      
	        // Act
	        int result = mapper.insertInterviewReview(review);

	        // Assert
	        assertEquals(1, result, "insertInterviewReview should return 1");
	        assertNotNull(review.getInterviewReviewNo(), "interviewReviewNo should be populated");
	        log.info("Inserted review ID: {}", review.getInterviewReviewNo());
	}
	
	
	@Test 
	void testInsertInterviewReviewInfromation(){
		// First insert a review to get a valid interviewReviewNo
        InterviewReviewVO review = new InterviewReviewVO();
        review.setInterviewNo("INTV000001");
        review.setComId("EMP001");
        review.setJobCode("JOB001");
        review.setInterviewDate("2025-07-02 10:00");
      
        mapper.insertInterviewReview(review);
        String reviewNo = review.getInterviewReviewNo();
        
        // Arrange information
        InterviewInformationVO info = new InterviewInformationVO();
        info.setInterviewReviewNo(reviewNo);
        info.setEvaluation("A");
        info.setInterviewLevel("1");
        info.setInterviewType("대");
        info.setInterviewContent("Detailed content test.");

        // Act
        int result = mapper.insertInterviewInformation(info);

        // Assert
        assertEquals(1, result, "insertInterviewInformation should return 1");
        assertNotNull(info.getInterviewInformationNo(), "interviewInformationNo should be populated");
        log.info("Inserted information ID: {}", info.getInterviewInformationNo());
	}
	
	@Test 
	void testInsertPassInfromation(){
	
	    PassInformationVO pass = new PassInformationVO();
	    pass.setInterviewReviewNo("P001");
	    pass.setInterviewReviewNo("INTV000001");
	    pass.setInterviewQuestion("면접 질문");
	    pass.setTip("잘 준비해");
	    pass.setInterviewPassYn("Y");


        // Act
        int result = mapper.insertPassInformation(pass);

        // Assert
        assertEquals(1, result, "insertPassInformation should return 1");
        log.info("Inserted pass information ID: {}", result);
	}
	
	
	@Test
	void testDeleteInfromation() {
		
	}
	
	
	
	
	

}
