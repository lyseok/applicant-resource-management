package kr.or.ddit.rest.workhistory.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.vo.project.WorkHistoryVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class WorkHistoryRestController {
	@GetMapping("/projects/{prjNo}/work_history")
	public List<WorkHistoryVO> getWorkHistoryApi(){
		return null;
	}
	
	@PostMapping("/work_history") 
	public WorkHistoryVO postWorkHistory(@RequestBody WorkHistoryVO workHistory) {
		return null;
	}
}
