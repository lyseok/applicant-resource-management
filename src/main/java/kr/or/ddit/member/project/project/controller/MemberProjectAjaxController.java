package kr.or.ddit.member.project.project.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.admin.project.project.service.AdminProjectAjaxService;
import kr.or.ddit.vo.project.ProjectVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ajax/member/project")
@RequiredArgsConstructor
public class MemberProjectAjaxController {
	private final AdminProjectAjaxService service;

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
	
}
