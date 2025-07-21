package kr.or.ddit.mapper.project;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.project.PrjTaskStatisticsVO;
import kr.or.ddit.vo.project.PrjTaskVO;

@Mapper
public interface PrjTaskMapper {
	public List<PrjTaskVO> selectPrjTaskList();
	public PrjTaskVO selectPrjTaskByPk(String taskNo);
	public int insertPrjTask(PrjTaskVO prjTask);
	public int updatePrjTask(PrjTaskVO prjTask);
	public int deletePrjTask(String taskNo);
	
	public List<PrjTaskVO> selectProjectTaskList(
			@Param("prjNo") String prjNo,
            @Param("status") String status,
            @Param("userId") String userId);
	public int insertTask(PrjTaskVO prjTaskVO);   // 생성
    public PrjTaskVO selectTaskByNo(String taskNo);
    public int updateTask(PrjTaskVO prjTask);
    public int updateTaskDelete(@Param("taskNo") String taskNo, @Param("deleteUserId") String deleteUserId);
    public int updateTaskStatus(@Param("taskNo") String taskNo, @Param("taskStatus") String taskStatus);
    int updateTaskProgress(@Param("taskNo") String taskNo, @Param("progressRate") String progressRate);
    public PrjTaskStatisticsVO selectTaskStatisticsByPrjNo(@Param("prjNo") String prjNo, @Param("today") String today);
}
