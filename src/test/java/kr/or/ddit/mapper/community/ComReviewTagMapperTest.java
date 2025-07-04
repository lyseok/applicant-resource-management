package kr.or.ddit.mapper.community;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.community.ComReviewTagVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class ComReviewTagMapperTest {
	
	@Autowired
	ComReviewTagMapper mapper;

	@Test
	void testSelectComReviewTagByNo(){
		ComReviewTagVO vo = new ComReviewTagVO();
		vo.setComReviewNo("");
		vo.setTagNo("");
		ComReviewTagVO vo2 =	mapper.selectComReviewTagByNo(vo);
		log.info("{}", vo);
	}

	@Test
	void testSelectComReviewTagList() {
		mapper.selectComReviewTagList().forEach(list ->{
			log.info("{}", list);
		});
	}

	@Test
	void testInsertComReviewTag() {
		ComReviewTagVO vo = new ComReviewTagVO();
		vo.setComReviewNo("CPRV000001");
		mapper.insertComReviewTag(vo);
		
		ComReviewTagVO vo2 = mapper.selectComReviewTagByNo(vo);
		log.info("{}", vo2);
	}

	@Test
	void testUpdateComReviewTag() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteComReviewTag() {
		fail("Not yet implemented");
	}

}
