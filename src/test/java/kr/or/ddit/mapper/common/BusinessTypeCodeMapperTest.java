package kr.or.ddit.mapper.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.common.BusinessTypeCodeVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class BusinessTypeCodeMapperTest {
	@Autowired
	BusinessTypeCodeMapper mapper;
	
	@Test
	void testSelectBusinessTypeCodeList() {
		List<BusinessTypeCodeVO> list = mapper.selectBusinessTypeCodeList();
		list.forEach(vo -> log.info("{}", vo));
		
		assertNotNull(list);
	}
	@Test
	void testSelectBusinessTypeCode() {
		BusinessTypeCodeVO vo = mapper.selectBusinessTypeCode("BNTC011804");
		
		log.info("{}", vo);
		
		assertNotNull(vo);
	}
	@Test
	void testInsertBusinessTypeCode() {
		BusinessTypeCodeVO vo = new BusinessTypeCodeVO();
		String name = "테스트";
		
		vo.setBusinessTypeName(name);
		vo.setInduNo("INNO000118");
		assertEquals(mapper.insertBusinessTypeCode(vo), 1);
		
	}
	@Test
	void testUpdateBusinessTypeCode() {
		BusinessTypeCodeVO vo = new BusinessTypeCodeVO();
		String name = "테스트수정";
		
		vo.setBusinessTypeNo("BNTC011808");
		vo.setBusinessTypeName(name);
		vo.setInduNo("INNO000118");
		assertEquals(mapper.updateBusinessTypeCode(vo), 1);
	}
	@Test
	void testDeleteBusinessTypeCode() {
		mapper.deleteBusinessTypeCode("BNTC011808");
		
		assertNull(mapper.selectBusinessTypeCode("BNTC011808"));
	}

}
