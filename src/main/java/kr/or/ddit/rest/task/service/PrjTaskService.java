package kr.or.ddit.rest.task.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.project.PrjTaskStatisticsVO;
import kr.or.ddit.vo.project.PrjTaskVO;

public interface PrjTaskService {
	public List<PrjTaskVO> readProjectTaskList(String prjNo, String status, String userId);
	public PrjTaskVO selectTaskByNo(@Param("taskNo") String taskNo);
	public PrjTaskVO createTask(PrjTaskVO prjTaskVO);
	public PrjTaskVO updateTask(String taskNo, PrjTaskVO prjTask);
	public int deleteTask(String taskNo);
	public PrjTaskVO updateTaskStatus(String taskNo, String taskStatus);
	public PrjTaskVO updateTaskProgress(String taskNo, String progressRate);
	public PrjTaskStatisticsVO getTaskStatistics(String prjNo);
}
