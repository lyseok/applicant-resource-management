package kr.or.ddit.mapper.project;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.dto.WorkHistoryDTO;
import kr.or.ddit.vo.project.WorkHistoryVO;

@Mapper
public interface WorkHistoryMapper {
	public List<WorkHistoryVO> selectWorkHistoryList();
	public List<WorkHistoryVO> searchWorkHistoryList(String prjNo);
	public WorkHistoryVO selectWorkHistoryByPk(String workHistNo);
	
	public List<WorkHistoryVO> selectWorkHistoryByPrjNo(String prjNo);
	
	/**
     * 전체 최근 작업 내역 조회
     */
    public List<WorkHistoryDTO> selectRecentWorkHistory(@Param("limit") int limit);

    /**
     * 프로젝트별 최근 작업 내역 조회
     */
    public List<WorkHistoryDTO> selectProjectRecentWorkHistory(@Param("projectId") String projectId, 
                                                       @Param("limit") int limit);

    /**
     * 프로젝트별 전체 작업 내역 조회
     */
    public List<WorkHistoryDTO> selectProjectWorkHistory(@Param("projectId") String projectId);

    /**
     * 작업 내역 생성
     */
    public int insertWorkHistory(WorkHistoryDTO WorkHistoryDTO);

    /**
     * 작업 내역 상세 조회
     */
    public WorkHistoryDTO selectWorkHistoryById(@Param("workHistNo") String workHistNo);
}
