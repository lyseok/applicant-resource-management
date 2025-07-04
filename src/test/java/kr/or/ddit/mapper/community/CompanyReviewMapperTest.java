package kr.or.ddit.mapper.community;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.community.CompanyReviewVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@SpringBootTest
class CompanyReviewMapperTest {
	@Autowired
	CompanyReviewMapper mapper;
	
	@Test
	void testSelectCompanyList() {
		mapper.selectCompanyReviewList().forEach(list ->{
			log.info("{}", list);
		});
	}

	@Test
	void testSelectCompanyReviewByPk() {
		mapper.selectCompanyReviewByPk("");
	}
	
	
	@Test
	void testInsertCompanyReview() {
		CompanyReviewVO vo = new CompanyReviewVO();
		vo.setComId("");
		vo.setJobCode("test");
		vo.setWorkingYn("Y");
		vo.setCompanyReviewStatus("Y");
		vo.setCompanyReviewOneLine("test");
		vo.setUserId("USR01");
		
		mapper.insertCompanyReview(vo);
		
		CompanyReviewVO vo2 = mapper.selectCompanyReviewByPk("CPRV000001");
		
		log.info("{}", vo2);
				
	}

	@Test
	void testDeleteCompanyReview() {
		mapper.deleteCompanyReview("");
		assertNull(mapper.selectCompanyReviewByPk(null));
	}

	@Test
	void testUpdateDeleteStatusMyCompanyReview() {
		
		CompanyReviewVO vo = new CompanyReviewVO();
		vo.setStatus("Y");
		vo.setCompanyReviewNo("");
		
		mapper.updateDeleteStatusMyCompanyReview(vo);
		
		CompanyReviewVO vo2 = mapper.selectCompanyReviewByPk("");
		
	}

}
