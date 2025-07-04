package kr.or.ddit.mapper.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.common.CmnCodeGroupVO;
import kr.or.ddit.vo.common.CmnCodeVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class CmnCodeMapperTest {
	@Autowired
	CmnCodeMapper mapper;
	
	@Test
	void testSelectCmnCodeByPk() {
		CmnCodeVO vo = mapper.selectCmnCodeByPk("EDUC-002");
		log.info("{}", vo);
		
		assertNotNull(vo);
	}
	@Test
	void testInsertCmnCode() {
		CmnCodeVO vo = new CmnCodeVO();
		vo.setCodeDetailNo("TEST-001");
		vo.setCodeGroupNo("EDUC");
		vo.setCodeName("테스트");
		vo.setUseYn("Y");
		
		assertEquals(mapper.insertCmnCode(vo), 1);
	}
	@Test
	void testUpdateCmnCode() {
		CmnCodeVO vo = new CmnCodeVO();
		vo.setCodeDetailNo("TEST-001");
		vo.setCodeGroupNo("EDUC");
		vo.setCodeName("테스트수정");
		vo.setUseYn("Y");
		
		assertEquals(mapper.updateCmnCode(vo), 1);
	}
	@Test
	void testDeleteCmnCode() {
		mapper.deleteCmnCode("TEST-001");
		
		assertNull(mapper.selectCmnCodeByPk("TEST-001"));
	}

}
