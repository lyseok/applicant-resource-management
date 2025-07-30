package kr.or.ddit.mapper.statistics;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanyStatisticsMapper {
	public Map<String, Object> selectRecruitmentStatusById(String userId);
}
