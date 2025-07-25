package kr.or.ddit.mapper.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.ScheduleVO;

@Mapper
public interface ScheduleMapper {
	public ScheduleVO selectScheduleByNo(String scheduleNo);
	public List<ScheduleVO> selectMySchedule(String userId);
	public List<ScheduleVO> selectScheduleList();
	public int insertSchedule(ScheduleVO schedule);
	public int updateSchedule(ScheduleVO schedule);
	public int deleteSchedule(String scheduleNo);
	public List<Map<String, Object>> findmemberRecruitSchedule(String userId);
	public List<Map<String, Object>> selectCompanyScrab(String userId);
	public List<Map<String, Object>> selectRecruitScrab(String userId);
	public List<ScheduleVO> selectMyScheduleById(String userId);
}
