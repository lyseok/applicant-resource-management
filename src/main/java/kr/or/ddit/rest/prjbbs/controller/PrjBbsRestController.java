package kr.or.ddit.rest.prjbbs.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.vo.project.PrjBbsVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class PrjBbsRestController {
	@GetMapping("/projects/{prjNo}/posts")
	public List<PrjBbsVO> getPrjBbsListApi(@RequestParam String page, @RequestParam String size){
		return null;
	}
	
	@GetMapping("/posts/{prjPostNo}")
	public PrjBbsVO getPrjBbsApi(@PathVariable String prjPostsNo) {
		return null;
	}
	
	@PostMapping("/posts")
	public PrjBbsVO postPrjBbsApi(@RequestBody PrjBbsVO prjBbs) {
		return null;
	}
	
	@PutMapping("/posts/{prjPostNo}")
	public PrjBbsVO putPrjBbsApi(@RequestBody PrjBbsVO prjBbs) {
		return null;
	}
	
	@DeleteMapping("/posts/{prjPostNo}")
	public ResponseEntity<?> deletePrjBbsApi(@PathVariable String prjPostNo) {
		return null;
	}
}
