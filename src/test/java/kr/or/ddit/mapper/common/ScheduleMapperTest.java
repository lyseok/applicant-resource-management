package kr.or.ddit.mapper.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.vo.common.ScheduleVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@SpringBootTest
class ScheduleMapperTest {
	@Autowired
	ScheduleMapper mapper;
	
	@Test
	void testSelectScheduleByNo() {
		mapper.selectScheduleByNo("SCDE000001");
	}

	@Test
	void testSelectScheduleList() {
		mapper.selectScheduleList().forEach(list ->{
			log.info("{}", list);
		});
	}

	@Test
	void testInsertSchedule() {
		ScheduleVO vo = new ScheduleVO();
		vo.setUserId("USR001");
		vo.setScheduleContent("테스트 스케줄");
		vo.setScheduleName("테스트 스케줄d");
		mapper.insertSchedule(vo);
		
		ScheduleVO vo2 = mapper.selectScheduleByNo("SCDE000001");
		log.info("{}", vo2);
	}

	@Test
	void testUpdateSchedule() {
		ScheduleVO vo = new ScheduleVO();
		vo.setUserId("USR001");
		vo.setScheduleContent("테스트 스케줄ㅇ");
		vo.setScheduleName("테스트 스케줄 수정.");
		vo.setScheduleNo("SCDE000001");
		mapper.updateSchedule(vo);
		ScheduleVO vo2 = mapper.selectScheduleByNo("SCDE000001");
		log.info("{}", vo2);
		
	}

	@Test
	void testDeleteSchedule() {
		mapper.deleteSchedule("SCDE000001");
		assertNull(mapper.selectScheduleByNo("SCDE000001"));
	}

}
