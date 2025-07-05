package kr.or.ddit.mapper.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.common.CmnCodeGroupVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class CmnCodeGroupMapperTest {
	@Autowired
	CmnCodeGroupMapper mapper;
	
	@Test
	void testSelectCmnCodeGroupList() {
		List<CmnCodeGroupVO> list = mapper.selectCmnCodeGroupList();
		list.forEach(vo -> log.info("{}", vo));
		
		assertNotNull(list);		
	}
	@Test
	void testSelectCmnCodeGroupByPk() {
		CmnCodeGroupVO vo = mapper.selectCmnCodeGroupByPk("EDUC");
		log.info("{}", vo.getCmnCodeList());
		
		assertNotNull(vo);
	}
	@Test
	void testInsertCmnCodeGroup() {
		CmnCodeGroupVO vo = new CmnCodeGroupVO();
		
		vo.setCodeGroupNo("TEST");
		vo.setCodeGroupName("테스트");
		vo.setDescription("테스트입니다");
		vo.setUseYn("Y");
		
		assertEquals(mapper.insertCmnCodeGroup(vo), 1);
	}
	@Test
	void testUpdateCmnCodeGroup() {
		CmnCodeGroupVO vo = new CmnCodeGroupVO();
		
		vo.setCodeGroupNo("TEST");
		vo.setCodeGroupName("테스트수정");
		vo.setDescription("수정 테스트입니다");
		vo.setUseYn("N");
		
		assertEquals(mapper.updateCmnCodeGroup(vo), 1);
	}
	@Test
	void testDeleteCmnCodeGroup() {
		mapper.deleteCmnCodeGroup("TEST");
		
		assertNull(mapper.selectCmnCodeGroupByPk("TEST"));
	}

}
