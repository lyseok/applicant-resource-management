package kr.or.ddit.mapper.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.common.TagVO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class TagMapperTest {

	@Autowired
	TagMapper mapper;

	@Test
	void testSelectTagList() {
		mapper.selectTagList().forEach(code -> {
			log.info("{}", code);
		});
	}

	@Test
	void testSelectTagByPk() {
		TagVO TV = new TagVO();
		
		TV.setTagName("TTEESSTT");
		TV.setTagNo("19899");
		
		mapper.selectTagByPk(TV);
	}

	@Test
	void testInsertTag() {
		TagVO TV = new TagVO();

		TV.setTagName("TTEESSTT");
		

		mapper.insertTag(TV);
	}

	@Test
	void testUpdateTag() {
		TagVO TV = new TagVO();

		TV.setTagName("TEST");
		TV.setTagNo("19899");
		
		mapper.updateTag(TV);
	}

	@Test
	void testDeleteTag() {
		TagVO TV = new TagVO();

		TV.setTagName("TEST");
		TV.setTagNo("19899");
		
		mapper.deleteTag(TV);
	}

}
