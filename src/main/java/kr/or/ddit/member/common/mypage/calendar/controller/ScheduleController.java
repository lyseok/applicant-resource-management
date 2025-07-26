package kr.or.ddit.member.common.mypage.calendar.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import kr.or.ddit.member.common.mypage.calendar.service.MemberSchedule;
import kr.or.ddit.vo.common.ScheduleVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/ajax/schedule")
public class ScheduleController {

	private final MemberSchedule service;
	
	@GetMapping("/recruit")
    public List<Map<String, Object>> getUserSchedule() {
        return service.getRecruitCalendar();
    }
	
	 // 사용자 일정 조회
    @GetMapping("/custom")
    public List<ScheduleVO> getUserSchedules() {
        return service.getMyPersonalSchedule();
    }

    // 등록
    @PostMapping("/custom")
    public void addSchedule(@RequestBody ScheduleVO schedule, @RequestParam String userId) {
    	service.addSchedule(schedule);
    }

    // 수정
    @PostMapping("/custom/{id}")
    public void updateSchedule(@PathVariable("id") Long id, @RequestBody ScheduleVO schedule) {
    	service.updateSchedule(schedule);
    }

    // 삭제
    @DeleteMapping("/custom/{id}")
    public void deleteSchedule(@PathVariable("id") String id) {
    	service.deleteSchedule(id);
    }
}
