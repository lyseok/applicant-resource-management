package kr.or.ddit.mapper.community;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.community.CommuTagVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class CommuTagMapperTest {

	@Autowired
	CommuTagMapper mapper;
	
	@Test
	void testSelectCommuTagByPk() {
		assertDoesNotThrow(()->mapper.selectCommuTagByPk("CTAG000001", "CMBD000001"));
		
		log.info("{}", mapper.selectCommuTagByPk("CTAG000001", "CMBD000001"));
	}
	
	@Test
	void testSearchCommuTagTagList() {
		assertDoesNotThrow(()->mapper.selectCommuTagTagList("CMBD000001"));
		
		List<CommuTagVO> list = mapper.selectCommuTagTagList("CMBD000001");
		list.forEach(t->{
			log.info("{}", t);
		});
		
		assertEquals(1, list.size());
	}

	@Test
	void testSearchCommuTagBoardList() {
		assertDoesNotThrow(()->mapper.selectCommuTagBoardList("CTAG000001"));
		
		List<CommuTagVO> list = mapper.selectCommuTagBoardList("CTAG000001");
		list.forEach(t->{
			log.info("{}", t);
		});
		
		assertEquals(1, list.size());
	}
	
	
	@Test
	void testSelectCommuTagList() {
		assertDoesNotThrow(()->mapper.selectCommuTagList());
		
		List<CommuTagVO> list = mapper.selectCommuTagList();
		list.forEach(t->{
			log.info("{}", t);
		});
		
		assertEquals(1, list.size());
	}

	@Test
	void testInsertCommuTag() {
		CommuTagVO tag = new CommuTagVO();
		
		tag.setBoardNo("CMBD000001");

		assertEquals(1, mapper.insertCommuTag(tag));		
		
		log.info("{}", mapper.selectCommuTagByPk("CTAG000002", "CMBD000001"));

	}

	@Test
	void testDeleteCommuTag() {
		log.info("{}", mapper.deleteCommuTag("CTAG000002", "CMBD000001"));  //1이면 삭제 성공
		assertNull(mapper.selectCommuTagByPk("CTAG000002", "CMBD000001"));
	}

}
