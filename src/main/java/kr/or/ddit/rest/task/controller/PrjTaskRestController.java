package kr.or.ddit.rest.task.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.rest.task.service.PrjTaskService;
import kr.or.ddit.vo.project.PrjTaskStatisticsVO;
import kr.or.ddit.vo.project.PrjTaskVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api/tasks")
@Slf4j
@RequiredArgsConstructor
public class PrjTaskRestController {
	private final PrjTaskService prjTaskService;
	
	@GetMapping("/{prjNo}/tasks")
	public ResponseEntity<List<PrjTaskVO>> getPrjTaskApi(
	        @PathVariable String prjNo,
	        @RequestParam(required = false) String status,
	        @RequestParam(required = false) String userId
	) {
	    List<PrjTaskVO> list = prjTaskService.readProjectTaskList(prjNo, status, userId);
	    return ResponseEntity.ok(list);
	}
	
	@PostMapping
    public ResponseEntity<PrjTaskVO> createTask(@RequestBody PrjTaskVO prjTaskVO) {
        PrjTaskVO createdTask = prjTaskService.createTask(prjTaskVO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }
	
	@PutMapping("/{taskNo}")
	public ResponseEntity<PrjTaskVO> putPrjTaskApi(
	        @PathVariable String taskNo,
	        @RequestBody PrjTaskVO prjTask) {
	    PrjTaskVO updatedTask = prjTaskService.updateTask(taskNo, prjTask);
	    return ResponseEntity.ok(updatedTask);
	}
	
	@DeleteMapping("/{taskNo}")
	public ResponseEntity<?> deletePrjTaskApi(@PathVariable String taskNo) {
	    int updated = prjTaskService.deleteTask(taskNo); // 1: 성공, 0: 실패

	    if (updated > 0) {
	        // 성공 시 응답 형식 (API문서 참고)
	        Map<String, Object> body = new HashMap<>();
	        body.put("message", "작업이 성공적으로 삭제되었습니다.");
	        body.put("taskNo", taskNo);
	        return ResponseEntity.ok(body);
	    } else {
	        // 실패 (이미 삭제 or 없는 taskNo)
	        Map<String, Object> body = new HashMap<>();
	        body.put("error", "NOT_FOUND");
	        body.put("message", "요청한 작업을 찾을 수 없습니다.");
	        body.put("taskNo", taskNo);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
	    }
	}
	
	@PatchMapping("/{taskNo}/status")
	public ResponseEntity<PrjTaskVO> patchPrjTaskStatusApi(
	        @PathVariable String taskNo,
	        @RequestBody Map<String, String> statusData
	) {
	    String taskStatus = statusData.get("taskStatus");
	    PrjTaskVO updatedTask = prjTaskService.updateTaskStatus(taskNo, taskStatus);
	    return ResponseEntity.ok(updatedTask);
	}

	@PatchMapping("/{taskNo}/progress")
	public ResponseEntity<PrjTaskVO> patchPrjTaskProgressApi(
	        @PathVariable String taskNo,
	        @RequestBody Map<String, String> progressData
	) {
	    String progressRate = progressData.get("progressRate");
	    PrjTaskVO updatedTask = prjTaskService.updateTaskProgress(taskNo, progressRate);
	    return ResponseEntity.ok(updatedTask);
	}
	
	@GetMapping("/{prjNo}/tasks/statistics")
    public ResponseEntity<PrjTaskStatisticsVO> getTaskStatistics(@PathVariable String prjNo) {
        PrjTaskStatisticsVO stats = prjTaskService.getTaskStatistics(prjNo);
        return ResponseEntity.ok(stats);
    }
}
