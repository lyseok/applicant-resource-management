package kr.or.ddit.rest.workhistory.service;

import java.util.List;

import kr.or.ddit.dto.WorkHistoryDTO;
import kr.or.ddit.vo.project.WorkHistoryVO;

public interface WorkHistoryService {
	public List<WorkHistoryVO> getWorkHistoryByPrjNo(String prjNo);
	
    /**
     * 전체 최근 작업 내역 조회
     */
    public List<WorkHistoryDTO> getRecentWorkHistory(int limit) ;

    /**
     * 프로젝트별 최근 작업 내역 조회
     */
    public List<WorkHistoryDTO> getProjectRecentWorkHistory(String projectId, int limit) ;

    /**
     * 프로젝트별 전체 작업 내역 조회
     */
    public List<WorkHistoryDTO> getProjectWorkHistory(String projectId) ;

    /**
     * 작업 내역 생성
     */
    public WorkHistoryDTO createWorkHistory(WorkHistoryDTO WorkHistoryDTO);

    /**
     * 작업 내역 자동 생성 (다른 서비스에서 호출)
     */
    public void createAutoWorkHistory(String prjNo, String userId, String workTable, 
                                    String workType, String workTarget, String workContent);
}
