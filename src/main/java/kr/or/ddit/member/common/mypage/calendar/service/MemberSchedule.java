package kr.or.ddit.member.common.mypage.calendar.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.common.ScheduleVO;

public interface MemberSchedule {
	
	public List<Map<String, Object>> getRecruitCalendar();
	public List<Map<String, Object>> getMyPersonalSchedule();
	public void addSchedule(ScheduleVO vo);
	public void updateSchedule(ScheduleVO vo);
	public void deleteSchedule(String scheduleNo);
}
