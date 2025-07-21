package kr.or.ddit.rest.workhistory.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@PostMapping("/work-history")
    public ResponseEntity<WorkHistoryVO> postWorkHistory(@RequestBody WorkHistoryVO workHistory) {
        WorkHistoryVO result = workHistoryService.insertWorkHistory(workHistory);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
