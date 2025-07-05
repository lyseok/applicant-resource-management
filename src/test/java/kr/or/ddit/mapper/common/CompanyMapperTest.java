package kr.or.ddit.mapper.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.common.CompanyVO;
import kr.or.ddit.vo.common.UsersVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class CompanyMapperTest {

	@Autowired
	CompanyMapper mapper;
	
	@Test
	void testSelectCompanyById() {
		UsersVO user = mapper.selectCompanyById("USR002");
		log.info("{}", user);
	}

	void testSelectCompanyList() {
		mapper.selectCompanyList().forEach(list ->{
			log.info("{}", list);
		});
	}

	@Test
	void testInsertCompany() {

	    CompanyVO newCompany = new CompanyVO();
	    newCompany.setUserId("USR002");
	    newCompany.setComName("JUnit Company");
	    newCompany.setComInfo("Unit test company");
	    newCompany.setComNum("01012345678");
	    newCompany.setComEmail("junit@company.com");
	    newCompany.setComUrl("<http://junit.company.com>");
	    newCompany.setComCreateYear("2023");
	    newCompany.setComMem(50);
	    newCompany.setComLogo("logo.png");
	    newCompany.setComPayment("Y");
	    newCompany.setIndustryType("IT");

	    mapper.insertCompany(newCompany);

	   CompanyVO vo = mapper.selectCompanyById("USR002");
	   log.info("{}", vo);



	}

	@Test
	void testUpdateCompany() {
		 CompanyVO newCompany = new CompanyVO();
		 	newCompany.setUserId("USR002");
	        newCompany.setComName("JUnit Company");
	        newCompany.setComInfo("Unit test company");
	        newCompany.setComNum("01012345678");
	        newCompany.setComEmail("junit@company.com");
	        newCompany.setComUrl("<http://junit.company.com>");
	        newCompany.setComCreateYear("2023");
	        newCompany.setComMem(50);
	        newCompany.setComLogo("logo.png");
	        newCompany.setComPayment("N");
	        newCompany.setIndustryType("IT");

	      CompanyVO vo = mapper.selectCompanyById("USR002");
	      log.info("{}", vo);
	}

	@Test
	void testDeleteCompany() {
		mapper.deleteCompany("USR006");
		assertNull(mapper.selectCompanyById("USR006"));

	}
}
