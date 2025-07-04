package kr.or.ddit.mapper.community;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
		log.info("{}", mapper.selectCommuTagByPk("CTAG000001", "CMBD000004"));
	}
	
	@Test
	void testSearchCommuTagTagList() {
		assertDoesNotThrow(()->mapper.searchCommuTagTagList("CMBD000004"));
		
		List<CommuTagVO> list = mapper.searchCommuTagTagList("CMBD000004");
		list.forEach(t->{
			log.info("{}", t);
		});
	}

	@Test
	void testSearchCommuTagBoardList() {
		assertDoesNotThrow(()->mapper.searchCommuTagBoardList("CTAG000001"));
		
		List<CommuTagVO> list = mapper.searchCommuTagBoardList("CTAG000001");
		list.forEach(t->{
			log.info("{}", t);
		});
	}
	
	
	@Test
	void testSelectCommuTagList() {
		assertDoesNotThrow(()->mapper.selectCommuTagList());
		
		List<CommuTagVO> list = mapper.selectCommuTagList();
		list.forEach(t->{
			log.info("{}", t);
		});
	}

	@Test
	void testInsertCommuTag() {
		CommuTagVO tag = new CommuTagVO();
		
		tag.setBoardNo("CMBD000004");
		
		log.info("{}", mapper.selectCommuTagByPk("CTAG000001", "CMBD000004"));

		assertEquals(1, mapper.insertCommuTag(tag));		
	}

	@Test
	void testDeleteCommuTag() {
		log.info("{}", mapper.deleteCommuTag("CTAG000001", "CMBD000004"));  //1이면 삭제 성공
		mapper.deleteCommuTag("CTAG000001", "CMBD000004");
	}

}
