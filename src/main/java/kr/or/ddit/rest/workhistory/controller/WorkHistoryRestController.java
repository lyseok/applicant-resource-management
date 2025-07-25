package kr.or.ddit.rest.workhistory.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.dto.ApiResponse;
import kr.or.ddit.dto.WorkHistoryDTO;
import kr.or.ddit.rest.workhistory.service.WorkHistoryService;
import kr.or.ddit.vo.project.WorkHistoryVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class WorkHistoryRestController {
	private final WorkHistoryService workHistoryService;
	
	@GetMapping("/projects/{prjNo}/work-history")
	public ResponseEntity<List<WorkHistoryVO>> getWorkHistoryApi(@PathVariable String prjNo) {
	    List<WorkHistoryVO> historyList = workHistoryService.getWorkHistoryByPrjNo(prjNo);
	    return ResponseEntity.ok(historyList);
	}
	
	
	/**
     * 전체 최근 작업 내역 조회
     */
    @GetMapping("/work-history/recent")
    public ResponseEntity<ApiResponse<List<WorkHistoryDTO>>> getRecentWorkHistory(
            @RequestParam(defaultValue = "10") int limit) {
        try {
            List<WorkHistoryDTO> workHistoryList = workHistoryService.getRecentWorkHistory(limit);
            return ResponseEntity.ok(ApiResponse.success(workHistoryList, "최근 작업 내역을 성공적으로 조회했습니다."));
        } catch (Exception e) {
            log.error("최근 작업 내역 조회 중 오류 발생", e);
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.error("최근 작업 내역 조회에 실패했습니다.", "WORK_HISTORY_FETCH_ERROR"));
        }
    }
    
    /**
     * 프로젝트별 최근 작업 내역 조회
     */
    @GetMapping("/work-history/{projectId}/recent")
    public ResponseEntity<ApiResponse<List<WorkHistoryDTO>>> getProjectRecentWorkHistory(
            @PathVariable String projectId,
            @RequestParam(defaultValue = "10") int limit) {
        try {
            List<WorkHistoryDTO> workHistoryList = workHistoryService.getProjectRecentWorkHistory(projectId, limit);
            return ResponseEntity.ok(ApiResponse.success(workHistoryList, "프로젝트 최근 작업 내역을 성공적으로 조회했습니다."));
        } catch (Exception e) {
            log.error("프로젝트 최근 작업 내역 조회 중 오류 발생: projectId={}", projectId, e);
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.error("프로젝트 최근 작업 내역 조회에 실패했습니다.", "PROJECT_WORK_HISTORY_FETCH_ERROR"));
        }
    }

    /**
     * 프로젝트별 전체 작업 내역 조회
     */
    @GetMapping("/work-history/{projectId}")
    public ResponseEntity<ApiResponse<List<WorkHistoryDTO>>> getProjectWorkHistory(
            @PathVariable String projectId) {
        try {
            List<WorkHistoryDTO> workHistoryList = workHistoryService.getProjectWorkHistory(projectId);
            return ResponseEntity.ok(ApiResponse.success(workHistoryList, "프로젝트 작업 내역을 성공적으로 조회했습니다."));
        } catch (Exception e) {
            log.error("프로젝트 작업 내역 조회 중 오류 발생: projectId={}", projectId, e);
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.error("프로젝트 작업 내역 조회에 실패했습니다.", "PROJECT_WORK_HISTORY_FETCH_ERROR"));
        }
    }

    /**
     * 작업 내역 생성
     */
    @PostMapping("/work-history")
    public ResponseEntity<ApiResponse<WorkHistoryDTO>> createWorkHistory(
            @RequestBody WorkHistoryDTO WorkHistoryDTO) {
        try {
            WorkHistoryDTO createdWorkHistory = workHistoryService.createWorkHistory(WorkHistoryDTO);
            return ResponseEntity.ok(ApiResponse.success(createdWorkHistory, "작업 내역이 성공적으로 생성되었습니다."));
        } catch (Exception e) {
            log.error("작업 내역 생성 중 오류 발생", e);
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.error("작업 내역 생성에 실패했습니다.", "WORK_HISTORY_CREATE_ERROR"));
        }
    }
}
