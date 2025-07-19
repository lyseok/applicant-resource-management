package kr.or.ddit.rest.comment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.vo.project.PrjBbsCommentVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/comments")
@Slf4j
@RequiredArgsConstructor
public class PrjBbsCommentRestController {
	@PostMapping
	public PrjBbsCommentVO postPrjBbsCommentApi(@RequestBody PrjBbsCommentVO prjBbsComment) {
		return null;
	}
	
	@PutMapping("/{commentNo}")
	public PrjBbsCommentVO putPrjBbsCommentApi(@PathVariable String commentNo, @RequestBody PrjBbsCommentVO prjBbsComment) {
		return null;
	} 
	
	@DeleteMapping("/{commentNo}")
	public ResponseEntity<?> deletePrjBbsCommentApi(@PathVariable String commentNo){
		return null;
	}
}
