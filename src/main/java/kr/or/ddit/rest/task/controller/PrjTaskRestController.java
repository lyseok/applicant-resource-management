package kr.or.ddit.rest.task.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.vo.project.PrjTaskVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/tasks")
@Slf4j
@RequiredArgsConstructor
public class PrjTaskRestController {
	@GetMapping("/{prjNo}/tasks")
	public List<PrjTaskVO> getPrjTaskApi(@PathVariable String prjNo){
		return null;
	}
	
	@PostMapping
	public PrjTaskVO createPrjTaskApi(@RequestBody PrjTaskVO prjTask) {
		return null;
	}
	
	@PutMapping("/{taskNo}")
	public PrjTaskVO putPrjTaskApi(@PathVariable String taskNo, @RequestBody PrjTaskVO prjTask) {
		return null;
	}
	
	@DeleteMapping("/taskNo")
	public ResponseEntity<?> deletePrjTaskApi(@PathVariable String taskNo){
		return null;
	}
	
	@PatchMapping("/{taskNo}/status")
	public PrjTaskVO patchPrjTaskStatusApi(@PathVariable String taskNo, @RequestBody Map<String, String> statusData) {
		return null;
	}
	
	@PatchMapping("/{taskNo}/progress")
	public PrjTaskVO patchPrjTaskProgressApi(@PathVariable String taskNo, @RequestBody Map<String, String> progressData) {
		return null;
	}
	
	
}
