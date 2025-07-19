package kr.or.ddit.rest.project.controller;

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

import kr.or.ddit.vo.project.ProjectVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/projects")
@Slf4j
@RequiredArgsConstructor
public class ProjectRestController {
	@GetMapping("/my")
	public List<ProjectVO> getMyProjectListApi() {
		return null;
	}
	
	@GetMapping("/{prjNo}")
	public ProjectVO getProjectApi(@PathVariable String prjNo) {
		return null;
	}
	
	@PostMapping
	public ProjectVO postProjectApi(@RequestBody ProjectVO projectVO) {
		return null;
	}
	
	@PutMapping("/{prjNo}")
	public ProjectVO putProjectApi(@RequestBody ProjectVO projectVO) {
		return null;
	}
	
	@DeleteMapping("/{prjNo}")
	public ResponseEntity<?> deleteProjectApi() {
		return null;
	}
}
