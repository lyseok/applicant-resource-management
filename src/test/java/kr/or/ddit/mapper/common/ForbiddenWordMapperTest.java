package kr.or.ddit.mapper.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.common.ForbiddenWordVO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class ForbiddenWordMapperTest {

	@Autowired
	ForbiddenWordMapper mapper;
	
	@Test
	void testSelectForbiddenWordList() {
		mapper.selectForbiddenWordList().forEach(code->{
			log.info("{}",code);
		});
	}

	@Test
	void testSelectForbiddenWordByPk() {
ForbiddenWordVO FW = new ForbiddenWordVO();
		
		FW.setForbiddenWordNo("123");
		FW.setWord("TEST");
		
		mapper.selectForbiddenWordByPk(FW);
	}

	@Test
	void testInsertForbiddenWord() {
		ForbiddenWordVO FW = new ForbiddenWordVO();
		
		FW.setWord("TEST");
		
		mapper.insertForbiddenWord(FW);
	}

	@Test
	void testUpdateForbiddenWord() {
		ForbiddenWordVO FW = new ForbiddenWordVO();
		
		FW.setForbiddenWordNo("123");
		FW.setWord("TEST999");
		
		mapper.updateForbiddenWord(FW);
	}

	@Test
	void testDeleteForbiddenWord() {
ForbiddenWordVO FW = new ForbiddenWordVO();
		
		FW.setForbiddenWordNo("123");
		FW.setWord("TEST999");
		
		mapper.deleteForbiddenWord(FW);
	}

}
