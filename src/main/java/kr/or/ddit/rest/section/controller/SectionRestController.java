package kr.or.ddit.rest.section.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.vo.project.PrjSectionVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class SectionRestController {
	@GetMapping("projects/{prjNo}/sections")
	public List<PrjSectionVO> getPrjSectionListApi(){
		return null;
	}
	
	@PostMapping("/section")
	public PrjSectionVO postPrjSectionApi(@RequestBody PrjSectionVO prjSection) {
		return null;
	}
	
	@PutMapping("/sections/{sectNo}")
	public PrjSectionVO putPrjSectionApi(@PathVariable String sectNo, @RequestBody PrjSectionVO prjSection) {
		return null;
	}
	
	@DeleteMapping("/sections/{sectNo}")
	public ResponseEntity<?> deletePrjSectionApi(@PathVariable String sectNo){
		return null;
	}
}
