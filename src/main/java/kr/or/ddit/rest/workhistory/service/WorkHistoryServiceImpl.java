package kr.or.ddit.rest.workhistory.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.dto.WorkHistoryDTO;
import kr.or.ddit.mapper.project.WorkHistoryMapper;
import kr.or.ddit.vo.project.WorkHistoryVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class WorkHistoryServiceImpl implements WorkHistoryService {
	private final WorkHistoryMapper workHistoryMapper;

    public List<WorkHistoryVO> getWorkHistoryByPrjNo(String prjNo) {
        return workHistoryMapper.selectWorkHistoryByPrjNo(prjNo);
    }

    /**
     * 전체 최근 작업 내역 조회
     */
    public List<WorkHistoryDTO> getRecentWorkHistory(int limit) {
        log.info("전체 최근 작업 내역 조회 - limit: {}", limit);
        return workHistoryMapper.selectRecentWorkHistory(limit);
    }

    /**
     * 프로젝트별 최근 작업 내역 조회
     */
    public List<WorkHistoryDTO> getProjectRecentWorkHistory(String projectId, int limit) {
        log.info("프로젝트별 최근 작업 내역 조회 - projectId: {}, limit: {}", projectId, limit);
        return workHistoryMapper.selectProjectRecentWorkHistory(projectId, limit);
    }

    /**
     * 프로젝트별 전체 작업 내역 조회
     */
    public List<WorkHistoryDTO> getProjectWorkHistory(String projectId) {
        log.info("프로젝트별 전체 작업 내역 조회 - projectId: {}", projectId);
        return workHistoryMapper.selectProjectWorkHistory(projectId);
    }

    /**
     * 작업 내역 생성
     */
    public WorkHistoryDTO createWorkHistory(WorkHistoryDTO WorkHistoryDTO) {
        log.info("작업 내역 생성 - workType: {}, workTable: {}", 
                WorkHistoryDTO.getWorkType(), WorkHistoryDTO.getWorkTable());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        WorkHistoryDTO.setUserId(username);
        
        // 작업 일시 설정
        WorkHistoryDTO.setWorkDate(LocalDateTime.now());
        
        // 작업 내역 저장
        workHistoryMapper.insertWorkHistory(WorkHistoryDTO);
        
        // 생성된 작업 내역 조회 후 반환
        return workHistoryMapper.selectWorkHistoryById(WorkHistoryDTO.getWorkHistNo());
    }

    /**
     * 작업 내역 자동 생성 (다른 서비스에서 호출)
     */
    public void createAutoWorkHistory(String prjNo, String userId, String workTable, 
            							String workType, String workTarget, String workContent) {
		WorkHistoryDTO workHistoryDTO = new WorkHistoryDTO();
		workHistoryDTO.setPrjNo(prjNo);
		workHistoryDTO.setUserId(userId);
		workHistoryDTO.setWorkDate(LocalDateTime.now());
		workHistoryDTO.setWorkTable(workTable);
		workHistoryDTO.setWorkType(workType);
		workHistoryDTO.setWorkTarget(workTarget);
		workHistoryDTO.setWorkContent(workContent);
		
		workHistoryMapper.insertWorkHistory(workHistoryDTO);
		log.info("자동 작업 내역 생성 완료 - prjNo: {}, workType: {}", prjNo, workType);
	}

}
