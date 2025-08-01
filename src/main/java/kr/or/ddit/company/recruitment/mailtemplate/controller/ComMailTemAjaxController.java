package kr.or.ddit.company.recruitment.mailtemplate.controller;

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

import kr.or.ddit.common.exception.DataInsertException;
import kr.or.ddit.company.recruitment.mailtemplate.service.ComMailTemService;
import kr.or.ddit.vo.recruitment.ComMailTemVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/ajax/company/mail")
public class ComMailTemAjaxController {
	private final ComMailTemService service;
	
	@GetMapping("list")
	public ResponseEntity<?> getMailTemplateList(){
		List<ComMailTemVO> list = service.readComMailTemList();
		
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{no}")
	public ResponseEntity<?> getMailTemplateDetail(@PathVariable String no){
		ComMailTemVO vo = new ComMailTemVO();
		vo.setTemNo(no);
		ComMailTemVO resp = service.readComMailTem(vo);
		
		return ResponseEntity.ok(resp);
	}
	
	@PostMapping
	public ResponseEntity<?> createMailTemplate(@RequestBody ComMailTemVO vo){
		int res = service.createComMailTem(vo);
		if(res > 0) {
			return ResponseEntity.ok("ok");			
		} else {
			throw new DataInsertException("데이터 추가 오류"); 						
		}
		
	}
	
	@PutMapping("/{no}")
	public ResponseEntity<?> putMailTemplate(@PathVariable String no, @RequestBody ComMailTemVO vo){
		vo.setTemNo(no);
		int res = service.modifyComMailTem(vo);
		
		if(res > 0) {
			return ResponseEntity.ok("ok");			
		} else {
			throw new DataInsertException("데이터 추가 오류"); 						
		}
	}
	
	@DeleteMapping("/{no}")
	public ResponseEntity<?> DeleteMailTemplate(@PathVariable String no){
		int res = service.removeComMailTem(no);
		if(res > 0) {
			return ResponseEntity.ok("ok");			
		} else {
			throw new DataInsertException("데이터 추가 오류"); 						
		}
	}
}
