package kr.or.ddit.mapper.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.common.InduCodeVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class InduCodeMapperTest {
	@Autowired
	InduCodeMapper mapper;
	
	@Test
	void testSelectInduCodeList() {
		List<InduCodeVO> list = mapper.selectInduCodeList();
		list.forEach(vo -> log.info("{}", vo));
		
		assertNotNull(list);
	}
	@Test
	void testSelectInduCodeByPk() {
		InduCodeVO vo = mapper.selectInduCodeByPk("INNO001002");
		log.info("{}", vo);
		
		assertNotNull(vo);
	}
	@Test
	void testInsertInduCode() {
		InduCodeVO vo = new InduCodeVO();
		String name = "테스트";
		
		vo.setInduName(name);
		vo.setInduClassNo("INCC000001");
		assertEquals(mapper.insertInduCode(vo), 1);
	}
	@Test
	void testUpdateInduCode() {
		InduCodeVO vo = new InduCodeVO();
		String name = "테스트수정";
		
		vo.setInduNo("INCC000123");
		vo.setInduName(name);
		vo.setInduClassNo("INCC000001");
		
		assertEquals(mapper.updateInduCode(vo), 1);
	}
	@Test
	void testDeleteInduCode() {
		mapper.deleteInduCode("INCC000123");
		
		assertNull(mapper.selectInduCodeByPk("INCC000123"));
	}

}
