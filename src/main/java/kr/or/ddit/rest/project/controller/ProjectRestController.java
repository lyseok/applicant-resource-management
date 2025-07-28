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

import kr.or.ddit.rest.project.service.ProjectService;
import kr.or.ddit.vo.project.ProjectVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/projects")
@Slf4j
@RequiredArgsConstructor
public class ProjectRestController {
	private final ProjectService projectService;
	
	@GetMapping("/my")
    public ResponseEntity<List<ProjectVO>> getMyProjects() {
        List<ProjectVO> list = projectService.readMyProjectList();
        return ResponseEntity.ok(list);
    }
	
	@GetMapping("/{prjNo}")
    public ResponseEntity<ProjectVO> getProjectDetail(@PathVariable String prjNo) {
        ProjectVO project = projectService.readProjectDetail(prjNo);
        if (project == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(project);
    }
	
	@PostMapping
	public ProjectVO postProjectApi(@RequestBody ProjectVO projectVO) {
		return null;
	}
	
	@PutMapping("/{prjNo}")
    public ResponseEntity<ProjectVO> updateProject(
            @PathVariable String prjNo,
            @RequestBody ProjectVO projectVO // 요청 body
    ) {
        // 서비스에서 수정 처리 및 VO 반환
        ProjectVO updated = projectService.modifyProject(prjNo, projectVO);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }
	
	@DeleteMapping("/{prjNo}")
	public ResponseEntity<?> deleteProjectApi() {
		return null;
	}
}
