package kr.or.ddit.mapper.project;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.project.WorkHistoryVO;

@Mapper
public interface WorkHistoryMapper {
	public List<WorkHistoryVO> selectWorkHistoryList();
	public List<WorkHistoryVO> searchWorkHistoryList(String prjNo);
	public WorkHistoryVO selectWorkHistoryByPk(String workHistNo);
	
	public List<WorkHistoryVO> selectWorkHistoryByPrjNo(String prjNo);
	public int insertWorkHistory(WorkHistoryVO workHistory);
}
