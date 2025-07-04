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
	void testSearchPrjAnncBoardTagList() {
		assertDoesNotThrow(()->mapper.searchCommuTagList("CMBD000004"));
		
		List<CommuTagVO> list = mapper.searchCommuTagList("CMBD000004");
		list.forEach(t -> log.info("{}", t));
	}
	
	@Test
	void testSelectCommuTagList() {
		assertDoesNotThrow(()->mapper.selectCommuTagList("CMBD000004"));
		
		List<CommuTagVO> list = mapper.selectCommuTagList("CMBD000004");
		list.forEach(t->{
			log.info("{}", t);
		});
	}

	@Test
	void testInsertCommuTag() {
		CommuTagVO tag = new CommuTagVO();
		
		tag.setBoardNo("CMBD000004");
		
		log.info("{}", mapper.insertCommuTag(tag));  //1이면 등록성공?

		assertEquals(1, mapper.insertCommuTag(tag));		
	}

	@Test
	void testDeleteCommuTag() {
		log.info("{}", mapper.deleteCommuTag("CTAG000001", "CMBD000004"));  //1이면 삭제
		mapper.deleteCommuTag("CTAG000001", "CMBD000004");
	}

}
