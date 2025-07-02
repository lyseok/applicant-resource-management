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
		
		mapper.selectInterviewList(id).forEach(iReview ->{
			log.info("{}", iReview);
		});
		
		
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
	void testDeleteInfromation() {
		
	}
	
	
	
	
	

}
