package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.JobVO;
@Mapper
public interface JobMapper {
	public List<JobVO> selectJobList();

	public JobVO selectJobByPk(JobVO vo);
	
	public List<JobVO> selectJobByTopJob(String topJobCode);
	
	public int insertJob(JobVO vo);
	
	public int updateJob(JobVO vo);
	
	public int deleteJob(JobVO vo);
}
