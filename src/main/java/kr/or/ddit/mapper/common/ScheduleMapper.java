package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.ScheduleVO;

@Mapper
public interface ScheduleMapper {
	public ScheduleVO selectScheduleByNo(String scheduleNo);
	public List<ScheduleVO> selectScheduleList();
	public int insertSchedule(ScheduleVO schedule);
	public int updateSchedule(ScheduleVO schedule);
	public int deleteSchedule();
}
