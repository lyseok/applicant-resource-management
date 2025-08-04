package kr.or.ddit.member.project.project.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.member.project.project.service.MemberProjectAjaxService;
import kr.or.ddit.vo.project.ProjectVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ajax/member/project")
@RequiredArgsConstructor
public class MemberProjectAjaxController {
	private final MemberProjectAjaxService service;

	@GetMapping
	public List<ProjectVO> getProjectList(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    	String username = authentication.getName();
    	return service.readProjectUserIdList(username);	    	

	}
	
	@GetMapping("/detail/{prjNo}")
	public ProjectVO getProjectByPk(@PathVariable String prjNo) {
		return service.readProjectByPk(prjNo);
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createProjectEditPrjAnncBbs(@RequestBody ProjectVO project){
		int result = service.createProject(project); // result가 1이면 성공
	    if(result == 1){
	        return ResponseEntity.ok("ok");
	    } else {
	        return ResponseEntity.status(500).body(0); // 실패 시 0 또는 에러
	    }
	}
}
