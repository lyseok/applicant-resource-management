package kr.or.ddit.mapper.recruitment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.recruitment.VideoInterviewVO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class VideoInterviewMapperTest {

	@Autowired
	VideoInterviewMapper mapper;
	
	@Test
	void testSelectVideoInterviewList() {
		mapper.selectVideoInterviewList().forEach(code -> {
			log.info("{}", code);		
		});

	}

	@Test
	void testSelectVideoInterview() {
		log.info("{}", mapper.selectVideoInterview("VITV000001"));
	}

	@Test
	void testInsertVideoInterview() {
		VideoInterviewVO vo = new VideoInterviewVO();
//		vo.setInterviewNo("INTV000001");
//		vo.setVideoInterviewStartTime("2025-07-15 10:00");
//		vo.setVideoInterviewEndTime("2025-07-15 11:00");
//		vo.setVideoInterviewStatus("1");
//		vo.setVideoInterviewUrl("url");
//		assertEquals(1, mapper.insertVideoInterview(vo));
	}

	@Test
	void testUpdateVideoInterview() {
		VideoInterviewVO vo = new VideoInterviewVO();
//		vo.setInterviewNo("INTV000001");
//		vo.setVideoInterviewStartTime("2025-07-15 11:00");
//		vo.setVideoInterviewEndTime("2025-07-15 12:00");
//		vo.setVideoInterviewStatus("2");
//		vo.setVideoInterviewUrl("url");
//		vo.setVideoInterviewNo("VITV000001");
//		assertEquals(1, mapper.updateVideoInterview(vo));
	}

	@Test
	void testDeleteVideoInterview() {
		assertEquals(1, mapper.deleteVideoInterview("VITV000001"));
	}

}
