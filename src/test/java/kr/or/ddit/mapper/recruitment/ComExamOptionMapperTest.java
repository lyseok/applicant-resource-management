package kr.or.ddit.mapper.recruitment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.recruitment.ComExamOptionVO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class ComExamOptionMapperTest {

	@Autowired
	ComExamOptionMapper mapper;
	
	@Test
	void testSelectComExamOptionList() {
		mapper.selectComExamOptionList().forEach(code -> {
			log.info("{}", code);		
		});
	}

	@Test
	void testSelectComExamOption() {
		log.info("{}", mapper.selectComExamOption("COOP000001"));
	}

	@Test
	void testInsertComExamOption() {
		ComExamOptionVO vo = new ComExamOptionVO();
		vo.setComQuestionsNo("COQE000001");
		vo.setComOptionContent("둘의 규모는 다르지 않다");
		vo.setComOptionCorrectYn("N");
		assertEquals(1, mapper.insertComExamOption(vo));
	}

	@Test
	void testUpdateComExamOption() {
		ComExamOptionVO vo = new ComExamOptionVO();
		vo.setComOptionContent("둘의 규모는 다르지 않다");
		vo.setComOptionCorrectYn("Y");
		vo.setComOptionNo("COOP000001");
		assertEquals(1, mapper.updateComExamOption(vo));
	}

	@Test
	void testDeleteComExamOption() {
		assertEquals(1, mapper.deleteComExamOption("COOP000001"));
	}

}
