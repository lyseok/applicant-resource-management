package kr.or.ddit.mapper.resume;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.resume.ResumeVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class ResumeMapperTest {
	@Autowired
	ResumeMapper mapper;

	@Test
	void testSelectResumeList() {
		mapper.selectResumeList().forEach(re-> {
			log.info("{}", re);
		});
	}

	@Test
	void testSelectResumeDetail() {
		ResumeVO vo = mapper.selectResumeDetail("RESM000001");
		log.info("{}", vo);		
	}

	@Test
	void testInsertResume() {
		ResumeVO vo = new ResumeVO();
		vo.setUserId("testUser");
		vo.setUserName("현정이");
		vo.setBirth("2000-00-00");
		vo.setEmail("seok@gmail.com");
		vo.setTel("010-1234-1234");
		vo.setAddress("대전광역시 중구 오류동 1-1");
		vo.setResumeSubmitYn("Y");
		mapper.insertResume(vo);
		
		log.info("{}", mapper.selectResumeDetail(vo.getResumeNo()));
	}

	@Test
	void testUpdateResume() {
		ResumeVO vo = new ResumeVO();
		vo.setResumeNo("RESM000001");
		vo.setUserId("USR001");
		vo.setUserName("현정이");
		vo.setBirth("2000-00-00");
		vo.setEmail("hj@gmail.com");
		vo.setTel("010-1234-1234");
		vo.setAddress("대전광역시 중구 오류동 1-1");
		vo.setResumeSubmitYn("Y");
		mapper.updateResume(vo);
		
		log.info("{}", mapper.selectResumeDetail(vo.getResumeNo()));
	}

	@Test
	void testDeleteResume() {
		mapper.deleteResume("RESM000004");
		log.info("{}", mapper.selectResumeDetail("RESM000004"));
	}

}
