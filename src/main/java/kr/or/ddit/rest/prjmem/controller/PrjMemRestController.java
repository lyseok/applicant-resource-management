package kr.or.ddit.rest.prjmem.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.vo.project.PrjMemVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/projects")
@Slf4j
@RequiredArgsConstructor
public class PrjMemRestController {
	@GetMapping("/{prjNo}/members/{userId}")
	public List<PrjMemVO> getPrjMemListApi() {
		return null;
	}
	
	@PostMapping("/{prjNo}/members")
	public PrjMemVO addPrjMemApi(@RequestBody PrjMemVO prjMemVO) {
		return null;
	}
	
	@PutMapping("/{prjNo}/members/{userId}")
	public PrjMemVO memberAuthModifyApi(
		@PathVariable String prjNo,
	    @PathVariable String userId,
	    @RequestBody Map<String, String> authData
	){
		return null;
	}
	
	@DeleteMapping("/{prjNo}/members/{userId}")
	public ResponseEntity<?> removePrjMemApi() {
		return null;
	}
	
}
