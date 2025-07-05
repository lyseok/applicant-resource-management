package kr.or.ddit.mapper.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.common.InduClassCodeVO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class InduClassCodeMapperTest {
	@Autowired
	InduClassCodeMapper mapper;
	
	@Test
	void testSelectInduClassCodeList() {
		List<InduClassCodeVO> list = mapper.selectInduClassCodeList();
		list.forEach(vo -> log.info("{}", vo));
		
		assertNotNull(list);
	}
	
	@Test
	void testSelectInduClassCodeBuPk() {
		InduClassCodeVO vo = mapper.selectInduClassCodeBuPk("1");
		log.info("{}", vo);
		
		assertNotNull(vo);
	}
	
	@Test
	void testInsertInduClassCode() {
		InduClassCodeVO vo = new InduClassCodeVO();
		String name = "테스트";
		vo.setInduClassName(name);
		
		assertEquals(mapper.insertInduClassCode(vo), 1);
	}
	
	@Test
	void testUpdateInduClassCode() {
		InduClassCodeVO vo = new InduClassCodeVO();
		String name = "테스트수정";
		vo.setInduClassNo("INCC000011");
		vo.setInduClassName(name);
		
		assertEquals(mapper.updateInduClassCode(vo), 1);
	}
	
	@Test
	void testDeleteInduClassCode() {
		mapper.deleteInduClassCode("INCC000011");
		
		assertNull(mapper.selectInduClassCodeBuPk("INCC000011"));
	}
}
