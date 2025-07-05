package kr.or.ddit.mapper.common;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.common.BusinessregistrationVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class BusinessRegistrationMapperTest {
	@Autowired
	BusinessRegistrationMapper mapper;

	@Test
	void testSelectBusinessRegistrationByNo() {
		BusinessregistrationVO vo = mapper.selectBusinessRegistrationByPk("BR000001");
		log.info("{}", vo);
		
	}

	@Test
	void testSelectBusinessRegistrationList() {
		mapper.selectBusinessRegistrationList().forEach(list ->{
			log.info("{}", list);
		});
	}

	@Test
	void testInsertBusinessregistration() {
		BusinessregistrationVO vo = new BusinessregistrationVO();
		//vo.setBrNumber("BR00004");
		vo.setComName("오픈AI");
		vo.setUserName("corp01");
		vo.setFilePath("test/file");
		
		mapper.insertBusinessregistration(vo);
		
		List<BusinessregistrationVO> list = mapper.selectBusinessRegistrationList();
		log.info("{}", list);
		
	}

	@Test
	void testUpdateBusinessregistration() {
		BusinessregistrationVO vo = mapper.selectBusinessRegistrationByPk("BR000001");
		vo.setBrNumber("BR00005");
		vo.setComName("오픈AI수정");
		vo.setUserName("corp03");
		vo.setFilePath("test수정/file");
		
		mapper.updateBusinessregistration(vo);
		log.info("{}", vo);
	}

	@Test
	void testDeleteBusinessregistration() {
		mapper.deleteBusinessregistration("BR000001");
		
		assertNull(mapper.selectBusinessRegistrationByPk("BR000001"));
	}

}
