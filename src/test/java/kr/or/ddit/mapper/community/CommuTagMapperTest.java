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
	void testSelectCommuTag() {
		log.info("{}", mapper.selectCommuTag("CTAG000001", "CMBD000004"));
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

		assertEquals(1, mapper.insertCommuTag(tag));
		
		log.info("{}", mapper.selectCommuTag("CTAG000001", "CMBD000004"));
	}

	@Test
	void testDeleteCommuTag() {
		mapper.deleteCommuTag("CTAG000001", "CMBD000004");
		assertNull(mapper.selectCommuTag("CTAG000001", "CMBD000004"));
	}

}
